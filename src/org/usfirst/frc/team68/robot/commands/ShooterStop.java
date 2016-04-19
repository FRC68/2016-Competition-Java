
package org.usfirst.frc.team68.robot.commands;

import org.usfirst.frc.team68.robot.MathUtil;
import org.usfirst.frc.team68.robot.Robot;
import org.usfirst.frc.team68.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ShooterStop extends Command {

	private boolean isFinished = false;
	private double currentSpeed = 0;
	
    public ShooterStop() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute(){
    		Robot.shooter.setSpeed(0);
    		Robot.shooter.resetShooterDirection();
    		Robot.shooter.closeHood();
    		isFinished = true;
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	SmartDashboard.putNumber("Shooter Current Speed: ",currentSpeed);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
