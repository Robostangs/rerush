package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.Talon;

public class DriveMotors {
	
	private static DriveMotors instance = null;
	private static Talon rightFront, rightMid, rightBack, leftFront, leftMid, leftBack, centerBack, centerFront;
	
	private DriveMotors() {
    	rightFront = new Talon(Constants.DT_RIGHT_FRONT_TALON_POS);
    	rightMid = new Talon(Constants.DT_RIGHT_MID_TALON_POS);
    	rightBack = new Talon(Constants.DT_RIGHT_BACK_TALON_POS);
    	leftFront = new Talon(Constants.DT_LEFT_FRONT_TALON_POS);
    	leftMid = new Talon(Constants.DT_LEFT_MID_TALON_POS);
    	leftBack = new Talon(Constants.DT_LEFT_BACK_TALON_POS);
    	centerBack = new Talon(Constants.DT_CENTER_BACK_TALON_POS);
    	centerFront = new Talon(Constants.DT_CENTER_FRONT_TALON_POS);
	}

	public static DriveMotors getInstance() {
		if(instance == null) {
			instance = new DriveMotors();
		}
		return instance;
	}
	
	public static void drive(double left, double right) {
		rightFront.set(-left);
		rightMid.set(-left);
		rightBack.set(-left);
		leftFront.set(right);
		leftMid.set(right);
		leftBack.set(right);
	}
	
	public static void driveCenter(double power) {
		centerBack.set(power);
		centerFront.set(-power);
	}
}
