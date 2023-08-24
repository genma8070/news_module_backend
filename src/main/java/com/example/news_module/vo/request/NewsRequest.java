package com.example.news_module.vo.request;

import java.time.LocalDate;

public class NewsRequest {

	private Integer newsId;

	private Integer mainCategory;

	private Integer subCategory;

	private String title;

	private String text;
	
	private Boolean open;
	
	private LocalDate creatDate;
	
	private LocalDate updataDate;
	
	private LocalDate startDate;
	
	private LocalDate endDate;
	
	private LocalDate openDate;
	
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

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public LocalDate getCreatDate() {
		return creatDate;
	}

	public void setCreatDate(LocalDate creatDate) {
		this.creatDate = creatDate;
	}

	public LocalDate getUpdataDate() {
		return updataDate;
	}

	public void setUpdataDate(LocalDate updataDate) {
		this.updataDate = updataDate;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public LocalDate getOpenDate() {
		return openDate;
	}

	public void setOpenDate(LocalDate openDate) {
		this.openDate = openDate;
	}

	
}
