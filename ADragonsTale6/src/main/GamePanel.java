package main;

import entity.Entity;
import entity.Player;
import tiles.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    ///Screen Settings
    final int originalTileSize = 32;  // 32x32 tile
    final int scale = 2;
    public final int tileSize = originalTileSize * scale; // 64x64 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; //1024 pixels
    public final int screenHeight = tileSize * maxScreenRow; //768 pixels

    //FPS;
    int FPS = 60;

    //System
    TileManager tileM = new TileManager(this, "/maps/lvl1.txt");
    TileManager tileM2 = new TileManager(this, "/maps/lvl1o.txt");
    KeyManager keyM = new KeyManager(this);
    public CollisionCheck cCheck = new CollisionCheck(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread;

    //Entity
    public Player player = new Player(this, keyM);
    public Entity npc[] = new Entity[10];

    //Game State
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyM);
        this.setFocusable(true);

    }

    public void setupGame() {
        aSetter.setNPC();
        gameState = playState;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS; //0.01666 secunde
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if (delta >= 1) {
                // 1 UPDATE: update informatiei ex pozitia caracterului
                update();
                // 2 DRAW: desenam pe exran cu informatia updated
                repaint();
                delta--;
                drawCount++;

            }
            if (timer >= 1000000000) {
                System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }

        }
    }

    public void update() {
        if (gameState == playState) {
            player.update();

            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    npc[i].update();
                }
            }
        }
        if (gameState == pauseState) {

        }
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        tileM.draw(g2);
        tileM2.draw(g2);

        //NPC

        for (int i = 0; i < npc.length; i++) {
            if (npc[i] != null) {
                npc[i].draw(g2);
            }
        }


        player.draw(g2);

        ui.draw(g2);

        g2.dispose();


    }
}
