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
//		if(driver.getLeftBumper()) {
//			DriveTrain.humanSuperSlowDrive(driver.getLeftStickYAxis(), driver.getRightStickYAxis());
//			DriveTrain.humanSuperSlowDriveStrafe(driver.getBothTriggerAxis());
//		} else if(driver.getRightBumper()) {
//			DriveTrain.humanSlowDrive(driver.getLeftStickYAxis(), driver.getRightStickYAxis());
//			DriveTrain.humanSlowDriveStrafe(driver.getBothTriggerAxis());
//		} else {
//			DriveTrain.humanDrive(driver.getLeftStickYAxis(), driver.getRightStickYAxis());
//			DriveTrain.humanDriveStrafe(driver.getBothTriggerAxis());
//		}
		
		if(driver.getAButton()) {
			DriveTrain.setStrafeDown();
		} else if(driver.getBButton()) {
			DriveTrain.setStrafeUp();
		}
		
		/* 2/7 driver and manip suggestions
		 
		 Strafe using x axis on joysticks and give driver ingesting abilities
		 Strafe left: left joystick x value is less than the negative human drive threshold
		 Strafe right: right joystick x value is greater than the human drive threshold
		 Driver ingest: left trigger
		 Driver exgest: right trigger
		 */
		 
		 if(driver.getLeftStickXAxis() < -Constants.DT_HUMAN_DRIVE_THRESHOLD) {
		  	DriveTrain.humanDriveStrafe(-driver.getLeftStickXAxis());
		 } else if(driver.getRightStickXAxis() > Constants.DT_HUMAN_DRIVE_THRESHOLD) {
			 DriveTrain.humanDriveStrafe(-driver.getRightStickXAxis());
		 } else {
			 DriveTrain.humanDriveStrafe(0);
		 }
		 
		 if(Math.abs(driver.getLeftStickYAxis()) > Constants.DT_HUMAN_DRIVE_THRESHOLD || Math.abs(driver.getRightStickYAxis()) > Constants.DT_HUMAN_DRIVE_THRESHOLD) {
			 DriveTrain.humanDrive(driver.getLeftStickYAxis(), driver.getRightStickYAxis());
		 } else {
			 DriveTrain.humanDrive(0, 0);
		 }
//		 if(Math.abs(driver.getLeftTriggerAxis()) > Constants.DT_HUMAN_STRAFE_THRESHOLD || Math.abs(driver.getRightTriggerAxis()) > Constants.DT_HUMAN_STRAFE_THRESHOLD) {
//			 Ingestor.setIngestorPower(driver.getBothTriggerAxis());
//		 }
		 
		 
	}
	
	public static void runManip() {
//		if(Math.abs(manip.getLeftStickXAxis()) > 0.2) {
//			Ingestor.setIngestorIn();
//			Ingestor.setDirection(manip.getLeftStickXAxis());
//		} else {
//			Ingestor.setIngestorIn();
//			Ingestor.setIngestorPower(manip.getBothTriggerAxis());
//		}
//		
//		if(manip.getLeftBumper()) {
//			Ingestor.setIngestorIn();
//		} else if(manip.getRightBumper()) {
//			Ingestor.setIngestorOut();
//		}
//		
//		if(manip.getStartButton()) {
//			Elevator.setContainerGrabberThingThatPicksUpContainerThingsThatAreRoundAndGreenOpen();
//		} else if(manip.getBackButton()) {
//			Elevator.setContainerGrabberThingThatPicksUpContainerThingsThatAreRoundAndGreenClosed();
//		}
//		
//		if(manip.isDPadTopHalf()) {
//			Arm.setArmForward();
//		} else if(manip.isDPadBottomHalf()) {
//			Arm.setArmBack();
//		}
//		
//		if(manip.getBButton());
	}

}
