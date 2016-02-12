
package org.usfirst.frc.team68.robot.subsystems;

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
		motorBase = new CANTalon(RobotMap.motorBase);
		motorShoulder = new CANTalon(RobotMap.motorShoulder);
		motorElbow = new CANTalon(RobotMap.motorElbow);
	}

    public void initDefaultCommand() {
        //Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

