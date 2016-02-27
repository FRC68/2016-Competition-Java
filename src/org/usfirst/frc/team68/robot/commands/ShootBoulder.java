
package org.usfirst.frc.team68.robot.commands;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class ShootBoulder extends CommandGroup {

    public ShootBoulder() {
    	// Assumes there is a boulder in the staged position in the intake
    	// 
    	// The following commented lines of code are a potential solution
    	// that still needs review
    	
/*
        addSequential(new SetArmSafeForShooting()); 
        addSequential(new IntakeDown());
        addSequential(new OpenHood());
        addSequential(new SetShooterSpeed(double shootingSpeed));
        addSequential(new FeedBoulderToShooter());
        addSequential(new WaitCommand(3));
        addSequential(new SetShooterSpeed(0));        
        addSequential(new CloseHood());
*/
    } 
}