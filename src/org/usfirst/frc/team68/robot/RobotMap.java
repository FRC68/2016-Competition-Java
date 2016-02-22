package org.usfirst.frc.team68.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
    private static RobotMap robotMap;
    public static PidProfile shooterPID;
    
    public static final String ARM_DRAWBRIDGE_PATH_FILENAME = "/paths/ARM_DBRIDGE.xml";
    public static final String ARM_SALLYPORT_PATH_FILENAME = "/paths/ARM_SPORT.xml";
    
    public static PointPath ArmDrawbridgePath;
    public static PointPath ArmSallyportPath;
    
    public static RobotMap getRobotMap() {
    	if( robotMap == null) {
    		robotMap = new RobotMap();
    	}
    	return robotMap;
    }

	private RobotMap() {
	    //Shooter PID
	    shooterPID = new PidProfile();
	    shooterPID.slot = 0;
	    shooterPID.f = 0.1097;
	    shooterPID.p = 0.22;
	    shooterPID.i = 0;
	    shooterPID.d = 0;
	    
	    ArmDrawbridgePath = PathLoader.loadPath(ARM_DRAWBRIDGE_PATH_FILENAME);
	    ArmSallyportPath = PathLoader.loadPath(ARM_SALLYPORT_PATH_FILENAME);
	}

    // DriveTrain Constants
    public static final int DRIVE_LEFT_FRONT = 1;		// CAN bus ID 1
    public static final int DRIVE_RIGHT_FRONT = 2;		// CAN bus ID 2
    public static final int DRIVE_LEFT_REAR = 3;		// CAN bus ID 3
    public static final int DRIVE_RIGHT_REAR = 4;		// CAN bus ID 4
    public static final int DRIVE_SHIFTER_PCM_A = 2;	// PCM ID 2
    public static final int DRIVE_SHIFTER_PCM_B = 3;	// PCM ID 3
    
    //Shooter Constants
    public static final int SHOOTER_PRIMARY_MOTOR = 5;  // CAN bus ID 5
    public static final int SHOOTER_FOLLOWER_MOTOR = 6; // CAN bus ID 6
    public static final int HOOD_FORWARD = 0;
    public static final int HOOD_REVERSE = 1;    
        
    //Shooter RPM values
    public static double[] shooterRPM = {0,500,1000,1500,2000,2500,3000,3500,4000,4500,5000,5500};
    
    //Arm Constants
    public static final int ARM_BASE_MOTOR = 7;  			// CAN bus ID 7
    public static final int ARM_SHOULDER_MOTOR = 8; 		// CAN bus ID 8
    public static final int ARM_ELBOW_MOTOR = 9;			// CAN bus ID 9
    public static final double ARM_BASE_GEAR_RATIO = 1;
    public static final double ARM_SHOULDER_GEAR_RATIO = 1;
    public static final double ARM_ELBOW_GEAR_RATIO = 1; 
    public static final double ARM_SHOULDER_LENGTH = 24;
    public static final double ARM_ELBOW_LENGTH = 21.5;
    public static final int ARM_ENCODER_COUNTS_PER_REV = 360*4; //four counts a tick probably?
    
    public static final int ARM_SETARMPOINT_ITERATION_MAX = 10000;
    		
    //IN DEGREES!
    public static final double ELBOW_CLEARENCE_F_SHOULDER = 15;
    public static final double SHOULDER_CLEARENCE_F_BASE = 80;
    public static final double BASE_MIN_SAFETY_ANGLE = -90;
    public static final double BASE_MAX_SAFETY_ANGLE = 90;
    public static final double SHOULDER_MIN_SAFETY_ANGLE = 45;
    public static final double SHOULDER_MAX_SAFETY_ANGLE = 145;
    public static final double ELBOW_MIN_SAFETY_ANGLE = 0;
    public static final double ELBOW_MAX_SAFETY_ANGLE = 260;

    
    //Intake Constants
    public static final int INTAKE_ROLLER_MOTOR = 10;  	// CAN bus ID 10
    public static final int INTAKE_ARM_MOTOR = 11;		// CAN bus ID 11
    
    // Shooter Hood Constants

    // Joystick Port Mapping Constants
    public static final int LEFT_JOYSTICK = 0;			// USB port 0
    public static final int RIGHT_JOYSTICK = 1;			// USB port 1
    public static final int JOYSTICK_Y = 1;				// This works for both joysticks
    public static final int JOYSTICK_TRIGGER = 1;		// This works for both joysticks
    
    // xBoxController Button & Axis Mapping Constants
    // Buttons
    public static final int XBOX_CONTROLLER = 2;		// USB port 2
    public static final int XBOX_A = 1;
    public static final int XBOX_B = 2;
    public static final int XBOX_X = 3;
    public static final int XBOX_Y = 4;
    public static final int XBOX_LB = 5;
    public static final int XBOX_RB = 6;
    // Axis
    public static final int XBOX_LY = 1;
    public static final int XBOX_RY = 5;

    
    // Pneumatics Port Mapping Constants
    public static final int PCM_MAIN = 0;
  
}

