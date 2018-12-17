package com.company;

public class Bird {

private int birdX=(GamePanel.getWIDTH())/2-10;
private int birdY=(GamePanel.getHEIGHT())/2-10;;

private int width=20;
private int height=20;



    public int getBirdX() {
        return birdX;
    }

    public int getBirdY() {
        return birdY;
    }

    public void setBirdY(int birdY) {
        this.birdY = birdY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
