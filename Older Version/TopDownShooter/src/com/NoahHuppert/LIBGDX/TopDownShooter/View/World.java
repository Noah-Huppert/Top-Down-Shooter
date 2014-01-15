package com.NoahHuppert.LIBGDX.TopDownShooter.View;



import java.util.Iterator;

import com.NoahHuppert.LIBGDX.TopDownShooter.TopDownShooter;
import com.NoahHuppert.LIBGDX.TopDownShooter.Model.Bullet;
import com.NoahHuppert.LIBGDX.TopDownShooter.Model.CombatLine;
import com.NoahHuppert.LIBGDX.TopDownShooter.Model.Enemy;
import com.NoahHuppert.LIBGDX.TopDownShooter.Model.EnemyBullet;
//import com.NoahHuppert.LIBGDX.TopDownShooter.Model.Enemy;
import com.NoahHuppert.LIBGDX.TopDownShooter.Model.HenchMan;
import com.NoahHuppert.LIBGDX.TopDownShooter.Model.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class World {
	
	  TopDownShooter game;
      Player player;
      CombatLine combatLine;
      Array<EnemyBullet> enemyBullets = new Array<EnemyBullet>();
      Array<Bullet> bullets = new Array<Bullet>();
      Array<Enemy> enemies = new Array<Enemy>();
      WorldRenderer wr;
      Iterator<EnemyBullet> ebIter;
      Iterator<Bullet> bIter;
      Iterator<Enemy> eIter;
      Bullet b;
      EnemyBullet eb;
      Enemy e;
      int enemyBulletTimer = 0;
      float enemySpeed = 0;
      Vector2 newEnemyPosition;
      public int combatLinePositionX;
      public int combatLinePositionY;
      public int retreatPositionX;
      public int retreatPositionY;
      boolean enemyHit = false;
      boolean playerHit = false;


    	
	public World(TopDownShooter game){
		this.game = game;
		player = new Player(new Vector2(5, 5), 1, 1, 0, 3f);
		Gdx.input.setInputProcessor(new InputHandler(this));
		
		
		addHenchMan(2f, new Vector2(0,0));
		//addHenchMan(2f, new Vector2(10, 10));
		}
	
	public Player getPlayer(){
		return player;
	}		
	
	public Array<Enemy> geEnemies(){
		return enemies;
	}
	
    public void update(){
    	ebIter = enemyBullets.iterator();
    	bIter = bullets.iterator();
    	eIter = enemies.iterator();
    	
        player.update();
        //shootPlayer(100);
        enemyToCombatLine("combatLine");
        
       // e.advance(Gdx.graphics.getDeltaTime(), player, "combatLine");
       // e.retreat(Gdx.graphics.getDeltaTime(), player);
        addCombatLine(10, 10);
        addRetreatPosition(20, 20);
        bulletUpdate();
        playerHitChecker();
        enemyHitChecker();
        System.out.println(new Vector2(combatLinePositionX, combatLinePositionY));

}

	public void setRenderer(WorldRenderer wr){
		this.wr = wr;
	}
	
	public void bulletUpdate(){
        bIter = bullets.iterator();
       while(bIter.hasNext()){
               b = bIter.next();
               b.update(player);
               
       }
	}
	
	public boolean playerHitChecker(){
		playerHit = false;
        eIter = enemies.iterator();
        while(bIter.hasNext()){
        	b = bIter.next(); 
            if(player.getBounds().overlaps(b.getBounds())){
            	playerHit = true;
            	bIter.remove();
                player.playerHealth = player.playerHealth - 1;
                if(TopDownShooter.DEBUG==true){
                	Gdx.app.log(TopDownShooter.LOG, "Player hit");
                	System.out.println("Player Health: " + player.playerHealth);
                }
            }
            else{
            	playerHit = false;
            }
        }
        return playerHit;
	}
	
	public boolean enemyHitChecker(){
		enemyHit = false;
		bIter = bullets.iterator();
        while(bIter.hasNext()){
        	b = bIter.next();                       
            eIter = enemies.iterator();
            while(eIter.hasNext()){
            	e = eIter.next();
            	if(e.getBounds().overlaps(b.getBounds())){
            		enemyHit = true;
                    //eIter.remove();
                    //bIter.remove();
                    if(TopDownShooter.DEBUG==true){
                    	Gdx.app.log(TopDownShooter.LOG, "Enemy Hit");
                    }
            	}
                else{
                	enemyHit = false;
                }
            }
        }
        return enemyHit;        
	}
	
	public WorldRenderer getRenderer(){
		return wr;
	}
	
	public void addBullet(Bullet b){
		bullets.add(b);
	}
	
	public void addEnemyBullet(EnemyBullet eb){
		enemyBullets.add(eb);
	}
	
	public Array<Bullet> getBullets(){
		return bullets;
	}
	
	public Array<EnemyBullet> getEnemyBullets(){
		return enemyBullets;
	}
	
 	public void addHenchMan(float enemySpeed, Vector2 newEnemyPosition){
		enemies.add(new HenchMan(enemySpeed, 0, 1, 1, newEnemyPosition));
	}

	public void addCombatLine(int X, int Y){
		combatLinePositionX = X;
		combatLinePositionY = Y;
	}
	
	public void addRetreatPosition(int X, int Y){
		retreatPositionX = X;
		retreatPositionY = Y;
	}
	
	public void shootPlayer(int frequency){
		enemyBulletTimer = enemyBulletTimer + 1;
		 eIter = enemies.iterator();
		 ebIter = enemyBullets.iterator();
	        while(ebIter.hasNext() && enemyBulletTimer == frequency){
	                eb = ebIter.next();
	                enemyBulletTimer = 0;
	                addEnemyBullet(new EnemyBullet(EnemyBullet.SPEED, 0 , 1, 1, new Vector2(e.getPosition().x , e.getPosition().y), new Vector2(player.getPosition().x, player.getPosition().y).nor()));
	        }
	}
	
	public void enemyToCombatLine(String advanceTo){
		eIter = enemies.iterator();
		e = eIter.next();
		if(TopDownShooter.DEBUG==true){
			//Gdx.app.log(TopDownShooter.LOG, "enemyToCombatLine Called");
		}
		
		while(eIter.hasNext()){
			e.advance(Gdx.graphics.getDeltaTime(), player, advanceTo);
		}
	}

	public void dispose(){
		
	}
}
