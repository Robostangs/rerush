package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Solenoid;


public class DriveTrain {

	private static DriveTrain instance = null; 
	private static Solenoid strafeSolenoid;
	private static Gyro gyro;
	private static boolean gyroInt = false;
	private static double gyroAngle = 0;

	private DriveTrain() {
		gyro = new Gyro(Constants.DT_GYRO_POS);
		//strafeSolenoid = new Solenoid(Constants.DT_STRAFE_SOL_POS);
		//strafeEncoder = new Encoder(Constants.DT_STRAFE_ENCODER_POS_1, Constants.DT_STRAFE_ENCODER_POS_2);
		//resetEncoders();
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
		strafeSolenoid.set(true);
	}

	public static void setStrafeUp() {
		strafeSolenoid.set(false);
	}

	public static void strafeStright(double power) {
		if(!gyroInt) {
			gyro.reset();
			gyroAngle = getGyroAngle();
			gyroInt = true;
		} else {
			if(gyroAngle < getGyroAngle()) {
				DriveMotors.drive(Constants.DT_STRAFE_MOD*power, -Constants.DT_STRAFE_MOD*power);
			}  else if(gyroAngle > getGyroAngle()) {
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


}
