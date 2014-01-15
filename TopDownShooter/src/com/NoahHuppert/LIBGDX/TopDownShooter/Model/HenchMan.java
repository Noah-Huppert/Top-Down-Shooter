package com.NoahHuppert.LIBGDX.TopDownShooter.Model;

import com.badlogic.gdx.math.Vector2;
import com.NoahHuppert.LIBGDX.TopDownShooter.Model.Bullet;
import com.NoahHuppert.LIBGDX.TopDownShooter.Model.Player;
import com.NoahHuppert.LIBGDX.TopDownShooter.View.World;

public class HenchMan extends Enemy {
	World world;
	private int enemyHealth;
	private boolean isSpecialEnemy = false;
	

        public HenchMan(float SPEED, float rotation, float width, float height,
                        Vector2 position, boolean isEnemyBullet, int enemyHealth) {
                super(SPEED, rotation, width, height, position, false, enemyHealth);
                //this.world = world;
        }

        
        public void advance(float delta, Player player, World world, String advanceTo, int X, int Y) {
        	this.world= world;
        	if("combatLine".equals(advanceTo)){
        		position.lerp(CombatLine.combatLinePositionVector, delta);
        		//System.out.println(CombatLine.combatLinePositionVector);
        	}
        	
        	if("player".equals(advanceTo)){
        		position.lerp(player.getPosition(), delta);
        	}
        	
        	if("retreatPosition".equals(advanceTo)){
        		position.lerp(new Vector2(World.retreatPositionX, world.retreatPositionY ), delta);
        	}
        	
        	if("custom".equals(advanceTo)){
        		position.lerp(new Vector2(X, Y), delta);
        	}
              
              super.update(player);
        }
       
        
        public void retreat(float delta, Player player) {
        	 position.lerp(new Vector2(world.retreatPositionX, world.retreatPositionY ), delta);
        	 super.update(player);
        	 
        }

        public boolean getIsSpecialEnemy(){
        	return this.isSpecialEnemy;
        }
        
}

