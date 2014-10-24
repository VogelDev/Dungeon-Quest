package com.onequest.dungeon.equipment;
/**
 * 
 * @author ccobb
 * @version 0.0.0001
 */
public class Equipment {

	int reqLvl;
	int strength;
	int health;
	String name;
	
	public Equipment(){
		reqLvl = 1;
		strength = 1;
		health = 1;
		name = "";
	}

	public Equipment(int requiredLevel, int strength, int health, String name) {
		super();
		this.reqLvl = requiredLevel;
		this.strength = strength;
		this.health = health;
		this.name = name;
	}

	/**
	 * @return the requiredLevel
	 */
	public int getReqLvl() {
		return reqLvl;
	}

	/**
	 * @param requiredLevel the requiredLevel to set
	 */
	public void setReqLvl(int requiredLevel) {
		this.reqLvl = requiredLevel;
	}

	/**
	 * @return the strength
	 */
	public int getStrength() {
		return strength;
	}

	/**
	 * @param strength the strength to set
	 */
	public void setStrength(int strength) {
		this.strength = strength;
	}

	/**
	 * @return the health
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * @param health the health to set
	 */
	public void setHealth(int health) {
		this.health = health;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		Equipment tmp;
		
		if(obj instanceof Equipment){
			tmp = (Equipment)obj;
			if(tmp.getName().equals(this.name) && tmp.getReqLvl() == this.reqLvl){
				return true;
			}
		}else{
			return false;
		}
		
		return false;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
}