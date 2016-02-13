
package org.usfirst.frc.team68.robot.subsystems;

import org.usfirst.frc.team68.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {
    
    private CANTalon primaryMotor;
    private CANTalon followerMotor;
    private static Shooter shooter;
    private DoubleSolenoid hood;
    
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
    	followerMotor.set(primaryMotor.getDeviceID());
    	primaryMotor.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
    	primaryMotor.reverseSensor(false);
    	primaryMotor.configNominalOutputVoltage(+0.0F,-0.0F);
    	primaryMotor.configPeakOutputVoltage(+12.0f, 0.0f);
    	// set closed loop gains in slot0
    	primaryMotor.setProfile(0);
    	primaryMotor.setF(0.1097);
    	primaryMotor.setP(0.22);
    	primaryMotor.setI(0);
    	primaryMotor.setD(0);
    	hood = new DoubleSolenoid(RobotMap.HOOD_FORWARD,HOOD_REVERSE);
    	this.openHood();
    }
    public void setSpeed(double speed) {
    	primaryMotor.set(speed);
    }
    
    public void openHood() {
    	hood.set(Value.kForward);
    }
    
    public void closeHood() {
    	hood.set(Value.kReverse);
    }
    
    public void reverseCurrentHoodPosition() {
    	if(hood.get() == Value.kForward) {
    		this.closeHood(); 
    	} else {
    		this.openHood();
    	
    	}
    }
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    }
}

