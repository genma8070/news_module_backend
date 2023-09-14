package com.example.news_module.service.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.news_module.entity.News;
import com.example.news_module.repository.NewsDao;
import com.example.news_module.service.ifs.NewsService;
import com.example.news_module.vo.request.NewsRequest;
import com.example.news_module.vo.response.NewsResponse;
import com.example.news_module.vo.response.NewsWithCategoryNameVo;

//ニュースのメゾッドを実装するクラス
@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	private NewsDao newsDao;

//	ニュースを追加する
	@Override
	public NewsResponse addNews(NewsRequest req) {
		String title = req.getTitle();
		String subTitle = req.getSubTitle();
		String text = req.getText();
		Integer mainCategory = req.getMainCategory();
		Integer subCategory = req.getSubCategory();
//		開放日を設定しない場合は追加成功した時点の時間になります
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime open = (req.getOpenDate() != null) ? req.getOpenDate() : now;
		Boolean status = null;
		if (title.isEmpty() || text.isEmpty() || subTitle.isEmpty()) {
			return new NewsResponse("標題及內容都須填寫");
		}
		if (mainCategory == null) {
			return new NewsResponse("請選擇父分類");
		}
		if (subCategory == null) {
			return new NewsResponse("請選擇子分類");
		}

//		開放日は追加した時間より以前なら公開状態を公開にします
		if (open.compareTo(now) <= 0) {
			status = true;
		} else {
			status = false;
		}

		News news = new News(mainCategory, subCategory, title, subTitle, text, status, now, open);
		newsDao.save(news);
		return new NewsResponse("新增成功");

	}

//	ニュースを更新する
	@Override
	public NewsResponse updateNews(NewsRequest req) {
		String title = req.getTitle();
		String subTitle = req.getSubTitle();
		String text = req.getText();
		Integer mainCategory = req.getMainCategory();
		Integer subCategory = req.getSubCategory();
		LocalDateTime open = req.getOpenDate();
		if (title.isEmpty() || text.isEmpty() || subTitle.isEmpty()) {
			return new NewsResponse("標題及內容都須填寫");
		}
		if (mainCategory == null) {
			return new NewsResponse("請選擇父分類");
		}
		if (subCategory == null) {
			return new NewsResponse("請選擇子分類");
		}

//		更新するニュースを取得する
		Optional<News> news = newsDao.findById(req.getNewsId());
		LocalDateTime now = LocalDateTime.now();
		News target = news.get();
		target.setMainCategory(mainCategory);
		target.setSubCategory(subCategory);
		target.setTitle(title);
		target.setSubTitle(subTitle);
		target.setText(text);
		target.setUpdateDate(now);
		target.setOpenDate(open);
		newsDao.save(target);
		return new NewsResponse("更新成功");

	}

//	ニュースの公開状態を非公開に変更する
	@Override
	public NewsResponse hideNews(NewsRequest req) {

//		選択したニュースのIDリストを取得する
		List<Integer> targets = req.getList();
		LocalDateTime now = LocalDateTime.now();
		for (Integer target : targets) {
			Optional<News> news = newsDao.findById(target);
			News n = news.get();
//			既に非公開中かどうかを判断する
			if (!n.getOpen()) {
				continue;
			} else {
				n.setOpen(false);
				n.setUpdateDate(now);
				n.setOpenDate(null);
				newsDao.save(n);
			}
		}
		return new NewsResponse("隱藏成功");

	}

//	ニュースの公開状態を公開に変更する
	@Override
	public NewsResponse openNews(NewsRequest req) {

//		選択したニュースのIDリストを取得する
		List<Integer> targets = req.getList();
		LocalDateTime now = LocalDateTime.now();
		for (Integer target : targets) {
			Optional<News> news = newsDao.findById(target);
			News n = news.get();

//			既に公開中かどうかを判断する
			if (n.getOpen()) {
				continue;
			} else {
				n.setOpen(true);
				n.setUpdateDate(now);
				n.setOpenDate(now);
				newsDao.save(n);
			}
		}
		return new NewsResponse("公開成功");

	}

