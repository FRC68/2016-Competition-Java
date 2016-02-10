
package org.usfirst.frc.team68.robot.subsystems;

import org.usfirst.frc.team68.robot.RobotMap;
import org.usfirst.frc.team68.robot.commands.DriveWithJoysticks;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {
    
	private CANTalon leftFront;
	private CANTalon leftRear;
	private CANTalon rightFront;
	private CANTalon rightRear;
	private RobotDrive drive;
	private static boolean useSquaredInputs = true;
	private static DriveTrain driveTrain;
	
	public static DriveTrain getDrive () {
		if (driveTrain == null) {
			driveTrain = new DriveTrain();
		}
		return driveTrain;
	}
    // Constructor
	private DriveTrain() {
		
		// Instantiate Drive Motors
		leftFront = new CANTalon(RobotMap.DRIVE_LEFT_FRONT);
		rightFront = new CANTalon(RobotMap.DRIVE_RIGHT_FRONT);
		leftRear = new CANTalon(RobotMap.DRIVE_LEFT_REAR);
		rightRear = new CANTalon(RobotMap.DRIVE_RIGHT_REAR);
		
		// Create the drive train
		drive = new RobotDrive(leftFront, leftRear, rightFront, rightRear);
		
		// Reverse the motors such that joystick forward produces positive
		// values and joystick backward produces negative values
		drive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
		drive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
		drive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
		drive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
		
		// Enable the fail safe for the drive train
		drive.setSafetyEnabled(true);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    	setDefaultCommand(new DriveWithJoysticks());
    }
    public void tankDrive(double leftSpeed, double rightSpeed) {
    	drive.tankDrive(leftSpeed, rightSpeed, useSquaredInputs);
    }
}

//Jennifer Smith.