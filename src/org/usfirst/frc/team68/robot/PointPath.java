package org.usfirst.frc.team68.robot;

import org.simpleframework.xml.*;

import java.awt.PointerInfo;
import java.util.ArrayList;
import java.util.List;

@Root
public class PointPath {
	@Element
	private List<Point> points = new ArrayList<Point>();
	
	public Point getPointAtPercent(double percent){
		int index = (int) Math.round((points.size()/100)*percent) -1; //Minus one b/c array index starts at and size is the number of elements
		if(index > points.size())
			index = points.size();
		
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
