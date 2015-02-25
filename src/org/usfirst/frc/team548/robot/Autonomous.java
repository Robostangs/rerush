package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.Timer;

public class Autonomous {
	
	private static Autonomous instance;
	private static int mode = 2;
	private static Timer autoTimer;
//	private static boolean step1Done = false, step2Done = false,
//			step3Done = false, step4Done = false, step5Done = false,
//			step6Done = false, step7Done = false, step8Done = false;

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
			Autonomous.driveToAutoZone1();
			break;
		// Drive into auto zone with container
		case 2:
			Autonomous.driveToAutoZoneWithContainer2();
			break;
		// Drive into auto zone with container and tote
		case 3:
			Autonomous.driveToAutoZoneWithToteAndContainer3();
			break;
		// Pick up container and tote do 180 pick up tote drive straight pick up last tote then turn into auto zone
		case 4:
			Autonomous.thinkOfAGoodNameForThisMethodForAuto4();
			break;
		//  Pick up container and tote do 180 pick up tote drive around container pick up last tote then turn into auto zone
		case 5: 
			Autonomous.godTierAuto5();
			break;
		case 6:
			Autonomous.strafeIntoAutoZoneWithToteAndContainer6();
			break;
		//  Strafe into the auto zone by pushing tote and container
		case 7:
			Autonomous.getToteAndBackIntoAutoZone7();
			break;
		//	Lift tote and back up into auto zone
		default: 
			Autonomous.driveToAutoZone1();
			break;
		}
	}
	
	
	private static void driveToAutoZone1() {
		if(autoTimer.get() <= 7) {
			DriveTrain.driveDistance(Constants.AUTON_1_DRIVE_DISTANCE_ROBOT_SET);
		} else {
			DriveMotors.stopMotors();
		}
	}
	
	
	private static void driveToAutoZoneWithContainer2() {		
		if(autoTimer.get() < 0.5) {
			Elevator.setContainerGrabberThingThatPicksUpContainerThingsThatAreRoundAndGreenClosed();
		} else if (autoTimer.get() > 0.5 && autoTimer.get() < 1.5) {
			Elevator.moveElevator(0.2);
		} else if (autoTimer.get() > 1.5 && autoTimer.get() < 5) {
			Elevator.stopElevator();
			DriveMotors.drive(0.35, -0.35);
		} else if(autoTimer.get() > 5 && autoTimer.get() < 6.5) {
			DriveMotors.stopMotors();
			DriveMotors.drive(0.7, 0.7);
		} else if(autoTimer.get() > 6.5 && autoTimer.get() < 7.5) {
			DriveMotors.stopMotors();
		}
	}
	
	
	private static void driveToAutoZoneWithToteAndContainer3() {
		Elevator.setContainerGrabberThingThatPicksUpContainerThingsThatAreRoundAndGreenClosed();
		if(autoTimer.get() <= 2.5) {
			Elevator.setElevatorToLevel(2);
		} else if(autoTimer.get() <= 3.5) {
			Elevator.stopElevator();
			DriveTrain.driveDistance(1000);
		} else if(autoTimer.get() <= 4.5) {
			DriveMotors.stopMotors();
			DriveMotors.drive(Constants.AUTON_3_LEFT_SPEED, Constants.AUTON_3_RIGHT_SPEED);
			DriveMotors.driveStrafe(Constants.AUTON_3_STRAFE_SPEED);
		} else if(autoTimer.get() <= 8) {
			DriveMotors.stopMotors();
			Ingestor.setIngestorIn();
			Ingestor.setIngestorPower(1);
		} else if(autoTimer.get() <= 9) {
			Elevator.setElevatorUp();
		} else if(autoTimer.get() <= 10.5) {
			DriveTrain.setStrafeDown();
			ElevatorMotors.setPower(0);
			DriveMotors.drive(0.5, 0);
			DriveMotors.driveStrafe(-1);
		} else if(autoTimer.get() < 13) {
			DriveMotors.stopMotors();
		}
	}
	
	
	private static void thinkOfAGoodNameForThisMethodForAuto4() {
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
	
	
	private static void godTierAuto5() {
		
	}
	
	
	private static void strafeIntoAutoZoneWithToteAndContainer6() {
		DriveTrain.setStrafeDown();
		if(autoTimer.get() <= 7) {
			DriveMotors.drive(Constants.AUTON_6_LEFT_SPEED, Constants.AUTON_6_RIGHT_SPEED);
			DriveMotors.driveStrafe(Constants.AUTON_6_STRAFE_SPEED);
		} else {
			DriveMotors.stopMotors();
		}
	}
	
	
	private static void getToteAndBackIntoAutoZone7() {
		if(autoTimer.get() <= 1) {
			Elevator.setElevatorPosition(Constants.AUTON_7_ELEVATOR_LIFT);
		} else if(autoTimer.get() <= 5) {
			DriveTrain.driveDistance(Constants.AUTON_7_DRIVE_DISTANCE_BACK);
		} else {
			DriveMotors.stopMotors();
		}
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
