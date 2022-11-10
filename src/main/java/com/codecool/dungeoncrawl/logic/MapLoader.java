package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.*;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {
    public static GameMap loadMap() {
        InputStream is = MapLoader.class.getResourceAsStream("/map.txt");
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 's':
                            cell.setType(CellType.FLOOR);
                            new Skeleton(cell);
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell));
                        case '!':
                            cell.setType(CellType.FLOOR);
                            new Exclamation(cell);
                            break;
                        case 'g':
                            cell.setType(CellType.FLOOR);
                            new Gork(cell);
                            break;
                        case 'm':
                            cell.setType(CellType.FLOOR);
                            new Mork(cell);
                            break;
                        case '1':
                            cell.setType(CellType.BOTTOMLEFT);
                            break;
                        case '3':
                            cell.setType(CellType.BOTTOMRIGHT);
                            break;
                        case '2':
                            cell.setType(CellType.BOTTOMWALL);
                            break;
                        case '8':
                            cell.setType(CellType.TOPWALL);
                            break;
                        case '4':
                            cell.setType(CellType.LEFTWALL);
                            break;
                        case '6':
                            cell.setType(CellType.RIGHTWALL);
                            break;
                        case '7':
                            cell.setType(CellType.TOPLEFT);
                            break;
                        case '9':
                            cell.setType(CellType.TOPRIGHT);
                            break;
                        case 'R':
                            cell.setType(CellType.RABBLE);
                            break;
                        case 'C':
                            cell.setType(CellType.SAPPHIRE);
                            break;
                        case 'F':
                            cell.setType(CellType.FLAME);
                            break;
                        case 'G':
                            cell.setType(CellType.GRASS);
                            break;
                        case 'T':
                            cell.setType(CellType.TREE);
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

}
