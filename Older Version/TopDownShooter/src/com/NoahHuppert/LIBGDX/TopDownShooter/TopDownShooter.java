package com.NoahHuppert.LIBGDX.TopDownShooter;

//import java.awt.SplashScreen;

import com.NoahHuppert.LIBGDX.TopDownShooter.Screens.GameScreen;
import com.NoahHuppert.LIBGDX.TopDownShooter.Screens.SplashScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.FPSLogger;

public class TopDownShooter extends Game {
	
	public static final String VERSION = "0.0.02 Pre-Alfa";
	public static final String LOG = "To Down Shooter";
	public static final boolean DEBUG = true;

	
	
	@Override
	public void create() {	

		//splash screen taken out for testing purposes and replaced with main menue screen
		if(DEBUG==false){
			setScreen(new SplashScreen(this));
		}
		if(DEBUG==true){
			setScreen(new GameScreen(this));
		}
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void render() {		
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
			super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}
}
