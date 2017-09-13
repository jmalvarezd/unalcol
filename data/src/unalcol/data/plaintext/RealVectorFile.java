/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.data.plaintext;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import unalcol.io.ShortTermMemoryReader;
import unalcol.types.collection.vector.Vector;
import unalcol.types.real.array.DoubleArrayPlainRead;

/**
 *
 * @author jgomez
 */
public class RealVectorFile {
	public static String[] headers(String fileName, char separator) throws IOException{
        BufferedReader file = new BufferedReader(new FileReader(fileName));
        String line = file.readLine();
        file.close();
        String[] dimensions = null;
        if( line != null ){
            dimensions = line.split(""+separator);
            try{
            	for( int i=0; i<dimensions.length; i++ ){
            		Double.parseDouble(dimensions[i]);
            	}
            	dimensions = null;
            }catch(NumberFormatException e){}
        }
        return dimensions;
	}
	
    public static Vector<double[]> load(String fileName, char separator) throws IOException{
    	String[] dimensions = headers(fileName, separator);
        BufferedReader file = new BufferedReader(new FileReader(fileName));
        String line = file.readLine();
        if( dimensions != null ){
        	line = file.readLine();
        }
        DoubleArrayPlainRead service = new DoubleArrayPlainRead(separator);
        Vector<double[]> data_points = new Vector<double[]>();
        StringReader r;
        ShortTermMemoryReader reader;
        while(line!=null){
            r = new StringReader( line );
            reader = new ShortTermMemoryReader(r);
            data_points.add(service.read(reader));
            line = file.readLine();
        }
        file.close();
        return data_points;
    }    
}
