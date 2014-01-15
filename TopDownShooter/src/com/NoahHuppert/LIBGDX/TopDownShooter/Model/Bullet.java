package com.NoahHuppert.LIBGDX.TopDownShooter.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Bullet extends MoveableEntity {

	public static float SPEED = 15;
	private int bulletDamage = 1;
	private int specialBulletDamage = 2;
			
	public Bullet(float SPEED, float rotation, float width, float height, Vector2 position, Vector2 velocity, boolean isEnemyBulletSwitch, boolean isSpecialEnemyBullet, int health) {
		super(SPEED, rotation, width, height, position, isEnemyBulletSwitch, isSpecialEnemyBullet, 0);
		this.velocity = velocity;
	}
	
	
	public void pUpdate(Player player){
		position.add(velocity.tmp().mul(Gdx.graphics.getDeltaTime() * SPEED));
		rotation = velocity.angle() - 90;
		super.update(player);
	}
	
	public void eUpdate(Enemy enemy){
		position.add(velocity.tmp().mul(Gdx.graphics.getDeltaTime() * SPEED));
		rotation = velocity.angle() - 90;
		super.update(enemy);
	}

	public int getBulletDamage(){
		return this.bulletDamage;
	}
	
	public void setBulletDamage(int bulletDamageSet){
		this.bulletDamage = bulletDamageSet;
	}
	
	public int getSpecialBulletDamage(){
		return this.specialBulletDamage;
	}
	
	public void setSpecialBulletDamage(int specialBulletDamageSet){
		this.specialBulletDamage = specialBulletDamageSet;
	}
	
}
