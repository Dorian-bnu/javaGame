package main;

import entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //Screen settings
    final int originalTileSize = 16; //Default tile size
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;  // 48x48size
    final int maxScreeCol = 16;
    final int maxScreeRow = 12;
    final int screenWidth = tileSize * maxScreeCol;
    final int screenHeight = tileSize * maxScreeRow;


    //FPS
    int FPS = 60;


    KeyHandler keyH = new KeyHandler();
    Player player = new Player(this, keyH);
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


            double drawInterval = 1000000000/FPS;
            double nextDrawTime = System.nanoTime() + drawInterval;

            // Update informations such as charachter positions
            update();
            //draw the screen witrh the updated informations
            repaint();
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime /1000000;
                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                //Pause game loop
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
                }
                catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public void update(){
        player.update();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        player.draw(g2);

        g2.dispose();
    }
}