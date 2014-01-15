package com.NoahHuppert.LIBGDX.TopDownShooter.Model;

import com.badlogic.gdx.math.Vector2;

public abstract class MoveableEntity extends Entity {

	protected Vector2 velocity;
	protected float SPEED;
	protected float rotation;
	private boolean isEnemyBullet;
	
	public MoveableEntity(float SPEED, float rotation, float width, float height, Vector2 position, boolean isEnemyBullet, boolean isSpecialEnemyBullet, int health){
		super(position, width, height, isEnemyBullet);
		this.SPEED = SPEED;
		this.rotation = rotation;
		this.isEnemyBullet = isEnemyBullet;
		velocity = new Vector2(0, 0);
		
	}
	
	
	
	public Vector2 getVelocity(){
		return velocity;
		
	}
	
	public void setVolocity(Vector2 velocity){
		this.velocity = velocity;
	}
	
	public float getRotation(){
		return rotation;
	}
	
	public boolean getBulletSide(){
		return isEnemyBullet;
	}
	
	public void setRoation(float rotation){
		this.rotation = rotation;
	}
	
	public void update(Player player){
		bounds.x = position.x;
		bounds.y = position.y;
	}
	
	public void update(Enemy enemy) {
		bounds.x = position.x;
		bounds.y = position.y;
		
	}
	


	
	
}

