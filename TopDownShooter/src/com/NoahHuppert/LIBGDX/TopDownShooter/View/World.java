package com.NoahHuppert.LIBGDX.TopDownShooter.View;

import java.util.Iterator;

import com.NoahHuppert.LIBGDX.TopDownShooter.TopDownShooter;
import com.NoahHuppert.LIBGDX.TopDownShooter.Model.Bullet;
import com.NoahHuppert.LIBGDX.TopDownShooter.Model.CombatLine;
import com.NoahHuppert.LIBGDX.TopDownShooter.Model.Enemy;
import com.NoahHuppert.LIBGDX.TopDownShooter.Model.HenchMan;
import com.NoahHuppert.LIBGDX.TopDownShooter.Model.Juggernaut;
import com.NoahHuppert.LIBGDX.TopDownShooter.Model.MachineGun;
import com.NoahHuppert.LIBGDX.TopDownShooter.Model.Pistol;
import com.NoahHuppert.LIBGDX.TopDownShooter.Model.Player;
import com.NoahHuppert.LIBGDX.TopDownShooter.Model.ShotGun;
import com.NoahHuppert.LIBGDX.TopDownShooter.Model.TeamMate;
import com.NoahHuppert.LIBGDX.TopDownShooter.Model.Weapon;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

//import com.NoahHuppert.LIBGDX.TopDownShooter.Model.Enemy;

public class World {

	World world;
	HenchMan henchMan;
	TopDownShooter game;
	Player player;
	CombatLine combatLine;
	Enemy enemy;
	
	Weapon weapon;
	ShotGun shotGun;
	Pistol pistol;
	MachineGun machineGun;

	Array<Bullet> bullets = new Array<Bullet>();
	Array<Enemy> enemies = new Array<Enemy>();
	WorldRenderer wr;
	InputHandler ih;
	Iterator<Bullet> bIter;
	Iterator<Enemy> eIter;
	Bullet b;
	public Enemy e;
	Vector2 newEnemyPosition;
	Vector2 newTeamMatePosition;
	int enemyBulletTimer = 0;
	float enemySpeed = 0;
	float teamMateSpeed = 0;
	public int combatLinePositionX;
	public int combatLinePositionY;
	public static int retreatPositionX;
	public static int retreatPositionY;
	boolean enemyHit = false;
	boolean playerHit = false;
	public boolean enemyInWorld;
	public boolean enemyMoveing = false;
	public int sprintCounter = 0;
	public boolean sprinting = false;
	public boolean sprintCalled = false;
	public int playerSpeed = 1;;
	public boolean sprintCalledState = false;
	public boolean lastSprintCalledState = false;
	public int shotGunDataArray[] = new int[5];
	private boolean specialBullet;
	private boolean hitCalculated = false;

	public World(TopDownShooter game) {
		this.game = game;
		Gdx.input.setInputProcessor(new InputHandler(this));

		// ---------------For Adding Entities--------------\\
		player = new Player(new Vector2(-5, -5), 1, 1, 0, 3f, 10);// Adds player at
		//addHenchMan(2f, new Vector2(0, 0));
		addJuggerNaut(2f, new Vector2(2, 0));
		addCombatLine(10, 10);// Adds combatLine at (10, 10)
		addRetreatPosition(20, 20);// Adds retreatPosition at (20, 20)
		shotGun = new ShotGun(25, 5, 4, 50, 0, true);
		pistol = new Pistol(36, 12, 1, 15, 0, false);
		machineGun = new MachineGun(90, 45, 1, 0, 0, false);
		
		
		
		if(TopDownShooter.DEBUG == true){
			System.out.println(TopDownShooter.changeLog);
		}
		
	}
	
	public void update() {
		bIter = bullets.iterator();
		eIter = enemies.iterator();

		
		
		enemyInWorld();

		player.update();
		shootPlayer(100, true);
		enemyAdvance("player", true, 0, 0);

		updateWeapons();
		sprintUpdate();
		bulletUpdate();
		playerHitChecker();
		enemyHitChecker();

	}
	
	public Player getPlayer() {
		return player;
	}

	public ShotGun getShotGun(){
		return shotGun;
	}
	
	public Pistol getPistol(){
		return pistol;
	}
	
	public MachineGun getMachineGun(){
		return machineGun;
	}
	
	public InputHandler getInputHandler(){
		return ih;
	}
	
	public Array<Enemy> geEnemies() {
		return enemies;
	}

	public void addTeamMate(int X, int Y) {
		TeamMate.add(new TeamMate(teamMateSpeed, 0, 1, 1, new Vector2(X, Y), 6));
	}

