
package org.usfirst.frc.team68.robot.subsystems;

import org.usfirst.frc.team68.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
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
    }

    public void initDefaultCommand() {
        //Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setIntakeSpeed(double speed) {
    	intakeRoller.set(speed);
    }
    
    public double getIntakeSpeed() {
    	return intakeMotor.get();
    	
    }
    
    
    public static void getSpeed() {
	}
    
    public void intakeWithXboxJoysticks (double speedXboxJoystickValue) {
    	this.setIntakeSpeed (speedXboxJoystickValue) ;
    	
    }
    
    public void stopIntakeMotor (double speed) {
    	this.setIntakeSpeed(0) ;
    } 
    
    public void setIntakeArm() {
    	
    }
}