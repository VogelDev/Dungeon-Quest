package com.onequest.dungeon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.onequest.dungeon.coordinates.*;
import com.onequest.dungeon.enemy.*;

/**
 * Center of application, handles controls and drawing to the screen
 * 
 * @author Rob Vogel
 * 
 */
public class World {

	public static Position worldPos = GlobalData.worldStart;

	protected SpriteBatch batch;
	protected boolean debug = false;
	protected int debugMap = 0;
	protected float delta;
	protected BitmapFont font;
	protected boolean isMoving;
	protected Position mapArea = GlobalData.mapArea;
	protected Tile[][] mapFog;
	protected Position mapLoc = GlobalData.mapLoc;
	protected MiniMap miniMap;
	protected ArrayList<RoomObject> obstacles;
	protected Position playArea = GlobalData.playArea;
	protected Vector2 playerMove = new Vector2(0, 0);
	protected Vector2 playerPos = new Vector2(8, 9);
	protected Sprite playerSprite;
	protected float speed;
	protected Teleporter teleporterIn;
	protected Teleporter teleporterOut;
	protected Position tileSize;
	protected float width, height, incWidth, incHeight;
	protected Position worldArea = GlobalData.worldArea;
	protected WorldTile[][] worldMap;
	protected WorldTile worldTile;
	protected ArrayList<EnemyNM> worldBosses;
	protected Vector2 playPos;

	/**
	 * Uses the SpriteBatch from @link com.onequest.dungeon.MainClass
	 * 
	 * @param batch
	 */
	public World(SpriteBatch batch) {
		this.batch = batch;

		init();
	}

	/**
	 * Returns whether the player can move to the destination. Needs to be
	 * updated to handle obstacles
	 * 
	 * @param destination
	 * @return
	 */
	public boolean canMove(Position destination) {

		// check if the destination is within the playarea
		if (destination.x < playArea.x && destination.y < playArea.y
				&& destination.x >= 0 && destination.y >= 0) {

			// check if the destination is a floor tile, any integer under 100
			// is designated for wall tiles
			if (worldTile.getTile(destination.x, destination.y).getType() < 100) {
				// playerMove.x = 0;
				// playerMove.y = 0;
				return false;
			}

			// check if the destination has an obstacle
			for (int i = 0; i < obstacles.size(); i++) {
				RoomObject obj = obstacles.get(i);
				if (destination.equals(obj.getPos())
						&& worldPos.equals(obj.getWorldLoc())) {
					obstacles.get(i).move(Player.getPos());
					isMoving = true;
					return false;
				}
			}

		} else {

			// if destination is not within playarea
			return false;
		}

		return true;
	}
	
	/**
	 * Returns whether the enemy can move to the destination. Needs to be
	 * updated to handle obstacles
	 * 
	 * @param destination
	 * @return
	 */
	public boolean enemyCanMove(Position destination, Position worldLoc) {

		WorldTile worldTile = worldMap[worldLoc.x][worldLoc.y];
		
		// check if the destination is within the playarea
		if (destination.x < playArea.x && destination.y < playArea.y
				&& destination.x >= 0 && destination.y >= 0) {

			// check if the destination is a floor tile, any integer under 100
			// is designated for wall tiles
			if (worldTile.getTile(destination.x, destination.y).getType() < 100) {
				// playerMove.x = 0;
				// playerMove.y = 0;
				return false;
			}

			/*
			// check if the destination has an obstacle
			for (int i = 0; i < obstacles.size(); i++) {
				RoomObject obj = obstacles.get(i);
				if (destination.equals(obj.getPos())
						&& worldPos.equals(obj.getWorldLoc())) {
					obstacles.get(i).move(Player.getPos());
					isMoving = true;
					return false;
				}
			}
			*/

		} else {

			// if destination is not within playarea
			return false;
		}

		return true;
	}

	/**
	 * Removes f.o.w. from the specified @see com.onequest.dungeon.Position
	 * 
	 * @param worldPos
	 */
	protected void clearFog(Position worldPos) {
		mapFog[worldPos.x][worldPos.y] = null;
	}

	/**
	 * Creates a fog of war for the minimap to hide areas the player hasn't been
	 * to yet
	 */
	protected void createFog() {
		for (int i = 0; i < worldArea.x; i++) {
			for (int j = 0; j < worldArea.y; j++) {
				mapFog[i][j] = new Tile(i, j);
				mapFog[i][j].setType(TileImages.WALL_EMPTY);
			}
		}

		clearFog(worldPos);
	}

