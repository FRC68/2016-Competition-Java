
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
    	intakeArm.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Absolute);
    	intakeArm.changeControlMode(CANTalon.TalonControlMode.Position);
    	//intakeArm.configEncoderCodesPerRev(RobotMap.INTAKE_ARM_ENCODER_COUNTS_PER_REV);
    	intakeArm.setPID(RobotMap.IntakeArmPID.p, RobotMap.IntakeArmPID.i, RobotMap.IntakeArmPID.d);
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
    	double desiredPos;
    	
    	if (beamBreak.get() == true || speedXboxJoystickValue < 0){
    		this.setIntakeSpeed (speedXboxJoystickValue);
    	} else {
    		this.stopIntakeMotor(0);
    	}
    	if(rightXboxJoystickValue != 0){
    		desiredPos = this.getIntakeArm() + (rightXboxJoystickValue * RobotMap.INTAKE_JOYSTICK_MULT);
    		if(!intakeRoller.isFwdLimitSwitchClosed() || desiredPos < this.getIntakeArm()){
    			this.setIntakeArm(desiredPos) ;
    		}else{
    			intakeArm.setPosition(0);
    			this.setIntakeArm(0);
    		}
    	}
    	
    	SmartDashboard.putNumber("Intake Arm Setpoint", intakeArm.getSetpoint());
    	SmartDashboard.putNumber("Intake position", this.getIntakeArm());
    	
    }
  
    public void manualIntakeArm (double rightXboxJoystickValue) {
    	
    }
    
    public void stopIntakeMotor (double speed) {
    	this.setIntakeSpeed(speed) ;
    } 
    
    public void setIntakeArm(double degrees) {
    	SmartDashboard.putNumber("Intake Set from in here", degrees);
    	intakeArm.setSetpoint(degrees);
    }
    
    public double getIntakeArm(){
    	return intakeArm.getPosition();
    }
    
    public void zeroIntakeArm(){
    	intakeArm.setPosition(0);
    }
}