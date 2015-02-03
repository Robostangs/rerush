package org.usfirst.frc.team548.robot;


import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Solenoid;

public class IngestorMotors {
	private static IngestorMotors instance = null;
	private static CANTalon leftMotor, rightMotor;
	
	public static IngestorMotors getInstance() {
		if(instance == null)  {
			instance = new IngestorMotors();
		}
		return instance;
	}
	
	private IngestorMotors() {
		leftMotor = new CANTalon(Constants.IN_LEFT_TALON_POS);
		rightMotor = new CANTalon(Constants.IN_RIGHT_TALON_POS);
	}
	
	public static void setIngestorPower(double power) {
		leftMotor.set(-power);
		rightMotor.set(power);
	}
	
}

