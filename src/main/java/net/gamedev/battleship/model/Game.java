package net.gamedev.battleship.model;

public class Game {
    public static final String ATTR = "game";
    private Player player1;
    private Player player2;
    private GameStatus status = GameStatus.INCOMPLETE;

    public Game(Player player1) {
        this.player1 = player1;
    }

    public void start(Player player2) throws IllegalAccessException {   // добавился Exception
        if (this.status != GameStatus.INCOMPLETE) {
            throw new IllegalAccessException("Status: "+this.status);
        }
        this.player2 = player2;
        this.status = GameStatus.SETTING_UP;

    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public GameStatus getStatus() {
        return status;
    }
}
