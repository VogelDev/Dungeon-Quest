package com.onequest.dungeon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.onequest.dungeon.coordinates.Position;

/**
 * Center of application, handles controls and drawing to the screen
 * 
 * @author Rob Vogel
 * @version 0.0.0001
 * 
 */
public class WorldDesktop extends World{

	public WorldDesktop(SpriteBatch batch) {
		super(batch);
	}

	/**
	 * Handles user input
	 */
	@Override
	protected void input() {
		Input input = Gdx.input;

		String direction = "";
		boolean canMove = false;
		Position destination = new Position();

		// configured from "wasd" movement type, one direction at a time
		if (input.isKeyPressed(Keys.W) && !isMoving) {
			direction = "up";
			playerMove.y = 1;
			isMoving = true;
			destination = Position.up(Player.getPos());
			// System.out.println(String.valueOf(canMove(destination)));
			// System.out.println(String.valueOf(destination));
		} else if (input.isKeyPressed(Keys.S) && !isMoving) {
			direction = "down";
			playerMove.y = -1;
			isMoving = true;
			destination = Position.down(Player.getPos());

			// System.out.println(String.valueOf(canMove(destination)));
			// System.out.println(String.valueOf(destination));
		} else if (input.isKeyPressed(Keys.A) && !isMoving) {
			direction = "left";
			playerMove.x = -1;
			isMoving = true;
			destination = Position.left(Player.getPos());

			// System.out.println(String.valueOf(canMove(destination)));
			// System.out.println(String.valueOf(destination));
		} else if (input.isKeyPressed(Keys.D) && !isMoving) {
			direction = "right";
			playerMove.x = 1;
			isMoving = true;
			destination = Position.right(Player.getPos());

			// System.out.println(String.valueOf(canMove(destination)));
			// System.out.println(String.valueOf(destination));
		}

		// for debugging, allows user to quickly cycle through the overworld
		if (input.isKeyPressed(Keys.LEFT) && worldPos.x > 0) {
			worldPos = Position.left(worldPos);
			clearFog(worldPos);
			return;
		}
		if (input.isKeyPressed(Keys.RIGHT) && worldPos.x < worldArea.x - 1) {
			worldPos = Position.right(worldPos);
			clearFog(worldPos);
			return;
		}
		if (input.isKeyPressed(Keys.UP) && worldPos.y < worldArea.y - 1) {
			worldPos = Position.up(worldPos);
			clearFog(worldPos);
			return;
		}
		if (input.isKeyPressed(Keys.DOWN) && worldPos.y > 0) {
			worldPos = Position.down(worldPos);
			clearFog(worldPos);
			return;
		}
		if(input.isKeyPressed(Keys.ENTER)){
			init();
		}

		// check if the given destination is a valid move for the player
		canMove = canMove(destination);

		// determines whether the player is about to step "off" of a worldTile
		// and shifts the map accordingly
		if (destination.x >= playArea.x || destination.x < 0
				|| destination.y >= playArea.y || destination.y < 0) {
			playerMove.x = 0;
			playerMove.y = 0;
			shiftMap(destination);
		} else {
			// checks if player is standing on the teleporter in the main world
			if (worldPos.equals(teleporterIn.getFirstWorldPos())
					&& Player.getPos().equals(teleporterIn.getFirstPos())) {
				worldPos = teleporterIn.getSecondWorldPos();
				Player.setPos(teleporterIn.getSecondPos());
				playerPos.x = Player.getX();
				playerPos.y = Player.getY();
				playerMove = Vector2.Zero;
				clearFog(worldPos);
				return;
			}
			// checks if player is standing on the teleporter in the hidden zone
			if (worldPos.equals(teleporterOut.getFirstWorldPos())
					&& Player.getPos().equals(teleporterOut.getFirstPos())) {
				worldPos = teleporterOut.getSecondWorldPos();
				Player.setPos(teleporterOut.getSecondPos());
				playerPos.x = Player.getX();
				playerPos.y = Player.getY();
				playerMove = Vector2.Zero;
				return;
			}

			// move the player in the given direction, if canMove is false the
			// character will turn and not move
			movePlayer(direction, canMove);

		}
	}
}
