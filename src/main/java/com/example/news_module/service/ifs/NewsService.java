package com.example.news_module.service.ifs;

import com.example.news_module.vo.request.NewsRequest;
import com.example.news_module.vo.response.NewsResponse;
import com.example.news_module.vo.response.NewsWithCategoryNameVo;

//ニュースのメゾッドを定義するインターフェース
public interface NewsService {

//	ニュースを追加する
	public NewsResponse addNews(NewsRequest newReq);

//	ニュースを更新する
	public NewsResponse updateNews(NewsRequest newReq);

//	ニュースの公開状態を公開に変更する
	public NewsResponse hideNews(NewsRequest newReq);

//	ニュースの公開状態を非公開に変更する
	public NewsResponse openNews(NewsRequest newReq);

//	該当IDのニュースを取得する
	public NewsResponse findNewsById(NewsRequest newReq);

//	選択したページのニュースを取得する（ユーザー側、公開日降順）
	public NewsWithCategoryNameVo findAllNewsF(NewsRequest newReq);

//	全てのニュースを取得する（ユーザー側、公開日降順）
	public NewsWithCategoryNameVo findAllF();

//	選択したページで検索条件と一致するニュースを取得する（管理者側、公開日降順）
	public NewsWithCategoryNameVo searchNewsBDesc(NewsRequest newReq);

//	検索条件と一致するニュースを取得する（管理者側、公開日降順）
	public NewsWithCategoryNameVo searchNewsAllBDesc(NewsRequest newReq);

//	選択したページのニュースを取得する（管理者側、公開日降順）
	public NewsWithCategoryNameVo findAllNewsBDesc(NewsRequest newReq);

//	全てのニュースを取得する（管理者側、降順）
	public NewsWithCategoryNameVo findAllBDesc();

//	選択したページで検索条件と一致するニュースを取得する（管理者側、公開日昇順）
	public NewsWithCategoryNameVo searchNewsBAsc(NewsRequest newReq);

//	検索条件と一致するニュースを取得する（管理者側、公開日昇順）
	public NewsWithCategoryNameVo searchNewsAllBAsc(NewsRequest newReq);

//	選択したページのニュースを取得する（管理者側、公開日昇順）
	public NewsWithCategoryNameVo findAllNewsBAsc(NewsRequest newReq);

//	全てのニュースを取得する（管理者側、公開日昇順）
	public NewsWithCategoryNameVo findAllBAsc();

//	該当IDのカテゴリ名称を含めているニュースデータを取得する
	public NewsWithCategoryNameVo findFullNewsById(NewsRequest req);

}
