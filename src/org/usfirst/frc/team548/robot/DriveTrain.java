package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.Talon;

public class DriveTrain {
	
	private static DriveTrain instance = null; 
	
	private DriveTrain() {
		DriveMotors.getInstance();
	}
	
	public static DriveTrain getInstance() {
		if(instance == null) {
			instance = new DriveTrain();
		}
		return instance;
	}

	public static void humanDrive(double left, double right) {
		if(Math.abs(left) < 0.2 && Math.abs(right) < 0.2) {
			DriveMotors.drive(0, 0);
		} else {
			DriveMotors.drive(left, right);
		}
	}
}
