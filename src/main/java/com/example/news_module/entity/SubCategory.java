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
	
	@Column(name = "sub_category_name")
	private String subCategoryName;
	
	@Column(name = "main_id")
	private Integer mainId;

	public Integer getSubId() {
		return subId;
	}

	public void setSubId(Integer subId) {
		this.subId = subId;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	public Integer getMainId() {
		return mainId;
	}

	public void setMainId(Integer mainId) {
		this.mainId = mainId;
	}

	public SubCategory(Integer subId, String subCategoryName, Integer mainId) {
		super();
		this.subId = subId;
		this.subCategoryName = subCategoryName;
		this.mainId = mainId;
	}

	public SubCategory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SubCategory(String subCategoryName, Integer mainId) {
		super();
		this.subCategoryName = subCategoryName;
		this.mainId = mainId;
	}

	public SubCategory(Integer subId, String subCategoryName) {
		super();
		this.subId = subId;
		this.subCategoryName = subCategoryName;
	}
}