	/**
	 * Initializes the player character
	 */
	protected void createPlayer() {
		new Player();
		playerSprite = new Sprite(Player.getTexture());
		playerSprite.setSize(tileSize.x, tileSize.y);
		playerSprite.setPosition(Player.getX() * tileSize.x + playPos.x,
				Player.getY() * tileSize.y);
		isMoving = false;
	}

	/**
	 * Called after hidden rooms are created, recursively adds @see
	 * com.onequest.dungeon.WorldTile
	 * 
	 * @param renderZone
	 */
	protected void createWorld(Position renderZone) {
		WorldTile up = null;
		WorldTile down = null;
		WorldTile left = null;
		WorldTile right = null;

		boolean isUp = true;
		boolean isDown = true;
		boolean isLeft = true;
		boolean isRight = true;

		if (renderZone.x >= worldArea.x || renderZone.x < 0
				|| renderZone.y >= worldArea.y || renderZone.y < 0) {
			return;
		}

		if (worldMap[renderZone.x][renderZone.y] != null) {
			return;
		}

		if (renderZone.x >= worldArea.x - 1) {
			right = new WorldTile(true);
			isRight = false;
		}
		if (renderZone.x < 1) {
			left = new WorldTile(true);
			isLeft = false;
		}

		if (renderZone.y >= worldArea.y - 1) {
			up = new WorldTile(true);
			isUp = false;
		}
		if (renderZone.y < 1) {
			down = new WorldTile(true);
			isDown = false;
		}

		if (isUp) {
			up = worldMap[renderZone.x][renderZone.y + 1];
		}
		if (isDown) {
			down = worldMap[renderZone.x][renderZone.y - 1];
		}
		if (isLeft) {
			left = worldMap[renderZone.x - 1][renderZone.y];
		}
		if (isRight) {
			right = worldMap[renderZone.x + 1][renderZone.y];
		}

		int wallType = (new Random().nextInt(2)) + 2;
		int floorType = wallType + 100;

		worldMap[renderZone.x][renderZone.y] = new WorldTile(renderZone,
				floorType, wallType, up, right, down, left);

		createWorld(Position.up(renderZone));
		createWorld(Position.down(renderZone));
		createWorld(Position.right(renderZone));
		createWorld(Position.left(renderZone));

	}

	/**
	 * Called from {@link com.onequest.dungeon.MainClass} to draw the world,
	 * minimap and player to the screen
	 */
	public void draw() {

		drawMiniMap(); // minimap
		drawPlayArea(); // player area
		drawMapPos(); // player position in overworld
		drawFog(); // fog of war over minimap
		drawTeleporter(); // teleporter in/out of hidden area
		drawEnemies(); // enemies
		playerSprite.draw(batch); // player character
		drawObstacles(); // obstacles in overworld
	}

	private void drawEnemies() {
		Texture tmp;
		Enemy enemy;
		Sprite sprite;

		for (int i = 0; i < worldTile.countEnemies(); i++) {
			enemy = worldTile.getEnemy(i);
			tmp = enemy.getImage();
			sprite = new Sprite(tmp);

			sprite.setSize(tileSize.x, tileSize.y);

			sprite.setPosition(enemy.getX() * tileSize.x + playPos.x,
					enemy.getY() * tileSize.y);
			sprite.draw(batch);

		}

		EnemyNM enemyNM;
		for (int i = 0; i < worldBosses.size(); i++) {
			enemyNM = worldBosses.get(i);

			if (enemyNM.getWorldLoc().equals(worldPos)) {
				System.out.println(enemyNM.getEnemy());
				tmp = enemyNM.getEnemy();
				sprite = new Sprite(tmp);

				sprite.setSize(tileSize.x, tileSize.y);

				sprite.setPosition(enemyNM.getX() * tileSize.x + playPos.x,
						enemyNM.getY() * tileSize.y);
				sprite.draw(batch);
			}

		}
	}

	/**
	 * Draws the f.o.w. to the screen
	 */
	protected void drawFog() {
		Texture tmp;
		Tile tile;
		Sprite sprite;

		for (int i = 0; i < worldArea.x; i++) {
			for (int j = 0; j < worldArea.y; j++) {
				if (mapFog[i][j] != null) {
					tile = mapFog[i][j];
					tmp = tile.getImage();
					sprite = new Sprite(tmp);
					sprite.setSize(tileSize.x, tileSize.y);
					sprite.setPosition((tile.getX()) * tileSize.x,
							(tile.getY() + mapLoc.y) * tileSize.y);

					sprite.draw(batch);
				}
			}
		}
	}

