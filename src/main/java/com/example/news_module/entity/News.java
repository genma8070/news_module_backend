package com.example.news_module.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "news")
public class News {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "news_id")
	private Integer newsId;
	
	@Column(name = "main_category")
	private Integer mainCategory;
	
	@Column(name = "sub_category")
	private Integer subCategory;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "text")
	private String text;
	
	@Column(name = "open")
	private Boolean open;
	
	@Column(name = "creat_date")
	private LocalDate creatDate;
	
	@Column(name = "updata_date")
	private LocalDate updataDate;
	
	@Column(name = "open_date")
	private LocalDate openDate;

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

	public News(Integer newsId, Integer mainCategory, Integer subCategory, String title, String text, Boolean open,
			LocalDate creatDate, LocalDate updataDate) {
		super();
		this.newsId = newsId;
		this.mainCategory = mainCategory;
		this.subCategory = subCategory;
		this.title = title;
		this.text = text;
		this.open = open;
		this.creatDate = creatDate;
		this.updataDate = updataDate;
	}

	public News() {
		super();
		// TODO Auto-generated constructor stub
	}

	public News(Integer newsId, Boolean open) {
		super();
		this.newsId = newsId;
		this.open = open;
	}

	public News(Integer mainCategory, Integer subCategory, String title, String text, Boolean open,
			LocalDate creatDate) {
		super();
		this.mainCategory = mainCategory;
		this.subCategory = subCategory;
		this.title = title;
		this.text = text;
		this.open = open;
		this.creatDate = creatDate;
	}

	public News(Integer newsId, Integer mainCategory, Integer subCategory, String title, String text,
			LocalDate updataDate) {
		super();
		this.newsId = newsId;
		this.mainCategory = mainCategory;
		this.subCategory = subCategory;
		this.title = title;
		this.text = text;
		this.updataDate = updataDate;
	}

	public LocalDate getOpenDate() {
		return openDate;
	}

	public void setOpenDate(LocalDate openDate) {
		this.openDate = openDate;
	}

	public News(Integer newsId, Integer mainCategory, Integer subCategory, String title, String text, Boolean open,
			LocalDate creatDate, LocalDate updataDate, LocalDate openDate) {
		super();
		this.newsId = newsId;
		this.mainCategory = mainCategory;
		this.subCategory = subCategory;
		this.title = title;
		this.text = text;
		this.open = open;
		this.creatDate = creatDate;
		this.updataDate = updataDate;
		this.openDate = openDate;
	}

	

}
