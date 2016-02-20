
package org.usfirst.frc.team68.robot.subsystems;

import org.usfirst.frc.team68.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.command.Subsystem;
/**
 *
 */
public class Arm extends Subsystem {
	//Declare Variables
	private static Arm arm;
	private CANTalon motorBase;
	private CANTalon motorShoulder;
	private CANTalon motorElbow;
	private double basePosition;
	private double shoulderPosition;
	private double elbowPosition;
	
	public static Arm getArm() {
		if (arm == null) {
			arm = new Arm();
		}
		return arm;
	}
	
	private Arm(){
		//Initialize motors
		motorBase = new CANTalon(RobotMap.ARM_BASE_MOTOR);
		motorBase.changeControlMode(CANTalon.TalonControlMode.Position);
//		this.setBase(baseAngle);
		motorBase.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		motorShoulder = new CANTalon(RobotMap.ARM_SHOULDER_MOTOR);
		motorShoulder.changeControlMode(CANTalon.TalonControlMode.Position);
//		this.setShoulder(shoulderAngle);
		motorShoulder.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		motorElbow = new CANTalon(RobotMap.ARM_ELBOW_MOTOR);
		motorElbow.changeControlMode(CANTalon.TalonControlMode.Position);
//		this.setElbow(elbowAngle);
		motorElbow.setFeedbackDevice(FeedbackDevice.QuadEncoder);

	}
	
    public void initDefaultCommand() {
        //Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

	private double getBase(){
		basePosition = motorBase.getPosition();
		return basePosition;
	}
	
	private void setBase(double position){
		motorBase.setPosition(position);
	}
	
	private double getShoulder(){
		shoulderPosition = motorShoulder.getPosition();
		return shoulderPosition;
	}
	
	private void setShoulder(double position){
		motorShoulder.setPosition(position);
	}
	
	private double getElbow(){
		elbowPosition = motorElbow.getPosition();
		return elbowPosition;
	}
	
	private void setElbow(double position){
		motorElbow.setPosition(position);
	}
	
	
}