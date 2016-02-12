
package org.usfirst.frc.team68.robot.subsystems;

import org.usfirst.frc.team68.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
/**
 *
 */
public class Arm extends Subsystem {
	//Declare Variables
	private CANTalon motorBase;
	private CANTalon motorShoulder;
	private CANTalon motorElbow;
	private static Arm arm;

	public static Arm GetArm() {
		if (arm == null) {
			arm = new Arm();
		}
		return arm;
	}
	
	private Arm(){
		//Initialize motors
		motorBase = new CANTalon(RobotMap.ARM_BASE_MOTOR);
		motorShoulder = new CANTalon(RobotMap.ARM_SHOULDER_MOTOR);
		motorElbow = new CANTalon(RobotMap.ARM_ELBOW_MOTOR);
	}

    public void initDefaultCommand() {
        //Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

