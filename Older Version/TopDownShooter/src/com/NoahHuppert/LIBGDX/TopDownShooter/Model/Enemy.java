package com.NoahHuppert.LIBGDX.TopDownShooter.Model;

import com.badlogic.gdx.math.Vector2;
import com.NoahHuppert.LIBGDX.TopDownShooter.Model.Bullet;
import com.NoahHuppert.LIBGDX.TopDownShooter.Model.Player;
import com.NoahHuppert.LIBGDX.TopDownShooter.View.World;
import java.util.Random;

public abstract class Enemy extends MoveableEntity {
	World world;
	Enemy enemy;
	Player player;
	public Vector2 enemyDistanceFromPlayer;

        public Enemy(float SPEED, float rotation, float width, float height, Vector2 position, World world) {
                super(SPEED, rotation, width, height, position);
                this.world = world;
                
               // enemyDistanceFromPlayer = new Vector2(enemy.getPosition().x - player.getPosition().x, enemy.getPosition().y - player.getPosition().y);
               //aa System.out.println(enemyDistanceFromPlayer);
        }
        
         
        
        public abstract void advance(float delta, Player player, String advanceTo);



		public void retreat(float deltaTime, Player player2) {
			// TODO Auto-generated method stub
			
		}
        
}