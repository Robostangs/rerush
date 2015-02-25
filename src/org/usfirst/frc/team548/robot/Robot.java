
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
    	sc = new SendableChooser();
    	sc.addDefault("Drive to auto zone", 1);
    	sc.addObject("Drive to auto with container", 2);
    	sc.addObject("Drive into auto zone with container and tote", 3);
    	sc.addObject("Strafe into auto zone", 6);
    	sc.addObject("Get tote and back into auto zone", 7);
    	SmartDashboard.putData("Auto type", sc);
    }
    
    public void autonomousInit() {
    	Autonomous.setAutoMode((int)sc.getSelected());
    	Autonomous.startTimer();
    	DriveMotors.resetEncoders();
    }
    
    public void autonomousPeriodic() {
    	Autonomous.run();
    }

    public void teleopPeriodic() {
    	TeleOperated.runDriver();
    	TeleOperated.runManip();
    	//SmartDashboard.putNumber("gyro", DriveTrain.getGyroAngle());
    	SmartDashboard.putNumber("Left Encoder Position", DriveMotors.getLeftEncoderPosition());
    	SmartDashboard.putNumber("Right Encoder Position", DriveMotors.getRightEncoderPosition());
    	SmartDashboard.putNumber("Left Encoder Velocity", DriveMotors.getLeftEncoderVelocity());
    	SmartDashboard.putNumber("Right Encoder Velocity", DriveMotors.getRightEncoderVelocity());
    	SmartDashboard.putNumber("Left Arm Encoder Position", ElevatorMotors.getLeftEncoder());
    	SmartDashboard.putNumber("TOTE LEVEL", Elevator.getToteZone());
    }
    
    public void testPeriodic() {
    	SmartDashboard.putBoolean("BOT", Elevator.getBotElevatorSwitch());
    	SmartDashboard.putNumber("Elevator Encoder", ElevatorMotors.getEncoderAverage());
    	LiveWindow.run();
    	System.out.println(ElevatorMotors.getEncoderAverage());
    }
    
   
    public void disabledPeriodic() {
    	SmartDashboard.putBoolean("BOT", Elevator.getBotElevatorSwitch());
    	SmartDashboard.putData("Auto type", sc);
    	
    }
}
