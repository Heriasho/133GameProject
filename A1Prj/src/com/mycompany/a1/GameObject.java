package com.mycompany.a1;

public abstract class GameObject extends GameWorld {
	private int size;
	private int color;
	private float locationX;
	private float locationY;
	
	private void size() {
		
	}
	private float getLocationX(float x){
		return x;
	}
	private float getLocationY(float y){
		return y;
	}
	private int getColor(int color){
		return color;
	}
	private void setLocationX(float x){
		this.locationX = x;
	}
	private void setLocationY(float y){
		this.locationY = y;
	}
	private void setColor(int color){
		this.color = color;
	}
	/* Controls the movement of the Opponents */
	private void move() {
		
	}
	/* Controls the movement of the Rescuers */
	private void guided() {
		
	}
	private void moveLeft(){
		
	}
	private void moveRight(){
		
	}
	private void moveUp(){
		
	}
	private void moveDown(){
		
	}
	private void jumpToLocation(){
		
	}
	
}