package com.onequest.dungeon;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Handles textures for map tiles
 * @author Rob Vogel
 *
 */
public class TileMap {
	private static Texture mapPos = new Texture(Gdx.files.internal("MapTiles/map-position.png"));
	private static Texture warp = new Texture(Gdx.files.internal("MapTiles/warp-portal.png"));

	public static Texture getMapPos() {
		return mapPos;
	}
	
	public static Texture getWarp() {
		return warp;
	}
}
