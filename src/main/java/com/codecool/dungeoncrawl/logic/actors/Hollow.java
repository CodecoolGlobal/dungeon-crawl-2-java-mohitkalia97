package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

public class Hollow extends Actor{
    public Hollow(Cell cell) {
        super(cell);
        this.health = 10;
        this.power = 3;
    }

    @Override
    public String getTileName() {
        return "hollow";
    }
}
