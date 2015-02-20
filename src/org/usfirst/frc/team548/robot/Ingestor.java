package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Ingestor {
	private static Ingestor instance = null;
	private static DoubleSolenoid leftSolenoid, rightSolenoid;
	private static CANTalon leftMotor, rightMotor;
	
	public static Ingestor getInstance() {
		if(instance == null)  {
			instance = new Ingestor();
		}
		return instance;
	}
	
	private Ingestor() {
		leftSolenoid = new DoubleSolenoid(Constants.IN_LEFT_SOL_POS_1, Constants.IN_LEFT_SOL_POS_2);
		rightSolenoid = new DoubleSolenoid(Constants.IN_RIGHT_SOL_POS_1, Constants.IN_RIGHT_SOL_POS_2);
		leftMotor = new CANTalon(Constants.IN_LEFT_TALON_POS);
		rightMotor = new CANTalon(Constants.IN_RIGHT_TALON_POS);
	}
	
	public static void setIngestorIn() {
		leftSolenoid.set(DoubleSolenoid.Value.kForward);
		rightSolenoid.set(DoubleSolenoid.Value.kForward);
	}
	
	public static void setIngestorOut() {
		leftSolenoid.set(DoubleSolenoid.Value.kReverse);
		rightSolenoid.set(DoubleSolenoid.Value.kReverse);
	}
		
	public static void setIngestorPower(double power) {
		leftMotor.set(-power);
		rightMotor.set(power);
	}
	
	public static void setDirection(double power) {
		leftMotor.set(power);
		rightMotor.set(power);
	}
}
