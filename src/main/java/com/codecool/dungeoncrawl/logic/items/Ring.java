package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Ring extends Item {
    public Ring(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "ring";
    }
}

