package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.Timer;

public class Autonomous {
	
	private static Autonomous instance;
	private static int mode = 1;
	private static Timer autoTimer;

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
			Autonomous.strafeIntoAutoZoneWithToteAndContainer4();
			break;
		//  Strafe into the auto zone by pushing tote and container
		case 5: 
			Autonomous.getToteAndBackIntoAutoZone5();
			break;
		//	Lift tote and back up into auto zone
		case 6:
			Autonomous.backIntoAutoZoneWithContainerDistance6();
			break;
		//  Back into auto zone with container, but based on distance
		case 7:
			Autonomous.strafeIntoAutoZoneWithContainer7();
			break;
		//	Strafe into auto zone pushing only container
		case 8:
			Autonomous.dreamAutonForLivonia8();
			break;
		//  Stack tote set in auton as long as middle container is removed
		case 9:
			Autonomous.otherDreamAuton9();
			break;
		//	Goes in a line to get the three totes by pushing containers out of the way with ingestors
		default: 
			Autonomous.driveToAutoZone1();
			break;
		}
	}
	
	//Works alright.
	private static void driveToAutoZone1() {
		if(autoTimer.get() <= 2.6) {
			DriveMotors.drive(0.5, -0.5);
		} else {
			DriveMotors.stopMotors();
		}
	}
	
	//Works, but is based on time. Method based on distance is more accurate.
	private static void driveToAutoZoneWithContainer2() {		
		if(autoTimer.get() < 0.5) {
			Elevator.setContainerGrabberThingThatPicksUpContainerThingsThatAreRoundAndGreenClosed();
			DriveTrain.setStrafeUp();
		} else if (autoTimer.get() > 0.5 && autoTimer.get() < 1.5) {
			Elevator.setElevatorUp();
		} else if (autoTimer.get() > 1.5 && autoTimer.get() < 3.8) {
			Elevator.stopElevator();
			DriveMotors.drive(0.35, -0.35);
		} else if(autoTimer.get() > 7) {
			DriveMotors.stopMotors();
		}
	}
	
	//Hasn't been tested
	private static void driveToAutoZoneWithToteAndContainer3() {
		Elevator.setContainerGrabberThingThatPicksUpContainerThingsThatAreRoundAndGreenClosed();
		if(autoTimer.get() <= 2.5) {
			Elevator.setElevatorToLevel(2);
		} else if(autoTimer.get() <= 3.5) {
			Elevator.stopElevator();
			DriveTrain.driveDistance(1000, 0.5);
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
	
	//Works, but only with both tote and container. Watch for worn down carpet under strafe wheel.
	private static void strafeIntoAutoZoneWithToteAndContainer4() {
		if(autoTimer.get() <= 1.0) {
			DriveTrain.setStrafeDown();
			Arm.setArmBack();
		} else if(autoTimer.get() <= 7.5) {
			DriveMotors.drive(Constants.AUTON_4_LEFT_SPEED, Constants.AUTON_4_RIGHT_SPEED);
			DriveMotors.driveStrafe(Constants.AUTON_4_STRAFE_SPEED);
		} else {
			DriveMotors.stopMotors();
		}
	}
	
	//Currently doesn't work with ingestor wheels 
	private static void getToteAndBackIntoAutoZone5() {
		if(autoTimer.get() <= 1) {
			Elevator.setElevatorPosition(Constants.AUTON_5_ELEVATOR_LIFT);
		} else if(autoTimer.get() <= 5) {
			DriveTrain.driveDistance(Constants.AUTON_5_DRIVE_DISTANCE_BACK, Constants.AUTON_5_DRIVE_DISTANCE_SPEED);
		} else {
			DriveMotors.stopMotors();
		}
	}
	
	//Same as method 2, but more accurate since based on distance rather than time.
	private static void backIntoAutoZoneWithContainerDistance6() {
		if(autoTimer.get() <= 0.5) {
			DriveMotors.resetEncoders();
			Elevator.setContainerGrabberThingThatPicksUpContainerThingsThatAreRoundAndGreenClosed();
			DriveTrain.setStrafeUp();
		} else if (autoTimer.get() <= 1.5) {
			Elevator.setElevatorUp();
		} else if(autoTimer.get() <= 7) {
			DriveTrain.driveDistance(Constants.AUTON_6_DRIVE_DISTANCE, Constants.AUTON_6_DRIVE_DISTANCE_SPEED);
		} else if(autoTimer.get() <= 8.75){
			DriveMotors.drive(0.35, 0.35);
		} else {
			Ingestor.setIngestorIn();
			DriveMotors.stopMotors();
		}
	}
	
	//Works, but only with just container. Watch for worn down carpet under strafe wheel.
	private static void strafeIntoAutoZoneWithContainer7() {
		if(autoTimer.get() <= 1.0) {
			DriveTrain.setStrafeDown();
			Arm.setArmBack();
		} else if(autoTimer.get() <= 7) {
			DriveMotors.drive(Constants.AUTON_7_LEFT_SPEED, Constants.AUTON_7_RIGHT_SPEED);
			DriveMotors.driveStrafe(Constants.AUTON_7_STRAFE_SPEED);
		} else {
			DriveMotors.stopMotors();
		}
	}
	
	//Hasn't been tested
	private static void dreamAutonForLivonia8() {
		if(autoTimer.get() <= 0.5) {
			Elevator.setElevatorUp();
		} else if(autoTimer.get() <= 1.5) {
			DriveTrain.driveDistance(Constants.AUTON_8_DRIVE_DISTANCE_TO_TOTE_1, Constants.AUTON_8_DRIVE_DISTANCE_SPEED);
		} else if(autoTimer.get() <= 2.2) {
			DriveTrain.resetEncoderInitBoolean();
			Ingestor.setIngestorIn();
			Ingestor.setIngestorPower(Constants.AUTON_8_INGEST_POWER);
		} else if(autoTimer.get() <= 2.8) {
			Elevator.setElevatorDown();
		} else if(autoTimer.get() <= 3.8) {
			Elevator.setElevatorUp();
		} else if(autoTimer.get() <= 5) {
			Ingestor.setIngestorOut();
			DriveTrain.turnAngle(Constants.AUTON_8_FIRST_TURN_ANGLE, Constants.AUTON_8_TURN_SPEED);
		} else if(autoTimer.get() <= 6) {
			DriveTrain.resetGyro();
			DriveTrain.driveDistance(Constants.AUTON_8_DRIVE_DISTANCE_TO_TOTE_2, Constants.AUTON_8_DRIVE_DISTANCE_SPEED);
		} else if(autoTimer.get() <= 6.5) {
			DriveTrain.resetEncoderInitBoolean();
			Ingestor.setIngestorIn();
			Ingestor.setIngestorPower(Constants.AUTON_8_INGEST_POWER);
		} else if(autoTimer.get() <= 7.1) {
			Elevator.setElevatorDown();
		} else if(autoTimer.get() <= 7.8) {
			Elevator.setElevatorUp();
		} else if(autoTimer.get() <= 9) {
			Ingestor.setIngestorOut();
			DriveTrain.driveDistance(Constants.AUTON_8_DRIVE_DISTANCE_TO_TOTE_3, Constants.AUTON_8_DRIVE_DISTANCE_SPEED);
		} else if(autoTimer.get() <= 9.5) {
			DriveTrain.resetEncoderInitBoolean();
			Ingestor.setIngestorIn();
			Ingestor.setIngestorPower(Constants.AUTON_8_INGEST_POWER);
		} else if(autoTimer.get() <= 10) {
			Elevator.setElevatorDown();
		} else if(autoTimer.get() <= 10.7) {
			Elevator.setElevatorUp();
		} else if(autoTimer.get() <= 13.5) {
			DriveMotors.driveStrafe(Constants.AUTON_8_STRAFE_POWER);
		} else if(autoTimer.get() <= 15) {
			DriveMotors.stopMotors();
			Elevator.setElevatorToLevel(3);
		}
	}
	
	private static void otherDreamAuton9() {
		if(autoTimer.get() <= 0.5) {
			Ingestor.setIngestorIn();
			Ingestor.setIngestorPower(Constants.AUTON_9_INGEST_POWER);
		} else if(autoTimer.get() <= 1.5) {
			Elevator.setElevatorToLevel(3);
		} else if(autoTimer.get() <= 3) {
			DriveMotors.drive(Constants.AUTON_9_READJUST_LEFT_SPEED, Constants.AUTON_9_READJUST_RIGHT_SPEED);
			DriveMotors.driveStrafe(Constants.AUTON_9_READJUST_STRAFE_SPEED);
		} else if(autoTimer.get() <= 5.5) {
			Ingestor.setDirection(Constants.AUTON_9_SET_INGESTOR_DIRECTION);
			DriveTrain.driveDistance(Constants.AUTON_9_DISTANCE_TO_TOTE_2, Constants.AUTON_9_DRIVE_DISTANCE_SPEED);
			if(DriveMotors.getEncoderAverage() > Constants.AUTON_9_DISTANCE_TO_CONTAINER_1) {
				Ingestor.setIngestorOut();
				Elevator.setElevatorToLevel(2);
			}
		} else if(autoTimer.get() <= 6.5) {
			DriveTrain.resetEncoderInitBoolean();
			Ingestor.setIngestorIn();
			Ingestor.setIngestorPower(Constants.AUTON_9_INGEST_POWER);
		} else if(autoTimer.get() <= 7.5) {
			Elevator.setElevatorDown();
		} else if(autoTimer.get() <= 7.7) {
			Elevator.setElevatorToLevel(4);
		} else if(autoTimer.get() <= 9.2) {
			Ingestor.setDirection(Constants.AUTON_9_SET_INGESTOR_DIRECTION);
			DriveTrain.driveDistance(Constants.AUTON_9_DISTANCE_TO_TOTE_3, Constants.AUTON_9_DRIVE_DISTANCE_SPEED);
			if(DriveMotors.getEncoderAverage() > Constants.AUTON_9_DISTANCE_TO_CONTAINER_2) {
				Ingestor.setIngestorOut();
				Elevator.setElevatorToLevel(3);
			}
		} else if(autoTimer.get() <= 10.2) {
			DriveTrain.resetEncoderInitBoolean();
			Ingestor.setIngestorIn();
			Ingestor.setIngestorPower(Constants.AUTON_9_INGEST_POWER);
		} else if(autoTimer.get() <= 10.5) {
			Elevator.setElevatorDown();
		} else if(autoTimer.get() <= 11.5) {
			Elevator.setElevatorUp();
		} else if(autoTimer.get() <= 14) {
			Ingestor.setIngestorOut();
			DriveMotors.driveStrafe(Constants.AUTON_9_STRAFE_POWER);
		} else if(autoTimer.get() <= 15) {
			Elevator.setElevatorToLevel(3);
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
