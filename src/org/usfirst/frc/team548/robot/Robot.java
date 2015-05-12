
package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	
	
    public void robotInit() {
    	TeleOperated.getInstance();
    	DriveMotors.getInstance();
    	DriveTrain.getInstance();
    	Arm.getInstance();
    	Elevator.getInstance();
		ElevatorMotors.getInstance();
    	Ingestor.getInstance();
    	DriveMotors.resetEncoders();
    	RRCPServer.getInstance();
    	RRCPServer.startServer();
    }
    
    public void autonomousInit() {

    }
    
    public void autonomousPeriodic() {

    }

    public void teleopPeriodic() {
    	TeleOperated.runDriver();
    	TeleOperated.runManip();
    	SmartDashboard.putNumber("Left Encoder Position", DriveMotors.getLeftEncoderPosition());
    	SmartDashboard.putNumber("Right Encoder Position", DriveMotors.getRightEncoderPosition());
    	SmartDashboard.putNumber("Left Encoder Velocity", DriveMotors.getLeftEncoderVelocity());
    	SmartDashboard.putNumber("Right Encoder Velocity", DriveMotors.getRightEncoderVelocity());
    	SmartDashboard.putNumber("Left Arm Encoder Position", ElevatorMotors.getLeftEncoder());
    	SmartDashboard.putNumber("TOTE LEVEL", Elevator.getToteZone());
    	SmartDashboard.putBoolean("BOT", Elevator.getBotElevatorSwitch());
    }
    
    public void testPeriodic() {
    	SmartDashboard.putBoolean("BOT", Elevator.getBotElevatorSwitch());
    	SmartDashboard.putNumber("Elevator Encoder", ElevatorMotors.getEncoderAverage());
    	LiveWindow.run();
    }
    
   
    public void disabledPeriodic() {
    	SmartDashboard.putBoolean("BOT", Elevator.getBotElevatorSwitch());
    	
    }
}
