package com.NoahHuppert.LIBGDX.TopDownShooter.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class EnemyBullet extends MoveableEntity {

	
	public static float SPEED = 15;
			
			
	public EnemyBullet(float SPEED, float rotation, float width, float height, Vector2 position, Vector2 velocity) {
		super(SPEED, rotation, width, height, position);
		this.velocity = velocity;
	}
	
	@Override
	public void update(Player player){
		position.add(velocity.tmp().mul(Gdx.graphics.getDeltaTime() * SPEED));
		rotation = velocity.angle() - 90;
		super.update(player);
	}

}
