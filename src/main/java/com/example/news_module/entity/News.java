package com.example.news_module.entity;

import java.time.LocalDateTime;

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
	
	@Column(name = "sub_title")
	private String subTitle;
	
	@Column(name = "text")
	private String text;
	
	@Column(name = "open")
	private Boolean open;
	
	
	@Column(name = "creat_date")
	private LocalDateTime creatDate;
	
	
	@Column(name = "updata_date")
	private LocalDateTime updataDate;
	
	
	@Column(name = "open_date")
	private LocalDateTime openDate;

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

	public LocalDateTime getCreatDate() {
		return creatDate;
	}

	public void setCreatDate(LocalDateTime creatDate) {
		this.creatDate = creatDate;
	}

	public LocalDateTime getUpdataDate() {
		return updataDate;
	}

	public void setUpdataDate(LocalDateTime updataDate) {
		this.updataDate = updataDate;
	}

	public LocalDateTime getOpenDate() {
		return openDate;
	}

	public void setOpenDate(LocalDateTime openDate) {
		this.openDate = openDate;
	}

	public News(Integer newsId, Integer mainCategory, Integer subCategory, String title, String text, Boolean open,
			LocalDateTime creatDate, LocalDateTime updataDate, LocalDateTime openDate) {
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

	public News(Integer mainCategory, Integer subCategory, String title, String text, Boolean open,
			LocalDateTime creatDate, LocalDateTime openDate) {
		super();
		this.mainCategory = mainCategory;
		this.subCategory = subCategory;
		this.title = title;
		this.text = text;
		this.open = open;
		this.creatDate = creatDate;
		this.openDate = openDate;
	}

	public News() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public News(Integer newsId, Integer mainCategory, Integer subCategory, String title, String subTitle, String text,
			Boolean open, LocalDateTime creatDate, LocalDateTime updataDate, LocalDateTime openDate) {
		super();
		this.newsId = newsId;
		this.mainCategory = mainCategory;
		this.subCategory = subCategory;
		this.title = title;
		this.subTitle = subTitle;
		this.text = text;
		this.open = open;
		this.creatDate = creatDate;
		this.updataDate = updataDate;
		this.openDate = openDate;
	}

	
}
