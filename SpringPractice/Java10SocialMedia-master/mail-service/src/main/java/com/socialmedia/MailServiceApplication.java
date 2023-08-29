package com.socialmedia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootApplication
public class MailServiceApplication {
  public static void main(String[] args) {
	SpringApplication.run(MailServiceApplication.class,args);
  }

  @Autowired
  private JavaMailSender javaMailSender;

  @EventListener(ApplicationReadyEvent.class)
  public void triggerMail() {
	SimpleMailMessage msg = new SimpleMailMessage();
	msg.setFrom("${MAIL}");
	msg.setTo("onurbass.ob@gmail.com");
	msg.setSubject("Testing from Spring Boot");
	msg.setText("Hello World \n Spring Boot Email");
	javaMailSender.send(msg);

  }
}