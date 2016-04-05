
package org.usfirst.frc.team68.robot.commands;

import org.usfirst.frc.team68.robot.MathUtil;
import org.usfirst.frc.team68.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShooterFireWhenReady extends Command {

	private boolean isFinished = false;
	double timeout;
	
    public ShooterFireWhenReady(double timeoutIn) {
    	requires(Robot.shooter);
    	requires(Robot.intake);
    	timeout = timeoutIn;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	this.setTimeout(timeout);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute(){
    	double currentSpeed = Robot.shooter.getSpeed();
    	double setpoint = Robot.shooter.getSetpoint();;
    	
    	while(!MathUtil.withinThresh(currentSpeed, setpoint, 50) && (Robot.shooter.getSpeed() != 0) && !(Robot.intake.getIntakeArm() < -3)){
        	currentSpeed = Robot.shooter.getSpeed();
        	setpoint = Robot.shooter.getSetpoint();
    	}
    	while(!this.isTimedOut()){
    		Robot.intake.setIntakeSpeed(1);
    	}
    		
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isFinished || this.isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
