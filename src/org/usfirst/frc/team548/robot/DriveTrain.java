package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class DriveTrain {

	private static DriveTrain instance = null; 
	private static Solenoid strafeSolenoid;
	private static double gyroInitAngle = 0;
	private static boolean encodersInit = false, gyroInit = false;

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

	
	
	
	
	public static boolean isAtDistance(double setpoint) {
		if(setpoint != DriveMotors.getEncoderAverage()) {
			return false;
		}
		return true;
	}
	
	public static void driveDistance(double position, double speed) {
		if(!encodersInit) {
			DriveMotors.resetEncoders();
			encodersInit = true;
		}
		
		if(position > 0) {
			if(position > DriveMotors.getRightEncoderPosition()) {
				DriveMotors.drive(speed, -speed);
			} else if(position <= DriveMotors.getRightEncoderPosition()) {
				DriveMotors.stopMotors();
			} 
		} else if(position < 0) {
			if(position < DriveMotors.getRightEncoderPosition()) {
				DriveMotors.drive(-speed, speed);
			} else if(position >= DriveMotors.getRightEncoderPosition()) {
				DriveMotors.stopMotors();
			} 
		}
	}
	
	public static void turnRightDistance(double position, double speed) {
		if(!encodersInit) {
			DriveMotors.resetEncoders();
			encodersInit = true;
		}
		
		if(position > 0) {
			if(position > DriveMotors.getRightEncoderPosition()) {
				DriveMotors.drive(-speed, -speed);
			} else if(position <= DriveMotors.getRightEncoderPosition()) {
				DriveMotors.stopMotors();
			} 
		} else if(position < 0) {
			if(position < DriveMotors.getRightEncoderPosition()) {
				DriveMotors.drive(speed, speed);
			} else if(position > DriveMotors.getRightEncoderPosition()) {
				DriveMotors.stopMotors();
			} 
		}
	}
	

	
	public static void driveStraight(double left, double right) {
		left = -left;
		//right = -right;
		if(Math.abs(DriveMotors.getLeftEncoderPosition()) > Math.abs(DriveMotors.getRightEncoderPosition())) {
			DriveMotors.drive((left*Constants.DRIVE_STRAIGHT_SLOW_MOD), (right*Constants.DRIVE_STRAIGHT_FAST_MOD));
			SmartDashboard.putString("IDK", "left");
		} else if(Math.abs(DriveMotors.getLeftEncoderPosition()) < Math.abs(DriveMotors.getRightEncoderPosition())) {
			DriveMotors.drive((left*Constants.DRIVE_STRAIGHT_FAST_MOD), (right*Constants.DRIVE_STRAIGHT_SLOW_MOD));
			SmartDashboard.putString("IDK", "right");
		} else {
			DriveMotors.drive(left, right);
		}
		SmartDashboard.putNumber("DDK", (DriveMotors.getLeftEncoderPosition()/Math.abs(DriveMotors.getRightEncoderPosition())));
	}


}
