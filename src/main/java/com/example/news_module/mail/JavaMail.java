package com.example.news_module.mail;

import java.time.LocalDateTime;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class JavaMail {
// ---------------------------------------------------------基本資料

	private String userName = "aszc30320@gmail.com"; // 寄件者信箱
	private String password = "srrlbtghlvufshhb"; // 寄件者密碼
	private String customer = "aszc30320@gmail.com"; // 收件者郵箱
	private String subject = "帳號驗證信件"; // 標題
	 // 內容
	private String token = "";
	private LocalDateTime limit = null;
	public void SendMail(String customer,String token,LocalDateTime limit) {
		
		this.customer = customer;
		this.token = token;
		this.limit = limit;
		
		
		// ---------------------------------------------------------連線設定
		Properties prop = new Properties();

		// 設定連線為smtp
		prop.setProperty("mail.transport.protocol", "smtp");

		// host主機:smtp.gmail.com
		prop.setProperty("mail.host", "smtp.gmail.com");

		// host port:465
		prop.put("mail.smtp.port", "465");

		// 寄件者帳號需要驗證：是
		prop.put("mail.smtp.auth", "true");

		// 需要安全資料傳輸層 (SSL)：是
		prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		// 安全資料傳輸層 (SSL) 通訊埠：465
		prop.put("mail.smtp.socketFactory.port", "465");

		// 顯示連線資訊
//		prop.put("mail.debug", "true");

		// ---------------------------------------------------------驗證
		// ---------------------------------------------------------Session默認屬性設定值
		// 匿名類別
//		Session session = Session.getDefaultInstance(prop, new Authenticator() {
//
//			@Override
//			protected PasswordAuthentication getPasswordAuthentication() {
//				return new PasswordAuthentication(userName, password);
//			}
//		});

		// class
		Auth auth = new Auth(userName, password);
		Session session = Session.getDefaultInstance(prop, auth);

		// ---------------------------------------------------------Message郵件格式
		MimeMessage message = new MimeMessage(session);

		try {
			// 寄件者
			// 匿名類別
			// message.setSender(new InternetAddress(userName));

			// class
			InternetAddress sender = new InternetAddress(userName);
			message.setSender(sender);
			
			// 收件者
			message.setRecipient(RecipientType.TO, new InternetAddress(customer));
			
			// 標題
			message.setSubject(subject);
			
			// 內容/格式
			
			String txt = ("<h1>我叫你驗證</h1>" + generateLink(token, limit));
			message.setContent(txt, "text/html;charset = UTF-8");
			
			
			// ---------------------------------------------------------Transport傳送Message
			Transport transport = session.getTransport();
			
			// transport將message送出
			transport.send(message);
			
			// 關閉Transport
			transport.close();
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	public static String generateLink(String token, LocalDateTime limit) {
        String baseUrl = "http://localhost:5173/comfirm";
        return baseUrl + "/verify?token=" + token +"&limit=" + limit;
    }
}



class Auth extends Authenticator {

	private String userName;
	private String password;

	public Auth(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		PasswordAuthentication pa = new PasswordAuthentication(userName, password);
		return pa;
	}

}