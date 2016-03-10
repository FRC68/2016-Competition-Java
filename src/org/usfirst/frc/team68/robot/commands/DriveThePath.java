
package org.usfirst.frc.team68.robot.commands;

import org.usfirst.frc.team68.robot.Robot;
import org.usfirst.frc.team68.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveThePath extends Command {
	private static double percent = 0.0;
	private static boolean isFinished = false;
    public DriveThePath() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	percent = 0.0;
    	isFinished = false;
    	Robot.driveTrain.setModePosition();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
     	
    	Robot.driveTrain.setPosition(
    			RobotMap.DtAuton1Path.getPointAtPercent(percent).x, 
    			RobotMap.DtAuton1Path.getPointAtPercent(percent).x);
    	
    	percent += 0.5;
    	if(percent >= 100)
    		isFinished = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.setModePercentVbus();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
