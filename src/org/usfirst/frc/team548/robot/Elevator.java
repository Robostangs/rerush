package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;

public class Elevator {
	
	private static Elevator instance = null;
	private static DigitalInput botLimitSwitch;
	private static Solenoid containerSolenoid;
	private static Timer grabTimer;
	private static int currentLevelSnapshot = 0;
	
	private Elevator() {
		botLimitSwitch = new DigitalInput(Constants.ELEVATOR_BOT_LIMIT_SWITCH_POS);
		containerSolenoid = new Solenoid(Constants.ELEVATOR_CONTAINER_SOL_POS);
		grabTimer = new Timer();
		
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
		} /*if(ElevatorMotors.getLeftEncoder() < 0 && power < Constants.ELEVATOR_INPUT_MIN || ElevatorMotors.getLeftEncoder() > Constants.ELEVATOR_INPUT_MAX && power > 0) {
			power = 0;
		} if (ElevatorMotors.getLeftEncoder() < Constants.ELEVATOR_SLOW_ZONE && power < 0) {
			power = Constants.ELEVATOR_SLOW_SPEED;
		}*/
		ElevatorMotors.setPower(power);
	}
	
	public static void stopElevator() {
		ElevatorMotors.disablePID();
		ElevatorMotors.setPower(0); 
	}
	
	public static void setContainerGrabberThingThatPicksUpContainerThingsThatAreRoundAndGreenOpen() {
		containerSolenoid.set(false);
	}
	
	public static void setContainerGrabberThingThatPicksUpContainerThingsThatAreRoundAndGreenClosed() {
		containerSolenoid.set(true);
	}
	
	public static boolean getBotElevatorSwitch() {
		return !botLimitSwitch.get();
	}
	
	public static void setElevatorPosition(double position) {
		ElevatorMotors.setDefaultPID();
		ElevatorMotors.enablePID();
		ElevatorMotors.runPID(position);
	}
	
	public static void setElevatorToLevel(int level) {
		setElevatorPosition(Constants.ELEVATOR_LEVELS[level]);
	}
	
	public static void setElevatorUpToLevel(int level) {
		setElevatorPosition(Constants.ELEVATOR_LEVELS[level]+Constants.ELEVATOR_OVERSHOOT);
	}
	
	public static void setElevatorDownToLevel(int level) {
		setElevatorPosition(Constants.ELEVATOR_LEVELS[level]+Constants.ELEVATOR_UNDERSHOOT);
	}
	
	public static int getToteZone() {
		for(int i = 1; i < 6; i++) {
			if(Constants.ELEVATOR_LEVELS[i] > ElevatorMotors.getLeftEncoder()) {
				return i-1;
			}
		}
		return 5;
	}
		
	public static boolean isAtSetpoint(double setpoint) {
		while(setpoint != ElevatorMotors.getEncoderAverage()) {
			return false;
		}
		return true;
	}
	
	public static void setElevatorUp() {
		setElevatorUpToLevel(currentLevelSnapshot+1);
	}
	
	public static void setElevatorDown() {
		setElevatorToLevel(currentLevelSnapshot);
	}
	
	public static void setCurrentLevelSnapshot() {
		currentLevelSnapshot = getToteZone();
	}
	
	public static void setCurrentLevelSnapshotToLevel(int level) {
		currentLevelSnapshot = level;
	}
	
	public static void calibrateEncoder() {
		if(!getBotElevatorSwitch()) {
			ElevatorMotors.setPower(Constants.ELEVATOR_CALIBRATION_DOWN_POWER);
		} else {
			Elevator.stopElevator();
			ElevatorMotors.resetEncoders();
		}
	}
	
	public static void autoGrabUp() {
		if(grabTimer.get() == 0) {
			grabTimer.start();
		}
		
		if(grabTimer.get() < 1.39) {
			Ingestor.setIngestorIn();
			Ingestor.setIngestorPower(1);		
		}
		
		if(grabTimer.get() < .75) {
			Ingestor.setIngestorIn();
			setElevatorDownToLevel(currentLevelSnapshot);
		} else if(grabTimer.get() < 1.5) {
			if(grabTimer.get() == 1.4) {
				Ingestor.setIngestorOut();
			}
			setElevatorUpToLevel(currentLevelSnapshot);
		} else if(grabTimer.get() < 1.7) {
			Ingestor.setIngestorNeutral();
			setElevatorUpToLevel(currentLevelSnapshot+1);
		} else if(grabTimer.get() < 3) {
			Ingestor.setIngestorIn();
			grabTimer.stop();
		}
	}
	
	public static void resetTimer() {
		grabTimer.reset();
	}
	
	public static boolean getCanSol() {
		return containerSolenoid.get();
	}
	
	public static void setCanSol(boolean bool) {
		containerSolenoid.set(bool);
	}

}
