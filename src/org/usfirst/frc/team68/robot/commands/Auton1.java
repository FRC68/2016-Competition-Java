
package org.usfirst.frc.team68.robot.commands;
import org.usfirst.frc.team68.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class Auton1 extends CommandGroup {

    public Auton1() {
    	addSequential(new DriveAtSpeed(-.6,8)); 
        
    	
    } 
}