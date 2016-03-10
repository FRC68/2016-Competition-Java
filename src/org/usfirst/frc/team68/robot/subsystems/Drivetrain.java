
package org.usfirst.frc.team68.robot.subsystems;

import org.usfirst.frc.team68.robot.RobotMap;
import org.usfirst.frc.team68.robot.commands.DriveWithJoysticks;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
	private int absolutePositionLeftRear;
	private int absolutePositionRightRear;
	private boolean isPercentVbus = true;

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
		rightRear = new CANTalon(RobotMap.DRIVE_RIGHT_REAR);
//		this.setAbsoluteEncoders();
		
		leftRear.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		leftRear.reverseSensor(false);
		leftRear.configNominalOutputVoltage(+0.0F,-0.0F);
		leftRear.configPeakOutputVoltage(+12.0f, 0.0f);
		leftRear.setAllowableClosedLoopErr(0);  // always servo
    	// set closed loop gains for slot 0
		leftRear.setProfile(RobotMap.driveLeftPID.slot);
    	leftRear.setF(RobotMap.driveLeftPID.f);
    	leftRear.setP(RobotMap.driveLeftPID.p);
    	leftRear.setI(RobotMap.driveLeftPID.i);
    	leftRear.setD(RobotMap.driveLeftPID.d);
    	
		rightRear.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		rightRear.reverseSensor(false);
		rightRear.configNominalOutputVoltage(+0.0F,-0.0F);
		rightRear.configPeakOutputVoltage(+12.0f, 0.0f);
		rightRear.setAllowableClosedLoopErr(0);  // always servo
    	// set closed loop gains for slot 0
		rightRear.setProfile(RobotMap.driveRightPID.slot);
		rightRear.setF(RobotMap.driveRightPID.f);
		rightRear.setP(RobotMap.driveRightPID.p);
		rightRear.setI(RobotMap.driveRightPID.i);
		rightRear.setD(RobotMap.driveRightPID.d);
		
		leftFront = new CANTalon(RobotMap.DRIVE_LEFT_FRONT);
		leftFront.changeControlMode(CANTalon.TalonControlMode.Follower);
    	leftFront.set(leftRear.getDeviceID());
    	
		rightFront = new CANTalon(RobotMap.DRIVE_RIGHT_FRONT);
		rightFront.changeControlMode(CANTalon.TalonControlMode.Follower);
    	rightFront.set(rightRear.getDeviceID());

		// Create the drive train. The configuration on each side is Master/Follower
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
    
    private void setAbsoluteEncoders() {
		absolutePositionLeftRear = leftRear.getPulseWidthPosition() & 0xFFF;
		leftRear.setEncPosition(absolutePositionLeftRear);
		absolutePositionRightRear = rightRear.getPulseWidthPosition() & 0xFFF;
		rightRear.setEncPosition(absolutePositionRightRear);
    }
    
    public boolean isDrivetrainPercentVbus() {
    	return isPercentVbus;
    }
    
    public void setModePercentVbus() {
		leftRear.changeControlMode(CANTalon.TalonControlMode.PercentVbus );
    	leftRear.set(0);
		rightRear.changeControlMode(CANTalon.TalonControlMode.PercentVbus );
    	rightRear.set(0);
    	isPercentVbus = true;
    }
    
    public void setModePosition() {
		leftRear.changeControlMode(CANTalon.TalonControlMode.Position);
    	leftRear.set(0);
		rightRear.changeControlMode(CANTalon.TalonControlMode.Position);
    	rightRear.set(0);
    	leftRear.enable();
    	rightRear.enable();
    	isPercentVbus = false;
    }
    
    public void setPosition(double left, double right){
    	if(this.isDrivetrainPercentVbus())
    		return;
    	
    	leftRear.setSetpoint(left);
    	rightRear.setSetpoint(right);
    	SmartDashboard.putNumber("dtLSet", leftRear.getSetpoint());
    	SmartDashboard.putNumber("dtRset", rightRear.getSetpoint());
    }

}