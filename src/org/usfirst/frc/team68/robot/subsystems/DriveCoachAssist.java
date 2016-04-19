
package org.usfirst.frc.team68.robot.subsystems;

import org.usfirst.frc.team68.robot.RobotMap;
import org.usfirst.frc.team68.robot.commands.ReportDCA;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveCoachAssist extends Subsystem {
    
	private static DriveCoachAssist dca;
	private double startTime;
	
    public static DriveCoachAssist getDCA() {
    	if (dca == null) {
    		dca = new DriveCoachAssist();
    	}
    	return dca;
    }
    
    private DriveCoachAssist() {
    	    	
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    	setDefaultCommand(new ReportDCA());
    }
 
    public double getTimeRemaining(){
    	return RobotMap.MATCH_TIME - (Timer.getFPGATimestamp()-startTime);
    }
    
    public void zeroTimer(){
    	startTime = Timer.getFPGATimestamp();
    }
    
}

