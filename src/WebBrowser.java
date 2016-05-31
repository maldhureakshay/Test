

import java.io.File;
import java.util.*;

/* WebBrowser Simulator */
public class WebBrowser {
	Stack<String> backward = new Stack<String>(); // Backward pages
	Stack<String> forward = new Stack<String>(); // Forward pages

	public WebBrowser(Scanner sc) {
		while (sc.hasNextLine()) {
			String input = sc.nextLine();
			System.out.println(input);
			String temp ="";
			if(input.startsWith("next")){	
				temp = "FORWARD";
			}
			if(input.startsWith("back")){
				temp = "BACKWARD";
			}
			
			input = input.replaceFirst("goto ", "");
			switch (temp) {
			case "BACKWARD":
				if (!backward.empty())
					forward.push(backward.pop()); // Remove from backward,
													// add to forward
				break;

			case "FORWARD":
				if (!forward.empty())
					backward.push(forward.pop()); // Remove from forward,
													// add to backward
				break;

			default:
				backward.push(input); // New page opened
				while (!forward.empty())
					// Clear forward stack
					forward.pop();
				break;
			}
			if (backward.empty() && !forward.empty()) // Add forward pages
														// to history
				backward.push(forward.pop());
		}

		if (backward.empty() && forward.empty())
			System.out.println("Browsing history is empty.");
		else {
			// If there is only 1 page, current page in forward stack else
			// in backward
			String current = (backward.empty()) ? forward.peek() : backward
					.peek();

			System.out.println("\n\nBrowsing History:");

			while (!forward.empty())
				// In order to print from least to most recent
				backward.push(forward.pop());

			while (!backward.empty())
				forward.push(backward.pop());

			while (!forward.empty())
				System.out.println(forward.pop());

			System.out.println("Current Page:");
			System.out.println(current);
		}

	}

	public static void main(String[] args) {
		try {
			File input = new File("src/history.txt");
			Scanner sc = new Scanner(input);
			WebBrowser bc = new WebBrowser(sc);
		} catch (Exception e) {

		}

	}
}
