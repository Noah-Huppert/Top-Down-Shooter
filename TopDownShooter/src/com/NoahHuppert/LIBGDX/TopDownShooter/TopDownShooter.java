package com.NoahHuppert.LIBGDX.TopDownShooter;

//import java.awt.SplashScreen;

import com.NoahHuppert.LIBGDX.TopDownShooter.Screens.GameScreen;
import com.NoahHuppert.LIBGDX.TopDownShooter.Screens.SplashScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.FPSLogger;

public class TopDownShooter extends Game {
	
	public static final String VERSION = "0.0.05 Pre-Alpha";
	public static final String LOG = "Top Down Shooter";
	public static final boolean DEBUG = false;
	public static final String changeLog = 
			"0.0.01 - Game Created\n" +
			"0.0.02 - Bullets And Enemies Added\n" +
			"0.0.03 - Health Bar For Player Is Added\n" +
			"0.0.04 - Sprinting Is Added\n" +
			"0.0.05 - Different Weapons Are Added With On Screen Weapon Indicator\n" +
			"2216 Lines of code. Last Counted 3/16/2013\n";
	//2216 Lines of code. Last Counted 3/16/2013

	
	@Override
	public void create() {	

		//splash screen taken out for testing purposes and replaced with main menu screen
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
