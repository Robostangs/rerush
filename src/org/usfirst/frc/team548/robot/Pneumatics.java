package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Timer;

public class Pneumatics {
	
	private static Pneumatics instance = null;
	private static Compressor pressure;
	private static Timer compressorTimer;
	
	private Pneumatics() {
		pressure = new Compressor(Constants.COMPRESSOR_POS);
		compressorTimer = new Timer();
	}
	
	public static Pneumatics getInstance() {
		if(instance == null) {
			instance = new Pneumatics();
		}
		return instance;
	}
	
	public static void compressorOn() {
		pressure.setClosedLoopControl(true);
		pressure.start();
	}
	
	
	public static void compressorOff() {
		pressure.setClosedLoopControl(false);
		pressure.start();
	}
	
	public static void startTimer() {
		compressorTimer.reset();
		compressorTimer.start();
	}

}
