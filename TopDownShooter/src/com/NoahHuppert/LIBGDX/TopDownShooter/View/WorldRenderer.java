package com.NoahHuppert.LIBGDX.TopDownShooter.View;

import java.util.Iterator;

import com.NoahHuppert.LIBGDX.TopDownShooter.TopDownShooter;
import com.NoahHuppert.LIBGDX.TopDownShooter.View.World;
import com.NoahHuppert.LIBGDX.TopDownShooter.Model.Bullet;
import com.NoahHuppert.LIBGDX.TopDownShooter.Model.CombatLine;
import com.NoahHuppert.LIBGDX.TopDownShooter.Model.Enemy;
import com.NoahHuppert.LIBGDX.TopDownShooter.Model.MachineGun;
import com.NoahHuppert.LIBGDX.TopDownShooter.Model.Pistol;
import com.NoahHuppert.LIBGDX.TopDownShooter.Model.RetreatPosition;
import com.NoahHuppert.LIBGDX.TopDownShooter.Model.Player;
import com.NoahHuppert.LIBGDX.TopDownShooter.Model.ShotGun;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;




public class WorldRenderer {
	World world;
	InputHandler ih;
	SpriteBatch batch;
	Player player;
	CombatLine combatLine;
	RetreatPosition retreatPosition;
	
	ShotGun shotGun;
	Pistol pistol;
	MachineGun machineGun;
	
	OrthographicCamera cam;
	Texture playerTexture, playerTextureLeft, playerTextureRight, henchManTexture, henchManTextureLeft, henchManTextureRight, bulletTexture, 
	enemyBulletTexture, teamMateTexture, teamMateTextureLeft, teamMateTextureRight, textureHealthBar, TextureHealthBar1, TextureHealthBar2, 
	TextureHealthBar3, TextureHealthBar4, TextureHealthBar5, TextureHealthBar6, TextureHealthBar7, TextureHealthBar8, TextureHealthBar9, 
	TextureHealthBar10, TextureSprintBarFrame, TextureSprintBar1, TextureSprintBar2, TextureSprintBar3, TextureSprintBar4, TextureSprintBar5,
	TextureShotGunIcon, TexturePistolIcon, TextureMachineGunIcon, TextureMacheteIcon, TextureFireAxeIcon, TextureWeaponSelect;
	public Texture combatLineTexture;
	Texture retreatPositionTexture;
	BitmapFont font;
	float width, height;
	ShapeRenderer sr;
	
	Array<Bullet> bullets = new Array<Bullet>();
    //Array<EnemyBullet> enemyBullets = new Array<EnemyBullet>();
    Array<Enemy> enemies = new Array<Enemy>();
    Iterator<Bullet> bIter;
    //Iterator<EnemyBullet> ebIter;
    Iterator<Enemy> eIter;
    Bullet b;
   // EnemyBullet eb;
    Enemy e;
    
	String playerFoot = "left";
	int playerWalkingClock = 0;
	String henchManFoot = "left";
	int henchManWalkingClock = 0;
	public static float combatLineTextureWidth;
	public static float combatLineTextureHeight;
	

    public Vector2 combatLinePosition;
    private Stage stage;
	
    
	
