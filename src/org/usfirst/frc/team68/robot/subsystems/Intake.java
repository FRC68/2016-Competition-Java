
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
    
    public void intakeWithXboxJoystick (double speedXboxJoystickValue, double rightXboxJoystickValue) {
    	if (beamBreak.get() == false){
    		this.setIntakeSpeed (speedXboxJoystickValue);
    	} else {
    		this.stopIntakeMotor(0);
    	}
    	if(MathUtil.withinRange(1.0, -1.0, rightXboxJoystickValue))
    		this.setIntakeArm(this.getIntakeArm() + (rightXboxJoystickValue * RobotMap.INTAKE_JOYSTICK_MULT)) ;
    	
    }
  
    public void manualIntakeArm (double rightXboxJoystickValue) {
    	
    }
    
    public void stopIntakeMotor (double speed) {
    	this.setIntakeSpeed(speed) ;
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