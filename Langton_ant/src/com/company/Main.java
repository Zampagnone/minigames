package com.company;

public class Main {

    public static void main(String[] args) {
	    Grid grid = new Grid(10);

        grid.display();

        grid.changeColor(5, 5);
        grid.display();
    }
}
