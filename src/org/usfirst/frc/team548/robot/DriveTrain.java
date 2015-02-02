package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class DriveTrain {

	private static DriveTrain instance = null; 
	private static Solenoid strafeSolenoid;
	private static Encoder leftEncoder, rightEncoder, strafeEncoder;
	private static Gyro gyro;
	private static boolean gyroInt = false;
	private static double gyroAngle = 0;

	private DriveTrain() {
		DriveMotors.getInstance();
		gyro = new Gyro(Constants.DT_GYRO_POS);
		//strafeSolenoid = new Solenoid(Constants.DT_STRAFE_SOL_POS);
		//leftEncoder = new Encoder(Constants.DT_LEFT_ENCODER_POS_1, Constants.DT_LEFT_ENCODER_POS_2);
		//rightEncoder = new Encoder(Constants.DT_RIGHT_ENCODER_POS_1, Constants.DT_RIGHT_ENCODER_POS_2);
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

	public static void humanDriveStrafe(double power) {

		if(Math.abs(power) < Constants.DT_HUMAN_STRAFE_THRESHOLD) {
			DriveMotors.driveStrafe(0);
		} else {

			DriveMotors.driveStrafe(power);
		}
	}

	public static void setStrafeDown() {
		strafeSolenoid.set(true);
	}

	public static void setStrafeUp() {
		strafeSolenoid.set(false);
	}

	public static void resetEncoders() {
		leftEncoder.reset();
		rightEncoder.reset();
		strafeEncoder.reset();
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
