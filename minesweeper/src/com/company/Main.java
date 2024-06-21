package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final int SIZE = 16;
    private static final int BOMBS = 25;
    private static final int[] DX = {-1, -1, -1, 0, 0, 1, 1, 1};
    private static final int[] DY = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        Cell[][] map = new Cell[SIZE][SIZE];
        initializeMap(map);
        placeBombs(rand, map);
        setNumbers(map);
        printMap(map);

        while (true) {
            System.out.print("Insert the x coordinate you want to uncover: ");
            int x = sc.nextInt();
            System.out.print("Insert the y coordinate you want to uncover: ");
            int y = sc.nextInt();

            if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) {
                System.out.println("Coordinates are out of bounds, try again.");
                continue;
            }

            if (map[x][y].getType().equals("Bomb")) {
                System.out.println("You hit a bomb! Game over.");
                break;
            } else {
                uncover(map, x, y);
                if (checkCompletion(map)) {
                    printMap(map);
                    System.out.println("You won!");
                    break;
                }
            }
            printMap(map);
        }
        sc.close();
    }

    private static void initializeMap(Cell[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = new Cell();
                map[i][j].setType("Empty");
                map[i][j].setCovered(true);
            }
        }
    }

    private static void printMap(Cell[][] map) {
        // Stampa l'intestazione delle colonne
        System.out.print("   "); // Spazi per l'allineamento della prima colonna
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < SIZE; i++) {
            // Stampa l'indice della riga all'inizio di essa
            System.out.print(i + "  ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j].display() + " ");
            }
            System.out.println(); // Vai a capo dopo aver stampato ogni riga
        }
    }

    private static void placeBombs(Random rand, Cell[][] map) {
        for (int i = 0; i < BOMBS; i++) {
            int x, y;
            do {
                x = rand.nextInt(SIZE);
                y = rand.nextInt(SIZE);
            } while (map[x][y].getType().equals("Bomb"));
            map[x][y].setType("Bomb");
        }
    }

    private static boolean checkCompletion(Cell[][] map) {
        for (Cell[] row : map) {
            for (Cell cell : row) {
                if (cell.isCovered() && !cell.getType().equals("Bomb")) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void setNumbers(Cell[][] map) {
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                if (!map[x][y].getType().equals("Bomb")) {
                    int bombs = countBombs(map, x, y);
                    if (bombs > 0) {
                        map[x][y].setType("Number");
                        map[x][y].setCloseBombs(bombs);
                    }
                }
            }
        }
    }

    private static int countBombs(Cell[][] map, int x, int y) {
        int count = 0;
        for (int i = 0; i < DX.length; i++) {
            int nx = x + DX[i];
            int ny = y + DY[i];
            if (nx >= 0 && nx < SIZE && ny >= 0 && ny < SIZE && map[nx][ny].getType().equals("Bomb")) {
                count++;
            }
        }
        return count;
    }

    private static void uncover(Cell[][] map, int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE || !map[x][y].isCovered()) {
            return;
        }

        map[x][y].setCovered(false);

        if (map[x][y].getType().equals("Empty")) {
            for (int i = 0; i < DX.length; i++) {
                uncover(map, x + DX[i], y + DY[i]);
            }
        }
    }
}