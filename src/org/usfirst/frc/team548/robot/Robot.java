
package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.Compressor;
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
    	compressor = new Compressor();
    }
    static boolean runAndroid = false, anCompressor = false, anArmSol = false, anIngSol = false, anCanSol = false, anSetPointBool = false;
    static double anSetPoint = 0, anSetLevel = 1, anSetIng = 0;
    static Compressor compressor;
    static RRCPCommand setEL = new RRCPCommand("setEL") {
		
		@Override
		protected void execute(Object data) {
			if((int)data == -10) {
				anSetPointBool = false;
			} else {
				anSetPointBool = true;
				Robot.anSetPoint = ((double)((int)data)*0.01)*Constants.ELEVATOR_INPUT_MAX;
			}
		}
	};
	
	static RRCPCommand booleanArray = new RRCPCommand("boolArray") {
		
		@Override
		protected void execute(Object data) {
			// TODO Auto-generated method stub
			byte[] b = (byte[])data; 
			Robot.anArmSol = b[0]!=0;
			Robot.anCanSol = b[1]!=0;
			Robot.anIngSol = b[2]!=0;
			Robot.anCompressor = b[3]!=0;
		}
	};
    
	static RRCPCommand setLevel = new RRCPCommand("setLevel") {
		
		@Override
		protected void execute(Object data) {
			// TODO Auto-generated method stub
			byte level = (byte)data;
			Robot.anSetLevel = (double)level;
		}
	};
	

	static RRCPCommand setIng = new RRCPCommand("setIng") {
		
		@Override
		protected void execute(Object data) {
			// TODO Auto-generated method stub
			Robot.anSetIng = (double)((byte)data-50)*0.01;
		}
	};
	
	static RRCPCommand init = new RRCPCommand("init") {
		
		@Override
		protected void execute(Object data) {
			// TODO Auto-generated method stub
			Robot.anArmSol = Arm.getArmSol();
			Robot.anCanSol = Elevator.getCanSol();
			Robot.anIngSol = Ingestor.getIngSol();
			Robot.anCompressor = compressor.getClosedLoopControl();
			this.sendByteArray(new byte[]{(byte)(anArmSol ? 1 : 0), (byte)(anCanSol ? 1 : 0), (byte)(anIngSol ? 1 : 0), (byte)(anCompressor ? 1 : 0), (byte)Elevator.getToteZone()});
			anSetPointBool = false;
			Robot.runAndroid = true;
		}
	};
	
static RRCPCommand staySafe = new RRCPCommand("SOCKETCLOSESD") {
		
		@Override
		protected void execute(Object data) {
			// TODO Auto-generated method stub
			Robot.runAndroid = false;
			TeleOperated.panic();
		}
	};
    
    
    
    public void autonomousInit() {

    }
    
    public void autonomousPeriodic() {

    }

    public void teleopPeriodic() {
    	if(!runAndroid) {
    		TeleOperated.runDriver();
    		TeleOperated.runManip();
    	} else {
    		TeleOperated.runCode();
    	}
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
