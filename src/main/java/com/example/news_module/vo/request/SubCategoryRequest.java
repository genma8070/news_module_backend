package com.example.news_module.vo.request;

import java.util.List;

public class SubCategoryRequest {
	
	private List<Integer> deleteList;

	private Integer subId;

	private String subCategoryName;

	private Integer mainId;

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

	public List<Integer> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<Integer> deleteList) {
		this.deleteList = deleteList;
	}

}
