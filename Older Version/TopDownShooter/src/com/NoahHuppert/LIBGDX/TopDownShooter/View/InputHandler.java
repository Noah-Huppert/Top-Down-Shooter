package com.NoahHuppert.LIBGDX.TopDownShooter.View;

import com.NoahHuppert.LIBGDX.TopDownShooter.Model.Bullet;
import com.NoahHuppert.LIBGDX.TopDownShooter.Model.Player;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;


public class InputHandler implements InputProcessor {
	
	World world;
	Player player;
	Vector3 touch = new Vector3();
	Vector2 vec2Touch = new Vector2();
	
	public InputHandler(World world)
	{
		this.world = world;
	}
	

	@Override
	public boolean keyDown(int keycode) {
		player = world.getPlayer();
		switch(keycode){
		case Keys.W:
			player.getVelocity().y = 1;
			break;
		case Keys.S:
			player.getVelocity().y = -1;
			break;
			
		case Keys.A:
			player.getVelocity().x = -1;
			break;
		
		case Keys.D:
			player.getVelocity().x = 1;
			break;
		default:
			break;
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		player = world.getPlayer();
		switch(keycode){
		case Keys.W:
			if(player.getVelocity().y==1)
			player.getVelocity().y = 0;
			break;
		case Keys.S:
			if(player.getVelocity().y==-1)
			player.getVelocity().y = 0;
			break;
			
		case Keys.A:
			if(player.getVelocity().x==-1)
			player.getVelocity().x = 0;
			break;
		
		case Keys.D:
			if(player.getVelocity().x==1)
			player.getVelocity().x =0;
			break;
		default:
			break;
		}
		return true;
		

		
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		touch.set(screenX, screenY, 0);
		world.getRenderer().getCamera().unproject(touch);
		vec2Touch.set(touch.x, touch.y);
		player = world.getPlayer();
		world.addBullet( new Bullet(Bullet.SPEED, 0 , 1, 1, new Vector2(player.getPosition().x, player.getPosition().y), new Vector2(vec2Touch.sub(player.getPosition()).nor())));
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
