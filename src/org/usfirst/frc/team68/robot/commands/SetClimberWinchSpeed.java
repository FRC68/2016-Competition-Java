
package org.usfirst.frc.team68.robot.commands;

import org.usfirst.frc.team68.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SetClimberWinchSpeed extends Command {
	private double winchSpeed;
	private boolean isFinished = false;

    public SetClimberWinchSpeed(double speed) {
        // Use requires() here to declare subsystem dependencies
        winchSpeed = speed;
        requires(Robot.climber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.climber.setSpeed(winchSpeed);
    	// TO DO - make sure we have a limit switches so it will stop itself
    	// regardless of direction OR come up with some other method
    	isFinished = true;
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
