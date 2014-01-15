package com.NoahHuppert.LIBGDX.TopDownShooter.Model;

import com.badlogic.gdx.math.Vector2;
import com.NoahHuppert.LIBGDX.TopDownShooter.Model.Bullet;
import com.NoahHuppert.LIBGDX.TopDownShooter.Model.Player;
import com.NoahHuppert.LIBGDX.TopDownShooter.View.World;

public class HenchMan extends Enemy {
	static World world;

	

        public HenchMan(float SPEED, float rotation, float width, float height,
                        Vector2 position) {
                super(SPEED, rotation, width, height, position, world);
                this.world = world;
        }

        
        public void advance(float delta, Player player, String advanceTo) {
        	if("player".equals(advanceTo)){
        		position.lerp(new Vector2(world.combatLinePositionX, world.combatLinePositionY), delta);
        	}
        	if("combatLine".equals(advanceTo)){
        		position.lerp(player.getPosition(), delta);
        	}
              
              super.update(player);
        }
       
        public void retreat(float delta, Player player) {
        	 position.lerp(new Vector2(world.retreatPositionX, world.retreatPositionY ), delta);
        	 super.update(player);
        }

}
