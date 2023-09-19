package com.example.news_module.service.ifs;

import com.example.news_module.vo.request.MainCategoryRequest;
import com.example.news_module.vo.response.MainCategoryResponse;

public interface MainCategoryService {
	
//	全てのメインカテゴリを取得する
	public MainCategoryResponse findMainCategory();
	
//	該当IDのメインカテゴリを取得する
	public MainCategoryResponse findMainCategoryById(MainCategoryRequest req);
	
//	メインカテゴリを追加する
	public MainCategoryResponse addMainCategory(MainCategoryRequest req);

//	メインカテゴリを更新する
	public MainCategoryResponse updateMainCategory(MainCategoryRequest req);

//	メインカテゴリを削除する
	public MainCategoryResponse deleteMainCategory(MainCategoryRequest req);

	
}
