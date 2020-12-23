import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        // Get a country randomly
        String[] data = getData();
        String country = data[(int)(Math.random() * data.length)];
        Scanner input = new Scanner(System.in);

        char[] word = toArray(country);
        char[] hiddenLetters =  hideLetters(word);

        // Introduce the game
        System.out.println("WELCOME TO THE HANGMAN GAME.");
        System.out.println("This is a country.");
        System.out.println("You can have up to " + (country.length() / 4 + 7) + " letters.");
        System.out.println("Be careful! You only have one chance to guess the word.");
        System.out.print("let's start by having a letter: ");

        // Determine how many letters can user takes
        int rights = (country.length() / 4 + 6);
        String letter = input.next();
        SearchForLetter(letter, word, hiddenLetters);

        // Game Process
        for (int i = 0; i < (country.length() / 4 + 7); i++, rights--) {
            if (rights == 0) {
                SearchForLetter(letter, word, hiddenLetters);
                System.out.println(hiddenLetters);
            }

            // Enable user to guess the word
            if (letter.equals("0")) {
                System.out.print("Write your guess for this country: ");
                String guess = input.next();
                checkTheGuess(guess, word);

                letter = "-1"; // If the guess is wrong
            } else {
                // Prompt user to enter a letter
                enterALetter(hiddenLetters, rights, word);
                letter = input.next();
                SearchForLetter(letter, word, hiddenLetters);
            }
        }
    }

    // Convert string country to an array
    public static char[] toArray(String country) {
        char[] letters = new char[country.length()];
        for (int i = 0; i < country.length(); i++) {
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
    public static void enterALetter(char[] wordArr, int rights, char[] word) {
        StringBuilder wordStr = new StringBuilder();
        Scanner input = new Scanner(System.in);

        // Convert array country to a string
        for (char e : wordArr) {
            wordStr.append(e);
        }

        if (rights == 1) {
            System.out.print("You can have one more letter for " + wordStr + " or enter 0 to guess > ");
        } else if (rights != 0) {
            System.out.print("Enter a latter in word " + wordStr + " or enter 0 to guess > ");
        } else {
            // The last chance to win the game
            System.out.print("Write your guess for country above: ");
            String guess = input.next();
            checkTheGuess(guess, word);
        }
    }

    // Check user's guess
    public static void checkTheGuess(String guess, char[] answer) {
        // String to char array
        char[] guessArr = new char[guess.length()];

        // Copy character by character into array
        for (int i = 0; i < guess.length(); i++) {
            guessArr[i] = guess.charAt(i);
        }

        StringBuilder ansStr = new StringBuilder();
        for (char c : answer) {
            ansStr.append(c);
        }

        // Is user's guess correct?
        boolean win = true;
        for (int i = 0; i < guessArr.length; i++) {
            if (guessArr[i] != answer[i]) {
                win = false;
                break;
            }
        }

        if (win) {
            System.out.println("Congratulations! " + ansStr + " is the correct answer.");
            System.out.println("Are you Google Maps?");
        } else {
            System.out.println("HAHAHAHA!");
            System.out.println("The correct answer was " + ansStr);
            System.out.println("You're a loser :D");
        }
        System.exit(0);
    }

    // All countries (I hope..)
    public static String[] getData() {
        return new String[]{
                "afghanistan",
                "albania",
                "algeria",
                "american samoa",
                "andorra",
                "angola",
                "anguilla",
                "antarctica",
                "antigua and barbuda",
                "argentina",
                "armenia",
                "aruba",
                "australia",
                "austria",
                "azerbaijan",
                "bahamas",
                "bahrain",
                "bangladesh",
                "barbados",
                "belarus",
                "belgium",
                "belize",
                "benin",
                "bermuda",
                "bhutan",
                "bolivia",
                "bosnia and herzegovina",
                "botswana",
                "bouvet island",
                "brazil",
                "british indian ocean territory",
                "brunei darussalam",
                "bulgaria",
                "burkina faso",
                "burundi",
                "cambodia",
                "cameroon",
                "canada",
                "cape verde",
                "cayman islands",
                "central african republic",
                "chad",
                "chile",
                "china",
                "christmas island",
                "cocos islands",
                "colombia",
                "comoros",
                "congo",
                "the democratic republic of the congo",
                "cook islands",
                "costa rica",
                "cote d'Ivoire",
                "croatia",
                "cuba",
                "cyprus",
                "czech republic",
                "denmark",
                "djibouti",
                "dominica",
                "dominican republic",
                "east timor",
                "ecuador",
                "egypt",
                "el salvador",
                "equatorial guinea",
                "eritrea",
                "estonia",
                "ethiopia",
                "falkland islands",
                "faroe islands",
                "fiji",
                "finland",
                "france",
                "france metropolitan",
                "french guiana",
                "french polynesia",
                "french southern territories",
                "gabon",
                "gambia",
                "georgia",
                "germany",
                "ghana",
                "gibraltar",
                "greece",
                "greenland",
                "grenada",
                "guadeloupe",
                "guam",
                "guatemala",
                "guinea",
                "guinea-bissau",
                "guyana",
                "haiti",
                "heard and mc donald islands",
                "holy see",
                "honduras",
                "hong kong",
                "hungary",
                "iceland",
                "india",
                "indonesia",
                "iran",
                "iraq",
                "ireland",
                "israel",
                "italy",
                "jamaica",
                "japan",
                "jordan",
                "kazakhstan",
                "kenya",
                "kiribati",
                "democratic people's republic of korea",
                "republic of korea",
                "kuwait",
                "kyrgyzstan",
                "people's democratic republic lao",
                "latvia",
                "lebanon",
                "lesotho",
                "liberia",
                "libyan arab jamahiriya",
                "liechtenstein",
                "lithuania",
                "luxembourg",
                "macau",
                "the former yugoslav republic of macedonia",
                "madagascar",
                "malawi",
                "malaysia",
                "maldives",
                "mali",
                "malta",
                "marshall islands",
                "martinique",
                "mauritania",
                "mauritius",
                "mayotte",
                "mexico",
                "Federated states of micronesia",
                "republic of moldova",
                "monaco",
                "mongolia",
                "montserrat",
                "morocco",
                "mozambique",
                "myanmar",
                "namibia",
                "nauru",
                "nepal",
                "netherlands",
                "netherlands antilles",
                "new caledonia",
                "new zealand",
                "nicaragua",
                "niger",
                "nigeria",
                "niue",
                "norfolk island",
                "northern mariana islands",
                "norway",
                "oman",
                "pakistan",
                "palau",
                "panama",
                "papua new guinea",
                "paraguay",
                "peru",
                "philippines",
                "pitcairn",
                "poland",
                "portugal",
                "puerto rico",
                "qatar",
                "reunion",
                "romania",
                "russian federation",
                "rwanda",
                "saint kitts and nevis",
                "saint lucia",
                "saint vincent and the grenadines",
                "samoa",
                "san marino",
                "sao tome and principe",
                "saudi arabia",
                "senegal",
                "seychelles",
                "sierra leone",
                "singapore",
                "slovakia",
                "slovenia",
                "solomon islands",
                "somalia",
                "south africa",
                "south georgia and the south sandwich islands",
                "spain",
                "sri lanka",
                "st. helena",
                "st. pierre and miquelon",
                "state of palestine",
                "sudan",
                "suriname",
                "svalbard and jan mayen islands",
                "swaziland",
                "sweden",
                "switzerland",
                "syrian arab republic",
                "taiwan",
                "tajikistan",
                "united republic of tanzania",
                "thailand",
                "togo",
                "tokelau",
                "tonga",
                "trinidad and tobago",
                "tunisia",
                "turkey",
                "turkmenistan",
                "turks and caicos islands",
                "tuvalu",
                "uganda",
                "ukraine",
                "united arab emirates",
                "united kingdom",
                "united states",
                "united states minor outlying islands",
                "uruguay",
                "uzbekistan",
                "vanuatu",
                "venezuela",
                "vietnam",
                "british virgin islands",
                "virgin islands of the united states",
                "wallis and futuna islands",
                "western sahara",
                "yemen",
                "yugoslavia",
                "zambia",
                "zimbabwe"
        };
    }
}
