package com.example.news_module.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sub_categorys")
public class SubCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer subId;
	
	@Column(name = "sub_title")
	private String subTitle;
	
	@Column(name = "main_id")
	private Integer mainId;

	public Integer getSubId() {
		return subId;
	}

	public void setSubId(Integer subId) {
		this.subId = subId;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public Integer getMainId() {
		return mainId;
	}

	public void setMainId(Integer mainId) {
		this.mainId = mainId;
	}

	public SubCategory(Integer subId, String subTitle, Integer mainId) {
		super();
		this.subId = subId;
		this.subTitle = subTitle;
		this.mainId = mainId;
	}

	public SubCategory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SubCategory(String subTitle, Integer mainId) {
		super();
		this.subTitle = subTitle;
		this.mainId = mainId;
	}

	public SubCategory(Integer subId, String subTitle) {
		super();
		this.subId = subId;
		this.subTitle = subTitle;
	}
}
