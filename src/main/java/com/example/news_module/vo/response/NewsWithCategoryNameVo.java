package com.example.news_module.vo.response;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

//ニューステーブルを基づいて外部結合によりメインカテゴリの名称とサブカテゴリの名称を追加したデータを扱うためのVo
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class NewsWithCategoryNameVo {
	
	private List<NewsWithCategoryNameVo> list;
	
	private Integer newsId;

	private Integer mainCategory;

	private Integer subCategory;
	
	private String mainCategoryName;
	
	private String subCategoryName;

	private String title;
	
	private String subTitle;

	private String text;
	
	private Boolean open;
	
	
	private LocalDateTime creatDate;
	
	
	private LocalDateTime updateDate;

	
	private LocalDateTime openDate;
	
	private String message;

	public List<NewsWithCategoryNameVo> getList() {
		return list;
	}

	public void setList(List<NewsWithCategoryNameVo> list) {
		this.list = list;
	}

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

	public String getMainCategoryName() {
		return mainCategoryName;
	}

	public void setMainCategoryName(String mainCategoryName) {
		this.mainCategoryName = mainCategoryName;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public NewsWithCategoryNameVo(List<NewsWithCategoryNameVo> list, Integer newsId, Integer mainCategory,
			Integer subCategory, String mainCategoryName, String subCategoryName, String title, String text,
			Boolean open, LocalDateTime creatDate, LocalDateTime updateDate, String message) {
		super();
		this.list = list;
		this.newsId = newsId;
		this.mainCategory = mainCategory;
		this.subCategory = subCategory;
		this.mainCategoryName = mainCategoryName;
		this.subCategoryName = subCategoryName;
		this.title = title;
		this.text = text;
		this.open = open;
		this.creatDate = creatDate;
		this.updateDate = updateDate;
		this.message = message;
	}

	public NewsWithCategoryNameVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NewsWithCategoryNameVo(String message) {
		super();
		this.message = message;
	}

	public NewsWithCategoryNameVo(List<NewsWithCategoryNameVo> list) {
		super();
		this.list = list;
	}

	public LocalDateTime getOpenDate() {
		return openDate;
	}

	public void setOpenDate(LocalDateTime openDate) {
		this.openDate = openDate;
	}

	public NewsWithCategoryNameVo(List<NewsWithCategoryNameVo> list, Integer newsId, Integer mainCategory,
			Integer subCategory, String mainCategoryName, String subCategoryName, String title, String text,
			Boolean open, LocalDateTime creatDate, LocalDateTime updateDate, LocalDateTime openDate) {
		super();
		this.list = list;
		this.newsId = newsId;
		this.mainCategory = mainCategory;
		this.subCategory = subCategory;
		this.mainCategoryName = mainCategoryName;
		this.subCategoryName = subCategoryName;
		this.title = title;
		this.text = text;
		this.open = open;
		this.creatDate = creatDate;
		this.updateDate = updateDate;
		this.openDate = openDate;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public NewsWithCategoryNameVo(List<NewsWithCategoryNameVo> list, Integer newsId, Integer mainCategory,
			Integer subCategory, String mainCategoryName, String subCategoryName, String title, String subTitle,
			String text, Boolean open, LocalDateTime creatDate, LocalDateTime updateDate, LocalDateTime openDate,
			String message) {
		super();
		this.list = list;
		this.newsId = newsId;
		this.mainCategory = mainCategory;
		this.subCategory = subCategory;
		this.mainCategoryName = mainCategoryName;
		this.subCategoryName = subCategoryName;
		this.title = title;
		this.subTitle = subTitle;
		this.text = text;
		this.open = open;
		this.creatDate = creatDate;
		this.updateDate = updateDate;
		this.openDate = openDate;
		this.message = message;
	}

	public NewsWithCategoryNameVo(Integer newsId, Integer mainCategory, Integer subCategory, String mainCategoryName,
			String subCategoryName, String title, String subTitle, String text, Boolean open, LocalDateTime creatDate,
			LocalDateTime updateDate, LocalDateTime openDate) {
		super();
		this.newsId = newsId;
		this.mainCategory = mainCategory;
		this.subCategory = subCategory;
		this.mainCategoryName = mainCategoryName;
		this.subCategoryName = subCategoryName;
		this.title = title;
		this.subTitle = subTitle;
		this.text = text;
		this.open = open;
		this.creatDate = creatDate;
		this.updateDate = updateDate;
		this.openDate = openDate;
	}
	
}
