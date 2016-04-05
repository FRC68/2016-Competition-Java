package org.usfirst.frc.team68.robot;

public class MathUtil {
	public static boolean withinThresh(double value, double target, double thresh){
		return (Math.abs(value-target) < thresh);
	}

	public static boolean withinThresh(int value, int target, int thresh){
		return (Math.abs(value-target) < thresh);
	}
	
	public static boolean withinPercentThresh(double value, double target, double thresh){
    	double actualDiff = Math.abs(value - target);
    	double allowableDiff = Math.abs(target * (1 - thresh));
    	boolean isWithinThresh = false;
		if(actualDiff <= allowableDiff){
			isWithinThresh = true;
		}
		return isWithinThresh;
	}
	
	public static boolean withinPercentThresh(int value, int target, double thresh){
    	double actualDiff = Math.abs(value - target);
    	double allowableDiff = Math.abs(target * (1 - thresh));
    	boolean isWithinThresh = false;
		if(actualDiff <= allowableDiff){
			isWithinThresh = true;
		}
		return isWithinThresh;
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
	
	public static double degreesToRot(double degrees){
		return degrees/360;
	}
	public static double degreesToRot(int degrees){
		return ((double)degrees)/360;
	}
	
	public static double rotToDegrees(double rots){
		return rots*360;
	}
	
	public static int findClosestIndex(double[] array, double val){
		int retval = 0;
		long retscore = Long.MAX_VALUE;
		for(int i = 0; i < array.length; i++){
			if(Math.abs(array[i] - val) < retscore){
				retscore = Math.round(Math.abs(array[i] - val));
				retval = i;
			}
		}
		return retval;
	}

}
