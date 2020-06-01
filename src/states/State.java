package states;

import dev.game.Game;

import java.awt.*;

public abstract class State {

    //what state we currently want to tick and render in our game
    private static State currentState = null;
    protected Game game;

    public State(Game game){
        this.game = game;
    }

    //CLASS
    public abstract void tick();
    public abstract  void render(Graphics g);

    //GETTER & SETTER
    public static void setState(State state){
        currentState = state;
    }
    public static State getState(){
        return currentState;
    }

}