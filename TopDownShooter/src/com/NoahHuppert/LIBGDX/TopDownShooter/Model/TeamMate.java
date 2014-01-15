package com.NoahHuppert.LIBGDX.TopDownShooter.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class TeamMate extends MoveableEntity{
	Player player;
	private int teamMateHealth = 6;

	public TeamMate(float SPEED, float rotation, float width, float height, Vector2 position, int teamMateHealth) {
		super(SPEED, rotation, width, height, position, false, false, teamMateHealth);
		// TODO Auto-generated constructor stub
	}
	
	public void update() {
		
		}
	
	public void follow(){
		position.lerp(new Vector2(player.getPosition()), Gdx.graphics.getDeltaTime());
	}



	/*@Override
	public void update(TeamMate teamMate) {
		
		
	}*/

	public static void add(TeamMate teamMate) {
		// TODO Auto-generated method stub
		
	}

}


