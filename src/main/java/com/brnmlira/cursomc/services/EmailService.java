package com.brnmlira.cursomc.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.brnmlira.cursomc.domain.Cliente;
import com.brnmlira.cursomc.domain.Pedido;

@Service
public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);

	void sendEmail(SimpleMailMessage msg);

	void sendNewPasswordEmail(Cliente cliente, String newPass);

	void sendOrderConfirmationHtmlEmail(Pedido obj);
}
