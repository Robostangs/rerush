package org.usfirst.frc.team548.robot;

public class Elevator {
	
	private static Elevator instance = null;
	
	private Elevator() {
		ElevatorMotors.getInstance();
	}
	
	public static Elevator getInstance() {
		if(instance == null) {
			instance = new Elevator();
		}
		return instance;
	}
	
	public static void moveArm(double power) {
		ElevatorMotors.setPower(power);
	}


}
