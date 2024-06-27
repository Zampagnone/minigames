package com.company;

public class Main {

    public static void main(String[] args) {
	    Ant ant = new Ant(10, 10, 'n', new Grid(20));

        while(!ant.isSpacePressed()){}

        ant.setTitle("Langton's ant");

        while(true){
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ant.turn();
        }
    }

}
