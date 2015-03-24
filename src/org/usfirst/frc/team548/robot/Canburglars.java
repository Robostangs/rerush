package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;

public class Canburglars {
	private static Canburglars instance;
	private static CANTalon alex, austin;
	
	public static Canburglars getInstance() {
		if(instance == null) {
			instance = new Canburglars();
		}
		return instance;
	}
	
	private Canburglars() {
		alex = new CANTalon(Constants.BURGLARS_LEFT_TALON_POS);
		austin = new CANTalon(Constants.BURGLARS_RIGHT_TALON_POS);
		setPID();
	}
	
	public static void setPID() {
		alex.setFeedbackDevice(FeedbackDevice.AnalogPot);
		austin.setFeedbackDevice(FeedbackDevice.AnalogPot);
		alex.setPID(Constants.BURGLARS_P, Constants.BURGLARS_I, Constants.BURGLARS_D);
		austin.setPID(Constants.BURGLARS_P, Constants.BURGLARS_I, Constants.BURGLARS_D);
	}
	
	public static void youreGoingToKillSomeoneWithLeft() {
		alex.setFeedbackDevice(FeedbackDevice.AnalogPot);
		alex.changeControlMode(ControlMode.Position);
		alex.reverseOutput(true);
		alex.setPID(Constants.BURGLARS_P, Constants.BURGLARS_I, Constants.BURGLARS_D);
		alex.set(Constants.LEFT_BURGLARS_DOWN_SETPOINT);
	}
	
	public static void youreGoingToKillSomeoneWithRight() {
		austin.changeControlMode(ControlMode.Position);
		austin.setFeedbackDevice(FeedbackDevice.AnalogPot);
		austin.changeControlMode(ControlMode.Position);
		austin.reverseOutput(false);
		austin.setPID(Constants.BURGLARS_P, Constants.BURGLARS_I, Constants.BURGLARS_D);
		austin.set(Constants.RIGHT_BURGLARS_DOWN_SETPOINT);
	}
	
	public static void setLeftUp() {
		alex.set(Constants.LEFT_BURGLARS_UP_SETPOINT);
	}
	
	public static void setRightUp()  {
		austin.set(Constants.RIGHT_BURGLARS_UP_SETPOINT);
	}
	
	public static void disablePID() {
		alex.changeControlMode(ControlMode.Disabled);
		alex.changeControlMode(ControlMode.Disabled);
	}
	
}
