package org.usfirst.frc.team548.robot;

public class Arm {
	
	public Arm instance = null;
	
	private Arm() {
	}
	
	public Arm getInstance() {
		if(instance == null) {
			instance = new Arm();
		}
		return instance;
	}

}
