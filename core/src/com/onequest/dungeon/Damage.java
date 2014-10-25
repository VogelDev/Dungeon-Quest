package com.onequest.dungeon;

/**
 * Damage class determine damage dealt
 * @author Rob Vogel
 * @version 0.0.0001
 *
 */
public class Damage {
	float damage;
	int strength;
	int level;
	String type; //not used yet
	
	public Damage(){
		damage = 0;
		type = "";
	}
	
	public Damage(float damage){
		this.damage = damage;
		type = "";
	}
	
	public Damage(int strength, int level){
		this.strength = strength;
		this.level = level;
	}
}
