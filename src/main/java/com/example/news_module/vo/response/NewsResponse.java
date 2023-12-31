package com.example.news_module.vo.response;

import java.time.LocalDateTime;
import java.util.List;

import com.example.news_module.entity.News;
import com.fasterxml.jackson.annotation.JsonInclude;

//ニュース関連のリスポンスのクラス
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class NewsResponse {
	
//	ニュースリスポンスのリスト
	private List<NewsResponse> list;
	
//	ニュースのエンティティ
	private News news;
	
//	ニュースのID
	private Integer newsId;

//	ニュースのメインカテゴリ
	private Integer mainCategory;

//	ニュースのサブカテゴリ
	private Integer subCategory;

//	ニュースのタイトル
	private String title;
	
//	ニュースのサブタイトル
	private String subTitle;

//	ニュースの本文
	private String text;
	
//	ニュースの公開状態
	private Integer open;
	
//	ニュースの作成日
	private LocalDateTime creatDate;
	
//	ニュースの更新日
	private LocalDateTime updateDate;
	
//	ニュースの公開日
	private LocalDateTime openDate;
	
//	メッセージ
	private String message;
	
//	メッセージ種類
    private Boolean messageType;

	public List<NewsResponse> getList() {
		return list;
	}

	public void setList(List<NewsResponse> list) {
		this.list = list;
	}

	public Integer getNewsId() {
		return newsId;
	}

	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}

	public Integer getMainCategory() {
		return mainCategory;
	}

	public void setMainCategory(Integer mainCategory) {
		this.mainCategory = mainCategory;
	}

	public Integer getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(Integer subCategory) {
		this.subCategory = subCategory;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getOpen() {
		return open;
	}

	public void setOpen(Integer open) {
		this.open = open;
	}

	public LocalDateTime getCreatDate() {
		return creatDate;
	}

	public void setCreatDate(LocalDateTime creatDate) {
		this.creatDate = creatDate;
	}

	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public NewsResponse(List<NewsResponse> list, Integer newsId, Integer mainCategory, Integer subCategory, String title,
			String text, Integer open, LocalDateTime creatDate, LocalDateTime updateDate, String message) {
		super();
		this.list = list;
		this.newsId = newsId;
		this.mainCategory = mainCategory;
		this.subCategory = subCategory;
		this.title = title;
		this.text = text;
		this.open = open;
		this.creatDate = creatDate;
		this.updateDate = updateDate;
		this.message = message;
	}

	public NewsResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NewsResponse(String message) {
		super();
		this.message = message;
	}

	public NewsResponse(List<NewsResponse> list) {
		super();
		this.list = list;
	}

	public NewsResponse(Integer newsId, Integer mainCategory, Integer subCategory, String title, String text,
			Integer open, LocalDateTime creatDate, LocalDateTime updateDate) {
		super();
		this.newsId = newsId;
		this.mainCategory = mainCategory;
		this.subCategory = subCategory;
		this.title = title;
		this.text = text;
		this.open = open;
		this.creatDate = creatDate;
		this.updateDate = updateDate;
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public NewsResponse(News news) {
		super();
		this.news = news;
	}

	public LocalDateTime getOpenDate() {
		return openDate;
	}

	public void setOpenDate(LocalDateTime openDate) {
		this.openDate = openDate;
	}

	public NewsResponse(List<NewsResponse> list, News news, Integer newsId, Integer mainCategory, Integer subCategory,
			String title, String text, Integer open, LocalDateTime creatDate, LocalDateTime updateDate, LocalDateTime openDate,
			String message) {
		super();
		this.list = list;
		this.news = news;
		this.newsId = newsId;
		this.mainCategory = mainCategory;
		this.subCategory = subCategory;
		this.title = title;
		this.text = text;
		this.open = open;
		this.creatDate = creatDate;
		this.updateDate = updateDate;
		this.openDate = openDate;
		this.message = message;
	}

	public NewsResponse(List<NewsResponse> list, News news, Integer newsId, Integer mainCategory, Integer subCategory,
			String title, String text, Integer open, LocalDateTime creatDate, LocalDateTime updateDate, LocalDateTime openDate) {
		super();
		this.list = list;
		this.news = news;
		this.newsId = newsId;
		this.mainCategory = mainCategory;
		this.subCategory = subCategory;
		this.title = title;
		this.text = text;
		this.open = open;
		this.creatDate = creatDate;
		this.updateDate = updateDate;
		this.openDate = openDate;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public NewsResponse(List<NewsResponse> list, News news, Integer newsId, Integer mainCategory, Integer subCategory,
			String title, String subTitle, String text, Integer open, LocalDateTime creatDate, LocalDateTime updateDate,
			LocalDateTime openDate, String message) {
		super();
		this.list = list;
		this.news = news;
		this.newsId = newsId;
		this.mainCategory = mainCategory;
		this.subCategory = subCategory;
		this.title = title;
		this.subTitle = subTitle;
		this.text = text;
		this.open = open;
		this.creatDate = creatDate;
		this.updateDate = updateDate;
		this.openDate = openDate;
		this.message = message;
	}

	public NewsResponse(Integer newsId, Integer mainCategory, Integer subCategory, String title, String subTitle,
			String text, Integer open, LocalDateTime creatDate, LocalDateTime updateDate, LocalDateTime openDate) {
		super();
		this.newsId = newsId;
		this.mainCategory = mainCategory;
		this.subCategory = subCategory;
		this.title = title;
		this.subTitle = subTitle;
		this.text = text;
		this.open = open;
		this.creatDate = creatDate;
		this.updateDate = updateDate;
		this.openDate = openDate;
	}

	public Boolean getMessageType() {
		return messageType;
	}

	public void setMessageType(Boolean messageType) {
		this.messageType = messageType;
	}

	public NewsResponse(String message, Boolean messageType) {
		super();
		this.message = message;
		this.messageType = messageType;
	}

	
	
}
