package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Solenoid;


public class DriveTrain {

	private static DriveTrain instance = null; 
	private static Solenoid strafeSolenoid;
	private static Gyro gyro;
	private static boolean gyroInt = false;
	private static double gyroInitAngle = 0;
	private static boolean encodersInit = false;

	private DriveTrain() {
		gyro = new Gyro(Constants.DT_GYRO_POS);
		strafeSolenoid = new Solenoid(Constants.DT_STRAFE_SOL_POS);
		//strafeEncoder = new Encoder(Constants.DT_STRAFE_ENCODER_POS_1, Constants.DT_STRAFE_ENCODER_POS_2);
	}

	public static DriveTrain getInstance() {
		if(instance == null) {
			instance = new DriveTrain();
		}
		return instance;
	}

	public static void humanDrive(double left, double right) {
		if(Math.abs(left) < Constants.DT_HUMAN_DRIVE_THRESHOLD && Math.abs(right) < Constants.DT_HUMAN_DRIVE_THRESHOLD) {
			DriveMotors.drive(0, 0);
		} else {
			DriveMotors.drive(left, right);
		}
	}

	public static void humanSlowDrive(double left, double right) {
		if(Math.abs(left) < Constants.DT_HUMAN_DRIVE_THRESHOLD && Math.abs(right) < Constants.DT_HUMAN_DRIVE_THRESHOLD) {
			DriveMotors.drive(0, 0);
		} else {
			DriveMotors.drive(left*Constants.DT_SLOW_DRIVE_MULTIPLIER, right*Constants.DT_SLOW_DRIVE_MULTIPLIER);
		}
	}
	
	public static void humanSuperSlowDrive(double left, double right) {
		if(Math.abs(left) < Constants.DT_HUMAN_DRIVE_THRESHOLD && Math.abs(right) < Constants.DT_HUMAN_DRIVE_THRESHOLD) {
			DriveMotors.drive(0, 0);
		} else {
			DriveMotors.drive(left*Constants.DT_SUPER_SLOW_DRIVE_MULTIPLIER, right*Constants.DT_SUPER_SLOW_DRIVE_MULTIPLIER);
		}
	}

	public static void humanDriveStrafe(double power) {

		if(Math.abs(power) < Constants.DT_HUMAN_STRAFE_THRESHOLD) {
			DriveMotors.driveStrafe(0);
		} else {
			DriveMotors.driveStrafe(power);
		}
	}
	
	public static void humanSlowDriveStrafe(double power) {

		if(Math.abs(power) < Constants.DT_HUMAN_STRAFE_THRESHOLD) {
			DriveMotors.driveStrafe(0);
		} else {
			DriveMotors.driveStrafe(power*Constants.DT_SLOW_STRAFE_MULTIPLIER);
		}
	}
	
	public static void humanSuperSlowDriveStrafe(double power) {

		if(Math.abs(power) < Constants.DT_HUMAN_STRAFE_THRESHOLD) {
			DriveMotors.driveStrafe(0);
		} else {
			DriveMotors.driveStrafe(power*Constants.DT_SUPER_SLOW_STRAFE_MULTIPLIER);
		}
	}

	public static void setStrafeDown() {
		strafeSolenoid.set(false);
	}

	public static void setStrafeUp() {
		strafeSolenoid.set(true);
	}

	public static void strafeStraight(double power) {
		if(!gyroInt) {
			gyro.reset();
			gyroInitAngle = getGyroAngle();
			gyroInt = true;
		} else {
			if(gyroInitAngle < getGyroAngle()) {
				DriveMotors.drive(Constants.DT_STRAFE_MOD*power, -Constants.DT_STRAFE_MOD*power);
			}  else if(gyroInitAngle > getGyroAngle()) {
				DriveMotors.drive(-Constants.DT_STRAFE_MOD*power, Constants.DT_STRAFE_MOD*power);
			} else {
				DriveMotors.drive(0, 0);
			}
		}
	}
	
	public static double getGyroAngle() {
		return gyro.getAngle();
	}
	
	public static void resetGyro() {
		gyro.reset();
		gyroInt = false;
	}
	
	public static boolean isAtDistance(double setpoint) {
		if(setpoint != DriveMotors.getEncoderAverage()) {
			return false;
		}
		return true;
	}
	
	public static void driveDistance(double position) {
		if(!encodersInit) {
			DriveMotors.resetEncoders();
			encodersInit = true;
		}
		
		if(!isAtDistance(position)) {		
			if(position > DriveMotors.getEncoderAverage()) {
				driveStraight(Constants.DRIVE_DISTANCE_SPEED_LEFT, Constants.DRIVE_DISTANCE_SPEED_RIGHT);
			} else if(position < ElevatorMotors.getEncoderAverage()) {
				driveStraight(-Constants.DRIVE_DISTANCE_SPEED_LEFT, -Constants.DRIVE_DISTANCE_SPEED_RIGHT);
			} 
		} else {
			DriveMotors.stopMotors();
		}	
	}
	
	public static void turn(double angle) {
		if(!gyroInt) {
			gyroInitAngle = gyro.getAngle();
			gyroInt = true;
		}
		if(!isAtTurn(angle)) {
			if(angle > gyro.getAngle()) {
				DriveMotors.drive(Constants.DRIVE_TURN_SPEED_LEFT, -Constants.DRIVE_TURN_SPEED_RIGHT);
			} else if(angle < gyro.getAngle()) {
				DriveMotors.drive(-Constants.DRIVE_DISTANCE_SPEED_LEFT, Constants.DRIVE_DISTANCE_SPEED_RIGHT);
			}
		} else {
			DriveMotors.stopMotors();
		}
	}
	
	public static boolean isAtTurn(double angle) {
		if(angle != gyro.getAngle()) {
			return false;
		} else {
			return true;
		}
		
	}
	
	public static void driveStraight(double left, double right) {
		left = -left;
		right = -right;
		if(DriveMotors.getLeftEncoderPosition() > DriveMotors.getRightEncoderPosition()) {
			DriveMotors.drive((left*Constants.DRIVE_STRAIGHT_SLOW_MOD), (right*Constants.DRIVE_STRAIGHT_FAST_MOD));
		} else if(DriveMotors.getLeftEncoderPosition() < DriveMotors.getRightEncoderPosition()) {
			DriveMotors.drive((left*Constants.DRIVE_STRAIGHT_FAST_MOD), (right*Constants.DRIVE_STRAIGHT_SLOW_MOD));
		} else {
			DriveMotors.drive(left, right);
		}
	}


}
