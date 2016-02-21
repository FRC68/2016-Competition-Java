
package org.usfirst.frc.team68.robot.subsystems;

import org.usfirst.frc.team68.robot.MathUtil;
import org.usfirst.frc.team68.robot.Point;
import org.usfirst.frc.team68.robot.PointPath;
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
		
		//TODO -=- I've not even started thinking about making sure the intake is in position -+-
		
		//Set joints to angles
		int iterations = 0;
		while(
				!(
				MathUtil.withinThresh(this.getElbow(), elbowAngle, threshold) && 
				MathUtil.withinThresh(this.getShoulder(), shoulderAngle, threshold) && 
				MathUtil.withinThresh(this.getBase(), baseAngle, threshold)
				) &&
				iterations < RobotMap.ARM_SETARMPOINT_ITERATION_MAX //Timeout! iteration ceiling reached!
				) {

			iterations++; 
			
			if(MathUtil.withinRange(RobotMap.SHOULDER_CLEARENCE_F_BASE, -1, shoulderAngle)){ //N.B. -1, this is 0 inclusive 
				/*
				 * If this is the case, then the base needs to move before anything else. Because moving the shoulder before the base would
				 * cause the shoulder to move into a self-damaging position.
				 */
				this.setBase(0); //because of checks earlier, base must be 0 here
				if(MathUtil.withinThresh(this.getBase(), 0, threshold)){
					//Okay!, Shoulder will clear chassis. (base is at 0)
					
					
					if(MathUtil.withinRange(RobotMap.ELBOW_CLEARENCE_F_SHOULDER, -1, elbowAngle)){ //N.B. -1, this is 0 inclusive
						// In this case, we are going down, so the shoulder must move before the elbow, and the shoulder angle must be 0
						
						this.setShoulder(0);
						if(MathUtil.withinThresh(this.getShoulder(), 0, threshold)){
							//Okay! safe to move Elbow!
							this.setElbow(elbowAngle);
						}
						
					}else{
						//in this case, we are going up, so the elbow must move 
						//Normal operation for the last two joints
						this.setElbow(elbowAngle);
						if(this.getShoulder() >= RobotMap.ELBOW_CLEARENCE_F_SHOULDER) //shoulder is safe to move
							this.setShoulder(shoulderAngle);
						
					}
				}
				
				
			} else if(MathUtil.withinRange(RobotMap.ELBOW_CLEARENCE_F_SHOULDER, -1, elbowAngle)){
					/*
					 * In this case, since the only time the elbow is allowed to be below ELBOW_CLEARENCE_F_SHOULDER is when the shoulder is 
					 * at 0, the base must already be at 0 (don't worry, we'll make sure anyway), and the shoulder must be moving to
					 * 0. So the shoulder must move before the elbow to avoid being trapped.
					 */
					this.setBase(0);
					if(MathUtil.withinThresh(this.getBase(), 0, threshold)){
						//Okay!, Shoulder will clear chassis. (base is at 0)
						this.setShoulder(0);
						if(MathUtil.withinThresh(this.getShoulder(), 0, threshold)){
							//Okay! safe to move Elbow!
							this.setElbow(elbowAngle);
						}	
					}
			} else {
				//Here everything is going to a place where there are probably no clearance issues

				this.setElbow(elbowAngle);
				if(this.getElbow() >= RobotMap.ELBOW_CLEARENCE_F_SHOULDER){
					this.setShoulder(shoulderAngle);
					if(this.getShoulder() >= RobotMap.SHOULDER_CLEARENCE_F_BASE)
						this.setBase(baseAngle);
				}
			}
		}
		
		
	}

	public Point getArmPoint(){
		return null;
	}
	
}