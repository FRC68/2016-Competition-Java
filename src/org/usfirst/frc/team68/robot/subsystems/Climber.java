
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
public class Climber extends Subsystem {
    
    private CANTalon winchMotor;
    private static Climber climber;
    private DoubleSolenoid latch;
    
    public static Climber getClimber() {
    	if (climber == null) {
    		climber = new Climber();
    	}
    	return climber;
    }
    
    private Climber() {
    	winchMotor = new CANTalon(RobotMap.CLIMBER_WINCH_MOTOR);
    	winchMotor.changeControlMode(CANTalon.TalonControlMode.Speed);
    	this.setSpeed(0);
    	
    	winchMotor.enableBrakeMode(false);
    	
    	winchMotor.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
    	winchMotor.reverseSensor(false);
    	winchMotor.configNominalOutputVoltage(+0.0F,-0.0F);
    	winchMotor.configPeakOutputVoltage(+12.0f, 0.0f);
    	winchMotor.setVoltageRampRate(8);
    	// set closed loop gains for slot 0
    	winchMotor.setProfile(RobotMap.shooterPID.slot);
    	winchMotor.setF(RobotMap.shooterPID.f);
    	winchMotor.setP(RobotMap.shooterPID.p);
    	winchMotor.setI(RobotMap.shooterPID.i);
    	winchMotor.setD(RobotMap.shooterPID.d);
    	latch = new DoubleSolenoid(RobotMap.LATCH_FORWARD, RobotMap.LATCH_REVERSE);
    	this.closeLatch();
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
       // setDefaultCommand(new );
    	
    }

    public void setSpeed(double speed) {
    	winchMotor.set(speed);
    }
    
    public double getSpeed() {
    	return winchMotor.get();
    }
    
    public double getSetpoint(){
    	return winchMotor.getSetpoint();
    }
    
    public boolean isLatchOpen() {
    	if(latch.get() == Value.kForward) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public void openLatch() {
    	latch.set(Value.kForward);
    }
    
    public void closeLatch() {
    	latch.set(Value.kReverse);
    }
    
   
}

