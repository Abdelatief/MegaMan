package com.megaman.game.Levels;

import com.megaman.game.MegamanGame;
import com.megaman.game.Screen.Playscreen;
import com.megaman.game.Sprites.Boss;
import com.megaman.game.Sprites.TallGreenEnemy;
import com.megaman.game.Sprites.WhiteEnemy;

public class Level2 extends Playscreen {

    public Level2(MegamanGame game) {
        super(game,"Level2.tmx");

    }

    @Override
    public void setEnemies() {
        getEnemies().add(new WhiteEnemy(getWorld(), this, "SNES - Mega Man X - Enemies 1", 100,4,.16f));
        getEnemies().add(new TallGreenEnemy(getWorld(), this, "SNES - Mega Man X - Enemies 2", 75,13,.16f));
        getEnemies().add(new WhiteEnemy(getWorld(), this, "SNES - Mega Man X - Enemies 1", 100,18f,.16f));
        getEnemies().add(new WhiteEnemy(getWorld(), this, "SNES - Mega Man X - Enemies 1", 100,20.5f,.16f));
        getEnemies().add(new TallGreenEnemy(getWorld(), this, "SNES - Mega Man X - Enemies 2", 75,23.5f,.16f));
        getEnemies().add(new TallGreenEnemy(getWorld(), this, "SNES - Mega Man X - Enemies 2", 75,28,.16f));
        getEnemies().add(new Boss(getWorld(),this,"SNES - Mega Man X - Enemies 1",250,31.5f,.16f,1000));
    }
}

