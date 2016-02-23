
package org.usfirst.frc.team68.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team68.robot.MathUtil;
import org.usfirst.frc.team68.robot.Point;
import org.usfirst.frc.team68.robot.Robot;

/**
 *
 */
public class SmartDashArmTester extends Command {

	private boolean isDone = false;
    public SmartDashArmTester() {
        // Use requires() here to declare subsystem dependencies
//        requires(Robot.exampleSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	
    	Point xyz = new Point();
    	xyz.x = 0;
    	xyz.y = 30;
    	xyz.z = 20;
    	Robot.arm.setArmPoint(xyz, 5);
    	isDone=true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isDone;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
