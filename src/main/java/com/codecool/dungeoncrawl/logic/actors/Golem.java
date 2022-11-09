package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Golem extends Actor{
    public Golem(Cell cell) {
        super(cell);
        this.health = 15;
        this.power = 4;
    }

    @Override
    public String getTileName() {
        return "golem";
    }
}
