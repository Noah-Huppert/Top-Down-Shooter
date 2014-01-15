package com.NoahHuppert.LIBGDX.TopDownShooter.Model;

import com.badlogic.gdx.math.Vector2;

public class RetreatPosition extends MoveableEntity{
	public static Vector2 retreatPositionVector;
	public RetreatPosition(float SPEED, float rotation, float width,
			float height, Vector2 position, int health) {
		super(SPEED, rotation, width, height, position, false, false, 0);
		// TODO Auto-generated constructor stub
	}

}
