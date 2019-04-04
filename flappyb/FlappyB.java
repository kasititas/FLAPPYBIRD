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

    static FlappyB flappybird;

    private final int WIDTH = 800, HEIGHT =600 ;

    protected JFrame frame;

    private int ticks, yMovement;
    private int score = 0;
    private PanelRenderer panelRenderer;



    private Rectangle bird;

    private ArrayList<Rectangle> tubes;

    private Random random;

    private boolean gameOver, started;

    private FlappyB() {
        panelRenderer = new PanelRenderer();
        random = new Random();
        Timer t = new Timer(15, this);

        frame = new JFrame("Flappy Bird");
        frame.add(panelRenderer);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        frame.setLocation(dim.width / 2 - 400, dim.height / 2 - 400);


        frame.setSize(WIDTH, HEIGHT);
        frame.setResizable(false);
        frame.addKeyListener(this);

        frame.setVisible(true);

        bird = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 20, 20);

        tubes = new ArrayList<Rectangle>();

        addTubes(true);
        addTubes(true);
        addTubes(true);
        addTubes(true);

        t.start();

    }

    private void addTubes(boolean start) {
        int space = 250;
        int width = 60;
        int height = 50 + random.nextInt(300);

        if (start) {
            tubes.add(new Rectangle(WIDTH + width + tubes.size() * 150, HEIGHT - height - 120, width, height));
            tubes.add(new Rectangle(WIDTH + width + (tubes.size() - 1) * 150, 0, width, HEIGHT - height - space));

        } else {
            tubes.add(new Rectangle(tubes.get(tubes.size() - 1).x + 300, HEIGHT - height - 120, width, height));
            tubes.add(new Rectangle(tubes.get(tubes.size() - 1).x, 0, width, HEIGHT - height - space));

        }

    }

    private void jump() {
        if (gameOver) {

            bird = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 20, 20);
            tubes.clear();
            yMovement = 0;
            score = 0;

            addTubes(true);
            addTubes(true);
            addTubes(true);
            addTubes(true);

            gameOver = false;
        }

        if (!started) {
            started = true;
        } else if (!false) {
            if (yMovement > 0) {
                yMovement = 0;
            }

            if (bird.y > bird.height) {
                yMovement -=11;
            } else {
                yMovement = 0;
            }

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (started) {
            if (gameOver) {

                ticks++;
                if (ticks % 2 == 0 && yMovement < 15) {
                    yMovement += 2;
                }

                bird.y += yMovement;

                if (bird.y + yMovement >= HEIGHT - 120) {
                    bird.y = HEIGHT - 120 - bird.height;
                }

            }
            if (!gameOver) {
                int speed = 5;
                for (Rectangle tube : tubes) {
                    tube.x -= speed;
                }

                ticks++;

                if (ticks % 2 == 0 && yMovement < 15) {
                    yMovement += 2;
                }

                for (int i = 0; i < tubes.size(); i++) {
                    Rectangle tube = tubes.get(i);
                    if (tube.x + tube.width < 0) {
                        tubes.remove(tube);
                        if (tube.y == 0) {
                            addTubes(false);
                        }
                    }
                }

                bird.y += yMovement;

                for (Rectangle tube : tubes) {

                    if (tube.y == 0 && bird.x + bird.width > tube.x + tube.width - 5 && bird.x + bird.width < tube.x + tube.width + 5) {
                        score++;

                    }
                    if (tube.intersects(bird) || bird.y == HEIGHT - 120) {

                        gameOver = true;

                        if (bird.x <= tube.x) {
                            bird.x = tube.x - bird.width;
                        } else {
                            if (tube.y != 0) {
                                bird.y = tube.y - bird.height;
                            } else if (bird.y < tube.height) {
                                bird.y = tube.height;
                            }
                        }

                    }
                }

                if (bird.y > HEIGHT - 120) {
                    bird.y = HEIGHT - 120 - bird.height;
                    gameOver = true;
                }


            }

        }

        panelRenderer.repaint();
    }

    /* public void BirdMovement() {
        if (bird.y >= 0 && bird.y <= HEIGHT) {
            speed += acc;
            bird.y += speed;
        }
    }*/
    private void paintTube(Graphics g, Rectangle tube) {
        g.setColor(Color.green.darker());
        g.fillRect(tube.x, tube.y, tube.width, tube.height);
    }

    void repaint(Graphics g) {

         g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, HEIGHT-120, WIDTH, 120 );

        g.setColor(Color.green.darker());
        g.fillRect(0, HEIGHT - 120, WIDTH, 20);


        g.setColor(Color.cyan);
        g.fillRect(bird.x, bird.y, bird.width, bird.height);

        for (Rectangle tube : tubes) {
            paintTube(g, tube);
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", 1, 100));

        if (!started) {
            g.drawString("Click to start", 75, HEIGHT / 2 - 50);
        }

        if (gameOver) {
            g.drawString("GAME OVER", 85, HEIGHT / 2 - 50);
            g.setFont(new Font("Arial", 1, 70));
            g.drawString("Your score: " + score, 120, HEIGHT / 2 + 50);
        }

        if (!gameOver && started) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", 1, 50));
            g.drawString("Score: " + score, 60, 100);
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