	protected void drawMapPos() {
		Texture tmp;
		Sprite sprite;

		// Draw map position icon
		{
			tmp = TileImages.getTileImages(TileImages.MAP_POS);

			sprite = new Sprite(tmp);
			sprite.setSize(tileSize.x, tileSize.y);

			sprite.setPosition((mapLoc.x + worldPos.x) * tileSize.x,
					(mapLoc.y + worldPos.y) * tileSize.y);
			sprite.draw(batch);
		}
	}

	protected void drawMiniMap() {
		Texture tmp;
		Sprite sprite;
		Tile tile;

		float xPos = mapLoc.x * tileSize.x;
		float yPos = mapLoc.y * tileSize.y;
		Vector2 size = new Vector2(tileSize.x, tileSize.y);
		Vector2 area = new Vector2(playArea.x, playArea.y);

		// Draw Minimap, still working to tweak and make more perfect
		for (int i = 0; i < mapArea.x; i++) {
			for (int j = 0; j < mapArea.y; j++) {

				tile = miniMap.getMap(i, j);

				tmp = tile.getImage();

				sprite = new Sprite(tmp);

				// sprite.setSize(tileSize.x / playArea.x, tileSize.y /
				// playArea.y);
				sprite.setSize(2 * (size.x / area.x), 2 * (size.y / area.y));

				/*
				 * sprite.setPosition((tile.getX() + mapLoc.x) * tileSize.x /
				 * playArea.x, (tile.getY() + mapLoc.y) * tileSize.y /
				 * playArea.y);
				 */

				sprite.setPosition(
						tile.getX() * tileSize.x / playArea.x + xPos,
						tile.getY() * tileSize.y / playArea.y + yPos);

				sprite.draw(batch);
			}
		}

		font.draw(batch, String.valueOf(worldPos), 0, 20);

		font.draw(batch, String.valueOf(obstacles.get(0).isMoving), 0, height
				- tileSize.y);
	}

	protected void drawObstacles() {
		Texture tmp;
		Sprite sprite;
		RoomObject obj;

		for (int i = 0; i < obstacles.size(); i++) {
			obj = obstacles.get(i);
			// System.out.println(obj.worldLoc);
			if (worldPos.equals(obj.worldLoc)) {
				tmp = obj.getImage();
				sprite = new Sprite(tmp);
				sprite.setSize(obj.size.x, obj.size.y);
				sprite.setPosition(obj.loc.x * tileSize.x + playPos.x,
						obj.loc.y * tileSize.y);
				sprite.draw(batch);
			}
		}
	}

	protected void drawPlayArea() {
		Texture tmp;
		Sprite sprite;
		Tile tile;

		// Draw the play area
		for (int i = 0; i < playArea.x; i++) {
			for (int j = 0; j < playArea.y; j++) {
				tile = worldTile.getTile(i, j);
				tmp = tile.getImage();
				sprite = new Sprite(tmp);

				sprite.setBounds(tile.getX() * tileSize.x + playPos.x,
						tile.getY() * tileSize.y, tileSize.x, tileSize.y);

				// sprite.setBounds(tile.getX() * tileSize.x, tile.getY() *
				// tileSize.y, tileSize.x, tileSize.y);

				sprite.draw(batch);
			}
		}
	}

	/**
	 * Checks if the current world position has a teleporter in it, if so draws
	 * it to the screen
	 */
	protected void drawTeleporter() {
		Tile tile;
		Texture tmp;
		Sprite sprite;

		if (worldPos.equals(teleporterIn.getFirstWorldPos())) {
			tile = new Tile(teleporterIn.getFirstPos().x,
					teleporterIn.getFirstPos().y);
			tile.setType(TileImages.MAP_WARP);
			tmp = tile.getImage();
			sprite = new Sprite(tmp);
			sprite.setSize(tileSize.x, tileSize.y);
			sprite.setPosition(tile.getX() * tileSize.x + playPos.x,
					tile.getY() * tileSize.y);
			sprite.draw(batch);
		}
		if (worldPos.equals(teleporterOut.getFirstWorldPos())) {
			tile = new Tile(teleporterOut.getFirstPos().x,
					teleporterOut.getFirstPos().y);
			tile.setType(TileImages.MAP_WARP);
			tmp = tile.getImage();
			sprite = new Sprite(tmp);
			sprite.setSize(tileSize.x, tileSize.y);
			sprite.setPosition(tile.getX() * tileSize.x + playPos.x,
					tile.getY() * tileSize.y);
			sprite.draw(batch);
		}
	}

