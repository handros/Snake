/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.Image;
import java.util.Random;

/**
 *
 * @author Draker
 */
public class Snake extends Sprite {
    
    private int velx;
    private int vely;
    
    private Random rand;
    private int ran;

    public Snake(int x, int y, int width, int height, Image image) {
        super(x, y, width, height, image);
        
        rand = new Random();
        ran = rand.nextInt(4);
        switch(ran) {
            case 0: //le
                velx = 0;
                vely = 25;
                break;
            case 1: //fel
                velx = 0;
                vely = -25;
                break;
            case 2: //jobb
                velx = 25;
                vely = 0;
                break;
            case 3: //bal
                velx = -25;
                vely = 0;
                break;
            default:
                velx = 0;
                vely = 0;
        }
    }
    
    public boolean moveX() {
        x += velx;
        if (x + width >= 800 || x <= 0) {
            return false;
        }
        return true;
    }
    
    public boolean moveY() {
        y += vely;
        if (y <= 0 || y >= 600-height) {
            return false;
        }
        return true;
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

    public int getRan() {
        return ran;
    }
    
    
}
