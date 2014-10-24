package com.onequest.dungeon;

import com.badlogic.gdx.graphics.Texture;

/**
 * Handles organizing textures for all other classes
 * @author robtv_000
 *
 */
public class TileImages {
	
	public static final int WALL_EMPTY = 0;
	public static final int WALL = 1;
	public static final int WALL_BRICK = 2;
	public static final int WALL_BRICK_BLUE = 3;
	public static final int WALL_BRICK_GREEN = 99;
	public static final int FLOOR_EMPTY = 100;
	public static final int FLOOR = 101;
	public static final int FLOOR_PLANK = 102;
	public static final int FLOOR_PLANK_BLUE = 103;
	public static final int FLOOR_PLANK_YELLOW = 199;
	public static final int MAP_POS = 200;
	public static final int MAP_WARP = 201;
	public static final int STATUE = 300;
	public static final int DPAD_LEFT = 900;
	public static final int DPAD_RIGHT = 901;
	public static final int DPAD_UP = 902;
	public static final int DPAD_DOWN = 903;
	public static final int TEST = 999;
	
	public static Texture getTileImages(int type) {

		switch (type) {
		case WALL_EMPTY:
			return TileWall.getEmpty();
		case WALL:
			return TileWall.getWall();
		case WALL_BRICK:
			return TileWall.getWallBrick();
		case WALL_BRICK_BLUE:
			return TileWall.getWallBrickBlue();
		case WALL_BRICK_GREEN:
			return TileWall.getWallBrickGreen();

		case FLOOR_EMPTY:
			return TileWall.getEmpty();
		case FLOOR:
			return TileFloor.getFloor();
		case FLOOR_PLANK:
			return TileFloor.getFloorPlank();
		case FLOOR_PLANK_BLUE:
			return TileFloor.getFloorPlankBlue();
		case FLOOR_PLANK_YELLOW:
			return TileFloor.getFloorPlankYellow();
			
		case MAP_POS:
			return TileMap.getMapPos();
		case MAP_WARP:
			return TileMap.getWarp();
			
		case STATUE:
			return TileRoom.getStatue();
			
		case DPAD_LEFT:
			return TileControls.getDpadLeft();
		case DPAD_RIGHT:
			return TileControls.getDpadRight();
		case DPAD_UP:
			return TileControls.getDpadUp();
		case DPAD_DOWN:
			return TileControls.getDpadDown();

		case TEST:
			return TileWall.getTest();

		}

		return null;
	}
}
