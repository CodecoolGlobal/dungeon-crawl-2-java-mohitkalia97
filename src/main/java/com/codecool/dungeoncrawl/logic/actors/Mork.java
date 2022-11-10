package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Mork extends Actor {
    public Mork(Cell cell) {super(cell);}

    @Override
    public String getTileName() {
        return "mork";
    }
}
