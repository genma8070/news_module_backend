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
		LocalDateTime open = req.getOpenDate();
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

		LocalDateTime now = LocalDateTime.now();
		if (open == null) {
			open = now;
			status = true;
		}else {
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
		LocalDateTime open =req.getOpenDate();
		if (title.isEmpty() || text.isEmpty() || subTitle.isEmpty()) {
			return new NewsResponse("標題及內容都須填寫");
		}
		if (mainCategory == null) {
			return new NewsResponse("請選擇父分類");
		}
		if (subCategory == null) {
			return new NewsResponse("請選擇子分類");
		}
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

//	ニュースの公開状態を変更する
	@Override
	public NewsResponse hideNews(NewsRequest req) {

		List<Integer> targets = req.getList();
		LocalDateTime now = LocalDateTime.now();
		for (Integer target : targets) {
			Optional<News> news = newsDao.findById(target);
			News n = news.get();
			if (!n.getOpen()) {
				continue;
			} else {
				n.setOpen(false);
				n.setUpdateDate(now);
				newsDao.save(n);
			}
		}
		return new NewsResponse("隱藏成功");

	}
	
	@Override
	public NewsResponse openNews(NewsRequest req) {

		List<Integer> targets = req.getList();
		LocalDateTime now = LocalDateTime.now();
		for (Integer target : targets) {
			Optional<News> news = newsDao.findById(target);
			News n = news.get();
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
	
	@Override
	public NewsWithCategoryNameVo findFullNewsById(NewsRequest req) {
		Integer id = req.getNewsId();
		List<Map<String, Object>> res = newsDao.findFullNewsById(id);
		NewsWithCategoryNameVo e = new NewsWithCategoryNameVo();
//		將找出的問卷物件重組
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
					if (map.get(item) != null) {
						e.setUpdateDate(((Timestamp) map.get(item)).toLocalDateTime());
						break;
					} else {
						e.setUpdateDate(null);
						break;
					}
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
			
		}
		
		return e;

	}

	
	
	
//	選択したページのニュースを取得する（ユーザー側）
	@Override
	public NewsWithCategoryNameVo findAllNewsF(NewsRequest newReq) {

		List<NewsWithCategoryNameVo> eList = new ArrayList<NewsWithCategoryNameVo>();

//		輸入INDEX找尋該頁號的問卷
		Integer index = newReq.getIndex();
		Integer items = newReq.getItems();
		List<Map<String, Object>> res = newsDao.findAllNewsPagingF(index, items);

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
					if (map.get(item) != null) {
						e.setUpdateDate(((Timestamp) map.get(item)).toLocalDateTime());
						break;
					} else {
						e.setUpdateDate(null);
						break;
					}
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
			eList.add(e);
		}
		if (eList.size() == 0) {
			return new NewsWithCategoryNameVo("此頁無資料");
		}
		return new NewsWithCategoryNameVo(eList);

	}

//	全てのニュースを取得する（ユーザー側）
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
				case "updata_date":
					if (map.get(item) != null) {
						e.setUpdateDate(((Timestamp) map.get(item)).toLocalDateTime());
						break;
					} else {
						e.setUpdateDate(null);
						break;
					}
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

//			ニュースが公開中なら公開時間が一週間超えたかどうかを判断する
			if (e.getOpen()) {
//				公開時間が七日超えたら公開状態を非公開にする
				e.setOpen(e.getOpenDate().plusDays(7).compareTo(LocalDateTime.now()) < 0 ? false : true);
//				公開状態が非公開になった場合はデータベースを更新する
				if (!e.getOpen()) {
					Optional<News> target = newsDao.findById(e.getNewsId());
					News news = target.get();
					news.setOpen(e.getOpen());
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
	
//	選択したページのニュースを取得する（ユーザー側）
	@Override
	public NewsWithCategoryNameVo findAllNewsFAsc(NewsRequest newReq) {

		List<NewsWithCategoryNameVo> eList = new ArrayList<NewsWithCategoryNameVo>();

//		輸入INDEX找尋該頁號的問卷
		Integer index = newReq.getIndex();
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
					if (map.get(item) != null) {
						e.setUpdateDate(((Timestamp) map.get(item)).toLocalDateTime());
						break;
					} else {
						e.setUpdateDate(null);
						break;
					}
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
			eList.add(e);
		}
		if (eList.size() == 0) {
			return new NewsWithCategoryNameVo("此頁無資料");
		}
		return new NewsWithCategoryNameVo(eList);

	}

//	全てのニュースを取得する（ユーザー側）
	@Override
	public NewsWithCategoryNameVo findAllFAsc() {

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
					if (map.get(item) != null) {
						e.setUpdateDate(((Timestamp) map.get(item)).toLocalDateTime());
						break;
					} else {
						e.setUpdateDate(null);
						break;
					}
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

//			ニュースが公開中なら公開時間が一週間超えたかどうかを判断する
			if (e.getOpen()) {
//				公開時間が七日超えたら公開状態を非公開にする
				e.setOpen(e.getOpenDate().plusDays(7).compareTo(LocalDateTime.now()) < 0 ? false : true);
//				公開状態が非公開になった場合はデータベースを更新する
				if (!e.getOpen()) {
					Optional<News> target = newsDao.findById(e.getNewsId());
					News news = target.get();
					news.setOpen(e.getOpen());
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

//	選択したページの検索条件と一致するニュースを取得する（管理者側）
	@Override
	public NewsWithCategoryNameVo searchNewsB(NewsRequest req) {
//		不輸入則搜尋全部所以不用設定防空白
		String title = req.getTitle();
		LocalDateTime startTime = req.getStartDate();
		LocalDateTime endTime = req.getEndDate();
		Integer index = req.getIndex();
		Integer items = req.getItems();
		Integer main = req.getMainCategory();
		Integer sub = req.getSubCategory();
		if (startTime != null && endTime != null && startTime.compareTo(endTime) > 0) {
			return new NewsWithCategoryNameVo("結束時間不可早於開始時間");
		}

		List<NewsWithCategoryNameVo> eList = new ArrayList<NewsWithCategoryNameVo>();

		List<Map<String, Object>> res = newsDao.findNewsByTitleOrCategoryOrDateB(title, main, sub, startTime, endTime,
				index, items);

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
					if (map.get(item) != null) {
						e.setUpdateDate(((Timestamp) map.get(item)).toLocalDateTime());
						break;
					} else {
						e.setUpdateDate(null);
						break;
					}
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
			eList.add(e);
		}
		if (eList.size() == 0) {
			return new NewsWithCategoryNameVo("查無資料");
		}
		return new NewsWithCategoryNameVo(eList);

	}

//	検索条件と一致するニュースを取得する（管理者側）
	@Override
	public NewsWithCategoryNameVo searchNewsAllB(NewsRequest req) {
//		不輸入則搜尋全部所以不用設定防空白
		String title = req.getTitle();
		LocalDateTime startTime = req.getStartDate();
		LocalDateTime endTime = req.getEndDate();
		Integer main = req.getMainCategory();
		Integer sub = req.getSubCategory();
		if (startTime != null && endTime != null && startTime.compareTo(endTime) > 0) {
			return new NewsWithCategoryNameVo("結束時間不可早於開始時間");
		}

		List<NewsWithCategoryNameVo> eList = new ArrayList<NewsWithCategoryNameVo>();

		List<Map<String, Object>> res = newsDao.findAllNewsByTitleOrCategoryOrDateB(title, main, sub, startTime,
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
					if (map.get(item) != null) {
						e.setUpdateDate(((Timestamp) map.get(item)).toLocalDateTime());
						break;
					} else {
						e.setUpdateDate(null);
						break;
					}
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
			eList.add(e);
		}
		if (eList.size() == 0) {
			return new NewsWithCategoryNameVo("查無資料");
		}
		return new NewsWithCategoryNameVo(eList);

	}

//	選択したページのニュースを取得する（管理者側）
	@Override
	public NewsWithCategoryNameVo findAllNewsB(NewsRequest newReq) {

		List<NewsWithCategoryNameVo> eList = new ArrayList<NewsWithCategoryNameVo>();

//		輸入INDEX找尋該頁號的問卷
		Integer index = newReq.getIndex();
		Integer items = newReq.getItems();
		List<Map<String, Object>> res = newsDao.findAllNewsPagingB(index, items);

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
					if (map.get(item) != null) {
						e.setUpdateDate(((Timestamp) map.get(item)).toLocalDateTime());
						break;
					} else {
						e.setUpdateDate(null);
						break;
					}
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
			eList.add(e);
		}
		if (eList.size() == 0) {
			return new NewsWithCategoryNameVo("此頁無資料");
		}
		return new NewsWithCategoryNameVo(eList);

	}

//	全てのニュースを取得する（管理者側）
	@Override
	public NewsWithCategoryNameVo findAllB() {

		List<NewsWithCategoryNameVo> eList = new ArrayList<NewsWithCategoryNameVo>();

		List<Map<String, Object>> res = newsDao.findAllNewsB();

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
					if (map.get(item) != null) {
						e.setUpdateDate(((Timestamp) map.get(item)).toLocalDateTime());
						break;
					} else {
						e.setUpdateDate(null);
						break;
					}
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
//			ニュースが公開中なら公開時間が一週間超えたかどうかを判断する
			if (e.getOpen()) {
//				公開時間が七日超えたら公開状態を非公開にする
				e.setOpen(e.getOpenDate().plusDays(7).compareTo(LocalDateTime.now()) < 0 ? false : true);
//				公開状態が非公開になった場合はデータベースを更新する
				if (!e.getOpen()) {
					Optional<News> target = newsDao.findById(e.getNewsId());
					News news = target.get();
					news.setOpen(e.getOpen());
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
