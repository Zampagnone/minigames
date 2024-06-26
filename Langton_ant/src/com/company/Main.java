package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    Ant ant = new Ant(10, 10, 'n', new Grid(20));
        Scanner sc = new Scanner(System.in);

        String tmp = "";

        do{
            System.out.printf("\ntype start to make the ant start wandering: ");
            tmp = sc.nextLine();
        }while(!tmp.equals("start"));

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
