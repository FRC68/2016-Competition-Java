
package org.usfirst.frc.team68.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team68.robot.Robot;

/**
 *
 */
public class GripReportBestTarget extends Command {
	boolean isFinished = false;

    public GripReportBestTarget() {
        // Use requires() here to declare subsystem dependencies
    	requires(Robot.gripInterface);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	double[] tar = Robot.gripInterface.getBestTarget();
    	if(tar != null){
    		SmartDashboard.putNumber("best target X", tar[0]);
    		SmartDashboard.putNumber("best target Y", tar[1]);
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
