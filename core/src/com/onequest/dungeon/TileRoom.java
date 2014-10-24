package com.onequest.dungeon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class TileRoom {
	private static Texture statue = new Texture(Gdx.files.internal("Objects/statue.png"));
	
	public static Texture getStatue(){
		return statue;
	}
}
