package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.Timer;

public class Autonomous {
	
	private static Autonomous instance;
	private static int mode = 1;
	private static Timer autoTimer;
	private static boolean step1Done = false, step2Done = false,
			step3Done = false, step4Done = false, step5Done = false,
			step6Done = false, step7Done = false, step8Done = false;

	private Autonomous() {
		autoTimer = new Timer();
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
//		if(!DriveTrain.isAtDistance(Constants.AUTON_2_DISTANCE_TO_CONTAINER) && autoTimer.get() <= 3 && !step1Done) {
//			Elevator.setContainerGrabberThingThatPicksUpContainerThingsThatAreRoundAndGreenOpen();
//			DriveTrain.driveDistance(Constants.AUTON_2_DISTANCE_TO_CONTAINER);
//			step1Done = true;
//		} else {
//			DriveMotors.stopMotors();
//			Elevator.setContainerGrabberThingThatPicksUpContainerThingsThatAreRoundAndGreenClosed();
//			//Elevator.setElevatorUpOneLevel();
//			//Elevator.setElevatorPosition();
//			if(!DriveTrain.isAtDistance(Constants.AUTON_2_DISTANCE_FROM_CONTAINER) && autoTimer.get() <= 4 && !step2Done) {
//				DriveTrain.driveDistance(Constants.AUTON_2_DISTANCE_FROM_CONTAINER);
//				step2Done = true;
//			} else {
//				DriveMotors.stopMotors();
//				//Elevator.setElevatorDownOneLevel();
//				//Elevator.setElevatorPosition();
//				Elevator.setContainerGrabberThingThatPicksUpContainerThingsThatAreRoundAndGreenOpen();
//			}
//		}
		
		if(autoTimer.get() < 0.5) {
			Elevator.setContainerGrabberThingThatPicksUpContainerThingsThatAreRoundAndGreenClosed();
		} else if (autoTimer.get() > 0.5 && autoTimer.get() < 1.5) {
			Elevator.moveElevator(-0.2);
		} else if (autoTimer.get() > 1.5 && autoTimer.get() < 3.5) {
			Elevator.stopElevator();
			DriveMotors.drive(0.35, 0.35);
		} else if(autoTimer.get() > 3.5 && autoTimer.get() < 5) {
			DriveMotors.stopMotors();
			DriveMotors.drive(0.7, -0.7);
		} else if(autoTimer.get() > 5 && autoTimer.get() < 6) {
			DriveMotors.stopMotors();
			Elevator.moveElevator(0.2);
		} else if(autoTimer.get()  > 6 && autoTimer.get() < 6.5) {
			Elevator.stopElevator();
			Elevator.setContainerGrabberThingThatPicksUpContainerThingsThatAreRoundAndGreenOpen();
		}
	}
	
