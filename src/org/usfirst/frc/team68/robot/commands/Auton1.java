
package org.usfirst.frc.team68.robot.commands;
import org.usfirst.frc.team68.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class Auton1 extends CommandGroup {

    public Auton1() {
        addSequential(new SetDrivePower(0.75));
        addSequential(new WaitCommand(2));
        addSequential(new SetDrivePower(0));
        
    	
    } 
}