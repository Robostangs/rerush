package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;

public class ElevatorMotors{
	
	private static ElevatorMotors instance = null;
	private static CANTalon leftMotor, rightMotor;
	private static TalonEncoder leftEncoder, rightEncoder;
	
	private ElevatorMotors() {
		leftMotor = new CANTalon(Constants.ELEVATOR_LEFT_TALON_POS);
		rightMotor = new CANTalon(Constants.ELEVATOR_RIGHT_TALON_POS);
		leftEncoder = new TalonEncoder(leftMotor);
		rightEncoder = new TalonEncoder(rightMotor);}
	
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

	public static double getEncoderAverage() {
		return ((-leftEncoder.getPosition() + rightEncoder.getPosition()) / 2);
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
	
	public static void setDefaultPID() {
		leftMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		leftMotor.setPID(Constants.ELEVATOR_DEFAULT_P, Constants.ELEVATOR_DEFAULT_I, Constants.ELEVATOR_DEFAULT_D, Constants.ELEVATOR_DEFAULT_F, Constants.ELEVATOR_DEFAULT_IZONE, Constants.ELEVATOR_DEFAULT_RAMP_RATE, Constants.ELEVATOR_DEFAULT_PROFILE);
		//leftMotor.setIZone(Constants.ELEVATOR_DEFAULT_IZONE);
	}
	
	public static void setPIDConstants(double p, double i, double d) {
		leftMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		leftMotor.setPID(p, i, d);
	}
	
	public static CANTalon getCANTalon() {
		return leftMotor;
	}
	
	public static void enablePID() {
		leftMotor.changeControlMode(ControlMode.Position);
		leftMotor.reverseOutput(true);
		rightMotor.changeControlMode(ControlMode.Follower);
		rightMotor.reverseOutput(true);
		rightMotor.set(leftMotor.getDeviceID());
	}
	
	public static void disablePID() {
		leftMotor.changeControlMode(ControlMode.PercentVbus);
		rightMotor.changeControlMode(ControlMode.PercentVbus);
	}
	
	public static void runPID(double setpoint) {
		leftMotor.set(-setpoint);
	}
	
	public static double getPIDError() {
		return leftMotor.getClosedLoopError();
	}
}
