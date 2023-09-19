package com.example.news_module.contants;

//	記入したデータをチェックする列挙型
	public enum Pattern{
		TITLE(false, "^.{1,45}$", "タイトルは1~45桁以内で入力してください"),
		TEXT(false, "^.{1,500}$", "本文は1~500桁以内で入力してください"),
		CATEGORY_NAME(false, "^.{1,10}$", "カテゴリ名称は1~10桁以内で入力してください");

//		対照用パターン
		private String Pattern;
//		エラーメッセージ
		private String message;
//		メッセージ種類
		private Boolean type;
		
		public String getPattern() {
			return Pattern;
		}
		public void setPattern(String pattern) {
			Pattern = pattern;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		private Pattern(String pattern, String message) {
			Pattern = pattern;
			this.message = message;
		}
		private Pattern(String message) {
			this.message = message;
		}
		public Boolean getType() {
			return type;
		}
		public void setType(Boolean type) {
			this.type = type;
		}
		private Pattern(Boolean type) {
			this.type = type;
		}
		private Pattern(Boolean type, String pattern, String message) {
			this.type = type;
			Pattern = pattern;
			this.message = message;
		}
		
	}


