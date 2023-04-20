package main;

import entity.Entity;
import tiles.TileManager;

import java.awt.*;

public class CollisionCheck {
    GamePanel gp;

    public CollisionCheck(GamePanel gp) {
        this.gp = gp;

    }

    public void checkTile(Entity entity) {

        int entityLeftX = entity.x + entity.solidArea.x;
        int entityRightX = entity.x + entity.solidArea.x + entity.solidArea.width;
        int entityTopY = entity.y + entity.solidArea.y;
        int entityBottomY = entity.y + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftX / gp.tileSize;
        int entityRightCol = entityRightX / gp.tileSize;
        int entityTopRow = entityTopY / gp.tileSize;
        int entityBottomRow = entityBottomY / gp.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM2.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM2.mapTileNum[entityRightCol][entityTopRow];
                if (gp.tileM2.tile[tileNum1].collision == true || gp.tileM2.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM2.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM2.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM2.tile[tileNum1].collision == true || gp.tileM2.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM2.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM2.mapTileNum[entityLeftCol][entityBottomRow];
                if (gp.tileM2.tile[tileNum1].collision == true || gp.tileM2.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM2.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM2.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM2.tile[tileNum1].collision == true || gp.tileM2.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;

        }
    }

    public int checkEntity(Entity entity, Entity[] target) {
        int index = 999;

        for (int i = 0; i < target.length; ++i) {
            if (target[i] != null) {
                entity.solidArea.x += entity.x;
                entity.solidArea.y += entity.y;
                target[i].solidArea.x += target[i].x;
                target[i].solidArea.y += target[i].y;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            entity.collisionOn = true;
                            index = i;

                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            entity.collisionOn = true;
                            index = i;

                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            entity.collisionOn = true;
                            index = i;

                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            entity.collisionOn = true;
                            index = i;

                        }
                        break;
                }

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;
            }
        }
        return index;
    }

    public void checkPlayer(Entity entity) {
        entity.solidArea.x += entity.x;
        entity.solidArea.y += entity.y;
        gp.player.solidArea.x += gp.player.x;
        gp.player.solidArea.y += gp.player.y;

        switch (entity.direction) {
            case "up":
                entity.solidArea.y -= entity.speed;
                if (entity.solidArea.intersects(gp.player.solidArea)) {
                    entity.collisionOn = true;

                }
                break;
            case "down":
                entity.solidArea.y += entity.speed;
                if (entity.solidArea.intersects(gp.player.solidArea)) {
                    entity.collisionOn = true;

                }
                break;
            case "left":
                entity.solidArea.x -= entity.speed;
                if (entity.solidArea.intersects(gp.player.solidArea)) {
                    entity.collisionOn = true;

                }
                break;
            case "right":
                entity.solidArea.x += entity.speed;
                if (entity.solidArea.intersects(gp.player.solidArea)) {
                    entity.collisionOn = true;

                }
                break;
        }

        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
    }


}