	public WorldRenderer(World world){
		this.world = world;
		//---------------Setting Up Scene 2D-----------------\\
		stage = new Stage();
        //Gdx.input.setInputProcessor(stage);//If active then all player controll is lost

		
        
        
		//---------------Setting Up Scale---------------\\
		width = (Gdx.graphics.getWidth() / 30);
		height = (Gdx.graphics.getHeight() / 30);
		
		//---------------Setting Up Camera---------------\\
		cam = new OrthographicCamera();
		cam.setToOrtho(false, width, height);
		cam.update();
		
		//---------------Setting Up Sprite Batches----------\\
		batch = new SpriteBatch();
		batch.setProjectionMatrix(cam.combined);
		
		//***********************************************************Defining Textures*****************************************************\\
		
		//---------------Defining Team Mate Textures----------------\\
		teamMateTexture = new Texture("data/goodGuyStandingCut.png");
		teamMateTextureLeft = new Texture("data/goodGuyLeftCut.png");
		teamMateTextureRight = new Texture("data/goodGuyRightCut.png");
	
		//---------------Defining Textures for AI Entities------------\\
		combatLineTexture = new Texture("data/combatLine.png");
		retreatPositionTexture = new Texture("data/retreatPosition.png");
		
		//---------------Defining Player Textures----------------------\\
		playerTexture = new Texture("data/standingCut.png");
		playerTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		playerTextureLeft = new Texture("data/leftCut.png");
		playerTextureLeft.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		playerTextureRight = new Texture("data/rightCut.png");
		playerTextureRight.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		//---------------Defining Bullet Textures----------------------\\
		bulletTexture = new Texture("data/bullet64.png");
		enemyBulletTexture = new Texture("data/bullet64.png");
		
		//---------------Defining Hench Man Textures-------------------\\
		henchManTexture = new Texture("data/badGuyStandingCut.png");
		henchManTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		henchManTextureRight = new Texture("data/badGuyRightCut.png");
		henchManTextureRight.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		henchManTextureLeft = new Texture("data/badGuyLeftCut.png");
		henchManTextureLeft.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		//---------------Defining Health Bar Textures----------------------\\
		textureHealthBar = new Texture("data/healthBarFrame.png");
		textureHealthBar.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		TextureHealthBar1 = new Texture("data/healthBarFrame1.png");
		TextureHealthBar1.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		TextureHealthBar2 = new Texture("data/healthBarFrame2.png");
		TextureHealthBar2.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		TextureHealthBar3 = new Texture("data/healthBarFrame3.png");
		TextureHealthBar3.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		TextureHealthBar4 = new Texture("data/healthBarFrame4.png");
		TextureHealthBar4.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		TextureHealthBar5 = new Texture("data/healthBarFrame5.png");
		TextureHealthBar5.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		TextureHealthBar6 = new Texture("data/healthBarFrame6.png");
		TextureHealthBar6.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		TextureHealthBar7 = new Texture("data/healthBarFrame7.png");
		TextureHealthBar7.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		TextureHealthBar8 = new Texture("data/healthBarFrame8.png");
		TextureHealthBar8.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		TextureHealthBar9 = new Texture("data/healthBarFrame9.png");
		TextureHealthBar9.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		TextureHealthBar10 = new Texture("data/healthBarFrame10.png");
		TextureHealthBar10.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		//---------------Defining Sprint Bar Textures----------------------\\
		
		TextureSprintBarFrame = new Texture("data/staminaBarFrame.png");
		TextureSprintBarFrame.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		TextureSprintBar1 = new Texture("data/staminaBar1.png");
		TextureSprintBar1.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		TextureSprintBar2 = new Texture("data/staminaBar2.png");
		TextureSprintBar2.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		TextureSprintBar3 = new Texture("data/staminaBar3.png");
		TextureSprintBar3.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		TextureSprintBar4 = new Texture("data/staminaBar4.png");
		TextureSprintBar4.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		TextureSprintBar5 = new Texture("data/staminaBar5.png");
		TextureSprintBar5.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		//---------------Defining Sprint Bar Textures----------------------\\
		
		TextureShotGunIcon = new Texture("data/shotgun64.png");
		TextureShotGunIcon.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		TexturePistolIcon = new Texture("data/pistol64.png");
		TexturePistolIcon.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		TextureMachineGunIcon = new Texture("data/machineGun64.png");
		TextureMachineGunIcon.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		TextureMacheteIcon = new Texture("data/machete64.png");
		TextureMacheteIcon.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		TextureFireAxeIcon = new Texture("data/fireAxe64.png");
		TextureFireAxeIcon.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		TextureWeaponSelect = new Texture("data/weaponSelect64.png");
		TextureWeaponSelect.setFilter(TextureFilter.Linear, TextureFilter.Linear);
			
		//********************************************************************************************************************************\\
				
		sr = new  ShapeRenderer();//Setting up shape renderer
		sr.setColor(Color.CYAN);//Setting hit box outline color to cyan
		
		//---------------For Making Enemy Go To Center Of Combat Line\\
		combatLineTextureWidth = combatLineTexture.getWidth();
		combatLineTextureHeight = combatLineTexture.getHeight();
		
	}
	
