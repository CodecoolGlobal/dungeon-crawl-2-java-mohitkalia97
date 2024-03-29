package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.Player;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.sql.SQLException;


public class Main extends Application {
    int playerCoordinates = 19;
    String currentMap = "/map.txt";
    GameMap map = MapLoader.loadMap(currentMap);
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    Label inventoryLabel = new Label();
    Label strengthLabel = new Label();
    Label exportLabel = new Label();
    private final Button pickUpButton = new Button("Pick up the item!");
    private final Button exportButton = new Button("Export the gamestate");

    GameDatabaseManager dbManager;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));

        ui.add(new Label("Health: "), 0, 0);
        ui.add(healthLabel, 1, 0);
        ui.add(new Label("Inventory: "), 0, 2);
        ui.add(inventoryLabel, 1, 2);
        ui.add(pickUpButton, 0, 50);
        ui.add(new Label("Strength"), 0, 1);
        ui.add(strengthLabel, 1, 1);
        ui.add(exportButton, 0, 51);


        pickUpButton.setFocusTraversable(false);
        exportButton.setFocusTraversable(false);


        pickUpButton.setOnAction(actionEvent ->  {
            Cell cell = map.getPlayer().getCell();
            if (cell.getItem() != null && cell.getActor() instanceof Player player) {
                //map.getPlayer().pickUpItem();
                player.pickUpItem();
                refresh();
            }
        });

        //exportButton.setOnAction(actionEvent -> player.exportGameState());


        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);

        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
    }

    private void onKeyReleased(KeyEvent keyEvent) {
        KeyCombination exitCombinationMac = new KeyCodeCombination(KeyCode.W, KeyCombination.SHORTCUT_DOWN);
        KeyCombination exitCombinationWin = new KeyCodeCombination(KeyCode.F4, KeyCombination.ALT_DOWN);
        if (exitCombinationMac.match(keyEvent)
                || exitCombinationWin.match(keyEvent)
                || keyEvent.getCode() == KeyCode.ESCAPE) {
            exit();
        }
    }

    private void onKeyPressed(KeyEvent keyEvent) {
    /*    Random random = new Random();
        int someInt = random.nextInt(3) - 1;*/
        switch (keyEvent.getCode()) {
            case UP:
                map.getPlayer().move(0, -1);
                changeLevel(map.getPlayer());
                refresh();
                break;
            case DOWN:
                map.getPlayer().move(0, 1);
                changeLevel(map.getPlayer());
                refresh();
                break;
            case LEFT:
                map.getPlayer().move(-1, 0);
                changeLevel(map.getPlayer());
                refresh();
                break;
            case RIGHT:
                map.getPlayer().move(1,0);
                changeLevel(map.getPlayer());
                refresh();
                break;
            case S:
                Player player = map.getPlayer();
                dbManager.savePlayer(player);
                break;
        }
        /*map.getPlayer().move(someInt,someInt);*/
    }

    private void changeLevel(Player player) {
        if(player.getX() == playerCoordinates && player.getY() == playerCoordinates) {
            if(currentMap.equals("/map.txt")){
                setupNewMap("/map1.txt");
            }
        }
    }
    private void setupNewMap(String newMap){
        MapLoader.loadMap(newMap);
        Canvas canvas = new Canvas(
                Math.min(map.getWidth(), 30) * Tiles.TILE_WIDTH,
                Math.min(map.getHeight(), 22) * Tiles.TILE_WIDTH);
        //GraphicsContext context = canvas.getGraphicsContext2D();
        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));

        currentMap = newMap;
        map = MapLoader.loadMap(currentMap);
        refresh();

    }
    private void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        int centerX = (int) (canvas.getWidth() / (Tiles.TILE_WIDTH * 2));
        int centerY = (int) (canvas.getHeight() / (Tiles.TILE_WIDTH * 2)) - 1;
        int[] focus = new int[2];
        if (map.getPlayer().getX() > centerX) {
            focus[0] = map.getPlayer().getX() - centerX;
        }
        if (map.getPlayer().getY() > centerY) {
            focus[1] = map.getPlayer().getY() - centerY;
        }
        for (int x = 0; x + focus[0] < map.getWidth(); x++) {
            for (int y = 0; y + focus[1] < map.getHeight(); y++) {
                Cell cell = map.getCell(x + focus[0], y + focus[1]);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y);
                } else if (cell.getItem() != null) {
                    Tiles.drawTile(context, cell.getItem(), x, y);
                } else {
                    Tiles.drawTile(context, cell, x, y);
                }
            }
        }
        healthLabel.setText("" + map.getPlayer().getHealth());
        inventoryLabel.setText("" + map.getPlayer().inventoryToString());
        strengthLabel.setText("" + map.getPlayer().getStrength());
    }

    private void setupDbManager() {
        dbManager = new GameDatabaseManager();
        try {
            dbManager.setup();
        } catch (SQLException ex) {
            System.out.println("Cannot connect to database.");
        }
    }

    private void exit() {
        try {
            stop();
        } catch (Exception e) {
            System.exit(1);
        }
        System.exit(0);
    }
}

