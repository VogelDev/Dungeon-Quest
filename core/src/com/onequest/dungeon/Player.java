package com.onequest.dungeon;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.onequest.dungeon.coordinates.Position;
import com.onequest.dungeon.enemy.Enemy;
import com.onequest.dungeon.equipment.Equipment;
import com.onequest.dungeon.equipment.EquippedGear;

/**
 * Player object to keep track of position and texture
 * 
 * @author Rob Vogel
 * @version 0.0.0002
 * 
 */
@SuppressWarnings("unused")
public class Player {
	public static String direction;
	public static Position pos = GlobalData.playerStart;
	private static Texture player;
	private static Texture up = new Texture(
			Gdx.files.internal("Player/back.png"));
	private static Texture down = new Texture(
			Gdx.files.internal("Player/front.png"));
	private static Texture left = new Texture(
			Gdx.files.internal("Player/left.png"));
	private static Texture right = new Texture(
			Gdx.files.internal("Player/right.png"));
	private float delta;
	public static int playerLevel;
	public static int strength;
	private static int health;
	private static EquippedGear gear;
	private static ArrayList<Equipment> inventory;
	private static Rectangle bounds;
	private static ArrayList<Damage> damage;

	/**
	 * Empty constructor to create a player character
	 */
	public Player() {
		playerLevel = 1;
		strength = 1;
		health = 1;

		inventory = new ArrayList<Equipment>();
		damage = new ArrayList<Damage>();
		gear = new EquippedGear();
		direction = "up";
		update();
	}

	/**
	 * Returns player @see com.onequest.dungeon.Position
	 * 
	 * @return
	 */
	public static Position getPos() {
		return pos;
	}

	/**
	 * Currently not being used, replaced with a private method in @see
	 * com.onequest.dungeon.World
	 * 
	 * @param direction
	 * @param canMove
	 */
	public static void move(String direction, boolean canMove) {
		if (canMove) {
			if (direction == "up") {
				pos.y++;
			}
			if (direction == "down") {
				pos.y--;
			}
			if (direction == "left") {
				pos.x--;
			}
			if (direction == "right") {
				pos.x++;
			}
		}

		Player.direction = direction;

		update();
	}

	/**
	 * Updates texture based on current direction
	 */
	public static void update() {
		if (direction == "up") {
			player = up;
		}
		if (direction == "down") {
			player = down;
		}
		if (direction == "left") {
			player = left;
		}
		if (direction == "right") {
			player = right;
		}
	}

	/**
	 * Returns x coordinate
	 * 
	 * @return
	 */
	public static int getX() {
		return pos.x;
	}

	/**
	 * Returns y coordinate
	 * 
	 * @return
	 */
	public static int getY() {
		return pos.y;
	}

	/**
	 * returns Texture of the player
	 * 
	 * @return
	 */
	public static Texture getTexture() {
		return player;
	}

	/**
	 * Returns the @see com.onequest.dungeon.Position of the player
	 * 
	 * @param x
	 * @param y
	 */
	public static void setPos(int x, int y) {
		pos.x = x;
		pos.y = y;
	}

	/**
	 * Sets the @see com.onequest.dungeon.Position of the player
	 * 
	 * @param pos
	 */
	public static void setPos(Position pos) {
		Player.pos = pos;
	}

	/**
	 * @return the playerLevel
	 */
	public static int getLvl() {
		return playerLevel;
	}

	/**
	 * @param playerLevel
	 *            the playerLevel to set
	 */
	public static void setLvl(int playerLevel) {
		Player.playerLevel = playerLevel;
	}

	/**
	 * Level the player up once
	 */
	public static void levelUp() {
		Player.playerLevel++;
	}

	/**
	 * @return the strength
	 */
	public static int getStrength() {
		return strength;
	}

	/**
	 * @param strength
	 *            the strength to set
	 */
	public static void setStrength(int strength) {
		Player.strength = strength;
	}

	/**
	 * @return the health
	 */
	public static int getHealth() {
		return health;
	}

	/**
	 * @param health
	 *            the health to set
	 */
	public static void setHealth(int health) {
		Player.health = health;
	}

	/**
	 * 
	 * @param gear
	 *            the gear to equip
	 */
	public static void equip(Equipment gear) {
		Player.gear.equip(gear);
	}

	public static void addInventory(Equipment item) {
		inventory.add(item);
	}

	public static void remInventory(Equipment item) {
		inventory.remove(item);
	}
	
	public static void takeDamage(Damage dmg){
		damage.add(dmg);
	}
	
	public static void attack(Damage dmg, Enemy enemy){
		enemy.takeDamage(dmg);
	}

}
