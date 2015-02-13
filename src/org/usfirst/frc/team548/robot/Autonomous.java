package org.usfirst.frc.team548.robot;

public class Autonomous {
	
	private static Autonomous instance;
	private static boolean hasReachedContainer = false;
	private static boolean done = false;
	
	private Autonomous() {

	}
	
	public static Autonomous getInstance() {
		if(instance == null) {
			instance = new Autonomous();
		}
		return instance;
	}
	
	public static void run() {
		
		//Drive into auto zone
		if(!done) {
			DriveTrain.driveDistance(Constants.AUTON_DRIVE_DISTANCE_ROBOT_SET);
			done = true;
		}
		
		//Drive into auto zone with container
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

}
