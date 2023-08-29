package ie.atu.sw;

/**
 *         @author jonathangriffey
 *         @version 1.0
 *         @since 1.8
 *         
 *         @The <b>Menu</b> Class prints a <i>GUI Menu</i> of 6 options for the user.
 *         <li>Option 1 allows the user to specify a Text file to be parsed.</li>
 *         <li>Option 2 allows the user to specify the Dictionary file to be parsed.</li>
 *         <li>Option 3 allows the user to specify a google common words file to be parsed.</li>
 *         <li>Option 4 allows the user to specify where to output the index file.</li>
 *         <li>Option 5 allows the user to execute the application and parse all file specified.</li>
 *         <li>Option 6 gives the user the option to terminate and exit the application</li>
 **/

import static java.lang.System.out;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;



public class Menu {
    private Scanner scanner;
    private FileParser parser;
    private String textFilePath = null;
    private Map<String, String> dictionary = new HashMap<>();
    private String outputFile = null;
    private Parser textParser;
    //private String searchWord = null;
    /**
     *      The <b>Menu()</b> constructor calls menu class and creates it. 
     *      it allows for a new scanner instance to take user input
     *      and calls FileParser to parse user specified files
     */
    public Menu() {
        scanner = new Scanner(System.in);
        parser = new FileParser();
    }

    /**
     *       The <b>startMenu()</b> method is called by runner when application starts
     *       startMenu() presents a menu of options to the user via the MainMenu method. 
     *       The user can then select an option by entering an integer, and the corresponding case in the switch statement will be executed. 
     *       If the user enters 1, the processFile method will be called with the argument "textFile". parses user specified text file and informs users of completion<br>
     *       If the user enters 2, the processFile method will be called with the argument "dictionary". parses user specified dictionary.csv and informs users of completion<br>
     *       if the user enters 3, the processFile method will be called with the argument "googleCommonWords". parses user specified google-1000.txt and informs users of completion<br>
     *       If the user enters 4, the processFile method will be called with the argument "outputFile". allows user to select file path for index print<br>
     *       If the user enters 5, the execute method will be called.<br>
     *       If the user enters 6, the program will exit.
     *       @throws InterruptedException -
     *       @throws IOException -
     */
    public void startMainMenu() throws InterruptedException, IOException {
        while(true) {
            MainMenu();
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
            case 1 -> {
                processFile("textFile");
                out.println("Processing of sample textFile complete.");
            }
            case 2 -> {
            	processFile("dictionary");
            	out.println("Processing of Dictionary.csv complete.");
            }
            case 3 -> {
            	processFile("googleCommonWords");
            	out.println("Processing of google-1000.txt complete.");
            }
            case 4 -> {
            	processFile("outputFile");
            	out.println("Output file selected.");
            }
            case 5 -> {
            	execute();
            }
            case 6 -> {
            	System.exit(0);
            }
            }
        }
    }

    /**
     *      <b>processFile()</b> propmts user to specify the file path for the specified file type. 
     *      It then uses a switch statement to determine what to do with the file based on the value of type. 
     *      If type is "textFile", the file path is stored in the instance variable textFilePath. 
     *      If type is "dictionary", the parseFile method of the parser object is called with the file path as an argument. 
     *      If type is "googleCommonWords", the parseGoogleWords method of the parser object is called with the file path as an argument. 
     *      If type is "outputFile", the file path is stored in the instance variable outputFile.<br>
     *      Big O-Notation = O(1)
     *      @param type File filePath
     *      @throws IOException
     */
    private void processFile(String type) throws IOException {
        out.print("Specify "+ type +" path:");
        String filePath = scanner.nextLine();
        switch (type) {
            case "textFile" -> this.textFilePath = filePath;
            case "dictionary" -> parser.parseFile(filePath);
            case "googleCommonWords" -> parser.parseStopWords(filePath);
            case "outputFile" -> this.outputFile = filePath;
        }
    }

    /**
     *      The <b>execute()</b> method processes the file specified in textFilePath
     *      it creates a new TextParser object, passing in the parser object and the value of the outputFile instance variable as arguments. 
     *      It then calls the parseFile method on the textParser object, passing in the value of the textFilePath instance variable as an argument. 
     *      This will cause the parseFile method of the textParser object to be called, which will likely parse the contents of the file specified by textFilePath
     *      @throws IOException - 
     */
    private void execute() throws IOException {
        textParser = new TextParser(parser, outputFile);
        textParser.parseFile(textFilePath);
    }

    /**
     *      <b>MainMenu()</b> is used as a GUI for user
     *      It does not take any arguments and does not return a value. 
     *      It simply displays the menu to the user. 
     *      When the user makes a selection, the corresponding option will be executed based on the code in the startMenu method
     */
    public static void MainMenu() {
        out.println(ConsoleColour.GREEN);
        out.println("************************************************************");
        out.println("*       ATU - Dept. Computer Science & Applied Physics     *");
        out.println("*                                                          *");
        out.println("*              Virtual Threaded Text Indexer               *");
        out.println("*                                                          *");
        out.println("************************************************************");
        out.println("(1) Specify Text File");
        out.println("(2) Configure Dictionary");
        out.println("(3) Configure Common Words");
        out.println("(4) Specify Output File");
        out.println("(5) Execute");
        out.println("(6) Quit");
        out.print(ConsoleColour.BLACK_BOLD_BRIGHT);
        out.print("Select Option [1-4]>");
        out.println();
    }

    /**
     *     <b>startLoadingBar()</b> displays a loading bar in the console by printing a series of characters to the console.
     *     The method takes no arguments and declares that it throws an InterruptedException,
     *     which means that it may throw an exception if the thread is interrupted while it is sleeping<br>
     *     Big O-Notation = O(n)
     *     @throws InterruptedException - 
     */
    public static void startLoadingBar() throws InterruptedException {
        System.out.print(ConsoleColour.YELLOW);
        int size = 100;
        for (int i =0 ; i < size ; i++) {
            printProgress(i + 1, size);
            Thread.sleep(10);
        }
    }

    /**
     *     <b>printProgress()</b> is used to determine progress of loading bar for application when executed<br>
     *     Big O-Notation = O(n)
     *     @param index index
     *     @param total total
     */
    public static void printProgress(int index, int total) {
        if (index > total) return;
        int size = 50;
        char done = '█';
        char todo = '░';

        int complete = (100 * index) / total;
        int completeLen = size * complete / 100;

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append((i < completeLen) ? done : todo);
        }

        System.out.print("\r" + sb + "] " + complete + "%");

        if (done == total) out.println("\n");
    }



}
