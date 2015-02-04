package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.Solenoid;

public class Arm {
	
	private static Arm instance = null;
	private static Solenoid armSolenoid;
	
	private Arm() {
		armSolenoid = new Solenoid(Constants.ARM_SOL_POS);
	}
	
	public static Arm getInstance() {
		if(instance == null) {
			instance = new Arm();
		}
		return instance;
	}
	
	public static void armSolenoid(boolean value) {
		armSolenoid.set(value);
	}

}
