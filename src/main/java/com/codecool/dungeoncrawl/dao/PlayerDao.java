package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.PlayerModel;

import java.util.List;

public interface PlayerDao {
    // commented out to show the rest of the program
/*    void add(Player player);
    void update(Player player);*/
    PlayerModel get(int id);
    List<PlayerModel> getAll();
}
