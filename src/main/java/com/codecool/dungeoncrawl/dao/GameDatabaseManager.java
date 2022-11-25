package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.PlayerModel;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class GameDatabaseManager {
    DataSource dataSource;
    private PlayerDao playerDao;
/*    private GameStateDaoJdbc gameStateDaoJdbc = new GameStateDaoJdbc(Da);*/


    public void setup() throws SQLException {
        dataSource = connect();
        playerDao = new PlayerDaoJdbc(dataSource);
    }

    public void saveGameState(GameState gameState) {
        GameStateDaoJdbc gameStateDaoJdbc = new GameStateDaoJdbc(dataSource);
        gameStateDaoJdbc.add(gameState);
    }
    //public GameStateDaoJdbc getGameStateDaoJdbc() {


    public PlayerDao getPlayerDao() {
        return playerDao;
    }

    private DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        String dbName = "dungeondb";
        String user = "tim";
        String password = "staytrue";

        dataSource.setDatabaseName(dbName);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        System.out.println("Trying to connect");
        dataSource.getConnection().close();
        System.out.println("Connection ok.");

        return dataSource;
    }
}
