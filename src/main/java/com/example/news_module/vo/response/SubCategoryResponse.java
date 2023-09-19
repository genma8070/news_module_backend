package com.example.news_module.vo.response;

import java.util.List;

import com.example.news_module.entity.SubCategory;

//サブカテゴリ関連のリスポンスのクラス
public class SubCategoryResponse {

//	サブカテゴリのID
	private Integer subId;

//	サブカテゴリの名称
	private String subCategoryName;

//	属しているメインカテゴリのID
	private Integer mainId;
	
//	カテゴリ中のニュース数
	private Integer news;

//	メッセージ
	private String message;
	
//	サブカテゴリのリスポンスのリスト
	private List<SubCategoryResponse> list;
	
//	サブカテゴリのエンティティのリスト
	private List<SubCategory> subList;
	
//	サブカテゴリのエンティティのリスト
	private SubCategory sub;
	
//	メッセージ種類
    private Boolean messageType;
	
	public Integer getSubId() {
		return subId;
	}

	public void setSubId(Integer subId) {
		this.subId = subId;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	public Integer getMainId() {
		return mainId;
	}

	public void setMainId(Integer mainId) {
		this.mainId = mainId;
	}

	public SubCategoryResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SubCategoryResponse(Integer subId, String subCategoryName, Integer mainId) {
		super();
		this.subId = subId;
		this.subCategoryName = subCategoryName;
		this.mainId = mainId;
	}

	public List<SubCategoryResponse> getList() {
		return list;
	}

	public void setList(List<SubCategoryResponse> list) {
		this.list = list;
	}

	public SubCategoryResponse(List<SubCategoryResponse> list) {
		super();
		this.list = list;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public SubCategoryResponse(String message) {
		super();
		this.message = message;
	}
	
	public List<SubCategory> getSubList() {
		return subList;
	}

	public void setSubList(List<SubCategory> subList) {
		this.subList = subList;
	}

	public SubCategoryResponse(String message, List<SubCategory> subList) {
		super();
		this.message = message;
		this.subList = subList;
	}

	public Integer getNews() {
		return news;
	}

	public void setNews(Integer news) {
		this.news = news;
	}

	public SubCategoryResponse(Integer subId, String subCategoryName, Integer mainId, Integer news) {
		super();
		this.subId = subId;
		this.subCategoryName = subCategoryName;
		this.mainId = mainId;
		this.news = news;
	}

	public SubCategory getSub() {
		return sub;
	}

	public void setSub(SubCategory sub) {
		this.sub = sub;
	}

	public SubCategoryResponse(SubCategory sub) {
		super();
		this.sub = sub;
	}

	public Boolean getMessageType() {
		return messageType;
	}

	public void setMessageType(Boolean messageType) {
		this.messageType = messageType;
	}

	public SubCategoryResponse(String message, Boolean messageType) {
		super();
		this.message = message;
		this.messageType = messageType;
	}

}
