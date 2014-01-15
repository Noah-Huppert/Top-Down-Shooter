package com.NoahHuppert.LIBGDX.TopDownShooter;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Top Down Shooter " + TopDownShooter.VERSION;
		cfg.useGL20 = true;
		cfg.width = 1080;
		cfg.height = 720;
		
		new LwjglApplication(new TopDownShooter(), cfg);
	}
}