//	該当IDのカテゴリ名称を含めているニュースデータを取得する
	@Override
	public NewsWithCategoryNameVo findFullNewsById(NewsRequest req) {
		Integer id = req.getNewsId();
		List<Map<String, Object>> res = newsDao.findFullNewsById(id);
		NewsWithCategoryNameVo e = new NewsWithCategoryNameVo();

		for (Map<String, Object> map : res) {

			for (String item : map.keySet()) {
				switch (item) {
				case "news_id":
					e.setNewsId((Integer) map.get(item));
					break;
				case "main_category":
					e.setMainCategory((Integer) map.get(item));
					break;
				case "sub_category":
					e.setSubCategory((Integer) map.get(item));
					break;
				case "main_category_name":
					e.setMainCategoryName((String) map.get(item));
					break;
				case "sub_category_name":
					e.setSubCategoryName((String) map.get(item));
					break;
				case "title":
					e.setTitle((String) map.get(item));
					break;
				case "sub_title":
					e.setSubTitle((String) map.get(item));
					break;
				case "text":
					e.setText((String) map.get(item));
					break;
				case "creat_date":
					e.setCreatDate(((Timestamp) map.get(item)).toLocalDateTime());
					break;
				case "updata_date":
//					NULLPOINT回避の判断式
					if (map.get(item) != null) {
						e.setUpdateDate(((Timestamp) map.get(item)).toLocalDateTime());
						break;
					} else {
						e.setUpdateDate(null);
						break;
					}
				case "open_date":
//					NULLPOINT回避の判断式
					if (map.get(item) != null) {
						e.setOpenDate(((Timestamp) map.get(item)).toLocalDateTime());
						break;
					} else {
						e.setOpenDate(null);
						break;
					}
				case "open":
					Object value = map.get(item);
					int intValue = Integer.parseInt(value.toString());
					if (intValue == 0) {
						e.setOpen(false);
					} else {
						e.setOpen(true);
					}
					break;
				}
			}
		}
		return e;
	}

//	選択したページの公開中ニュースを取得する（ユーザー側、公開日降順）
	@Override
	public NewsWithCategoryNameVo findAllNewsF(NewsRequest newReq) {

		List<NewsWithCategoryNameVo> eList = new ArrayList<NewsWithCategoryNameVo>();

//		ページ数を意味する引数
		Integer index = newReq.getIndex();

//		ページごとに表示するニュース数を意味する引数
		Integer items = newReq.getItems();

//		該当ページの指定数の公開中ニュースを取得する
		List<Map<String, Object>> res = newsDao.findAllNewsPagingF(index, items);

		for (Map<String, Object> map : res) {
			NewsWithCategoryNameVo e = new NewsWithCategoryNameVo();
			for (String item : map.keySet()) {
				switch (item) {
				case "news_id":
					e.setNewsId((Integer) map.get(item));
					break;
				case "main_category":
					e.setMainCategory((Integer) map.get(item));
					break;
				case "sub_category":
					e.setSubCategory((Integer) map.get(item));
					break;
				case "main_category_name":
					e.setMainCategoryName((String) map.get(item));
					break;
				case "sub_category_name":
					e.setSubCategoryName((String) map.get(item));
					break;
				case "title":
					e.setTitle((String) map.get(item));
					break;
				case "sub_title":
					e.setSubTitle((String) map.get(item));
					break;
				case "text":
					e.setText((String) map.get(item));
					break;
				case "creat_date":
					e.setCreatDate(((Timestamp) map.get(item)).toLocalDateTime());
					break;
				case "updata_date":
//					NULLPOINT回避の判断式
					if (map.get(item) != null) {
						e.setUpdateDate(((Timestamp) map.get(item)).toLocalDateTime());
						break;
					} else {
						e.setUpdateDate(null);
						break;
					}
				case "open_date":
//					NULLPOINT回避の判断式
					if (map.get(item) != null) {
						e.setOpenDate(((Timestamp) map.get(item)).toLocalDateTime());
						break;
					} else {
						e.setOpenDate(null);
						break;
					}
				case "open":
					Object value = map.get(item);
					int intValue = Integer.parseInt(value.toString());
					if (intValue == 0) {
						e.setOpen(false);
					} else {
						e.setOpen(true);
					}
					break;
				}

			}
			eList.add(e);
		}
		if (eList.size() == 0) {
			return new NewsWithCategoryNameVo("此頁無資料");
		}
		return new NewsWithCategoryNameVo(eList);

	}

