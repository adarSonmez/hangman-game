import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) throws FileNotFoundException {
        createRulesFile();
        // Get a country randomly
        String[] data = getData();
        String country = data[(int) (Math.random() * data.length)];

        // Introduce the game
        String letter = JOptionPane.showInputDialog(
                null,
                "WELCOME TO THE HANGMAN GAME. \n" +
                        "This is a country. \n" +
                        "You can have up to " + (country.length() / 4 + 7) + " letters. \n" +
                        "Let's start by having a letter: "
        );

        char[] word = toArray(country);
        char[] hiddenLetters = hideLetters(word);
        ArrayList<String> letters = new ArrayList<>();

        // Determine how many letters can user takes
        int rights = (country.length() / 4 + 6);
        SearchForLetter(letter, word, hiddenLetters);

        // Game Process
        for (int i = 0; i < (country.length() / 4 + 7); i++, rights--) {
            // Stack letters user entered
            if (letters.contains(letter)) {
                JOptionPane.showMessageDialog(
                        null,
                        "You already entered " + letter,
                        "Message",
                        JOptionPane.INFORMATION_MESSAGE
                );
                rights++;
                i--;
            } else {
                letters.add(letter);
            }

            // This is your last chance
            if (rights == 0) {
                SearchForLetter(letter, word, hiddenLetters);
            }

            // Enable user to guess the word
            if (letter.equals("0")) {
                String guess = JOptionPane.showInputDialog(
                        null,
                        "Write your guess for this country: \n" +
                                format(hiddenLetters),
                        "Guess",
                        JOptionPane.QUESTION_MESSAGE
                ).toLowerCase();
                checkTheGuess(guess, word);

                letter = "-1"; // If the guess is wrong
            } else {
                // Prompt user to enter a letter
                letter = enterALetter(hiddenLetters, rights, word);
                SearchForLetter(letter, word, hiddenLetters);
            }
        }
    }

    public static void createRulesFile() throws FileNotFoundException {
        File rules = new File("../Hangman/rules.txt");
        if (rules.exists()) {
            // when you use try-with source syntax, you don't have to write "output.close"
            try (PrintWriter output = new PrintWriter(rules)) {
                output.println("RULES!!");
                output.println("Welcome to the hangman game!");
                output.println("We will choose a country from our world and you will try to guess it by taking letters.");
                output.println("You can only have a certain number of letters.");
                output.println("As the number of words in the country increases, we will give you the right to take more letters");
                output.println("Be careful! You only have one chance to guess the word.");
                output.println("Now you are ready to play the game.");
                output.println("Have Fun!");
                output.println();
                output.print("https://github.com/adarSonmez/hangman-game");
                // output.close(); is redundant)
            }
        }
    }

    // Convert string country to an array
    public static char[] toArray(String country) {
        char[] letters = new char[country.length()];
        for (int i = 0; i < country.length(); i++) {
            if (country.charAt(i) == ' ') {
                // Convert spaces to dashes
                letters[i] = ' ';
                continue;
            }
            letters[i] = country.charAt(i);
        }
        return letters;
    }

    // Create a new char array, in which the letters are invisible
    public static char[] hideLetters(char[] country) {
        char[] hidden = new char[country.length];
        System.arraycopy(country, 0, hidden, 0, country.length);

        for (int i = 0; i < hidden.length; i++) {
            if (Character.isLetter(hidden[i])) {
                if (hidden[i] == ' ')
                    continue;
                hidden[i] = '*';
            }
        }
        return hidden;
    }

    // Make matching letters visible
    public static void SearchForLetter(String str, char[] word, char[] hiddenLetters) {
        char letter = str.charAt(0);
        for (int i = 0; i < word.length; i++) {
            if (word[i] == letter) {
                hiddenLetters[i] = letter;
            }
        }
    }

    // Prompt user to enter a letter
    public static String enterALetter(char[] wordArr, int rights, char[] word) {
        StringBuilder wordStr = format(wordArr);

        if (rights == 1) {
            return  JOptionPane.showInputDialog(
                    null,
                    "You can have one more letter for " + wordStr +
                            "\n or enter 0 to guess" ,
                    "Input",
                    JOptionPane.QUESTION_MESSAGE
            );
        } else if (rights != 0) {
            return  JOptionPane.showInputDialog(
                    null,
                    "Enter a latter in word " + wordStr +
                            "\n or enter 0 to guess" ,
                    "Input",
                    JOptionPane.QUESTION_MESSAGE
            );
        } else {
            // The last chance to win the game
            String guess = JOptionPane.showInputDialog(
                    null,
                    wordStr +
                            "Write your guess for this country ",
                    "Guess",
                    JOptionPane.QUESTION_MESSAGE
            ).toLowerCase();
            checkTheGuess(guess, word);
        }
        return "";
    }

    public static StringBuilder format(char[] wordArr) {
        StringBuilder wordStr = new StringBuilder();

        // Convert array country to a string
        for (char e : wordArr) {
            wordStr.append(e);
        }
        return wordStr;
    }

    // Check user's guess
    public static void checkTheGuess(String guess, char[] answer) {
        // String to char array
        char[] guessArr = new char[guess.length()];

        // Copy character by character into array
        for (int i = 0; i < guess.length(); i++) {
            guessArr[i] = guess.charAt(i);
        }

        StringBuilder ansStrBuilder = new StringBuilder();
        for (char c : answer) {
            ansStrBuilder.append(c);
        }

        // Is user's guess correct?
        boolean win = true;
        for (int i = 0; i < answer.length; i++) {
            if (guessArr[i] != answer[i]) {
                win = false;
                break;
            }
        }

        String ansStr = ansStrBuilder.toString().toUpperCase();

        if (guessArr.length != answer.length) {
            JOptionPane.showMessageDialog(
                    null,
                    "Pay attention to the number of the letters! \n" +
                            "The correct answer was '" + ansStr + " " +
                            "'\nYou lose.",
                    "You Lose!",
                    JOptionPane.PLAIN_MESSAGE
            );
        } else if (win) {
            JOptionPane.showMessageDialog(
                    null,
                    "Congratulations! '" + ansStr + "' is the correct answer.! \n" +
                            "Are you Google Maps?",
                    "You Won!",
                    JOptionPane.PLAIN_MESSAGE
            );
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "HAHAHAHA! \n" +
                            "The correct answer was \n'" +
                            ansStr + " " +
                            "'You're a loser :D \n",
                    "You Lose!",
                    JOptionPane.PLAIN_MESSAGE
            );
        }
        System.exit(0);
    }

    // All countries (I hope..)
    public static String[] getData() throws FileNotFoundException {
        ArrayList<String> myList = new ArrayList<>();
        File file = new File("../Hangman/countries.txt");
        Scanner c = new Scanner(file);
        // data.useDelimiter();

        while (c.hasNext()) {
            myList.add(c.nextLine());
        }
        c.close();

        String[] data = new String[myList.size()];
        return myList.toArray(data);
    }
}
