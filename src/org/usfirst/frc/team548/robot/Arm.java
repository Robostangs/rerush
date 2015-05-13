package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Arm {
	
	private static Arm instance = null;
	private static DoubleSolenoid armSolenoid;
	
	private Arm() {
		armSolenoid = new DoubleSolenoid(Constants.ARM_SOL_POS_1, Constants.ARM_SOL_POS_2);
	}
	
	public static Arm getInstance() {
		if(instance == null) {
			instance = new Arm();
		}
		return instance;
	}

	public static void setArmBack() {
		armSolenoid.set(DoubleSolenoid.Value.kForward);
	}
	
	public static void setArmForward() {
		armSolenoid.set(DoubleSolenoid.Value.kReverse);
	}
	
	public static boolean getArmSol() {
		if(armSolenoid.get().value == DoubleSolenoid.Value.kReverse.value) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public static void setArm(boolean bool) {
		if(bool) {
			setArmForward();
		} else {
			setArmBack();
		}
	}
}
