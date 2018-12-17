package com.company;

import java.awt.*;

public class GameLogic {
    private Bird bird;
    private Tube tube;
    private Tube tube2;
    private boolean GameOver=false;
    private int score=0;
    private static int speed = 2;
    private int acc = 1;






    private static int speed1=-6;

    public boolean isGameOver() {
        return GameOver;
    }

    public void count(){
        if (tube.getTubeX()==bird.getBirdX()){
            score++;
        }

    }
    public int getScore() {
        return score;
    }

    public GameLogic(Bird bird, Tube tube, Tube tube2 ) {
        this.bird = bird;
        this.tube= tube;
        this.tube2=tube2;

    }

    public void tubeMovement(){
        int nextX=tube.getTubeX();
        int nextY=tube.getTubeY();
        int height=tube.getTubeHeight();
        nextX+=speed1;
        if(nextX<=-tube.getTubeWidth() ){
           nextX=GamePanel.getWIDTH();
           nextY=tube.getTubeY();
           height=GamePanel.getHEIGHT()-nextY;
            tube.setTubeX(nextX);
            tube.setTubeY(nextY);
            tube.setTubeHeight(height);
        }

        tube.setTubeX(nextX);
        tube.setTubeY(nextY);
        tube.setTubeHeight(height);
        Rectangle lowerRect=new Rectangle(tube.getTubeX(),tube.getTubeY(),tube.getTubeWidth(), tube.getTubeHeight());
        Rectangle upperRect=new Rectangle(tube.getTubeX(),0,tube.getTubeWidth(), GamePanel.getHEIGHT()-(tube.getTubeHeight()+tube.getGap()));

        if(lowerRect.intersects(getBirdRect())|| upperRect.intersects(getBirdRect())){
            reset();
            tubeReset();


        }

        count();


    }
    private void tubeReset(){
        int y=tube.getTubeY();
        int height=GamePanel.getHEIGHT()-y;
        tube.setTubeY(y);
        tube.setTubeHeight(height);

        GameOver=true;

    }

    public void tubeMovement2(){
        int nextX=tube2.getTubeX();
        int nextY=tube2.getTubeY();
        int height=tube2.getTubeHeight();
        nextX+=speed1;
        if(nextX<=-tube2.getTubeWidth() ){
            nextX=GamePanel.getWIDTH();
            nextY=tube2.getTubeY();
            height=GamePanel.getHEIGHT()-nextY;
            tube2.setTubeX(nextX);
            tube2.setTubeY(nextY);
            tube2.setTubeHeight(height);
        }

        tube2.setTubeX(nextX);
        tube2.setTubeY(nextY);
        tube2.setTubeHeight(height);

    }

    public void movement() {
        if (bird.getBirdX() >= 0 && bird.getBirdY() <= GamePanel.getHEIGHT()) {
            int nextY = bird.getBirdY();
            speed += acc;
            nextY += speed;

            bird.setBirdY(nextY);

        } else {
            reset();
        }

    }

    public void goUp(){
        speed=-14;
    }

    private void reset(){
        speed=2;
        int y=GamePanel.getHEIGHT()/2-10;
        bird.setBirdY(y);
    }
    public Rectangle getBirdRect(){
        Rectangle birdRect= new Rectangle(bird.getBirdX(),bird.getHeight(),bird.getWidth(),bird.getHeight());
        return birdRect;
    }

}