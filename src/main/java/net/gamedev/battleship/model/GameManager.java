package net.gamedev.battleship.model;

import java.util.concurrent.BlockingQueue;

public class GameManager {
    public static final String ATTR = "gameManager";
    private Game pending;

    public synchronized Game join(Player player) {   // первый создает игру, второй присоединяется
        Game result;
        if (pending == null) {
            pending = new Game(player);
            result = pending;
        } else {
            result = pending;
            pending = null;
            result.start(player);
        }
        return result;
    }

    public synchronized void surrender(Game game, Player player) {
        if (game.getStatus() == GameStatus.FINISHED) {
            return;
        }
        game.surrender(player);
    }
}