	@SuppressWarnings("static-access")
	public void render(){
		Gdx.gl.glClearColor(1, 1, 1, 1);//Sets background color
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);//Clears screen so backround color change takes place
	
		
		world.setRenderer(this);
		
		
		
		//----------------Gets Data------------\\
		player = world.getPlayer();
		enemies = world.geEnemies();
		bullets = world.getBullets();
		shotGun = world.getShotGun();
		pistol = world.getPistol();
		machineGun = world.getMachineGun();
		ih = world.getInputHandler();
		
		//---------------Sets Up Iterators--------------\\
    	//ebIter = enemyBullets.iterator();
    	bIter = bullets.iterator();
    	eIter = enemies.iterator();
		
    	//---------------Sets Up Camera Stuff---------------\\
		cam.position.set(player.getPosition().x, player.getPosition().y, 0);//Makes camera follow player
		cam.update();//Updates camera
		batch.setProjectionMatrix(cam.combined);
		
		//***********************************************************Rendering***********************************************************\\
		batch.begin();//Starts rendering

		//Player Walk Cycle
		if(player.getVelocity().x!=0f || player.getVelocity().y!=0f){//Checks to see ifd player is moveing
			playerWalkingClock = playerWalkingClock +1;//Adds 1 to the timer so player switches feet
			if("left".equals(playerFoot)){
				batch.draw(playerTextureLeft, player.getPosition().x, player.getPosition().y, player.getWidth() / 2, player.getHeight() / 2, player.getWidth(), player.getHeight(), 1, 1, player.getRotation(), 0, 0, playerTextureLeft.getWidth(), playerTextureLeft.getHeight(), false, false);
				if(playerWalkingClock==10){
					playerFoot = "right";//Sets to opposite foot
					playerWalkingClock = 0;//Resets walking timer
					
					
				}
			}
			if("right".equals(playerFoot)){
				batch.draw(playerTextureRight, player.getPosition().x, player.getPosition().y, player.getWidth() / 2, player.getHeight() / 2, player.getWidth(), player.getHeight(), 1, 1, player.getRotation(), 0, 0, playerTextureRight.getWidth(), playerTextureLeft.getHeight(), false, false);
				if(playerWalkingClock==10){
					playerFoot = "left";//Sets to opposite foot
					playerWalkingClock = 0;//resets walking timer
				}
			}
		}
		if(player.getVelocity().x==0 && player.getVelocity().y==0){//If player is standing still renders player standing still
			batch.draw(playerTexture, player.getPosition().x, player.getPosition().y, player.getWidth() / 2, player.getHeight() / 2, player.getWidth(), player.getHeight(), 1, 1, player.getRotation(), 0, 0, playerTexture.getWidth(), playerTexture.getHeight(), false, false);
		}
		
		//---------------Renders Enemies---------------\\
		eIter = enemies.iterator();
		while(eIter.hasNext()){
			e = eIter.next();
			batch.draw(henchManTexture, e.getPosition().x, e.getPosition().y, e.getWidth() / 2, e.getHeight() / 2,  e.getWidth(), e.getHeight(), 1, 1, e.getRotation(), 0 , 0, henchManTexture.getWidth(), henchManTexture.getHeight(), false, false);	
		}
		
		//---------------Renders Enemy Bullets---------------\\
		/*ebIter = enemyBullets.iterator();
		while(ebIter.hasNext()){
			eb = ebIter.next();
			batch.draw(enemyBulletTexture, eb.enemyBulletPositionX, eb.enemyBulletPositionY, eb.enemyBulletWidth / 2, eb.enemyBulletHeight / 2,  eb.enemyBulletWidth, eb.enemyBulletHeight, 2, 2, eb.enemyBulletRotation, 0 , 0, enemyBulletTexture.getWidth(), enemyBulletTexture.getHeight(), false, false);
			//batch.draw(enemyBulletTexture, eb.getPosition().x, eb.getPosition().y, eb.getWidth() / 2, eb.getHeight() / 2,  eb.getWidth(), eb.getHeight(), 2, 2, eb.getRotation(), 0 , 0, enemyBulletTexture.getWidth(), enemyBulletTexture.getHeight(), false, false);
		}*/
		
