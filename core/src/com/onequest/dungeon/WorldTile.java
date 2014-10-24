package com.onequest.dungeon;

import java.util.ArrayList;
import java.util.Random;
import com.onequest.dungeon.coordinates.Position;
import com.onequest.dungeon.enemy.*;

/**
 * Collection of @see com.onequest.dungeon.Tile to form a "room" for the player
 * to interact with
 * 
 * @author Rob Vogel
 * 
 */
public class WorldTile {
	private int[][] landscape;
	private Tile[][] tiles;
	private Position playArea = new Position(16, 18);
	private int floorType, wallType;
	private boolean isSet = false;
	private Position position;
	private boolean debug = false;
	private boolean isBorder = false;
	private ArrayList<Enemy> enemies;

	/**
	 * Default constructor used recursively to create the world
	 * 
	 * @param position
	 * @param floorType
	 * @param wallType
	 * @param up
	 * @param right
	 * @param down
	 * @param left
	 */
	public WorldTile(Position position, int floorType, int wallType,
			WorldTile up, WorldTile right, WorldTile down, WorldTile left) {

		this.position = position.clone();
		this.floorType = floorType;
		this.wallType = wallType;

		landscape = new int[playArea.x][playArea.y];

		createPlayField(up, right, down, left);

		createTiles();
	}

	/**
	 * Constructor used if this world tile is outside the player area so the
	 * appropriate borders can be created
	 * 
	 * @param isBorder
	 */
	public WorldTile(boolean isBorder) {
		this.isBorder = isBorder;
	}

	/**
	 * Constructor used for rooms hidden from player without using @see
	 * com.onequest.dungeon.Teleporter
	 * 
	 * @param renderZone
	 * @param floorType
	 * @param wallType
	 * @param hiddenPos
	 */
	public WorldTile(Position renderZone, int floorType, int wallType,
			ArrayList<Position> hiddenPos) {
		this.position = renderZone.clone();
		this.floorType = floorType;
		this.wallType = wallType;

		landscape = new int[playArea.x][playArea.y];

		createHidden(hiddenPos);
		createTiles();
	}

	/**
	 * Create a room where the only doorways are to adjacent hidden rooms It
	 * uses the given List of Positions to determine if adjacent rooms are
	 * hidden
	 * 
	 * @param hiddenPos
	 */
	private void createHidden(ArrayList<Position> hiddenPos) {

		int topGap = 0;
		int bottomGap = 0;
		int leftGap = 0;
		int rightGap = 0;

		int topSide = 0;
		int bottom = 0;
		int leftSide = 0;
		int rightSide = 0;

		if (hiddenPos.contains(Position.up(position))) {
			topGap = 4;
			topSide = playArea.x - 6;
		}
		if (hiddenPos.contains(Position.down(position))) {
			bottomGap = 4;
			bottom = playArea.x - 6;
		}
		if (hiddenPos.contains(Position.left(position))) {
			leftGap = 4;
			leftSide = playArea.x - 7;
		}
		if (hiddenPos.contains(Position.right(position))) {
			rightGap = 4;
			rightSide = playArea.x - 7;
		}
		for (int i = 0; i < playArea.x; i++) {
			for (int j = 0; j < playArea.y; j++) {
				landscape[i][j] = 1;

				// left side
				if (i == 0) {
					if (leftGap == 0) {
						landscape[i][j] = 0;
					} else if (!(j > leftGap && j < leftSide)) {
						landscape[i][j] = 0;
					}
				}
				// right side
				if (i == playArea.x - 1) {
					if (rightGap == 0) {
						landscape[i][j] = 0;
					} else if (!(j > rightGap && j < rightSide)) {
						landscape[i][j] = 0;
					}
				}
				// bottom
				if (j == 0) {
					if (bottomGap == 0) {
						landscape[i][j] = 0;
					} else if (!(i > bottomGap && i < bottom)) {
						landscape[i][j] = 0;
					}
				}
				// top side
				if (j == playArea.y - 1) {
					if (topGap == 0) {
						landscape[i][j] = 0;
					} else if (!(i > topGap && i < topSide)) {
						landscape[i][j] = 0;
					}
				}
			}
		}
	}

