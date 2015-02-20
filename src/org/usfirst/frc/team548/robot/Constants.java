package org.usfirst.frc.team548.robot;

public class Constants {
	//Robot
	public static final int XBOX_DRIVER_PORT = 0;
	public static final int XBOX_MANIP_PORT = 1;
	
	//Autonomouse
	public static final double AUTON_1_DRIVE_DISTANCE_ROBOT_SET = 7000;
	public static final double AUTON_2_DISTANCE_TO_CONTAINER = -1;
	public static final double AUTON_2_DISTANCE_FROM_CONTAINER = -1;
	public static final double AUTON_3_DISTANCE_TO_CONTAINER = -1;
	public static final double AUTON_3_DISTANCE_TO_TOTE = -1;
	public static final double AUTON_3_TURN_ANGLE = -1;
	public static final double AUTON_3_DISTANCE_TO_AUTO_ZONE = -1;
	public static final double AUTON_4_DISTANCE_TO_FIRST_CONTAINER = -1;
	public static final double AUTON_4_DISTANCE_TO_FIRST_TOTE = -1;
	public static final double AUTON_4_FIRST_TURN_ANGLE = -1;
	public static final double AUTON_4_DISTANCE_TO_SECOND_TOTE = -1;
	public static final double AUTON_4_DISTANCE_TO_THIRD_TOTE = -1;
	public static final double AUTON_4_DISTANCE_BACKING_UP = -1;
	public static final double AUTON_4_SECOND_TURN_ANGLE = -1;
	public static final double AUTON_4_DISTANCE_TO_AUTO_ZONE = -1;
	
	//Drive Train
	public static final int DT_LEFT_BACK_TALON_POS = 1;
	public static final int DT_LEFT_FRONT_TALON_POS = 2;
	public static final int DT_RIGHT_BACK_TALON_POS = 9;
	public static final int DT_RIGHT_FRONT_TALON_POS = 8;
	public static final int DT_CENTER_STRAFE_TALON_POS = 7;
	public static final int DT_STRAFE_SOL_POS = 3;
	public static final int DT_GYRO_POS = 0;
	public static final double DT_HUMAN_DRIVE_THRESHOLD = 0.2;
	public static final double DT_HUMAN_STRAFE_THRESHOLD = 0.15;
	public static final double DT_STRAFE_MOD = .1;
	public static final double DT_SLOW_DRIVE_MULTIPLIER = .5;
	public static final double DT_SUPER_SLOW_DRIVE_MULTIPLIER = .25;
	public static final double DT_SLOW_STRAFE_MULTIPLIER = .5;
	public static final double DT_SUPER_SLOW_STRAFE_MULTIPLIER = .25;
	public static final double DRIVE_DISTANCE_SPEED_LEFT = 0.5;
	public static final double DRIVE_DISTANCE_SPEED_RIGHT = 0.5;
	public static final double DRIVE_TURN_SPEED_LEFT = 0.3;
	public static final double DRIVE_TURN_SPEED_RIGHT = 0.3;
	public static final double DRIVE_STRAIGHT_SLOW_MOD = 0.85;
	public static final double DRIVE_STRAIGHT_FAST_MOD = 1.1;
	
	//Arm
	public static final int ARM_SOL_POS_1 = 6;
	public static final int ARM_SOL_POS_2 = 7;
	
	//Elevator
	public static final int ELEVATOR_LEFT_TALON_POS = 3;
	public static final int ELEVATOR_RIGHT_TALON_POS = 6;
	public static final int ELEVATOR_LEFT_ENCODER_POS_1 = -1;
	public static final int ELEVATOR_LEFT_ENCODER_POS_2 = -1;
	public static final int ELEVATOR_RIGHT_ENCODER_POS_1 = -1;
	public static final int ELEVATOR_RIGHT_ENCODER_POS_2 = -1;
	public static final int ELEVATOR_CONTAINER_SOL_POS = 2;
	public static final int ELEVATOR_TOP_LIMIT_SWITCH_POS = -1;
	public static final int ELEVATOR_BOT_LIMIT_SWITCH_POS = 0;
	public static final double[] ELEVATOR_LEVELS = { 0, //Level 0
		4100, //level 1
		8500, //level 2
		12800, //level 3
		17100, //level 4
		21700, //level 5
		25000}; //level 6
	public static final double ELEVATOR_SPEED = 0.5;
	public static final double ELEVATOR_CALIBRATION_DOWN_POWER = -0.20;
	public static final double ELEVATOR_DEFAULT_P = 0.4;
	public static final double ELEVATOR_DEFAULT_I = 0.0005;
	public static final double ELEVATOR_DEFAULT_D = 100;
	public static final double ELEVATOR_DEFAULT_F = 0.01;
	public static final int ELEVATOR_DEFAULT_IZONE = 1000;
	public static final double ELEVATOR_DEFAULT_RAMP_RATE = 36;
	public static final int ELEVATOR_DEFAULT_PROFILE = 0;
	public static final double ELEVATOR_INPUT_MIN = 0;
	public static final double ELEVATOR_INPUT_MAX = 21000;
	public static final double ELEVATOR_OUTPUT_MIN = -.5;
	public static final double ELEVATOR_OUTPUT_MAX = .5;
	public static final double ELEVATOR_PID_ERROR_TRESHOLD = 300;
	
	
	//Ingestor
	public static final int IN_LEFT_TALON_POS = 4;
	public static final int IN_RIGHT_TALON_POS = 5;
	public static final int IN_RIGHT_SOL_POS_1 = 4;
	public static final int IN_RIGHT_SOL_POS_2 = 0;	
	public static final int IN_LEFT_SOL_POS_1 = 5;
	public static final int IN_LEFT_SOL_POS_2 = 1;
	
	//Pneumatics
	public static final int COMPRESSOR_POS = -1;
}
