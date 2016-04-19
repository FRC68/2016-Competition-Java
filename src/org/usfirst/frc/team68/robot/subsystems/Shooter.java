
package org.usfirst.frc.team68.robot.subsystems;

import org.usfirst.frc.team68.robot.RobotMap;
import org.usfirst.frc.team68.robot.commands.ShooterGeneral;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Shooter extends Subsystem {
    
    private CANTalon primaryMotor;
    private CANTalon followerMotor;
    private static Shooter shooter;
    private DoubleSolenoid hood;
    private boolean reversed;
    
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
    	
    	primaryMotor.enableBrakeMode(false);
    	followerMotor.enableBrakeMode(false);
    	
    	primaryMotor.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
    	primaryMotor.reverseSensor(false);
    	//primaryMotor.configNominalOutputVoltage(+0.0F,-0.0F);
    	//primaryMotor.configPeakOutputVoltage(+12.0f, 0.0f);
    	primaryMotor.setVoltageRampRate(8);
    	// set closed loop gains for slot 0
    	primaryMotor.setProfile(RobotMap.shooterPID.slot);
    	primaryMotor.setF(RobotMap.shooterPID.f);
    	primaryMotor.setP(RobotMap.shooterPID.p);
    	primaryMotor.setI(RobotMap.shooterPID.i);
    	primaryMotor.setD(RobotMap.shooterPID.d);
    	hood = new DoubleSolenoid(RobotMap.HOOD_FORWARD, RobotMap.HOOD_REVERSE);
    	this.closeHood();
    	
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here. 
        setDefaultCommand(new ShooterGeneral());
    }
    

    public void setSpeed(double speed) {
    	primaryMotor.set(speed);
    }
    
    public double getSpeed() {
    	return primaryMotor.get();
    }
    public double getSetpoint(){
    	return primaryMotor.getSetpoint();
    }
    
    public double getVoltage() {
    	return primaryMotor.getOutputVoltage();
    }
    
    public double getWattage() {
    	return (primaryMotor.getOutputVoltage() * primaryMotor.getOutputCurrent()) + (followerMotor.getOutputVoltage() * followerMotor.getOutputCurrent());
    }


    public boolean isHoodOpen() {
    	if(hood.get() == Value.kForward) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public void openHood() {
    	hood.set(Value.kForward);
    }
    
    public void closeHood() {
    	hood.set(Value.kReverse);
    }
    
    public void reverseShooter(){
    	primaryMotor.reverseOutput(true);
    	followerMotor.reverseOutput(true);
    	reversed = true;
    }
    public void resetShooterDirection(){
    	primaryMotor.reverseOutput(false);
    	followerMotor.reverseOutput(false);
    	reversed = false;
    }
    
    public boolean getReversal(){
    	return reversed;
    }
    
    public void reverseCurrentHoodPosition() {
    	if(hood.get() == Value.kForward) {
    		this.closeHood(); 
    	} else {
    		this.openHood();
    	
    	}
    }
}

