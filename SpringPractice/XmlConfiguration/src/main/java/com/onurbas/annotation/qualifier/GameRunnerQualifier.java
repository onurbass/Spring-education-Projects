package com.onurbas.annotation.qualifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class GameRunnerQualifier {

    @Autowired
    @Qualifier("contra")
    IRunableQualifier game;

    //const injection
//    public GameRunnerQualifier(@Qualifier("contra") IRunableQualifier game) {
//        this.game = game;
//    }

    public void run(){
        System.out.println("Oyun calısıyor==>"+game.getClass().getSimpleName());
        game.up();
        game.down();
        game.left();
        game.right();
    }

    public IRunableQualifier getGame() {
        return game;
    }

}
