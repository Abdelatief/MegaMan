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

        getEnemies().add(new BirdEnemy(getWorld(), this, "SNES - Mega Man X - Enemies 2", 25,1.8f,.16f));
        getEnemies().add(new BlueBirdEnemy(getWorld(), this, "SNES - Mega Man X - Enemies 2", 50, 6, .16f));
        getEnemies().add(new RobotEnemy(getWorld(), this, "SNES - Mega Man X - Enemies 2", 75, 17.5f, .16f));
        getEnemies().add(new BlueBirdEnemy(getWorld(), this, "SNES - Mega Man X - Enemies 2", 25,21.5f,.16f));
        getEnemies().add(new BirdEnemy(getWorld(), this, "SNES - Mega Man X - Enemies 2", 75,10.5f,.16f));
        getEnemies().add(new RobotEnemy(getWorld(), this, "SNES - Mega Man X - Enemies 2", 75,13,.16f));
        getEnemies().add(new BlueBoss(getWorld(), this, "SNES - Mega Man X - Enemies 1", 200,25.5f,.16f));

    }
}
