package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;

public class Elevator {
	
	private static Elevator instance = null;
	private static DigitalInput topLimitSwitch, botLimitSwitch;
	private static Solenoid containerSolenoid;
	private static int currentElevatorPos;
	
	private Elevator() {
		topLimitSwitch = new DigitalInput(Constants.ELEVATOR_TOP_LIMIT_SWITCH_POS);
		botLimitSwitch = new DigitalInput(Constants.ELEVATOR_BOT_LIMIT_SWITCH_POS);
		containerSolenoid = new Solenoid(Constants.ELEVATOR_CONTAINER_SOL_POS);
	}
	
	public static Elevator getInstance() {
		if(instance == null) {
			instance = new Elevator();
		}
		return instance;
	}
	
	public static void moveElevator(double power) {
		if(botLimitSwitch.get() && power < 0) {
			power = 0;
		} else if(topLimitSwitch.get() && power > 0) {
			power = 0;
		}
		ElevatorMotors.setPower(power);
	}
	
	public static void stopElevator() {
		ElevatorMotors.setPower(0);
	}
	
	public static void setContainerGrabberThingThatPicksUpContainerThingsThatAreRoundAndGreenOpen() {
		containerSolenoid.set(true);
	}
	
	public static void setContainerGrabberThingThatPicksUpContainerThingsThatAreRoundAndGreenClosed() {
		containerSolenoid.set(false);
	}
	
	public static boolean getTopElevatorSwitch() {
		return topLimitSwitch.get();
	}
	
	public static boolean getBotElevatorSwitch() {
		return botLimitSwitch.get();
	}
	
	public static void setElevatorPosition(double position) {
		if(!isAtSetpoint(position)) {		
			if(position > ElevatorMotors.getEncoderAverage()) {
				moveElevator(Constants.ELEVATOR_SPEED);
			} else if(position < ElevatorMotors.getEncoderAverage()) {
				moveElevator(-Constants.ELEVATOR_SPEED);
			} 
		} else {
			stopElevator();
		}	
	}
	
	public static void setElevatorToLevel(int level) {
		if(level == 0) {
			setElevatorPosition(Constants.ELEVATOR_LEVEL_0_POS);
		} else if(level == 1) {
			setElevatorPosition(Constants.ELEVATOR_LEVEL_1_POS);
		} else if(level == 2) {
			setElevatorPosition(Constants.ELEVATOR_LEVEL_2_POS);
		} else if(level == 3) {
			setElevatorPosition(Constants.ELEVATOR_LEVEL_3_POS);
		} else if(level == 4) {
			setElevatorPosition(Constants.ELEVATOR_LEVEL_4_POS);
		} else if(level == 5) {
			setElevatorPosition(Constants.ELEVATOR_LEVEL_5_POS);
		} else if(level == 6) {
			setElevatorPosition(Constants.ELEVATOR_LEVEL_6_POS);
		}
		currentElevatorPos = level;
	}
	
	public static boolean isAtSetpoint(double setpoint) {
		while(setpoint != ElevatorMotors.getEncoderAverage()) {
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
	
	public static void calibrateEncoder() {
		if(!botLimitSwitch.get()) {
			ElevatorMotors.setPower(Constants.ELEVATOR_CALIBRATION_DOWN_POWER);
		} else {
			Elevator.stopElevator();
			ElevatorMotors.resetEncoders();
		}
	}

}
