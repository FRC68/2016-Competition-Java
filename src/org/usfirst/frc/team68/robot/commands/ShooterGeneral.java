
package org.usfirst.frc.team68.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team68.robot.MathUtil;
import org.usfirst.frc.team68.robot.Point;
import org.usfirst.frc.team68.robot.Robot;
import org.usfirst.frc.team68.robot.RobotMap;

/**
 *
 */
public class ShooterGeneral extends Command {

	private boolean isFinished = false;
	private double shootingSpeed;
	
    public ShooterGeneral() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute(){
    	double currentSpeed, setpoint;
    	double currentVoltage;
    	double currentWattage;
    	
    	boolean intakeSafe = (Robot.intake.getIntakeArm() < -3);
    	
    	//temporary shooting speed threshold is 2% 
    	//maybe add a RobotMap constant?
    	SmartDashboard.putNumber("RT", Robot.oi.getRigthXboxTriggerValue());
    	if(Robot.oi.getRigthXboxTriggerValue() > 0.12){
    		Robot.shooter.openHood();
    	}
    	
    	currentSpeed = Robot.shooter.getSpeed();
    	setpoint = Robot.shooter.getSetpoint();
    	SmartDashboard.putString("Shoot", (MathUtil.withinThresh(currentSpeed, setpoint, 100) && intakeSafe && setpoint != 0)?"CLEAR TO SHOOT":"NOT READY" );
    	SmartDashboard.putString("Intake is", intakeSafe? "SAFE" : "NOT SAFE");
    	SmartDashboard.putNumber("Shooter RPM: ", currentSpeed);
    	currentVoltage = Robot.shooter.getVoltage();
    	SmartDashboard.putNumber("Shooter Voltage: ", currentVoltage);
    	currentWattage = Robot.shooter.getWattage();
    	SmartDashboard.putNumber("Shooter Wattage: ", currentWattage);
    	
    	
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
