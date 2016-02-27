
package org.usfirst.frc.team68.robot.commands;
import org.usfirst.frc.team68.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class TestSetShooterSpeed extends CommandGroup {

    public TestSetShooterSpeed() {
    	
    	double[] shooterRPM = {3000,6000,9000,12000,15000,18000};
    	
		for(int i=0;i<6;i++) {
    		addSequential(new SetShooterSpeed(shooterRPM[i]));
    		addSequential(new WaitCommand(5));
    	}
        addSequential(new SetShooterSpeed(0));        
    } 
}