package unalcol.types.real.matrix;

import unalcol.io.*;
import unalcol.services.MicroService;

import java.io.*;



/**
 * <p>Title: DoubleMatrixSimplePersistent </p>
 * <p>Description: A double matrix persistent method that uses a given charater for separating the matrix values</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: Kunsamu</p>
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */

public class DoubleMatrixPlainWrite extends MicroService<double[][]> implements Write<double[][]> {
    /**
     * Character used for separating the values in the array
     */
    protected char separator = ' ';

    /**
     * Creates an integer array persistent method that uses an space for separatng the array values
     */
    public DoubleMatrixPlainWrite() {}

    /**
     * Creates a double matrix persistent method that uses the give charater for separating the matrix values
     * @param separator Character used for separating the matrix values
     */
    public DoubleMatrixPlainWrite(char separator) {
        this.separator = separator;
    }

    /**
     * Writes an array to the given writer (writes the size and the values) using the associated separator char
     * @param obj array to write
     * @param out The writer object
     * @throws IOException IOException
     */
    public void write( Writer out) throws IOException {
	double[][] obj = caller();
        int n = obj.length;
        int m = (n>0)?obj[0].length:0;
        out.write(n);
        out.write(separator);
        out.write(m);
        for (int i = 0; i < n; i++) {
            out.write('\n');
            for (int j = 0; j < m; j++) {
                out.write(separator);
                out.write("" + obj[i][j]);
            }
        }
    }
}
