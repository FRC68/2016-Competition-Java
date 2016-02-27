package org.usfirst.frc.team68.robot;

import org.simpleframework.xml.*;

import java.awt.PointerInfo;
import java.util.ArrayList;
import java.util.List;

@Root
public class PointPath {
	@ElementList
	private List<Point> points = new ArrayList<Point>();
	
	public Point getPointAtPercent(double percent){
		int index = (int) Math.round(((double)(points.size()-1)/100)*percent);
		if(index > points.size())
			index = points.size();
		if(index < 0)
			index = 0;
		
		return points.get(index);
	}
	
	public void addPoint(int x, int y, int z){
		Point ins = new Point();
		ins.x = x; ins.y = y; ins.z = z;
		points.add(ins);
	}
	
	public void addPoint(int index, int x, int y, int z){
		Point ins = new Point();
		ins.x = x; ins.y = y; ins.z = z;
		points.add(index, ins);
	}
	
	public void removePoint(int index){
		points.remove(index);
	}
	
	public String toString(){
		StringBuilder retval = new StringBuilder();
		
		for(int i = 0; i < points.size()-1; i++){
			retval.append(points.get(i).toString());
			retval.append("\n");
		}
		return retval.toString();
	}
	
}
