package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Weapon extends Item {
    public Weapon(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "weapon";
    }
}
