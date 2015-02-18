package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.CANTalon;

public class TalonEncoder {
	private CANTalon talon;
	private double resetPos;
	
	public TalonEncoder(CANTalon talon) {
		this.talon = talon;
		this.resetPos = this.talon.getEncPosition();
	}
	
	public double getPosition() {
		return talon.getEncPosition();
	}
	public double getVelocity() {
		return talon.getEncVelocity();
	}
	
	public void reset() {
		talon.setPosition(0);
	}
}
