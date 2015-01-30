package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;


public class DriveTrain {
	
	private static DriveTrain instance = null; 
	private static Solenoid strafeSolenoid;
	private static Encoder leftEncoder, rightEncoder;
	
	private DriveTrain() {
		DriveMotors.getInstance();
		strafeSolenoid = new Solenoid(Constants.DT_STRAFE_SOL_POS);
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
		
		if(Math.abs(power) < Constants.DT_HUMAN_DRIVE_THRESHOLD) {
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
}
