package tiles;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp, String filePath) {
        this.gp = gp;
        tile = new Tile[20];
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow]; //aici se stocheaza matricea hartii
        getTileImage();
        loadMap(filePath);
    }

    public void getTileImage() {

        setup(0, "ground1", false);
        setup(1, "wall1", true);
        setup(2, "wall2", true);
        setup(3, "walll", true);
        setup(4, "wallr", true);
        setup(5, "1l", true);
        setup(6, "1r", true);
        setup(7, "1u", true);
        setup(8, "1d", true);
        setup(9, "1dl", true);
        setup(10, "1dr", true);
        setup(11, "1ul", true);
        setup(12, "1ur", true);
        setup(13, "Bookshelf", true);


    }

    public void setup(int index, String imageName, boolean collision) {
        UtilityTool uTool = new UtilityTool();
        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/background_Lvl1/" + imageName + ".png"));
            tile[index].image = uTool.scaledImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;
            while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
                String line = br.readLine();
                while (col < gp.maxScreenCol) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxScreenCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
        }
    }

    public void draw(Graphics2D g2) {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
            int tileNum = mapTileNum[col][row];
            g2.drawImage(tile[tileNum].image, x, y, null);
            col++;
            x += gp.tileSize;

            if (col == gp.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }

    }
}
