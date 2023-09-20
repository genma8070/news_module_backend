package com.example.news_module.service.ifs;

import com.example.news_module.vo.request.MainCategoryRequest;
import com.example.news_module.vo.response.MainCategoryResponse;

public interface MainCategoryService {
	
//	全てのメインカテゴリを取得する
	public MainCategoryResponse findMainCategory();
	
//	該当IDのメインカテゴリを取得する
	public MainCategoryResponse findMainCategoryById(MainCategoryRequest req);
	
//	メインカテゴリの追加か更新
	public MainCategoryResponse addOrUpdateMainCategory(MainCategoryRequest req);

//	メインカテゴリを削除する
	public MainCategoryResponse deleteMainCategory(MainCategoryRequest req);

	
}
