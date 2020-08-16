package com.megaman.game.Screen;

import com.badlogic.gdx.Screen;
import com.megaman.game.MegamanGame;
//Screen represents one of many application screens, such as a main menu, a Levels menu, the Play screen and end Level menu.
public abstract class screen  implements Screen {
    protected MegamanGame game;

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }
//Called when this screen is no longer the current screen for a Game.
    @Override
    public void hide() {

    }
//Called when this screen should release all resources.
    @Override
    public void dispose() {

    }
}
