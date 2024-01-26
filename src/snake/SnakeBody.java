/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.Image;

/**
 *
 * @author Draker
 */
public class SnakeBody extends Sprite {
    
    private int velx;
    private int vely;
    
    private final int SNAKE_SIZE = 25;
    
    
    public SnakeBody(int x, int y, int width, int height, Image image) {
        super(x, y, width, height, image);
        
//        velx = 0;
//        vely = 0;
    }
    
    public void move(Snake snLead) {
        if(snLead.getVely() == 0 && snLead.getVelx() > 0) { //jobbra
            x = snLead.getX() - SNAKE_SIZE;
            y = snLead.getY();
        }
        if(snLead.getVely() == 0 && snLead.getVelx() < 0) { //balra
            x = snLead.getX() + SNAKE_SIZE;
            y = snLead.getY();
        }
        if(snLead.getVely() > 0 && snLead.getVelx() == 0) { //le
            x = snLead.getX();
            y = snLead.getY() - SNAKE_SIZE;
        }
        if(snLead.getVely() < 0 && snLead.getVelx() == 0) { //fel
            x = snLead.getX();
            y = snLead.getY() + SNAKE_SIZE;
        }
        velx = snLead.getVelx();
        vely = snLead.getVely();
    }
    
    public void move(SnakeBody snLead) {
        if(snLead.getVely() == 0 && snLead.getVelx() > 0) { //jobbra
            x = snLead.getX()/* - SNAKE_SIZE*/;
            y = snLead.getY();
        }
        if(snLead.getVely() == 0 && snLead.getVelx() < 0) { //balra
            x = snLead.getX()/* + SNAKE_SIZE*/;
            y = snLead.getY();
        }
        if(snLead.getVely() > 0 && snLead.getVelx() == 0) { //le
            x = snLead.getX();
            y = snLead.getY()/* - SNAKE_SIZE*/;
        }
        if(snLead.getVely() < 0 && snLead.getVelx() == 0) { //fel
            x = snLead.getX();
            y = snLead.getY() /*+ SNAKE_SIZE*/;
        }
        velx = snLead.getVelx();
        vely = snLead.getVely();
    }

    
    public int getVelx() {
        return velx;
    }

    public void setVelx(int velx) {
        this.velx = velx;
    }

    public int getVely() {
        return vely;
    }

    public void setVely(int vely) {
        this.vely = vely;
    }
    
    
}
