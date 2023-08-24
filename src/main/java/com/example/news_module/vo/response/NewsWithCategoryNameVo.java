package com.example.news_module.vo.response;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class NewsWithCategoryNameVo {
	
	private List<NewsWithCategoryNameVo> list;
	
	private Integer newsId;

	private Integer mainCategory;

	private Integer subCategory;
	
	private String mainCategoryName;
	
	private String subCategoryName;

	private String title;

	private String text;
	
	private Boolean open;
	
	private LocalDate creatDate;
	
	private LocalDate updataDate;

	private LocalDate openDate;
	
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public NewsWithCategoryNameVo(List<NewsWithCategoryNameVo> list, Integer newsId, Integer mainCategory,
			Integer subCategory, String mainCategoryName, String subCategoryName, String title, String text,
			Boolean open, LocalDate creatDate, LocalDate updataDate, String message) {
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
		this.updataDate = updataDate;
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

	public LocalDate getOpenDate() {
		return openDate;
	}

	public void setOpenDate(LocalDate openDate) {
		this.openDate = openDate;
	}

	public NewsWithCategoryNameVo(List<NewsWithCategoryNameVo> list, Integer newsId, Integer mainCategory,
			Integer subCategory, String mainCategoryName, String subCategoryName, String title, String text,
			Boolean open, LocalDate creatDate, LocalDate updataDate, LocalDate openDate) {
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
		this.updataDate = updataDate;
		this.openDate = openDate;
	}
	
}
