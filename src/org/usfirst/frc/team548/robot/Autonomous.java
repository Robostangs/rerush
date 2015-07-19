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
		case 6:
			Autonomous.backIntoAutoZoneWithContainerDistance6();
			break;
		case 10:
			Autonomous.canBurglarAutoNoBump10();
			break;
		// Gets cans and backs up starting on no bump
		case 11:
			Autonomous.canBurglarAutoWithBump11();
			break;
		// Gets can and backs up starting on bump
		case 12:
			Autonomous.canBurglarPotAutoNoBump12();
			break;
		// Gets cans and backs up starting on no bump based on canburg position	
		case 13:
			Autonomous.canBurglarPotAutoWithBump13();
			break;
			// Grab cans based off of pot values and back up asap
		case 14:
			Autonomous.canBurglarFastPotAutoNoBump14();
			break;
			// Quickly grab cans full speed and back up based on pot value no bump
		case 15:
			Autonomous.canBurglarFastPotAutoWithBump15();
			break;
			//Quickly grab cans full speed and back up based on pot value with bump
		case 16:
			Autonomous.doNothing16();
			break;
			//LOL NOTHING
		case 17:
			Autonomous.canBurglarJHLevelPotAutoNoBump17();
			break;
			//Slow for Jake and Haley line up (:
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
	
	
	//RUNS ON TIME
	//SHOULD BE RE-TUNED BEFORE RUNNING AGAIN
	private static void driveToAutoZone1() {
		if(autoTimer.get() <= 2.6) {
			DriveMotors.drive(0.5, -0.5);
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
	
	
	
//CAN BURGLARS
	
	//NO BUMP
	//Runs PID and triggers driving w time
	private static void canBurglarAutoNoBump10() {
		DriveTrain.setStrafeUp();
		if(autoTimer.get() <= 3.5) {
			Canburglars.setLeftDownNoBump();
			Canburglars.setRightDownNoBump();
			if(autoTimer.get() >= 0.9) {
				DriveTrain.driveDistance(Constants.AUTON_CAN_BURGLARS_PULLBACK_DISTANCE, 1);
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
	
	//WITH BUMP
	//Runs PID and triggers driving w time
	private static void canBurglarAutoWithBump11() {
		DriveTrain.setStrafeUp();
		if(autoTimer.get() <= 3.5) {
			Canburglars.setLeftDownWithBump();
			Canburglars.setRightDownWithBump();
			if(autoTimer.get() >= 0.9) {
				DriveTrain.driveDistance(Constants.AUTON_CAN_BURGLARS_PULLBACK_DISTANCE, 1);
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
	
	//NO BUMP
	//Runs PID and triggers driving w pot values
	static double v = -1;
	private static void canBurglarPotAutoNoBump12() {
		DriveTrain.setStrafeUp();
		if(autoTimer.get() <= 3.5) {
			Canburglars.setLeftDownNoBump();
			Canburglars.setRightDownNoBump();
			
			if(autoTimer.get() >= 2) {
					DriveTrain.driveDistance(Constants.AUTON_CAN_BURGLARS_PULLBACK_DISTANCE, 1);				
			}
		} else if(autoTimer.get() <= 7) {
			Canburglars.setLeftUpNormal();
			Canburglars.setRightUpNormal();
		} else if(autoTimer.get() <= 8.5) {
			DriveMotors.drive(-0.35, -0.35);
		} else {
			DriveMotors.stopMotors();
			Arm.setArmForward();
			Ingestor.setIngestorIn();
		}
	}
	
	//WITH BUMP
	//Runs PID and triggers driving w pot values
	private static void canBurglarPotAutoWithBump13() {
		DriveTrain.setStrafeUp();
		if(autoTimer.get() <= 3.5) {
			Canburglars.setLeftDownWithBump();
			Canburglars.setRightDownWithBump();
			if(autoTimer.get() >= 0.5) { //(Canburglars.getLeftPosition() > Constants.LEFT_BURGLARS_DOWN_WITH_STEP_SETPOINT && Canburglars.getRightPosition() < Constants.RIGHT_BURGLARS_DOWN_WITH_STEP_SETPOINT) || 
				DriveTrain.driveDistance(Constants.AUTON_CAN_BURGLARS_PULLBACK_DISTANCE, 1);
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

	//NO BUMP
	//Goes at full speed for 0.05 seconds
	//Then runs PID and triggers driving w pot values
	private static void canBurglarFastPotAutoNoBump14() {
		DriveTrain.setStrafeUp();
		if(autoTimer.get() <= 3.5) {		
			if((Canburglars.getLeftPosition() > Constants.LEFT_BURGLARS_DOWN_NO_STEP_TRIGGER && Canburglars.getRightPosition() < Constants.RIGHT_BURGLARS_DOWN_NO_STEP_TRIGGER) || autoTimer.get() >= 0.9) {
				DriveTrain.driveDistance(Constants.AUTON_CAN_BURGLARS_PULLBACK_DISTANCE, 1);
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
	
	//WITH BUMP
	//Goes at full speed for 0.05 seconds
	//Then runs PID and triggers driving w pot values
	private static void canBurglarFastPotAutoWithBump15() {
		DriveTrain.setStrafeUp();
		if(autoTimer.get() <= 3.5) {			
			if((Canburglars.getLeftPosition() > Constants.LEFT_BURGLARS_DOWN_WITH_STEP_TRIGGER && Canburglars.getRightPosition() < Constants.RIGHT_BURGLARS_DOWN_WITH_STEP_TRIGGER) || autoTimer.get() >= 0.9) {
				DriveTrain.driveDistance(Constants.AUTON_CAN_BURGLARS_PULLBACK_DISTANCE, 1);
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
	
//DO NOTHING
	//Stop, wait 15 seconds
	private static void doNothing16() {
		//Elevator.stopElevator();
		DriveMotors.stopMotors();
		Arm.setArmForward();
		//Elevator.calibrateEncoder();
		Ingestor.setIngestorIn();
		DriveTrain.setStrafeDown();
	}
	
	//NO BUMP
		//R
		private static void canBurglarJHLevelPotAutoNoBump17() {
			DriveTrain.setStrafeUp();
			if(autoTimer.get() <= 3.5) {
				Canburglars.setLeftDownNoBumpJHLevel();
				Canburglars.setRightDownNoBumpJHLevel();
				
				if((Canburglars.getLeftPosition() > Constants.LEFT_BURGLARS_DOWN_NO_STEP_TRIGGER && Canburglars.getRightPosition() < Constants.RIGHT_BURGLARS_DOWN_NO_STEP_TRIGGER) || autoTimer.get() >= 0.9) {
						DriveTrain.driveDistance(Constants.AUTON_CAN_BURGLARS_PULLBACK_DISTANCE, 1);
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
		
		private static void canBurglarTugOfWar18() {
			DriveTrain.setStrafeUp();
			if(autoTimer.get() <= 3.5) {
				Canburglars.setLeftDownNoBumpJHLevel();
				Canburglars.setRightDownNoBumpJHLevel();
				
				if((Canburglars.getLeftPosition() > Constants.LEFT_BURGLARS_DOWN_NO_STEP_TRIGGER && Canburglars.getRightPosition() < Constants.RIGHT_BURGLARS_DOWN_NO_STEP_TRIGGER) || autoTimer.get() >= 0.9) {
						DriveTrain.driveDistance(Constants.AUTON_CAN_BURGLARS_PULLBACK_DISTANCE, 1);
				}
			}
			Arm.setArmForward();
			Ingestor.setIngestorIn();
		}
		
		static int eo = 0;
		private static void canBurglarAutoNoBumpWingle21() {
			DriveTrain.setStrafeUp();
			if(autoTimer.get() <= 5) {
				Canburglars.setLeftDownNoBump();
				Canburglars.setRightDownNoBump();
				if (autoTimer.get() <= 3 && autoTimer.get() > 1) { 
					System.out.println(eo);
					if((eo/10)%2 == 0) {
						DriveMotors.drive(0.2, 0.2);
					} else {
						DriveMotors.drive(-0.2, -0.2);
					}
				} else {
					DriveMotors.stopMotors();
				}
			} else if(autoTimer.get() < 7) {
				DriveTrain.driveDistance(Constants.AUTON_CAN_BURGLARS_PULLBACK_DISTANCE, 1);
			} else if(autoTimer.get() <= 10.5) {
				Canburglars.setLeftUpNormal();
				Canburglars.setRightUpNormal();
			} else if(autoTimer.get() <= 12){
				DriveMotors.drive(-0.35, -0.35);
			} else {
				DriveMotors.stopMotors();
				Arm.setArmForward();
//				//Elevator.calibrateEncoder();
				Ingestor.setIngestorIn();
			}
			eo++;
		}
}
