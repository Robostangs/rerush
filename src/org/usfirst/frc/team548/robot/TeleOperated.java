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
		
		/*
		 * Driving controls with trigger strafe
		 * Includes super slow with left bumper, slow with right bumper
		 * 
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
		*/
		
		
		/*
		 * A BUTTON: strafe wheel down
		 * B BUTTON: strafe wheel up
		 */
		if(driver.getAButton()) {
			DriveTrain.setStrafeDown();
		} else if(driver.getBButton()) {
			DriveTrain.setStrafeUp();
		}
		
		/*
		 * Outside x axis human strafe
		 * LEFT BUMPER: Super slow human strafe
		 * RIGHT BUMPER: Slow human strafe
		 * WHEEL IS ONLY DOWN WHEN STRAFE IS ACTIVATED. OTHERWISE WHEEL GOES UP
		 */
		if(driver.getLeftBumper()) {
			if(driver.getLeftStickXAxis() < -Constants.DT_HUMAN_DRIVE_THRESHOLD) {
				DriveTrain.setStrafeDown();
				DriveTrain.humanSuperSlowDriveStrafe(-driver.getLeftStickXAxis());
			} else if(driver.getRightStickXAxis() > Constants.DT_HUMAN_DRIVE_THRESHOLD) {
				DriveTrain.setStrafeDown();
				DriveTrain.humanSuperSlowDriveStrafe(-driver.getRightStickXAxis());
			} else {
				DriveTrain.setStrafeUp();
				DriveTrain.humanSuperSlowDriveStrafe(0);
			}
		 } else if(driver.getRightBumper()) {
			 if(driver.getLeftStickXAxis() < -Constants.DT_HUMAN_DRIVE_THRESHOLD) {
				 DriveTrain.setStrafeDown();
					DriveTrain.humanSlowDriveStrafe(-driver.getLeftStickXAxis());
				} else if(driver.getRightStickXAxis() > Constants.DT_HUMAN_DRIVE_THRESHOLD) {
					DriveTrain.setStrafeDown();
					DriveTrain.humanSlowDriveStrafe(-driver.getRightStickXAxis());
				} else {
					DriveTrain.setStrafeUp();
					DriveTrain.humanSlowDriveStrafe(0);
				}
		 } else {
			 if(driver.getLeftStickXAxis() < -Constants.DT_HUMAN_DRIVE_THRESHOLD) {
				 DriveTrain.setStrafeDown();
					DriveTrain.humanDriveStrafe(-driver.getLeftStickXAxis());
				} else if(driver.getRightStickXAxis() > Constants.DT_HUMAN_DRIVE_THRESHOLD) {
					DriveTrain.setStrafeDown();
					DriveTrain.humanDriveStrafe(-driver.getRightStickXAxis());
				} else {
					DriveTrain.setStrafeUp();
					DriveTrain.humanDriveStrafe(0);
				}
		 }
		 
		 
		 /*
		  * Human drive
		  * LEFT BUMPER: Super slow human drive
		  * RIGHT BUMPER: Slow human drive
		  */
		 
		 if(driver.getLeftBumper()) {
				DriveTrain.humanSuperSlowDrive(driver.getLeftStickYAxis(), driver.getRightStickYAxis());
			} else if(driver.getRightBumper()) {
				DriveTrain.humanSlowDrive(driver.getLeftStickYAxis(), driver.getRightStickYAxis());
			} else {
				DriveTrain.humanDrive(driver.getLeftStickYAxis(), driver.getRightStickYAxis());
			}
		 
		 /*
		  * Driver arm solenoid controls
		  * DPAD TOP HALF: arm forward
		  * DPAD BOTTOM HALF: arm backwards
		  */
		 if(driver.isDPadTopHalf()) {
				Arm.setArmBack();
		 } else if(driver.isDPadBottomHalf()) {
				Arm.setArmForward();
		 }
		 
		 
		 /*
		  * Driver ingestor controls using triggers
		  *
		 if(Math.abs(driver.getLeftTriggerAxis()) > Constants.DT_HUMAN_STRAFE_THRESHOLD || Math.abs(driver.getRightTriggerAxis()) > Constants.DT_HUMAN_STRAFE_THRESHOLD) {
			 Ingestor.setIngestorPower(driver.getBothTriggerAxis());
		 }
		  */
		 
//		 if(driver.getRightJoystickButton()) {
//			 DriveTrain.driveStraight(0.5, 0.5);
//		 }
//		 
//		 if(driver.getLeftJoystickButton()) {
//			 DriveMotors.drive(-0.5, -0.5);
//		 }
		 
		 if(driver.getStartButton()) {
			 DriveMotors.resetEncoders();
		 }
		 
	}
	
	public static void runManip() {
		
		/*
		 * Manip ingestor wheel controls
		 * LEFT JOYSTICK X AXIS: shift totes side to side
		 * TRIGGERS: ingest and exgest
		 */
		if(Math.abs(manip.getLeftStickXAxis()) > 0.2) {
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
		 *
		if(manip.getAButton()) {
			Elevator.setElevatorDownOneLevel();
		} else if (manip.getBButton()) {
			Elevator.setElevatorUpOneLevel();
		} else if (manip.g
		etXButton()) {
			Elevator.setElevatorToLevel(0);
		} else if (manip.getYButton()) {
			Elevator.setElevatorToLevel(6);
		} else {
			Elevator.moveElevator(manip.getRightStickYAxis());
		} */
		if(Math.abs(manip.getRightStickYAxis()) > 0.2) {
			Elevator.moveElevator((manip.getRightStickYAxis())/2);
		} else {
			Elevator.moveElevator(0);
		}
		
//		if(manip.getAButton()) {
//			Elevator.setElevatorPositionUp(2000);
//		} else {
//			Elevator.moveElevator(0);
//		}

		
		
	}
}
