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

        getEnemies().add(new NormalEnemy(getWorld(), this, this.getAtlas().findRegion("SNES - Mega Man X - Enemies 2"), 25, 1.8f, .16f,2,100,100,50,90, 75) );
        getEnemies().add(new NormalEnemy(getWorld(), this, this.getAtlas().findRegion("SNES - Mega Man X - Enemies 2"), 50, 6, .16f,6,50,195,40,55,100));
        getEnemies().add(new StrongEnemy(getWorld(), this, this.getAtlas().findRegion("SNES - Mega Man X - Enemies 2"), 75, 17.5f, .16f,5,50,56,45,50,200));
        getEnemies().add(new NormalEnemy(getWorld(), this, this.getAtlas().findRegion("SNES - Mega Man X - Enemies 2"), 50,21.5f,.16f,6,50,195,40,55,100));
        getEnemies().add(new NormalEnemy(getWorld(), this, this.getAtlas().findRegion("SNES - Mega Man X - Enemies 2"), 25, 10.5f, .16f,2,100,100,50,90, 75));
        getEnemies().add(new StrongEnemy(getWorld(), this, this.getAtlas().findRegion("SNES - Mega Man X - Enemies 2"), 100,13,.16f,5,50,56,45,50,250));
        getEnemies().add(new Bosses(getWorld(), this, this.getAtlas().findRegion("SNES - Mega Man X - Enemies 1"), 300,25.5f,.16f, 2, 100, 800, 50, 100,1000));

    }
}
