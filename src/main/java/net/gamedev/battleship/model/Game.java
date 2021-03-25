package net.gamedev.battleship.model;

public class Game {
    public static final String ATTR = "game";
    private Player player1;
    private Player player2;
    private GameStatus status = GameStatus.INCOMPLETE;

    public Game(Player player1) {
        this.player1 = player1;
    }

    public void start(Player player2) {   // Exception
        if (this.status != GameStatus.INCOMPLETE) {
            throw new IllegalStateException("Status: "+this.status);
        }
        this.player2 = player2;
        this.status = GameStatus.SETTING_UP;
    }

    public Player opponentOf(Player player) {
        if (player == player1) {
            return player2;
        } else {
            return player1;
        }
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
