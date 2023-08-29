package ie.atu.sw;

/**
 * 
 *         @author jonathangriffey
 *         @version 1.0
 *         @since 1.8
 *         The <b>TextParser</b> Class implements the <i>Interface</i> Parser
 *         TextParser class is used to parse the user specified file for book of choice
 *
 **/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;


public class TextParser implements Parser{
	private int page = 1;
    private int lineNumber = 0;
    private int wordCount = 0;
    private Indexer indexer;
	
    
    /**
     *      <b>TextParser()</b> is a public constructor for the TextParser class that takes a parser and an outputFile as arguments 
     *      and creates a new Indexer object with them".
     *      @param parser
     *      @param outputFile
     */
	public TextParser(FileParser parser, String outputFile) {
		indexer = new Indexer(parser, outputFile);
	}

	/**
	 *      <b>parseFile()</b> takes a file path, reads the lines of the file, processes each line, and then prints the results"<br>
	 *      Big O-Notation = O(n)
	 */
	@Override
	public void parseFile(String filePath) throws IOException {
		Files.lines(Path.of(filePath))
        .forEach(line -> processLine(line));
        print();
	}

	/**
	 *      <b>processLine()</b> checks if the value of lineNumber is divisible by 40 and if it is, it increments the value of page by 1.
	 *      It then splits the line into an array of words, creates stream of words and calls add() method
	 *      then increments line number by 1<br>
	 *      Big O-Notation = O(n)
	 */
	@Override
	public void processLine(String line) {
		if (lineNumber % 40 == 0) page++;
        Arrays.stream(line.split("\\s+")).forEach(w -> add(w));
        lineNumber++;
	}

	/**
	 *      the <b>add()</b> method takes a String input and adds it to Indexer. 
	 *      The page object is also passed to the addToIndex method.
	 */
	@Override
	public void add(String input) {
		indexer.addToIndex(input, page);
	}

	/**
	 *      <b>print()</b> method prints words to index
	 */
	public void print() {
        indexer.printIndex();
    }
	
}

