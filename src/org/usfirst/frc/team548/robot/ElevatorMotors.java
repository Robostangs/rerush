package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.Talon;

public class ElevatorMotors {
	
	private static ElevatorMotors instance = null;
	private static Talon leftMotor, rightMotor;
	
	private ElevatorMotors() {
		Talon leftMotor = new Talon(Constants.ELEVATOR_LEFT_TALON_POS);
		Talon rightsMotor = new Talon(Constants.ELEVATOR_RIGHT_TALON_POS);
	}
	
	public static ElevatorMotors getInstance() {
		if(instance == null) {
			instance = new ElevatorMotors();
		}
		return instance;
	}
	
	public static void setPower(double power) {
		leftMotor.set(power);
		rightMotor.set(power);
	}

}
