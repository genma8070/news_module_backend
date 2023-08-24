package com.example.news_module.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.news_module.repository.SubCategoryDao;
import com.example.news_module.service.ifs.SubCategoryService;
import com.example.news_module.vo.request.SubCategoryRequest;
import com.example.news_module.vo.response.SubCategoryResponse;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {

	@Autowired
	private SubCategoryDao subCategoryDao;
	
//	全てのサブカテゴリを取得する
	@Override
	public SubCategoryResponse findSubCategory(SubCategoryRequest req) {

		List<SubCategoryResponse> eList = new ArrayList<SubCategoryResponse>();

		List<Map<String, Object>> res = subCategoryDao.findAllSubByFather(req.getFatherId());

		for (Map<String, Object> map : res) {
			SubCategoryResponse e = new SubCategoryResponse();
			for (String item : map.keySet()) {
				switch (item) {
				
				case "id":
					e.setSubId((Integer) map.get(item));
					break;
				case "sub_title":
					e.setSubTitle((String) map.get(item));
					break;
				case "father":
					e.setFatherId((Integer) map.get(item));
					break;
				
				}
			}
			eList.add(e);
		}	
		if (eList.size() == 0) {
			return new SubCategoryResponse("查無資料");
		}
		
		return new SubCategoryResponse(eList);
	
	}
}
