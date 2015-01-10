package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.CANTalon;

public class DriveMotors {
	
	private static DriveMotors instance = null;
	private static CANTalon rightFront, rightBack, leftFront, leftBack, centerBack, centerFront;
	
	private DriveMotors() {
    	rightFront = new CANTalon(Constants.DT_RIGHT_FRONT_TALON_POS);
    	rightBack = new CANTalon(Constants.DT_RIGHT_BACK_TALON_POS);
    	leftFront = new CANTalon(Constants.DT_LEFT_FRONT_TALON_POS);
    	leftBack = new CANTalon(Constants.DT_LEFT_BACK_TALON_POS);
    	centerBack = new CANTalon(Constants.DT_CENTER_BACK_TALON_POS);
    	centerFront = new CANTalon(Constants.DT_CENTER_FRONT_TALON_POS);
	}

	public static DriveMotors getInstance() {
		if(instance == null) {
			instance = new DriveMotors();
		}
		return instance;
	}
	
	public static void drive(double left, double right) {
		rightFront.set(-left);
		rightBack.set(-left);
		leftFront.set(right);
		leftBack.set(right);
	}

	
	public static void driveCenter(double power) {
		centerBack.set(power);
		centerFront.set(-power);
	}
	
	public static void stopMotors() {
		rightFront.set(0);
		rightBack.set(0);
		leftFront.set(0);
		leftBack.set(0);
		centerBack.set(0);
		centerFront.set(0);
	}

}
