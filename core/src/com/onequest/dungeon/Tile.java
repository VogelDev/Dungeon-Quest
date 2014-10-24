package com.onequest.dungeon;

import com.badlogic.gdx.graphics.Texture;

/**
 * Individual tile used to hold texture
 * @author Rob Vogel
 *
 */
public class Tile {
	private int type;
	private Texture tileImage;
	private int x, y;
	private boolean isSet;
	
	/**
	 * Sets the coordinates of the new Tile
	 * @param x
	 * @param y
	 */
	public Tile(int x, int y){
		this.x = x;
		this.y = y;
		isSet = false;
	}
	
	/**
	 * Not currently used
	 * @return
	 */
	public boolean isSet(){
		return isSet;
	}
	
	/**
	 * Not currently used
	 * @param isSet
	 */
	public void set(boolean isSet){
		this.isSet = isSet;
	}
	
	/**
	 * Returns the int type of the Tile as defined in @see com.onequest.dungeon.TileImages
	 * @return
	 */
	public int getType(){
		return type;
	}
	
	/**
	 * Returns the image/Texture of the tile
	 * @return
	 */
	public Texture getImage(){
		return tileImage;
	}
	
	/**
	 * Sets the int type and image/Texture from given parameter
	 * @param type
	 */
	public void setType(int type){
		this.type = type;
		tileImage = TileImages.getTileImages(type);
	}

	/**
	 * Returns x coordinate
	 * @return
	 */
	public int getX() {
		return x;
	}

	/**
	 * Sets x coordinate
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Returns y coordinate
	 * @return
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets y coordinate
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Not currently used
	 * @param another
	 * @return
	 */
	public boolean equals(Tile another){
		if(this.x == another.getX() && this.y == another.getY()){
			return true;
		}
		
		return false;
	}
	
	/**
	 * Returns a String format of the Tile
	 */
	public String toString(){
		return String.valueOf(type);
	}
}
