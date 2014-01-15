package com.NoahHuppert.LIBGDX.TopDownShooter.Model;

import com.NoahHuppert.LIBGDX.TopDownShooter.View.World;
import com.badlogic.gdx.math.Vector2;

public class CombatLine extends MoveableEntity{
	World world;

	public CombatLine(float SPEED, float rotation, float width, float height,
			Vector2 position) {
		super(SPEED, rotation, width, height, position);
		this.world = world;
	}

	
}
