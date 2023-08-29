package ie.atu.sw;

/**
 * The class <b>Runner</b> implements main method which starts the application
 * @author jonathangriffey
 * @version 1.0
 * @since 1.8
 *
 */

import java.util.HashMap;

public class Runner {
	
	/**
	 * Starts Application by creating Menu object and calling <b>MainMenu()</b>
	 * @param args string
	 * @throws Exception - 
	 */
	public static void main(String[] args) throws Exception {
		HashMap<String, String> map = new HashMap<>();
		Menu m = new Menu();
		m.startMainMenu();
	}

}
