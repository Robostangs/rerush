package org.usfirst.frc.team548.robot;

public class Constants {
	//Robot
	public static final int XBOX_DRIVER_PORT = 0;
	public static final int XBOX_MANIP_PORT = 1;
	public static final double XBOX_JOYSTICK_THRESHOLD = 0.2;
	public static final double XBOX_TRIGGER_THRESHOLD = 0.15;
	
	//Autonomous
	//Drive to Auto Zone (1)
	public static final double AUTON_1_DRIVE_DISTANCE_ROBOT_SET = 7000;
	//Drive to Auto Zone with Container based on time (2)
	public static final double AUTON_2_DISTANCE_TO_CONTAINER = -1;
	public static final double AUTON_2_DISTANCE_FROM_CONTAINER = -1;
	//Drive to Auto Zone with Container and Tote (3)
	public static final double AUTON_3_DISTANCE_TO_CONTAINER = -1;
	public static final double AUTON_3_DISTANCE_TO_TOTE = -1;
	public static final double AUTON_3_TURN_ANGLE = -1;
	public static final double AUTON_3_DISTANCE_TO_AUTO_ZONE = -1;
	public static final double AUTON_3_LEFT_SPEED = 0;
	public static final double AUTON_3_RIGHT_SPEED = 0.8;
	public static final double AUTON_3_STRAFE_SPEED = -1;
	//Strafe to Auto Zone with tote and container (4)
	public static final double AUTON_4_LEFT_SPEED = 0;
	public static final double AUTON_4_RIGHT_SPEED = 0;
	public static final double AUTON_4_STRAFE_SPEED = 0.75;
	//Back into auto zone with tote (5)
	public static final double AUTON_5_ELEVATOR_LIFT = 1200;
	public static final double AUTON_5_DRIVE_DISTANCE_BACK = -4600;
	public static final double AUTON_5_DRIVE_DISTANCE_SPEED = 0.5;
	//Back into auto zone with container based on distance (6)
	public static final double AUTON_6_DRIVE_DISTANCE = -4500;
	public static final double AUTON_6_DRIVE_DISTANCE_SPEED = 0.35;
	//Strafe to auto zone with only container (7)
	public static final double AUTON_7_LEFT_SPEED = 0;
	public static final double AUTON_7_RIGHT_SPEED = 0;
	public static final double AUTON_7_STRAFE_SPEED = 0.75;
	//Gets container, places on tote, turns and gets other two totes if middle container removed (8)
	public static final double AUTON_8_DRIVE_DISTANCE_TO_TOTE_1 = 800;
	public static final double AUTON_8_DRIVE_DISTANCE_SPEED = 0.5;
	public static final double AUTON_8_INGEST_POWER = 1;
	public static final double AUTON_8_FIRST_TURN_ANGLE = -1;
	public static final double AUTON_8_TURN_SPEED = 0.5;
	public static final double AUTON_8_DRIVE_DISTANCE_TO_TOTE_2 = 2000;
	public static final double AUTON_8_DRIVE_DISTANCE_TO_TOTE_3 = 2500;
	public static final double AUTON_8_STRAFE_POWER = -1;
	//Gets all three totes in a line by pushing aside containers (9)
	public static final double AUTON_9_DISTANCE_TO_TOTE_1 = 300;
	public static final double AUTON_9_INGEST_POWER = 1;
	public static final double AUTON_9_READJUST_LEFT_SPEED = -0.25;
	public static final double AUTON_9_READJUST_RIGHT_SPEED = 0;
	public static final double AUTON_9_READJUST_STRAFE_SPEED = 0.75;
	public static final double AUTON_9_DISTANCE_TO_TOTE_2 = 5000;
	public static final double AUTON_9_DRIVE_DISTANCE_SPEED = 0.25;
	public static final double AUTON_9_DISTANCE_TO_CONTAINER_1 = 2500;
	public static final double AUTON_9_SET_INGESTOR_DIRECTION = 1;
	public static final double AUTON_9_DISTANCE_TO_TOTE_3 = 5000;
	public static final double AUTON_9_DISTANCE_TO_CONTAINER_2 = 2500;
	public static final double AUTON_9_STRAFE_POWER = 0.75;
	
	
	//Drive Train
	//Ports
	public static final int DRIVE_LEFT_BACK_TALON_POS = 1;
	public static final int DRIVE_LEFT_FRONT_TALON_POS = 2;
	public static final int DRIVE_RIGHT_BACK_TALON_POS = 9;
	public static final int DRIVE_RIGHT_FRONT_TALON_POS = 8;
	public static final int DRIVE_CENTER_STRAFE_TALON_POS = 7;
	public static final int DRIVE_STRAFE_SOL_POS = 3;
	public static final int DRIVE_GYRO_POS = 0;
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
	public static final int ELEVATOR_BOT_LIMIT_SWITCH_POS = 1;
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
	
	//Pneumatics
	//Ports
	public static final int COMPRESSOR_POS = -1;
	
	//Canburglars
	public static final int BURGLARS_LEFT_TALON_POS = 10;
	public static final int BURGLARS_RIGHT_TALON_POS = 11;
	public static final double LEFT_BURGLARS_DOWN_NO_STEP_SETPOINT = 600;
	public static final double LEFT_BURGLARS_DOWN_WITH_STEP_SETPOINT = 550;
	public static final double LEFT_BURGLARS_UP_SETPOINT = 5;
	
	public static final double RIGHT_BURGLARS_DOWN_NO_STEP_SETPOINT = 250;
	public static final double RIGHT_BURGLARS_DOWN_WITH_STEP_SETPOINT = 324;
	public static final double RIGHT_BURGLARS_UP_SETPOINT = 927;
	
	public static final double RIGHT_BURGLARS_DOWN_DOWN = 100;
	public static final double LEFT_BURGLARS_DOWN_DOWN = 772;
	//PID
	public static final double LEFT_BURGLARS_P_NORMAL = 2.5;
	public static final double RIGHT_BURGLARS_P_NORMAL = 2.5;
	
	public static final double LEFT_BURGLARS_P_SLOW = 0.5;
	public static final double RIGHT_BURGLARS_P_SLOW = 0.5;
	public static final double SLOW_I = 0.00025;
	
	public static final double BURGLARS_I_NORMAL = 0;
	public static final double BURGLARS_D_NORMAL = 0;
	
}
