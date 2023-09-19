package com.example.news_module.service.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.news_module.contants.Pattern;
import com.example.news_module.contants.MsgCode;
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
	public NewsResponse addOrUpdateNews(NewsRequest req) {
		String title = req.getTitle();
		String subTitle = req.getSubTitle();

		if (!title.matches(Pattern.TITLE.getPattern())) {
			return new NewsResponse(Pattern.TITLE.getMessage(), Pattern.TITLE.getType());
		}

		if (!subTitle.matches(Pattern.TITLE.getPattern())) {
			return new NewsResponse(Pattern.TITLE.getMessage(), Pattern.TITLE.getType());
		}

		String text = req.getText();

		if (!text.matches(Pattern.TEXT.getPattern())) {
			return new NewsResponse(Pattern.TEXT.getMessage(), Pattern.TEXT.getType());
		}
		Integer mainCategory = req.getMainCategory();
		Integer subCategory = req.getSubCategory();
//		開放日を設定しない場合は追加成功した時点の時間になります
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime open = (req.getOpenDate() != null) ? req.getOpenDate() : now;
		Integer status = null;
		if (title.isBlank() || text.isBlank() || subTitle.isBlank()) {
			return new NewsResponse(MsgCode.CANNOT_BLANK.getMessage(), MsgCode.CANNOT_BLANK.getType());
		}
		if (mainCategory == null || subCategory == null) {
			return new NewsResponse(MsgCode.CHOICE_CATEGORY.getMessage(), MsgCode.CHOICE_CATEGORY.getType());
		}

//		開放日は追加した時間より以前なら公開状態を公開にします
		if (open.compareTo(now) <= 0) {
			status = 1;
		} else {
			status = 0;
		}
		
		if (req.getNewsId() != null) {
//		更新するニュースを取得する
			Optional<News> news = newsDao.findById(req.getNewsId());
			News target = news.get();
			target.setMainCategory(mainCategory);
			target.setSubCategory(subCategory);
			target.setTitle(title);
			target.setSubTitle(subTitle);
			target.setText(text);
			target.setUpdateDate(now);
			target.setOpenDate(open);
			target.setOpen(status);
			newsDao.save(target);
			return new NewsResponse(MsgCode.NEWS_EDIT_SUCCESSFUL.getMessage(), MsgCode.NEWS_EDIT_SUCCESSFUL.getType());
		}

		News news = new News(mainCategory, subCategory, title, subTitle, text, status, now, open);
		newsDao.save(news);
		return new NewsResponse(MsgCode.NEWS_CREATE_SUCCESSFUL.getMessage(), MsgCode.NEWS_CREATE_SUCCESSFUL.getType());

	}

//	ニュースの公開状態を非公開に変更する
	@Override
	public NewsResponse hideNews(NewsRequest req) {

//		選択したニュースのIDリストを取得する
		List<Integer> targets = req.getList();

		if (targets.size() == 0) {
			return new NewsResponse(MsgCode.NO_TARGET.getMessage(), MsgCode.NO_TARGET.getType());
		}

		LocalDateTime now = LocalDateTime.now();
		for (Integer target : targets) {
			Optional<News> news = newsDao.findById(target);
			News n = news.get();
//			既に非公開中かどうかを判断する
			if (n.getOpen() != 1) {
				continue;
			} else {
				n.setOpen(2);
				n.setUpdateDate(now);
				newsDao.save(n);
			}
		}
		return new NewsResponse(MsgCode.NEWS_DELETE_SUCCESSFUL.getMessage(), MsgCode.NEWS_DELETE_SUCCESSFUL.getType());

	}

//	ニュースの公開状態を公開に変更する
	@Override
	public NewsResponse openNews(NewsRequest req) {

//		選択したニュースのIDリストを取得する
		List<Integer> targets = req.getList();

		if (targets.size() == 0) {
			return new NewsResponse(MsgCode.NO_TARGET.getMessage(), MsgCode.NO_TARGET.getType());
		}

		LocalDateTime now = LocalDateTime.now();
		for (Integer target : targets) {
			Optional<News> news = newsDao.findById(target);
			News n = news.get();

//			既に公開中かどうかを判断する
			if (n.getOpen() == 1) {
				continue;
			} else {
				n.setOpen(1);
				n.setUpdateDate(now);
				n.setOpenDate(now);
				newsDao.save(n);
			}
		}
		return new NewsResponse(MsgCode.NEWS_OPEN_SUCCESSFUL.getMessage(), MsgCode.NEWS_OPEN_SUCCESSFUL.getType());

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
					e.setOpen((Integer) map.get(item));
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
					e.setOpen((Integer) map.get(item));
					break;
				}

			}
			eList.add(e);
		}
		if (eList.size() == 0) {
			return new NewsWithCategoryNameVo(MsgCode.NOT_FOUND.getMessage(), MsgCode.NOT_FOUND.getType());
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
					e.setOpen((Integer) map.get(item));
					break;
				}
			}
//			非公開のニュースが公開時間に到達したかどうかを判断する	
			if (e.getOpen() == 0) {
				LocalDateTime now = LocalDateTime.now();
				if (e.getOpenDate().compareTo(now) <= 0) {
//				到達した場合は公開状態を公開にして更新する
					e.setOpen(1);
					News news = newsDao.findById(e.getNewsId()).get();
					news.setOpen(1);
					newsDao.save(news);
				}
			}
//			公開中のニュースだけリストに追加する
			if (e.getOpen() == 1) {
				eList.add(e);
			}
		}
		if (eList.size() == 0) {
			return new NewsWithCategoryNameVo(MsgCode.NOT_FOUND.getMessage(), MsgCode.NOT_FOUND.getType());
		}
		return new NewsWithCategoryNameVo(eList);

	}

