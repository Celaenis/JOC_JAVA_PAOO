package main;

import object.OBJ_Heart;
import object.SuperObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_90B;
    BufferedImage heart_full, heart_half, heart_blank;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;

    public UI(GamePanel gp) {
        this.gp = gp;
        arial_90B = new Font("Arial", Font.BOLD, 90);
        arial_40 = new Font("Arial", Font.PLAIN, 40);

        //Creare obiect HUD
        SuperObject heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;
    }


    public void showMassage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(arial_40);
        g2.setColor(Color.YELLOW);

        if (gp.gameState == gp.playState) {

        }
        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }

        drawPlayerLife();

        //Testing afisare mesaje
        /**
        if (gameFinished == true) {
            g2.setFont(arial_90B);
            g2.setColor(Color.GREEN);
            String text;
            int textLength;
            int x, y;
            text = "You finished the game";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            x = gp.screenWidth / 2 - textLength / 2;
            y = gp.screenHeight / 2 - gp.tileSize * 3;
            g2.drawString(text, x, y);

            gp.gameThread = null;

        } else {
            g2.setFont(arial_40);
            g2.setColor(Color.RED);
            g2.drawString("Health", 50, 50);

            if (messageOn == true) {
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, 500, 50);

                messageCounter++;
                if (messageCounter > 120) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
         */
    }

    public void drawPlayerLife(){

        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;

        while(i < gp.player.maxLife/2){
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.tileSize;
        }
        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;
        while(i < gp.player.life){
            g2.drawImage(heart_half, x, y, null);
            i++;
            if(i < gp.player.life){
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gp.tileSize;
        }

    }

    public void drawPauseScreen() {
        g2.setFont(arial_90B);
        String text = "PAUSED";
        int x = getXforCenter(text);
        int y = gp.screenHeight / 2 - gp.tileSize * 3;
        g2.drawString(text, x, y);

    }
    public int getXforCenter(String text){
        int textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - textLength / 2;
        return x;
    }

}
