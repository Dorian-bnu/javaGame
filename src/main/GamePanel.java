package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //Screen settings
    final int originalTileSize = 16; //Default tile size
    final int scale = 3;

    final int tileSize = originalTileSize * scale;  // 48x48size
    final int maxScreeCol = 16;
    final int maxScreeRow = 12;
    final int screenWidth = tileSize * maxScreeCol;
    final int screenHeight = tileSize * maxScreeRow;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    //set player's default position
    int playerX, playerY = 100;
    int playerSpeed = 4;


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);// this for gamePanel
        gameThread.start();
    }

    @Override
    public void run() { //Automatiquement appelé lors du call du gameThread
        while(gameThread != null) {
            // Update informations such as charachter positions
            update();
            //draw the screen witrh the updated informations
            repaint();

        }
    }

    public void update(){
        if(keyH.up == true) {
            playerY -= playerSpeed;
        }
        if(keyH.down == true) {
            playerY += playerSpeed;
        }
        if(keyH.left == true) {
            playerX -= playerSpeed;
        }
        if(keyH.right == true) {
            playerX += playerSpeed;
        }
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.red);
        g2.fillRect(playerX + 100, playerY, 100,  100);

        g2.dispose();
    }
}