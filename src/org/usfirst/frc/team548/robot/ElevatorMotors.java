package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.PIDOutput;

public class ElevatorMotors implements PIDOutput {
	
	private static ElevatorMotors instance = null;
	private static CANTalon leftMotor, rightMotor;
	private static TalonEncoder leftEncoder, rightEncoder;
	
	private ElevatorMotors() {
		leftMotor = new CANTalon(Constants.ELEVATOR_LEFT_TALON_POS);
		rightMotor = new CANTalon(Constants.ELEVATOR_RIGHT_TALON_POS);
		leftEncoder = new TalonEncoder(leftMotor);
		rightEncoder = new TalonEncoder(rightMotor);
	}
	
	public static ElevatorMotors getInstance() {
		if(instance == null) {
			instance = new ElevatorMotors();
		}
		return instance;
	}
	
	public static void setPower(double power) {
		leftMotor.set(-power);
		rightMotor.set(power);
	}
	
	public void pidWrite(double power) {
		setPower(power);
	}

	public static double getEncoderAverage() {
		return ((leftEncoder.getPosition() + rightEncoder.getPosition()) / 2);
	}
	
	public static void resetEncoders() {
		leftEncoder.reset();
		rightEncoder.reset();
	}
	
	public static double getLeftEncoder() {
		return leftEncoder.getPosition();
	}
	
	public static double getRightEncoder() {
		return rightEncoder.getPosition();
	}
	
	public static double getEncoderError() {
		return (leftEncoder.getPosition() - rightEncoder.getPosition());
	}

}
