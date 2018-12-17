package com.company;

import java.util.Random;

public class Tube {
private Random r= new Random();
private int tubeX;
private int tubeY=50+r.nextInt(GamePanel.getHEIGHT()-400);
private int tubeWidth=50;
private int tubeHeight=GamePanel.getHEIGHT()-tubeY;
private int gap=200;

public Tube(int tubeX){
this.tubeX=tubeX;
}

    public int getTubeX() {
        return tubeX;
    }

    public int getTubeY() {
        return tubeY;
    }

    public int getTubeWidth() {
        return tubeWidth;
    }

    public int getTubeHeight() {
        return tubeHeight;
    }

    public int getGap() {
        return gap;
    }

    public void setTubeX(int tubeX) {
        this.tubeX = tubeX;
    }

    public void setTubeY(int tubeY) {
        this.tubeY = tubeY;
    }

    public void setTubeHeight(int tubeHeight) {
        this.tubeHeight = tubeHeight;
    }
}
