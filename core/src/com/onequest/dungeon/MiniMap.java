package com.onequest.dungeon;

import com.onequest.dungeon.coordinates.Position;


/**
 * Creates the minimap
 * 
 * @author Rob Vogel
 * 
 */
public class MiniMap {
	Tile[][] tiles;
	Position worldArea = GlobalData.worldArea;
	Position playArea = GlobalData.playArea;
	Position mapArea = GlobalData.mapArea;

	/**
	 * Empty constructor creates minimap based on map size in @see
	 * com.onequest.dungeon.GlobalData
	 */
	public MiniMap() {
		tiles = new Tile[mapArea.x][mapArea.y];
	}

	/**
	 * Creares the minimap based on the tiles within the given parameter
	 * 
	 * @param worldMap
	 *            WorldMap given from @see com.onequest.dungeon.World
	 */
	public void createMap(WorldTile[][] worldMap) {
		Tile tile = null;
		int x = 0;
		int y = 0;

		// cycles through the worldMap placing tiles into the minimap at new
		// coordinates to coincide with minimap coordinates
		for (int i = 0; i < worldArea.x; i++) {
			for (int j = 0; j < worldArea.y; j++) {
				for (int k = 0; k < playArea.x; k++) {
					for (int l = 0; l < playArea.y; l++) {
						x = k + (playArea.x * i);
						y = l + (playArea.y * j);
						tile = new Tile(x, y);
						tile.setType(worldMap[i][j].getTile(k, l).getType());
						tiles[x][y] = tile;
					}
				}
			}
		}
	}

	/**
	 * Returns the tile requested at the x, y coordinated of the minimap
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public Tile getMap(int x, int y) {
		return tiles[x][y];
	}
}
