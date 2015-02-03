package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.CANTalon;

public class DriveMotors {
	
	private static DriveMotors instance = null;
	private static CANTalon rightFront, rightBack, leftFront, leftBack, centerStrafe;
	
	private DriveMotors() {
    	rightFront = new CANTalon(Constants.DT_RIGHT_FRONT_TALON_POS);
    	rightBack = new CANTalon(Constants.DT_RIGHT_BACK_TALON_POS);
    	leftFront = new CANTalon(Constants.DT_LEFT_FRONT_TALON_POS);
    	leftBack = new CANTalon(Constants.DT_LEFT_BACK_TALON_POS);
    	centerStrafe = new CANTalon(Constants.DT_CENTER_STRAFE_TALON_POS);
	}

	public static DriveMotors getInstance() {
		if(instance == null) {
			instance = new DriveMotors();
		}
		return instance;
	}
	
	public static void drive(double left, double right) {
		rightFront.set(right);
		rightBack.set(right);
		leftFront.set(-left);
		leftBack.set(-left);
	}

	
	public static void driveStrafe(double power) {
		centerStrafe.set(-power);
	}
	
	public static void stopMotors() {
		rightFront.set(0);
		rightBack.set(0);
		leftFront.set(0);
		leftBack.set(0);
		centerStrafe.set(0);
	}

	public static double getLeftEncoderPosition() {
		return leftBack.getEncPosition();
	}
	
	public static double getRightEncoderPosition() {
		return rightBack.getEncPosition();
	}
	
	public static double getLeftEncoderVelocity() {
		return leftBack.getEncVelocity();
	}
	
	public static double getRightEncoderVelocity() {
		return rightBack.getEncVelocity();
	}
	
	public static void resetEncoders() {
		rightBack.setPosition(0.0);
		leftBack.setPosition(0.0);
	}

}
