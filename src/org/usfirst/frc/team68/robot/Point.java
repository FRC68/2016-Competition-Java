package org.usfirst.frc.team68.robot;

import org.simpleframework.xml.*;

@Root
public class Point {
	@Element
	public double x, y, z;

	public String toString(){
		return "(x:" + x +" y:" + y + " z:" + z + ")";
	}
}
