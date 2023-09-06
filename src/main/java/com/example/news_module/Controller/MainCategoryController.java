package com.example.news_module.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.news_module.service.ifs.MainCategoryService;
import com.example.news_module.vo.request.MainCategoryRequest;
import com.example.news_module.vo.response.MainCategoryResponse;

@CrossOrigin
@RestController
public class MainCategoryController {
	
	@Autowired
	private MainCategoryService mainCategoryService;
	
//	全てのメインカテゴリを取得する
	@GetMapping("/find_mainC")
	public MainCategoryResponse findMainCategory() {
		return mainCategoryService.findMainCategory();

	};
	
	@PostMapping("/find_main_by_id")
	public MainCategoryResponse findMainCategoryById(@RequestBody MainCategoryRequest req) {
		return mainCategoryService.findMainCategoryById(req);

	};

}
