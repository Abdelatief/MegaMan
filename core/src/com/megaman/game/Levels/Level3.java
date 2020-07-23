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
        getEnemies().add(new GreenEnemy(getWorld(), this, "SNES - Mega Man X - Enemies 2", 75, 2.5f, .16f,100));
        getEnemies().add(new BlueManEnemy(getWorld(), this, "SNES - Mega Man X - Enemies 2", 100, 6, .16f,150));
        getEnemies().add(new GreenEnemy(getWorld(), this, "SNES - Mega Man X - Enemies 2", 100, 9, .16f,125));
        getEnemies().add(new RedCarEnemy(getWorld(), this, "SNES - Mega Man X - Enemies 1", 150, 12, .16f,175));
        getEnemies().add(new RedCarEnemy(getWorld(), this, "SNES - Mega Man X - Enemies 1", 200, 15.5f, .16f,200));
        getEnemies().add(new GreenEnemy(getWorld(), this, "SNES - Mega Man X - Enemies 2", 100, 20, .16f,150));
        getEnemies().add(new BlueManEnemy(getWorld(), this, "SNES - Mega Man X - Enemies 2", 175, 22.8f, .16f,200));
        getEnemies().add(new BlueManEnemy(getWorld(), this, "SNES - Mega Man X - Enemies 2", 100, 27, .16f,150));
        getEnemies().add(new GreenEnemy(getWorld(), this, "SNES - Mega Man X - Enemies 2", 75, 31, .16f,100));
        getEnemies().add(new RedCarEnemy(getWorld(), this, "SNES - Mega Man X - Enemies 1", 150, 37.5f, .16f,175));
        getEnemies().add(new BlueBoss(getWorld(), this, "SNES - Mega Man X - Enemies 1", 450,41.6f,.16f,3000));
    }
}
