package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.hal.CanTalonSRX;

public class DriveMotors {
	
	private static DriveMotors instance = null;
	private static CanTalonSRX rightFront, rightBack, leftFront, leftBack, centerBack, centerFront;
	
	private DriveMotors() {
    	rightFront = new CanTalonSRX(Constants.DT_RIGHT_FRONT_TALON_POS);
    	rightBack = new CanTalonSRX(Constants.DT_RIGHT_BACK_TALON_POS);
    	leftFront = new CanTalonSRX(Constants.DT_LEFT_FRONT_TALON_POS);
    	leftBack = new CanTalonSRX(Constants.DT_LEFT_BACK_TALON_POS);
    	centerBack = new CanTalonSRX(Constants.DT_CENTER_BACK_TALON_POS);
    	centerFront = new CanTalonSRX(Constants.DT_CENTER_FRONT_TALON_POS);
	}

	public static DriveMotors getInstance() {
		if(instance == null) {
			instance = new DriveMotors();
		}
		return instance;
	}
	
	public static void drive(double left, double right) {
		rightFront.Set(-left);
		rightBack.Set(-left);
		leftFront.Set(right);
		leftBack.Set(right);
	}

	
	public static void driveCenter(double power) {
		centerBack.Set(power);
		centerFront.Set(-power);
	}
	
	public static void stopMotors() {
		rightFront.Set(0);
		rightBack.Set(0);
		leftFront.Set(0);
		leftBack.Set(0);
		centerBack.Set(0);
		centerFront.Set(0);
	}

}