//	全ての公開中ニュースを取得する（ユーザー側、公開日降順）
	@Override
	public NewsWithCategoryNameVo findAllF() {

		List<NewsWithCategoryNameVo> eList = new ArrayList<NewsWithCategoryNameVo>();

		List<Map<String, Object>> res = newsDao.findAllNewsF();

		for (Map<String, Object> map : res) {
			NewsWithCategoryNameVo e = new NewsWithCategoryNameVo();
			for (String item : map.keySet()) {
				switch (item) {
				case "news_id":
					e.setNewsId((Integer) map.get(item));
					break;
				case "main_category":
					e.setMainCategory((Integer) map.get(item));
					break;
				case "sub_category":
					e.setSubCategory((Integer) map.get(item));
					break;
				case "main_category_name":
					e.setMainCategoryName((String) map.get(item));
					break;
				case "sub_category_name":
					e.setSubCategoryName((String) map.get(item));
					break;
				case "title":
					e.setTitle((String) map.get(item));
					break;
				case "sub_title":
					e.setSubTitle((String) map.get(item));
					break;
				case "text":
					e.setText((String) map.get(item));
					break;
				case "creat_date":
					e.setCreatDate(((Timestamp) map.get(item)).toLocalDateTime());
					break;
//					NULLPOINT回避の判断式
				case "updata_date":
					if (map.get(item) != null) {
						e.setUpdateDate(((Timestamp) map.get(item)).toLocalDateTime());
						break;
					} else {
						e.setUpdateDate(null);
						break;
					}
//					NULLPOINT回避の判断式
				case "open_date":
					if (map.get(item) != null) {
						e.setOpenDate(((Timestamp) map.get(item)).toLocalDateTime());
						break;
					} else {
						e.setOpenDate(null);
						break;
					}
				case "open":
					Object value = map.get(item);
					int intValue = Integer.parseInt(value.toString());
					if (intValue == 0) {
						e.setOpen(false);
					} else {
						e.setOpen(true);
					}
					break;
				}
			}
//			非公開のニュースが公開時間に到達したかどうかを判断する	
			if (!e.getOpen()) {
				LocalDateTime now = LocalDateTime.now();
				if (e.getOpenDate().compareTo(now) <= 0) {
//				到達した場合は公開状態を公開にして更新する
					e.setOpen(true);
					News news = new News(e.getNewsId(), e.getOpen());
					newsDao.save(news);
				}
			}
//			公開中のニュースだけリストに追加する
			if (e.getOpen()) {
				eList.add(e);
			}
		}
		if (eList.size() == 0) {
			return new NewsWithCategoryNameVo("此頁無資料");
		}
		return new NewsWithCategoryNameVo(eList);

	}

//	選択したページの検索条件と一致するニュースを取得する（管理者側、公開日降順）
	@Override
	public NewsWithCategoryNameVo searchNewsBDesc(NewsRequest req) {

//		タイトルで検索
		String title = req.getTitle();

//		時区間で検索（片方の検索もOK）
		LocalDateTime startTime = req.getStartDate();
		LocalDateTime endTime = req.getEndDate();

//		ページ数を意味する引数
		Integer index = req.getIndex();

//		ページごとに表示するニュース数を意味する引数
		Integer items = req.getItems();

//		カテゴリで検索
		Integer main = req.getMainCategory();
		Integer sub = req.getSubCategory();

//		後ろの日付けが前の日付けより前かどうかの判断式
		if (startTime != null && endTime != null && startTime.compareTo(endTime) > 0) {
			return new NewsWithCategoryNameVo("結束時間不可早於開始時間");
		}

		List<NewsWithCategoryNameVo> eList = new ArrayList<NewsWithCategoryNameVo>();

//		検索条件を基づいて検索する
		List<Map<String, Object>> res = newsDao.findNewsByTitleOrCategoryOrDateBDesc(title, main, sub, startTime,
				endTime, index, items);

		for (Map<String, Object> map : res) {
			NewsWithCategoryNameVo e = new NewsWithCategoryNameVo();
			for (String item : map.keySet()) {
				switch (item) {
				case "news_id":
					e.setNewsId((Integer) map.get(item));
					break;
				case "main_category":
					e.setMainCategory((Integer) map.get(item));
					break;
				case "sub_category":
					e.setSubCategory((Integer) map.get(item));
					break;
				case "main_category_name":
					e.setMainCategoryName((String) map.get(item));
					break;
				case "sub_category_name":
					e.setSubCategoryName((String) map.get(item));
					break;
				case "title":
					e.setTitle((String) map.get(item));
					break;
				case "sub_title":
					e.setSubTitle((String) map.get(item));
					break;
				case "text":
					e.setText((String) map.get(item));
					break;
				case "creat_date":
					e.setCreatDate(((Timestamp) map.get(item)).toLocalDateTime());
					break;
				case "updata_date":
//					NULLPOINT回避の判断式
					if (map.get(item) != null) {
						e.setUpdateDate(((Timestamp) map.get(item)).toLocalDateTime());
						break;
					} else {
						e.setUpdateDate(null);
						break;
					}
				case "open_date":
//					NULLPOINT回避の判断式
					if (map.get(item) != null) {
						e.setOpenDate(((Timestamp) map.get(item)).toLocalDateTime());
						break;
					} else {
						e.setOpenDate(null);
						break;
					}
				case "open":
					Object value = map.get(item);
					int intValue = Integer.parseInt(value.toString());
					if (intValue == 0) {
						e.setOpen(false);
					} else {
						e.setOpen(true);
					}
					break;
				}
			}
			eList.add(e);
		}
		if (eList.size() == 0) {
			return new NewsWithCategoryNameVo("查無資料");
		}
		return new NewsWithCategoryNameVo(eList);

	}

