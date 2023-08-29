package ie.atu.sw;

/**
 *       @author jonathangriffey
 *       @version 1.0
 *       @since 1.8
 *       The <i>interface</i> Parser is an <b>abstraction</b>  
 *       It specifies what the implementing classes should do but not how
 *       It defines the method signatures for the methods in the <b>FileParser</b> and <b>TextParser</b> classes
 *       Both these classes implement these methods
 */

import java.io.IOException;




public interface Parser {
	/**
	 *   Interface <b>parseFile()</b> method that allows claases that implement it to parse certain file types
	 *   @param filePath file path for user specified files
	 *   @throws IOException throws IO exception
	 */
	public void parseFile(String filePath) throws IOException;
	/**
	 *   Interface <b>processLine()</b> method that allows claases that implement it to process lines of files
	 *   @param line 
	 */
    public void processLine(String line);
    /**
     *   Interface <b>add()</b> method to add input from parsing to HashMap data Structure
     *   @param input word
     */
    public void add(String input);
}
