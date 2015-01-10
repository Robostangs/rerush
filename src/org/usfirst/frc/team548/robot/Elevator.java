package org.usfirst.frc.team548.robot;

public class Elevator {
	
	public Elevator instance = null;
	
	private Elevator() {
	}
	
	public Elevator getInstance() {
		if(instance == null) {
			instance = new Elevator();
		}
		return instance;
	}
	

}
