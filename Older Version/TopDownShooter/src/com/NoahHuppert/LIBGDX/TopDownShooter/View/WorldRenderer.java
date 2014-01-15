package com.NoahHuppert.LIBGDX.TopDownShooter.View;

import java.util.Iterator;

import com.NoahHuppert.LIBGDX.TopDownShooter.TopDownShooter;
import com.NoahHuppert.LIBGDX.TopDownShooter.View.World;
import com.NoahHuppert.LIBGDX.TopDownShooter.Model.Bullet;
import com.NoahHuppert.LIBGDX.TopDownShooter.Model.CombatLine;
import com.NoahHuppert.LIBGDX.TopDownShooter.Model.Enemy;
import com.NoahHuppert.LIBGDX.TopDownShooter.Model.EnemyBullet;
import com.NoahHuppert.LIBGDX.TopDownShooter.Model.RetreatPosition;
//import com.NoahHuppert.LIBGDX.TopDownShooter.Model.Enemy;
import com.NoahHuppert.LIBGDX.TopDownShooter.Model.HenchMan;
import com.NoahHuppert.LIBGDX.TopDownShooter.Model.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;


public class WorldRenderer {
	World world;
	SpriteBatch batch;
	Player player;
	CombatLine combatLine;
	RetreatPosition retreatPosition;
	OrthographicCamera cam;
	Texture playerTexture, playerTextureLeft, playerTextureRight, henchManTexture, henchManTextureLeft, henchManTextureRight, bulletTexture, enemyBulletTexture, combatLineTexture, retreatPositionTexture;
	float width, height;
	ShapeRenderer sr;
	Array<Bullet> bullets;
	Array<EnemyBullet> enemyBullets;
	Array<Enemy> enemies;
	Iterator<Bullet> bIter;
	Iterator<EnemyBullet> ebIter;
    Iterator<Enemy> eIter;
    Bullet b;
    EnemyBullet eb;
    Enemy e;
	String playerFoot = "left";
	int playerWalkingClock = 0;
	String henchManFoot = "left";
	int henchManWalkingClock = 0;
	

    public Vector2 combatLinePosition;

	
	
	public WorldRenderer(World world){
		
		width = (Gdx.graphics.getWidth() / 30);
		height = (Gdx.graphics.getHeight() / 30);
		
		this.world = world;
		
		cam = new OrthographicCamera();
		cam.setToOrtho(false, width, height);
		cam.update();
		
		batch = new SpriteBatch();
		batch.setProjectionMatrix(cam.combined);
	
		combatLineTexture = new Texture("data/combatLine.png");
		
		retreatPositionTexture = new Texture("data/retreatPosition.png");
		
		playerTexture = new Texture("data/standingCut.png");
		playerTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		playerTextureLeft = new Texture("data/leftCut.png");
		playerTextureLeft.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		playerTextureRight = new Texture("data/rightCut.png");
		playerTextureRight.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		
		
		bulletTexture = new Texture("data/bullet64.png");
		enemyBulletTexture = new Texture("data/bullet64.png");
		
		henchManTexture = new Texture("data/badGuyStandingCut.png");
		henchManTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		henchManTextureRight = new Texture("data/badGuyRightCut.png");
		henchManTextureRight.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		henchManTextureLeft = new Texture("data/badGuyLeftCut.png");
		henchManTextureLeft.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		sr = new  ShapeRenderer();
		sr.setColor(Color.CYAN);
	}
	
