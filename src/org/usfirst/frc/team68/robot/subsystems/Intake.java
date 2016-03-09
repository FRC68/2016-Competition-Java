
package org.usfirst.frc.team68.robot.subsystems;

import org.usfirst.frc.team68.robot.MathUtil;
import org.usfirst.frc.team68.robot.RobotMap;
import org.usfirst.frc.team68.robot.commands.IntakeWithXboxJoystick;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {
	
	//Declare variables
	private DigitalInput beamBreak;
	private CANTalon intakeRoller;
	private CANTalon intakeArm;
	private static Intake intake;
	
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
    	intakeArm.reverseSensor(true);
    	intakeArm.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    	intakeArm.changeControlMode(CANTalon.TalonControlMode.Position);
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
    	double currentPosition;
    	double position;
    	if (beamBreak.get() == false){
    		this.setIntakeSpeed(leftXboxJoystickValue);
    	} else {
    		this.setIntakeSpeed(0);
    	}
    	currentPosition = this.getIntakeArm();
    	position = currentPosition + (rightXboxJoystickValue * RobotMap.INTAKE_JOYSTICK_MULT);
    	if(!intakeArm.isFwdLimitSwitchClosed() || position <= currentPosition ) {
        	this.setIntakeArm(position);
    	}

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