package org.usfirst.frc.team548.robot;

public class Autonomous {
	
	private static Autonomous instance;
	
	private Autonomous() {
		
	}
	
	public static Autonomous getInstance() {
		if(instance == null) {
			instance = new Autonomous();
		}
		return instance;
	}
	
	public static void run() {
		
	}

}
