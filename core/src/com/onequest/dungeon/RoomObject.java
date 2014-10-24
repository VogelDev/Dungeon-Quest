package com.onequest.dungeon;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.onequest.dungeon.coordinates.Position;

import java.util.Arrays;

/**
 * A room object for obstacles
 * @author Rob Vogel
 *
 */
public class RoomObject {
	protected Rectangle bounds;
	protected boolean[] isMovable;
	protected boolean isMoving;
	protected Position pos;
	protected Position move;
	protected Position worldLoc;
	protected Texture image;
	protected Vector2 loc;
	protected Position size;
	
	public RoomObject(Position pos, Position worldLoc, Position size, boolean[] isMovable){
		this.pos = pos.clone();
		//this.size = new Position(size.x, size.y);
		this.size = size.clone();
		this.worldLoc = worldLoc.clone();
		
		if(isMovable == null){
			this.isMovable = new boolean[4];
			Arrays.fill(this.isMovable, false);
		}else{
			this.isMovable = isMovable.clone();
		}
		
		setBounds();
	}
	
	public RoomObject(Position pos, Position worldLoc, Position size, boolean isMovable){
		this.pos = pos.clone();
		//this.size = new Position(size.x, size.y);
		this.size = size.clone();
		this.worldLoc = worldLoc.clone();

		this.isMovable = new boolean[4];
		
		if(isMovable == false){
			Arrays.fill(this.isMovable, false);
		}else{
			Arrays.fill(this.isMovable, true);	
		}
		
		setBounds();
	}
	
	public Position getPos(){
		return pos;
	}
	
	public Position getWorldLoc(){
		return worldLoc;
	}
	
	public Rectangle getBounds(){
		return bounds;
	}
	
	public Texture getImage(){
		return image;
	}
	
	public void setBounds(){
		loc = new Vector2(pos.x, pos.y);
		if(image != null){
			bounds = new Rectangle(pos.x, pos.y, size.x, size.y);
		}
		
		move = new Position(0,0);
	}
	
	public void move(Position playerPos) {
		loc.x = pos.x;
		loc.y = pos.y;
		
		if (playerPos.equals(Position.down(pos)) && isMovable[0]) {
			pos = Position.up(pos);
			move = new Position(0, 1);
		}
		if (playerPos.equals(Position.up(pos)) && isMovable[1]) {
			pos = Position.down(pos);
			move = new Position(0, -1);
		}
		if (playerPos.equals(Position.left(pos)) && isMovable[2]) {
			pos = Position.right(pos);
			move = new Position(1, 0);
		}
		if (playerPos.equals(Position.right(pos)) && isMovable[3]) {
			pos = Position.left(pos);
			move = new Position(-1, 0);
		}
		
		System.out.println("\t\t" + pos + "\n" + move);
	}
	
	public void update(float delta, float speed){
		if(Math.abs(pos.x - loc.x) > .1 || Math.abs(pos.y - loc.y) > .1){
			isMoving = true;
			loc.x += move.x * delta * speed / size.x;
			loc.y += move.y * delta * speed / size.y;
			System.out.println("\t\t loc: " + loc.x + "," + loc.y);
			System.out.println("\t\t pos: " + pos.x + "," + pos.y);
		}else if(!move.equals(Position.zero)){
			isMoving = false;
			pos.x = (int)(loc.x + .2);
			pos.y = (int)(loc.y + .2);
			move = Position.zero;
		}
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	public boolean isMoving(){
		return this.isMoving;
	}
}
