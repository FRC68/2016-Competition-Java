
package org.usfirst.frc.team68.robot.subsystems;

import org.usfirst.frc.team68.robot.RobotMap;
import org.usfirst.frc.team68.robot.commands.GripReportBestTarget;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.DrawMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ShapeMode;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.AxisCamera;


/**
 *
 */
public class GripInterface extends Subsystem {
	private static GripInterface grip;
	private static NetworkTable nTable;
	private static double lastX = RobotMap.SCREENWIDTH/2;
	private static double lastY = RobotMap.SCREENHEIGHT/2; 
	private int mismatches = 0;
	
	
	public static GripInterface getGrip() {
		if ( grip == null) {
			grip = new GripInterface();
		}
		return grip;
	}
	
	private GripInterface(){
        nTable = NetworkTable.getTable("GRIP/myContoursReport");
	}
	
	public void initDefaultCommand(){
		setDefaultCommand(new GripReportBestTarget());
	}
    
	private double[] getXs(){
		return nTable.getNumberArray("centerX", (double[])null);	
	}
	private double[] getYs(){
		return nTable.getNumberArray("centerY", (double[])null);	
	}
	
	private double calcDist(double ax, double ay, double bx, double by){
		return Math.sqrt(Math.pow((ax-bx), 2) + Math.pow((ay-by), 2));
	}
	
	public double[] getBestTarget(){
		double[] retval = {0, 0};
		double[] Xs = this.getXs();
		double[] Ys = this.getYs();
		
		try{
		int indexLow = 0;
		double distLow = Double.MAX_VALUE;
		
		

		
		if((Xs.length == 0) || (Ys.length == 0)){
			retval[0] = lastX;
			retval[1] = lastY;

		}else{
			retval[0] = Xs[0];
			retval[1] = Ys[0];
		}

		}catch(Exception e){
			
		}
		
		return retval;
	}
	
	public static double getXerror(double[] box, double xTar){
		if(box.length != 2)
			return 0;
		
		return box[0] - xTar;
	}
    

}



