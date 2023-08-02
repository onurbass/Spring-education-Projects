package com.bilgeadam.iocconfigwithannotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameRunner {
    @Autowired
    IRunable game;

//const inj
//    public GameRunner(IRunable game) {
//
//        this.game = game;
//    }

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
