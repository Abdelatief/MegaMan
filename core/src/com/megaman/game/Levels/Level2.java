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
        getEnemies().add(new WhiteEnemy(getWorld(), this, "SNES - Mega Man X - Enemies 1", 100,4,.16f,200));
        getEnemies().add(new TallGreenEnemy(getWorld(), this, "SNES - Mega Man X - Enemies 2", 150,13,.16f,300));
        getEnemies().add(new WhiteEnemy(getWorld(), this, "SNES - Mega Man X - Enemies 1", 150,18f,.16f,250));
        getEnemies().add(new WhiteEnemy(getWorld(), this, "SNES - Mega Man X - Enemies 1", 100,20.5f,.16f,200));
        getEnemies().add(new TallGreenEnemy(getWorld(), this, "SNES - Mega Man X - Enemies 2", 175,23.5f,.16f,350));
        getEnemies().add(new TallGreenEnemy(getWorld(), this, "SNES - Mega Man X - Enemies 2", 150,28,.16f,300));
        getEnemies().add(new Boss(getWorld(),this,"SNES - Mega Man X - Enemies 1",400,31.5f,.16f,2000));
    }
}

