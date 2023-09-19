package com.example.news_module.vo.request;

import java.time.LocalDateTime;
import java.util.List;

//ニュース関連のリクエストのクラス
public class NewsRequest {
	
//	ニュースIDのリスト
	private List<Integer> list;

//	ニュースのID
	private Integer newsId;
	
//	ページごとに表示するニュース数を意味する引数
	private Integer items;

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
	
//	検索条件の開始日
	private LocalDateTime startDate;
	
//	検索条件の結束日
	private LocalDateTime endDate;
	
//	ニュースの公開日
	private LocalDateTime openDate;
	
//	ニュースの並べ方
	private Boolean sort;
	
//	ページ数を意味する引数
	private Integer index;

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

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public LocalDateTime getOpenDate() {
		return openDate;
	}

	public void setOpenDate(LocalDateTime openDate) {
		this.openDate = openDate;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public List<Integer> getList() {
		return list;
	}

	public void setList(List<Integer> list) {
		this.list = list;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public Integer getItems() {
		return items;
	}

	public void setItems(Integer items) {
		this.items = items;
	}

	public Boolean getSort() {
		return sort;
	}

	public void setSort(Boolean sort) {
		this.sort = sort;
	}

	
}
