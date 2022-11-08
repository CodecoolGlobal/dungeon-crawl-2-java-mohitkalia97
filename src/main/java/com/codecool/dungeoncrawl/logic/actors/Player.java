package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

public class Player extends Actor {
    public Player(Cell cell) {
        super(cell);
        this.health = 10;
        this.power = 5;
    }
    @Override
    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (nextCell.getActor() instanceof Skeleton) {
            System.out.println("HIT");
            attack(nextCell.getActor());
        }

        if (!nextCell.getTileName().equals(CellType.WALL.getTileName()) && nextCell.getActor() == null) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }

    public void attack(Actor enemy){
        enemy.health -= power;
        if (enemy.health > 0) {
            this.health -= enemy.power;
            System.out.println("It is still alive");
        } else {
            System.out.println("It is dead");
            enemy.getCell().setActor(null);

        }
    }

    public String getTileName() {
        return "player";
    }
}