	/**
	 * Initializes most variables for the application
	 */
	protected void init() {
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		incWidth = width / (playArea.x * 2);
		incHeight = height / playArea.y;
		playPos = new Vector2(width / 4, 0);

		// tileSize = new Vector2(incWidth / 2, incHeight / 2);
		tileSize = new Position((int) incWidth, (int) incHeight);

		speed = 200;

		font = new BitmapFont();
		font.setColor(Color.WHITE);

		createPlayer();

		worldMap = new WorldTile[worldArea.x][worldArea.y];

		for (WorldTile[] row : worldMap) {
			Arrays.fill(row, null);
		}

		initHidden();

		createWorld(worldPos);

		miniMap = new MiniMap();
		miniMap.createMap(worldMap);

		worldTile = worldMap[worldPos.x][worldPos.y];

		mapFog = new Tile[worldArea.x][worldArea.y];

		createFog();

		createEnemies();

		// System.out.println(tileSize);
	}

	private void createEnemies() {

		// worldTile enemies that stay in their room
		worldTile.addEnemy(Player.getLvl(), new Position(1, 1));
		worldTile.addEnemy(Player.getLvl(), new Position(6, 8));
		worldTile.addEnemy(Player.getLvl(), new Position(8, 12));

		// world bosses that travel from room to room
		worldBosses = new ArrayList<EnemyNM>();
		worldBosses.add(new EnemyNM(Player.getLvl(), new Position(8, 8),
				new Position(0, 0)));
		//System.out.println(worldBosses.get(0).getEnemy());
	}

	public void updateBosses() {
		for (int i = 0; i < worldBosses.size(); i++) {
			worldBosses.get(i).move(Player.getPos(), worldPos, delta);
		}
	}

	/**
	 * Initialize hidden rooms in the world
	 * 
	 * Picks a random number from 0-4 and picks adjacent Positions. Places these
	 * positions into an ArrayList Finally it takes the List and adds the hidden
	 * rooms to the world
	 */
	protected void initHidden() {
		Random random = new Random();
		int hiddenCount = random.nextInt(5);
		ArrayList<Position> hiddenPos = new ArrayList<Position>();

		do {
			hiddenPos.add(new Position(random.nextInt(worldArea.x), random
					.nextInt(worldArea.y)));
		} while (hiddenPos.get(0).equals(worldPos));

		int direction;

		for (int i = 1; i < hiddenCount; i++) {
			Position pos = null;
			do {
				direction = random.nextInt(4);

				switch (direction) {
				case 0:
					pos = Position.up(hiddenPos.get(i - 1));
					break;
				case 1:
					pos = Position.right(hiddenPos.get(i - 1));
					break;
				case 2:
					pos = Position.down(hiddenPos.get(i - 1));
					break;
				case 3:
					pos = Position.left(hiddenPos.get(i - 1));
					break;
				}

			} while (pos.equals(worldPos) || pos.x >= worldArea.x
					|| pos.y >= worldArea.y || pos.x < 0 || pos.y < 0
					|| hiddenPos.contains(pos));

			hiddenPos.add(pos);
		}

		for (int i = 0; i < hiddenPos.size(); i++) {
			Position renderZone = hiddenPos.get(i);
			worldMap[renderZone.x][renderZone.y] = new WorldTile(renderZone,
					TileImages.FLOOR_PLANK_YELLOW, TileImages.WALL_BRICK_GREEN,
					hiddenPos);
		}

		setTeleporter(hiddenPos);
	}

	/**
	 * Handles user input
	 * 
	 * To be Overridden by the child that needs to i.e. Android/Desktop
	 */
	protected void input() {

	}

	/**
	 * Handles player movement and updates the player texture
	 * 
	 * @param direction
	 * @param canMove
	 */
	protected void movePlayer(String direction, boolean canMove) {
		if (canMove) {
			if (direction == "up") {
				Player.setPos(Position.up(Player.getPos()));
			}
			if (direction == "down") {
				Player.setPos(Position.down(Player.getPos()));
			}
			if (direction == "left") {
				Player.setPos(Position.left(Player.getPos()));
			}
			if (direction == "right") {
				Player.setPos(Position.right(Player.getPos()));
			}
		}

		Player.direction = direction;

		Player.update();
	}

	protected void setObstacles() {
		obstacles = new ArrayList<RoomObject>();

		if (teleporterIn != null) {
			obstacles.add(new RoomStatue(teleporterIn.getFirstPos(),
					teleporterIn.getFirstWorldPos(), tileSize, true));
			obstacles.add(new RoomStatue(teleporterOut.getFirstPos(),
					teleporterOut.getFirstWorldPos(), tileSize, true));
		}

	}

