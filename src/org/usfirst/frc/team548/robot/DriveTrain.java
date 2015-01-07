package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.Talon;

public class DriveTrain {
	
	private static DriveTrain instance = null; 
	private static Talon rightFront, rightMid, rightBack, leftFront, leftMid, leftBack, center;
	
	private DriveTrain() {
    	rightFront = new Talon(Constants.DT_RIGHT_FRONT_TALON_POS);
    	rightMid = new Talon(Constants.DT_RIGHT_MID_TALON_POS);
    	rightBack = new Talon(Constants.DT_RIGHT_BACK_TALON_POS);
    	leftFront = new Talon(Constants.DT_LEFT_FRONT_TALON_POS);
    	leftMid = new Talon(Constants.DT_LEFT_MID_TALON_POS);
    	leftBack = new Talon(Constants.DT_LEFT_BACK_TALON_POS);
    	center = new Talon(Constants.DT_CENTER_TALON_POS);
	}
	
	public static DriveTrain getInstance() {
		if(instance == null) {
			instance = new DriveTrain();
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
	
	public static void humanDrive(double left, double right) {
		if(Math.abs(left) < 0.2 && Math.abs(right) < 0.2) {
			drive(0, 0);
		} else {
			drive(left, right);
		}
	}
}
