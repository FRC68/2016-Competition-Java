
package org.usfirst.frc.team68.robot.subsystems;

import org.usfirst.frc.team68.robot.MathUtil;
import org.usfirst.frc.team68.robot.Robot;
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
		intakeRoller.enableBrakeMode(true);
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
    
    public void intakeWithXboxJoystick (double rightXboxJoystickValue, boolean rightJSPush, double leftTrigger, boolean leftBumper) {
    	double desiredPos;
    	
    	rightXboxJoystickValue *= -1;
    	if (leftTrigger > 0){
    		this.setIntakeSpeed (RobotMap.INTAKE_ON_SPEED);
    	} else if((beamBreak.get() && leftBumper) || Robot.oi.getXboxBack()){
    		this.setIntakeSpeed(-1*RobotMap.INTAKE_ON_SPEED);
    	} else {
    		this.stopIntakeMotor(0);
    	}
    	
    	SmartDashboard.putString("BALL:", beamBreak.get()?"NOT DETECTED":"DETECTED");
    	
    	
    	if(!MathUtil.withinThresh(rightXboxJoystickValue, 0, RobotMap.INTAKE_ARM_DEADBAND)){
    		desiredPos = this.getIntakeArm() + (rightXboxJoystickValue * RobotMap.INTAKE_JOYSTICK_MULT);
    		if(!intakeRoller.isFwdLimitSwitchClosed() || desiredPos < this.getIntakeArm() || rightJSPush){
    			this.setIntakeArm(desiredPos) ;
    		}else{
   			intakeArm.setPosition(0);
   			this.setIntakeArm(0);
    		}
    	}
    	SmartDashboard.putNumber("Intake position", -1*this.getIntakeArm());
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