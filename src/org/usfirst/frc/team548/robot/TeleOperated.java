package org.usfirst.frc.team548.robot;

public class TeleOperated {
	
	private static TeleOperated instance;
	public static XboxController driver, manip;
	public static boolean buttonPressed = false, canBurLeft = false, canBurRight = false, buttonPressedX = false, buttonPressedB = false, buttonPressedA = false;
	
	private TeleOperated() {
    	driver = new XboxController(Constants.XBOX_DRIVER_PORT);
    	manip = new XboxController(Constants.XBOX_MANIP_PORT);	
	}
	
	public static TeleOperated getInstance() {
		if(instance == null) {
			instance = new TeleOperated();
		}
		return instance;
	}
	
	public static void runCode() {
		
	}
	
	public static void runDriver() {		
		
		if(driver.getLeftBumper()) {
			if(driver.getLeftStickXAxis() < -Constants.DRIVE_HUMAN_DRIVE_THRESHOLD) {
				DriveTrain.humanSuperSlowDrive(driver.getLeftStickYAxis(), driver.getRightStickYAxis());
				DriveTrain.humanSuperSlowDriveStrafe(-driver.getLeftStickXAxis());
			} else if(driver.getRightStickXAxis() > Constants.DRIVE_HUMAN_DRIVE_THRESHOLD) {
				DriveTrain.humanSuperSlowDrive(driver.getLeftStickYAxis(), driver.getRightStickYAxis());
				DriveTrain.humanSuperSlowDriveStrafe(-driver.getRightStickXAxis());
			} else {
				DriveTrain.humanSuperSlowDrive(driver.getLeftStickYAxis(), driver.getRightStickYAxis());
				DriveTrain.humanSuperSlowDriveStrafe(0);
			}
		 } else if(driver.getRightBumper()) {
			 if(driver.getLeftStickXAxis() < -Constants.DRIVE_HUMAN_DRIVE_THRESHOLD) {
					DriveTrain.humanSlowDrive(driver.getLeftStickYAxis(), driver.getRightStickYAxis());
					DriveTrain.humanSlowDriveStrafe(-driver.getLeftStickXAxis());
				} else if(driver.getRightStickXAxis() > Constants.DRIVE_HUMAN_DRIVE_THRESHOLD) {
					DriveTrain.humanSlowDrive(driver.getLeftStickYAxis(), driver.getRightStickYAxis());
					DriveTrain.humanSlowDriveStrafe(-driver.getRightStickXAxis());
				} else {
					DriveTrain.humanSlowDrive(driver.getLeftStickYAxis(), driver.getRightStickYAxis());
					DriveTrain.humanSlowDriveStrafe(0);
				}
		 } else {
			 if(driver.getLeftStickXAxis() < -Constants.DRIVE_HUMAN_DRIVE_THRESHOLD) {
					DriveTrain.humanDrive(driver.getLeftStickYAxis(), driver.getRightStickYAxis());
					DriveTrain.humanDriveStrafe(-driver.getLeftStickXAxis());
				} else if(driver.getRightStickXAxis() > Constants.DRIVE_HUMAN_DRIVE_THRESHOLD) {
					DriveTrain.humanDrive(driver.getLeftStickYAxis(), driver.getRightStickYAxis());
					DriveTrain.humanDriveStrafe(-driver.getRightStickXAxis());
				} else {
					DriveTrain.humanDrive(driver.getLeftStickYAxis(), driver.getRightStickYAxis());
					DriveTrain.humanDriveStrafe(0);
				}
		 }
		 
	   	 /*
		  * Driver arm solenoid controls
		  * DPAD TOP HALF: arm forward
		  * DPAD BOTTOM HALF: arm backwards
		  */
		 if(driver.isDPadTopHalf()) {
				Arm.setArmForward();
		 } else if(driver.isDPadBottomHalf()) {
				Arm.setArmBack();
		 }
		 
		 /*
		  * Driver wants left trigger to hold strafe wheel up
		  */
		 if(driver.getLeftTriggerButton()) {
			 DriveTrain.setStrafeUp();
		 } else if(!driver.getLeftTriggerButton()){
			 DriveTrain.setStrafeDown();
		 }
		 

	}
	
	public static void runManip() {
		
		/*
		 * Manip ingestor wheel controls
		 * LEFT JOYSTICK X AXIS: shift totes side to side
		 * TRIGGERS: ingest and exgest
		 */
		if(Math.abs(manip.getLeftStickXAxis()) > Constants.XBOX_JOYSTICK_THRESHOLD) {
			Ingestor.setDirection(manip.getLeftStickXAxis());
		} else {
			Ingestor.setIngestorPower(manip.getBothTriggerAxis());
		}
		
		
		/*
		 * Manip ingestor attachment controls
		 * LEFT BUMPER: in/closed
		 * RIGHT BUMPER: out/open 
		 */
		if(manip.getLeftBumper()) {
			Ingestor.setIngestorIn();
		} else if(manip.getRightBumper()) {
			Ingestor.setIngestorOut();
		}
		
		
		/*
		 * Manip container grabber controls
		 * START BUTTON: open grabber
		 * BACK BUTTON: close grabber 
		 */
		if(manip.getStartButton()) {
			Elevator.setContainerGrabberThingThatPicksUpContainerThingsThatAreRoundAndGreenOpen();
		} else if(manip.getBackButton()) {
			Elevator.setContainerGrabberThingThatPicksUpContainerThingsThatAreRoundAndGreenClosed();
		}
		
		
		/*
		 * Manip arm solenoid controls
		 * DPAD TOP HALF: arm forward
		 * DPAD BOTTOM HALF: arm backwards 
		 */
		if(manip.isDPadTopHalf()) {
			Arm.setArmBack();
		} else if(manip.isDPadBottomHalf()) {
			Arm.setArmForward();
		}
		
		
		/*
		 * Manip elevator controls
		 * A BUTTON: down one level
		 * B BUTTON: up one level
		 * X BUTTON: bottom
		 * Y BUTTON: top 
		 * LEFT JOYSTICK: manual
		 */
		if(manip.getAButton()) {
			if(!buttonPressed) {
				buttonPressed = true;
				Elevator.setCurrentLevelSnapshot();
			}
			Elevator.setElevatorDown();
		} else if (manip.getBButton()) {
			if(!buttonPressed) {
				buttonPressed = true;
				Elevator.setCurrentLevelSnapshot();
			}
			Elevator.setElevatorUp();
		} else if (manip.getXButton()) {
			Elevator.setElevatorToLevel(0);
		} else if (manip.getYButton()) {
			if(!buttonPressed) {
				buttonPressed = true;
				Elevator.setCurrentLevelSnapshot();
			}
			Elevator.autoGrabUp();
		} else if(Math.abs(manip.getRightStickYAxis()) > Constants.XBOX_JOYSTICK_THRESHOLD) {
			Elevator.moveElevator(manip.getRightStickYAxis());
		} else if(driver.getBackButton()) {
			Elevator.calibrateEncoder();
		} else {
			buttonPressed = false;
			Elevator.stopElevator();
			Elevator.resetTimer();
		}		
	}
}
