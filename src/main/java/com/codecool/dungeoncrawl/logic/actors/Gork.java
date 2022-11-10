package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Gork extends Actor {
    public Gork(Cell cell) {super(cell);}

    @Override
    public String getTileName() {
        return "gork";
    }
}
