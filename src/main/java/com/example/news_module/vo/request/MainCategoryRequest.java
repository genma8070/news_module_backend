package com.example.news_module.vo.request;

import java.util.List;

//メインカテゴリ関連のリクエストのクラス
public class MainCategoryRequest {
	
//	メインカテゴリのIDリスト
	private List<Integer> deleteList;
	
//	メインカテゴリのID
	private Integer mainId;

//	メインカテゴリの名称
	private String mainCategoryName;

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

	public List<Integer> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<Integer> deleteList) {
		this.deleteList = deleteList;
	}

}
