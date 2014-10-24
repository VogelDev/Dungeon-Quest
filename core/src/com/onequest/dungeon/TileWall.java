package com.onequest.dungeon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Handles textures for wall Tiles
 * @author Rob Vogel
 *
 */
public class TileWall {
	private static Texture empty = new Texture(Gdx.files.internal("RoomTiles/empty.png"));
	private static Texture test = new Texture(Gdx.files.internal("RoomTiles/test.png"));
	private static Texture wall = new Texture(Gdx.files.internal("RoomTiles/wall.png"));
	private static Texture wallBrick = new Texture(Gdx.files.internal("RoomTiles/wall-brick.png"));
	private static Texture wallBrickBlue = new Texture(Gdx.files.internal("RoomTiles/wall-brick-blue.png"));
	private static Texture wallBrickGreen = new Texture(Gdx.files.internal("RoomTiles/wall-brick-green.png"));
	
	public static Texture getEmpty(){
		return empty;
	}
	
	public static Texture getTest(){
		return test;
	}
	
	public static Texture getWall(){
		return wall;
	}
	
	public static Texture getWallBrick(){
		return wallBrick;
	}
	
	public static Texture getWallBrickBlue(){
		return wallBrickBlue;
	}
	
	public static Texture getWallBrickGreen(){
		return wallBrickGreen;
	}
}
