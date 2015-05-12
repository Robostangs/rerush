package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.Solenoid;


public class DriveTrain {

	private static DriveTrain instance = null; 
	private static Solenoid strafeSolenoid;

	private DriveTrain() {
		
		strafeSolenoid = new Solenoid(Constants.DRIVE_STRAFE_SOL_POS);
		//strafeEncoder = new Encoder(Constants.DRIVE_STRAFE_ENCODER_POS_1, Constants.DRIVE_STRAFE_ENCODER_POS_2);
	}

	public static DriveTrain getInstance() {
		if(instance == null) {
			instance = new DriveTrain();
		}
		return instance;
	}

	public static void humanDrive(double left, double right) {
		if(Math.abs(left) < Constants.DRIVE_HUMAN_DRIVE_THRESHOLD && Math.abs(right) < Constants.DRIVE_HUMAN_DRIVE_THRESHOLD) {
			DriveMotors.drive(0, 0);
		} else {
			DriveMotors.drive(left, right);
		}
	}

	public static void humanSlowDrive(double left, double right) {
		if(Math.abs(left) < Constants.DRIVE_HUMAN_DRIVE_THRESHOLD && Math.abs(right) < Constants.DRIVE_HUMAN_DRIVE_THRESHOLD) {
			DriveMotors.drive(0, 0);
		} else {
			DriveMotors.drive(left*Constants.DRIVE_SLOW_DRIVE_MULTIPLIER, right*Constants.DRIVE_SLOW_DRIVE_MULTIPLIER);
		}
	}
	
	public static void humanSuperSlowDrive(double left, double right) {
		if(Math.abs(left) < Constants.DRIVE_HUMAN_DRIVE_THRESHOLD && Math.abs(right) < Constants.DRIVE_HUMAN_DRIVE_THRESHOLD) {
			DriveMotors.drive(0, 0);
		} else {
			DriveMotors.drive(left*Constants.DRIVE_SUPER_SLOW_DRIVE_MULTIPLIER, right*Constants.DRIVE_SUPER_SLOW_DRIVE_MULTIPLIER);
		}
	}

	public static void humanDriveStrafe(double power) {

		if(Math.abs(power) < Constants.DRIVE_HUMAN_STRAFE_THRESHOLD) {
			DriveMotors.driveStrafe(0);
		} else {
			DriveMotors.driveStrafe(power);
		}
	}
	
	public static void humanSlowDriveStrafe(double power) {

		if(Math.abs(power) < Constants.DRIVE_HUMAN_STRAFE_THRESHOLD) {
			DriveMotors.driveStrafe(0);
		} else {
			DriveMotors.driveStrafe(power*Constants.DRIVE_SLOW_STRAFE_MULTIPLIER);
		}
	}
	
	public static void humanSuperSlowDriveStrafe(double power) {

		if(Math.abs(power) < Constants.DRIVE_HUMAN_STRAFE_THRESHOLD) {
			DriveMotors.driveStrafe(0);
		} else {
			DriveMotors.driveStrafe(power*Constants.DRIVE_SUPER_SLOW_STRAFE_MULTIPLIER);
		}
	}

	public static void setStrafeDown() {
		strafeSolenoid.set(true);
	}

	public static void setStrafeUp() {
		strafeSolenoid.set(false);
	}

}
