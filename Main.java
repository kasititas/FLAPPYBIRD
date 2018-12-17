package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private final JFrame frame;



    private Main(){
        frame= new JFrame("Flappy Bird");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(GamePanel.getWIDTH(), GamePanel.getHEIGHT());
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setVisible(true);

    }


    public static void main(String[] args) {

        Main main=new Main();
        main.rendering();


    }

    private void rendering()
    {
        GamePanel gp= new GamePanel();
        Timer timer = new Timer(15, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gp.repaint();
                gp.move();


            }
        });
        frame.add(gp);
        frame.setVisible(true);
        timer.start();
    }
}