	/**
	 * Creates a numeric grid of 1's and 0's called landscape to determine walls
	 * and doorways Creates floor tiles for entire room, then creates walls on
	 * borders based on a random method of creating doorways and adjacent rooms
	 * 
	 * @param up
	 * @param right
	 * @param down
	 * @param left
	 */
	public void createPlayField(WorldTile up, WorldTile right, WorldTile down,
			WorldTile left) {

		int topGap = 4;
		int bottomGap = 4;
		int leftGap = 4;
		int rightGap = 4;

		if (position.x == 4 && position.y == 4) {
			topGap = 6;
			leftGap = 6;
			rightGap = 6;
			bottomGap = 6;
		}

		int topSide = playArea.x - 6;
		int bottom = playArea.x - 6;
		int leftSide = playArea.y - 7;
		int rightSide = playArea.y - 7;

		if (!debug) {
			topGap = new Random().nextInt(6);
			leftGap = new Random().nextInt(7);
			rightGap = new Random().nextInt(7);
			bottomGap = new Random().nextInt(6);

			if (position.x == 4 && position.y == 4) {
				topGap = 4;
				leftGap = 4;
				rightGap = 4;
				bottom = 4;
			}

			topSide = (new Random().nextInt(playArea.x - topGap)) + topGap;
			bottom = (new Random().nextInt(playArea.x - bottomGap)) + bottomGap;
			leftSide = (new Random().nextInt(playArea.y - leftGap)) + leftSide;
			rightSide = (new Random().nextInt(playArea.y - rightGap))
					+ rightSide;
		}

		for (int i = 0; i < playArea.x; i++) {
			for (int j = 0; j < playArea.y; j++) {
				landscape[i][j] = 1;

				// left side
				if (i == 0) {
					if (left == null) {
						if (!(j > leftGap && j < leftSide)) {
							landscape[i][j] = 0;
						}
					} else if (left.isBorder()) {
						landscape[i][j] = 0;
					} else {
						landscape[i][j] = left.getLandscape()[playArea.x - 1][j];
					}
				}

				// right side
				if (i == playArea.x - 1) {
					if (right == null) {
						if (!(j > rightGap && j < rightSide)) {
							landscape[i][j] = 0;
						}
					} else if (right.isBorder()) {
						landscape[i][j] = 0;
					} else {
						landscape[i][j] = right.getLandscape()[0][j];
					}
				}

				// bottom
				if (j == 0) {
					if (down == null) {
						if (!(i > bottomGap && i < bottom)) {
							landscape[i][j] = 0;
						}
					} else if (down.isBorder()) {
						landscape[i][j] = 0;
					} else {
						landscape[i][j] = down.getLandscape()[i][playArea.y - 1];
					}
				}

				// top side
				if (j == playArea.y - 1) {
					if (up == null) {
						if (!(i > topGap && i < topSide)) {
							landscape[i][j] = 0;
						}
					} else if (up.isBorder()) {
						landscape[i][j] = 0;
					} else {
						landscape[i][j] = up.getLandscape()[i][0];
					}
				}

			}
		}
	}

	/**
	 * Returns whether this room is outside the play area
	 * 
	 * @return
	 */
	public boolean isBorder() {
		return isBorder;
	}

	/**
	 * Uses the numeric data in "landscape" to set @see
	 * com.onequest.dungeon.Tile to appropriate tile type for the wall/floor
	 * combination
	 */
	public void createTiles() {

		tiles = new Tile[playArea.x][playArea.y];

		Tile tile;

		for (int i = 0; i < playArea.x; i++) {
			for (int j = 0; j < playArea.y; j++) {
				tile = new Tile(i, j);

				if (landscape[i][j] == 0) {
					tile.setType(wallType);
				}
				if (landscape[i][j] == 1) {
					tile.setType(floorType);
				}
				if (landscape[i][j] == 2) {
					tile.setType(0);
				}

				tiles[i][j] = tile;
				// System.out.println(tile.getType());
			}
		}

		enemies = new ArrayList<Enemy>();
	}

	/**
	 * Returns the requested Tile to determine the type of Tile
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	public Tile getTile(int i, int j) {
		return tiles[i][j];
	}

	/**
	 * Counts the number of wall tiles in the room, not currently used
	 * 
	 * @return
	 */
	private int countWalls() {
		int walls = 0;

		for (int i = 0; i < playArea.x; i++) {
			for (int j = 0; j < playArea.y; j++) {
				if (i == 0 || i == playArea.x - 1 || j == 0
						|| j == playArea.y - 1) {
					walls += landscape[i][j];
				}
			}
		}

		return walls;
	}

	/**
	 * Returns whether the room has enough exit area / doorways, not currently
	 * used
	 * 
	 * @return
	 */
	public boolean isExit() {
		if (countWalls() > 55) {
			return false;
		}

		return true;
	}

	/**
	 * Not currently used
	 * 
	 * @return
	 */
	public boolean isSet() {
		return isSet;
	}

	/**
	 * Not currently used
	 * 
	 * @param isSet
	 */
	public void set(boolean isSet) {
		this.isSet = isSet;
	}

	/**
	 * Returns numeric grid data from this room
	 * 
	 * @return
	 */
	public int[][] getLandscape() {
		return landscape;
	}

	public void addEnemy(int playerLevel, Position levelPos) {
		enemies.add(new Enemy(playerLevel, levelPos));
	}

	public Enemy getEnemy(int enemy) {
		return enemies.get(enemy);
	}

	public void update(float delta) {
		updateEnemies(delta);
	}

	private void updateEnemies(float delta) {
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).move(Player.getPos(), delta);
			//enemies.get(i).update(delta);
		}
	}

	public int countEnemies() {
		return enemies.size();
	}

	/**
	 * Returns String format of this room
	 */
	public String toString() {
		return String.valueOf("\t" + position);
	}
}
