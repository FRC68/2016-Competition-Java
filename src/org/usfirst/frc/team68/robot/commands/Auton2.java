
package org.usfirst.frc.team68.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Auton2 extends CommandGroup {

    public Auton2() {
    	
    	addSequential(new DriveDistance(5, 5, 5));
    	addSequential(new ShooterPrep());
    	addSequential(new IntakePositionArrayIndex(2));
    	addSequential(new DriveDistance(0.75, -0.1, 1));
    	addSequential(new AutoAlign(5));
    	addSequential(new ShooterFireWhenReady(10));
    	
    	
    } 
    
}