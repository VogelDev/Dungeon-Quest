package com.onequest.dungeon.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.onequest.dungeon.GlobalData;
import com.onequest.dungeon.MainClass;
import com.onequest.dungeon.coordinates.Position;

/**
 * 
 * @author Rob Vogel
 * @version 0.0.0001
 *
 */
public class EnemyNM extends Enemy {

	Position worldLoc;
	private Texture enemy;
	private Texture up = new Texture(Gdx.files.internal("Enemy/back.png"));
	private Texture down = new Texture(Gdx.files.internal("Enemy/front.png"));
	private Texture left = new Texture(Gdx.files.internal("Enemy/left.png"));
	private Texture right = new Texture(Gdx.files.internal("Enemy/right.png"));

	public EnemyNM() {
		super();
		init();
	}

	public EnemyNM(int playerLevel, Position levelPos) {
		super(playerLevel, levelPos);
		init();
	}

	public EnemyNM(int playerLevel, Position levelPos, Position worldLoc) {
		super(playerLevel, levelPos);
		this.level = playerLevel + 9;
		this.worldLoc = worldLoc.clone();
		init();
	}

	public EnemyNM(int playerLevel) {
		super(playerLevel);
		init();
	}

	private void init() {
		speed = 5;
		enemy = down;
		System.out.println(worldLoc);
	}

	/**
	 * @return the worldLoc
	 */
	public Position getWorldLoc() {
		return worldLoc;
	}

	/**
	 * @param worldLoc
	 *            the worldLoc to set
	 */
	public void setWorldLoc(Position worldLoc) {
		this.worldLoc = worldLoc;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.onequest.dungeon.enemy.Enemy#move(com.onequest.dungeon.coordinates
	 * .Position, float)
	 */
	public boolean move(Position playerPos, Position worldLoc, float delta) {

		Position moveTo = pos.clone();

		/*
		if (this.worldLoc.equals(worldLoc)) {
			if (Position.right(pos).equals(playerPos)) {
				direction = "right";
			} else if (Position.left(pos).equals(playerPos)) {
				direction = "left";
			} else if (Position.up(pos).equals(playerPos)) {
				direction = "up";
			} else if (Position.down(pos).equals(playerPos)) {
				direction = "down";
			} else if (isMoving) {
				// do nothing
			} else {
				distance = Math.abs(Math.hypot(pos.x - playerPos.x, pos.y
						- playerPos.y));
				isMoving = true;
				if (dir == 0) {
					if (playerPos.x > pos.x) {
						move.setX(1);
						direction = "right";
					} else if (playerPos.x < pos.x) {
						move.setX(-1);
						direction = "left";
					}
				} else {
					if (playerPos.y > pos.y) {
						move.setY(1);
						direction = "up";
					} else if (playerPos.y < pos.y) {
						move.setY(-1);
						direction = "down";
					}
				}

				if (distance < 8) {
					speed *= 1.5;
				}
			}
		} else {*/
			isMoving = true;
			move = new Position(r.nextInt(3) - 1, r.nextInt(3) - 1);
		//}

		isMoving = true;
		if (moveTo.x > GlobalData.playArea.x || moveTo.x < 0
				|| moveTo.y > GlobalData.playArea.y || moveTo.y < 0) {
			shiftRoom(moveTo);
		} else if (MainClass.world.enemyCanMove(moveTo, worldLoc)) {
			pos.add(move);
		} else {
			pos.add(new Position(move.x * -1, move.y * -1));
		}

		update(delta);

		speed = 5;
		move.setX(0);
		move.setY(0);

		System.out.println("Enemy Pos: " + pos);
		System.out.println("\tWorld Pos: " + this.worldLoc);

		return super.move(playerPos, delta);
	}

	private void shiftRoom(Position moveTo) {
		if (moveTo.x > GlobalData.playArea.x) {
			worldLoc.x++;
			pos.x = 1;
		} else if (moveTo.x < 0) {
			worldLoc.x--;
			pos.x = GlobalData.playArea.x - 2;
		} else if (moveTo.y > GlobalData.playArea.y) {
			worldLoc.y++;
			pos.y = 0;
		} else if (moveTo.y < 0) {
			worldLoc.y--;
			pos.y = GlobalData.playArea.y - 2;
		}
		
		System.out.println("Shifted");
	}

	@Override
	public void update(float delta) {

		if (direction == "up") {
			enemy = up;
		}
		if (direction == "down") {
			enemy = down;
		}
		if (direction == "left") {
			enemy = left;
		}
		if (direction == "right") {
			enemy = right;
		}

		moving += delta * speed;

		if (moving > 2) {
			moving = 0;
			isMoving = false;
		}
	}

	/**
	 * @return the enemy
	 */
	public Texture getEnemy() {
		return enemy;
	}

	/**
	 * @param enemy
	 *            the enemy to set
	 */
	public void setEnemy(Texture enemy) {
		this.enemy = enemy;
	}

}
