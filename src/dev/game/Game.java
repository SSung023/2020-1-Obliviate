package dev.game;

import display.Display;
import gfx.Assets;
import gfx.ImageLoader;
import gfx.SpriteSheet;
import states.GameState;
import states.MenuState;
import states.State;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

//Game class will be the ****MAIN**** part of our game
//it will hold all of the base code(start, run, close...)
public class Game implements Runnable{

    private Display display;
    public int width, height;
    public String title;

    private boolean running = false;
    private Thread thread;

    private BufferStrategy bs;
    private Graphics g;

    //STATES - to initialize all states
    private State gameState;
    private State menuState;


    public Game(String title, int width, int height){
        //public int width, height에 해당
        this.width = width;
        this.height = height;
        this.title = title;

    }

    //나중에 image나 display같은 전반적인 것들을 init
    private void init(){
        display = new Display(title, width, height);
        //image를 담고 있는 class 안 -> init method 실행
        Assets.init();

        //polymorphism 사용, init에서 setState를 함으로써 첫 시작 state 설정
        //passing 'game' object
        gameState = new GameState(this);
        menuState = new MenuState(this);
        State.setState(gameState);
    }

    //Runnable implements하면 run 함수를 넣어야함
    public void run(){

        init();

        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        //boolean 변수, running이 true인 동안 while문 실행
        while(running){

            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if(delta >= 1){
                tick();
                render();
                ticks++;
                delta--;
            }

            if(timer >= 1000000000){
                System.out.println("Ticks and Frames : " + ticks);
                ticks = 0;
                timer = 0;
            }

        }
        stop();
    }


    //update & render(draw things) everything in our game
    private void tick(){

        //현재 state가 정해져있다면
        if(State.getState() != null){
            //해당 state의 tick method 실행
            State.getState().tick();
        }
    }
    private void render(){
        //to render things, we have to access to the 'canvas' in Display Class
        //this will set our 'bs' equals to whatever the current buffer strategy is of our game(canvas)
        //buffers strategy is a way for the computer to draw things to the screen
        bs = display.getCanvas().getBufferStrategy();
        //아직 bs가 만들어지지 않았다면 새로 create
        if(bs == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        //it's like paint brush, it can draw full images, line... etc
        g = bs.getDrawGraphics();
        //CLEAR SCREEN
        g.clearRect(0,0, width, height);
        //*****DRAW HERE!!

        //현재 state가 정해져있다면
        if(State.getState() != null){
            //해당 state의 render method 실행
            State.getState().render(g);
        }

        //*****END DRAWING!!!

        bs.show();
        g.dispose();
    }



    //only use that whenever you're working with threads directly
    public synchronized void start(){
        if(running) //게임이 이미 running이라면 return
            return;
        running = true;
        thread = new Thread(this); //Game class를 실행시킬 것 이기 때문
        thread.start(); //start method는 run method를 call한다
    }
    public synchronized  void stop(){
        if(!running)
            return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
