package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.Timer;

public class Autonomous {
	
	private static Autonomous instance;
	private static int mode = 12;
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
		case 4:
			Autonomous.strafeIntoAutoZoneWithToteAndContainer4();
			break;
		//  Strafe into the auto zone by pushing tote and container
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
		case 10:
			Autonomous.canBurglarAutoNoBump10();
			break;
		// Gets cans and backs up starting on no step
		case 11:
			Autonomous.canBurglarAutoWithBump11();
			break;
		// Gets can and backs up starting on step
		case 12:
			Autonomous.canBurglarPotAutoNoBump12();
			break;
		// Gets cans and backs up starting on no step based on canburg position	
		case 13:
			Autonomous.canBurglarPotAutoWithBump13();
			break;
			// Grab cans based off of pot values and back up asap
		case 14:
			Autonomous.canBurglarFastPotAutoNoBump14();
			break;
			// Quickly grab cans full speed and back up based on pot value no step
		case 15:
			Autonomous.canBurglarFastPotAutoWithBump15();
			break;
			//Quickly grab cans full speed and back up based on pot value with step
		case 16:
			Autonomous.doNothing();
			break;
			//LOL NOTHING
		default: 
			Autonomous.canBurglarPotAutoNoBump12();
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
	
	//Stop, wait 15 seconds
	private static void doNothing() {
		Elevator.stopElevator();
		DriveMotors.stopMotors();
		Arm.setArmForward();
		Elevator.calibrateEncoder();
		Ingestor.setIngestorIn();
		DriveTrain.setStrafeDown();
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
	
	//Is currently being worked on :)
	private static void dreamAutonForLivonia8() {
		/*
		 * TODO
		 * Drive to auto zone
		 * Use a gyro (Try DriveTrain.turnRightGyro(angle, speed);)
		 * FASTER!
		 * 
		 */
		if(autoTimer.get() <= 1) { // Raise can to get next tote
			DriveTrain.setStrafeDown();
			DriveTrain.resetEncoderInitBoolean();
			Elevator.setElevatorToLevel(2);
		} else if(autoTimer.get() <= 2) { // Drive to first tote
			DriveTrain.driveDistance(-600, 0.5);
		} else if(autoTimer.get() <= 2.01) {
			DriveTrain.resetGyroInitBoolean();
			DriveTrain.resetEncoderInitBoolean();
		} else if(autoTimer.get() <= 2.8) { // Turn to line up to tote, get current level
			DriveTrain.turnGyro(-20, 0.9);
			Elevator.setCurrentLevelSnapshotToLevel(2);
		} else if(autoTimer.get() <= 7.4) { // Stop moving, set ingestors in, autograb tote 
			if(autoTimer.get() <= 5.19) {
				DriveTrain.driveDistance(-200, 0.4);
			}
			if(autoTimer.get() <= 5.5) {
				Elevator.autoGrabUp();
			}
			DriveTrain.resetGyroInitBoolean();
			if(autoTimer.get() >= 5.2) {
				DriveTrain.turnGyro(-160, 0.7);
			}
			if(autoTimer.get() >= 5.51) {
				Ingestor.setIngestorOut();
			}
		} else if(autoTimer.get() <= 7.8) { // Do a 180, set ingestors out
			//DriveTrain.turnRightDistance(-1600, 0.7);
//			DriveTrain.turnGyro(-160, 0.7);
//			Ingestor.setIngestorOut();
		} else if(autoTimer.get() <= 7.9) {
			DriveTrain.resetEncoderInitBoolean();
			Elevator.setCurrentLevelSnapshotToLevel(3);
		} else if(autoTimer.get() <= 9.6) { // Drive to second tote, start ingestors
			DriveTrain.driveDistance(-1000, 0.5);
			Elevator.resetTimer();
			Ingestor.setIngestorPower(1);
			if(autoTimer.get() > 8.3) {
				Ingestor.setIngestorIn();
			}
			if(autoTimer.get() == 9.2) Ingestor.setIngestorIn();
		} else if(autoTimer.get() <= 11.8) { // Auto grab second tote
			Elevator.autoGrabUp();
			DriveTrain.resetEncoderInitBoolean();
		} else if(autoTimer.get() <= 13.8) { 
			Ingestor.setIngestorPower(1);// Do you need this?
			DriveTrain.driveDistance(-3500, 0.5); // Drive to third tote
			if(autoTimer.get() == 13.2) {
				Ingestor.setIngestorIn();
			} else if(autoTimer.get() < 12) {
				Ingestor.setIngestorOut();
			}
			Elevator.setCurrentLevelSnapshotToLevel(4);
			Elevator.resetTimer();
		} else if(autoTimer.get() <= 15.3) {
			DriveTrain.resetEncoderInitBoolean();
			Elevator.autoGrabUp();
		} else if(autoTimer.get() <= 17.5) {
			Ingestor.setIngestorPower(1);
			DriveTrain.turnGyro(20, 0.7);
		} else if(autoTimer.get() <= 18.5) {
			DriveTrain.driveDistance(-2000, 0.5);
		} else if(autoTimer.get() <= 19) {
			Elevator.setElevatorToLevel(3);
		
//		} else if(autoTimer.get() <= 13) {
//			Ingestor.setIngestorPower(Constants.AUTON_8_INGEST_POWER);
//			Elevator.setElevatorDownToLevel(2);
//		} else if(autoTimer.get() <= 15) {
//			Elevator.setElevatorUpToLevel(3);
//		} else if(autoTimer.get() <= 5) {
//			Ingestor.setIngestorOut();
//			DriveTrain.turnAngle(Constants.AUTON_8_FIRST_TURN_ANGLE, Constants.AUTON_8_TURN_SPEED);
//		} else if(autoTimer.get() <= 6) {
//			DriveTrain.resetGyro();
//			DriveTrain.driveDistance(Constants.AUTON_8_DRIVE_DISTANCE_TO_TOTE_2, Constants.AUTON_8_DRIVE_DISTANCE_SPEED);
//		} else if(autoTimer.get() <= 6.5) {
//			DriveTrain.resetEncoderInitBoolean();
//			Ingestor.setIngestorIn();
//			Ingestor.setIngestorPower(Constants.AUTON_8_INGEST_POWER);
//		} else if(autoTimer.get() <= 7.1) {
//			Elevator.setElevatorDown();
//		} else if(autoTimer.get() <= 7.8) {
//			Elevator.setElevatorUp();
//		} else if(autoTimer.get() <= 9) {
//			Ingestor.setIngestorOut();
//			DriveTrain.driveDistance(Constants.AUTON_8_DRIVE_DISTANCE_TO_TOTE_3, Constants.AUTON_8_DRIVE_DISTANCE_SPEED);
//		} else if(autoTimer.get() <= 9.5) {
//			DriveTrain.resetEncoderInitBoolean();
//			Ingestor.setIngestorIn();
//			Ingestor.setIngestorPower(Constants.AUTON_8_INGEST_POWER);
//		} else if(autoTimer.get() <= 10) {
//			Elevator.setElevatorDown();
//		} else if(autoTimer.get() <= 10.7) {
//			Elevator.setElevatorUp();
//		} else if(autoTimer.get() <= 13.5) {
//			DriveMotors.driveStrafe(Constants.AUTON_8_STRAFE_POWER);
//		} else if(autoTimer.get() <= 15) {
//			DriveMotors.stopMotors();
//			Elevator.setElevatorToLevel(3);
		}
	}
	
	private static void canBurglarAutoNoBump10() {
		DriveTrain.setStrafeUp();
		if(autoTimer.get() < 0.2) {
			DriveMotors.resetEncoders();
			DriveTrain.resetGyro();
		}
		if(autoTimer.get() <= 3.5) {
			Canburglars.setLeftDownNoBump();
			Canburglars.setRightDownNoBump();
			if(autoTimer.get() >= 0.9) {
				DriveTrain.driveDistance(-3500, 1);
			}
		} else if(autoTimer.get() <= 5) {
			Canburglars.setLeftUpNormal();
			Canburglars.setRightUpNormal();
			DriveMotors.drive(-0.35, -0.35);
		} else {
			DriveMotors.stopMotors();
			Arm.setArmForward();
			Elevator.calibrateEncoder();
			Ingestor.setIngestorIn();
		}
	}
	
	private static void canBurglarAutoWithBump11() {
		DriveTrain.setStrafeUp();
		if(autoTimer.get() < 0.2) {
			DriveMotors.resetEncoders();
			DriveTrain.resetGyro();
		}
		if(autoTimer.get() <= 3.5) {
			Canburglars.setLeftDownWithBump();
			Canburglars.setRightDownWithBump();
			if(autoTimer.get() >= 0.9) {
				DriveTrain.driveDistance(-3500, 1);
			}
		} else if(autoTimer.get() <= 5) {
			Canburglars.setLeftUpNormal();
			Canburglars.setRightUpNormal();
			DriveMotors.drive(-0.35, -0.35);
		} else {
			DriveMotors.stopMotors();
			Arm.setArmForward();
			Ingestor.setIngestorIn();
			Elevator.calibrateEncoder();
		}
	}
	
	//Fast
	private static void canBurglarPotAutoNoBump12() {
		DriveTrain.setStrafeUp();
		if(autoTimer.get() <= 3.5) {
			Canburglars.setLeftDownNoBump();
			Canburglars.setRightDownNoBump();
			if((Canburglars.getLeftPosition() > 755 && Canburglars.getRightPosition() < 153) || autoTimer.get() >= 0.9) {
				DriveTrain.driveDistance(-3500, 1);
			}
		} else if(autoTimer.get() <= 5) {
			Canburglars.setLeftUpNormal();
			Canburglars.setRightUpNormal();
			DriveMotors.drive(-0.35, -0.35);
		} else {
			DriveMotors.stopMotors();
			Arm.setArmForward();
			Ingestor.setIngestorIn();
			Elevator.calibrateEncoder();
		}
	}
	
	private static void canBurglarPotAutoWithBump13() {
		DriveTrain.setStrafeUp();
		if(autoTimer.get() < 0.2) {
			DriveMotors.resetEncoders();
			DriveTrain.resetGyro();
		}
		if(autoTimer.get() <= 3.5) {
			
			if((Canburglars.getLeftPosition() > 705 && Canburglars.getRightPosition() < 227) || autoTimer.get() >= 0.9) {
				DriveTrain.driveDistance(-3500, 1);
				Canburglars.setLeftDownDown();
				Canburglars.setRightDownDown();
			} else {
				Canburglars.setLeftDownWithBump();
				Canburglars.setRightDownWithBump();
			}
		} else if(autoTimer.get() <= 5) {
			Canburglars.setLeftUpNormal();
			Canburglars.setRightUpNormal();
			DriveMotors.drive(-0.35, -0.35);
		} else {
			DriveMotors.stopMotors();
			Arm.setArmForward();
			Elevator.calibrateEncoder();
			Ingestor.setIngestorIn();
		}
	}

	
	private static void canBurglarFastPotAutoNoBump14() {
		DriveTrain.setStrafeUp();
		if(autoTimer.get() < 0.2) {
			DriveMotors.resetEncoders();
			DriveTrain.resetGyro();
		}
		if(autoTimer.get() <= 3.5) {
			
			if((Canburglars.getLeftPosition() > 755 && Canburglars.getRightPosition() < 153) || autoTimer.get() >= 0.9) {
				DriveTrain.driveDistance(-3500, 1);
				Canburglars.setLeftDownDown();
				Canburglars.setRightDownDown();
			} else if(autoTimer.get() < 0.05) {
				Canburglars.setPower(1);
			} else {
				Canburglars.setLeftDownNoBump();
				Canburglars.setRightDownNoBump();
			}
		} else if(autoTimer.get() <= 5) {
			Canburglars.setLeftUpNormal();
			Canburglars.setRightUpNormal();
			DriveMotors.drive(-0.35, -0.35);
		} else {
			DriveMotors.stopMotors();
			Arm.setArmForward();
			Elevator.calibrateEncoder();
			Ingestor.setIngestorIn();
		}
	}
	
	private static void canBurglarFastPotAutoWithBump15() {
		DriveTrain.setStrafeUp();
		if(autoTimer.get() < 0.2) {
			DriveMotors.resetEncoders();
			DriveTrain.resetGyro();
		}
		if(autoTimer.get() <= 3.5) {
			
			if((Canburglars.getLeftPosition() > 705 && Canburglars.getRightPosition() < 227) || autoTimer.get() >= 0.9) {
				DriveTrain.driveDistance(-3500, 1);
				Canburglars.setLeftDownDown();
				Canburglars.setRightDownDown();
			} else if(autoTimer.get() < 0.05) {
				Canburglars.setPower(1);
			} else {
				Canburglars.setLeftDownWithBump();
				Canburglars.setRightDownWithBump();
			}
		} else if(autoTimer.get() <= 5) {
			Canburglars.setLeftUpNormal();
			Canburglars.setRightUpNormal();
			DriveMotors.drive(-0.35, -0.35);
		} else {
			DriveMotors.stopMotors();
			Arm.setArmForward();
			Elevator.calibrateEncoder();
			Ingestor.setIngestorIn();
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
