package com.example.news_module.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//ニューステーブルのエンティティ
@Entity
@Table(name = "news")
public class News {

//	ニュースのID（自動生成）
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "news_id")
	private Integer newsId;
	
//	ニュースのメインカテゴリ
	@Column(name = "main_category")
	private Integer mainCategory;
	
//	ニュースのサブカテゴリ
	@Column(name = "sub_category")
	private Integer subCategory;
	
//	ニュースのタイトル
	@Column(name = "title")
	private String title;
	
//	ニュースのサブタイトル
	@Column(name = "sub_title")
	private String subTitle;
	
//	ニュースの本文
	@Column(name = "text")
	private String text;
	
//	ニュースの公開状態
	@Column(name = "open")
	private Integer open;
	
//	ニュースの作成日
	@Column(name = "creat_date")
	private LocalDateTime creatDate;
	
//	ニュースの更新日
	@Column(name = "update_date")
	private LocalDateTime updateDate;
	
//	ニュースの公開日
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

	public LocalDateTime getOpenDate() {
		return openDate;
	}

	public void setOpenDate(LocalDateTime openDate) {
		this.openDate = openDate;
	}

	public News(Integer newsId, Integer mainCategory, Integer subCategory, String title, String text, Integer open,
			LocalDateTime creatDate, LocalDateTime updateDate, LocalDateTime openDate) {
		super();
		this.newsId = newsId;
		this.mainCategory = mainCategory;
		this.subCategory = subCategory;
		this.title = title;
		this.text = text;
		this.open = open;
		this.creatDate = creatDate;
		this.updateDate = updateDate;
		this.openDate = openDate;
	}

	public News(Integer mainCategory, Integer subCategory, String title, String text, Integer open,
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
			Integer open, LocalDateTime creatDate, LocalDateTime updateDate, LocalDateTime openDate) {
		super();
		this.newsId = newsId;
		this.mainCategory = mainCategory;
		this.subCategory = subCategory;
		this.title = title;
		this.subTitle = subTitle;
		this.text = text;
		this.open = open;
		this.creatDate = creatDate;
		this.updateDate = updateDate;
		this.openDate = openDate;
	}

	public News(Integer mainCategory, Integer subCategory, String title, String subTitle, String text, Integer open,
			LocalDateTime creatDate, LocalDateTime updateDate, LocalDateTime openDate) {
		super();
		this.mainCategory = mainCategory;
		this.subCategory = subCategory;
		this.title = title;
		this.subTitle = subTitle;
		this.text = text;
		this.open = open;
		this.creatDate = creatDate;
		this.updateDate = updateDate;
		this.openDate = openDate;
	}

	public News(Integer mainCategory, Integer subCategory, String title, String subTitle, String text, Integer open,
			LocalDateTime creatDate, LocalDateTime openDate) {
		super();
		this.mainCategory = mainCategory;
		this.subCategory = subCategory;
		this.title = title;
		this.subTitle = subTitle;
		this.text = text;
		this.open = open;
		this.creatDate = creatDate;
		this.openDate = openDate;
	}

	public News(Integer newsId, Integer open) {
		super();
		this.newsId = newsId;
		this.open = open;
	}

	public News(Integer newsId, LocalDateTime openDate) {
		super();
		this.newsId = newsId;
		this.openDate = openDate;
	}

	
}
