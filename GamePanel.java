package com.company;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GamePanel extends JPanel {
    private static final int WIDTH=600;
    private static final int HEIGHT=800;
    private Bird bird=new Bird();
    private Tube tube= new Tube(GamePanel.WIDTH);
    private Tube tube2=new Tube(GamePanel.WIDTH+(GamePanel.WIDTH/2));
    private GameLogic gameLogic=new GameLogic(bird, tube, tube2);

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public GamePanel(){

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                gameLogic.goUp();
            }
        });

    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, HEIGHT-120, WIDTH, 120 );

        g.setColor(Color.green.darker());
        g.fillRect(0, HEIGHT - 120, WIDTH, 20);

        g.setColor(Color.cyan);
        g.fillRect(bird.getBirdX(), bird.getBirdY(), bird.getWidth(), bird.getHeight());

        g.setColor(Color.BLACK);
        g.fillRect(tube.getTubeX(),(tube.getTubeY()-tube.getGap()), tube.getTubeWidth(), tube.getTubeHeight());

        g.setColor(Color.BLACK);
        g.fillRect(tube2.getTubeX(),(tube2.getTubeY()-tube2.getGap()), tube2.getTubeWidth(), tube2.getTubeHeight());;

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", 1, 50));
        g.drawString("Score: " + gameLogic.getScore(), 60, 100);



    }
    public void move()
    {
        gameLogic.movement();
        gameLogic.tubeMovement();
        gameLogic.tubeMovement2();
        gameLogic.count();




    }
}
