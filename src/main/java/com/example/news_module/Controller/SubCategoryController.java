package com.example.news_module.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@PostMapping("/find_sub_by_id")
	public SubCategoryResponse findSubCategoryById(@RequestBody SubCategoryRequest req) {
		return subCategoryService.findSubCategoryById(req);

	};
	
	@GetMapping("/get_subC")
	public SubCategoryResponse getAllSubCategory() {
		return subCategoryService.getAllSubCategory();

	};
	
	@PostMapping("/delete_sub")
	public SubCategoryResponse deleteSubCategory(@RequestBody SubCategoryRequest req) {
		return subCategoryService.deleteSubCategory(req);

	};
	
	@PostMapping("/update_sub")
	public SubCategoryResponse updateSubCategory(@RequestBody SubCategoryRequest req) {
		return subCategoryService.updateSubCategory(req);

	};
	
	@PostMapping("/add_sub")
	public SubCategoryResponse addSubCategory(@RequestBody SubCategoryRequest req) {
		return subCategoryService.addSubCategory(req);

	};

}
