
package org.usfirst.frc.team68.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team68.robot.MathUtil;
import org.usfirst.frc.team68.robot.Robot;
import org.usfirst.frc.team68.robot.RobotMap;
import org.usfirst.frc.team68.robot.subsystems.GripInterface;

/**
 *
 */
public class AutoAlign extends Command {
	boolean isFinished = false;
	double timeout;

    public AutoAlign(double timeoutIn) {
        // Use requires() here to declare subsystem dependencies
    	requires(Robot.driveTrain);
    	requires(Robot.gripInterface);
    	timeout = timeoutIn;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	this.setTimeout(timeout);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double[] goal = Robot.gripInterface.getBestTarget();
		double power = 0;
		if(goal != null && !MathUtil.withinThresh(goal[0], RobotMap.X_TAR, RobotMap.X_TOL)){
			power = GripInterface.getXerror(goal, RobotMap.X_TAR)*RobotMap.DT_VIS_CENTER_P;
			if(power < RobotMap.DT_VIS_TURN_POWER_MIN)
				power = RobotMap.DT_VIS_TURN_POWER_MIN;
			else if(power > RobotMap.DT_VIS_TURN_POWER_MAX)
				power = RobotMap.DT_VIS_TURN_POWER_MAX;
			
			Robot.driveTrain.setPowers(power, -1*power);
		}else{
			Robot.driveTrain.setPower(0);
			isFinished = true;
		}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isFinished || this.isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
