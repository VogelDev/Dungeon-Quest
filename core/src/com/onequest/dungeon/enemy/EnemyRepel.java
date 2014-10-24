package com.onequest.dungeon.enemy;

import com.onequest.dungeon.coordinates.Position;

public class EnemyRepel extends Enemy{
	
	public EnemyRepel(){
		super();
		follows = false;
	}

	public EnemyRepel(int playerLevel, Position levelPos) {
		super(playerLevel, levelPos);
		follows = false;
	}

	public EnemyRepel(int playerLevel) {
		super(playerLevel);
		follows = false;
	}
	
	
}
