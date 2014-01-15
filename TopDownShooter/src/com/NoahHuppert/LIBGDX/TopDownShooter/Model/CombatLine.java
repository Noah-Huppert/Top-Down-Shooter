package com.NoahHuppert.LIBGDX.TopDownShooter.Model;

import com.NoahHuppert.LIBGDX.TopDownShooter.View.World;
import com.badlogic.gdx.math.Vector2;

public class CombatLine extends MoveableEntity{
	World world;
	public static Vector2 combatLinePositionVector = new Vector2(0,0);

	public CombatLine(float SPEED, float rotation, float width, float height,
			Vector2 position, int health) {
		super(SPEED, rotation, width, height, position, false, false, 0);
		this.world = world;
	}

	
}