	/**
	 * Sets teleporters in the world to get into and out of the hidden rooms
	 * 
	 * @param hiddenPos
	 */
	protected void setTeleporter(ArrayList<Position> hiddenPos) {
		Random random = new Random();
		Position firstWorldPos, pos, pos2, secondWorldPos;

		// check if there are any hidden worlds
		if (hiddenPos.size() > 0) {

			do {
				// set the position of the teleporter
				firstWorldPos = new Position(random.nextInt(worldArea.x),
						random.nextInt(worldArea.y));

				// check if the teleporter is within the hidden area
			} while (hiddenPos.contains(firstWorldPos));

			do {
				pos = new Position(random.nextInt(playArea.x - 1) + 1,
						random.nextInt(playArea.y - 1) + 1);

				// check if the teleporter is on the outside border
			} while (pos.x <= 1 || pos.y <= 1 || pos.x >= playArea.x - 2
					|| pos.y >= playArea.y - 2);

			// sets the second teleporter in the first hidden room
			secondWorldPos = hiddenPos.get(0);

			// set the teleporter with the data above
			teleporterIn = new Teleporter(firstWorldPos, pos, secondWorldPos,
					pos);

			secondWorldPos = hiddenPos.get(hiddenPos.size() - 1);

			do {
				pos2 = new Position(random.nextInt(playArea.x - 2) + 1,
						random.nextInt(playArea.y - 2) + 1);
			} while (pos.equals(pos2));

			teleporterOut = new Teleporter(secondWorldPos, pos2, firstWorldPos,
					pos2);

			setObstacles();
		} else {
			teleporterIn = null;
			teleporterOut = null;
		}

		System.out.println(teleporterIn);
		// System.out.println(teleporterOut);
	}

	/**
	 * Shifts currently drawn world position to the current world position when
	 * player moves off screen
	 * 
	 * @param destination
	 */
	protected void shiftMap(Position destination) {
		if (destination.x < 0) {
			worldPos = Position.left(worldPos);
			Player.setPos(playArea.x - 1, Player.getPos().y);
		}
		if (destination.x >= playArea.x) {
			worldPos = Position.right(worldPos);
			Player.setPos(0, Player.getPos().y);
		}
		if (destination.y < 0) {
			worldPos = Position.down(worldPos);
			Player.setPos(Player.getPos().x, playArea.y - 1);
		}
		if (destination.y >= playArea.y) {
			worldPos = Position.up(worldPos);
			Player.setPos(Player.getPos().x, 0);
		}

		playerMove.x = 0;
		playerMove.y = 0;
		playerPos.x = Player.getX();
		playerPos.y = Player.getY();

		clearFog(worldPos);
		worldTile = worldMap[worldPos.x][worldPos.y];
	}

	/**
	 * Called from @see com.onequest.dungeon.MainClass to update current fps,
	 * player data, input and current world position
	 */
	public void update() {

		delta = Gdx.graphics.getDeltaTime();

		updateObstacles();

		updatePlayer();

		input();

		worldTile = worldMap[worldPos.x][worldPos.y];

		worldTile.update(delta);

		updateBosses();
	}

	/**
	 * Updates obstacles position and status
	 */
	protected void updateObstacles() {
		for (int i = 0; i < obstacles.size(); i++) {
			obstacles.get(i).update(delta, speed);

			if (obstacles.get(i).isMoving())
				isMoving = true;
		}
	}

	/**
	 * Updates player, handles player movement animation
	 */
	protected void updatePlayer() {
		Player.update();

		playerSprite.setPosition(playerPos.x * tileSize.x + playPos.x,
				playerPos.y * tileSize.y);

		playerSprite.setTexture(Player.getTexture());
		// check if player has moved the player character
		if (isMoving) {
			boolean obsMove = false;
			// check if any of the obstacles are moving
			for (int i = 0; i < obstacles.size(); i++) {
				if (obstacles.get(i).isMoving())
					obsMove = true;
			}

			// check if the player character has reached the destination
			if (Math.abs(Player.getY() - playerPos.y) > .1
					|| Math.abs(Player.getX() - playerPos.x) > .1) {
				playerPos.x += playerMove.x * delta * speed / tileSize.x;
				playerPos.y += playerMove.y * delta * speed / tileSize.y;
			} else if (obsMove) {
				isMoving = true;
			}
			// if the character is at the destination, zero movement and
			// correct any discrepancy from the previous check
			else {

				playerMove.x = 0;
				playerMove.y = 0;
				playerPos.x = Player.getX();
				playerPos.y = Player.getY();

				isMoving = false;
			}

		}
	}
}
