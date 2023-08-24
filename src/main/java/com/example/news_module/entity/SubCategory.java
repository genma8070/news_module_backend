package com.example.news_module.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sub_categorys")
public class SubCategory {

	@Id
	@Column(name = "id")
	private Integer subId;
	
	@Column(name = "sub_title")
	private String subTitle;
	
	@Column(name = "father")
	private Integer fatherId;

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

	public Integer getFatherId() {
		return fatherId;
	}

	public void setFatherId(Integer fatherId) {
		this.fatherId = fatherId;
	}

	public SubCategory(Integer subId, String subTitle, Integer fatherId) {
		super();
		this.subId = subId;
		this.subTitle = subTitle;
		this.fatherId = fatherId;
	}

	public SubCategory() {
		super();
		// TODO Auto-generated constructor stub
	}
}
