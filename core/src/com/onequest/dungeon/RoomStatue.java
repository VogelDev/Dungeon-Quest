package com.onequest.dungeon;

import java.util.Arrays;

import com.onequest.dungeon.coordinates.Position;


public class RoomStatue extends RoomObject {

	public RoomStatue(Position pos, Position worldLoc, Position size, boolean[] isMovable) {
		super(pos, worldLoc, size, isMovable);
		this.image = TileImages.getTileImages(TileImages.STATUE);
		float sizeRatio = image.getWidth() / size.x;
		this.size.x = size.x;
		this.size.y = size.x * 2;
		setBounds();
	}
	
	public RoomStatue(Position pos, Position worldLoc, Position size, boolean isMovable) {
		super(pos, worldLoc, size, isMovable);
		this.image = TileImages.getTileImages(TileImages.STATUE);
		float sizeRatio = image.getWidth() / size.x;
		this.size.x = size.x;
		this.size.y = size.x * 2;
		setBounds();
	}

	public RoomStatue(Position pos, Position worldLoc, Position size, boolean[] isMovable, int statueType) {
		super(pos, worldLoc, size, isMovable);
		this.image = TileImages.getTileImages(statueType);
		float sizeRatio = image.getWidth() / size.x;
		this.size.x = size.x;
		this.size.y = size.x * 2;
		setBounds();
	}
	
	public RoomStatue(Position pos, Position worldLoc, Position size, boolean isMovable, int statueType) {
		super(pos, worldLoc, size, isMovable);
		this.image = TileImages.getTileImages(statueType);
		float sizeRatio = image.getWidth() / size.x;
		this.size.x = size.x;
		this.size.y = size.x * 2;
		setBounds();
	}
	
	@Override
	public void move(Position playerPos){
		super.move(playerPos);
		
		Arrays.fill(isMovable, false);
	}

}
