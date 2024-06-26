package com.company;

import java.awt.*;

public class Ant {
    private int x;
    private int y;
    private char facing;

    private Grid grid;

    public Ant(int x, int y, char facing, Grid grid){
        this.x = x;
        this.y = y;
        this.facing = facing;
        this.grid = grid;
        grid.display();
    }

    public void turn(){
        switch(facing){
            case 'n':
                if(grid.getColor(x, y) == Color.BLACK){
                    facing = 'e';
                }else{
                    facing = 'w';
                }
                grid.changeColor(x, y);
                break;
            case 's':
                if(grid.getColor(x, y) == Color.BLACK){
                    facing = 'w';
                }else{
                    facing = 'e';
                }
                break;
            case 'e':
                if(grid.getColor(x, y) == Color.BLACK){
                    facing = 's';
                }else{
                    facing = 'n';
                }
                break;
            case 'w':
                if(grid.getColor(x, y) == Color.BLACK){
                    facing = 'n';
                }else{
                    facing = 's';
                }
                break;
        }
        move();
        grid.refresh();
    }
    private void move(){
        grid.changeColor(x, y);
        grid.setContent(x, y, ' ');
        switch(facing){
            case 'n':
                x--;
                grid.setContent(x, y, '^');
                break;
            case 's':
                x++;
                grid.setContent(x, y, 'v');
                break;
            case 'e':
                y++;
                grid.setContent(x, y, '>');
                break;
            case 'w':
                y--;
                grid.setContent(x, y, '<');
                break;
        }
    }

}
