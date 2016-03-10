
package org.usfirst.frc.team68.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team68.robot.commands.ArmDrawbridge;
import org.usfirst.frc.team68.robot.commands.ArmHome;
import org.usfirst.frc.team68.robot.commands.ArmPathTest;
import org.usfirst.frc.team68.robot.commands.Auton1;
import org.usfirst.frc.team68.robot.commands.Auton2;
import org.usfirst.frc.team68.robot.commands.Auton3;
import org.usfirst.frc.team68.robot.commands.IntakeManualDown;
import org.usfirst.frc.team68.robot.commands.IntakeZero;
import org.usfirst.frc.team68.robot.commands.ManualMoveBaseLeft;
import org.usfirst.frc.team68.robot.commands.ManualMoveBaseRight;
import org.usfirst.frc.team68.robot.commands.ManualMoveElbowDown;
import org.usfirst.frc.team68.robot.commands.ManualMoveElbowUp;
import org.usfirst.frc.team68.robot.commands.ManualMoveShoulderDown;
import org.usfirst.frc.team68.robot.commands.ManualMoveShoulderUp;
import org.usfirst.frc.team68.robot.subsystems.Arm;
import org.usfirst.frc.team68.robot.subsystems.Drivetrain;
import org.usfirst.frc.team68.robot.subsystems.Intake;
import org.usfirst.frc.team68.robot.subsystems.OffBoardCompressor;
import org.usfirst.frc.team68.robot.subsystems.Shooter;
import org.usfirst.frc.team68.robot.subsystems.Vision;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static Drivetrain driveTrain;
	public static Intake intake;
	public static Shooter shooter;
	public static Arm arm;
	public static OffBoardCompressor offBoardCompressor;
//	public static Vision vision;
	
//	public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	public static RobotMap robotMap;
	public static OI oi;

    Command autonomousCommand;
    SendableChooser chooser;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	robotMap = RobotMap.getRobotMap();
    	driveTrain = Drivetrain.getDrive();
    	intake = Intake.getIntake();
    	shooter = Shooter.getShooter();
    	arm = Arm.getArm();
    	offBoardCompressor = OffBoardCompressor.getOffBoardCompressor();
//    	vision = Vision.getVision();
        chooser = new SendableChooser();
        chooser.addDefault("Auton One (Default) ", new Auton1());
        chooser.addObject("Auton Two ", new Auton2());
        chooser.addObject("Auton Three ", new Auton3());
        SmartDashboard.putData("Auto mode", chooser);
        oi = OI.getOI();
        
        SmartDashboard.putData("Arm Home", new ArmHome());
        SmartDashboard.putData("Arm Path Test", new ArmPathTest());
        SmartDashboard.putData("DT path", new ArmDrawbridge());
        SmartDashboard.putData("Base Left", new ManualMoveBaseLeft());
        SmartDashboard.putData("Base Rigt", new ManualMoveBaseRight());
        SmartDashboard.putData("Shoulder Up", new ManualMoveShoulderUp());
        SmartDashboard.putData("Shoulder Down", new ManualMoveShoulderDown());
        SmartDashboard.putData("Elbow Down", new ManualMoveElbowDown());
        SmartDashboard.putData("Elbow Up", new ManualMoveElbowUp());
        SmartDashboard.putData("Intake Down", new IntakeManualDown());
        SmartDashboard.putData("Zero Intake", new IntakeZero());
    }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    public void autonomousInit() {
        autonomousCommand = (Command) chooser.getSelected();
        
		/* String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		switch(autoSelected) {
		case "My Auto":
			autonomousCommand = new MyAutoCommand();
			break;
		case "Default Auto":
		default:
			autonomousCommand = new ExampleCommand();
			break;
		} */
    	
    	// schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
