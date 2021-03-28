package net.gamedev.battleship.model;

import java.util.*;

public class Player {
    public static final String ATTR = "player";
    private final String name;
    private final Map<String, CellStatus> playerField = new HashMap<>();
    private final Map<String, CellStatus> opponentView = new HashMap<>();
    private final List<String> history = new ArrayList<>();
    private boolean playerFieldValid = false;

    public Player(String name) {
        this.name = name;
    }

    public void addHistory(Player player, String addr, boolean isHit) {
        String name;
        int number;
        if (player == this) {
            name = "me";
        } else {
            name = player.getName();
        }
        var result = isHit ? "hit" : "miss";
        number = history.size() + 1;
        var msg = String.format("%d. %s attacked %s: %s", number, name, addr, result);
        history.add(msg);
    }

    public List<String> getHistory() {
        return history;
    }

    public String getName() {
        return name;
    }

    public Map<String, CellStatus> getPlayerField() {
        return playerField;
    }

    public Map<String, CellStatus> getOpponentView() {
        return opponentView;
    }

    public boolean isPlayerFieldValid() {
        return playerFieldValid;
    }

    public void setShips(Set<String> addresses) {
        if (playerFieldValid) {
            throw new IllegalStateException("Field is set already");
        }
        playerField.clear();
        for (var addr : addresses) {
            playerField.put(addr, CellStatus.SHIP);     // запоминаем корабли
        }
        playerFieldValid = playerField.size() == 3;     // проверяем сколько полей отмечено
    }

    public void setPlayerFieldCell(String addr, CellStatus status) {
        playerField.put(addr, status);
    }

    public void setOpponentViewCell(String addr, CellStatus status) {
        opponentView.put(addr, status);
    }

    public CellStatus getPlayerFieldCell(String addr) {
        return playerField.getOrDefault(addr, CellStatus.EMPTY);    // возвращает статус ячейки, если нет - EMPTY
    }

    public boolean hasMoreShips() {
        return playerField.containsValue(CellStatus.SHIP);     // проверяет есть ли еще корабли, если да - возвращает TRUE
    }
}
