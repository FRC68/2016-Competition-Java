
package org.usfirst.frc.team68.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team68.robot.Robot;

public class DriveAtSpeed extends Command {
	
//	  private double distance;
	  private double speed;
	  private double timeout;
//	  private double angle;

    public DriveAtSpeed(double speed, double timeout) {
        // Use requires() here to declare subsystem dependencies
        this.speed = speed;
        this.timeout = timeout;	
        requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(timeout);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.tankDrive(speed, speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return this.isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	speed = 0;
    	Robot.driveTrain.tankDrive(speed, speed);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
