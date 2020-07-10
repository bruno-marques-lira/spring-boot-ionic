package com.brnmlira.cursomc.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.brnmlira.cursomc.domain.Cliente;

@Service
public class SmtpEmailService extends AbstractEmailService {

	@Autowired
	private MailSender mailSender;

	@Autowired
	private JavaMailSender javaMailSender;

	private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);

	@Override
	public void sendEmail(SimpleMailMessage msg) {
		mailSender.send(msg);
		LOG.info(msg.toString());
		LOG.info("Email enviado");
	}

	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		LOG.info("Enviando email...");
		javaMailSender.send(msg);
		LOG.info(msg.toString());
		LOG.info("Email enviado");
	}

	@Override
	public void sendNewPasswordEmail(Cliente cliente, String newPass) throws Exception {
		// TODO Auto-generated method stub
		if(newPass == null) {
			throw new Exception("A senha digitada é nulla. Digite uma nova senha.");
		} else if (cliente.getSenha() == newPass) {
			throw new Exception("Informe uma senha diferente da atual");
		} else {
			cliente.setSenha(newPass);
		}
	}
}