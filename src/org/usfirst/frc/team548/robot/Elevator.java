package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;

public class Elevator {
	
	private static Elevator instance = null;
	private static DigitalInput botLimitSwitch;
	private static Solenoid containerSolenoid;
	private static int currentElevatorPos = 0;
	private static boolean pidConfigured = false;
	
	private Elevator() {
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
		if(getBotElevatorSwitch()) {
			ElevatorMotors.resetEncoders();
			if(power < 0) {
				power = 0;
			}
		} if(ElevatorMotors.getEncoderAverage() < 0 && power < 0 || ElevatorMotors.getEncoderAverage() > 21000 && power > 0) {
			power = 0;
		} if (ElevatorMotors.getEncoderAverage() < 1000 && power < 0) {
			power *=.5;
		}
		ElevatorMotors.setPower(power);
	}
	
	public static void stopElevator() {
		ElevatorMotors.disablePID();
		ElevatorMotors.setPower(0);
	}
	
	public static void setContainerGrabberThingThatPicksUpContainerThingsThatAreRoundAndGreenOpen() {
		containerSolenoid.set(true);
	}
	
	public static void setContainerGrabberThingThatPicksUpContainerThingsThatAreRoundAndGreenClosed() {
		containerSolenoid.set(false);
	}
	
	public static boolean getBotElevatorSwitch() {
		return !botLimitSwitch.get();
	}
	
	public static void setElevatorPosition(double position) {
		if(!pidConfigured && ElevatorMotors.getPIDError() > Constants.ELEVATOR_PID_ERROR_TRESHOLD) {
			ElevatorMotors.setDefaultPID();
			ElevatorMotors.enablePID();
			pidConfigured = true;
		} else if(ElevatorMotors.getPIDError() > Constants.ELEVATOR_PID_ERROR_TRESHOLD) {
			ElevatorMotors.runPID(position);
		} else {
			ElevatorMotors.disablePID();
			pidConfigured = false;
		}
	}
/*	
	public static void setElevatorPositionUp(double position) {
			if(position > ElevatorMotors.getEncoderAverage()) {
				if((position-1000) > ElevatorMotors.getEncoderAverage()) {
					moveElevator(-Constants.ELEVATOR_SPEED);
				} else {
					moveElevator(-Constants.ELEVATOR_SPEED/3);
				}
			} else {
				stopElevator();
			}
		}
	
	public static void setElevatorPositionDown(double position) {
		if(position < ElevatorMotors.getEncoderAverage()) {
			if((position+1000) < ElevatorMotors.getEncoderAverage()) {
				if(position > ElevatorMotors.getEncoderAverage()) {
					moveElevator(Constants.ELEVATOR_SPEED);
				} else {
					moveElevator(Constants.ELEVATOR_SPEED/3);
				}
			} else {
				stopElevator();
			}
		}
		
	}
	
	public static void setElevatorToLevelUp(int level) {
		if(Constants.ELEVATOR_LEVELS[level] > ElevatorMotors.getEncoderAverage()) {
				setElevatorPositionDown(Constants.ELEVATOR_LEVEL_1_POS);
			} else {
				moveElevator(0);
			}
	}
	
	public static void setElevatorToLevelDown(int level) {
			if(Constants.ELEVATOR_LEVELS[level] < ElevatorMotors.getEncoderAverage()) {
				setElevatorPositionDown(Constants.ELEVATOR_LEVEL_1_POS);
			} else {
				moveElevator(0);
			}
	}
*/	
	public static void setElevatorToLevel(int level) {
		setElevatorPosition(Constants.ELEVATOR_LEVELS[level]);
	}
	
	public static boolean isAtSetpoint(double setpoint) {
		while(setpoint != ElevatorMotors.getEncoderAverage()) {
			return false;
		}
		return true;
	}
	
	public static void setElevatorUpOneLevel() {
		if(currentElevatorPos < 5) {
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
		if(!getBotElevatorSwitch()) {
			ElevatorMotors.setPower(Constants.ELEVATOR_CALIBRATION_DOWN_POWER);
		} else {
			Elevator.stopElevator();
			ElevatorMotors.resetEncoders();
		}
	}

}
