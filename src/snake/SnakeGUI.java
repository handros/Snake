/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
/**
 *
 * @author Draker
 */
public class SnakeGUI {
    private JFrame frame;
    private GameEngine gameArea;
//    private HighScores highScores;
    
    public SnakeGUI() {
//        try {
//            highScores = new HighScores(10);
//            System.out.println(highScores.getHighScores());
////            highScores.putHighScore(gameArea.getPlayer(), gameArea.getPoints());
//            System.out.println(highScores.getHighScores());
//        } catch (SQLException ex) {
//            Logger.getLogger(SnakeGUI.class.getName()).log(Level.SEVERE, null, ex);
//        }
        frame = new JFrame("Snake");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        gameArea = new GameEngine();
        frame.getContentPane().add(gameArea);
        
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu gameMenu = new JMenu("Game");
        menuBar.add(gameMenu);
        
        JMenuItem newMenu = new JMenuItem("New");
        newMenu.setMnemonic('N');
        gameMenu.add(newMenu);
        newMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameArea.restart();
            }
        });
        
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setMnemonic('E');
        gameMenu.add(exitMenuItem);
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });

    
        
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }
}
