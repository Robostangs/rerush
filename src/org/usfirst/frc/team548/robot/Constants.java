package org.usfirst.frc.team548.robot;

public class Constants {
	//Robot
	public static final int XBOX_DRIVER_PORT = 0;
	public static final int XBOX_MANIP_PORT = 1;
	
	//Drive Train
	public static final int DT_LEFT_BACK_TALON_POS = 1;
	public static final int DT_LEFT_FRONT_TALON_POS = 2;
	public static final int DT_RIGHT_BACK_TALON_POS = 9;
	public static final int DT_RIGHT_FRONT_TALON_POS = 8;
	public static final int DT_CENTER_STRAFE_TALON_POS = 7;
	public static final double DT_HUMAN_DRIVE_THRESHOLD = 0.2;
	public static final double DT_HUMAN_STRAFE_THRESHOLD = 0.15;
	public static final int DT_STRAFE_SOL_POS = 0;
	public static final int DT_LEFT_ENCODER_POS_1 = 0;
	public static final int DT_LEFT_ENCODER_POS_2 = 0;
	public static final int DT_RIGHT_ENCODER_POS_1 = 0;
	public static final int DT_RIGHT_ENCODER_POS_2 = 0;
	public static final int DT_STRAFE_ENCODER_POS_1 = 0;
	public static final int DT_STRAFE_ENCODER_POS_2 = 0;
	public static final int DT_GYRO_POS = 0;
	public static final double DT_STRAFE_MOD = .1;
	
	//Arm
	public static final int ARM_TALON_POS = -1;
	public static final int ARM_SOL_POS = 0;
	
	//Elevator
	public static final int ELEVATOR_LEFT_TALON_POS = 3;
	public static final int ELEVATOR_RIGHT_TALON_POS = 6;
	public static final int ELEVATOR_LEFT_ENCODER_POS_1 = 0;
	public static final int ELEVATOR_LEFT_ENCODER_POS_2 = 0;
	public static final int ELEVATOR_RIGHT_ENCODER_POS_1 = 0;
	public static final int ELEVATOR_RIGHT_ENCODER_POS_2 = 0;
	public static final int ELEVATOR_CONTAINER_SOL_POS = 0;
	public static final int ELEVATOR_LIMIT_SWITCH_POS = 0;
	
	//Ingestor
	public static final int IN_LEFT_TALON_POS = 4;
	public static final int IN_RIGHT_TALON_POS = 5;
	public static final int IN_RIGHT_SOL_POS = 0;
	public static final int IN_LEFT_SOL_POS = 0;
}
