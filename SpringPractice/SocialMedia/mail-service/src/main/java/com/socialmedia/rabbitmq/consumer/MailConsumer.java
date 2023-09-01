package com.socialmedia.rabbitmq.consumer;

import com.socialmedia.rabbitmq.model.MailModel;
import com.socialmedia.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
@Slf4j //loglama kütüphanesi..
public class MailConsumer {
  private final MailService mailService;

  @RabbitListener(queues = ("${rabbitmq.mail-queue}"))
  public void sendMail(MailModel model){
	log.info("model => {}",model);
	mailService.sendMail(model);

  }
}


