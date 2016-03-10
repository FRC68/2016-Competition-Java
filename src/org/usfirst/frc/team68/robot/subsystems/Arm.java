
package org.usfirst.frc.team68.robot.subsystems;

import org.usfirst.frc.team68.robot.MathUtil;
import org.usfirst.frc.team68.robot.Point;
import org.usfirst.frc.team68.robot.RobotMap;
import org.usfirst.frc.team68.robot.commands.ArmWriteAnglestoDash;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Arm extends Subsystem {
	//Declare Variables
	private static Arm arm;
	public CANTalon motorBase;
	public CANTalon motorShoulder;
	public CANTalon motorElbow;
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
		motorBase.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		//motorShoulder.reverseSensor(true); //Maybe?
		motorBase.changeControlMode(CANTalon.TalonControlMode.Position);
		motorBase.configNominalOutputVoltage(+0.0F,-0.0F);
		motorBase.configPeakOutputVoltage(+12.0f, 0.0f);
    	// set closed loop gains for slot 0
		motorBase.setProfile(RobotMap.armPID.slot);
		motorBase.setF(RobotMap.armPID.f);
		motorBase.setP(RobotMap.armPID.p);
		motorBase.setI(RobotMap.armPID.i);
		motorBase.setD(RobotMap.armPID.d);
		motorBase.configEncoderCodesPerRev(RobotMap.ARM_ENCODER_COUNTS_PER_REV);
		this.setBase(0);
		
		motorShoulder = new CANTalon(RobotMap.ARM_SHOULDER_MOTOR);
		motorShoulder.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		motorShoulder.reverseSensor(true);
		motorShoulder.changeControlMode(CANTalon.TalonControlMode.Position);
		motorShoulder.configNominalOutputVoltage(+0.0F,-0.0F);
		motorShoulder.configPeakOutputVoltage(+12.0f, 0.0f);
    	// set closed loop gains for slot 0
		motorShoulder.setProfile(RobotMap.armPID.slot);
		motorShoulder.setF(RobotMap.armPID.f);
		motorShoulder.setP(RobotMap.armPID.p);
		motorShoulder.setI(RobotMap.armPID.i);
		motorShoulder.setD(RobotMap.armPID.d);

		motorShoulder.configEncoderCodesPerRev(RobotMap.ARM_ENCODER_COUNTS_PER_REV);
		this.setShoulder(0);

		motorElbow = new CANTalon(RobotMap.ARM_ELBOW_MOTOR);
		motorElbow.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		motorElbow.changeControlMode(CANTalon.TalonControlMode.Position);
		motorElbow.reverseOutput(true);
		motorElbow.configNominalOutputVoltage(+0.0F,-0.0F);
		motorElbow.configPeakOutputVoltage(+12.0f, 0.0f);
    	// set closed loop gains for slot 0
		motorElbow.setProfile(RobotMap.armPID.slot);
		motorElbow.setF(RobotMap.armPID.f);
		motorElbow.setP(RobotMap.armPID.p);
		motorElbow.setI(RobotMap.armPID.i);
		motorElbow.setD(RobotMap.armPID.d);

		motorElbow.configEncoderCodesPerRev(RobotMap.ARM_ENCODER_COUNTS_PER_REV);
		this.setElbow(0);
	}
	
    public void initDefaultCommand() {
        //Set the default command for a subsystem here.
        setDefaultCommand(new ArmWriteAnglestoDash());
    }

	public double getBase(){
		basePosition = MathUtil.rotToDegrees(motorBase.getPosition()/RobotMap.ARM_BASE_GEAR_RATIO);
		return basePosition;
	}
	
	public void setBase(double baseAngle){
		double currentPosition;
		double position;
		currentPosition = this.getBase();
		position = MathUtil.degreesToRot(baseAngle*RobotMap.ARM_BASE_GEAR_RATIO);
//		if(!motorBase.isFwdLimitSwitchClosed() || position <= currentPosition) {
			motorBase.setSetpoint(position);
//		}
	}
	
	public double getShoulder(){
		shoulderPosition = MathUtil.rotToDegrees(motorShoulder.getPosition()/RobotMap.ARM_SHOULDER_GEAR_RATIO);
		return shoulderPosition;
	}
	
	public void setShoulder(double shoulderAngle){
		double currentPosition;
		double position;
		currentPosition = this.getShoulder();
		position = MathUtil.degreesToRot(shoulderAngle*RobotMap.ARM_SHOULDER_GEAR_RATIO);
//		if(!motorBase.isFwdLimitSwitchClosed() || position <= currentPosition) {
			motorShoulder.setSetpoint(position);
//		}
	}
	
	public double getElbow(){
		elbowPosition = MathUtil.rotToDegrees(motorElbow.getPosition()/RobotMap.ARM_ELBOW_GEAR_RATIO);
		return elbowPosition;
	}
	
	public void setElbow(double elbowAngle){
		double currentPosition;
		double position;
		currentPosition = this.getElbow();
		position = MathUtil.degreesToRot(elbowAngle*RobotMap.ARM_ELBOW_GEAR_RATIO);
//		if(!motorBase.isFwdLimitSwitchClosed() || position <= currentPosition) {
			motorElbow.setSetpoint(position);
//		}
	}
	
	public void setArmPoint(Point xyz, double threshold) {
		double baseAngle;
		double shoulderAngle;
		double elbowAngle;
		
		//shorter names for arm limb lengths
		double Ls = RobotMap.ARM_SHOULDER_LENGTH;
		double Le = RobotMap.ARM_ELBOW_LENGTH;
		
		if(xyz.x == 0)
			baseAngle = 0;
		else if(xyz.z == 0)
			baseAngle = 90;
		else
			baseAngle = Math.toDegrees(Math.atan(xyz.x/xyz.z));
		
		//The base length of the xz-y plane
		double f = Math.sqrt(Math.pow(xyz.z, 2) + Math.pow(xyz.x, 2));
		
		//The length of the distance from the origin to the end point
		double op = Math.sqrt(Math.pow(f, 2) + Math.pow(xyz.y, 2));
		
		//The component of shoulder angle that is between the line op and f
		double thetaS0 = Math.atan(xyz.y/f);
		//The other component of the shoulder angle
		double thetaS1 = Math.acos((Math.pow(Ls, 2) + Math.pow(op, 2) - Math.pow(Le, 2))/(2*Ls*op));
		shoulderAngle = thetaS0 + thetaS1;
		
		
		
		
		elbowAngle = Math.acos((Math.pow(Ls, 2) + Math.pow(Le, 2) - Math.pow(op, 2))/(2*Ls*Le));
		
		shoulderAngle = 180.0 - Math.toDegrees(shoulderAngle);		
		elbowAngle = Math.toDegrees(elbowAngle);
		
		if(Double.isNaN(shoulderAngle))
			shoulderAngle = 0;
		if(Double.isNaN(elbowAngle))
			elbowAngle = 0;
		
		//Check to see that all angles are possible
		if(!(
				MathUtil.withinRange(RobotMap.BASE_MAX_SAFETY_ANGLE, RobotMap.BASE_MIN_SAFETY_ANGLE, baseAngle) &&
				MathUtil.withinRange(RobotMap.SHOULDER_MAX_SAFETY_ANGLE, RobotMap.SHOULDER_MIN_SAFETY_ANGLE, shoulderAngle) &&
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
						if(this.getElbow() >= RobotMap.ELBOW_CLEARENCE_F_SHOULDER) //shoulder is safe to move
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
				if(this.getElbow() > 10 && this.getShoulder() < 20){
					this.setElbow(this.getElbow());
				}
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