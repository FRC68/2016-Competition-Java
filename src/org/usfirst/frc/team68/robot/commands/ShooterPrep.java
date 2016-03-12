
package org.usfirst.frc.team68.robot.commands;
import org.usfirst.frc.team68.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ShooterPrep extends CommandGroup {

    public ShooterPrep() {
        addSequential(new OpenHood());
        addSequential(new SetShooterSpeed(5800));
    } 
}