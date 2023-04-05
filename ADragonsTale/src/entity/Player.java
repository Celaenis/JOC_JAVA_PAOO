package entity;

import main.GamePanel;
import main.KeyManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gp;
    KeyManager keyM;

    public Player(GamePanel gp, KeyManager keyM) {
        this.gp = gp;
        this.keyM = keyM;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4; //nr de pixeli distanta la apasarea unei taste de miscare
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/eu1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/eu2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/player/eu3.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/ed1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/ed2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/player/ed3.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/el1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/el2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/player/el3.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/er1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/er2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/player/er3.png"));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        //if (keyM.upPressed == true || keyM.downPressed == true
               // || keyM.leftPressed == true || keyM.rightPressed == true) {
            if (keyM.upPressed == true) {
                direction = "up";
                y -= speed;
            } if (keyM.downPressed == true) {
                direction = "down";
                y += speed;
            }  if (keyM.leftPressed == true) {
                direction = "left";
                x -= speed;
            }  if (keyM.rightPressed == true) {
                direction = "right";
                x += speed;
            }
            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 3;
                } else if (spriteNum == 3) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
    //    }

    }

    public void draw(Graphics2D g2) {
        //       g2.setColor(Color.white);
        //       g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;
        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                if (spriteNum == 3) {
                    image = up3;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                if (spriteNum == 3) {
                    image = down3;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                if (spriteNum == 3) {
                    image = left3;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                if (spriteNum == 3) {
                    image = right3;
                }
                break;
        }
        g2.drawImage(image, x, y, gp.tileSize * 4, gp.tileSize * 4, null);


    }
}
