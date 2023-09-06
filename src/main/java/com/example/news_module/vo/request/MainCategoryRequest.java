package com.example.news_module.vo.request;

import java.util.List;

public class MainCategoryRequest {
	
	private List<Integer> deleteList;
	
	private Integer mainId;

	private String mainTitle;

	public Integer getMainId() {
		return mainId;
	}

	public void setMainId(Integer mainId) {
		this.mainId = mainId;
	}

	public String getMainTitle() {
		return mainTitle;
	}

	public void setMainTitle(String mainTitle) {
		this.mainTitle = mainTitle;
	}

	public List<Integer> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<Integer> deleteList) {
		this.deleteList = deleteList;
	}

}
