
package org.usfirst.frc.team68.robot.subsystems;

import org.usfirst.frc.team68.robot.MathUtil;
import org.usfirst.frc.team68.robot.RobotMap;
import org.usfirst.frc.team68.robot.commands.IntakeWithXboxJoystick;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Intake extends Subsystem {
	
	//Declare variables
	private DigitalInput beamBreak;
	private CANTalon intakeRoller;
	private CANTalon intakeArm;
	private static Intake intake;
	private double intakeDesired;
	
	public static Intake getIntake() {
		if (intake == null)  {
			intake = new Intake();
		}
		return intake;
	}
	
	private Intake() {
		beamBreak = new DigitalInput(RobotMap.INTAKE_BEAM_BREAK);
		intakeRoller = new CANTalon(RobotMap.INTAKE_ROLLER_MOTOR);
    	intakeArm = new CANTalon(RobotMap.INTAKE_ARM_MOTOR);
    	intakeArm.changeControlMode(CANTalon.TalonControlMode.Position);
    	intakeArm.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    	intakeArm.reverseSensor(false);
    	// set closed loop gains for slot 0
    	intakeArm.setProfile(RobotMap.IntakeArmPID.slot);
    	intakeArm.setF(RobotMap.IntakeArmPID.f);
    	intakeArm.setP(RobotMap.IntakeArmPID.p);
    	intakeArm.setI(RobotMap.IntakeArmPID.i);
    	intakeArm.setD(RobotMap.IntakeArmPID.d);
    	intakeArm.configEncoderCodesPerRev(RobotMap.INTAKE_ARM_ENCODER_COUNTS_PER_REV);
    	intakeArm.set(0);
    }

    public void initDefaultCommand() {
        //Set the default command for a subsystem here.
        setDefaultCommand(new IntakeWithXboxJoystick());
    }
    
    public void setIntakeSpeed(double speed) {
    		intakeRoller.set(speed);
    }
    
    public double getIntakeSpeed() {
    	return intakeRoller.get();
    }
    
    public double getSpeed() {
    	return intakeRoller.get();
	}
    
    public void intakeWithXboxJoysticks (double leftXboxJoystickValue, double rightXboxJoystickValue) {
    	double currentSetpoint;
    	double position;
    	
    	if (beamBreak.get() == true){
    		this.setIntakeSpeed(leftXboxJoystickValue);
    	} else {
    		this.setIntakeSpeed(0);
    	}
    	currentSetpoint = intakeArm.getSetpoint();
    	intakeDesired += (rightXboxJoystickValue * RobotMap.INTAKE_JOYSTICK_MULT);
//    	if(!intakeArm.isFwdLimitSwitchClosed() || position <= currentPosition ) {
     	this.setIntakeArm(intakeDesired);
//    	}
    	SmartDashboard.putNumber("Intake Arm Set: ", intakeArm.getSetpoint());
    	SmartDashboard.putNumber("Intake Arm Position: ", intakeArm.getPosition());


    }
  
    public void manualIntakeArm (double rightXboxJoystickValue) {
    	
    }
    
    public void setIntakeArm(double degrees) {
        intakeArm.setSetpoint(MathUtil.degreesToRot(degrees)*RobotMap.INTAKE_ARM_GEAR_RATIO);
    }
    
    public double getIntakeArm(){
    	return MathUtil.rotToDegrees(intakeArm.getPosition()/RobotMap.INTAKE_ARM_GEAR_RATIO);
    }
    
    public void zeroIntakeArm(){
    	intakeArm.setPosition(0);
    }
}