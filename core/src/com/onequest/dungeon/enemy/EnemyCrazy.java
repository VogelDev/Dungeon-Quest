package com.onequest.dungeon.enemy;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.onequest.dungeon.coordinates.Position;

/**
 * Enemy, this move method dances around the screen
 * 
 * @author Rob Vogel
 *
 */
public class EnemyCrazy {

	int level;
	double levelMod;
	int strength;
	int health;
	Position pos;
	Position move;
	Random r;
	private Texture enemy;
	private Texture up = new Texture(Gdx.files.internal("Enemy/back.png"));
	private Texture down = new Texture(Gdx.files.internal("Enemy/front.png"));
	private Texture left = new Texture(Gdx.files.internal("Enemy/left.png"));
	private Texture right = new Texture(Gdx.files.internal("Enemy/right.png"));
	String direction;
	boolean isMoving = false;
	float moving;
	Vector2 loc;

	public EnemyCrazy() {
		init();
		level = 1;
	}

	public EnemyCrazy(int playerLevel) {
		init();
		level = r.nextInt(10) + playerLevel;
	}

	public EnemyCrazy(int playerLevel, Position levelPos) {
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
	}

	public boolean move(Position playerPos, float delta) {
		int dir = r.nextInt(1000) % 2;

		if (Position.right(pos).equals(playerPos)){
			direction = "right";
		}else if(Position.left(pos).equals(playerPos)){
			direction = "left";
		}else if(Position.up(pos).equals(playerPos)){
			direction = "up";
		}else if(Position.down(pos).equals(playerPos)) {
			direction = "down";
		} else if(isMoving){
			//do nothing
		}else{
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
			isMoving = true;
			pos.add(move);
		}
		
		update(delta);
		
		move = Position.zero;

		return false;
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
		
		moving += delta * 10;
		
		if(moving > 2){
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
}