	public boolean enemyInWorld() {
		eIter = enemies.iterator();
		if (eIter.hasNext() == true) {
			// Gdx.app.log(TopDownShooter.LOG, "Enemy In World");
			e = eIter.next();
			enemyInWorld = true;
			return enemyInWorld;
		}
		if (eIter.hasNext() == false) {
			// Gdx.app.log(TopDownShooter.LOG, "Enemy Not In World");
			enemyInWorld = false;
			return enemyInWorld;
		}

		return enemyInWorld;
	}

	public void setRenderer(WorldRenderer wr) {
		this.wr = wr;
	}

	public void bulletUpdate() {
		bIter = bullets.iterator();

		while (bIter.hasNext()) {
			b = bIter.next();
			b.pUpdate(player);
			b.eUpdate(enemy);
		}
	}

	public void sprintUpdate() {

		if (sprintCalled == true) {
			if (sprintCounter < 499) {
				sprinting = true;
				playerSpeed = 1;

				sprintCounter = sprintCounter + 1;
			}

			if (sprintCounter >= 500) {
				sprinting = false;
				playerSpeed = 1;

				sprintCounter = sprintCounter - 1;
			}
		}

		if (sprintCalled == false && sprintCounter > 0) {
			sprinting = false;
			playerSpeed = 1;

			sprintCounter = sprintCounter - 1;
		}

		lastSprintCalledState = sprintCalledState;
		sprintCalledState = sprinting;

		if (lastSprintCalledState == false && sprintCalledState == true
				&& sprinting == true && sprintCounter != 499) {
			switch (((int) player.getVelocity().x)) {
			case 1:
				player.getVelocity().x = 2;
				break;
			case -1:
				player.getVelocity().x = -2;
				break;
			case 0:
				break;
			}

			switch (((int) player.getVelocity().y)) {
			case 1:
				player.getVelocity().y = 2;
				break;
			case -1:
				player.getVelocity().y = -2;
				break;
			case 0:
				break;
			}

		}

		if (lastSprintCalledState == true && sprintCalledState == false
				&& sprinting == false || sprintCounter == 499) {
			player.getVelocity().y = 0;
			player.getVelocity().x = 0;
		}

		// System.out.println("sprintCalled: " + sprintCalled + "		sprinting: "
		// + sprinting + "		sprintCounter: " + sprintCounter +
		// "	sprintCalledState: " + sprintCalledState +
		// "	lastSprintCalledState: " + lastSprintCalledState +
		// "	Player Velocity: " + new Vector2(player.getVelocity().x =
		// player.getVelocity().x * playerSpeed, player.getVelocity().y =
		// player.getVelocity().y * playerSpeed));
	}

	public boolean playerHitChecker() {
		playerHit = false;
		eIter = enemies.iterator();
		bIter = bullets.iterator();
		while (bIter.hasNext()) {
			b = bIter.next();
			if (player.getBounds().overlaps(b.getBounds())
					&& b.getBulletSide() == true) {
				playerHit = true;
				bIter.remove();
				player.playerHealth = player.playerHealth - b.getBulletDamage();
				if (TopDownShooter.DEBUG == true) {

				}
				if (player.playerHealth == 0) {

				}
			} else {
				playerHit = false;
			}
		}
		return playerHit;
	}

	public boolean enemyHitChecker() {
		enemyHit = false;
		bIter = bullets.iterator();
		while (bIter.hasNext()) {
			b = bIter.next();
			eIter = enemies.iterator();
			while (eIter.hasNext()) {
				e = eIter.next();
				if (e.getBounds().overlaps(b.getBounds()) && b.getBulletSide() == false) {
					enemyHit = true;
					
					/*if(e.getEnemyHealth() == 1 || (e.getEnemyHealth() -1) == 0 && hitCalculated == false){
						e.setEnemyHealth(e.getEnemyHealth() - 1);
						eIter.remove();
						bIter.remove();
						hitCalculated =  true;
						System.out.println("1!");
					}
					
					if(e.getEnemyHealth() != 1 && (e.getEnemyHealth() - 1) != 0 && hitCalculated == false){
						e.setEnemyHealth(e.getEnemyHealth() - 1);
						bIter.remove();
						hitCalculated = true;
						System.out.println("2!");
					}*/
					System.out.println("Enemy Hit Checker Starting\n" + "Start Enemy Health: " + e.getEnemyHealth());
					bIter.remove();
					System.out.println("Bullet Removed");
					e.setEnemyHealth(e.getEnemyHealth() - 1);
					System.out.println("Enemy Damage Dealt");
					
					if(e.getEnemyHealth() == 0){
						eIter.remove();
						System.out.println("Enemy Removed");
					}
					
					System.out.println("Enemy Hit Checker Ending\n" + "End Enemy Health: " + e.getEnemyHealth());
					
					if (TopDownShooter.DEBUG == true) {
						Gdx.app.log(TopDownShooter.LOG, "Enemy Hit");
						System.out.println("Enemy Health: " + e.getEnemyHealth());
					}
					hitCalculated = false;
				} else {
					enemyHit = false;
				}
			}
		}
		return enemyHit;
	}

