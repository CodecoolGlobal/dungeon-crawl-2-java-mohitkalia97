package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

import java.util.Random;

public class Skeleton extends Actor {
    public Skeleton(Cell cell) {
        super(cell);
        this.health = 5;
        this.power = 2;
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }

   /* public void move() {
        int dx = 0;
        int dy = 0;
        switch (getRandomNumberForMovement()) {
            case 1:
                dx = 0;
                dy =-1;
                break;
            case 2:
                dx =0;
                dy =1;
                break;
            case 3:
                dx =-1;
                dy =0;
                break;
            case 4:
                dx =1;
                dy =0;
                break;
        }
        Cell nextCell = getCell().getNeighbor(dx, dy);
        if(nextCell.getType()!= CellType.WALL && nextCell.getItem()==null && nextCell.getActor()==null ) {
            super.move(dx, dy);
        }
    }
    public int getRandomNumberForMovement(){
        Random r = new Random();
        int low = 1;
        int high = 5;
        return r.nextInt(high-low) + low;
    }*/
}
