package com.onurbas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MailServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MailServiceApplication.class);
    }

    // deneme amaçlı kod

//    @Autowired
//    private  MailSenderService mailSenderService;
//
//    @EventListener(ApplicationReadyEvent.class)
//    public void sendMail(){
//        mailSenderService.sendMail("123");
//    }


}