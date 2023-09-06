package com.example.news_module.service.ifs;

import com.example.news_module.vo.request.MainCategoryRequest;
import com.example.news_module.vo.response.MainCategoryResponse;

public interface MainCategoryService {
	
//	全てのメインカテゴリを取得する
	public MainCategoryResponse findMainCategory();
	
	public MainCategoryResponse findMainCategoryById(MainCategoryRequest req);
	
	public MainCategoryResponse addMainCategory(MainCategoryRequest req);

	public MainCategoryResponse updateMainCategory(MainCategoryRequest req);

	public MainCategoryResponse deleteMainCategory(MainCategoryRequest req);

	
}
