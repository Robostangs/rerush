package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.Talon;

public class ArmMotors {
	
	private static ArmMotors instance = null;
	private static Talon armCIM;
	
	private ArmMotors() {
		armCIM = new Talon(Constants.ARM_TALON_POS);
	}
	
	public static ArmMotors getInstance() {
		if(instance == null) {
			instance = new ArmMotors();
		}
		return instance;
	}
	
	public static void setPower(double power) {
		armCIM.set(power);
	}
	
	public static void stop() {
		armCIM.set(0);
	}

}
