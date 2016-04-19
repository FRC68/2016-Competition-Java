
package org.usfirst.frc.team68.robot.subsystems;

import org.usfirst.frc.team68.robot.RobotMap;
import org.usfirst.frc.team68.robot.commands.ReportTotalEnergy;
import org.usfirst.frc.team68.robot.commands.ShooterGeneral;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PDPMonitor extends Subsystem {
    
	private static PDPMonitor monitor;
	private PowerDistributionPanel pdp;
	
    public static PDPMonitor getMonitor() {
    	if (monitor == null) {
    		monitor = new PDPMonitor();
    	}
    	return monitor;
    }
    
    private PDPMonitor() {
    	pdp = new PowerDistributionPanel();
    	
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    	setDefaultCommand(new ReportTotalEnergy());
    }
 
    public double getVoltage(){
    	return pdp.getVoltage();
    }
    
    public double getTotalEnergy(){
    	return pdp.getTotalEnergy();
    }
    
}

