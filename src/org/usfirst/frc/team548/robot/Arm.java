package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.Solenoid;

public class Arm {
	
	private static Arm instance = null;
	private static Solenoid armSolenoidLeft, armSolenoidRight;
	
	private Arm() {
		armSolenoidLeft = new Solenoid(Constants.ARM_SOL_LEFT_POS);
		armSolenoidRight = new Solenoid(Constants.ARM_SOL_RIGHT_POS);
	}
	
	public static Arm getInstance() {
		if(instance == null) {
			instance = new Arm();
		}
		return instance;
	}

	public static void setArmForward() {
		armSolenoidLeft.set(true);
		armSolenoidRight.set(true);
	}
	
	public static void setArmBack() {
		armSolenoidLeft.set(false);
		armSolenoidRight.set(false);
	}
	
}
