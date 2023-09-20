package com.example.news_module.service.ifs;

import com.example.news_module.vo.request.SubCategoryRequest;
import com.example.news_module.vo.response.SubCategoryResponse;

public interface SubCategoryService {

//	全てのサブカテゴリを取得する
	public SubCategoryResponse getAllSubCategory();
	
//	該当メインカテゴリIDを基づいて所属しているサブカテゴリを取得する
	public SubCategoryResponse findSubCategoryByMainId(SubCategoryRequest req);

//	該当IDのサブカテゴリを取得する
	public SubCategoryResponse findSubCategoryById(SubCategoryRequest req);
	
//	サブカテゴリの追加か更新
	public SubCategoryResponse addOrUpdateSubCategory(SubCategoryRequest req);

//	サブカテゴリを削除する
	public SubCategoryResponse deleteSubCategory(SubCategoryRequest req);

}
