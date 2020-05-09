package es.eventup.app.controllers;

import java.util.Map;

import javax.security.auth.Subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import es.eventup.app.models.entity.Mail;
import es.eventup.app.util.mail.MailSend;




@Controller
@SessionAttributes("mail")
public class MailController {
	
	@Autowired
	private MailSend ms;

	@RequestMapping(value="/mail/enviar", method=RequestMethod.GET)
	public String crear(Map<String, Object> model) {
		model.put("tituloWeb", "Mail: Enviar");
		model.put("mail", new Mail());
		return "mail/enviar";
	}
	
	@RequestMapping(value="/mail/enviar", method=RequestMethod.POST)
	public String enviar(Mail mail, SessionStatus stat) {
		
		ms.sendEmail(mail.getTo(), mail.getSubject(), mail.getContent());
		stat.setComplete();
		return "redirect:/";
	}

}
