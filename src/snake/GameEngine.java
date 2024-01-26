package snake;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import java.util.Random;
import javax.swing.JTextField;
/**
 *
 * @author Draker
 */
public class GameEngine extends JPanel {
    private final int FPS = /*240*/7;
    private final int SNAKE_MOVEMENT = 25;
    private final int SNAKE_WIDTH = 25;
    private final int SNAKE_HEIGHT = 25;
    private final int FOOD_SIZE = 35;
    private final int BRICK_SIZE = 50;
    
    private int points;
    
    private boolean over;
    private boolean paused = false;
    private Image background;
    private Snake snake;
    ArrayList<SnakeBody> snakeB;
    
    private Food food;

    ArrayList<Brick> bricks;
    private Timer newFrameTimer;
    
    private Random rand;
    
    public GameEngine() {
        points = 0;
        over = false;
        snakeB  = new ArrayList<>();
        
        bricks = new ArrayList<>();
        rand = new Random();
        
        background = new ImageIcon("data/background.jpg").getImage();
        this.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "pressed left");
        this.getActionMap().put("pressed left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(snake.getVelx() == 0) {
                    snake.setVelx(-SNAKE_MOVEMENT);
                    snake.setVely(0);
                }
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "pressed right");
        this.getActionMap().put("pressed right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(snake.getVelx() == 0) {
                    snake.setVelx(SNAKE_MOVEMENT);
                    snake.setVely(0);
                }
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "pressed down");
        this.getActionMap().put("pressed down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(snake.getVely() == 0) {
                    snake.setVely(SNAKE_MOVEMENT);
                    snake.setVelx(0);
                }
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("UP"), "pressed up");
        this.getActionMap().put("pressed up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(snake.getVely() == 0) {
                    snake.setVely(-SNAKE_MOVEMENT);
                    snake.setVelx(0);
                }
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("ESCAPE"), "escape");
        this.getActionMap().put("escape", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                paused = !paused;
            }
        });
        
        restart();
        newFrameTimer = new Timer(1000 / FPS, new NewFrameListener());
        newFrameTimer.start();
    }
    
    public void restart() { //pálya létrehozása/újrakezdése
        over = false;
        points = 0;
        Image snakeHImage = new ImageIcon("data/snakehead.png").getImage();
        snake = new Snake(370, 270, SNAKE_WIDTH, SNAKE_HEIGHT, snakeHImage);
        
        Image snakeImage = new ImageIcon("data/snake.jpg").getImage();
        snakeB.clear();
        if(snake.getVelx() < 0 && snake.getVely() == 0) { //balra indul
            snakeB.add(new SnakeBody(370 + SNAKE_WIDTH, 270, SNAKE_WIDTH, SNAKE_HEIGHT, snakeImage));
        } else if(snake.getVelx() > 0 && snake.getVely() == 0) { //jobbra indul
            snakeB.add(new SnakeBody(370 - SNAKE_WIDTH, 270, SNAKE_WIDTH, SNAKE_HEIGHT, snakeImage));
        } else if(snake.getVelx() == 0 && snake.getVely() < 0) { //fel indul
            snakeB.add(new SnakeBody(370, 270 + SNAKE_HEIGHT, SNAKE_WIDTH, SNAKE_HEIGHT, snakeImage));
        } else if(snake.getVelx() == 0 && snake.getVely() > 0) { //le indul
            snakeB.add(new SnakeBody(370, 270 - SNAKE_HEIGHT, SNAKE_WIDTH, SNAKE_HEIGHT, snakeImage));
        }
        
        createBricks();
        generateFood();
    }
    
    public void addBodyPart() {
        Image snakeImage = new ImageIcon("data/snake.jpg").getImage();
        
        SnakeBody snakeEnd = snakeB.get(snakeB.size() - 1);
        
        if(snakeEnd.getVelx() < 0 && snakeEnd.getVely() == 0) { //balra indul
            snakeB.add(new SnakeBody(snakeEnd.getX() + SNAKE_WIDTH, snakeEnd.getY(), SNAKE_WIDTH, SNAKE_HEIGHT, snakeImage));
        } else if(snakeEnd.getVelx() > 0 && snakeEnd.getVely() == 0) { //jobbra indul
            snakeB.add(new SnakeBody(snakeEnd.getX() - SNAKE_WIDTH, snakeEnd.getY(), SNAKE_WIDTH, SNAKE_HEIGHT, snakeImage));
        } else if(snakeEnd.getVelx() == 0 && snakeEnd.getVely() < 0) { //fel indul
            snakeB.add(new SnakeBody(snakeEnd.getX(), snakeEnd.getY() + SNAKE_HEIGHT, SNAKE_WIDTH, SNAKE_HEIGHT, snakeImage));
        } else if(snakeEnd.getVelx() == 0 && snakeEnd.getVely() > 0) { //le indul
            snakeB.add(new SnakeBody(snakeEnd.getX(), snakeEnd.getY() - SNAKE_HEIGHT, SNAKE_WIDTH, SNAKE_HEIGHT, snakeImage));
        }
    }
    
    public void generateFood() {
        int x, y;
        boolean noBrick = false;
        boolean noSnake = false;
        Image foodImage = new ImageIcon("data/apple.png").getImage();
        do {
            x = rand.nextInt(650)+50;
            y = rand.nextInt(450)+50;
            
            for (Brick b : bricks) {
                if (x >= b.getX() && x+BRICK_SIZE <= b.getX()) {
                    noBrick = false;
                } else {
                    noBrick = true;
                    break;
                }
                if (y >= b.getY() && y+BRICK_SIZE <= b.getY()) {
                    noBrick = false;
                } else {
                    noBrick = true;
                    break;
                }
            }
            
            for (SnakeBody b : snakeB) {
                if (x >= b.getX() && x+SNAKE_WIDTH <= b.getX()) {
                    noSnake = false;
                } else {
                    noSnake = true;
                    break;
                }
                if (y >= b.getY() && y+SNAKE_HEIGHT <= b.getY()) {
                    noSnake = false;
                } else {
                    noSnake = true;
                    break;
                }
            }
            
        } while(!noBrick && !noSnake);
        
        food = new Food(x, y, FOOD_SIZE, FOOD_SIZE, foodImage);
        
    }
    
    public void createBricks() {
        bricks.clear();
        Image brickImage = new ImageIcon("data/brick.jpg").getImage();
        bricks.add(new Brick(75, 125, BRICK_SIZE, BRICK_SIZE, brickImage));
        bricks.add(new Brick(175, 150, BRICK_SIZE, BRICK_SIZE, brickImage));
        bricks.add(new Brick(100, 475, BRICK_SIZE, BRICK_SIZE, brickImage));
        bricks.add(new Brick(625, 450, BRICK_SIZE, BRICK_SIZE, brickImage));
        bricks.add(new Brick(500, 75, BRICK_SIZE, BRICK_SIZE, brickImage));
    }
    
    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        grphcs.drawImage(background, 0, 0, 800, 600, null);
        food.draw(grphcs);
        snake.draw(grphcs);
        drawSnakeBody(grphcs);
        drawBricks(grphcs);
        
    }
    
    public void drawSnakeBody(Graphics g) {
        for (SnakeBody b : snakeB) {
            b.draw(g);
        }
    }
    
    public void drawBricks(Graphics g) {
        for (Brick b : bricks) {
            b.draw(g);
        }
    }
    
    
    
    class NewFrameListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (!paused) {
                if(!snake.moveX()) {
                    over = true;
                }
                if(!snake.moveY()) {
                    over = true;
                }
                
                for (Brick b : bricks) {
                    if (snake.collides(b)) {
                        over = true;
                        break;
                    }
                }

                for(int b=snakeB.size()-1; b>=0; b--) {
                    if(b == 0) { //fej mögötti rész
                        snakeB.get(b).move(snake);
                    } else {
                        snakeB.get(b).move(snakeB.get(b-1));
                    }
                    if (snake.collides(snakeB.get(b))) {
                        over = true;
                        break;
                    }
                }
                
                if (snake.collides(food)) {
                    generateFood();
                    points++;
                    addBodyPart();
                }

                
                if (over) {
                    System.out.println(points);
                    restart();
                }
            }
            repaint();
        }
    }
    
    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }    
}
