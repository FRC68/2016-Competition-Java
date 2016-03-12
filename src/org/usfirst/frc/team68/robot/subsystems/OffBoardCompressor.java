
package org.usfirst.frc.team68.robot.subsystems;

import org.usfirst.frc.team68.robot.RobotMap;
import org.usfirst.frc.team68.robot.commands.ReportAirPressure;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class OffBoardCompressor extends Subsystem {
	private Compressor compressor;
	private AnalogInput pressure;
	private static OffBoardCompressor offBoardCompressor;
	
	public static OffBoardCompressor getOffBoardCompressor(){
		if (offBoardCompressor == null) {
			offBoardCompressor = new OffBoardCompressor();
		}
		return offBoardCompressor;
	}
	
	private OffBoardCompressor(){
		compressor = new Compressor(RobotMap.PCM_MAIN);
		pressure = new AnalogInput(RobotMap.AIR_PRESSURE_SENSOR);
		
	}
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public double getAirPressure(){
		double voltage = pressure.getVoltage();
		voltage -= RobotMap.AIR_EMPTY;
		return (voltage/(RobotMap.AIR_FULL-RobotMap.AIR_EMPTY))*100;
				
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new ReportAirPressure());
    }	

}