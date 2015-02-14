package org.usfirst.frc.team548.robot;

public class Autonomous {
	
	private static Autonomous instance;
	private static boolean hasReachedContainer = false;
	private static boolean done = false;
	private static int mode = 1;
	
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
		if(!done) {
			DriveTrain.driveDistance(Constants.AUTON_DRIVE_DISTANCE_ROBOT_SET);
			done = true;
		}
	}
	
	private static void driveToAutoZoneWithContainer() {
		if(!done) {
			if(!hasReachedContainer) {
				if(DriveMotors.getEncoderAverage() != Constants.AUTON_DISTANCE_TO_CONTAINER) {
					DriveTrain.driveDistance(Constants.AUTON_DISTANCE_TO_CONTAINER);
				} else {
					hasReachedContainer = true;
				}
			} else {
			Elevator.setContainerGrabberThingThatPicksUpContainerThingsThatAreRoundAndGreenClosed();
			DriveTrain.driveDistance(Constants.AUTON_DISTANCE_FROM_CONTAINER);
			done = true;
			}
		}
	}
	
	private static void driveToAutoZoneWithToteAndContainer() {
		
	}
	
	private static void thinkOfAGoodNameForThidMethodForAuto4() {
		
	}
	
	private static void godTierAuto() {
		
	}
	
	public static void setAutoMode(int mode) {
		Autonomous.mode = mode;
	}
	
	public static int getAutoMode() {
		return Autonomous.mode;
	}
}
