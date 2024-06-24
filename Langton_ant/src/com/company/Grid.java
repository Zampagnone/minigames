package com.company;

import javax.swing.*;

public class Grid extends JFrame{
    private int size = 10;

    private JButton [][] grid;

    public Grid(int size) {
        this.size = size;

        JFrame frame = new JFrame();

        this.grid = new JButton[size][size];

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                grid[i][j] = new JButton(String.valueOf((char)(i+41)) + (j));
            }
        }
    }
}