	@SuppressWarnings("static-access")
	public void render(){
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		world.setRenderer(this);
		
		
		
		player = world.getPlayer();
		enemies = world.geEnemies();
		enemyBullets = world.getEnemyBullets();
		bullets = world.getBullets();
		
		cam.position.set(player.getPosition().x, player.getPosition().y, 0);
		cam.update();
		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		//batch.draw(setPlayerTexture, player.getPosition().x, player.getPosition().y, player.getWidth() / 2, player.getHeight() / 2, player.getWidth(), player.getHeight(), 1, 1, player.getRotation(), 0, 0, playerTexture.getWidth(), playerTexture.getHeight(), false, false);
		
		//Player Walk Cycle
		if(playerWalkingClock==10){
			playerWalkingClock = 0;
		}

		if(player.getVelocity().x!=0 || player.getVelocity().y!=0){
			playerWalkingClock = playerWalkingClock +1;
			if("left".equals(playerFoot)){
				batch.draw(playerTextureLeft, player.getPosition().x, player.getPosition().y, player.getWidth() / 2, player.getHeight() / 2, player.getWidth(), player.getHeight(), 1, 1, player.getRotation(), 0, 0, playerTextureLeft.getWidth(), playerTextureLeft.getHeight(), false, false);
				if(playerWalkingClock==10){
					playerFoot = "right";
					playerWalkingClock = 0;
					
					
				}
			}
			if("right".equals(playerFoot)){
				batch.draw(playerTextureRight, player.getPosition().x, player.getPosition().y, player.getWidth() / 2, player.getHeight() / 2, player.getWidth(), player.getHeight(), 1, 1, player.getRotation(), 0, 0, playerTextureRight.getWidth(), playerTextureLeft.getHeight(), false, false);
				if(playerWalkingClock==10){
					playerFoot = "left";
					playerWalkingClock = 0;
				}
			}
		}
		if(player.getVelocity().x==0 || player.getVelocity().y==0){
			batch.draw(playerTexture, player.getPosition().x, player.getPosition().y, player.getWidth() / 2, player.getHeight() / 2, player.getWidth(), player.getHeight(), 1, 1, player.getRotation(), 0, 0, playerTexture.getWidth(), playerTexture.getHeight(), false, false);
		}
		
		
		
		//Hench Man Walk Cycle
		/*hIter = henchMen.iterator();
		
		while(hIter.hasNext()){
			if(henchManWalkingClock==10){
				henchManWalkingClock = 0;
			}
			if(h.getVelocity().x !=0 || h.getVelocity().y != 0){//h.getVelocity().x!=0 || h.getVelocity().y!=0
				henchManWalkingClock = henchManWalkingClock +1;
				if("left".equals(henchManFoot)){
					batch.draw(henchManTextureLeft, h.getPosition().x, h.getPosition().y, h.getWidth() / 2, h.getHeight() / 2, h.getWidth(), h.getHeight(), 1, 1, h.getRotation(), 0, 0, henchManTextureLeft.getWidth(), henchManTextureLeft.getHeight(), false, false);
					if(henchManWalkingClock==10){
						henchManFoot = "right";
						henchManWalkingClock = 0;
					}
				}
				if("right".equals(henchManFoot)){
					batch.draw(henchManTextureRight, h.getPosition().x, h.getPosition().y, h.getWidth() / 2, h.getHeight() / 2, h.getWidth(), h.getHeight(), 1, 1, h.getRotation(), 0, 0, henchManTextureRight.getWidth(), henchManTextureLeft.getHeight(), false, false);
					if(henchManWalkingClock==10){
						henchManFoot = "left";
						henchManWalkingClock = 0;
					}
				}
			}
		
			if(h.getVelocity().x==0 && h.getVelocity().y==0){
				batch.draw(henchManTexture, h.getPosition().x, h.getPosition().y, h.getWidth() / 2, h.getHeight() / 2, h.getWidth(), h.getHeight(), 1, 1, h.getRotation(), 0, 0, henchManTexture.getWidth(), henchManTexture.getHeight(), false, false);
			
			}
		}
		*/
		
		
		eIter = enemies.iterator();
		while(eIter.hasNext()){
			e = eIter.next();
			batch.draw(henchManTexture, e.getPosition().x, e.getPosition().y, e.getWidth() / 2, e.getHeight() / 2,  e.getWidth(), e.getHeight(), 1, 1, e.getRotation(), 0 , 0, henchManTexture.getWidth(), henchManTexture.getHeight(), false, false);
			
		}
		
		ebIter = enemyBullets.iterator();
		while(ebIter.hasNext()){
			eb = ebIter.next();
			batch.draw(enemyBulletTexture, eb.getPosition().x, eb.getPosition().y, eb.getWidth() / 2, eb.getHeight() / 2,  eb.getWidth(), eb.getHeight(), 2, 2, eb.getRotation(), 0 , 0, enemyBulletTexture.getWidth(), enemyBulletTexture.getHeight(), false, false);
		}
		
		bIter = bullets.iterator();
		while(bIter.hasNext()){
			b = bIter.next();
			batch.draw(bulletTexture, b.getPosition().x, b.getPosition().y, b.getWidth() / 2, b.getHeight() / 2,  b.getWidth(), b.getHeight(), 2, 2, b.getRotation(), 0 , 0, bulletTexture.getWidth(), bulletTexture.getHeight(), false, false);
			
		}
		//batch.draw(combatLineTexture, world.combatLinePositionX, world.combatLinePositionY);
		batch.draw(combatLineTexture, world.combatLinePositionX, world.combatLinePositionY, combatLineTexture.getWidth() / 2, combatLineTexture.getHeight() / 2,  combatLineTexture.getWidth(), combatLineTexture.getHeight(), 1, 1, 0, 0 , 0, combatLineTexture.getWidth(), combatLineTexture.getHeight(), false, false);
		
		//batch.draw(retreatPositionTexture, world.retreatPositionX, world.retreatPositionY);
		batch.draw(retreatPositionTexture, world.retreatPositionX, world.retreatPositionY, retreatPositionTexture.getWidth() / 2, retreatPositionTexture.getHeight() / 2,  retreatPositionTexture.getWidth(), retreatPositionTexture.getHeight(), 1, 1, 0, 0 , 0, retreatPositionTexture.getWidth(), retreatPositionTexture.getHeight(), false, false);
		
		batch.end();
		
		
		//For Debugging
		
		if(TopDownShooter.DEBUG==true){
			sr.setProjectionMatrix(cam.combined);
			sr.begin(ShapeType.Rectangle);
			sr.setColor(Color.CYAN);
			sr.rect(player.getBounds().x, player.getBounds().y, player.getBounds().width, player.getBounds().height);
			sr.rect(world.combatLinePositionX, world.combatLinePositionY, combatLineTexture.getWidth(), combatLineTexture.getHeight());
			
			//batch.draw(combatLineTexture, combatLinePositionX, combatLinePositionY, player.getWidth() / 2, player.getHeight() / 2, combatLine.getWidth(), combatLine.getHeight(), 1, 1, 1, player.getRotation(), 0, 0, combatLineTexture.getWidth(), combatLineTexture.getHeight(), false, false);
			
		
			while(eIter.hasNext()){
				e = eIter.next();
				sr.rect(e.getBounds().x, e.getBounds().y, e.getBounds().width, e.getBounds().height);
			}
			while(bIter.hasNext()){
				b = bIter.next();
				sr.rect(b.getBounds().x, b.getBounds().y, b.getBounds().width, b.getBounds().height);
			
			}
			
			while(ebIter.hasNext()){
				eb = ebIter.next();
				sr.rect(eb.getBounds().x, eb.getBounds().y, eb.getBounds().width, eb.getBounds().height);
			
			}
		
			sr.end();
		}
		
	}
	
	public OrthographicCamera getCamera(){
		return cam;
	}
	
	public void update(){
		
	}
	
	public void dispose(){
		batch.dispose();
		playerTexture.dispose();
		sr.dispose();
		
	}

}
