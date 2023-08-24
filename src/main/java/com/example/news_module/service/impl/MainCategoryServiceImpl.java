package com.example.news_module.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.news_module.repository.MainCategoryDao;
import com.example.news_module.service.ifs.MainCategoryService;
import com.example.news_module.vo.response.MainCategoryResponse;

@Service
public class MainCategoryServiceImpl implements MainCategoryService {

	@Autowired
	private MainCategoryDao mainCategoryDao;
	
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
					break;
				case "main_title":
					e.setMainTitle((String) map.get(item));
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
}
