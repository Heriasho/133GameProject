package com.mycompany.a3;

public class Random {
	private static final java.util.Random R = new java.util.Random();
	private static Random instance = new Random();
	
	private Random() {}
	
	public static Random getInstance() {
		if(instance != null) return instance;
		else return instance = new Random();
	}
	
	public int nextInt(int min, int max) {
		return R.nextInt(max - min + 1) + min;
	}
	
	public double nextDouble(double min, double max) {
		return min + (max - min) * R.nextDouble();
	}
}