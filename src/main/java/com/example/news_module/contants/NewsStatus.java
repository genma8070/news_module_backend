package com.example.news_module.contants;

//ニュース状態
public enum NewsStatus {

//	開放中
	OPEN(1),
//	隠蔽中
	HIDE(2),
//	未公開
	WAIT(0);

	//ニュース状態
	private Integer Status;

	public Integer getStatus() {
		return Status;
	}

	public void setStatus(Integer status) {
		Status = status;
	}

	private NewsStatus(Integer status) {
		Status = status;
	}

}
