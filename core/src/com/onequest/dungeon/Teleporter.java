package com.onequest.dungeon;

import com.onequest.dungeon.coordinates.Position;

/**
 * A teleporter object for moving the player around the world
 * @author Rob Vogel
 *
 */
public class Teleporter {
	private Position[] firstPos;
	private Position[] secondPos;

	/**
	 * Currently first and second position are the same.
	 * @param firstWorldPos
	 * 	World coordinate where the teleporter pad will be
	 * @param firstPos
	 * 	Specific tile the pad will be in the WorldTile
	 * @param secondWorldPos
	 * 	Destination world coordinate
	 * @param secondPos
	 * 	Specific tile the player will "land" on
	 */
	public Teleporter(Position firstWorldPos, Position firstPos,
			Position secondWorldPos, Position secondPos) {
		this.firstPos = new Position[] { firstWorldPos.clone(), firstPos.clone() };
		this.secondPos = new Position[] { secondWorldPos.clone(), secondPos.clone() };
	}

	/**
	 * Returns World coordinate where the teleporter pad will be
	 * @return
	 */
	public Position getFirstWorldPos() {
		return firstPos[0];
	}

	/**
	 * Returns Specific tile the pad will be in the WorldTile
	 * @return
	 */
	public Position getFirstPos() {
		return firstPos[1];
	}
	/**
	 * Returns Destination world coordinate
	 * @return
	 */
	public Position getSecondWorldPos() {
		return secondPos[0];
	}
	/**
	 * Returns Specific tile the player will "land" on
	 * @return
	 */
	public Position getSecondPos() {
		return secondPos[1];
	}

	/**
	 * Returns a String format for the teleporter
	 */
	public String toString() {
		String str = "teleporter:\n" + firstPos[0] + "\n" + firstPos[1]
				+ "\nlanding:\n" + secondPos[0] + "\n" + secondPos[1];

		return str;
	}
}
