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
	}
	
	public static void runManip() {
	}

}
