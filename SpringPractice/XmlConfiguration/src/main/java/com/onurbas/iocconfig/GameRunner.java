package com.onurbas.iocconfig;

import org.springframework.stereotype.Component;


public class GameRunner {

    IRunable game;

    public GameRunner(IRunable game) {

        this.game = game;
    }

    public void run(){
        System.out.println("Oyun calısıyor==>"+game.getClass().getSimpleName());
        game.up();
        game.down();
        game.left();
        game.right();
    }

    public IRunable getGame() {
        return game;
    }

}
