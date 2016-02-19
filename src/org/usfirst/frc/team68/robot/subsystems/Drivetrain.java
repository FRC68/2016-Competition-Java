
package org.usfirst.frc.team68.robot.subsystems;

import org.usfirst.frc.team68.robot.RobotMap;
import org.usfirst.frc.team68.robot.commands.DriveWithJoysticks;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {
    
	private CANTalon leftFront;
	private CANTalon leftRear;
	private CANTalon rightFront;
	private CANTalon rightRear;
	private RobotDrive drive;
	private DoubleSolenoid driveShifter;
	private static boolean useSquaredInputs = true;

	private static Drivetrain driveTrain;
	
	public static Drivetrain getDrive () {
		if (driveTrain == null) {
			driveTrain = new Drivetrain();
		}
		return driveTrain;
	}
    // Constructor
	private Drivetrain() {
		
		// Instantiate Drive Motors
		leftRear = new CANTalon(RobotMap.DRIVE_LEFT_REAR);
		leftRear.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		
		leftFront = new CANTalon(RobotMap.DRIVE_LEFT_FRONT);
		leftFront.changeControlMode(CANTalon.TalonControlMode.Follower);
    	leftFront.set(leftRear.getDeviceID());
    	
		rightRear = new CANTalon(RobotMap.DRIVE_RIGHT_REAR);
		rightRear.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		
		rightFront = new CANTalon(RobotMap.DRIVE_RIGHT_FRONT);
		rightFront.changeControlMode(CANTalon.TalonControlMode.Follower);
    	rightFront.set(rightRear.getDeviceID());

		// Create the drive train. The config on each side is Master/Follower
    	// so we will create the drive with only the master speed controllers
		drive = new RobotDrive(leftRear, rightRear);
		
		// Reverse the motors such that joystick forward produces positive
		// values and joystick backward produces negative values
		//drive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
		drive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
		//drive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
		drive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
		
		// Enable the fail safe for the drivetrain
		drive.setSafetyEnabled(true);
		
		// Create the drive shifter
		driveShifter = new DoubleSolenoid(RobotMap.DRIVE_SHIFTER_PCM_A, RobotMap.DRIVE_SHIFTER_PCM_B);
		// Initialize the shifter to low
		this.setShifterLow();
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    	setDefaultCommand(new DriveWithJoysticks());
    }
    
    public void tankDrive(double leftSpeed, double rightSpeed) {
    	drive.tankDrive(leftSpeed, rightSpeed, useSquaredInputs);
    }
    
    public void setShifterHigh() {
    	driveShifter.set(Value.kForward);
    }
    
    public void setShifterLow() {
    	driveShifter.set(Value.kReverse);
    }
    
    public void shift() {
    	if(this.getShifter() == Value.kForward){
    	  	this.setShifterLow();
    	} else {
    		this.setShifterHigh();
    	}
    }
    
    public DoubleSolenoid.Value getShifter() {
    	return driveShifter.get();
    }
}