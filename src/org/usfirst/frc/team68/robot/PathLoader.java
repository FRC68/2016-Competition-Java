package org.usfirst.frc.team68.robot;

import java.io.File;

import org.simpleframework.xml.*;
import org.simpleframework.xml.core.Persister;

public class PathLoader {
	public static PointPath loadPath(String filename){

        Serializer serializer = new Persister();
        
        try{
        	File input = new File(filename);
        	PointPath retval = serializer.read(PointPath.class, input);
        	return retval;
        }catch(Exception e){
        	System.out.println("ERROR! PathLoader.loadPath failed to load path from" + filename);
        	System.out.println("Please check to ensure that the  file exists and contains a vaild path in XML format\nStackTrace:");
        	e.printStackTrace();
        	return null;
        }
        
        
	}
}
