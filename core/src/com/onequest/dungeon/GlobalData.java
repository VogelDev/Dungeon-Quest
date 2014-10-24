package com.onequest.dungeon;

import com.onequest.dungeon.coordinates.Position;

/**
 * Global data to be used throughout the application
 * @author Rob Vogel
 *
 */
public class GlobalData {
	//total number of WorldTiles to be created in the overworld
	public static Position worldArea = new Position(8, 8);
	
	//total number of Tiles to be created in the playfield
	public static Position playArea = new Position(16, 18);
	
	//total number of Tiles to be created in the MiniMap
	public static Position mapArea = new Position(worldArea.x * playArea.x, worldArea.y * playArea.y);
	
	//position the first "WorldTile" of the MiniMap should be drawn
	//currently set to be drawn in the upper left of the screen
	//playArea is the max area on the screen, subtract worldArea to get the start position
	//subtract one to adjust for arrays
	public static Position mapLoc = new Position(0, playArea.y - worldArea.y);
	
	//position the player spawns on screen at
	//currently the center of the screen
	public static Position playerStart = new Position(playArea.x / 2, playArea.y / 2);
	
	//position the player spawns in the overworld
	//currently the center of the overworld
	public static Position worldStart = new Position(worldArea.x / 2, worldArea.y / 2);
}
