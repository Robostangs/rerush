package org.usfirst.frc.team548.robot;

public class TeleOperated {
	
	private static TeleOperated instance;
	public static XboxController driver, manip;
	
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
	
	public static void runDriver() {
		if(driver.getLeftBumper()) {
			DriveTrain.humanSuperSlowDrive(driver.getLeftStickYAxis(), driver.getRightStickYAxis());
			DriveTrain.humanSuperSlowDriveStrafe(driver.getBothTriggerAxis());
		} else if(driver.getRightBumper()) {
			DriveTrain.humanSlowDrive(driver.getLeftStickYAxis(), driver.getRightStickYAxis());
			DriveTrain.humanSlowDriveStrafe(driver.getBothTriggerAxis());
		} else {
			DriveTrain.humanDrive(driver.getLeftStickYAxis(), driver.getRightStickYAxis());
			DriveTrain.humanDriveStrafe(driver.getBothTriggerAxis());
		}
		
		if(driver.getAButton()) {
			DriveTrain.setStrafeDown();
		} else if(driver.getBButton()) {
			DriveTrain.setStrafeUp();
		}
	}
	
	public static void runManip() {
		if(Math.abs(manip.getLeftStickXAxis()) > 0.2) {
			Ingestor.setDirection(manip.getLeftStickXAxis());
		} else {
			Ingestor.setIngestorPower(manip.getBothTriggerAxis());
		}
		
		if(manip.getLeftBumper()) {
			Ingestor.setIngestorIn();
		} else if(manip.getRightBumper()) {
			Ingestor.setIngestorOut();
		}
		
		if(manip.getStartButton()) {
			Elevator.setContainerGrabberThingThatPicksUpContainerThingsThatAreRoundAndGreenOpen();
		} else if(manip.getBackButton()) {
			Elevator.setContainerGrabberThingThatPicksUpContainerThingsThatAreRoundAndGreenClosed();
		}
		
		if(manip.isDPadTopHalf()) {
			Arm.setArmForward();
		} else if(manip.isDPadBottomHalf()) {
			Arm.setArmBack();
		}
		
		if(manip.getBButton());
	}

}
