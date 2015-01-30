package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Solenoid;

public class Ingestor {
	private static Ingestor instance = null;
	private static Solenoid leftSolenoid, rightSolenoid;
	private static CANTalon leftMotor, rightMotor;
	
	public static Ingestor getInstance() {
		if(instance == null)  {
			instance = new Ingestor();
		}
		return instance;
	}
	
	private Ingestor() {
		leftSolenoid = new Solenoid(Constants.IN_LEFT_SOL_POS);
		rightSolenoid = new Solenoid(Constants.IN_RIGHT_SOL_POS);
		leftMotor = new CANTalon(Constants.IN_LEFT_TALON_POS);
		rightMotor = new CANTalon(Constants.IN_RIGHT_TALON_POS);
	}
	
	public static void setIngestorOut() {
		leftSolenoid.set(true);
		rightSolenoid.set(true);
	}
	
	public static void setIngestorIn() {
		leftSolenoid.set(false);
		rightSolenoid.set(false);
	}
	
	public static void setIngestorPower(double power) {
		leftMotor.set(power);
		rightMotor.set(power);
	}
	
	
}
