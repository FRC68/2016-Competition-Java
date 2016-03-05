
package org.usfirst.frc.team68.robot.commands;
import org.usfirst.frc.team68.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class ShootBoulder extends CommandGroup {

    public ShootBoulder() {
    	// Assumes there is a boulder in the staged position in the intake
    	// 
        addSequential(new SetArmSafeForShooting()); 
        addSequential(new SetIntakeSafeForShooting());
        addSequential(new OpenHood());
        addSequential(new SetShooterSpeed(RobotMap.SHOOTER_SHOOTING_SPEED));
        addSequential(new SetIntakeSpeed(RobotMap.INTAKE_BOULDER_FEED_SPEED));
        addSequential(new WaitCommand(RobotMap.SHOOTER_FEED_DELAY));
        addSequential(new SetIntakeSpeed(0));
        addSequential(new SetShooterSpeed(0));        
        addSequential(new CloseHood());
    } 
}