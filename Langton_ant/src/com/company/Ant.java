package com.company;

import java.awt.*;

public class Ant {
    private int x;
    private int y;
    private char facing;

    private boolean spacePressed = false;

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
                if(grid.getColor(x, y) == Color.WHITE){
                    facing = 'e';
                    grid.setContent(x, y, '>');
                }else{
                    facing = 'w';
                    grid.setContent(x, y, '<');
                }
                grid.changeColor(x, y);
                break;
            case 's':
                if(grid.getColor(x, y) == Color.WHITE){
                    facing = 'w';
                    grid.setContent(x, y, '<');
                }else{
                    facing = 'e';
                    grid.setContent(x, y, '>');
                }
                grid.changeColor(x, y);
                break;
            case 'e':
                if(grid.getColor(x, y) == Color.WHITE){
                    facing = 's';
                    grid.setContent(x, y, 'v');
                }else{
                    facing = 'n';
                    grid.setContent(x, y, '^');
                }
                grid.changeColor(x, y);
                break;
            case 'w':
                if(grid.getColor(x, y) == Color.WHITE){
                    facing = 'n';
                    grid.setContent(x, y, '^');
                }else{
                    facing = 's';
                    grid.setContent(x, y, 'v');
                }
                grid.changeColor(x, y);

                break;
        }

        move();
        grid.refresh();
    }
    public void setTitle(String title){
        grid.setTitle(title);
    }
    private void move(){
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        grid.setContent(x, y, ' ');
        switch(facing){
            case 'w':


                if(y-1 < 0){
                    y = grid.getGridSize() -1;
                }else{
                    y--;
                }
                grid.setContent(x, y, '<');

                break;
            case 'e':


                if(y+1 >= grid.getGridSize()){
                    y = 0;
                }else{
                    y++;
                }

                grid.setContent(x, y, '>');
                break;
            case 's':


                if(x+1 >= grid.getGridSize()){
                    x = 0;
                }else{
                    x++;
                }
                grid.setContent(x, y, 'v');

                break;
            case 'n':


                if(x-1 < 0){
                    x = grid.getGridSize() -1;
                }else{
                    x--;
                }
                grid.setContent(x, y, '^');

                break;
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isSpacePressed(){
        return grid.isSpacePressed();
    }



}
