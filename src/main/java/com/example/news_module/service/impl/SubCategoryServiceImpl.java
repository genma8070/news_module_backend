package com.example.news_module.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.news_module.entity.SubCategory;
import com.example.news_module.repository.NewsDao;
import com.example.news_module.repository.SubCategoryDao;
import com.example.news_module.service.ifs.SubCategoryService;
import com.example.news_module.vo.request.SubCategoryRequest;
import com.example.news_module.vo.response.SubCategoryResponse;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {

	@Autowired
	private SubCategoryDao subCategoryDao;

	@Autowired
	private NewsDao newsDao;
	
	@Override
	public SubCategoryResponse findSubCategoryById(SubCategoryRequest req) {
		SubCategory target = subCategoryDao.findById(req.getSubId()).get();
		return new SubCategoryResponse(target);
		
	}

//	全てのサブカテゴリを取得する
	@Override
	public SubCategoryResponse findSubCategory(SubCategoryRequest req) {

		List<SubCategoryResponse> eList = new ArrayList<SubCategoryResponse>();

		List<Map<String, Object>> res = subCategoryDao.findAllSubByFather(req.getMainId());

		for (Map<String, Object> map : res) {
			SubCategoryResponse e = new SubCategoryResponse();
			for (String item : map.keySet()) {
				switch (item) {

				case "id":
					e.setSubId((Integer) map.get(item));
					List<Map<String, Object>> size = newsDao.findNewsByCategory(null, e.getSubId());
					e.setNews(size.size());
					break;
				case "sub_category_name":
					e.setSubCategoryName((String) map.get(item));
					break;
				case "main_id":
					e.setMainId((Integer) map.get(item));
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
	
	@Override
	public SubCategoryResponse getAllSubCategory() {

		List<SubCategory> res = subCategoryDao.findAll();
		List<SubCategoryResponse> outPutList = new ArrayList<>();
		for(SubCategory target : res) {
			SubCategoryResponse outPut = new SubCategoryResponse();
			List<Map<String, Object>> size = newsDao.findNewsByCategory(null, target.getSubId());
			outPut.setSubId(target.getSubId());
			outPut.setSubCategoryName(target.getSubCategoryName());
			outPut.setMainId(target.getMainId());
			outPut.setNews(size.size());
			outPutList.add(outPut);
		}
		
		if (res.size() == 0) {
			return new SubCategoryResponse("查無資料");
		}

		return new SubCategoryResponse(outPutList);

	}
	

	public SubCategoryResponse addSubCategory(SubCategoryRequest req) {
		String name = req.getSubCategoryName();
		Integer main = req.getMainId();
		SubCategory create = new SubCategory(name, main);
		List<Map<String, Object>> target = subCategoryDao.findByTitle(name);
		if (target.size() == 0) {
			subCategoryDao.save(create);
			return new SubCategoryResponse("新建成功");
		} else {
			return new SubCategoryResponse("分類命名不得重複");
		}

	}

	public SubCategoryResponse updateSubCategory(SubCategoryRequest req) {
		Integer id = req.getSubId();
		String name = req.getSubCategoryName();
		SubCategory update = new SubCategory(id, name);
		List<Map<String, Object>> target = subCategoryDao.findByTitle(name);
		if (target.size() == 0) {
			subCategoryDao.save(update);
			return new SubCategoryResponse("更新成功");
		} else {
			return new SubCategoryResponse("分類命名不得重複");
		}

	}

	public SubCategoryResponse deleteSubCategory(SubCategoryRequest req) {
		Integer id = req.getSubId();
		String name = req.getSubCategoryName();
		SubCategory delete = new SubCategory(id, name);
		List<Map<String, Object>> target = newsDao.findNewsByCategory(null, id);
		if (target.size() == 0) {
			subCategoryDao.delete(delete);
			return new SubCategoryResponse("刪除成功");
		} else {
			return new SubCategoryResponse("只能刪除空的分類");
		}

	}

}
