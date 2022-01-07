package com.balloonbuster.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class BalloonLevel extends BaseScreen {

    //property
    protected final int mapWidth = 1260;
    protected final int mapHeight = 960;

    private BaseActor background;

    private float spawnTimer;
    private float spawnInterval;

    private int popped;
    private int escaped;
    private int clickCount;

    private Label poppedLabel;
    private Label escapedLabel;
    private Label hitRatioLabel;

    private int popToWin = 100;
    private int escapedToFail = 15;

    //constactor
    public BalloonLevel(Game game) {
        super(game);
    }

    //method
    @Override
    protected void create() {

        //membuat sebuah variabel dari sebuah class BaseActor
        background = new BaseActor();

        //kemudian kita masukkan gambar untuk background, yang ada di dalam asset
        background.setTexture(new Texture(Gdx.files.internal("bg ji.png") ));
        background.setOrigin(background.getWidth() / 2, background.getHeight() / 2);
        background.setPosition((viewWidth / 2) - background.getOriginX(),(viewHeight / 2) - background.getOriginY());
        background.setVisible(true);
        mainStage.addActor(background);


        spawnTimer = 0;
        //dan berinterfal 0,5 detik
        spawnInterval = 0.5f;

        //warna font
        BitmapFont font = new BitmapFont();
        LabelStyle style  = new LabelStyle(font, Color.NAVY);

        //mengset poped = 0;
        popped = 0;

        //membuat label dan membuat font
        poppedLabel = new Label("Popped: 0", style);
        poppedLabel.setFontScale(2);
        poppedLabel.setPosition(20, 440);
        uiStage.addActor(poppedLabel);


        escaped = 0;
        escapedLabel = new Label("Escaped: 0", style);
        escapedLabel.setFontScale(2);
        escapedLabel.setPosition(220, 440);
        uiStage.addActor(escapedLabel);


        clickCount = 0;
        hitRatioLabel = new Label("Hit ratio: ---", style);
        hitRatioLabel.setFontScale(2);
        hitRatioLabel.setPosition(420,440);
        uiStage.addActor(hitRatioLabel);
    }


    //method update
    //di method ini kita akan mengupdate pooped
    @Override
    protected void update(float dt) {
        spawnTimer += dt;

        if (spawnTimer > spawnInterval) {

            spawnTimer -= spawnInterval;


            final Balloon b = new Balloon(this);
            b.addListener(


                    new InputListener(){
                        @Override
                        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                            popped++;
                            //dan objek yang diklik akan otomatis terhapus
                            b.remove();
                            return true;
                        }
                    }
            );
            mainStage.addActor(b);
        }


        poppedLabel.setText("Popped: " + popped);
        escapedLabel.setText("Escaped: " + escaped);

        //menghitung ratio klik yang kita punya
        if (clickCount > 0) {
            int percent = (int) (100 * popped / clickCount);
            hitRatioLabel.setText("Hit ratio: " + percent + "%");
        }

        if (popped >= popToWin) {
            game.setScreen(new BalloonMenu(game));
        }

        if (escaped >= escapedToFail) {
            game.setScreen(new BalloonMenu(game));
        }
    }

    public void escapedInc() {
        escaped++;
        clickCount++;
    }
}
