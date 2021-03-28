package net.gamedev.battleship.model;

public class Game {
    public static final String ATTR = "game";
    private final Player player1;
    private Player player2;
    private GameStatus status = GameStatus.INCOMPLETE;
    private boolean player1turn = true;

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

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player opponentOf(Player player) {
        if (player == player1) {
            return player2;
        } else {
            return player1;
        }
    }

    public GameStatus getStatus() {
        return status;
    }

    public void checkGameStart() {
        if (player1.isPlayerFieldValid() && player2.isPlayerFieldValid()) {
            status = GameStatus.IN_PROGRESS;
        }
    }

    public Player getCurrentPlayer() {
        return player1turn ? player1 : player2;     // тернарный оператор (вместо if-else)
    }

    public Player getAwaitPlayer() {
        if (player1turn) {
            return player2;
        } else {
            return player1;
        }
    }

    public void fire(String addr) {
        var cur = getCurrentPlayer();
        var await = getAwaitPlayer();
        var attackCellStatus = await.getPlayerFieldCell(addr);
        boolean isHit = false;
        switch (attackCellStatus) {
            case SHIP:
                cur.setOpponentViewCell(addr, CellStatus.HIT);
                await.setPlayerFieldCell(addr, CellStatus.HIT);
                if (!await.hasMoreShips()) {
                    status = GameStatus.FINISHED;
                }
                isHit = true;
                break;
            case EMPTY:
                cur.setOpponentViewCell(addr, CellStatus.MISS);
                await.setPlayerFieldCell(addr, CellStatus.MISS);
                player1turn = !player1turn;     // меняем игрока
                break;
            case HIT:
            case MISS:
                player1turn = !player1turn;
                break;
        }
        player1.addHistory(cur, addr, isHit);
        player2.addHistory(cur, addr, isHit);
    }
}
