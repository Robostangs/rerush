package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.Timer;

public class Autonomous {
	
	private static Autonomous instance;
	private static int mode = 1;
	private static Timer autoTimer;
	
	private Autonomous() {

	}
	
	public static Autonomous getInstance() {
		if(instance == null) {
			instance = new Autonomous();
		}
		return instance;
	}
	
	public static void run() {
		
		
		switch (Autonomous.getAutoMode()) {
		// Drive into auto zone
		case 1:
			Autonomous.driveToAutoZone();
			break;
		// Drive into auto zone with container
		case 2:
			Autonomous.driveToAutoZoneWithContainer();
			break;
		// Drive into auto zone with container and tote
		case 3:
			Autonomous.driveToAutoZoneWithToteAndContainer();
			break;
		// Pick up container and tote do 180 pick up tote drive straight pick up last tote then turn into auto zone
		case 4:
			Autonomous.thinkOfAGoodNameForThidMethodForAuto4();
			break;
		//  Pick up container and tote do 180 pick up tote drive around container pick up last tote then turn into auto zone
		case 5: 
			Autonomous.godTierAuto();
			break;
		default: 
			Autonomous.driveToAutoZone();
			break;
		}
	}
	
	private static void driveToAutoZone() {
		if(autoTimer.get() <= 7) {
			DriveTrain.driveDistance(Constants.AUTON_1_DRIVE_DISTANCE_ROBOT_SET);
		} else {
			DriveMotors.stopMotors();
		}
	}
	
	private static void driveToAutoZoneWithContainer() {
		if(!DriveTrain.isAtDistance(Constants.AUTON_2_DISTANCE_TO_CONTAINER) && autoTimer.get() <= 3) {
			DriveTrain.driveDistance(Constants.AUTON_2_DISTANCE_TO_CONTAINER);
		} else {
			DriveMotors.stopMotors();
			Elevator.setContainerGrabberThingThatPicksUpContainerThingsThatAreRoundAndGreenClosed();
			Elevator.setElevatorUpOneLevel();
			if(!DriveTrain.isAtDistance(Constants.AUTON_2_DISTANCE_FROM_CONTAINER) && autoTimer.get() <= 4) {
				DriveTrain.driveDistance(Constants.AUTON_2_DISTANCE_FROM_CONTAINER);
			} else {
				DriveMotors.stopMotors();
				Elevator.setElevatorDownOneLevel();
				Elevator.setContainerGrabberThingThatPicksUpContainerThingsThatAreRoundAndGreenOpen();
			}
		}
	}
	
	private static void driveToAutoZoneWithToteAndContainer() {
		if(!DriveTrain.isAtDistance(Constants.AUTON_3_DISTANCE_TO_CONTAINER)) {
			DriveTrain.driveDistance(Constants.AUTON_2_DISTANCE_TO_CONTAINER);
		} else {
			Elevator.setContainerGrabberThingThatPicksUpContainerThingsThatAreRoundAndGreenClosed();
			Elevator.setElevatorUpOneLevel();
			if(!DriveTrain.isAtDistance(Constants.AUTON_3_DISTANCE_TO_TOTE)) {
				DriveTrain.driveDistance(Constants.AUTON_3_DISTANCE_TO_TOTE);
				Ingestor.setIngestorIn();
				Ingestor.setIngestorPower(1);
			} else {
				Ingestor.setIngestorPower(0);
				DriveMotors.stopMotors();
				Elevator.setElevatorUpOneLevel();
				if(!DriveTrain.isAtTurn(Constants.AUTON_3_TURN_ANGLE)) {
					DriveTrain.turn(Constants.AUTON_3_TURN_ANGLE);
				} else {
					DriveMotors.stopMotors();
					if(!DriveTrain.isAtDistance(Constants.AUTON_3_DISTANCE_TO_AUTO_ZONE)) {
						DriveTrain.driveDistance(Constants.AUTON_3_DISTANCE_TO_AUTO_ZONE);
					} else {
						DriveMotors.stopMotors();
						Elevator.setElevatorToLevel(0);
						Elevator.setContainerGrabberThingThatPicksUpContainerThingsThatAreRoundAndGreenOpen();
					}
				}
			}
		}
		
	}
	
	private static void thinkOfAGoodNameForThidMethodForAuto4() {
		
	}
	
	private static void godTierAuto() {
		
	}
	
	public static void setAutoMode(int mode) {
		Autonomous.mode = mode;
	}
	
	public static void startTimer() {
		Autonomous.autoTimer.reset();
		Autonomous.autoTimer.start();
	}
	
	public static int getAutoMode() {
		return Autonomous.mode;
	}
}
