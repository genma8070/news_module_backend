package com.example.news_module.vo.response;

import java.util.List;

import com.example.news_module.entity.SubCategory;

public class SubCategoryResponse {

	private Integer subId;

	private String subCategoryName;

	private Integer mainId;
	
	private Integer news;

	private String message;
	
	private List<SubCategoryResponse> list;
	
	private List<SubCategory> subList;
	
	private SubCategory sub;
	
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

}
