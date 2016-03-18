package org.usfirst.frc.team68.robot;

import org.usfirst.frc.team68.robot.commands.CloseHood;
import org.usfirst.frc.team68.robot.commands.DriveShiftHigh;
import org.usfirst.frc.team68.robot.commands.DriveShiftLow;
import org.usfirst.frc.team68.robot.commands.IntakePositionDownByArray;
import org.usfirst.frc.team68.robot.commands.IntakePositionUpByArray;
import org.usfirst.frc.team68.robot.commands.ManualMoveElbowDown;
import org.usfirst.frc.team68.robot.commands.ManualMoveElbowUp;
import org.usfirst.frc.team68.robot.commands.ManualMoveShoulderDown;
import org.usfirst.frc.team68.robot.commands.ManualMoveShoulderUp;
import org.usfirst.frc.team68.robot.commands.OpenHood;
import org.usfirst.frc.team68.robot.commands.SetDriveMultiplierHigh;
import org.usfirst.frc.team68.robot.commands.SetShooterSpeed;
import org.usfirst.frc.team68.robot.commands.ShootBoulder;
import org.usfirst.frc.team68.robot.commands.ShooterPrep;
import org.usfirst.frc.team68.robot.commands.ShooterStop;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
	
	private Joystick leftJoy;
	private Joystick rightJoy;
	private Joystick xboxController;
	
	private Button leftTrigger;
	private Button leftJoyB2;
	private Button rightTrigger;
	private Button rightJoyB2;
	private Button xboxA;
	private Button xboxB;
	private Button xboxX;
	private Button xboxY;
	private Button xboxRB;
	private Button xboxLB;
	private Button xboxSTART;
	private Button xboxBACK;
	
	
	private static OI oi;
	
	public static OI getOI(){
		if (oi == null) {
			oi = new OI();
		}
		return oi;	
	}
	
	private OI(){
		leftJoy = new Joystick(RobotMap.LEFT_JOYSTICK);
		rightJoy = new Joystick(RobotMap.RIGHT_JOYSTICK);
		xboxController = new Joystick(RobotMap.XBOX_CONTROLLER);
		
		leftTrigger = new JoystickButton(leftJoy, RobotMap.JOYSTICK_TRIGGER);
		leftTrigger.whenPressed(new DriveShiftLow());
		rightTrigger = new JoystickButton(rightJoy, RobotMap.JOYSTICK_TRIGGER);
		rightTrigger.whenPressed(new DriveShiftHigh());
		
		leftJoyB2 = new JoystickButton(leftJoy, RobotMap.JOYSTICK_BUTTON2);
		leftJoyB2.whenPressed(new SetDriveMultiplierHigh());
		rightJoyB2 = new JoystickButton(rightJoy, RobotMap.JOYSTICK_BUTTON2);
		rightJoyB2.whenPressed(new SetDriveMultiplierHigh());
		
		xboxA = new JoystickButton(xboxController, RobotMap.XBOX_A);
		xboxA.whenPressed(new IntakePositionDownByArray());
		
		xboxB = new JoystickButton(xboxController, RobotMap.XBOX_B);
		xboxB.whenPressed(new IntakePositionUpByArray());
		
		xboxSTART = new JoystickButton(xboxController, RobotMap.XBOX_START);
		xboxSTART.whenPressed(new ShooterPrep());
		
		xboxRB = new JoystickButton(xboxController, RobotMap.XBOX_RB);
		xboxRB.whenPressed(new ShooterStop());
		

	}
	
	public boolean getRightXboxJoystickButton(){
		return xboxController.getRawButton(RobotMap.XBOX_RJB);
	}
	public boolean getLeftXboxJoystickButton(){
		return xboxController.getRawButton(RobotMap.XBOX_LJB);
	}
	public boolean getLeftBumper(){
		return xboxController.getRawButton(RobotMap.XBOX_LB);
	}
	public boolean getXboxBack(){
		return xboxController.getRawButton(RobotMap.XBOX_BACK);
	}
	
	public double getLeftXboxTriggerValue(){
		double leftAxis;
		leftAxis = xboxController.getRawAxis(RobotMap.XBOX_LT);
		// Allow for up to 10% of joystick noise
		leftAxis = (Math.abs(leftAxis) < 0.1) ? 0 : leftAxis;
    	return leftAxis;
	}
	
	public double getLeftXboxJoystickValue() {
		double leftAxis;
		leftAxis = xboxController.getRawAxis(RobotMap.XBOX_LY);
		// Allow for up to 10% of joystick noise
		leftAxis = (Math.abs(leftAxis) < 0.1) ? 0 : leftAxis;
    	return leftAxis;
	}
	
	public double getRightXboxJoystickValue() {
		double rightAxis;
		rightAxis = xboxController.getRawAxis(RobotMap.XBOX_RY);
		// Allow for up to 10% of joystick noise
		rightAxis = (Math.abs(rightAxis) < 0.1) ? 0 : rightAxis;
    	return rightAxis;
	}
	
    public double getLeftJoystickValue() {
		double leftAxis;
		leftAxis = leftJoy.getY();
		// Allow for up to 10% of joystick noise
		leftAxis = (Math.abs(leftAxis) < 0.1) ? 0 : leftAxis;
    	return leftAxis;
    }
    
    public double getRightJoystickValue() {
		double rightAxis;
		rightAxis = rightJoy.getY();
		// Allow for up to 10% of joystick noise
		rightAxis = (Math.abs(rightAxis) < 0.1) ? 0 : rightAxis;
    	return rightAxis;
    }
}