		//---------------Renders Player Bullets---------------\\
		
		
		bIter = bullets.iterator();
		while(bIter.hasNext()){
			b = bIter.next();
			batch.draw(bulletTexture, b.getPosition().x, b.getPosition().y, b.getWidth() / 2, b.getHeight() / 2,  b.getWidth(), b.getHeight(), 2, 2, b.getRotation(), 0 , 0, bulletTexture.getWidth(), bulletTexture.getHeight(), false, false);
			
		}
		
		
		//Renders TeamMate
		//batch.draw(teamMateTexture, world.newTeamMatePosition.X, world.newTeamMatePosition.Y, teamMateTexture.getWidth() / 2, teamMateTexture.getHeight() / 2,  teamMateTexture.getWidth(), teamMateTexture.getHeight(), 1, 1, 0, 0 , 0, teamMateTexture.getWidth(), teamMateTexture.getHeight(), false, false);
		
		
		
		if(TopDownShooter.DEBUG==true){//Renders AI Markers If Debug is True
			//Renders Combat line Marker
			batch.draw(combatLineTexture, world.combatLinePositionX, world.combatLinePositionY, combatLineTexture.getWidth() / 2, combatLineTexture.getHeight() / 2,  combatLineTexture.getWidth(), combatLineTexture.getHeight(), 1, 1, 0, 0 , 0, combatLineTexture.getWidth(), combatLineTexture.getHeight(), false, false);
		
			//Renders Retreat Position Marker
			batch.draw(retreatPositionTexture, world.retreatPositionX, world.retreatPositionY, retreatPositionTexture.getWidth() / 2, retreatPositionTexture.getHeight() / 2,  retreatPositionTexture.getWidth(), retreatPositionTexture.getHeight(), 1, 1, 0, 0 , 0, retreatPositionTexture.getWidth(), retreatPositionTexture.getHeight(), false, false);
		
		}
	
		batch.end();
		
		
		//For Debugging
		
		if(TopDownShooter.DEBUG==true){
			sr.setProjectionMatrix(cam.combined);
			sr.begin(ShapeType.Rectangle);
			sr.setColor(Color.CYAN);
			sr.rect(player.getBounds().x, player.getBounds().y, player.getBounds().width, player.getBounds().height);
			sr.rect(world.combatLinePositionX, world.combatLinePositionY, combatLineTexture.getWidth(), combatLineTexture.getHeight());
			
			//batch.draw(combatLineTexture, combatLinePositionX, combatLinePositionY, player.getWidth() / 2, player.getHeight() / 2, combatLine.getWidth(), combatLine.getHeight(), 1, 1, 1, player.getRotation(), 0, 0, combatLineTexture.getWidth(), combatLineTexture.getHeight(), false, false);
			eIter = enemies.iterator();
			bIter = bullets.iterator();
			//ebIter = enemyBullets.iterator();
			
			
			while(eIter.hasNext()){
				//Gdx.app.log(TopDownShooter.LOG, "Drawing Enemy Bounds");
				e = eIter.next();
				sr.rect(e.getBounds().x, e.getBounds().y, e.getBounds().width, e.getBounds().height);
			}
			
			while(bIter.hasNext()){
				//Gdx.app.log(TopDownShooter.LOG, "Drawing Bullet Bounds");
				b = bIter.next();
				sr.rect(b.getBounds().x, b.getBounds().y, b.getBounds().width, b.getBounds().height);
			
			}
			
			/*while(ebIter.hasNext()){
				//Gdx.app.log(TopDownShooter.LOG, "Drawing EnemyBullet Bounds");
				eb = ebIter.next();
				sr.rect(eb.enemyBulletBoundsX, eb.enemyBulletBoundsY, eb.enemyBulletBoundsWidth, eb.enemyBulletBoundsHeight);
			
			}*/
			

			sr.end();
			
		}
			
			
			//---------------Scene 2D Rendering---------------\\
			//Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	        stage.act(Gdx.graphics.getDeltaTime());
	        stage.draw();

	        Table.drawDebug(stage);
	        Image healthBar = new Image(TextureHealthBar10);
	        Image sprintBar = new Image(TextureSprintBar5);
	        Image weaponSelect = new Image(TextureShotGunIcon);
	        font = new BitmapFont(Gdx.files.internal("data/font.fnt"), false);
	        /*
	         * Right now the weapon select is just a single square showing the weapon selected
	         * Later on I hope to make it a Half Life like weapon select with transparency and a square that goes over selected weapon when scrolling
	         */

	      
	        if(shotGun.getWeaponSelected() == true){
	        	weaponSelect = new Image(TextureShotGunIcon);
	        }
	        
