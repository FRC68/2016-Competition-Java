
package org.usfirst.frc.team68.robot.subsystems;

import org.usfirst.frc.team68.robot.RobotMap;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class OffBoardCompressor extends Subsystem {
	private Compressor compressor;
	private static OffBoardCompressor offBoardCompressor;
	
	public static OffBoardCompressor getOffBoardCompressor(){
		if (offBoardCompressor == null) {
			offBoardCompressor = new OffBoardCompressor();
		}
		return offBoardCompressor;
	}
	
	private OffBoardCompressor(){
		compressor = new Compressor(RobotMap.PCM_MAIN);
	}
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }	

}