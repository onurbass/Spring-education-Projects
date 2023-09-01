package com.socialmedia.service;

import com.socialmedia.rabbitmq.model.MailModel;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

  private final JavaMailSender javaMailSender;

  public void sendMail(MailModel model) {
	SimpleMailMessage mailMessage = new SimpleMailMessage();
	mailMessage.setFrom("${MAIL}");
	mailMessage.setTo(model.getEmail());
	mailMessage.setSubject("AKTIVASYON KODUNUZ...");
	mailMessage.setText("token=>" + model.getToken() + "\n" + "aktivasyon kodunuz =>" + model.getActivationCode());
	javaMailSender.send(mailMessage);
  }
}
