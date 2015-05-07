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
		alex = new CANTalon(Constants.BURGLARS_LEFT_TALON_POS);// With Pot
		austin = new CANTalon(Constants.BURGLARS_RIGHT_TALON_POS);//With Pot
	}

	
	public static void setPower(double power) {
		alex.changeControlMode(ControlMode.PercentVbus);
		austin.changeControlMode(ControlMode.PercentVbus);
		alex.set(power);
		austin.set(power);
	}
	
	
	//normal
	public static void setLeftPIDNormalDown() {
		alex.changeControlMode(ControlMode.Position);
		alex.setFeedbackDevice(FeedbackDevice.AnalogPot);
		alex.reverseOutput(true);
		alex.setPID(Constants.LEFT_BURGLARS_P_NORMAL, Constants.BURGLARS_I_NORMAL, Constants.BURGLARS_D_NORMAL);
	}
	
	public static void setRightPIDNormalDown() {
		austin.changeControlMode(ControlMode.Position);
		austin.setFeedbackDevice(FeedbackDevice.AnalogPot);
		austin.reverseOutput(true);
		austin.setPID(Constants.RIGHT_BURGLARS_P_NORMAL, Constants.BURGLARS_I_NORMAL, Constants.BURGLARS_D_NORMAL);
	}
	//no bump
	public static void setLeftDownNoBump() {
		setLeftPIDNormalDown();
		alex.set(Constants.LEFT_BURGLARS_DOWN_NO_STEP_SETPOINT);
	}
	
	public static void setRightDownNoBump() {
		setRightPIDNormalDown();
		austin.set(Constants.RIGHT_BURGLARS_DOWN_NO_STEP_SETPOINT);
	}
	
	public static void setLeftHoverNoBump() {
		setLeftPIDNormalDown();
		alex.set(Constants.LEFT_BURGLARS_HOVER_NO_STEP_SETPOINT);
	}
	
	public static void setRightHoverNoBump() {
		setRightPIDNormalDown();
		austin.set(Constants.RIGHT_BURGLARS_HOVER_NO_STEP_SETPOINT);
	}
	//bump
	public static void setLeftDownWithBump() {
		setLeftPIDNormalDown();
		alex.set(Constants.LEFT_BURGLARS_DOWN_WITH_STEP_SETPOINT);
	}
	
	public static void setLeftHoverWithBump() {
		setLeftPIDNormalDown();
		alex.set(Constants.LEFT_BURGLARS_HOVER_WITH_STEP_SETPOINT);
	}
	
	public static void setRightDownWithBump() {
		setRightPIDNormalDown();
		austin.set(Constants.RIGHT_BURGLARS_DOWN_WITH_STEP_SETPOINT);
	}
	
	public static void setRightHoverWithBump() {
		setRightPIDNormalDown();
		austin.set(Constants.RIGHT_BURGLARS_HOVER_WITH_STEP_SETPOINT);
	}
	
	public static void setLeftDownDown() {
		setLeftPIDNormalDown();
		alex.set(Constants.LEFT_BURGLARS_DOWN_DOWN);
	}
	
	public static void setRightDownDown() {
		setRightPIDNormalDown();
		austin.set(Constants.RIGHT_BURGLARS_DOWN_DOWN);
	}

	
	//up
	public static void setLeftPIDNormalUp() {
		alex.changeControlMode(ControlMode.Position);
		alex.setFeedbackDevice(FeedbackDevice.AnalogPot);
		alex.reverseOutput(true);
		alex.setPID(Constants.LEFT_BURGLARS_P_SLOW, Constants.SLOW_I, Constants.BURGLARS_D_NORMAL);
	}
	
	public static void setRightPIDNormalUp() {
		austin.changeControlMode(ControlMode.Position);
		austin.setFeedbackDevice(FeedbackDevice.AnalogPot);
		austin.reverseOutput(true);
		austin.setPID(Constants.RIGHT_BURGLARS_P_SLOW, Constants.SLOW_I, Constants.BURGLARS_D_NORMAL);
	}
	
	public static void setLeftUpNormal() {
		setLeftPIDNormalUp();
		alex.set(Constants.LEFT_BURGLARS_UP_SETPOINT);
	}
	
	public static void setRightUpNormal()  {
		setRightPIDNormalUp();
		austin.set(Constants.RIGHT_BURGLARS_UP_SETPOINT);
	}
	
	
	public static double getLeftPosition() {
		return alex.getAnalogInPosition();
	}
	
	public static double getRightPosition() {
		return austin.getAnalogInPosition();
	}
	
	
	public static void disablePID() {
		alex.changeControlMode(ControlMode.Disabled);
		austin.changeControlMode(ControlMode.Disabled);
	}
	
	public static int getLeftPIDError() {
		return alex.getClosedLoopError();
	}
	
	public static int getRightPIDError() {
		return austin.getClosedLoopError();
	}
}