//	選択したページの検索条件と一致するニュースを取得する（管理者側、公開日降順）
	@Override
	public NewsWithCategoryNameVo searchNewsBySort(NewsRequest req) {

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
			return new NewsWithCategoryNameVo(MsgCode.END_DATE_CANNOT_EARLY_THAN_START_DATE.getMessage(),
					MsgCode.END_DATE_CANNOT_EARLY_THAN_START_DATE.getType());
		}

		List<NewsWithCategoryNameVo> eList = new ArrayList<NewsWithCategoryNameVo>();

		Boolean sort = req.getSort();

//		検索条件を基づいて検索する
		List<Map<String, Object>> res = sort
				? newsDao.findNewsByTitleOrCategoryOrDateBDesc(title, main, sub, startTime, endTime, index, items)
				: newsDao.findNewsByTitleOrCategoryOrDateBAsc(title, main, sub, startTime, endTime, index, items);

		if (res == null) {
			return new NewsWithCategoryNameVo(MsgCode.NOT_FOUND.getMessage(), MsgCode.NOT_FOUND.getType());
		}
		
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
					e.setOpen((Integer) map.get(item));
					break;
				}
			}
			eList.add(e);
		}
		
		return new NewsWithCategoryNameVo(eList);

	}

//	検索条件と一致するニュースを取得する（管理者側、公開日降順）
	@Override
	public NewsWithCategoryNameVo searchNewsAllBySort(NewsRequest req) {
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
			return new NewsWithCategoryNameVo(MsgCode.END_DATE_CANNOT_EARLY_THAN_START_DATE.getMessage(),
					MsgCode.END_DATE_CANNOT_EARLY_THAN_START_DATE.getType());
		}

		List<NewsWithCategoryNameVo> eList = new ArrayList<NewsWithCategoryNameVo>();

		Boolean sort = req.getSort();

		List<Map<String, Object>> res = sort
				? newsDao.findAllNewsByTitleOrCategoryOrDateBDesc(title, main, sub, startTime, endTime)
				: newsDao.findAllNewsByTitleOrCategoryOrDateBAsc(title, main, sub, startTime, endTime);

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
					e.setOpen((Integer) map.get(item));
					break;
				}
			}
//			非公開のニュースが公開時間に到達したかどうかを判断する	
			if (e.getOpen() == 0) {
				LocalDateTime now = LocalDateTime.now();
				if (e.getOpenDate().compareTo(now) <= 0) {
//				到達した場合は公開状態を公開にして更新する
					e.setOpen(1);
					News news = newsDao.findById(e.getNewsId()).get();
					news.setOpen(1);
					newsDao.save(news);
				}
			}
			eList.add(e);
		}
		if (eList.size() == 0) {
			return new NewsWithCategoryNameVo(MsgCode.NOT_FOUND.getMessage(), MsgCode.NOT_FOUND.getType());
		}
		return new NewsWithCategoryNameVo(eList);

	}

//	選択したページのニュースを取得する（管理者側、公開日降順）
	@Override
	public NewsWithCategoryNameVo findAllNewsBySort(NewsRequest newReq) {

		Boolean sort = newReq.getSort();
		List<NewsWithCategoryNameVo> eList = new ArrayList<NewsWithCategoryNameVo>();

//		ページ数を意味する引数
		Integer index = newReq.getIndex();

//		ページごとに表示するニュース数を意味する引数
		Integer items = newReq.getItems();

		List<Map<String, Object>> res = sort ? newsDao.findAllNewsPagingBDesc(index, items)
				: newsDao.findAllNewsPagingBAsc(index, items);

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
					e.setOpen((Integer) map.get(item));
					break;
				}
			}
//			非公開のニュースが公開時間に到達したかどうかを判断する	
			if (e.getOpen() == 0) {
				LocalDateTime now = LocalDateTime.now();
				if (e.getOpenDate().compareTo(now) <= 0) {
//				到達した場合は公開状態を公開にして更新する
					e.setOpen(1);
					News news = newsDao.findById(e.getNewsId()).get();
					news.setOpen(1);
					newsDao.save(news);
				}
			}
			eList.add(e);
		}
		if (eList.size() == 0) {
			return new NewsWithCategoryNameVo(MsgCode.NOT_FOUND.getMessage(), MsgCode.NOT_FOUND.getType());
		}
		return new NewsWithCategoryNameVo(eList);

	}

//	全てのニュースを取得する（管理者側、公開日降順）
	@Override
	public NewsWithCategoryNameVo findAllBySort(NewsRequest newReq) {

		Boolean sort = newReq.getSort();

		List<NewsWithCategoryNameVo> eList = new ArrayList<NewsWithCategoryNameVo>();

		List<Map<String, Object>> res = sort ? newsDao.findAllNewsBDesc() : newsDao.findAllNewsBAsc();

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
					e.setOpen((Integer) map.get(item));
					break;
				}
			}
//			非公開のニュースが公開時間に到達したかどうかを判断する	
			if (e.getOpen() == 0) {
				LocalDateTime now = LocalDateTime.now();
				if (e.getOpenDate().compareTo(now) <= 0) {
//				到達した場合は公開状態を公開にして更新する
					e.setOpen(1);
					News news = newsDao.findById(e.getNewsId()).get();
					news.setOpen(1);
					newsDao.save(news);
				}
			}
			eList.add(e);
		}
		if (eList.size() == 0) {
			return new NewsWithCategoryNameVo(MsgCode.NOT_FOUND.getMessage(), MsgCode.NOT_FOUND.getType());
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
