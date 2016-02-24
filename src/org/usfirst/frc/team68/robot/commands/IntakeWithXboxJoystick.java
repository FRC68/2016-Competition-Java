
package org.usfirst.frc.team68.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team68.robot.Robot;
import org.usfirst.frc.team68.robot.subsystems.Intake;

/**
 * Manual control of the intake
 */
public class IntakeWithXboxJoystick extends Command {
	
    public IntakeWithXboxJoystick() {
        // Use requires() here to declare subsystem dependencies
    	requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.intake.intakeWithXboxJoystick(Robot.oi.getLeftXboxJoystickValue(), Robot.oi.getRightXboxJoystickValue());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	// This command should always run, so always return false
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
