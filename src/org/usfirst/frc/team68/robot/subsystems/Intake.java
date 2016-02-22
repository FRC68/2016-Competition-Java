
package org.usfirst.frc.team68.robot.subsystems;

import org.usfirst.frc.team68.robot.MathUtil;
import org.usfirst.frc.team68.robot.RobotMap;
import org.usfirst.frc.team68.robot.commands.IntakeWithXboxJoystick;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {
	
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
    	intakeRoller = new CANTalon(RobotMap.INTAKE_ROLLER_MOTOR);
    	intakeArm = new CANTalon(RobotMap.INTAKE_ARM_MOTOR);
    	intakeArm.setFeedbackDevice(FeedbackDevice.QuadEncoder);
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
    
    public void intakeWithXboxJoystick (double speedXboxJoystickValue) {
    	this.setIntakeSpeed (speedXboxJoystickValue);
    }
  
    public void manualIntakeArm (double rightXboxJoystickValue) {
    	this.setIntakeArm(this.getIntakeArm() + (rightXboxJoystickValue * RobotMap.INTAKE_JOYSTICK_MULT)) ;
    }
    
    public void stopIntakeMotor (double speed) {
    	this.setIntakeSpeed(0) ;
    } 
    
    public void setIntakeArm(double degrees) {
    	intakeArm.set(MathUtil.degreesToRot(degrees));
    }
    
    public double getIntakeArm(){
    	return MathUtil.rotToDegrees(intakeArm.get());
    }
}