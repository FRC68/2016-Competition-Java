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
        	e.printStackTrace();
        	return null;
        }
        
        
	}
}
