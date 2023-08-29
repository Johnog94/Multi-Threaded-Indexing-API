package ie.atu.sw;

/**
 * 
 *      @author jonathangriffey
 *      @version 1.0
 *      @since 1.8
 *
 */

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;



public class Writer {
	 private String outputFile = null;

	     /**
	     * 
	     * @param outputFile
	     */
	    public Writer(String outputFile) {
	        this.outputFile = outputFile;
	    }

	    /**
	     *     <b>save()</b> method creates a FileWriter object and iterates over the key-value pairs in the map argument. 
	     *     For each key-value pair, it writes the key and the value, separated by a comma, to the file specified by fileName. 
	     *     Finally, it closes the FileWriter object
	     *     @param map
	     *     @param key
	     */
	    public static void save(HashMap<String, WordDetail> map, String outputFile) {
	        try {
	            // create a FileWriter object
	            FileWriter writer = new FileWriter(outputFile);
	            // iterate over the map and write each key-value pair to the file
	            for (Map.Entry<String, WordDetail> entry : map.entrySet()) {
	                writer.write(entry.getKey() + "," + entry.getValue() + "\n");
	            }
	            // close the writer
	            writer.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    public static HashMap<String, WordDetail> cleanIndex() {
	        return null;
	    }
}

