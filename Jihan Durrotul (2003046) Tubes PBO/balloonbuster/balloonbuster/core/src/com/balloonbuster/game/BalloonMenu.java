package com.balloonbuster.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Align;

public class BalloonMenu extends BaseScreen {

    //property
    private BaseActor bg;
    private BaseActor title;
    private Label text;


    //constructor
    public BalloonMenu(Game game) {
        super(game);
    }


    //membuat blackground dan title
    @Override
    protected void create() {
        bg = ActorManger.createActor(mainStage, "bg ji.png", viewWidth / 2, viewHeight / 2);
        title = ActorManger.createActor(mainStage, "tulisan.png", viewWidth / 2, viewHeight / 2);

        BitmapFont font = new BitmapFont();
        LabelStyle style = new LabelStyle(font, Color.NAVY);

        text = new Label("Mulai!!!", style);
        text.setPosition(viewWidth / 2 - text.getWidth() / 2, 100);
        text.setFontScale(2);
        text.setAlignment(Align.center);

        uiStage.addActor(text);
    }


    @Override
    protected void update(float dt) {

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        game.setScreen(new BalloonLevel(game));

        return false;
    }
}
