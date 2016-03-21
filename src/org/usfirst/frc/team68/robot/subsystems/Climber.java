
package org.usfirst.frc.team68.robot.subsystems;

import org.usfirst.frc.team68.robot.Robot;
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
    	
    	winchMotor.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
    	winchMotor.reverseSensor(false);
    	winchMotor.configNominalOutputVoltage(+0.0F,-0.0F);
    	winchMotor.configPeakOutputVoltage(+12.0f, 0.0f);
    	// set closed loop gains for slot 0
    	winchMotor.setProfile(RobotMap.climberPID.slot);
    	winchMotor.setF(RobotMap.climberPID.f);
    	winchMotor.setP(RobotMap.climberPID.p);
    	winchMotor.setI(RobotMap.climberPID.i);
    	winchMotor.setD(RobotMap.climberPID.d);
    	latch = new DoubleSolenoid(RobotMap.CLIMBER_LATCH_FORWARD, RobotMap.CLIMBER_LATCH_REVERSE);
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
    
    public void openLatch() {
    	// Button 8 and Button 9 must be pressed in order to open the latch.
    	// Button 8 triggers the command so we check button 9 before opening
    	if(Robot.oi.getRightJoystickButton9()) {
    		latch.set(Value.kForward);
    	}
    }
    
    public void closeLatch() {
    	latch.set(Value.kReverse);
    }

}

