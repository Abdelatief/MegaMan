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
        getEnemies().add(new GreenEnemy(getWorld(), this, "SNES - Mega Man X - Enemies 2", 100, 2.5f, .16f));
        getEnemies().add(new BlueManEnemy(getWorld(), this, "SNES - Mega Man X - Enemies 2", 75, 6, .16f));
        getEnemies().add(new GreenEnemy(getWorld(), this, "SNES - Mega Man X - Enemies 2", 100, 9, .16f));
        getEnemies().add(new RedCarEnemy(getWorld(), this, "SNES - Mega Man X - Enemies 1", 150, 12, .16f));
        getEnemies().add(new RedCarEnemy(getWorld(), this, "SNES - Mega Man X - Enemies 1", 150, 15.5f, .16f));
        getEnemies().add(new GreenEnemy(getWorld(), this, "SNES - Mega Man X - Enemies 2", 100, 20, .16f));
        getEnemies().add(new BlueManEnemy(getWorld(), this, "SNES - Mega Man X - Enemies 2", 75, 22.8f, .16f));
        getEnemies().add(new BlueManEnemy(getWorld(), this, "SNES - Mega Man X - Enemies 2", 75, 27, .16f));
        getEnemies().add(new GreenEnemy(getWorld(), this, "SNES - Mega Man X - Enemies 2", 100, 31, .16f));
        getEnemies().add(new RedCarEnemy(getWorld(), this, "SNES - Mega Man X - Enemies 1", 150, 37.5f, .16f));
        getEnemies().add(new BlueBoss(getWorld(), this, "SNES - Mega Man X - Enemies 1", 350,41.6f,.16f));
    }
}
