package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;

public class Elevator {
	
	private static Elevator instance = null;
	private static Encoder leftEncoder, rightEncoder;
	private static DigitalInput limitSwitch;
	private static Solenoid containerSolenoid;
	
	private Elevator() {
		ElevatorMotors.getInstance();
		leftEncoder = new Encoder(Constants.ELEVATOR_LEFT_ENCODER_POS_1, Constants.DT_LEFT_ENCODER_POS_2);
		rightEncoder = new Encoder(Constants.ELEVATOR_RIGHT_ENCODER_POS_1, Constants.ELEVATOR_RIGHT_ENCODER_POS_2);
		limitSwitch = new DigitalInput(Constants.ELEVATOR_LIMIT_SWITCH_POS);
		containerSolenoid = new Solenoid(Constants.ELEVATOR_CONTAINER_SOL_POS);
	}
	
	public static Elevator getInstance() {
		if(instance == null) {
			instance = new Elevator();
		}
		return instance;
	}
	
	public static void moveElevator(double power) {
		ElevatorMotors.setPower(power);
	}
	
	public static void resetEncoders() {
		leftEncoder.reset();
		rightEncoder.reset();
	}
	
	public static void setContainerGrabberThing(boolean value) {
		containerSolenoid.set(value);
	}
	
	public static boolean getElevatorSwitch() {
		return limitSwitch.get();
	}

}
