package ie.atu.sw;

/**
 *         The <b>WordDetail</b> is a data structure that stores information about a word that is encountered in a text file
 *         @author jonathangriffey
 *         @version 1.0
 *         @since 1.8
 *
 */

import java.util.Collection;
import java.util.TreeSet;



public class WordDetail {
	private String definition;
    private Collection<Integer> pageNumber;
    private StringBuffer sb;

    /**
     *     <b>WordDetail()</b> constructor which creates a new WordDetail object and initialize its 
     *     fields, definition and pageNumber, with the values from the wordDetail argument.
     *     @param wordDetail
     */
    public WordDetail(WordDetail wordDetail) {
        this.definition = wordDetail.definition;
        this.pageNumber = wordDetail.pageNumber;
    }

    /**
     *     another constructor that takes two arguments: an int called page and a String called definition.
     *     It initializes a new TreeSet of page numbers called pageNumber.
     *     It adds the page argument to the pageNumber TreeSet.
     *     It initializes the definition field of the WordDetail object with the definition argument.
     *     @param page
     *     @param definition
     */
    public WordDetail(int page, String definition) {
        pageNumber = new TreeSet<>();
        this.pageNumber.add(page);
        this.definition = definition;
    }

    /**
     *     <b>add()</b> method of the WordDetail class adds a page number to the pageNumber TreeSet field of the WordDetail object.
     *     The method takes an int called page as its argument and adds it to the pageNumber TreeSet.
     *     It is used to update the WordDetail object with the page number of a page where the word associated with the WordDetail object appears
     *     @param page
     */
    public void add(int page){
        this.pageNumber.add(page);
    }

    /**
     *     <b>toString()</b> method is used to create a string representation of the WordDetail object 
     *     that includes the definition of the word and the page numbers where the word appears. String buffer is thread safe<br>
     *     Big O-Notation = O(1)
     */
    public String toString() {
        sb = new StringBuffer();
        sb.append(this.definition+"\r\n"+"Pages: "+this.pageNumber);
        return sb.toString();
    }
}
