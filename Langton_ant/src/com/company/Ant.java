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
                    grid.setContent(x, y, 'v');
                }else{
                    facing = 'w';
                    grid.setContent(x, y, '^');
                }
                grid.changeColor(x, y);
                break;
            case 's':
                if(grid.getColor(x, y) == Color.BLACK){
                    facing = 'w';
                    grid.setContent(x, y, '^');
                }else{
                    facing = 'e';
                    grid.setContent(x, y, 'v');
                }
                grid.changeColor(x, y);
                break;
            case 'e':
                if(grid.getColor(x, y) == Color.BLACK){
                    facing = 's';
                    grid.setContent(x, y, '>');
                }else{
                    facing = 'n';
                    grid.setContent(x, y, '<');
                }
                grid.changeColor(x, y);
                break;
            case 'w':
                if(grid.getColor(x, y) == Color.BLACK){
                    facing = 'n';
                    grid.setContent(x, y, '<');
                }else{
                    facing = 's';
                    grid.setContent(x, y, '>');
                }
                grid.changeColor(x, y);

                break;
        }

        move();
        grid.refresh();
    }
    private void move(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        grid.setContent(x, y, ' ');
        System.out.println(x + ", " + y);
        switch(facing){
            case 'n':


                if(y-1 < 0){
                    y = grid.getGridSize() -1;
                }else{
                    y--;
                }
                grid.setContent(x, y, '^');

                break;
            case 's':


                if(y+1 >= grid.getGridSize()){
                    y = 0;
                }else{
                    y++;
                }

                grid.setContent(x, y, 'v');
                break;
            case 'e':


                if(x+1 >= grid.getGridSize()){
                    x = 0;
                }else{
                    x++;
                }
                grid.setContent(x, y, '<');

                break;
            case 'w':


                if(x-1 < 0){
                    x = grid.getGridSize() -1;
                }else{
                    x--;
                }
                grid.setContent(x, y, '>');

                break;
        }
    }

}