//	検索条件と一致するニュースを取得する（管理者側、公開日降順）
	@Override
	public NewsWithCategoryNameVo searchNewsAllBDesc(NewsRequest req) {
//		不輸入則搜尋全部所以不用設定防空白
//		タイトルで検索
		String title = req.getTitle();

//		時区間で検索（片方の検索もOK）
		LocalDateTime startTime = req.getStartDate();
		LocalDateTime endTime = req.getEndDate();

//		カテゴリで検索
		Integer main = req.getMainCategory();
		Integer sub = req.getSubCategory();

		if (startTime != null && endTime != null && startTime.compareTo(endTime) > 0) {
			return new NewsWithCategoryNameVo("結束時間不可早於開始時間");
		}

		List<NewsWithCategoryNameVo> eList = new ArrayList<NewsWithCategoryNameVo>();

		List<Map<String, Object>> res = newsDao.findAllNewsByTitleOrCategoryOrDateBDesc(title, main, sub, startTime,
				endTime);

//		將找出的問卷物件重組
		for (Map<String, Object> map : res) {
			NewsWithCategoryNameVo e = new NewsWithCategoryNameVo();
			for (String item : map.keySet()) {
				switch (item) {
				case "news_id":
					e.setNewsId((Integer) map.get(item));
					break;
				case "main_category":
					e.setMainCategory((Integer) map.get(item));
					break;
				case "sub_category":
					e.setSubCategory((Integer) map.get(item));
					break;
				case "main_category_name":
					e.setMainCategoryName((String) map.get(item));
					break;
				case "sub_category_name":
					e.setSubCategoryName((String) map.get(item));
					break;
				case "title":
					e.setTitle((String) map.get(item));
					break;
				case "sub_title":
					e.setSubTitle((String) map.get(item));
					break;
				case "text":
					e.setText((String) map.get(item));
					break;
				case "creat_date":
					e.setCreatDate(((Timestamp) map.get(item)).toLocalDateTime());
					break;
				case "updata_date":
//					NULLPOINT回避の判断式
					if (map.get(item) != null) {
						e.setUpdateDate(((Timestamp) map.get(item)).toLocalDateTime());
						break;
					} else {
						e.setUpdateDate(null);
						break;
					}
				case "open_date":
//					NULLPOINT回避の判断式
					if (map.get(item) != null) {
						e.setOpenDate(((Timestamp) map.get(item)).toLocalDateTime());
						break;
					} else {
						e.setOpenDate(null);
						break;
					}
				case "open":
					Object value = map.get(item);
					int intValue = Integer.parseInt(value.toString());
					if (intValue == 0) {
						e.setOpen(false);
					} else {
						e.setOpen(true);
					}
					break;
				}
			}
//			非公開のニュースが公開時間に到達したかどうかを判断する	
			if (!e.getOpen()) {
				LocalDateTime now = LocalDateTime.now();
				if (e.getOpenDate().compareTo(now) <= 0) {
//				到達した場合は公開状態を公開にして更新する
					e.setOpen(true);
					News news = new News(e.getNewsId(), e.getOpen());
					newsDao.save(news);
				}
			}
			eList.add(e);
		}
		if (eList.size() == 0) {
			return new NewsWithCategoryNameVo("查無資料");
		}
		return new NewsWithCategoryNameVo(eList);

	}

