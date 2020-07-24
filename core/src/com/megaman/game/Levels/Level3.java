package com.megaman.game.Levels;

import com.megaman.game.MegamanGame;
import com.megaman.game.Screen.Playscreen;
import com.megaman.game.Sprites.*;

public class Level3 extends Playscreen {

    public Level3(MegamanGame game) {
        super(game, "level3.tmx");

    }

    @Override
    public void setEnemies() {
        getEnemies().add(new NormalEnemy(getWorld(), this, this.getAtlas().findRegion("SNES - Mega Man X - Enemies 2"), 200, 2.5f, .16f,6, 50, 500, 40, 45,400));
        getEnemies().add(new StrongEnemy(getWorld(), this, this.getAtlas().findRegion("SNES - Mega Man X - Enemies 2"), 250, 6, .16f,6,50,600,40,45,500));
        getEnemies().add(new NormalEnemy(getWorld(), this, this.getAtlas().findRegion("SNES - Mega Man X - Enemies 2"), 225, 9, .16f,6, 50, 500, 40, 45,600));
        getEnemies().add(new StrongEnemy(getWorld(), this, this.getAtlas().findRegion("SNES - Mega Man X - Enemies 1"), 250, 12, .16f,4,90,650,80,45,500));
        getEnemies().add(new StrongEnemy(getWorld(), this, this.getAtlas().findRegion("SNES - Mega Man X - Enemies 1"), 275, 15.5f, .16f, 4,90,650,80,45,700));
        getEnemies().add(new NormalEnemy(getWorld(), this, this.getAtlas().findRegion("SNES - Mega Man X - Enemies 2"), 225, 20, .16f,6, 50, 500, 40, 45,600));
        getEnemies().add(new StrongEnemy(getWorld(), this, this.getAtlas().findRegion("SNES - Mega Man X - Enemies 2"), 250, 22.8f, .16f,6,50,600,40,45,500));
        getEnemies().add(new StrongEnemy(getWorld(), this, this.getAtlas().findRegion("SNES - Mega Man X - Enemies 2"), 275, 27, .16f,6,50,600,40,45,700));
        getEnemies().add(new NormalEnemy(getWorld(), this, this.getAtlas().findRegion("SNES - Mega Man X - Enemies 2"), 225, 31, .16f,6, 50, 500, 40, 45,600));
        getEnemies().add(new StrongEnemy(getWorld(), this, this.getAtlas().findRegion("SNES - Mega Man X - Enemies 1"), 250, 37.5f, .16f,4,90,650,80,45,500));
        getEnemies().add(new Bosses(getWorld(), this, this.getAtlas().findRegion("SNES - Mega Man X - Enemies 1"), 600,41.6f,.16f,2, 145, 210,140,110,3000));
    }
}
