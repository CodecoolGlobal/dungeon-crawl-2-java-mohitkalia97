package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.PlayerModel;

import java.util.List;

public interface PlayerDao {
    void add(Player player);
    void update(Player player);
    PlayerModel get(int id);
    List<PlayerModel> getAll();
}