//	選択したページのニュースを取得する（管理者側、公開日降順）
	@Override
	public NewsWithCategoryNameVo findAllNewsBDesc(NewsRequest newReq) {

		List<NewsWithCategoryNameVo> eList = new ArrayList<NewsWithCategoryNameVo>();

//		ページ数を意味する引数
		Integer index = newReq.getIndex();

//		ページごとに表示するニュース数を意味する引数
		Integer items = newReq.getItems();

		List<Map<String, Object>> res = newsDao.findAllNewsPagingBDesc(index, items);

//		將找出的問卷物件重組
		for (Map<String, Object> map : res) {
			NewsWithCategoryNameVo e = new NewsWithCategoryNameVo();
			for (String item : map.keySet()) {
				switch (item) {
				case "news_id":
					e.setNewsId((Integer) map.get(item));
					break;
				case "main_category":
					e.setMainCategory((Integer) map.get(item));
					break;
				case "sub_category":
					e.setSubCategory((Integer) map.get(item));
					break;
				case "main_category_name":
					e.setMainCategoryName((String) map.get(item));
					break;
				case "sub_category_name":
					e.setSubCategoryName((String) map.get(item));
					break;
				case "title":
					e.setTitle((String) map.get(item));
					break;
				case "sub_title":
					e.setSubTitle((String) map.get(item));
					break;
				case "text":
					e.setText((String) map.get(item));
					break;
				case "creat_date":
					e.setCreatDate(((Timestamp) map.get(item)).toLocalDateTime());
					break;
				case "updata_date":
//					NULLPOINT回避の判断式
					if (map.get(item) != null) {
						e.setUpdateDate(((Timestamp) map.get(item)).toLocalDateTime());
						break;
					} else {
						e.setUpdateDate(null);
						break;
					}
				case "open_date":
//					NULLPOINT回避の判断式
					if (map.get(item) != null) {
						e.setOpenDate(((Timestamp) map.get(item)).toLocalDateTime());
						break;
					} else {
						e.setOpenDate(null);
						break;
					}
				case "open":
					Object value = map.get(item);
					int intValue = Integer.parseInt(value.toString());
					if (intValue == 0) {
						e.setOpen(false);
					} else {
						e.setOpen(true);
					}
					break;
				}
			}
//			非公開のニュースが公開時間に到達したかどうかを判断する	
			if (!e.getOpen()) {
				LocalDateTime now = LocalDateTime.now();
				if (e.getOpenDate().compareTo(now) <= 0) {
//				到達した場合は公開状態を公開にして更新する
					e.setOpen(true);
					News news = new News(e.getNewsId(), e.getOpen());
					newsDao.save(news);
				}
			}
			eList.add(e);
		}
		if (eList.size() == 0) {
			return new NewsWithCategoryNameVo("此頁無資料");
		}
		return new NewsWithCategoryNameVo(eList);

	}

