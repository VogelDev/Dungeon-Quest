package com.onequest.dungeon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Handles the textures for floor tiles
 * @author Rob Vogel
 *
 */
public class TileFloor {
	private static Texture floor = new Texture(Gdx.files.internal("RoomTiles/floor.png"));
	private static Texture floorPlank = new Texture(Gdx.files.internal("RoomTiles/floor-plank.png"));
	private static Texture floorPlankBlue = new Texture(Gdx.files.internal("RoomTiles/floor-plank-blue.png"));
	private static Texture floorPlankYellow = new Texture(Gdx.files.internal("RoomTiles/floor-plank-yellow.png"));
	private static Texture floorSlotted = new Texture(Gdx.files.internal("RoomTiles/floor-slotted.png"));
	
	public static Texture getFloor(){
		return floor;
	}
	
	public static Texture getFloorPlank(){
		return floorPlank;
	}
	
	public static Texture getFloorPlankBlue(){
		return floorPlankBlue;
	}
	
	public static Texture getFloorPlankYellow(){
		return floorPlankYellow;
	}
	
	public static Texture getFloorSlotted(){
		return floorSlotted;
	}
}
