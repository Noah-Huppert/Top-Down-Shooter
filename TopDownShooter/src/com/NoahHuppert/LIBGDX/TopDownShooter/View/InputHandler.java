package com.NoahHuppert.LIBGDX.TopDownShooter.View;

import com.NoahHuppert.LIBGDX.TopDownShooter.Model.Bullet;
import com.NoahHuppert.LIBGDX.TopDownShooter.Model.Enemy;
import com.NoahHuppert.LIBGDX.TopDownShooter.Model.MachineGun;
import com.NoahHuppert.LIBGDX.TopDownShooter.Model.Pistol;
import com.NoahHuppert.LIBGDX.TopDownShooter.Model.Player;
import com.NoahHuppert.LIBGDX.TopDownShooter.Model.ShotGun;
import com.NoahHuppert.LIBGDX.TopDownShooter.Model.Weapon;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class InputHandler implements InputProcessor {
	
	World world;
	Player player;
	Enemy enemy;
	
	Weapon weapon;
	ShotGun shotGun;
	Pistol pistol;
	MachineGun machineGun;
	
	Vector3 touch = new Vector3();
	public Vector2 vec2Touch = new Vector2();
	private int selectedWeapon = 1;
	
	

	
	public InputHandler(World world)
	{
		this.world = world;

	}


	
	@Override
	public boolean keyDown(int keycode) {

		player = world.getPlayer();
		


			switch(keycode){
			case Keys.W:
				player.getVelocity().y = 1;
			break;
			case Keys.S:
				player.getVelocity().y = -1;
			break;
				
			case Keys.A:
				player.getVelocity().x = -1;
			break;
			
			case Keys.D:
				player.getVelocity().x = 1;
			break;
			
			case Keys.SHIFT_LEFT:
				world.sprintCalled = true;
			break;
			default:
				break;
			}
		
		
	
		
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		player = world.getPlayer();
		
			switch(keycode){
			case Keys.W:
				if(player.getVelocity().y==1)
				player.getVelocity().y = 0;
			break;
			case Keys.S:
				if(player.getVelocity().y==-1)
				player.getVelocity().y = 0;
			break;
			
			case Keys.A:
				if(player.getVelocity().x==-1)
				player.getVelocity().x = 0;
			break;
		
			case Keys.D:
				if(player.getVelocity().x==1)
				player.getVelocity().x =0;
			break;
		
			case Keys.SHIFT_LEFT:
				world.sprintCalled = false;
			default:
			break;
			}
		
		
		
	
		return true;
		

		
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		shotGun = world.getShotGun();
		pistol = world.getPistol();
		machineGun = world.getMachineGun();
		
		player = world.getPlayer();
		if(new Vector2(vec2Touch.sub(player.getPosition()).nor()) != null){//Checks to make sure bullet will not have 0 velocity
			
			switch(selectedWeapon){
			case 1://If ShotGun Is Selected
				if(shotGun.getAmmo() > 0 && shotGun.getWeaponLastFired() > shotGun.getFireRate()){
					touch.set(screenX, screenY, 0);
	                world.getRenderer().getCamera().unproject(touch);
	                vec2Touch.set(touch.x, touch.y);
	                
					world.addBullet(new Bullet(Bullet.SPEED, 0 , 1, 1, new Vector2(player.getPosition().x, player.getPosition().y), new Vector2(vec2Touch.sub(player.getPosition()).nor()), false, false, 0));
					shotGun.setLastFired(0);
					shotGun.setAmmo(shotGun.getAmmo() - 1);
					
					System.out.println(shotGun.getAmmo() + "	" + selectedWeapon);
				}
			break;
			
			case 2://If Pistol Is Selected
				if(pistol.getAmmo() > 0 && pistol.getWeaponLastFired() > pistol.getFireRate()){
					touch.set(screenX, screenY, 0);
	                world.getRenderer().getCamera().unproject(touch);
	                vec2Touch.set(touch.x, touch.y);
	                
					world.addBullet( new Bullet(Bullet.SPEED, 0 , 1, 1, new Vector2(player.getPosition().x, player.getPosition().y), new Vector2(vec2Touch.sub(player.getPosition()).nor()), false, false, 0));
					pistol.setLastFired(0);
					pistol.setAmmo(pistol.getAmmo() - 1);
					
					System.out.println(pistol.getAmmo() + "	" + selectedWeapon);
				}
			break;
			
			case 3://Machine Gun Is Selected
				if(machineGun.getAmmo() > 0 && machineGun.getWeaponLastFired() > machineGun.getFireRate()){
					touch.set(screenX, screenY, 0);
	                world.getRenderer().getCamera().unproject(touch);
	                vec2Touch.set(touch.x, touch.y);
	                
					world.addBullet( new Bullet(Bullet.SPEED, 0 , 1, 1, new Vector2(player.getPosition().x, player.getPosition().y), new Vector2(vec2Touch.sub(player.getPosition()).nor()), false, false, 0));
					machineGun.setAmmo(machineGun.getAmmo() - 1);
					
					System.out.println(machineGun.getAmmo() + "	" + selectedWeapon);
				}
			break;
			
			case 4:
				//Add Melee Weapons In Later
			break;
			
			case 5:
				//Add Melee Weapons In Later
			break;
			}
			
		}
		
		
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		shotGun = world.getShotGun();
		pistol = world.getPistol();
		machineGun = world.getMachineGun();
		
		player = world.getPlayer();

		if(selectedWeapon + (amount * -1) != 0 && (selectedWeapon  + (amount * -1)) != 6){
			selectedWeapon = selectedWeapon + (amount * -1);
			System.out.println(selectedWeapon);
		}
		
		switch(selectedWeapon){
		case 1:
			shotGun.setWeaponSelected(true);
			pistol.setWeaponSelected(false);
			machineGun.setWeaponSelected(false);
			//machete.setWeaponSelected(false);
			//fireAxe.setWeaponSelected(false);
		break;
		
		case 2:
			pistol.setWeaponSelected(true);
			shotGun.setWeaponSelected(false);
			machineGun.setWeaponSelected(false);
			//machete.setWeaponSelected(false);
			//fireAxe.setWeaponSelected(false);
		break;
		
		case 3:
			machineGun.setWeaponSelected(true);
			pistol.setWeaponSelected(false);
			shotGun.setWeaponSelected(false);
			//machete.setWeaponSelected(false);
			//fireAxe.setWeaponSelected(false);
		break;
		
		case 4:
			//machete.setWeaponSelected(true);
			pistol.setWeaponSelected(false);
			machineGun.setWeaponSelected(false);
			//fireAxe.setWeaponSelected(false);
		break;
		
		case 5:
			//fireAxe.setWeaponSelected(true);
			pistol.setWeaponSelected(false);
			machineGun.setWeaponSelected(false);
			//machete.setWeaponSelected(false);
		break;
		}
		
		
		return false;
	}
	

	
}
