package com.NoahHuppert.LIBGDX.TopDownShooter.Model;

import com.NoahHuppert.LIBGDX.TopDownShooter.View.InputHandler;
import com.NoahHuppert.LIBGDX.TopDownShooter.View.World;
import com.badlogic.gdx.math.Vector2;

public class Weapons{

/*
	World world;
	Player player;
	InputHandler inputHandler;
	
	public int shotGunAmmo;
	public int pistolAmmo;
	public int machineGunAmmo;
	
	public int shotGunClipSize;
	public int pistolClipSize;
	public int machineGunClipSize;
	
	public int shotGunDamage;
	public int pistolDamage;
	public int machineGunDamage;
	public int fireAxeDamage;
	public int macheteDamage;
	
	public int shotGunFireRate;
	public int pistolFireRate;
	public int machineGunFireRate;
	public int fireAxeFireRate;
	public int macheteFireRate;
	
	public float shotGunLastFired;
	public float pistolLastFired;
	public float machineGunLastFired;
	public float fireAxeLastFired;
	public float macheteLastFired;
	
	public boolean weaponReadyToUse;
	
	public int selectedWeapon;

	/*
	 * Shot Gun = 1
	 * Pistol = 2
	 * Machine Gun = 3
	 * Fire Axe = 4
	 * Machete = 5
	 */
	
	
	/*
	public void useWeapon(){
		
		switch(selectedWeapon){
			case 1://Shot Gun
				if(weaponReadyToUse(1)==true){				
					//world.addBullet( new Bullet(Bullet.SPEED, 0 , 1, 1, new Vector2(player.getPosition().x, player.getPosition().y), new Vector2(inputHandler.vec2Touch.sub(player.getPosition()).nor()), false));
	
					shotGunAmmo = shotGunAmmo - 1;
					shotGunLastFired = 0;
				}
			break;
			
			case 2://Pistol
				if(weaponReadyToUse(2)==true){
					//Fire
					pistolAmmo = pistolAmmo -1;
					pistolLastFired = 0;
				}
			break;
			
			case 3://Machine Gun
				if(weaponReadyToUse(3)==true){
					//Fire
					machineGunAmmo = machineGunAmmo - 1;
					machineGunLastFired = 0;
				}
			break;
			
			case 4://Fire Axe
				if(weaponReadyToUse(4)==true){
					//Fire
					fireAxeLastFired = 0;
				}
			break;
			
			case 5://Machete
				if(weaponReadyToUse(5)==true){
					//Fire
					macheteLastFired = 0;
				}
			break;
		
		}
		
	}
	
	public boolean weaponReadyToUse(int weapon){
		switch(weapon){
		case 1://Shot Gun
			if(shotGunLastFired <= shotGunFireRate){
				if(shotGunAmmo > 0){
					weaponReadyToUse = true;
				}
			}
			else{
				weaponReadyToUse = false;
			}
		break;
		
		case 2://Pistol
			if(pistolLastFired <= pistolFireRate){
				if(pistolAmmo > 0){
					weaponReadyToUse = true;
				}
			}
			else{
				weaponReadyToUse = false;
			}
		break;
		
		case 3://Machine Gun
			if(machineGunLastFired <= machineGunFireRate){
				if(machineGunAmmo > 0){
					weaponReadyToUse = true;
				}
			}
			else{
				weaponReadyToUse = false;
			}
		break;
		
		case 4://Fire Axe
			if(fireAxeLastFired <= fireAxeFireRate){
				weaponReadyToUse = true;
			}
			else{
				weaponReadyToUse = false;
			}
		break;
		
		case 5://Machete
			if(macheteLastFired <= macheteFireRate){
				weaponReadyToUse = true;
			}
			else{
				weaponReadyToUse = false;
			}
		break;
	}
		return weaponReadyToUse;
	}
		
	public void setSelectedWeapon(int setSelectedWeapon){
		selectedWeapon = setSelectedWeapon;
	}
	
	public void updateWeapons(){
		shotGunLastFired = shotGunLastFired + 1;
		pistolLastFired = pistolLastFired + 1;
		machineGunLastFired = machineGunLastFired + 1;
		fireAxeLastFired = fireAxeLastFired + 1;
		macheteLastFired = macheteLastFired +1;
		weaponReadyToUse(selectedWeapon);
	}

	public void startWeapons(){//Initalize all weapons so there are no nullPointerExeptions
		shotGunAmmo = 15;
		pistolAmmo = 36;
		machineGunAmmo = 135;
		
		shotGunClipSize = 5;
		pistolClipSize = 12;
		machineGunClipSize = 45;
		
		shotGunDamage = 4;
		pistolDamage = 1;
		machineGunDamage = 1;
		fireAxeDamage = 6;
		macheteDamage = 2;
		
		shotGunFireRate = 500;
		pistolFireRate = 0;
		machineGunFireRate = 0;
		fireAxeFireRate = 4000;
		macheteFireRate = 1500;
		
		shotGunLastFired = 0;
		pistolLastFired = 0;
		machineGunLastFired = 0;
		fireAxeLastFired = 0;
		macheteLastFired = 0;
		
		weaponReadyToUse = false;
		
		selectedWeapon = 5;
	}
	
*/
}