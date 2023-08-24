package com.example.news_module.vo.response;

import java.util.List;

public class SubCategoryResponse {

	private Integer subId;

	private String subTitle;

	private Integer fatherId;

	private String message;
	
	private List<SubCategoryResponse> list;
	public Integer getSubId() {
		return subId;
	}

	public void setSubId(Integer subId) {
		this.subId = subId;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public Integer getFatherId() {
		return fatherId;
	}

	public void setFatherId(Integer fatherId) {
		this.fatherId = fatherId;
	}

	public SubCategoryResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SubCategoryResponse(Integer subId, String subTitle, Integer fatherId) {
		super();
		this.subId = subId;
		this.subTitle = subTitle;
		this.fatherId = fatherId;
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

}
