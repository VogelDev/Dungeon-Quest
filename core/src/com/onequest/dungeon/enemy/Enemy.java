package com.onequest.dungeon.enemy;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.onequest.dungeon.MainClass;
import com.onequest.dungeon.Player;
import com.onequest.dungeon.coordinates.Position;
import com.onequest.dungeon.Damage;

/**
 * Enemy, this move method follows the player around the screen
 * 
 * @author Rob Vogel
 * @version 0.0.0002
 * 
 */
public class Enemy {

	int level;
	double levelMod;
	int strength;
	int health;
	Position pos;
	Position move;
	Random r;
	protected Texture enemy;
	private Texture up = new Texture(Gdx.files.internal("Enemy/back.png"));
	private Texture down = new Texture(Gdx.files.internal("Enemy/front.png"));
	private Texture left = new Texture(Gdx.files.internal("Enemy/left.png"));
	private Texture right = new Texture(Gdx.files.internal("Enemy/right.png"));
	String direction;
	boolean isMoving = false;
	float moving;
	Vector2 loc;
	boolean follows;
	int speed;
	ArrayList<Damage> damage;

	public Enemy() {
		init();
		level = 1;
	}

	public Enemy(int playerLevel) {
		init();
		level = r.nextInt(10) + playerLevel;
	}

	public Enemy(int playerLevel, Position levelPos) {
		init();
		level = r.nextInt(10) + playerLevel;
		pos = levelPos.clone();
	}

	private void init() {
		r = new Random();
		levelMod = 1;
		strength = 1;
		health = 1;
		move = new Position();
		pos = new Position();
		direction = "down";
		moving = 0;
		follows = true;
		speed = 5;
		damage = new ArrayList<Damage>();
	}

	public boolean move(Position playerPos, float delta) {
		int dir = r.nextInt(1000) % 2;
		Position moveTo = pos.clone();

		if (Position.right(pos).equals(playerPos)) {
			direction = "right";
			attackPlayer();
		} else if (Position.left(pos).equals(playerPos)) {
			direction = "left";
			attackPlayer();
		} else if (Position.up(pos).equals(playerPos)) {
			direction = "up";
			attackPlayer();
		} else if (Position.down(pos).equals(playerPos)) {
			direction = "down";
			attackPlayer();
		} else if (isMoving) {
			// do nothing
		} else {
			if (follows) {
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
			}else{
				if (dir == 0) {
					if (playerPos.x > pos.x) {
						move.setX(-1);
						direction = "right";
					} else if (playerPos.x < pos.x) {
						move.setX(1);
						direction = "left";
					}
				} else {
					if (playerPos.y > pos.y) {
						move.setY(-1);
						direction = "up";
					} else if (playerPos.y < pos.y) {
						move.setY(1);
						direction = "down";
					}
				}
			}

			moveTo.add(move);

			isMoving = true;
			if(MainClass.world.canMove(moveTo))
				pos.add(move);
			else
				pos.add(new Position(move.x * -1, move.y * -1));
			
		}

		update(delta);

		move.setX(0);
		move.setY(0);

		return false;
	}

	private void attackPlayer() {
		Player.takeDamage(new Damage(strength, level));
	}

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

	public Texture getImage() {
		return enemy;
	}

	public int getX() {
		return pos.x;
	}

	public int getY() {
		return pos.y;
	}

	/**
	 * @return the pos
	 */
	public Position getPos() {
		return pos;
	}

	/**
	 * @param pos the pos to set
	 */
	public void setPos(Position pos) {
		this.pos = pos;
	}
	
	public void takeDamage(Damage dmg){
		damage.add(dmg);
	}
}