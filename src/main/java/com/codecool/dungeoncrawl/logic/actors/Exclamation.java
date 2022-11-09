package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Exclamation extends Actor {
    public Exclamation(Cell cell) {super(cell);}

    @Override
    public String getTileName() {
        return "exclamation";
    }
}
