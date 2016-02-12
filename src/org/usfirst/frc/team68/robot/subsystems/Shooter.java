
package org.usfirst.frc.team68.robot.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {
    
    private CANTalon primaryMotor;
    private CANTalon followerMotor;
    private static Shooter shooter;
    
    public static Shooter getShooter() {
    	if (shooter == null) {
    		shooter = new Shooter();
    	}
    	return shooter;
    }
    
    private Shooter() {
    	primaryMotor = new CANTalon(RobotMap.SHOOTER_PRIMARY_MOTOR);
    	primaryMotor.changeControlMode(CANTalon.TalonControlMode.Speed);
    	followerMotor = new CANTalon(RobotMap.SHOOTER_FOLLOWER_MOTOR);
    	followerMotor.changeControlMode(CANTalon.TalonControlMode.Follower);
    	followerMotor.set(primaryMotor.getDeviceID(RobotMap.SHOOTER_PRIMARY_MOTOR));
    	primaryMotor.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Absolute);
    }
    
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    }
}

