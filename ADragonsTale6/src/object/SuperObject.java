package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import main.GamePanel;
import main.UtilityTool;

public class SuperObject {
    public BufferedImage image, image2, image3;
    public String name;
    public boolean collision = false;

    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    UtilityTool uTool=new UtilityTool();
}