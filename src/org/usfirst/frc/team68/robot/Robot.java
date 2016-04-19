
package org.usfirst.frc.team68.robot;

import org.usfirst.frc.team68.robot.commands.Auton1;
import org.usfirst.frc.team68.robot.commands.Auton2;
import org.usfirst.frc.team68.robot.commands.Auton3;
import org.usfirst.frc.team68.robot.subsystems.Climber;
import org.usfirst.frc.team68.robot.subsystems.DriveCoachAssist;
import org.usfirst.frc.team68.robot.subsystems.Drivetrain;
import org.usfirst.frc.team68.robot.subsystems.GripInterface;
import org.usfirst.frc.team68.robot.subsystems.Intake;
import org.usfirst.frc.team68.robot.subsystems.OffBoardCompressor;
import org.usfirst.frc.team68.robot.subsystems.PDPMonitor;
import org.usfirst.frc.team68.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
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
	public static Climber climber;
	public static PDPMonitor pdpMonitor;
	public static DriveCoachAssist DCA;
	public static GripInterface gripInterface;
//	public static Arm arm;
	public static OffBoardCompressor offBoardCompressor;
//	public static Vision vision;
	//public static USBCamera usbCamera;
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
    	pdpMonitor = PDPMonitor.getMonitor();
    	intake = Intake.getIntake();
    	shooter = Shooter.getShooter();
    	climber = Climber.getClimber();
    	DCA = DriveCoachAssist.getDCA();
    	gripInterface = GripInterface.getGrip();
//    	arm = Arm.getArm();
    	offBoardCompressor = OffBoardCompressor.getOffBoardCompressor();
//    	vision = Vision.getVision();
    	//usbCamera = USBCamera.getUSBCamera();
    	// Make it possible for the drivers to select which auton to run
    	// directly from the SmartDashboard
        chooser = new SendableChooser();
        chooser.addDefault("Auton One (Default) ", new Auton1());
        chooser.addObject("Auton Two ", new Auton2());
        chooser.addObject("Auton Three ", new Auton3());
        SmartDashboard.putData("Auto mode", chooser);
        oi = OI.getOI();
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
     	// schedule the autonomous command 
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
        DCA.zeroTimer();
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
