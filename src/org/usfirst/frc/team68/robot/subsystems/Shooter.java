
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
    	this.setSpeed(0);
    	followerMotor = new CANTalon(RobotMap.SHOOTER_FOLLOWER_MOTOR);
    	followerMotor.changeControlMode(CANTalon.TalonControlMode.Follower);
    	followerMotor.set(primaryMotor.getDeviceID());
    	primaryMotor.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
    	primaryMotor.reverseSensor(false);
    	primaryMotor.configNominalOutputVoltage(+0.0F,-0.0F);
    	primaryMotor.configPeakOutputVoltage(+12.0f, 0.0f);
    	// set closed loop gains for slot 0
    	primaryMotor.setProfile(RobotMap.shooterPID.slot);
    	primaryMotor.setF(RobotMap.shooterPID.f);
    	primaryMotor.setP(RobotMap.shooterPID.p);
    	primaryMotor.setI(RobotMap.shooterPID.i);
    	primaryMotor.setD(RobotMap.shooterPID.d);
    	hood = new DoubleSolenoid(RobotMap.HOOD_FORWARD, RobotMap.HOOD_REVERSE);
    	this.openHood();
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
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
}

