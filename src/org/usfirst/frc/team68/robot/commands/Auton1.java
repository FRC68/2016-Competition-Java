
package org.usfirst.frc.team68.robot.commands;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class Auton1 extends CommandGroup {

    public Auton1() {
    	addSequential(new DriveAtSpeed(-.9,3.5)); 
        
    	
    } 
}