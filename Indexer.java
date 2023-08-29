package ie.atu.sw;

/**
 *       <b>Indexer()</b> is a constructor that takes two arguments: parser and outputFile.
 *       The parser argument is an instance of the FileParser class, which is used to parse input file.
 *       The outputFile argument is a String that specifies the name or path of the output file where the index will be written.
 *       The Indexer class also has a HashMap field called index, which is initialized in the constructor, used to store the index that is generated.<br>
 *       Big O-Notation = O(1)
 *       @param parser
 *       @param outputFile
 */

import static java.lang.System.out;

/**
 *            The <b>Indexer()</b> class is responsible for generating an index of words from a text file.
 *            @author jonathangriffey
 *            @version 1.0
 *            @since 1.8
 *
 */

import java.util.HashMap;
import java.util.Map;

public class Indexer {
	private Map<String, WordDetail> index = null;
    private FileParser parser;

    private Writer writer;
    private WordDetail wordDetail;

    
    public Indexer(FileParser parser, String outputFile) {
        index = new HashMap<>();
        this.parser = parser;
        writer = new Writer(outputFile);
    }

    /**
     *       The <b>addToIndex()</b> method first trims the word of trailing white space and converts to lower case
     *       While also removing characters that are not letters or digits
     *       then checks if the parser object's checkGoogleWords method returns true for the modified word. If it does, the method returns without adding anything to the index.
     *       then checks if the modified word is not found in the dictionary, which is a list of words that is managed by the parser object. 
     *       If the word is not found, the method returns without adding anything to the index.
     *       if the word has not been encountered before, a new WordDetail object is created with the page number and the definition of the word from the dictionary. 
     *       The WordDetail object is then added to the index with the word as the key.
     *       If the word has already been encountered before, a new WordDetail object is created with the existing WordDetail object for the word from the index. 
     *       The new WordDetail object's add method is then called with the page number as an argument. 
     *       Finally, the updated WordDetail object is added back to the index with the word as the key.<br>
     *       Big O-Notation O(1)
     *       @param word
     *       @param page
     */
    public void addToIndex(String word, int page) {
        String trimmed = word.trim().toLowerCase().replaceAll("[^A-Za-z0-9]", "");
        if (parser.checkGoogleWords(trimmed) || (parser.searchDictionary(trimmed) == null)) return;

        if (!index.containsKey(word)) {
            wordDetail = new WordDetail(page, parser.searchDictionary(trimmed));
            index.put(word, wordDetail);
        }
        wordDetail = new WordDetail(index.get(word));
        wordDetail.add(page);
        index.put(word, wordDetail);

    }

    /**
     *      <b>getIndex()</b> method gets the index of word with wordDetail
     *      @return index
     */
    public Map<String, WordDetail> getIndex() {
        return index;
    }

    /**
     *      <b>printIndex()</b> method iterates through the key-value pairs in the index HashMap and prints them to an output stream<br>
     *      Big O-Notation = O(n)
     */
    public void printIndex() {
        for (Map.Entry<String, WordDetail> entry : index.entrySet()) {
            out.println("index "+entry.getKey() + ": " + entry.getValue());
            out.println();
        }
        

    }
}