//	全てのニュースを取得する（管理者側、公開日降順）
	@Override
	public NewsWithCategoryNameVo findAllBDesc() {

		List<NewsWithCategoryNameVo> eList = new ArrayList<NewsWithCategoryNameVo>();

		List<Map<String, Object>> res = newsDao.findAllNewsBDesc();

		for (Map<String, Object> map : res) {
			NewsWithCategoryNameVo e = new NewsWithCategoryNameVo();
			for (String item : map.keySet()) {
				switch (item) {
				case "news_id":
					e.setNewsId((Integer) map.get(item));
					break;
				case "main_category":
					e.setMainCategory((Integer) map.get(item));
					break;
				case "sub_category":
					e.setSubCategory((Integer) map.get(item));
					break;
				case "main_category_name":
					e.setMainCategoryName((String) map.get(item));
					break;
				case "sub_category_name":
					e.setSubCategoryName((String) map.get(item));
					break;
				case "title":
					e.setTitle((String) map.get(item));
					break;
				case "sub_title":
					e.setSubTitle((String) map.get(item));
					break;
				case "text":
					e.setText((String) map.get(item));
					break;
				case "creat_date":
					e.setCreatDate(((Timestamp) map.get(item)).toLocalDateTime());
					break;
				case "updata_date":
//					NULLPOINT回避の判断式
					if (map.get(item) != null) {
						e.setUpdateDate(((Timestamp) map.get(item)).toLocalDateTime());
						break;
					} else {
						e.setUpdateDate(null);
						break;
					}
				case "open_date":
//					NULLPOINT回避の判断式
					if (map.get(item) != null) {
						e.setOpenDate(((Timestamp) map.get(item)).toLocalDateTime());
						break;
					} else {
						e.setOpenDate(null);
						break;
					}
				case "open":
					Object value = map.get(item);
					int intValue = Integer.parseInt(value.toString());
					if (intValue == 0) {
						e.setOpen(false);
					} else {
						e.setOpen(true);
					}
					break;
				}
			}
//			非公開のニュースが公開時間に到達したかどうかを判断する	
			if (!e.getOpen()) {
				LocalDateTime now = LocalDateTime.now();
				if (e.getOpenDate().compareTo(now) <= 0) {
//				到達した場合は公開状態を公開にして更新する
					e.setOpen(true);
					News news = new News(e.getNewsId(), e.getOpen());
					newsDao.save(news);
				}
			}
			eList.add(e);
		}
		if (eList.size() == 0) {
			return new NewsWithCategoryNameVo("此頁無資料");
		}
		return new NewsWithCategoryNameVo(eList);

	}
	
//	該当ページの検索条件と一致するニュースを取得する（管理者側、公開日昇順）
	@Override
	public NewsWithCategoryNameVo searchNewsBAsc(NewsRequest req) {
//		タイトルで検索
		String title = req.getTitle();

//		時区間で検索（片方の検索もOK）
		LocalDateTime startTime = req.getStartDate();
		LocalDateTime endTime = req.getEndDate();

//		ページ数を意味する引数
		Integer index = req.getIndex();

//		ページごとに表示するニュース数を意味する引数
		Integer items = req.getItems();

//		カテゴリで検索
		Integer main = req.getMainCategory();
		Integer sub = req.getSubCategory();
		
//		後ろの日付けが前の日付けより前かどうかの判断式
		if (startTime != null && endTime != null && startTime.compareTo(endTime) > 0) {
			return new NewsWithCategoryNameVo("結束時間不可早於開始時間");
		}

		List<NewsWithCategoryNameVo> eList = new ArrayList<NewsWithCategoryNameVo>();

		List<Map<String, Object>> res = newsDao.findNewsByTitleOrCategoryOrDateBAsc(title, main, sub, startTime,
				endTime, index, items);

//		將找出的問卷物件重組
		for (Map<String, Object> map : res) {
			NewsWithCategoryNameVo e = new NewsWithCategoryNameVo();
			for (String item : map.keySet()) {
				switch (item) {
				case "news_id":
					e.setNewsId((Integer) map.get(item));
					break;
				case "main_category":
					e.setMainCategory((Integer) map.get(item));
					break;
				case "sub_category":
					e.setSubCategory((Integer) map.get(item));
					break;
				case "main_category_name":
					e.setMainCategoryName((String) map.get(item));
					break;
				case "sub_category_name":
					e.setSubCategoryName((String) map.get(item));
					break;
				case "title":
					e.setTitle((String) map.get(item));
					break;
				case "sub_title":
					e.setSubTitle((String) map.get(item));
					break;
				case "text":
					e.setText((String) map.get(item));
					break;
				case "creat_date":
					e.setCreatDate(((Timestamp) map.get(item)).toLocalDateTime());
					break;
				case "updata_date":
//					NULLPOINT回避の判断式
					if (map.get(item) != null) {
						e.setUpdateDate(((Timestamp) map.get(item)).toLocalDateTime());
						break;
					} else {
						e.setUpdateDate(null);
						break;
					}
				case "open_date":
//					NULLPOINT回避の判断式
					if (map.get(item) != null) {
						e.setOpenDate(((Timestamp) map.get(item)).toLocalDateTime());
						break;
					} else {
						e.setOpenDate(null);
						break;
					}
				case "open":
					Object value = map.get(item);
					int intValue = Integer.parseInt(value.toString());
					if (intValue == 0) {
						e.setOpen(false);
					} else {
						e.setOpen(true);
					}
					break;
				}
			}
			eList.add(e);
		}
		if (eList.size() == 0) {
			return new NewsWithCategoryNameVo("查無資料");
		}
		return new NewsWithCategoryNameVo(eList);

	}

