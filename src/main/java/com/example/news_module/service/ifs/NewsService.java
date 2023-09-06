package com.example.news_module.service.ifs;

import com.example.news_module.vo.request.NewsRequest;
import com.example.news_module.vo.response.NewsResponse;
import com.example.news_module.vo.response.NewsWithCategoryNameVo;

public interface NewsService {

//	ニュースを追加する
	public NewsResponse addNews(NewsRequest newReq);

//	ニュースを更新する
	public NewsResponse updataNews(NewsRequest newReq);

//	ニュースの公開状態を変更する
	public NewsResponse hideNews(NewsRequest newReq);
	
	public NewsResponse openNews(NewsRequest newReq);
	
//	該当IDのニュースを取得する
	public NewsResponse findNewsById(NewsRequest newReq);



//	選択したページのニュースを取得する（ユーザー側）
	public NewsWithCategoryNameVo findAllNewsF(NewsRequest newReq);

//	全てのニュースを取得する（ユーザー側）
	public NewsWithCategoryNameVo findAllF();
	
//	選択したページのニュースを取得する（ユーザー側）
	public NewsWithCategoryNameVo findAllNewsFAsc(NewsRequest newReq);

//	全てのニュースを取得する（ユーザー側）
	public NewsWithCategoryNameVo findAllFAsc();

//	選択したページの検索条件と一致するニュースを取得する（管理者側）
	public NewsWithCategoryNameVo searchNewsB(NewsRequest newReq);
	
//	検索条件と一致するニュースを取得する（管理者側）
	public NewsWithCategoryNameVo searchNewsAllB(NewsRequest newReq);

//	選択したページのニュースを取得する（管理者側）
	public NewsWithCategoryNameVo findAllNewsB(NewsRequest newReq);

//	全てのニュースを取得する（管理者側）
	public NewsWithCategoryNameVo findAllB();
	
	public NewsWithCategoryNameVo findFullNewsById(NewsRequest req);

}
