package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;

public class Elevator {
	
	private static Elevator instance = null;
	private static Encoder leftEncoder, rightEncoder;
	private static DigitalInput limitSwitch;
	private static Solenoid containerSolenoid;
	private static int currentElevatorPos;
	
	private Elevator() {
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
	
	public static void stopElevator() {
		ElevatorMotors.setPower(0);
	}
	
	public static void resetEncoders() {
		leftEncoder.reset();
		rightEncoder.reset();
	}
	
	public static void setContainerGrabberThingThatPicksUpContainerThingsThatAreRoundAndGreenOpen() {
		containerSolenoid.set(true);
	}
	
	public static void setContainerGrabberThingThatPicksUpContainerThingsThatAreRoundAndGreenClosed() {
		containerSolenoid.set(false);
	}
	
	public static double getEncoderAverage() {
		return ((leftEncoder.getDistance() + rightEncoder.getDistance()) / 2);
	}
	
	public static boolean getElevatorSwitch() {
		return limitSwitch.get();
	}
	
	public static void setElevatorPosition(double position) {
		while(!isAtSetpoint(position)) {
			if(position > getEncoderAverage()) {
				moveElevator(Constants.ELEVATOR_SPEED);
			} else if(position < getEncoderAverage()) {
				moveElevator(-Constants.ELEVATOR_SPEED);
			} else {
				stopElevator();
			}
		}	
	}
	
	public static void setElevatorToLevel(int level) {
		if(level == 0) {
			setElevatorPosition(Constants.ELEVATOR_LEVEL_0_POS);
			currentElevatorPos = 0;
			resetEncoders();
		} else if(level == 1) {
			setElevatorPosition(Constants.ELEVATOR_LEVEL_1_POS);
			currentElevatorPos = 1;
		} else if(level == 2) {
			setElevatorPosition(Constants.ELEVATOR_LEVEL_2_POS);
			currentElevatorPos = 2;
		} else if(level == 3) {
			setElevatorPosition(Constants.ELEVATOR_LEVEL_3_POS);
			currentElevatorPos = 3;
		} else if(level == 4) {
			setElevatorPosition(Constants.ELEVATOR_LEVEL_4_POS);
			currentElevatorPos = 4;
		} else if(level == 5) {
			setElevatorPosition(Constants.ELEVATOR_LEVEL_5_POS);
			currentElevatorPos = 5;
		} else if(level == 6) {
			setElevatorPosition(Constants.ELEVATOR_LEVEL_6_POS);
			currentElevatorPos = 6;
		}
	}
	
	public static boolean isAtSetpoint(double setpoint) {
		while(setpoint != getEncoderAverage()) {
			return false;
		}
		return true;
	}
	
	public static void setElevatorUpOneLevel() {
		if(currentElevatorPos < 6) {
			int newLevel = currentElevatorPos++;
			setElevatorToLevel(newLevel);
			currentElevatorPos = newLevel;
		}
	}
	
	public static void setElevatorDownOneLevel() {
		if(currentElevatorPos > 0) {
			int newLevel = currentElevatorPos--;
			setElevatorToLevel(newLevel);
			currentElevatorPos = newLevel;
		}
	}

}
