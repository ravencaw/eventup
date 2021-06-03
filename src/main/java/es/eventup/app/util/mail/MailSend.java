package es.eventup.app.util.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSend {

	@Autowired
	private JavaMailSender mailSender;

	// Pasamos por parametro: destinatario, asunto y el mensaje
	public void sendEmail(String to, String subject, String content) {

		SimpleMailMessage email = new SimpleMailMessage();

		email.setTo(to);
		email.setSubject(subject);
		email.setText(content);

		mailSender.send(email);
	}

	// Pasamo es array con los correos para que se envia a todos como COPIA OCULTA
	public void sendEmailCCO(String[] cco, String subject, String content) {

		SimpleMailMessage email = new SimpleMailMessage();

		email.setBcc(cco);
		email.setSubject(subject);
		email.setText(content);

		mailSender.send(email);
	}

	public void sendEmailEntradas(String[] cco, String subject, String content) {

		SimpleMailMessage email = new SimpleMailMessage();

		email.setBcc(cco);
		email.setSubject(subject);
		email.setText(content);
		mailSender.send(email);
	}

}
