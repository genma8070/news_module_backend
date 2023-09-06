package com.example.news_module.service.ifs;

import com.example.news_module.vo.request.SubCategoryRequest;
import com.example.news_module.vo.response.SubCategoryResponse;

public interface SubCategoryService {

//	全てのサブカテゴリを取得する
	public SubCategoryResponse getAllSubCategory();
	
	public SubCategoryResponse findSubCategory(SubCategoryRequest req);

	public SubCategoryResponse findSubCategoryById(SubCategoryRequest req);
	
	public SubCategoryResponse addSubCategory(SubCategoryRequest req);

	public SubCategoryResponse updateSubCategory(SubCategoryRequest req);

	public SubCategoryResponse deleteSubCategory(SubCategoryRequest req);

}
