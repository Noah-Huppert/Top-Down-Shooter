package com.NoahHuppert.LIBGDX.TopDownShooter.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;


public class Player extends MoveableEntity{
	public int playerHealth = 10;

	public Player(Vector2 position, float width, float height, float rotation, float SPEED){
		super(SPEED, rotation, width, height, position);
		
		
		
	}

	
	public void update() {
		position.add(velocity.tmp().mul(Gdx.graphics.getDeltaTime() * SPEED));
		if(velocity.x != 0 || velocity.y !=0){
		rotation = velocity.angle() - 90;
		
		bounds.x = position.x;
		bounds.y = position.y;
		}
		

		
	}


	@Override
	public void update(Player player) {
		
		
	}

}
