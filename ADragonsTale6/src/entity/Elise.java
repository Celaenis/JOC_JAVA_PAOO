package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;

public class Elise extends Entity {

    public Elise(GamePanel gp) {
        super(gp);
        direction = "down";
        speed = 3;

        getImage();
    }

    public void getImage() {

        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/Elise/elu1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/Elise/elu2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/Elise/elu3.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/Elise/eld1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/Elise/eld2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/Elise/eld3.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/Elise/ell1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/Elise/ell2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/Elise/ell3.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/Elise/elr1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/Elise/elr2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/Elise/elr3.png"));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAction() {
        actionLockCounter++;

        if (actionLockCounter == 90) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;

            if (i <= 25) {
                direction = "down";
            }
            if (i > 25 && i <= 50) {
                direction = "up";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i <= 100) {
                direction = "right";
            }
            actionLockCounter = 0;
        }


    }

}
