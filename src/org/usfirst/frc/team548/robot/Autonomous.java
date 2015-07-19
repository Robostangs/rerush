package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.Timer;

public class Autonomous {

	private static Autonomous instance;
	private static int mode = 21;
	private static Timer autoTimer;

	private Autonomous() {
		autoTimer = new Timer();
	}

	public static Autonomous getInstance() {
		if (instance == null) {
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
		case 6:
			Autonomous.backIntoAutoZoneWithContainerDistance6();
			break;
		case 12:
			Autonomous.canBurglarPotAutoNoBump12();
			break;
		// Gets cans and backs up starting on no bump based on canburg position
		case 16:
			Autonomous.doNothing16();
			break;
		// LOL NOTHING
		case 18:
			Autonomous.canBurglarTugOfWar18();
			break;
		case 21:
			Autonomous.canBurglarAutoNoBumpWingle21();
			break;
		default:
			Autonomous.canBurglarPotAutoNoBump12();
			break;
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

	// RUNS ON TIME
	// SHOULD BE RE-TUNED BEFORE RUNNING AGAIN
	private static void driveToAutoZone1() {
		if (autoTimer.get() <= 2.6) {
			DriveMotors.drive(0.5, -0.5);
		} else {
			DriveMotors.stopMotors();
		}
	}

	// Same as method 2, but more accurate since based on distance rather than
	// time.
	private static void backIntoAutoZoneWithContainerDistance6() {
		if (autoTimer.get() <= 0.5) {
			DriveMotors.resetEncoders();
			Elevator.setContainerGrabberThingThatPicksUpContainerThingsThatAreRoundAndGreenClosed();
			DriveTrain.setStrafeUp();
		} else if (autoTimer.get() <= 1.5) {
			Elevator.setElevatorUp();
		} else if (autoTimer.get() <= 7) {
			DriveTrain.driveDistance(Constants.AUTON_6_DRIVE_DISTANCE,
					Constants.AUTON_6_DRIVE_DISTANCE_SPEED);
		} else if (autoTimer.get() <= 8.75) {
			DriveMotors.drive(0.35, 0.35);
		} else {
			Ingestor.setIngestorIn();
			DriveMotors.stopMotors();
		}
	}

	// CAN BURGLARS

	// NO BUMP
	// Runs PID and triggers driving w pot values
	static double v = -1;

	private static void canBurglarPotAutoNoBump12() {
		DriveTrain.setStrafeUp();
		if (autoTimer.get() <= 3.5) {
			Canburglars.setLeftPIDNormalDown();
			Canburglars.setRightPIDNormalDown();

			if (autoTimer.get() >= 2) {
				DriveTrain.driveDistance(
						Constants.AUTON_CAN_BURGLARS_PULLBACK_DISTANCE, 1);
			}
		} else if (autoTimer.get() <= 7) {
			Canburglars.setLeftUpNormal();
			Canburglars.setRightUpNormal();
		} else if (autoTimer.get() <= 8.5) {
			DriveMotors.drive(-0.35, -0.35);
		} else {
			DriveMotors.stopMotors();
			Arm.setArmForward();
			Ingestor.setIngestorIn();
		}
	}

	// DO NOTHING
	// Stop, wait 15 seconds
	private static void doNothing16() {
		// Elevator.stopElevator();
		DriveMotors.stopMotors();
		Arm.setArmForward();
		// Elevator.calibrateEncoder();
		Ingestor.setIngestorIn();
		DriveTrain.setStrafeDown();
	}

	private static void canBurglarTugOfWar18() {
		DriveTrain.setStrafeUp();
		if (autoTimer.get() <= 3.5) {
			Canburglars.setLeftPIDNormalDown();
			Canburglars.setRightPIDNormalDown();

			if ((Canburglars.getLeftPosition() > Constants.LEFT_BURGLARS_DOWN_NO_STEP_TRIGGER && Canburglars
					.getRightPosition() < Constants.RIGHT_BURGLARS_DOWN_NO_STEP_TRIGGER)
					|| autoTimer.get() >= 0.9) {
				DriveTrain.driveDistance(
						Constants.AUTON_CAN_BURGLARS_PULLBACK_DISTANCE, 1);
			}
		}
		Arm.setArmForward();
		Ingestor.setIngestorIn();
	}

	static int eo = 0;

	private static void canBurglarAutoNoBumpWingle21() {
		DriveTrain.setStrafeUp();
		if (autoTimer.get() <= 5) {
			Canburglars.setLeftPIDNormalDown();
			Canburglars.setRightPIDNormalDown();
			if (autoTimer.get() <= 3 && autoTimer.get() > 1) {
				System.out.println(eo);
				if ((eo / 10) % 2 == 0) {
					DriveMotors.drive(0.2, 0.2);
				} else {
					DriveMotors.drive(-0.2, -0.2);
				}
			} else {
				DriveMotors.stopMotors();
			}
		} else if (autoTimer.get() < 7) {
			DriveTrain.driveDistance(
					Constants.AUTON_CAN_BURGLARS_PULLBACK_DISTANCE, 1);
		} else if (autoTimer.get() <= 10.5) {
			Canburglars.setLeftUpNormal();
			Canburglars.setRightUpNormal();
		} else if (autoTimer.get() <= 12) {
			DriveMotors.drive(-0.35, -0.35);
		} else {
			DriveMotors.stopMotors();
			Arm.setArmForward();
			// //Elevator.calibrateEncoder();
			Ingestor.setIngestorIn();
		}
		eo++;
	}
}
