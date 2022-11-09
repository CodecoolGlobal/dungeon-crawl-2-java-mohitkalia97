package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.*;

import java.util.ArrayList;

public class Player extends Actor {

    public ArrayList<Item> inventory = new ArrayList<>();
    public boolean hasKey = false;

    public Player(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "player";
    }

    public void pickUpItem() {
        inventory.add(getCell().getItem());
        if (getCell().getItem() instanceof Key) {
            setHasKey(true);
        }
        else if (getCell().getItem() instanceof Weapon) {
            System.out.println("You picked up a weapon!");
        }

    }

}