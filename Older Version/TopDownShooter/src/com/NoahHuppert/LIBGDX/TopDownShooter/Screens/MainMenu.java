package com.NoahHuppert.LIBGDX.TopDownShooter.Screens;

import com.NoahHuppert.LIBGDX.TopDownShooter.TopDownShooter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;



public class MainMenu implements Screen{
	
	TopDownShooter game;
	Stage stage;
	BitmapFont font;
	BitmapFont white;
	public TextureAtlas atlas;
	public Skin skin;
	SpriteBatch batch;
	TextButton button;
	//Label label;
	Image image;
	Sprite logo;
	Texture logoTexture;
	
	public MainMenu(TopDownShooter game){
		this.game = game;
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		stage.act(delta);
		batch.begin();
		
		stage.draw();
		//white.draw(batch,  "Top Down Shooter", Gdx.graphics.getWidth() / 2 - 15, Gdx.graphics.getHeight() / 2 + 100);
		  
		batch.end();
		
	}

	@Override
	public void resize(int width, int height) {
		if(stage==null){
			stage = new Stage(width, height, true);
			stage.clear();
			Gdx.input.setInputProcessor(stage);
		}
		
		TextButtonStyle style = new TextButtonStyle();
		style.up = skin.getDrawable("NotPressed");
		style.down = skin.getDrawable("Pressed");
		style.font = font;
		
		button = new TextButton("Start Game", style);
		button.setWidth(400);
		button.setHeight(100);
		button.setX(Gdx.graphics.getWidth() / 2 - button.getWidth() / 2);
		button.setY(Gdx.graphics.getHeight() / 2 - button.getHeight()  / 2);
		
		button.addListener(new InputListener(){
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button){
				System.out.println("Down");
				return true;
			}
			
			public void touchUp (InputEvent event, float x, float y, int pointer , int button){
				game.setScreen(new GameScreen(game));
			}
		});
		
		Image image = new Image(logoTexture);
		image.setWidth(Gdx.graphics.getWidth() / 2 - image.getWidth() / 2);
		image.setX(Gdx.graphics.getWidth() / 2  - image.getWidth() / 2);
		image.setY(Gdx.graphics.getHeight() / 2 + image.getHeight() * 2);
		
		//image.setAlign(Align.center);
		
		/*LabelStyle ls = new LabelStyle(white, Color.WHITE);
		label = new Label("Top Down Shooter", ls);
		label.setX(0);
		label.setY(Gdx.graphics.getHeight() / 2 + 100);
		label.setWidth(width);
		label.setAlignment(Align.center);*/
		
		stage.addActor(button);
		stage.addActor(image);
		//stage.addActor(label);
		
		
	}

	@Override
	public void show() {
		batch = new SpriteBatch();
		atlas = new TextureAtlas("data/inGameAssets.pack");
		skin = new Skin();
		skin.addRegions(atlas);
		font = new BitmapFont(Gdx.files.internal("data/font.fnt"), false);
		white = new BitmapFont(Gdx.files.internal("data/white.fnt"), false);
		
		logoTexture = new Texture("data/logov5.png");
		logoTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		logo = new Sprite(logoTexture);
		logo.setColor(1, 1, 1, 0);
		logo.setX(Gdx.graphics.getWidth() / 2 - logo.getWidth() / 2);
		logo.setY(Gdx.graphics.getHeight() / 2 - logo.getHeight() / 2);
		
		
		
	}

	@Override
	public void hide() {
		dispose();
		
	}

	@Override
	public void pause() {
		
		
	}

	@Override
	public void resume() {
		
		
	}

	@Override
	public void dispose() {
		batch.dispose();
		skin.dispose();
		atlas.dispose();
		font.dispose();
		stage.dispose();
		
		
		
	}

}
