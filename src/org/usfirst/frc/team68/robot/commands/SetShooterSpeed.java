
package org.usfirst.frc.team68.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team68.robot.MathUtil;
import org.usfirst.frc.team68.robot.Point;
import org.usfirst.frc.team68.robot.Robot;

/**
 *
 */
public class SetShooterSpeed extends Command {

	private boolean isFinished = false;
	private double shootingSpeed;
	
    public SetShooterSpeed(double speed) {
    	shootingSpeed = speed;
        // Use requires() here to declare subsystem dependencies
        requires(Robot.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute(){
    	double currentSpeed;
    	//temporary shooting speed threshold is 2% 
    	//maybe add a RobotMap constant?
    	currentSpeed = Robot.shooter.getSpeed();
    	if(currentSpeed <= RobotMap.SHOOTER_SHOOTING_SPEED * (100 - RobotMap.SHOOTER_THRESHOLD) || currentSpeed >= RobotMap.SHOOTER_SHOOTING_SPEED * (100 + RobotMap.SHOOTER_THRESHOLD)) {
    		Robot.shooter.setSpeed(shootingSpeed);
    	}
    	else{
    		isFinished = true;
    	}
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
