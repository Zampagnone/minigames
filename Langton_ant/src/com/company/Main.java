package com.company;

public class Main {

    public static void main(String[] args) {
	    Ant ant = new Ant(4, 4, 'n', new Grid(10));

        while(true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ant.turn();
        }
    }
}
