package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.CANTalon;

public class TalonEncoder {
	private CANTalon talon;
	
	public TalonEncoder(CANTalon talon) {
		this.talon = talon;
	}
	
	public double getPosition() {
		return talon.getEncPosition();
	}
	public double getVelocity() {
		return talon.getEncVelocity();
	}
	
	public void reset() {
		talon.setPosition(-1100);
	}
}
