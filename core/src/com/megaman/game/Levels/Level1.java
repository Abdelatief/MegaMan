package com.megaman.game.Levels;

import com.megaman.game.MegamanGame;
import com.megaman.game.Screen.Playscreen;
import com.megaman.game.Sprites.*;


public class Level1 extends Playscreen {

    public Level1(MegamanGame game) {
        super(game, "Mega_Level1.tmx");

    }

    @Override
    public void setEnemies() {

        getEnemies().add(new BirdEnemy(getWorld(), this, "SNES - Mega Man X - Enemies 2", 25,1.8f,.16f,75));
        getEnemies().add(new BlueBirdEnemy(getWorld(), this, "SNES - Mega Man X - Enemies 2", 50, 6, .16f,100));
        getEnemies().add(new RobotEnemy(getWorld(), this, "SNES - Mega Man X - Enemies 2", 75, 17.5f, .16f,200));
        getEnemies().add(new BlueBirdEnemy(getWorld(), this, "SNES - Mega Man X - Enemies 2", 50,21.5f,.16f,100));
        getEnemies().add(new BirdEnemy(getWorld(), this, "SNES - Mega Man X - Enemies 2", 75,10.5f,.16f,150));
        getEnemies().add(new RobotEnemy(getWorld(), this, "SNES - Mega Man X - Enemies 2", 100,13,.16f,250));
        getEnemies().add(new BlueBoss(getWorld(), this, "SNES - Mega Man X - Enemies 1", 300,25.5f,.16f,1000));

    }
}
