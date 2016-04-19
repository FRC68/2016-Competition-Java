
package org.usfirst.frc.team68.robot.commands;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class Auton3 extends CommandGroup {

    public Auton3() {
    	
    	addSequential(new IntakeManualPosition(-9.5));
    	addSequential(new WaitCommand(3));
    	addSequential(new DriveAtSpeed(-.85,3.5)); 

    	
    } 
    
}