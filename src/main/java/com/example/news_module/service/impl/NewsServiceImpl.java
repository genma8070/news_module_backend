package com.example.news_module.service.impl;

import java.sql.Date;
import java.time.LocalDate;
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

	@Override
	public NewsResponse addNews(NewsRequest req) {
		String title = req.getTitle();
		String text = req.getText();
		Integer mainCategory = req.getMainCategory();
		Integer subCategory = req.getSubCategory();
		if (title.isEmpty() || text.isEmpty()) {
			return new NewsResponse("標題及內容都須填寫");
		}
		if (mainCategory == null) {
			return new NewsResponse("請選擇父分類");
		}
		if (subCategory == null) {
			return new NewsResponse("請選擇子分類");
		}
		LocalDate now = LocalDate.now();
		News news = new News(mainCategory, subCategory, title, text, true, now);
		news.setOpenDate(now);
		newsDao.save(news);
		return new NewsResponse("新增成功");

	}

	@Override
	public NewsResponse updataNews(NewsRequest req) {
		String title = req.getTitle();
		String text = req.getText();
		Integer mainCategory = req.getMainCategory();
		Integer subCategory = req.getSubCategory();
		if (title.isEmpty() || text.isEmpty()) {
			return new NewsResponse("標題及內容都須填寫");
		}
		if (mainCategory == null) {
			return new NewsResponse("請選擇父分類");
		}
		if (subCategory == null) {
			return new NewsResponse("請選擇子分類");
		}
		Optional<News> news = newsDao.findById(req.getNewsId());
		LocalDate now = LocalDate.now();
		News target = news.get();
		target.setMainCategory(mainCategory);
		target.setSubCategory(subCategory);
		target.setTitle(title);
		target.setText(text);
		target.setUpdataDate(now);
		target.setOpenDate(now);
		newsDao.save(target);
		return new NewsResponse("更新成功");

	}

	@Override
	public NewsWithCategoryNameVo searchNewsF(NewsRequest req) {
//		不輸入則搜尋全部所以不用設定防空白
		String title = req.getTitle();
		LocalDate startTime = req.getStartDate();
		LocalDate endTime = req.getEndDate();
		Integer index = req.getIndex();
		Integer main = req.getMainCategory();
		Integer sub = req.getSubCategory();
		if (startTime != null && endTime != null && startTime.compareTo(endTime) > 0) {
			return new NewsWithCategoryNameVo("結束時間不可早於開始時間");
		}

		List<NewsWithCategoryNameVo> eList = new ArrayList<NewsWithCategoryNameVo>();

		List<Map<String, Object>> res = newsDao.findNewsByTitleOrCategoryOrDatePagingF(title, main, sub, startTime,
				endTime, index);

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
				case "main_title":
					e.setMainCategoryName((String) map.get(item));
					break;
				case "sub_title":
					e.setSubCategoryName((String) map.get(item));
					break;
				case "title":
					e.setTitle((String) map.get(item));
					break;
				case "text":
					e.setText((String) map.get(item));
					break;
				case "creat_date":
					e.setCreatDate(((Date) map.get(item)).toLocalDate());
					break;
				case "updata_date":
					if (map.get(item) != null) {
						e.setUpdataDate(((Date) map.get(item)).toLocalDate());
						break;
					} else {
						e.setUpdataDate(null);
						break;
					}
				case "open_date":
					if (map.get(item) != null) {
						e.setOpenDate(((Date) map.get(item)).toLocalDate());
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

	@Override
	public NewsWithCategoryNameVo searchNewsAllF(NewsRequest req) {
//		不輸入則搜尋全部所以不用設定防空白
		String title = req.getTitle();
		LocalDate startTime = req.getStartDate();
		LocalDate endTime = req.getEndDate();
		Integer main = req.getMainCategory();
		Integer sub = req.getSubCategory();
		if (startTime != null && endTime != null && startTime.compareTo(endTime) > 0) {
			return new NewsWithCategoryNameVo("結束時間不可早於開始時間");
		}

		List<NewsWithCategoryNameVo> eList = new ArrayList<NewsWithCategoryNameVo>();

		List<Map<String, Object>> res = newsDao.findNewsByTitleOrCategoryOrDateF(title, main, sub, startTime, endTime);

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
				case "main_title":
					e.setMainCategoryName((String) map.get(item));
					break;
				case "sub_title":
					e.setSubCategoryName((String) map.get(item));
					break;
				case "title":
					e.setTitle((String) map.get(item));
					break;
				case "text":
					e.setText((String) map.get(item));
					break;
				case "creat_date":
					e.setCreatDate(((Date) map.get(item)).toLocalDate());
					break;
				case "updata_date":
					if (map.get(item) != null) {
						e.setUpdataDate(((Date) map.get(item)).toLocalDate());
						break;
					} else {
						e.setUpdataDate(null);
						break;
					}
				case "open_date":
					if (map.get(item) != null) {
						e.setOpenDate(((Date) map.get(item)).toLocalDate());
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

	@Override
	public NewsResponse changNewsStatus(NewsRequest req) {
		Optional<News> news = newsDao.findById(req.getNewsId());
		News target = news.get();
		target.setOpen(!target.getOpen());
		newsDao.save(target);
		return new NewsResponse("更變開放狀態成功");

	}

	@Override
	public NewsWithCategoryNameVo findAllNewsF(NewsRequest newReq) {

		List<NewsWithCategoryNameVo> eList = new ArrayList<NewsWithCategoryNameVo>();

//		輸入INDEX找尋該頁號的問卷
		Integer index = newReq.getIndex();
		List<Map<String, Object>> res = newsDao.findAllNewsPagingF(index);

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
				case "main_title":
					e.setMainCategoryName((String) map.get(item));
					break;
				case "sub_title":
					e.setSubCategoryName((String) map.get(item));
					break;
				case "title":
					e.setTitle((String) map.get(item));
					break;
				case "text":
					e.setText((String) map.get(item));
					break;
				case "creat_date":
					e.setCreatDate(((Date) map.get(item)).toLocalDate());
					break;
				case "updata_date":
					if (map.get(item) != null) {
						e.setUpdataDate(((Date) map.get(item)).toLocalDate());
						break;
					} else {
						e.setUpdataDate(null);
						break;
					}
				case "open_date":
					if (map.get(item) != null) {
						e.setOpenDate(((Date) map.get(item)).toLocalDate());
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
				case "main_title":
					e.setMainCategoryName((String) map.get(item));
					break;
				case "sub_title":
					e.setSubCategoryName((String) map.get(item));
					break;
				case "title":
					e.setTitle((String) map.get(item));
					break;
				case "text":
					e.setText((String) map.get(item));
					break;
				case "creat_date":
					e.setCreatDate(((Date) map.get(item)).toLocalDate());
					break;
				case "updata_date":
					if (map.get(item) != null) {
						e.setUpdataDate(((Date) map.get(item)).toLocalDate());
						break;
					} else {
						e.setUpdataDate(null);
						break;
					}
				case "open_date":
					if (map.get(item) != null) {
						e.setOpenDate(((Date) map.get(item)).toLocalDate());
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
			if (e.getOpen()) {
				e.setOpen(e.getOpenDate().plusDays(7).compareTo(LocalDate.now()) < 0 ? false : true);
				if (!e.getOpen()) {
					Optional<News> target = newsDao.findNewsById(e.getNewsId());
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

	@Override
	public NewsWithCategoryNameVo searchNewsB(NewsRequest req) {
//		不輸入則搜尋全部所以不用設定防空白
		String title = req.getTitle();
		LocalDate startTime = req.getStartDate();
		LocalDate endTime = req.getEndDate();
		Integer index = req.getIndex();
		Integer main = req.getMainCategory();
		Integer sub = req.getSubCategory();
		if (startTime != null && endTime != null && startTime.compareTo(endTime) > 0) {
			return new NewsWithCategoryNameVo("結束時間不可早於開始時間");
		}

		List<NewsWithCategoryNameVo> eList = new ArrayList<NewsWithCategoryNameVo>();

		List<Map<String, Object>> res = newsDao.findNewsByTitleOrCategoryOrDateB(title, main, sub, startTime, endTime,
				index);

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
				case "main_title":
					e.setMainCategoryName((String) map.get(item));
					break;
				case "sub_title":
					e.setSubCategoryName((String) map.get(item));
					break;
				case "title":
					e.setTitle((String) map.get(item));
					break;
				case "text":
					e.setText((String) map.get(item));
					break;
				case "creat_date":
					e.setCreatDate(((Date) map.get(item)).toLocalDate());
					break;
				case "updata_date":
					if (map.get(item) != null) {
						e.setUpdataDate(((Date) map.get(item)).toLocalDate());
						break;
					} else {
						e.setUpdataDate(null);
						break;
					}
				case "open_date":
					if (map.get(item) != null) {
						e.setOpenDate(((Date) map.get(item)).toLocalDate());
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

	@Override
	public NewsWithCategoryNameVo searchNewsAllB(NewsRequest req) {
//		不輸入則搜尋全部所以不用設定防空白
		String title = req.getTitle();
		LocalDate startTime = req.getStartDate();
		LocalDate endTime = req.getEndDate();
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
				case "main_title":
					e.setMainCategoryName((String) map.get(item));
					break;
				case "sub_title":
					e.setSubCategoryName((String) map.get(item));
					break;
				case "title":
					e.setTitle((String) map.get(item));
					break;
				case "text":
					e.setText((String) map.get(item));
					break;
				case "creat_date":
					e.setCreatDate(((Date) map.get(item)).toLocalDate());
					break;
				case "updata_date":
					if (map.get(item) != null) {
						e.setUpdataDate(((Date) map.get(item)).toLocalDate());
						break;
					} else {
						e.setUpdataDate(null);
						break;
					}
				case "open_date":
					if (map.get(item) != null) {
						e.setOpenDate(((Date) map.get(item)).toLocalDate());
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

	@Override
	public NewsWithCategoryNameVo findAllNewsB(NewsRequest newReq) {

		List<NewsWithCategoryNameVo> eList = new ArrayList<NewsWithCategoryNameVo>();

//		輸入INDEX找尋該頁號的問卷
		Integer index = newReq.getIndex();
		List<Map<String, Object>> res = newsDao.findAllNewsPagingB(index);

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
				case "main_title":
					e.setMainCategoryName((String) map.get(item));
					break;
				case "sub_title":
					e.setSubCategoryName((String) map.get(item));
					break;
				case "title":
					e.setTitle((String) map.get(item));
					break;
				case "text":
					e.setText((String) map.get(item));
					break;
				case "creat_date":
					e.setCreatDate(((Date) map.get(item)).toLocalDate());
					break;
				case "updata_date":
					if (map.get(item) != null) {
						e.setUpdataDate(((Date) map.get(item)).toLocalDate());
						break;
					} else {
						e.setUpdataDate(null);
						break;
					}
				case "open_date":
					if (map.get(item) != null) {
						e.setOpenDate(((Date) map.get(item)).toLocalDate());
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
				case "main_title":
					e.setMainCategoryName((String) map.get(item));
					break;
				case "sub_title":
					e.setSubCategoryName((String) map.get(item));
					break;
				case "title":
					e.setTitle((String) map.get(item));
					break;
				case "text":
					e.setText((String) map.get(item));
					break;
				case "creat_date":
					e.setCreatDate(((Date) map.get(item)).toLocalDate());
					break;
				case "updata_date":
					if (map.get(item) != null) {
						e.setUpdataDate(((Date) map.get(item)).toLocalDate());
						break;
					} else {
						e.setUpdataDate(null);
						break;
					}
				case "open_date":
					if (map.get(item) != null) {
						e.setOpenDate(((Date) map.get(item)).toLocalDate());
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
			if (e.getOpen()) {
				e.setOpen(e.getOpenDate().plusDays(7).compareTo(LocalDate.now()) < 0 ? false : true);
				if (!e.getOpen()) {
					Optional<News> target = newsDao.findNewsById(e.getNewsId());
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

	@Override
	public NewsResponse findNewsById(NewsRequest req) {
		Integer id = req.getNewsId();
		Optional<News> target = newsDao.findNewsById(id);

		News news = target.get();
		return new NewsResponse(news);

	}

}
