
package org.usfirst.frc.team68.robot.commands;

import org.usfirst.frc.team68.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetIntakeSpeed extends Command {

	private boolean isFinished = false;
	private double intakeSpeed;
	
    public SetIntakeSpeed(double speed) {
    	intakeSpeed = speed;
        // Use requires() here to declare subsystem dependencies
        requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute(){
    		Robot.intake.setIntakeSpeed(intakeSpeed);
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
