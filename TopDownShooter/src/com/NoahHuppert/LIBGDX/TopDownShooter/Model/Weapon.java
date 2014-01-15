package com.NoahHuppert.LIBGDX.TopDownShooter.Model;

import com.NoahHuppert.LIBGDX.TopDownShooter.View.InputHandler;
import com.NoahHuppert.LIBGDX.TopDownShooter.View.World;
import com.badlogic.gdx.math.Vector2;

public abstract class Weapon{



	World world;
	Player player;
	InputHandler inputHandler;
	
	

	/*
	 * Shot Gun = 1
	 * Pistol = 2
	 * Machine Gun = 3
	 * Fire Axe = 4
	 * Machete = 5
	 */
	
	protected int weaponAmmo;
	protected int weaponClipSize;
	protected int weaponDamage;
	protected int weaponFireRate;
	protected int weaponLastFired;
	protected boolean weaponSelected;
	//public int selectedWeapon = 5;//Commented out for now because this variable should only be used in the Input Handler
	

	public Weapon(int weaponAmmo, int weaponClipSize, int weaponDamage, int weaponFireRate, int weaponLastFired, boolean weaponSelected){
		this.weaponAmmo = weaponAmmo;
		this.weaponClipSize = weaponClipSize;
		this.weaponDamage = weaponDamage;
		this.weaponFireRate = weaponFireRate;
		this.weaponLastFired = weaponLastFired;
		
	}
	//Update Methods
	public void updateLastFired(){
		this.weaponLastFired ++;
	}
	//\\
	
	//Getters
	public boolean getWeaponSelected(){
		return this.weaponSelected;
	}
	
	public int getAmmo(){
		return this.weaponAmmo;
	}
	
	public int getClipSize(){
		return this.weaponClipSize;
	}
	
	public int getDamage(){
		return weaponDamage;
	}
	
	public int getFireRate(){
		return this.weaponFireRate;
	}
	
	public int getLastFired(){
		return this.weaponLastFired;
	}
	
	/*public int getSelectedWeapon(){
		return selectedWeapon;
	}*/
	
	public int getWeaponLastFired(){
		return this.weaponLastFired;
	}
	//\\
	
	//Setters
	public void setWeaponSelected(boolean weaponSelected){
		this.weaponSelected = weaponSelected;
	}
	
	public void setAmmo(int ammoSet){
		this.weaponAmmo = ammoSet;
	}
	
	public void setClipSize(int clipSizeSet){
		this.weaponClipSize = clipSizeSet;
	}
	
	public void setDamage(int damageSet){
		this.weaponDamage = damageSet;
	}
	
	public void setFireRate(int fireRateSet){
		this.weaponFireRate = fireRateSet;
	}
	
	public void setLastFired(int lastFiredSet){
		this.weaponLastFired = lastFiredSet;
	}
	
	/*public void setSelectedWeapon(int selectedWeaponSet){
		selectedWeapon = selectedWeaponSet;
	}*/
	//\\
}
