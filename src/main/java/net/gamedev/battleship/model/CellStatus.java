package net.gamedev.battleship.model;

public enum CellStatus {
    EMPTY,
    SHIP, // стоит корабль
    HIT,  // попал
    MISS  // промазал
}
