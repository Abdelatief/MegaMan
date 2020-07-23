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
        getEnemies().add(new NormalEnemy(getWorld(), this, "SNES - Mega Man X - Enemies 2", 75, 2.5f, .16f,6, 50, 500, 40, 45,100));
        getEnemies().add(new StrongEnemy(getWorld(), this, "SNES - Mega Man X - Enemies 2", 100, 6, .16f,6,50,600,40,45,150));
        getEnemies().add(new NormalEnemy(getWorld(), this, "SNES - Mega Man X - Enemies 2", 100, 9, .16f,6, 50, 500, 40, 45,125));
        getEnemies().add(new StrongEnemy(getWorld(), this, "SNES - Mega Man X - Enemies 1", 150, 12, .16f,4,90,650,80,45,175));
        getEnemies().add(new StrongEnemy(getWorld(), this, "SNES - Mega Man X - Enemies 1", 200, 15.5f, .16f, 4,90,650,80,45,200));
        getEnemies().add(new NormalEnemy(getWorld(), this, "SNES - Mega Man X - Enemies 2", 100, 20, .16f,6, 50, 500, 40, 45,150));
        getEnemies().add(new StrongEnemy(getWorld(), this, "SNES - Mega Man X - Enemies 2", 175, 22.8f, .16f,6,50,600,40,45,200));
        getEnemies().add(new StrongEnemy(getWorld(), this, "SNES - Mega Man X - Enemies 2", 100, 27, .16f,6,50,600,40,45,150));
        getEnemies().add(new NormalEnemy(getWorld(), this, "SNES - Mega Man X - Enemies 2", 75, 31, .16f,6, 50, 500, 40, 45,100));
        getEnemies().add(new StrongEnemy(getWorld(), this, "SNES - Mega Man X - Enemies 1", 150, 37.5f, .16f,4,90,650,80,45,175));
        getEnemies().add(new Bosses(getWorld(), this, "SNES - Mega Man X - Enemies 1", 450,41.6f,.16f,2, 145, 210,140,110,3000));
    }
}
