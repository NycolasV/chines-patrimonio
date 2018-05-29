package br.info.pweb.chines.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtils {

	public static final String remetente = "senai132.info.2017.1s@gmail.com";
	
	public static final String senhaRemetente = "TecInfoManha2017";
	
	private static Session getConfiguracoesEmail() {
		Properties props = new Properties();
		
		props.put("mail.smtp.host", "smtp.gmail.com");
		
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.port", "465");
		
		props.put("mail.smtp.port", "465");
		
		Session configuracao = Session.getInstance(props, new Authenticator() {	
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(remetente, senhaRemetente);
			}
		});
		
		return configuracao;
	}
	
	public static void enviarMensagem(String titulo, String corpo, String destinatario) throws AddressException, MessagingException {
		
		Message msg = new MimeMessage(getConfiguracoesEmail());
		msg.setFrom(new InternetAddress(remetente));
		msg.addRecipient(RecipientType.TO, new InternetAddress(destinatario));
		msg.setSubject(titulo);
		msg.setText(corpo);
		
		Transport.send(msg);
	}
}