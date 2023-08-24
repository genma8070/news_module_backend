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
	@PostMapping("/updata_news")
	public NewsResponse updataNews(@RequestBody NewsRequest req) {
		return newsService.updataNews(req);
	};
	
//	ニュースの公開状態を変更する
	@PostMapping("/change_open")
	public NewsResponse changNewsStatus(@RequestBody NewsRequest req) {
		return newsService.changNewsStatus(req);
	};
	
//	全てのニュースを取得する（ユーザー側）
	@GetMapping("/get_all_f")
	public NewsWithCategoryNameVo findAllF() {
		return newsService.findAllF();
	};
//	選択したページのニュースを取得する（ユーザー側）
	@PostMapping("/find_all_news_f")
	public NewsWithCategoryNameVo findAllNewsF(@RequestBody NewsRequest req) {
		return newsService.findAllNewsF(req);
	};
	
//	選択したページの検索条件と一致するニュースを取得する（ユーザー側）
	@PostMapping("/search_news_f")
	public NewsWithCategoryNameVo searchNewsF(@RequestBody NewsRequest req) {
		return newsService.searchNewsF(req);
	};
	
//	検索条件と一致するニュースを取得する（ユーザー側）
	@PostMapping("/search_news_f_A")
	public NewsWithCategoryNameVo searchNewsAllF(@RequestBody NewsRequest req) {
		return newsService.searchNewsAllF(req);
	};
	
//	全てのニュースを取得する（管理者側）
	@GetMapping("/get_all_b")
	public NewsWithCategoryNameVo findAllB() {
		return newsService.findAllB();
	};
	
//	選択したページのニュースを取得する（管理者側）
	@PostMapping("/find_all_news_b")
	public NewsWithCategoryNameVo findAllNewsB(@RequestBody NewsRequest req) {
		return newsService.findAllNewsB(req);
	};

//	選択したページの検索条件と一致するニュースを取得する（管理者側）
	@PostMapping("/search_news_b")
	public NewsWithCategoryNameVo searchNewsB(@RequestBody NewsRequest req) {
		return newsService.searchNewsB(req);
	};
	
//	検索条件と一致するニュースを取得する（管理者側）
	@PostMapping("/search_news_b_A")
	public NewsWithCategoryNameVo searchNewsAllB(@RequestBody NewsRequest req) {
		return newsService.searchNewsAllB(req);
	};

//	該当IDのニュースを取得する
	@PostMapping("/find_news_by_id")
	public NewsResponse findNewsById(@RequestBody NewsRequest req) {
		return newsService.findNewsById(req);
	};

}
