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
    }
    
    public void move(Snake snLead) {
        if(snLead.getVely() == 0 && snLead.getVelx() > 0) { //right move
            x = snLead.getX() - SNAKE_SIZE;
            y = snLead.getY();
        }
        if(snLead.getVely() == 0 && snLead.getVelx() < 0) { //left move
            x = snLead.getX() + SNAKE_SIZE;
            y = snLead.getY();
        }
        if(snLead.getVely() > 0 && snLead.getVelx() == 0) { //down move
            x = snLead.getX();
            y = snLead.getY() - SNAKE_SIZE;
        }
        if(snLead.getVely() < 0 && snLead.getVelx() == 0) { //up move
            x = snLead.getX();
            y = snLead.getY() + SNAKE_SIZE;
        }
        velx = snLead.getVelx();
        vely = snLead.getVely();
    }
    
    public void move(SnakeBody snLead) {
        if(snLead.getVely() == 0 && snLead.getVelx() > 0) { //right move
            x = snLead.getX();
            y = snLead.getY();
        }
        if(snLead.getVely() == 0 && snLead.getVelx() < 0) { //left move
            x = snLead.getX();
            y = snLead.getY();
        }
        if(snLead.getVely() > 0 && snLead.getVelx() == 0) { //down move
            x = snLead.getX();
            y = snLead.getY();
        }
        if(snLead.getVely() < 0 && snLead.getVelx() == 0) { //up move
            x = snLead.getX();
            y = snLead.getY();
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
