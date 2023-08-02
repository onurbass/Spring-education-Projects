package com.onurbas.annotation;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {


    public static void main(String[] args) {

        ClassPathXmlApplicationContext context=
                new ClassPathXmlApplicationContext("annotationContext.xml");

        IRunable game=context.getBean(IRunable.class);

        GameRunner gameRunner=context.getBean(GameRunner.class);

        gameRunner.run();


    }
}
