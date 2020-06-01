package dev.game;

import display.Display;

public class Lancher {

    public static void main(String[] args){
        Game game = new Game("title", 900,675);

        //Game class의 start method 실행
        //start -> thread.start -> run -> init -> tick & render
        game.start();
    }
}
