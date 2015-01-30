
package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.IterativeRobot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
	XboxController driver, manip;
	
    public void robotInit() {
    	driver = new XboxController(Constants.XBOX_DRIVER_PORT);
    	manip = new XboxController(Constants.XBOX_MANIP_PORT);
    	DriveTrain.getInstance();
//    	Elevator.getInstance();
//    	Arm.getInstance();
//    	Ingestor.getInstance();

    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	DriveTrain.humanDrive(driver.getLeftStickYAxis(), driver.getRightStickYAxis());
    	DriveTrain.humanDriveStrafe(driver.getBothTriggerAxis());
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
