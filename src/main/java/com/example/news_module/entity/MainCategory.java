package com.example.news_module.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "main_categorys")
public class MainCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer mainId;
	
	@Column(name = "main_category_name")
	private String mainCategoryName;

	public Integer getMainId() {
		return mainId;
	}

	public void setMainId(Integer mainId) {
		this.mainId = mainId;
	}

	public String getMainCategoryName() {
		return mainCategoryName;
	}

	public void setMainCategoryName(String mainCategoryName) {
		this.mainCategoryName = mainCategoryName;
	}

	public MainCategory(Integer mainId, String mainCategoryName) {
		super();
		this.mainId = mainId;
		this.mainCategoryName = mainCategoryName;
	}

	public MainCategory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MainCategory(String mainCategoryName) {
		super();
		this.mainCategoryName = mainCategoryName;
	}
	

}
