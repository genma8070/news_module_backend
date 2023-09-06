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
	
	@Column(name = "main_title")
	private String mainTitle;

	public Integer getMainId() {
		return mainId;
	}

	public void setMainId(Integer mainId) {
		this.mainId = mainId;
	}

	public String getMainTitle() {
		return mainTitle;
	}

	public void setMainTitle(String mainTitle) {
		this.mainTitle = mainTitle;
	}

	public MainCategory(Integer mainId, String mainTitle) {
		super();
		this.mainId = mainId;
		this.mainTitle = mainTitle;
	}

	public MainCategory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MainCategory(String mainTitle) {
		super();
		this.mainTitle = mainTitle;
	}
	

}
