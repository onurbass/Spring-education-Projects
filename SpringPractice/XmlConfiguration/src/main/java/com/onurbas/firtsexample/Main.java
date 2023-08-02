package com.bilgeadam.firtsexample;

public class Main {


    public static void main(String[] args) {
        GameRunner gameRunner=new GameRunner(new Pacman());
        gameRunner.run();
    }
}
