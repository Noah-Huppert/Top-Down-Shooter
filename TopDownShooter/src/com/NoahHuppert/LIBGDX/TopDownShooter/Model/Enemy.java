package com.NoahHuppert.LIBGDX.TopDownShooter.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.NoahHuppert.LIBGDX.TopDownShooter.Model.Bullet;
import com.NoahHuppert.LIBGDX.TopDownShooter.Model.Player;
import com.NoahHuppert.LIBGDX.TopDownShooter.View.World;
import java.util.Random;

public abstract class Enemy extends MoveableEntity {
	
		private int enemyHealth;
		World world;
		private boolean isSpecialEnemy;
		

        public Enemy(float SPEED, float rotation, float width, float height, Vector2 position, boolean isEnemyBullet, int enemyHealth) {
                super(SPEED, rotation, width, height, position, false, false, enemyHealth);

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
    	public void update(Enemy enemy) {
    			
    	}
   
        public abstract void advance(float delta, Player player, World world, String advanceTo, int X, int Y);

		public void retreat(float deltaTime, Player player2) {
			
		}

        public int getEnemyHealth(){
        	return this.enemyHealth;
        }
        
        public void setEnemyHealth(int enemyHealthSet){
        	this.enemyHealth = enemyHealthSet;
        }
        
        public boolean getIsSpecialEnemy(){
        	return this.isSpecialEnemy;
        }
        
}