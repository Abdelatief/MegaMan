package com.megaman.game.Levels;

import com.megaman.game.MegamanGame;
import com.megaman.game.Screen.Playscreen;
import com.megaman.game.Sprites.Boss;
import com.megaman.game.Sprites.NormalEnemy;
import com.megaman.game.Sprites.StrongEnemy;

public class Level2 extends Playscreen {

    public Level2(MegamanGame game) {
        super(game,"Level2.tmx");

    }

    @Override
    public void setEnemies() {
        getEnemies().add(new NormalEnemy(getWorld(), this, "SNES - Mega Man X - Enemies 1", 100,4,.16f,4,50,1050,50,60,200));
        getEnemies().add(new StrongEnemy(getWorld(), this, "SNES - Mega Man X - Enemies 2", 150,13,.16f,5,50,348,50,50,300));
        getEnemies().add(new NormalEnemy(getWorld(), this, "SNES - Mega Man X - Enemies 1", 150,18f,.16f,4,50,1050,50,60,250));
        getEnemies().add(new NormalEnemy(getWorld(), this, "SNES - Mega Man X - Enemies 1", 100,20.5f,.16f,4,50,1050,50,60,200));
        getEnemies().add(new StrongEnemy(getWorld(), this, "SNES - Mega Man X - Enemies 2", 175,23.5f,.16f,5,50,348,50,50,350));
        getEnemies().add(new StrongEnemy(getWorld(), this, "SNES - Mega Man X - Enemies 2", 150,28,.16f,5,50,348,50,50,300));
        getEnemies().add(new Boss(getWorld(),this,"SNES - Mega Man X - Enemies 1",400,31.5f,.16f,2000));
    }
}