//	検索条件と一致するニュースを取得する（管理者側、公開日昇順）
	@Override
	public NewsWithCategoryNameVo searchNewsAllBAsc(NewsRequest req) {
//		タイトルで検索
		String title = req.getTitle();

//		時区間で検索（片方の検索もOK）
		LocalDateTime startTime = req.getStartDate();
		LocalDateTime endTime = req.getEndDate();

//		カテゴリで検索
		Integer main = req.getMainCategory();
		Integer sub = req.getSubCategory();
//		後ろの日付けが前の日付けより前かどうかの判断式
		if (startTime != null && endTime != null && startTime.compareTo(endTime) > 0) {
			return new NewsWithCategoryNameVo("結束時間不可早於開始時間");
		}

		List<NewsWithCategoryNameVo> eList = new ArrayList<NewsWithCategoryNameVo>();

		List<Map<String, Object>> res = newsDao.findAllNewsByTitleOrCategoryOrDateBAsc(title, main, sub, startTime,
				endTime);

//		將找出的問卷物件重組
		for (Map<String, Object> map : res) {
			NewsWithCategoryNameVo e = new NewsWithCategoryNameVo();
			for (String item : map.keySet()) {
				switch (item) {
				case "news_id":
					e.setNewsId((Integer) map.get(item));
					break;
				case "main_category":
					e.setMainCategory((Integer) map.get(item));
					break;
				case "sub_category":
					e.setSubCategory((Integer) map.get(item));
					break;
				case "main_category_name":
					e.setMainCategoryName((String) map.get(item));
					break;
				case "sub_category_name":
					e.setSubCategoryName((String) map.get(item));
					break;
				case "title":
					e.setTitle((String) map.get(item));
					break;
				case "sub_title":
					e.setSubTitle((String) map.get(item));
					break;
				case "text":
					e.setText((String) map.get(item));
					break;
				case "creat_date":
					e.setCreatDate(((Timestamp) map.get(item)).toLocalDateTime());
					break;
				case "updata_date":
//					NULLPOINT回避の判断式
					if (map.get(item) != null) {
						e.setUpdateDate(((Timestamp) map.get(item)).toLocalDateTime());
						break;
					} else {
						e.setUpdateDate(null);
						break;
					}
				case "open_date":
//					NULLPOINT回避の判断式
					if (map.get(item) != null) {
						e.setOpenDate(((Timestamp) map.get(item)).toLocalDateTime());
						break;
					} else {
						e.setOpenDate(null);
						break;
					}
				case "open":
					Object value = map.get(item);
					int intValue = Integer.parseInt(value.toString());
					if (intValue == 0) {
						e.setOpen(false);
					} else {
						e.setOpen(true);
					}
					break;
				}
			}
//			非公開のニュースが公開時間に到達したかどうかを判断する	
			if (!e.getOpen()) {
				LocalDateTime now = LocalDateTime.now();
				if (e.getOpenDate().compareTo(now) <= 0) {
//				到達した場合は公開状態を公開にして更新する
					e.setOpen(true);
					News news = new News(e.getNewsId(), e.getOpen());
					newsDao.save(news);
				}
			}
			eList.add(e);
		}
		if (eList.size() == 0) {
			return new NewsWithCategoryNameVo("查無資料");
		}
		return new NewsWithCategoryNameVo(eList);

	}

