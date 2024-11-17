package main;

import javax.swing.*;

public class Main {


    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("MyGame :)");
        window.setResizable(false);

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();

        window.setLocationRelativeTo(null); //Affichée au milieu de l'écran

        gamePanel.startGameThread();

        window.setVisible(true);

    }
}