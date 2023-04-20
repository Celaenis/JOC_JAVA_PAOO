
package main;

import entity.Elise;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setNPC() {
        gp.npc[0] = new Elise(gp);
        gp.npc[0].x = gp.tileSize * 3;
        gp.npc[0].y = gp.tileSize * 3;
    }
}