//	選択したページのニュースを取得する（管理者側、公開日昇順）
	@Override
	public NewsWithCategoryNameVo findAllNewsBAsc(NewsRequest newReq) {

		List<NewsWithCategoryNameVo> eList = new ArrayList<NewsWithCategoryNameVo>();

//		ページ数を意味する引数
		Integer index = newReq.getIndex();

//		ページごとに表示するニュース数を意味する引数
		Integer items = newReq.getItems();

		List<Map<String, Object>> res = newsDao.findAllNewsPagingBAsc(index, items);

//		將找出的問卷物件重組
		for (Map<String, Object> map : res) {
			NewsWithCategoryNameVo e = new NewsWithCategoryNameVo();
			for (String item : map.keySet()) {
				switch (item) {
				case "news_id":
					e.setNewsId((Integer) map.get(item));
					break;
				case "main_category":
					e.setMainCategory((Integer) map.get(item));
					break;
				case "sub_category":
					e.setSubCategory((Integer) map.get(item));
					break;
				case "main_category_name":
					e.setMainCategoryName((String) map.get(item));
					break;
				case "sub_category_name":
					e.setSubCategoryName((String) map.get(item));
					break;
				case "title":
					e.setTitle((String) map.get(item));
					break;
				case "sub_title":
					e.setSubTitle((String) map.get(item));
					break;
				case "text":
					e.setText((String) map.get(item));
					break;
				case "creat_date":
					e.setCreatDate(((Timestamp) map.get(item)).toLocalDateTime());
					break;
				case "updata_date":
//					NULLPOINT回避の判断式
					if (map.get(item) != null) {
						e.setUpdateDate(((Timestamp) map.get(item)).toLocalDateTime());
						break;
					} else {
						e.setUpdateDate(null);
						break;
					}
				case "open_date":
//					NULLPOINT回避の判断式
					if (map.get(item) != null) {
						e.setOpenDate(((Timestamp) map.get(item)).toLocalDateTime());
						break;
					} else {
						e.setOpenDate(null);
						break;
					}
				case "open":
					Object value = map.get(item);
					int intValue = Integer.parseInt(value.toString());
					if (intValue == 0) {
						e.setOpen(false);
					} else {
						e.setOpen(true);
					}
					break;
				}
			}
			eList.add(e);
		}
		if (eList.size() == 0) {
			return new NewsWithCategoryNameVo("此頁無資料");
		}
		return new NewsWithCategoryNameVo(eList);

	}

//	全てのニュースを取得する（管理者側、公開日昇順）
	@Override
	public NewsWithCategoryNameVo findAllBAsc() {

		List<NewsWithCategoryNameVo> eList = new ArrayList<NewsWithCategoryNameVo>();

		List<Map<String, Object>> res = newsDao.findAllNewsBAsc();

		for (Map<String, Object> map : res) {
			NewsWithCategoryNameVo e = new NewsWithCategoryNameVo();
			for (String item : map.keySet()) {
				switch (item) {
				case "news_id":
					e.setNewsId((Integer) map.get(item));
					break;
				case "main_category":
					e.setMainCategory((Integer) map.get(item));
					break;
				case "sub_category":
					e.setSubCategory((Integer) map.get(item));
					break;
				case "main_category_name":
					e.setMainCategoryName((String) map.get(item));
					break;
				case "sub_category_name":
					e.setSubCategoryName((String) map.get(item));
					break;
				case "title":
					e.setTitle((String) map.get(item));
					break;
				case "sub_title":
					e.setSubTitle((String) map.get(item));
					break;
				case "text":
					e.setText((String) map.get(item));
					break;
				case "creat_date":
					e.setCreatDate(((Timestamp) map.get(item)).toLocalDateTime());
					break;
				case "updata_date":
//					NULLPOINT回避の判断式
					if (map.get(item) != null) {
						e.setUpdateDate(((Timestamp) map.get(item)).toLocalDateTime());
						break;
					} else {
						e.setUpdateDate(null);
						break;
					}
				case "open_date":
//					NULLPOINT回避の判断式
					if (map.get(item) != null) {
						e.setOpenDate(((Timestamp) map.get(item)).toLocalDateTime());
						break;
					} else {
						e.setOpenDate(null);
						break;
					}
				case "open":
					Object value = map.get(item);
					int intValue = Integer.parseInt(value.toString());
					if (intValue == 0) {
						e.setOpen(false);
					} else {
						e.setOpen(true);
					}
					break;
				}
			}
//			非公開のニュースが公開時間に到達したかどうかを判断する	
			if (!e.getOpen()) {
				LocalDateTime now = LocalDateTime.now();
				if (e.getOpenDate().compareTo(now) <= 0) {
//				到達した場合は公開状態を公開にして更新する
					e.setOpen(true);
					News news = new News(e.getNewsId(), e.getOpen());
					newsDao.save(news);
				}
			}
			eList.add(e);
		}
		if (eList.size() == 0) {
			return new NewsWithCategoryNameVo("此頁無資料");
		}
		return new NewsWithCategoryNameVo(eList);

	}

//	該当IDのニュースを取得する
	@Override
	public NewsResponse findNewsById(NewsRequest req) {
		Integer id = req.getNewsId();
		Optional<News> target = newsDao.findById(id);
		News news = target.get();
		return new NewsResponse(news);

	}

}
