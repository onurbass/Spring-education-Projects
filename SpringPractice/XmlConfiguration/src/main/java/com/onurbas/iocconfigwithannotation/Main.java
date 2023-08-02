package com.bilgeadam.iocconfigwithannotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {


    public static void main(String[] args) {

        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(IocConfig.class);




//       IRunable game=context.getBean("getMario", IRunable.class);
//        System.out.println(game);
//        IRunable game2=context.getBean(IRunable.class);
//        System.out.println(game2);
//
        GameRunner gameRunner=context.getBean(GameRunner.class);
//
       gameRunner.run();


    }
}
