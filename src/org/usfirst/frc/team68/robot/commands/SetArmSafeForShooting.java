
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
public class SetArmSafeForShooting extends Command {

	private boolean isFinished = false;
	
    public SetArmSafeForShooting() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.arm);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double currentPosition = Robot.arm.getBase();
        if( MathUtil.withinThresh(currentPosition, RobotMap.ARM_BASE_HOME, RobotMap.ARM_BASE_HOME_THRESHOLD)) {
        	isFinished = true;
        } else {
        	Robot.arm.setBase(RobotMap.ARM_BASE_HOME);
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
