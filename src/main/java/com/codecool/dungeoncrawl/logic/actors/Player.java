package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.items.*;

import java.util.ArrayList;
import java.util.StringJoiner;




public class Player extends Actor {

    public ArrayList<Item> inventory = new ArrayList<>();
    public boolean hasKey = false;
    private int id;
    private String name;

    public Player(Cell cell) {
        super(cell);
        this.health = 10;
        this.power = 5;
    }
    @Override
    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (nextCell.getActor() instanceof Skeleton ||
            nextCell.getActor() instanceof Hollow ||
            nextCell.getActor() instanceof Golem) {
            /*Next line is a quick check. Delete before final commit*/
            System.out.println("HIT");
            attack(nextCell.getActor());
        }

        if (!nextCell.getTileName().equals(CellType.WALL.getTileName())
                && !nextCell.getTileName().equals(CellType.TOPWALL.getTileName())
                && !nextCell.getTileName().equals(CellType.BOTTOMWALL.getTileName())
                && !nextCell.getTileName().equals(CellType.LEFTWALL.getTileName())
                && !nextCell.getTileName().equals(CellType.RIGHTWALL.getTileName())
                && !nextCell.getTileName().equals(CellType.TOPLEFT.getTileName())
                && !nextCell.getTileName().equals(CellType.TOPRIGHT.getTileName())
                && !nextCell.getTileName().equals(CellType.BOTTOMLEFT.getTileName())
                && !nextCell.getTileName().equals(CellType.BOTTOMRIGHT.getTileName())
                && !nextCell.getTileName().equals(CellType.FLAME.getTileName())
                && !nextCell.getTileName().equals(CellType.CRYSTAL.getTileName())
                && !nextCell.getTileName().equals(CellType.TREE.getTileName())
                && nextCell.getActor() == null) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }

    public void attack(Actor enemy){
        enemy.health -= power;
        if (enemy.health > 0) {
            this.health -= enemy.power;
            /*Next line is a quick check. Delete before final commit*/
            System.out.println("It is still alive");
        } else {
            /*Next line is a quick check. Delete before final commit*/
            System.out.println("It is dead");
            enemy.getCell().setActor(null);

        }
    }

    public String getTileName() {
        return "player";
    }

    public void pickUpItem() {
        inventory.add(getCell().getItem());
        if (getCell().getItem() instanceof Key) {
            System.out.println("You picked up a key!");
            setHasKey(true);
            cell.setItem(null);
        }
        else if (getCell().getItem() instanceof Weapon) {
            System.out.println("You picked up a weapon!");
            power += 5;
            cell.setItem(null);
            System.out.println(this.power);
        }
        else if (getCell().getItem() instanceof Coin) {
            System.out.println("You picked up a Coin!");
            /*treasurecount += 1;*/
            cell.setItem(null);
            /*cell.setItem();*/
        }
        else if (getCell().getItem() instanceof Crown) {
            System.out.println("You picked up a Crown!");
            /*treasurecount += 1;*/
            cell.setItem(null);
            cell.setType(CellType.GRASS);
        }
        else if (getCell().getItem() instanceof Ring) {
            System.out.println("You picked up a Ring!");
            /*treasurecount += 1;*/
            cell.setItem(null);
            cell.setType(CellType.EMPTY);
        }


    }

    public String inventoryToString() {
        StringJoiner sj = new StringJoiner("\n");
        for (Item item : inventory) {
            if (item != null) sj.add(item.getTileName());
        }
        return sj.toString();
    }

    public int getId(){
        return id;
    }

    @Override
    public Cell getCell() {
        var currentPosOfPlayer = super.getCell();
        return currentPosOfPlayer;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }
}