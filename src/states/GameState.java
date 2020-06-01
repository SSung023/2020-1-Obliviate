package states;

import dev.game.Game;
import entities.creatures.Player;
import gfx.Assets;

import java.awt.*;

public class GameState extends State {

    private Player player;

    public GameState(Game game){
        super(game);
        player = new Player(100,100);
    }

    @Override
    public void tick() {
        player.tick();
    }

    @Override
    public void render(Graphics g) {
        player.render(g);
    }

}
