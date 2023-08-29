package ie.atu.sw;

/**
 *         @author jonathangriffey
 *         @version 1.0
 *         @since 1.8
 *         The <b>FileParser</b> Class implements the <i>Interface</i> Parser
 *         its responsible for parsing a text file and generating a dictionary of words and their definitions. 
 *         It also has a method to parse a file of common words and check if a given word is in that list
 **/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

public class FileParser implements Parser{
	private ConcurrentSkipListSet<String> googleWords = null;
    private ConcurrentHashMap<String, String> dictionary = null;
    private StringBuffer tempDefinition;
	
    /**
	 *     The public method <b>parseFile()</b> takes a file path, reads the lines of the file concurrently
	 *     and starts a virtual thread for each line to process it.<br>
	 *     Big O-Notation = O(n)
	 */
	public void parseFile(String filePath) throws IOException {
		dictionary = new ConcurrentHashMap<>();
        Files.lines(Path.of(filePath))
            .forEach(line -> Thread.startVirtualThread(() -> processLine(line))
            );
	}

	/**
	 *    processLine() adds line to 
	 */
	public void processLine(String line) {
		add(line);
	}

	/**
	 *    <b>add()</b> method takes a String input called line and split it into two parts using the split method, 
	 *    which separates the line string into an array of substrings based on a delimiter
	 *    <br>
	 *    first element of the tempArray array is  processed by calling the toLowerCase, trim, and replaceAll methods on it. 
	 *    The toLowerCase method converts the string to lowercase, the trim method removes leading and trailing white space, 
	 *    and the replaceAll method removes any characters that are not letters or numbers. The resulting string is stored in a variable called word.
	 *    <br>
	 *    The second element of the tempArray array is stored in a variable called def. 
	 *    <br>
	 *    Finally, the dictionary object is updated by adding a new key-value pair, 
	 *    where the key is the word string and the value is the def string<br>
	 *    Big O-Notation = O(1)
	 *    
	 */
	public void add(String line) {
		String[] tempArray = line.split(",");
        String word = tempArray[0].toLowerCase().trim().replaceAll("[^a-zA-Z0-9]", "");
        String def = tempArray[1];
        dictionary.put(word, def);
	}

	/**
	 *    The <b>searchDictionary()</b> method uses a string buffer object called tempDefinition
	 *    to check the Dictionary map to see if it contains a key equal to word argument
	 *    returns null if no match.<br>
	 *    Big O-Notation = O(1)
	 *    @param word
	 *    @return Definition
	 */
    public String searchDictionary(String word) {
        tempDefinition = new StringBuffer();
        if (dictionary.containsKey(word)){
            return tempDefinition.append(word+", "+dictionary.get(word)).toString();
        }   return null;
    }

    /**
     *    The <b>getDictionary()</b> method is a public method that returns the dictionary ConcurrentHashMap object
     *    @return Dictionary index
     */
    public ConcurrentHashMap<String, String> getDictionary() {
        return dictionary;
    }
    
    /**
     *    <b>parseGoogleWords()</b> method parses the google-1000 text file for common words
     *    it reads the lines of the file specified by filePath, and iterates over each line using the forEach method. For each line, 
     *    the method creates a new virtual thread using the startVirtualThread method and adds the line to the googleWords set using the add method<br>
     *    Big O-Notation = O(n)
     *    @param filePath google-1000.txt
     *    @throws IOException
     */
    public void parseStopWords(String filePath) throws IOException {
        googleWords = new ConcurrentSkipListSet<>();
        Files.lines(Path.of(filePath))
                .forEach(line -> Thread.startVirtualThread(() -> googleWords.add(line))
                );
    }

    /**
     *    <b>checkGoogleWords()</b> is used to check google-1000txt for common words within index
     *    takes a String input called word and returns a boolean value indicating whether the googleWords set contains the word string.
     *    checkGoogleWords method returns true if the googleWords set contains the word string, and false if it does not.
     *    @param word
     *    @return
     */
    public boolean checkGoogleWords(String word) {
        return googleWords.contains(word);
    }

}

