package com.example.news_module.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.news_module.service.ifs.NewsService;
import com.example.news_module.vo.request.NewsRequest;
import com.example.news_module.vo.response.NewsResponse;
import com.example.news_module.vo.response.NewsWithCategoryNameVo;

//ニュースのメゾッドを制御するクラス
@CrossOrigin
@RestController
public class NewsController {
	
	@Autowired
	private NewsService newsService;
	
//	ニュースを追加する
	@PostMapping("/new_news")
	public NewsResponse addOrUpdateNews(@RequestBody NewsRequest req) {
		return newsService.addOrUpdateNews(req);
	};
	
//	ニュースの公開状態を非公開に変更する
	@PostMapping("/hide")
	public NewsResponse hideNews(@RequestBody NewsRequest req) {
		return newsService.hideNews(req);
	};
	
//	ニュースを公開に変更する
	@PostMapping("/open")
	public NewsResponse openNews(@RequestBody NewsRequest req) {
		return newsService.openNews(req);
	};
	
//	全てのニュースを取得する（ユーザー側、公開日降順）
	@GetMapping("/get_all_f")
	public NewsWithCategoryNameVo findAllF() {
		return newsService.findAllF();
	};
//	該ページのニュースを取得する（ユーザー側、公開日降順）
	@PostMapping("/find_all_news_f")
	public NewsWithCategoryNameVo findAllNewsF(@RequestBody NewsRequest req) {
		return newsService.findAllNewsF(req);
	};
	
//	全てのニュースを取得する（管理者側、公開日降順）
	@PostMapping("/get_all")
	public NewsWithCategoryNameVo findAllBySort(@RequestBody NewsRequest req) {
		return newsService.findAllBySort(req);
	};
	
//	選択したページのニュースを取得する（管理者側、公開日降順）
	@PostMapping("/find_all_news")
	public NewsWithCategoryNameVo findAllNewsBySort(@RequestBody NewsRequest req) {
		return newsService.findAllNewsBySort(req);
	};

//	選択したページの検索条件と一致するニュースを取得する（管理者側、公開日降順）
	@PostMapping("/search_news")
	public NewsWithCategoryNameVo searchNewsBySort(@RequestBody NewsRequest req) {
		return newsService.searchNewsBySort(req);
	};
	
//	検索条件と一致するニュースを取得する（管理者側、公開日降順）
	@PostMapping("/search_news_A")
	public NewsWithCategoryNameVo searchNewsAllBySort(@RequestBody NewsRequest req) {
		return newsService.searchNewsAllBySort(req);
	};
	
//	該当IDのニュースを取得する
	@PostMapping("/find_news_by_id")
	public NewsResponse findNewsById(@RequestBody NewsRequest req) {
		return newsService.findNewsById(req);
	};
	
//	該当IDのニュース画面用データを取得する
	@PostMapping("/find_full_news_by_id")
	public NewsWithCategoryNameVo findFullNewsById(@RequestBody NewsRequest req) {
		return newsService.findFullNewsById(req);
	};
	
}
