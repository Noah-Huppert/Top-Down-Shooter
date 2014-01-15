package com.NoahHuppert.LIBGDX.TopDownShooter.Screens;

import com.NoahHuppert.LIBGDX.TopDownShooter.TopDownShooter;
import com.NoahHuppert.LIBGDX.TopDownShooter.View.World;
import com.NoahHuppert.LIBGDX.TopDownShooter.View.WorldRenderer;
import com.badlogic.gdx.Screen;

public class GameScreen implements Screen{

	TopDownShooter game;
	World world;
	WorldRenderer render;
	
	public GameScreen(TopDownShooter game){
		this.game = game;
		world = new World(game);
		render = new WorldRenderer(world);
		
	}
	
	
	@Override
	public void render(float delta) {
		world.update();
		render.render();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		dispose();
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		world.dispose();
		
	}
	

}
