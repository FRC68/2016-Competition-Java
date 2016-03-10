
package org.usfirst.frc.team68.robot.commands;

import org.usfirst.frc.team68.robot.MathUtil;
import org.usfirst.frc.team68.robot.Robot;
import org.usfirst.frc.team68.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Manual control of the intake
 */
public class IntakePositionDownByArray extends Command {
	private boolean isFinished = false;
	
    public IntakePositionDownByArray() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	int index;
    	index = MathUtil.findClosestIndex(RobotMap.intakePositions, Robot.intake.getIntakeArm()) - 1;
    	if(index > RobotMap.intakePositions.length -1)
    		index = RobotMap.intakePositions.length -1;
    	if(index < 0)
    		index = 0;
    	
    	Robot.intake.setIntakeArm(RobotMap.intakePositions[index]);
    	SmartDashboard.putNumber("Intake set to", RobotMap.intakePositions[index]);
    	
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
