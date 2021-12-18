import codekata.AnagramByWordlistLookup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Application {
    // information that is displayed to the user upon program start
    private static final String HEADLINE = "########## Codekata: Anagram 101 ##########".toUpperCase();
    private static final String WELCOMEMESSAGE = "Welcome!\n";
    private static final String ABOUTANAGRAM = "This program tells you to type in any word. Please type in any word then. The application validates your input, searches for anagrams and prints any results to the console.";
    private static final String ABOUTVALIDINPUT = "Your input counts as valid if the following requirements are met: Your input consists of lowercase or uppercase letters and is at least two characters long. Any other input will be rejected.\n";
    // messages that respond to different states of a program run
    private static final String INPUTPROMPT = "Please type in any word: ";
    private static final String INVALIDINPUT = "Warning: You entered a single character or non-alphabetical characters. Please try again.";

    public static void main( String[] args ) {
        showInstructionsToUser();
        String userInput = getUserInput();
        AnagramByWordlistLookup anagramByWordlistLookup = new AnagramByWordlistLookup(userInput);
        long start = System.currentTimeMillis();
        long executionTime;
        List<String> anagrams = new ArrayList<>();
        try {
            anagrams = anagramByWordlistLookup.findAnagrams( anagramByWordlistLookup.getOrigin() );
        } catch (IOException e) {
            System.out.println("Could not access wordlist.\n\nError message: " + e.getMessage());
            System.exit(-1);
        }

        if( anagrams.isEmpty() ) {
            System.out.println("There are no anagrams for " + anagramByWordlistLookup.getOrigin() + ".");
        } else {
            System.out.println("Anagrams of " + anagramByWordlistLookup.getOrigin() + ": " + anagrams.toString().replace("[", "").replace("]", ""));
        }
        executionTime = executionTime(start, System.currentTimeMillis());
        System.out.println("This program ran for " + executionTime + "ms.");
    }

    public static void showInstructionsToUser() {
        System.out.println( HEADLINE );
        System.out.println( WELCOMEMESSAGE );
        System.out.println( ABOUTANAGRAM );
        System.out.println( ABOUTVALIDINPUT );
    }

    public static String getUserInput() {
        System.out.println( INPUTPROMPT );
        Scanner scanner = new Scanner( System.in );
        String userInput = scanner.nextLine();
        if ( isValid( userInput ) ) {
            scanner.close();
            return userInput;
        }
        System.out.println( INVALIDINPUT );
        return getUserInput();
    }

    public static boolean isValid( final String userInput ) {
        return Pattern.compile( "^[A-Za-z]+$" ).matcher( userInput ).find() && userInput.length() >= 2;
    }

    private static long executionTime( final long start, final long end ) {

        return end - start;
    }
}
