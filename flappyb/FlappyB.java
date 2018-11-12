package flappyb;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;

import javax.swing.JFrame;

public class FlappyB implements ActionListener, KeyListener {

    public static FlappyB flappybird;

    public final int WIDTH = 800, HEIGHT = 800;

    private JFrame frame;

    

    public int tck, yMotion;
    public int score = 0;

    public PanelRenderer rend;

    public Rectangle bird;

    public ArrayList<Rectangle> columns;

    public Random rand;

    public boolean gameOver, started;

    public FlappyB() {
        rend = new PanelRenderer();
        rand = new Random();
        Timer t = new Timer(20, this);

        frame = new JFrame("Flappy Bird");
        frame.add(rend);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-400, dim.height/2-400);
        frame.setSize(WIDTH, HEIGHT);
        frame.setResizable(false);
        frame.addKeyListener(this);

        //frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
        frame.setVisible(true);

        bird = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 20, 20);

        columns = new ArrayList<Rectangle>();

        addColumn(true);
        addColumn(true);
        addColumn(true);
        addColumn(true);
        t.start();

    }
    
    

    public void addColumn(boolean start) {
        int space = 250;
        int width = 60;
        int height = 50 + rand.nextInt(300);

        if (start) {
            columns.add(new Rectangle(WIDTH + width + columns.size() * 150, HEIGHT - height - 120, width, height));
            columns.add(new Rectangle(WIDTH + width + (columns.size() - 1) * 150, 0, width, HEIGHT - height - space));

        } else {
            columns.add(new Rectangle(columns.get(columns.size() - 1).x + 300, HEIGHT - height - 120, width, height));
            columns.add(new Rectangle(columns.get(columns.size() ).x, 0, width, HEIGHT - height - space));

        }

    }

    public void jump() {
        if (gameOver) {

            bird = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 20, 20);
            columns.clear();
            yMotion = 0;
            score = 0;

            addColumn(true);
            addColumn(true);
            addColumn(true);
            addColumn(true);

            gameOver = false;
        }

        if (!started) {
            started = true;
        } else if (!gameOver) {
            if (yMotion > 0) {
                yMotion = 0;
            }
            {
                if (bird.y > 0 + bird.height) {
                    yMotion -= 11;
                } else {
                    yMotion = 0;
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (started) {
            if (gameOver) {

                tck++;
                if (tck % 2 == 0 && yMotion < 15) {
                    yMotion += 2;
                }

                bird.y += yMotion;

                if (bird.y + yMotion >= HEIGHT - 120) {
                    bird.y = HEIGHT - 120 - bird.height;
                }

            }
            if (!gameOver) {
                int speed = 6;
                for (int i = 0; i < columns.size(); i++) {
                    Rectangle column = columns.get(i);
                    column.x -= speed;
                }

                tck++;
                if (tck % 2 == 0 && yMotion < 15) {
                    yMotion += 2;
                }

                for (int i = 0; i < columns.size(); i++) {
                    Rectangle column = columns.get(i);
                    if (column.x + column.width < 0) {
                        columns.remove(column);
                        if (column.y == 0) {
                            addColumn(false);
                        }
                    }
                }

                bird.y += yMotion;

                for (Rectangle column : columns) {

                    if (column.y == 0 && bird.x + bird.width > column.x + column.width - 6 && bird.x + bird.width < column.x + column.width + 6) {
                        score++;

                    }
                    if (column.intersects(bird) || bird.y == HEIGHT - 120) {

                        gameOver = true;

                        if (bird.x <= column.x) {
                            bird.x = column.x - bird.width;
                        } else {
                            if (column.y != 0) {
                                bird.y = column.y - bird.height;
                            } else if (bird.y < column.height) {
                                bird.y = column.height;
                            }
                        }
//
                    }
                }

                if (bird.y > HEIGHT - 120) {
                    bird.y = HEIGHT - 120 - bird.height;
                    gameOver = true;
                }

                if (bird.y >= HEIGHT - HEIGHT) {

                }
            }

        }
        
 

        rend.repaint();
    }

    /* public void BirdMovement() {
        if (bird.y >= 0 && bird.y <= HEIGHT) {
            speed += acc;
            bird.y += speed;
        }
    }*/
    public void paintColumn(Graphics g, Rectangle column) {
        g.setColor(Color.green.darker());
        g.fillRect(column.x, column.y, column.width, column.height);
    }

    public void repaint(Graphics g) {

        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.darkGray);
        g.fillRect(0, HEIGHT - 120, WIDTH, 120);

        g.setColor(Color.green.darker());
        g.fillRect(0, HEIGHT - 120, WIDTH, 20);

        g.setColor(Color.cyan);
        g.fillRect(bird.x, bird.y, bird.width, bird.height);

        for (Rectangle column : columns) {
            paintColumn(g, column);
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", 1, 100));

        if (!started) {
            g.drawString("Click to start", 75, HEIGHT / 2 - 50);
        }

        if (gameOver) {
            g.drawString("GAME OVER", 85, HEIGHT / 2 - 50);
            g.setFont(new Font("Arial", 1, 70));
            g.drawString("Your score: " + String.valueOf(score), 120, HEIGHT / 2 + 50);
        }

        if (!gameOver && started) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", 1, 50));
            g.drawString("Score: " + String.valueOf(score), 60, 100);
        }

    }

    public static void main(String[] args) {
        flappybird = new FlappyB();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            jump();

        }
    }

}
