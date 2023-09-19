package com.example.news_module.vo.request;

import java.util.List;

//サブカテゴリ関連のリクエストのクラス
public class SubCategoryRequest {
	
//	サブカテゴリのIDリスト
	private List<Integer> deleteList;

//	サブカテゴリのID
	private Integer subId;

//	サブカテゴリの名称
	private String subCategoryName;

//	属しているメインカテゴリのID
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
