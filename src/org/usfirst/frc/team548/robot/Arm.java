package org.usfirst.frc.team548.robot;

public class Arm {
	
	private static Arm instance = null;
	
	private Arm() {
		ArmMotors.getInstance();
	}
	
	public static Arm getInstance() {
		if(instance == null) {
			instance = new Arm();
		}
		return instance;
	}
	
	public static void moveArm(double power) {
		ArmMotors.setPower(power);
	}

}
