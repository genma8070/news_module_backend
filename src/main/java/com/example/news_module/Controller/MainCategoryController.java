package com.example.news_module.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.news_module.service.ifs.MainCategoryService;
import com.example.news_module.vo.response.MainCategoryResponse;

@CrossOrigin
@RestController
public class MainCategoryController {
	
	@Autowired
	private MainCategoryService mainCategoryService;
	
	@GetMapping("/find_mainC")
	public MainCategoryResponse findMainCategory() {
		return mainCategoryService.findMainCategory();

	};

}
