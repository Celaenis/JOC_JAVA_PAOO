package entity;

import jdk.jshell.execution.Util;
import main.GamePanel;
import main.KeyManager;
import main.UtilityTool;

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

        solidArea = new Rectangle(32, 50, 32, 32);

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = gp.tileSize * 10;
        y = gp.tileSize * 4;
        speed = 5; //nr de pixeli distanta la apasarea unei taste de miscare
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
         /**
        up1 = setup("eu1");
        up2 = setup("eu2");
        up3 = setup("eu3");
        down1 = setup("ed1");
        down2 = setup("ed2");
        down3 = setup("ed3");
        left1 = setup("el1");
        left2 = setup("el2");
        left3 = setup("el3");
        right1 = setup("er1");
        right2 = setup("er2");
        right3 = setup("el3");
    }



    public BufferedImage setup(String imageName) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/player/" + imageName + ".png"));
            image = uTool.scaledImage(image, gp.tileSize * 3, gp.tileSize * 3);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;

    }
         */
    public void update() {
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
        if (keyM.upPressed == true || keyM.downPressed == true || keyM.leftPressed == true || keyM.rightPressed == true) {
            if (keyM.upPressed == true) {
                direction = "up";
            }
            if (keyM.downPressed == true) {
                direction = "down";
            }
            if (keyM.leftPressed == true) {
                direction = "left";
            }
            if (keyM.rightPressed == true) {
                direction = "right";
            }

            //Verifica coliziunea
            collisionOn = false;
            gp.cCheck.checkTile(this);

            //Daca collision e false, playerul se poate misca
            if (collisionOn == false) {
                switch (direction) {
                    case "up":
                        y -= speed;
                        break;
                    case "down":
                        y += speed;
                        break;
                    case "left":
                        x -= speed;
                        break;
                    case "right":
                        x += speed;
                        break;
                }
            }


        }
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
        g2.drawImage(image, x, y, null);


    }
}
