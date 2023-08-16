package com.bilgeadam.rabbitmq.consumer;

import com.bilgeadam.rabbitmq.model.SkorModel;
import com.bilgeadam.service.SkorService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SkorConsumer {

    private final SkorService skorService;
    @RabbitListener(queues = "skor-queue")
    public void updateSkor(SkorModel model){
    skorService.saveOrUpdateSkor(model);
    }
}
