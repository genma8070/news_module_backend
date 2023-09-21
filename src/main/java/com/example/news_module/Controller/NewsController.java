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
	
//	ニュースの追加か更新
	@PostMapping("/new_news")
	public NewsResponse addOrUpdateNews(@RequestBody NewsRequest req) {
		return newsService.addOrUpdateNews(req);
	};
	
//	ニュースの削除
	@PostMapping("/delete_news")
	public NewsResponse deleteNews(@RequestBody NewsRequest req) {
		return newsService.deleteNews(req);
	};
	

//	ニュースを公開に変更する
	@PostMapping("/open_or_hide")
	public NewsResponse openOrHideNews(@RequestBody NewsRequest req) {
		return newsService.openOrHideNews(req);
	};
	
//	全てのニュースを取得する（ユーザー側、公開日降順）
	@GetMapping("/user_get_all")
	public NewsWithCategoryNameVo findAllUser() {
		return newsService.findAllUser();
	};
//	該ページのニュースを取得する（ユーザー側、公開日降順）
	@PostMapping("/user_find_all_news")
	public NewsWithCategoryNameVo findAllNewsUser(@RequestBody NewsRequest req) {
		return newsService.findAllNewsUser(req);
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
