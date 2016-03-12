
package org.usfirst.frc.team68.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team68.robot.Robot;
import org.usfirst.frc.team68.robot.RobotMap;

/**
 *
 */
public class DriveWithJoysticks extends Command {

    public DriveWithJoysticks() {
        // Use requires() here to declare subsystem dependencies
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.driveTrain.isDrivetrainPercentVbus()) {
        	Robot.driveTrain.tankDrive(Robot.oi.getLeftJoystickValue(), Robot.oi.getRightJoystickValue());	
    	} else if((Robot.oi.getLeftJoystickValue() > RobotMap.DRIVE_RECLAIM) || (Robot.oi.getRightJoystickValue() > RobotMap.DRIVE_RECLAIM) ){
    		Robot.driveTrain.setModePercentVbus();
    	}
    	SmartDashboard.putString("Gear:", Robot.driveTrain.getGear()?"High":"Low");
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
