package com.codecool.dungeoncrawl.logic;

public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),
    WALL("wall"),
    INCLINELEFT("inclineLeft"),
    INCLINERIGHT("inclineRight"),
    DECLINELEFT("declineLeft"),
    DECLINERIGHT("declineRight"),
    BOTTOMWALL("bottomWall"),
    TOPWALL("topWall"),
    LEFTWALL("leftWall"),
    RIGHTWALL("rightWall"),
    RABBLE("rabble"),
    CRYSTAL("crystal");

    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