	public WorldRenderer getRenderer() {
		return wr;
	}

	public void addBullet(Bullet b) {
		bullets.add(b);
	}

	public Array<Bullet> getBullets() {
		return bullets;
	}

	public void addHenchMan(float enemySpeed, Vector2 newEnemyPosition) {
		enemies.add(new HenchMan(enemySpeed, 0, 1, 1, newEnemyPosition, false, 2));
	}

	public void addJuggerNaut(float enemySpeed, Vector2 newEnemyPosition){
		enemies.add(new Juggernaut(enemySpeed, 0, 1, 1, newEnemyPosition, false, 4));
	}
	
	public void addCombatLine(int X, int Y) {
		combatLinePositionX = X;
		combatLinePositionY = Y;
		CombatLine.combatLinePositionVector = new Vector2(combatLinePositionX
				+ wr.combatLineTextureWidth / 2, combatLinePositionY
				+ wr.combatLineTextureWidth / 2);

	}

	public void addRetreatPosition(int X, int Y) {
		retreatPositionX = X;
		retreatPositionY = Y;
	}

	public void shootPlayer(int frequency, boolean checkForEnemy) {
		if (checkForEnemy == true) {
			if (enemyInWorld == true) {
				enemyBulletTimer = enemyBulletTimer + 1;
				eIter = enemies.iterator();
				bIter = bullets.iterator();

				if (eIter.hasNext() && enemyBulletTimer == frequency) {

					e = eIter.next();

					if(e.getIsSpecialEnemy() ==  true){
						specialBullet = true;
					}
					
					if(e.getIsSpecialEnemy() == false){
						specialBullet = false;
					}
					
					addBullet(new Bullet(Bullet.SPEED, 0, 1, 1, new Vector2(e.getPosition().x, e.getPosition().y), new Vector2(new Vector2(player.getPosition().x, player.getPosition().y).sub(e.getPosition()).nor()), true, specialBullet, 0));

					b = bIter.next();

					enemyBulletTimer = 0;
				}
			}
		}

		if (checkForEnemy == false) {
			enemyBulletTimer = enemyBulletTimer + 1;
			eIter = enemies.iterator();
			if (eIter.hasNext() == true && enemyBulletTimer == frequency) {
				eIter.next();
				bIter.next();
				
				if(e.getIsSpecialEnemy() ==  true){
					specialBullet = true;
				}
				
				if(e.getIsSpecialEnemy() == false){
					specialBullet = false;
				}
				
				addBullet(new Bullet(Bullet.SPEED, 0, 1, 1, new Vector2(e.getPosition().x, e.getPosition().y), new Vector2(new Vector2(player.getPosition().x,player.getPosition().y).sub(e.getPosition()).nor()), true, specialBullet, 0));
				enemyBulletTimer = 0;
			}
		}
	}

	public void enemyAdvance(String advanceTo, boolean checkForEnemy, int X,
			int Y) {
		eIter = enemies.iterator();

		if (checkForEnemy == true) {
			if (enemyInWorld == true) {
				// e = eIter.next();
				if (TopDownShooter.DEBUG == true) {
					// Gdx.app.log(TopDownShooter.LOG,
					// "enemyAdvance Called, enemyInWorld checker: Enabled");
				}

				while (eIter.hasNext()) {
					e = eIter.next();
					e.advance(Gdx.graphics.getDeltaTime(), player, world,
							advanceTo, X, Y);
					enemyMoveing = true;
				}
			}
			if (enemyInWorld == false) {
				if (TopDownShooter.DEBUG == true) {
					// Gdx.app.log(TopDownShooter.LOG,
					// "No enemy in world, can not call enemy.Advance()");
				}
				enemyMoveing = false;
			}

		}
		if (checkForEnemy == false) {

			if (TopDownShooter.DEBUG == true) {
				// Gdx.app.log(TopDownShooter.LOG,
				// "enemyAdvance Called, enemyInWorld checker: Disabled");
			}

			while (eIter.hasNext()) {
				e = eIter.next();
				e.advance(Gdx.graphics.getDeltaTime(), player, world,
						advanceTo, X, Y);
				enemyMoveing = true;
			}
		}
	}
	
	public void updateWeapons(){
		shotGun.updateLastFired();
		pistol.updateLastFired();
		machineGun.updateLastFired();
	}
	
	public void dispose() {

	}
}
