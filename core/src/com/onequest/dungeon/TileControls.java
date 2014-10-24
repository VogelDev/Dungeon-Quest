package com.onequest.dungeon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class TileControls {
	private static Texture dpad_left = new Texture(Gdx.files.internal("Controls/dpad-left.png"));
	private static Texture dpad_right = new Texture(Gdx.files.internal("Controls/dpad-right.png"));
	private static Texture dpad_up = new Texture(Gdx.files.internal("Controls/dpad-up.png"));
	private static Texture dpad_down = new Texture(Gdx.files.internal("Controls/dpad-down.png"));
	
	public static Texture getDpadLeft(){
		return dpad_left;
	}
	
	public static Texture getDpadRight(){
		return dpad_right;
	}
	
	public static Texture getDpadUp(){
		return dpad_up;
	}
	
	public static Texture getDpadDown(){
		return dpad_down;
	}

}
