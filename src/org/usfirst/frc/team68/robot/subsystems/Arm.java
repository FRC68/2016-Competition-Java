
package org.usfirst.frc.team68.robot.subsystems;

import org.usfirst.frc.team68.robot.MathUtil;
import org.usfirst.frc.team68.robot.Point;
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
		this.setBase(0);
		motorBase.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		motorShoulder = new CANTalon(RobotMap.ARM_SHOULDER_MOTOR);
		motorShoulder.changeControlMode(CANTalon.TalonControlMode.Position);
		this.setShoulder(0);
		motorShoulder.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		motorElbow = new CANTalon(RobotMap.ARM_ELBOW_MOTOR);
		motorElbow.changeControlMode(CANTalon.TalonControlMode.Position);
		this.setElbow(0);
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
	
	private void setBase(double baseAngle){
		motorBase.setPosition(baseAngle*RobotMap.ARM_BASE_GEAR_RATIO);
	}
	
	private double getShoulder(){
		shoulderPosition = motorShoulder.getPosition();
		return shoulderPosition;
	}
	
	private void setShoulder(double shoulderAngle){
		motorShoulder.setPosition(shoulderAngle*RobotMap.ARM_SHOULDER_GEAR_RATIO);
	}
	
	private double getElbow(){
		elbowPosition = motorElbow.getPosition();
		return elbowPosition;
	}
	
	private void setElbow(double elbowAngle){
		motorElbow.setPosition(elbowAngle*RobotMap.ARM_ELBOW_GEAR_RATIO);
	}
	
	public void setArmPoint(Point xyz, double threshold) {
		double baseAngle;
		double shoulderAngle;
		double elbowAngle;
		
		//Calculation variables
		double shoulderArcTan = ((Math.atan(xyz.y/(Math.sqrt(Math.pow(xyz.x,2))+Math.pow(xyz.z,2)))));
		double shoulderArcCosNum = ((Math.pow(RobotMap.ARM_SHOULDER_LENGTH,2)) + (Math.pow((Math.pow(xyz.x,2)) + ((Math.pow(xyz.y,2)) + ((Math.pow(xyz.z,2)))),2) - (Math.pow(RobotMap.ARM_ELBOW_LENGTH, 2))));
		double shoulderArcCosDen = ((2) * (RobotMap.ARM_SHOULDER_LENGTH) * ((Math.pow(xyz.x,2)) + (Math.pow(xyz.y,2)) + (Math.pow(xyz.z,2))));
		double shoulderArcCos = (Math.acos((shoulderArcCosNum)/(shoulderArcCosDen)));
		double elbowArcCosNum = ((Math.pow(RobotMap.ARM_SHOULDER_LENGTH,2)) - (Math.pow((Math.pow(xyz.x,2)) + ((Math.pow(xyz.y,2)) + ((Math.pow(xyz.z,2)))),2) + (Math.pow(RobotMap.ARM_ELBOW_LENGTH, 2))));
		double elbowArcCosDen = ((2) * (RobotMap.ARM_SHOULDER_LENGTH) * (RobotMap.ARM_ELBOW_LENGTH));
		double elbowArcCos = Math.acos(elbowArcCosNum/elbowArcCosDen);
		
		//Angle Calculations
		baseAngle = Math.atan2(xyz.z,xyz.x);
		shoulderAngle = shoulderArcTan + shoulderArcCos;
		elbowAngle = elbowArcCos;
		
		//Check to see that all angles are possible
		if(!(
				MathUtil.withinRange(RobotMap.BASE_MAX_SAFETY_ANGLE, RobotMap.BASE_MIN_SAFETY_ANGLE, baseAngle) &&
				MathUtil.withinRange(RobotMap.SHOULDER_MAX_SAFETY_ANGLE, RobotMap.SHOULDER_MIN_SAFETY_ANGLE, baseAngle) &&
				MathUtil.withinRange(RobotMap.ELBOW_MAX_SAFETY_ANGLE, RobotMap.ELBOW_MIN_SAFETY_ANGLE, elbowAngle)
				)){
			return;
			//TODO!!!!! HANDLE FAILURE!! ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		}
		
		
		
		//Check to see that secondary clearances are met!
		
		//Elbow angle allows shoulder angle?
		if(MathUtil.withinRange(RobotMap.ELBOW_CLEARENCE_F_SHOULDER, 0, elbowAngle) && shoulderAngle != 0){
			return;
			//TODO!!!!! HANDLE FAILURE!! ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		}
		
		//Shoulder angle allows base angle?
		if(MathUtil.withinRange(RobotMap.SHOULDER_CLEARENCE_F_BASE, 0, shoulderAngle) && baseAngle != 0){
			return;
			//TODO!!!!! HANDLE FAILURE!! ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		}
		
		
		
		//Set joints to angles
		while(!(
				MathUtil.withinThresh(this.getElbow(), elbowAngle, threshold) && 
				MathUtil.withinThresh(this.getShoulder(), shoulderAngle, threshold) && 
				MathUtil.withinThresh(this.getBase(), baseAngle, threshold))) {
		
			
			//TODO!! Determine order and delay joints until clearences are met.
			this.setElbow(elbowAngle);
			this.setShoulder(shoulderAngle);
			this.setBase(baseAngle);
		}
		
		
	}

	public Point getArmPoint(){
		return null;
	}
	
}