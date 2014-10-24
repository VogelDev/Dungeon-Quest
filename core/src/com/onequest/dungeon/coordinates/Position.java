package com.onequest.dungeon.coordinates;

/**
 * A custom coordinate system using integers for block/grid style coordinates
 * 
 * @author Rob Vogel
 * 
 */
public class Position implements Cloneable{
	public static Position zero = new Position();
	public int x;
	public int y;

	/**
	 * Empty constructor creates a Position at zero coordinates
	 */
	public Position() {
		x = 0;
		y = 0;
	}

	/**
	 * Creats a Position at the given coordinates
	 * 
	 * @param x
	 * @param y
	 */
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Returns x coordinate
	 * 
	 * @return
	 */
	public int X() {
		return x;
	}

	/**
	 * Returns y coordinates
	 * 
	 * @return
	 */
	public int Y() {
		return y;
	}

	/**
	 * Returns a String format for the Position
	 */
	public String toString() {
		return "x: " + x + ", y: " + y;
	}

	/**
	 * Returns a new Position one cell above given Position
	 * 
	 * @param another
	 * @return
	 */
	public static Position up(Position another) {
		return new Position(another.x, another.y + 1);
	}

	/**
	 * Returns a new Position one cell below given Position
	 * 
	 * @param another
	 * @return
	 */
	public static Position down(Position another) {
		return new Position(another.x, another.y - 1);
	}

	/**
	 * Returns a new Position one cell left of the given Position
	 * 
	 * @param another
	 * @return
	 */
	public static Position left(Position another) {
		return new Position(another.x - 1, another.y);
	}

	/**
	 * Returns a new Position one cell right of the given Position
	 * 
	 * @param another
	 * @return
	 */
	public static Position right(Position another) {
		return new Position(another.x + 1, another.y);
	}

	/**
	 * Compares two Positions and returns whether they are the same
	 * 
	 * @param position
	 * @return
	 */
	public boolean equals(Position position) {
		if (this.x == position.x && this.y == position.y) {
			return true;
		}

		return false;
	}

	@Override
	/**
	 * Same as @see com.onequest.dungeon.Position#equals(Position) to be used with built in functions such as ArrayList's contains method
	 */
	public boolean equals(Object obj) {
		Position pos = (Position) obj;
		if (this.x == pos.x && this.y == pos.y) {
			return true;
		}

		return false;
	}
	
	public Position clone(){
		
		Position answer = null;
	      
	    try {
			answer = (Position)super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	      
	    return answer;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}	

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	public void add(Position move) {
		this.x += move.x;
		this.y += move.y;
	}
}
