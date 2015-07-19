
package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	
	private SendableChooser sc;
	
    public void robotInit() {
    	Autonomous.getInstance();
    	TeleOperated.getInstance();
    	DriveMotors.getInstance();
    	DriveTrain.getInstance();
    	Arm.getInstance();
    	Elevator.getInstance();
		ElevatorMotors.getInstance();
    	Ingestor.getInstance();
    	DriveMotors.resetEncoders();
    	Canburglars.getInstance();
    	sc = new SendableChooser();
//    	sc.addObject("Back into auto zone", 1);
//    	sc.addObject("Strafe to auto zone w tote + container", 4);
//    	sc.addObject("Back to auto zone w container", 6);
//    	sc.addObject("Strafe to auto zone w container", 7);
//    	sc.addObject("CB, NO bump, time", 10);
//    	sc.addObject("CB WITH bump, time", 11);
//    	sc.addDefault("CB NO bump, pot", 12);
//    	sc.addObject("CB WITH bump, pot", 13);
//    	sc.addObject("CB NO bump FAST, pot", 14);
//    	sc.addObject("CB WITH bump FAST, pot", 15);
//    	sc.addObject("Nothing", 16);
//    	SmartDashboard.putData("Auto type", sc);
    }
    
    public void autonomousInit() {
//    	Autonomous.setAutoMode((int)sc.getSelected());
    	Autonomous.startTimer();
    	DriveMotors.resetEncoders();
    }
    
    public void autonomousPeriodic() {
    	Autonomous.run();
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
    	Canburglars.setLeftPIDNormalDown();
    }
    
   
    public void disabledPeriodic() {
    	SmartDashboard.putBoolean("BOT", Elevator.getBotElevatorSwitch());
//    	SmartDashboard.putData("Auto type", sc);
    	
    }
}
