package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Grid extends JFrame implements KeyListener{
    private int size;
    private int i, j;

    private boolean spacePressed = false;

    JFrame frame = new JFrame();

    private JButton [][] grid;

    public Grid(int size) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.size = size;

        this.grid = new JButton[size][size];

        for(i = 0; i < size; i++){
            for(j = 0; j < size; j++){

                grid[i][j] = new JButton(" ");
                grid[i][j].setBackground(Color.white);
                grid[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(((JButton) e.getSource()).getBackground() == Color.BLACK){
                            ((JButton) e.getSource()).setBackground(Color.WHITE);
                            ((JButton) e.getSource()).setForeground(Color.BLACK);
                        }else{
                            ((JButton) e.getSource()).setBackground(Color.BLACK);
                            ((JButton) e.getSource()).setForeground(Color.WHITE);
                        }

                    }
                });

            }
        }
    }
    public void refresh(){
        frame.invalidate();
        frame.validate();
        frame.repaint();
    }
    public void changeColor(int x, int y){
        if(grid[x][y].getBackground() == Color.BLACK){
            grid[x][y].setBackground(Color.WHITE);
            grid[x][y].setForeground(Color.BLACK);
            grid[x][y].setOpaque(true);
        }else{
            grid[x][y].setBackground(Color.BLACK);
            grid[x][y].setForeground(Color.WHITE);
            grid[x][y].setOpaque(true);
        }

    }
    public int getGridSize(){
        return size;
    }
    public Color getColor(int x, int y){
        return grid[x][y].getBackground();
    }
    public void setContent(int x, int y, char content){
        grid[x][y].setText(content + "");
    }
    public void setTitle(String title){
        frame.setTitle(title);
    }
    public void display(){
        frame.setPreferredSize(new Dimension(size*44,size*44));
        frame.setLayout(new GridLayout(size, size));

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                frame.add(grid[i][j]);
            }
        }

        frame.setFocusable(true);
        frame.addKeyListener(this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Press space to start");
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            setSpacePressed(true);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            setSpacePressed(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            setSpacePressed(true);
        }
    }
    public void setSpacePressed(boolean spacePressed){
        this.spacePressed = spacePressed;
    }
    public boolean isSpacePressed(){
        return spacePressed;
    }

}
