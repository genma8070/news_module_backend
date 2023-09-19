package com.example.news_module.vo.response;

import java.util.List;

import com.example.news_module.entity.MainCategory;

//メインカテゴリ関連のリスポンスのクラス
public class MainCategoryResponse {

//	メインカテゴリのID
	private Integer mainId;

//	メインカテゴリ名称
	private String mainCategoryName;
	
//	メッセージ
	private String message;
	
//	メインカテゴリ中のニュース数
	private Integer news;
	
//	メインカテゴリのエンティティ
	private MainCategory main;
	
//	メインカテゴリのエンティティのリスト
	private List<MainCategory> list;
	
//	メインカテゴリのリスポンスのリスト
	private List<MainCategoryResponse> list2;
	
//	メッセージ種類
    private Boolean messageType;
		

	public Integer getMainId() {
		return mainId;
	}

	public void setMainId(Integer mainId) {
		this.mainId = mainId;
	}

	public String getMainCategoryName() {
		return mainCategoryName;
	}

	public void setMainCategoryName(String mainCategoryName) {
		this.mainCategoryName = mainCategoryName;
	}

	public MainCategoryResponse(Integer mainId, String mainCategoryName) {
		super();
		this.mainId = mainId;
		this.mainCategoryName = mainCategoryName;
	}

	public MainCategoryResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<MainCategory> getList() {
		return list;
	}

	public void setList(List<MainCategory> list) {
		this.list = list;
	}

	public MainCategoryResponse(Integer mainId, String mainCategoryName, String message, List<MainCategory> list) {
		super();
		this.mainId = mainId;
		this.mainCategoryName = mainCategoryName;
		this.message = message;
		this.list = list;
	}



	public MainCategoryResponse(String message) {
		super();
		this.message = message;
	}

	public List<MainCategoryResponse> getList2() {
		return list2;
	}

	public void setList2(List<MainCategoryResponse> list2) {
		this.list2 = list2;
	}

	public MainCategoryResponse(List<MainCategoryResponse> list2) {
		super();
		this.list2 = list2;
	}

	public Integer getNews() {
		return news;
	}

	public void setNews(Integer news) {
		this.news = news;
	}

	public MainCategoryResponse(Integer mainId, String mainCategoryName, Integer news) {
		super();
		this.mainId = mainId;
		this.mainCategoryName = mainCategoryName;
		this.news = news;
	}

	public MainCategory getMain() {
		return main;
	}

	public void setMain(MainCategory main) {
		this.main = main;
	}

	public MainCategoryResponse(MainCategory main) {
		super();
		this.main = main;
	}

	public Boolean getMessageType() {
		return messageType;
	}

	public void setMessageType(Boolean messageType) {
		this.messageType = messageType;
	}

	public MainCategoryResponse(String message, Boolean messageType) {
		super();
		this.message = message;
		this.messageType = messageType;
	}

}
