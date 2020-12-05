package com.mvc.ultis;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.handlers.text_html;

import java.util.Properties;

public class SendEmail {

	public void sendEmail(String emailReceive, int verifyCode) {

		final String username = "haropire@gmail.com";
		final String password = "ditmemay";

		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("haropire@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailReceive));
			message.setSubject("Mã xác nhận đổi mật khẩu Enjoy2gether");
			message.setText(String.valueOf(verifyCode));

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
