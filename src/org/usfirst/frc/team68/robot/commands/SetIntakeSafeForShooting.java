
package org.usfirst.frc.team68.robot.commands;

import org.usfirst.frc.team68.robot.Robot;
import org.usfirst.frc.team68.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetIntakeSafeForShooting extends Command {

	private boolean isFinished = false;
	
    public SetIntakeSafeForShooting() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double currentPosition = Robot.intake.getIntakeArm();
    	// !!! TO DO add constant for threshold
        if( currentPosition <= -2 || currentPosition >= 2 ) {
        	Robot.intake.setIntakeArm(RobotMap.INTAKE_ARM_SHOOTING_SAFETY_ANGLE);
        } else {
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
