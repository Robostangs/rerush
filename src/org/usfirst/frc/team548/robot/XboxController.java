package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.Joystick;

public class XboxController extends Joystick{
	
	public XboxController(int port) {
		super(port);
	}
	
	public void setLeftRumble(double rumble) {
		setRumble(Joystick.RumbleType.kLeftRumble, (float)rumble);
	}
	
	public void setRightRumble(double rumble) {
		setRumble(Joystick.RumbleType.kRightRumble, (float)rumble);
	}
	
	public double getLeftStickXAxis() {
		return getRawAxis(0);
	}
	
	public double getLeftStickYAxis() {
		return getRawAxis(1);
	}
	
	public double getRightTriggerAxis() {
		return getRawAxis(2);
	}
	
	public double getLeftTriggerAxis() {
		return getRawAxis(3);
	}
	
	public double getRightStickXAxis() {
		return getRawAxis(4);
	}
	
	public double getRightStickYAxis() {
		return getRawAxis(5);
	}

	public boolean getAButton() {
		return getRawButton(0);
	}

	public boolean getBButton() {
		return getRawButton(1);
	}

	public boolean getXButton() {
		return getRawButton(2);
	}

	public boolean getYButton() {
		return getRawButton(3);
	}

	public boolean getLeftBumper() {
		return getRawButton(4);
	}

	public boolean getRightBumper() {
		return getRawButton(5);
	}

	public boolean getBackButton() {
		return getRawButton(6);
	}

	public boolean getStartButton() {
		return getRawButton(7);
	}
	
	public boolean getLeftJoystickButton() {
		return getRawButton(8);
	}
	
	public boolean getRightJoystickButton() {
		return getRawButton(9);
	}
	
	public boolean getRightTriggerButton() {
		if(getRawAxis(2) > 0.2) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean getLeftTriggerButton() {
		if(getRawAxis(3) > 0.2) {
			return true;
		} else {
			return false;
		}
	}
	
	public int getDPad() {
		return getPOV();
	}
	
	public boolean isDPadTopHalf() {
		if(getDPad() == 7 || getDPad() == 0 || getDPad() == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isDPadBottomHalf() {
		if(getDPad() == 3 || getDPad() == 4 || getDPad() == 5) {
			return true;
		} else {
			return false;
		}
	}
	
	public double getBothTriggerAxis() {
		return (getRightTriggerAxis()-getLeftTriggerAxis());
	}
}