package org.usfirst.frc.team68.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

    // DriveTrain Port Mapping Constants
    public static final int DRIVE_LEFT_FRONT = 1;		// CAN bus port 1
    public static final int DRIVE_RIGHT_FRONT = 2;		// CAN bus port 2
    public static final int DRIVE_LEFT_REAR = 3;		// CAN bus port 3
    public static final int DRIVE_RIGHT_REAR = 4;		// CAN bus port 4

 // Joystick Port Mapping Constants
    public static final int LEFT_JOYSTICK = 0;			// USB port 0
    public static final int RIGHT_JOYSTICK = 1;			// USB port 1
    public static final int JOYSTICK_Y = 1;				// This works for both joysticks
}

