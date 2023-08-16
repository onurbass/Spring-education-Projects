package com.bilgeadam.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

private String exchange="tahmin-exchange";
private String queuSkor="skor-queue";
private String skorBindingKey="skor-key";


@Bean
    DirectExchange exchangeTahmin(){
    return new DirectExchange(exchange);
}

@Bean
Queue skorQueue(){
    return new Queue(queuSkor);
}

@Bean
public Binding bindingSkor( final Queue skorQueue,final DirectExchange exchangeTahmin ){
    return BindingBuilder.bind(skorQueue).to(exchangeTahmin).with(skorBindingKey);
}

}
