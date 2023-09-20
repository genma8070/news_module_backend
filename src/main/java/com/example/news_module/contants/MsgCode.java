package com.example.news_module.contants;

//メッセージコード
	public enum MsgCode{
		
//		成功メッセージ
		NEWS_CREATE_SUCCESSFUL(true, "ニュース追加成功しました"),
		NEWS_EDIT_SUCCESSFUL(true, "ニュース更新成功しました"),
		NEWS_HIDE_SUCCESSFUL(true, "ニュース隠蔽成功しました"),
		NEWS_OPEN_SUCCESSFUL(true, "ニュース公開成功しました"),
		NEWS_DELETE_SUCCESSFUL(true, "ニュース削除成功しました"),
		CATEGORY_CREATE_SUCCESSFUL(true, "カテゴリ追加成功しました"),
		CATEGORY_EDIT_SUCCESSFUL(true, "カテゴリ更新成功しました"),
		CATEGORY_DELETE_SUCCESSFUL(true, "カテゴリ削除成功しました"),
		
//		エラーメッセージ
		IS_EXISTED(false, "同名のカテゴリが存在しています"),
		CHOICE_CATEGORY(false, "カテゴリを選択してください"),
		NEED_MAINCATEGORY_ID(false, "所属するメインカテゴリを選択してください"),
		NOT_EMPTY(false, "ニュースがあるカテゴリは削除できません"),
		CANNOT_BLANK(false, "空白だけの記入はいけません"),
		NO_TARGET(false, "実行対象がいません"),
		NOT_FOUND(false, "資料が見つかりませんでした"),
		END_DATE_CANNOT_EARLY_THAN_START_DATE(false, "終了時間は開始時間より前になりません");
		
//		メッセージ種類
		private Boolean type;
//		メッセージ
		private String message;
		
		public Boolean getType() {
			return type;
		}
		public void setType(Boolean type) {
			this.type = type;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		private MsgCode(Boolean type, String message) {
			this.type = type;
			this.message = message;
		}
		
	}


