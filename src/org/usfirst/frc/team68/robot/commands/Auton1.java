
package org.usfirst.frc.team68.robot.commands;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class Auton1 extends CommandGroup {

    public Auton1() {
    	addSequential(new DriveAtSpeed(-.6,8)); 
        
    	
    } 
}