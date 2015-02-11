package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.PIDOutput;

public class ElevatorMotors implements PIDOutput {
	
	private static ElevatorMotors instance = null;
	private static CANTalon leftMotor, rightMotor;
	
	private ElevatorMotors() {
		leftMotor = new CANTalon(Constants.ELEVATOR_LEFT_TALON_POS);
		rightMotor = new CANTalon(Constants.ELEVATOR_RIGHT_TALON_POS);
	}
	
	public static ElevatorMotors getInstance() {
		if(instance == null) {
			instance = new ElevatorMotors();
		}
		return instance;
	}
	
	public static void setPower(double power) {
		leftMotor.set(power);
		rightMotor.set(-power);
	}
	
	public void pidWrite(double power) {
		setPower(power);
	}

}
