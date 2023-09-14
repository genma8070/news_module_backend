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
	public NewsResponse addNews(@RequestBody NewsRequest req) {
		return newsService.addNews(req);
	};
	
//	ニュースを更新する
	@PostMapping("/update_news")
	public NewsResponse updateNews(@RequestBody NewsRequest req) {
		return newsService.updateNews(req);
	};
	
//	ニュースの公開状態を非公開に変更する
	@PostMapping("/hide")
	public NewsResponse hideNews(@RequestBody NewsRequest req) {
		return newsService.hideNews(req);
	};
	
//	ニュースの公開状態を公開に変更する
	@PostMapping("/open")
	public NewsResponse openNews(@RequestBody NewsRequest req) {
		return newsService.openNews(req);
	};
	
//	全てのニュースを取得する（ユーザー側、公開日降順）
	@GetMapping("/get_all_f")
	public NewsWithCategoryNameVo findAllF() {
		return newsService.findAllF();
	};
//	選択したページのニュースを取得する（ユーザー側、公開日降順）
	@PostMapping("/find_all_news_f")
	public NewsWithCategoryNameVo findAllNewsF(@RequestBody NewsRequest req) {
		return newsService.findAllNewsF(req);
	};
	
//	全てのニュースを取得する（管理者側、公開日降順）
	@GetMapping("/get_all_b_desc")
	public NewsWithCategoryNameVo findAllBDesc() {
		return newsService.findAllBDesc();
	};
	
//	選択したページのニュースを取得する（管理者側、公開日降順）
	@PostMapping("/find_all_news_b_desc")
	public NewsWithCategoryNameVo findAllNewsBDesc(@RequestBody NewsRequest req) {
		return newsService.findAllNewsBDesc(req);
	};

//	選択したページの検索条件と一致するニュースを取得する（管理者側、公開日降順）
	@PostMapping("/search_news_b_desc")
	public NewsWithCategoryNameVo searchNewsBDesc(@RequestBody NewsRequest req) {
		return newsService.searchNewsBDesc(req);
	};
	
//	検索条件と一致するニュースを取得する（管理者側、公開日降順）
	@PostMapping("/search_news_b_A_desc")
	public NewsWithCategoryNameVo searchNewsAllBDesc(@RequestBody NewsRequest req) {
		return newsService.searchNewsAllBDesc(req);
	};
	
//	全てのニュースを取得する（管理者側、公開日昇順）
	@GetMapping("/get_all_b_asc")
	public NewsWithCategoryNameVo findAllBAsc() {
		return newsService.findAllBAsc();
	};
	
//	選択したページのニュースを取得する（管理者側、公開日昇順）
	@PostMapping("/find_all_news_b_asc")
	public NewsWithCategoryNameVo findAllNewsBAsc(@RequestBody NewsRequest req) {
		return newsService.findAllNewsBAsc(req);
	};

//	選択したページの検索条件と一致するニュースを取得する（管理者側、公開日昇順）
	@PostMapping("/search_news_b_asc")
	public NewsWithCategoryNameVo searchNewsBAsc(@RequestBody NewsRequest req) {
		return newsService.searchNewsBAsc(req);
	};
	
//	検索条件と一致するニュースを取得する（管理者側、公開日昇順）
	@PostMapping("/search_news_b_A_asc")
	public NewsWithCategoryNameVo searchNewsAllBAsc(@RequestBody NewsRequest req) {
		return newsService.searchNewsAllBAsc(req);
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
