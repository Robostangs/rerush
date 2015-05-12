package org.usfirst.frc.team548.robot;

public class Constants {
	//Robot
	public static final int XBOX_DRIVER_PORT = 0;
	public static final int XBOX_MANIP_PORT = 1;
	public static final double XBOX_JOYSTICK_THRESHOLD = 0.2;
	public static final double XBOX_TRIGGER_THRESHOLD = 0.15;
	
	
	
	//Drive Train
	//Ports
	public static final int DRIVE_LEFT_BACK_TALON_POS = 1;
	public static final int DRIVE_LEFT_FRONT_TALON_POS = 2;
	public static final int DRIVE_RIGHT_BACK_TALON_POS = 9;
	public static final int DRIVE_RIGHT_FRONT_TALON_POS = 8;
	public static final int DRIVE_CENTER_STRAFE_TALON_POS = 7;
	public static final int DRIVE_STRAFE_SOL_POS = 3;
	//Thresholds
	public static final double DRIVE_HUMAN_DRIVE_THRESHOLD = 0.2;
	public static final double DRIVE_HUMAN_STRAFE_THRESHOLD = 0.15;
	//Modifiers
	public static final double DRIVE_SLOW_DRIVE_MULTIPLIER = .5;
	public static final double DRIVE_SUPER_SLOW_DRIVE_MULTIPLIER = .25;
	public static final double DRIVE_SLOW_STRAFE_MULTIPLIER = .5;
	public static final double DRIVE_SUPER_SLOW_STRAFE_MULTIPLIER = .25;
	public static final double DRIVE_STRAIGHT_SLOW_MOD = 0.85;
	public static final double DRIVE_STRAIGHT_FAST_MOD = 1.1;
	public static final double DRIVE_STRAFE_MOD = .1;
	
	//Arm
	public static final int ARM_SOL_POS_1 = 6;
	public static final int ARM_SOL_POS_2 = 7;
	
	//Elevator
	//Ports
	public static final int ELEVATOR_LEFT_TALON_POS = 3;
	public static final int ELEVATOR_RIGHT_TALON_POS = 6;
	public static final int ELEVATOR_CONTAINER_SOL_POS = 2;
	public static final int ELEVATOR_BOT_LIMIT_SWITCH_POS = 0;
	//Speeds
	public static final double ELEVATOR_OUTPUT_MIN = -.5;
	public static final double ELEVATOR_OUTPUT_MAX = .5;
	public static final double ELEVATOR_SPEED = 0.5;
	public static final double ELEVATOR_SLOW_SPEED = -.1;
	public static final double ELEVATOR_CALIBRATION_DOWN_POWER = -0.20;
	//Positions
	public static final double ELEVATOR_INPUT_MIN = 0;
	public static final double ELEVATOR_INPUT_MAX = 23200;
	public static final double[] ELEVATOR_LEVELS = { 0, //Level 0
		2750, //level 1*
		7550, //level 2*
		12400, //level 3* 12300
		17150, //level 4*
		21750, //level 5*
		22500};
	public static final double ELEVATOR_OVERSHOOT = 700;
	public static final double ELEVATOR_UNDERSHOOT = -200;
	public static final double ELEVATOR_SLOW_ZONE = 1100;
	public static final double ELEVATOR_ENCODER_RESET_POINT = 0;
	public static final double ELEVATOR_PID_ERROR_TRESHOLD = 300;
	//PID
	public static final double ELEVATOR_DEFAULT_P = 0.4;
	public static final double ELEVATOR_DEFAULT_I = 0.0005;
	public static final double ELEVATOR_DEFAULT_D = 100;
	public static final double ELEVATOR_DEFAULT_F = 0.01;
	public static final int ELEVATOR_DEFAULT_IZONE = 1000;
	public static final double ELEVATOR_DEFAULT_RAMP_RATE = 36;
	public static final int ELEVATOR_DEFAULT_PROFILE = 0;
	
	
	//Ingestor
	//Ports
	public static final int IN_LEFT_TALON_POS = 4;
	public static final int IN_RIGHT_TALON_POS = 5;
	public static final int IN_RIGHT_SOL_POS_1 = 4;
	public static final int IN_RIGHT_SOL_POS_2 = 0;	
	public static final int IN_LEFT_SOL_POS_1 = 5;
	public static final int IN_LEFT_SOL_POS_2 = 1;
	

}
