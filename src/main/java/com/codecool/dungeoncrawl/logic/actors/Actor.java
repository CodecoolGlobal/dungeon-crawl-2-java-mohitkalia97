package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class Actor implements Drawable {
    protected Cell cell;
    protected int health;
    protected int power;
    private boolean hasKey = false;


    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {               //Kollisionsabfrage
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (!nextCell.getTileName().equals(CellType.WALL.getTileName())
            && !nextCell.getTileName().equals(CellType.RABBLE.getTileName())
            && !nextCell.getTileName().equals(CellType.TREE.getTileName())
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
            && nextCell.getActor() == null) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }

    public int getHealth() {
        return health;
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

    public void setHasKey(boolean hasKey) {
        this.hasKey = hasKey;
    }

    public boolean hasKey() {
        return hasKey;
    }
}
