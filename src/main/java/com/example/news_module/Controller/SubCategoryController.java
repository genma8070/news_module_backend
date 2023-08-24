package com.example.news_module.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.news_module.service.ifs.SubCategoryService;
import com.example.news_module.vo.request.SubCategoryRequest;
import com.example.news_module.vo.response.SubCategoryResponse;

@CrossOrigin
@RestController
public class SubCategoryController {
	
	@Autowired
	private SubCategoryService subCategoryService;
	
//	全てのサブカテゴリを取得する
	@PostMapping("/find_subC")
	public SubCategoryResponse findSubCategory(@RequestBody SubCategoryRequest req) {
		return subCategoryService.findSubCategory(req);

	};

}
