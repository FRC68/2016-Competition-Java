
package org.usfirst.frc.team68.robot.subsystems;

import org.usfirst.frc.team68.robot.RobotMap;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.DrawMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ShapeMode;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.vision.AxisCamera;


/**
 *
 */
public class USBCamera extends Subsystem {
	private static USBCamera usbCamera;
	private CameraServer server;

	public static USBCamera getUSBCamera() {
		if ( usbCamera == null) {
			usbCamera = new USBCamera();
		}
		return usbCamera;
	}
	
	private USBCamera(){
        server = CameraServer.getInstance();
        server.setQuality(50);
        //the camera name can be found through the roborio web interface
        server.startAutomaticCapture("cam0");
	}
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }	

}



