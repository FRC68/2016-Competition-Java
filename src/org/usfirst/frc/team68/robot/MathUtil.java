package org.usfirst.frc.team68.robot;

public class MathUtil {
	public static boolean withinThresh(double value, double target, double thresh){
		if(Math.abs(value-target) > thresh)
			return false;
		return true;
	}
	public static boolean withinThresh(int value, int target, int thresh){
		if(Math.abs(value-target) > thresh)
			return false;
		return true;
	}
	
	public static boolean withinRange(double high, double low, double value){
		if(value < high && value > low)
			return true;
		return false;		
	}
	public static boolean withinRange(int high, int low, int value){
		if(value < high && value > low)
			return true;
		return false;		
	}

}
