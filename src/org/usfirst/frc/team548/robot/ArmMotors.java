package org.usfirst.frc.team548.robot;

public class ArmMotors {
	
	public ArmMotors instance = null;
	
	private ArmMotors() {
	}
	
	public ArmMotors getInstance() {
		if(instance == null) {
			instance = new ArmMotors();
		}
		return instance;
	}

}