	private static void driveToAutoZoneWithToteAndContainer() {
//		if(!DriveTrain.isAtDistance(Constants.AUTON_3_DISTANCE_TO_CONTAINER) && !step1Done) {
//			DriveTrain.driveDistance(Constants.AUTON_2_DISTANCE_TO_CONTAINER);
//		} else {
//			step1Done = true;
//			Elevator.setContainerGrabberThingThatPicksUpContainerThingsThatAreRoundAndGreenClosed();
//			Elevator.setElevatorUpOneLevel();
//			if(!DriveTrain.isAtDistance(Constants.AUTON_3_DISTANCE_TO_TOTE) && !step2Done) {
//				DriveTrain.driveDistance(Constants.AUTON_3_DISTANCE_TO_TOTE);
//				Ingestor.setIngestorIn();
//				Ingestor.setIngestorPower(1);
//			} else {
//				step2Done = true;
//				Ingestor.setIngestorPower(0);
//				DriveMotors.stopMotors();
//				Elevator.setElevatorUpOneLevel();
//				if(!DriveTrain.isAtTurn(Constants.AUTON_3_TURN_ANGLE) && !step3Done) {
//					DriveTrain.turn(Constants.AUTON_3_TURN_ANGLE);
//				} else {
//					step3Done = true;
//					DriveMotors.stopMotors();
//					if(!DriveTrain.isAtDistance(Constants.AUTON_3_DISTANCE_TO_AUTO_ZONE) && !step4Done) {
//						DriveTrain.driveDistance(Constants.AUTON_3_DISTANCE_TO_AUTO_ZONE);
//					} else {
//						step4Done = true;
//						DriveMotors.stopMotors();
//						Elevator.setElevatorToLevel(0);
//						Elevator.setContainerGrabberThingThatPicksUpContainerThingsThatAreRoundAndGreenOpen();
//					}
//				}
//			}
//		}
//		
	}
//	
	private static void thinkOfAGoodNameForThidMethodForAuto4() {
//		if(!DriveTrain.isAtDistance(Constants.AUTON_4_DISTANCE_TO_FIRST_CONTAINER) && !step1Done) {
//			Elevator.setContainerGrabberThingThatPicksUpContainerThingsThatAreRoundAndGreenOpen();
//			DriveTrain.driveDistance(Constants.AUTON_4_DISTANCE_TO_FIRST_CONTAINER);
//		} else {
//			step1Done = true;
//			DriveMotors.stopMotors();
//			Elevator.setContainerGrabberThingThatPicksUpContainerThingsThatAreRoundAndGreenClosed();
//			Elevator.setElevatorUpOneLevel();
//			if(!DriveTrain.isAtDistance(Constants.AUTON_4_DISTANCE_TO_FIRST_TOTE) && !step2Done) {
//				Ingestor.setIngestorIn();
//				Ingestor.setIngestorPower(1);
//				DriveTrain.driveDistance(Constants.AUTON_4_DISTANCE_TO_FIRST_TOTE);
//			} else {
//				step2Done = true;
//				DriveMotors.stopMotors();
//				Ingestor.setIngestorPower(0);
//				Elevator.setElevatorUpOneLevel();
//				if(!DriveTrain.isAtTurn(Constants.AUTON_4_FIRST_TURN_ANGLE) && !step3Done) {
//					DriveTrain.turn(Constants.AUTON_4_FIRST_TURN_ANGLE);
//				} else {
//					step3Done = true;
//					if(!DriveTrain.isAtDistance(Constants.AUTON_4_DISTANCE_TO_SECOND_TOTE) && !step4Done) {
//						Ingestor.setIngestorIn();
//						Ingestor.setIngestorPower(1);
//						DriveTrain.driveDistance(Constants.AUTON_4_DISTANCE_TO_SECOND_TOTE);
//					} else {
//						step4Done = true;
//						DriveMotors.stopMotors();
//						Ingestor.setIngestorPower(0);
//						Elevator.setElevatorUpOneLevel();
//						if(!DriveTrain.isAtDistance(Constants.AUTON_4_DISTANCE_TO_THIRD_TOTE) && !step5Done) {
//							Ingestor.setIngestorIn();
//							Ingestor.setIngestorPower(1);
//							DriveTrain.driveDistance(Constants.AUTON_4_DISTANCE_TO_THIRD_TOTE);
//						} else {
//							step5Done = true;
//							DriveMotors.stopMotors();
//							Ingestor.setIngestorPower(0);
//							Elevator.setElevatorUpOneLevel();
//							if(!DriveTrain.isAtDistance(Constants.AUTON_4_DISTANCE_BACKING_UP) && !step6Done) {
//								DriveTrain.driveDistance(Constants.AUTON_4_DISTANCE_BACKING_UP);
//							} else {
//								step6Done = true;
//								DriveMotors.stopMotors();
//								if(!DriveTrain.isAtTurn(Constants.AUTON_4_SECOND_TURN_ANGLE) && !step7Done) {
//									DriveTrain.turn(Constants.AUTON_4_SECOND_TURN_ANGLE);
//								} else {
//									step7Done = true;
//									if(!DriveTrain.isAtDistance(Constants.AUTON_4_DISTANCE_TO_AUTO_ZONE) && !step8Done) {
//										DriveTrain.driveDistance(Constants.AUTON_4_DISTANCE_TO_AUTO_ZONE);
//									} else {
//										step8Done = true;
//										DriveMotors.stopMotors();
//										Elevator.setElevatorPosition(0);
//										Elevator.setContainerGrabberThingThatPicksUpContainerThingsThatAreRoundAndGreenOpen();
//									}
//								}
//							}
//						}
//					}
//				}
//			}
//		}
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
