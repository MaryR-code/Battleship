package net.gamedev.battleship.model;

import java.util.concurrent.BlockingQueue;

public class GameManager {
    public static final String ATTR = "gameManager";
    private Game pending;

    public synchronized Game join(Player player) throws IllegalAccessException {   // первый создает игру, второй присоединяется - опять добавился Exception
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
}
