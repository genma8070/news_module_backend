package com.example.news_module.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.news_module.entity.MainCategory;
import com.example.news_module.repository.MainCategoryDao;
import com.example.news_module.repository.NewsDao;
import com.example.news_module.service.ifs.MainCategoryService;
import com.example.news_module.vo.request.MainCategoryRequest;
import com.example.news_module.vo.response.MainCategoryResponse;

@Service
public class MainCategoryServiceImpl implements MainCategoryService {

	@Autowired
	private MainCategoryDao mainCategoryDao;

	@Autowired
	private NewsDao newsDao;

	@Override
	public MainCategoryResponse findMainCategoryById(MainCategoryRequest req) {
		MainCategory target = mainCategoryDao.findById(req.getMainId()).get();
		return new MainCategoryResponse(target);
		
	}
	
//	全てのメインカテゴリを取得する
	@Override
	public MainCategoryResponse findMainCategory() {

		List<MainCategoryResponse> eList = new ArrayList<MainCategoryResponse>();

		List<Map<String, Object>> res = mainCategoryDao.findAllMain();

		for (Map<String, Object> map : res) {
			MainCategoryResponse e = new MainCategoryResponse();
			for (String item : map.keySet()) {
				switch (item) {

				case "id":
					e.setMainId((Integer) map.get(item));
					List<Map<String, Object>> size = newsDao.findNewsByCategory(e.getMainId(), null);
					e.setNews(size.size());
					break;
				case "main_category_name":
					e.setMainCategoryName((String) map.get(item));
					break;

				}
			}
			eList.add(e);
		}
		if (eList.size() == 0) {
			return new MainCategoryResponse("查無資料");
		}

		return new MainCategoryResponse(eList);

	}

	public MainCategoryResponse addMainCategory(MainCategoryRequest req) {
		String name = req.getMainCategoryName();
		MainCategory create = new MainCategory(name);
		List<Map<String, Object>> target = mainCategoryDao.findByTitle(name);
		if (target.size() == 0) {
			mainCategoryDao.save(create);
			return new MainCategoryResponse("新建成功");
		} else {
			return new MainCategoryResponse("分類命名不得重複");
		}

	}

	public MainCategoryResponse updateMainCategory(MainCategoryRequest req) {
		Integer id = req.getMainId();
		String name = req.getMainCategoryName();
		MainCategory update = new MainCategory(id, name);
		List<Map<String, Object>> target = mainCategoryDao.findByTitle(name);
		if (target.size() == 0) {
			mainCategoryDao.save(update);
			return new MainCategoryResponse("更新成功");
		} else {
			return new MainCategoryResponse("分類命名不得重複");
		}

	}

	public MainCategoryResponse deleteMainCategory(MainCategoryRequest req) {
		Integer id = req.getMainId();
		String name = req.getMainCategoryName();
		MainCategory delete = new MainCategory(id, name);
		List<Map<String, Object>> target = newsDao.findNewsByCategory(id, null);
		if (target.size() == 0) {
			mainCategoryDao.delete(delete);
			return new MainCategoryResponse("刪除成功");
		} else {
			return new MainCategoryResponse("只能刪除空的分類");
		}

	}

}