	        if(pistol.getWeaponSelected() == true){
	        	weaponSelect = new Image(TexturePistolIcon);
	        }
	        
	        if(machineGun.getWeaponSelected() == true){
	        	weaponSelect = new Image(TextureMachineGunIcon);
	        }
	        
	        //Add Melee Weapons Later
	        /*if(machete.getWeaponSelected() == true){
	        	//Set Machete Tile
	        }
	        
	        if(fireAxe.getWeaponSelected() == true){
	        	//Set Fire Axe Tile
	        }*/
	        
	        if(world.sprintCounter>0 && world.sprintCounter<=100){
	        	sprintBar = new Image(TextureSprintBar5);
	       	}
	        
	        if(world.sprintCounter>100 && world.sprintCounter<=200){
	        	sprintBar = new Image(TextureSprintBar4);
	       	}
	        
	        if(world.sprintCounter>200 && world.sprintCounter<=300){
	        	sprintBar = new Image(TextureSprintBar3);
	       	}
	        
	        if(world.sprintCounter>300 && world.sprintCounter<=400){
	        	sprintBar = new Image(TextureSprintBar2);
	       	}
	        
	        if(world.sprintCounter>400 && world.sprintCounter<=490){
	        	sprintBar = new Image(TextureSprintBar1);
	       	}
	        
	        if(world.sprintCounter>=491){
	        	sprintBar = new Image(TextureSprintBarFrame);
	       	}
	        
	        
	        
	        switch(player.playerHealth){
	        case 1:healthBar = new Image(TextureHealthBar1);
	        
	        break;
	        case 2:healthBar = new Image(TextureHealthBar2);
	        
	        break;
	        case 3:healthBar = new Image(TextureHealthBar3);
	        
	        break;
	        case 4:healthBar = new Image(TextureHealthBar4);
	        
	        break;
	        case 5:healthBar = new Image(TextureHealthBar5);
	        
	        break;
	        case 6:healthBar = new Image(TextureHealthBar6);
	        
	        break;
	        case 7:healthBar = new Image(TextureHealthBar7);
	        
	        break;
	        case 8:healthBar = new Image(TextureHealthBar8);
	        
	        break;
	        case 9:healthBar = new Image(TextureHealthBar9);
	        
	        break;
	        case 10:healthBar = new Image(TextureHealthBar10);
	        
	        break;
	        }
	        
	        
	        stage.clear();
	        Table healthBarTable = new Table();
	        Table sprintBarTable = new Table();
	        Table weaponSelectTable = new Table();
	        if(TopDownShooter.DEBUG==true){
	        	sprintBarTable.debug();
	        	healthBarTable.debug();
	        	weaponSelectTable.debug();
	        }
	        
	        sprintBarTable.setFillParent(true);
	        healthBarTable.setFillParent(true);
	        weaponSelectTable.setFillParent(true);
	        stage.addActor(healthBarTable);
	        stage.addActor(sprintBarTable);
	        stage.addActor(weaponSelectTable);
	        
	        healthBarTable.left().top();
	        sprintBarTable.left().top();
	        weaponSelectTable.right().bottom();
	        healthBarTable.add().width(TextureHealthBar1.getWidth() / 5).right();
	        sprintBarTable.add().width(TextureHealthBar1.getWidth() / 5).right();
	        
	        healthBarTable.add(healthBar).width((Gdx.graphics.getWidth() / 2) - Gdx.graphics.getWidth() / 10).top();
	        sprintBarTable.add(sprintBar).width(((Gdx.graphics.getWidth() / 3) - Gdx.graphics.getWidth() / 10)).padTop(TextureHealthBar1.getHeight() * 2);
	        weaponSelectTable.add(weaponSelect).width(TextureShotGunIcon.getWidth()).height(TextureShotGunIcon.getHeight()).padBottom((Gdx.graphics.getHeight() - TextureShotGunIcon.getHeight()) / 2);
	        
	        
			//**********************************************************************************************************************\\
		
		
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
		stage.dispose();
		
	}

}
