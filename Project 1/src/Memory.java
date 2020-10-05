/**
 * Alexander Pham
 * September 11, 2019
 *A card matching game that shuffles the deck and the user tries to match two cards together. Matching cards stay face up and unmatched cards, flip face back down.
 */
import java.util.Scanner;
public class Memory {

    public static void main( String args[]){
        while ( true ) {
            //The deck
            String[][] userDeck = {{"1 ", "A "}, {"2 ", "A "}, {"3 ", "B "}, {"4 ", "B "}, {"5 ", "C "}, {"6 ", "C "}, {"7 ", "D "}, {"8 ", "D "}, {"9 ", "E "}, {"10", "E "}, {"11", "F "}, {"12", "F "}, {"13", "G "}, {"14", "G "}, {"15", "H "}, {"16", "H "}};
            //The state of the cards
            Boolean[][] deckBoolean = {{false, true}, {false, true}, {false, true}, {false, true}, {false, true}, {false, true}, {false, true}, {false, true}, {false, true}, {false, true}, {false, true}, {false, true}, {false, true}, {false, true}, {false, true}, {false, true}};
            userDeck = shuffle( userDeck );
            while (true) {
                displayBoard(userDeck);
                //User picks the first card to flip
                int cardChoice1 = getChoice();
                while (true) {
                    boolean alreadyFlipped = checkFlipped(cardChoice1, deckBoolean);
                    if (alreadyFlipped == true) {
                        System.out.println("This card was already flipped");
                        cardChoice1 = getChoice();
                    } else {
                        break;
                    }
                }
                //Checks to see if card is flipped
                flipChoice(cardChoice1, userDeck, deckBoolean);
                //Display the board with the chosen card face up
                displayBoard(userDeck);
                //User picks the second card to flip
                int cardChoice2 = getChoice();
                while (true) {
                    boolean alreadyFlipped = checkFlipped(cardChoice2, deckBoolean);
                    if (alreadyFlipped == true) {
                        System.out.println( "This card was already flipped" );
                        cardChoice2 = getChoice();
                    } else {
                        break;
                    }
                }
                //Checks to see if the card is flipped
                flipChoice(cardChoice2, userDeck, deckBoolean);
                //Display the board with the chosen card face up
                displayBoard(userDeck);
                boolean aMatch = isMatch(userDeck, cardChoice1, cardChoice2);
                if (aMatch == false) {
                    //Flips cards back if they don't match
                    flipChoice(cardChoice1, userDeck, deckBoolean);
                    flipChoice(cardChoice2, userDeck, deckBoolean);
                    System.out.println("Not a match");
                } else {
                    System.out.println("A match");
                }
                //Keeps track of all cards that are flipped over
                int flipCounter = 0;
                for (int i = 0; i < deckBoolean.length; i++) {
                    if (deckBoolean[i][0] == true) {
                        flipCounter += 1;
                    }
                }
                //When all cards match, the game ends
                if (flipCounter == 16) {
                    System.out.println( "Congratulations, you won!" );
                    break;
                }
            }
            break;
        }
    }

    /**
     * Get the user choice of card to flip
     * @return the user's choice
     */
    public static int getChoice(){
        System.out.print( "Which card would you like to flip over? : " );
        int userChoice = CheckInput.getInt();
        while ( userChoice<1 || userChoice>16 ) {
            System.out.print("Invalid, try again: ");
            userChoice = CheckInput.getInt();
        }
        userChoice = userChoice - 1; //Turns the user's 1-based choice to 0-based
        return userChoice;
    }

    /**
     * Shuffles the deck
     * @param userDeck
     * @return the shuffled deck
     */
    public static String[][] shuffle( String[][] userDeck ){
        //Set lower and upper bound to 0-15 for the different indexes
        final int UPPERBOUND = 15;
        final int LOWERBOUND = 0;
        //Shuffles 100 times
        for ( int i = 1; i <= 100; i++ ){
            int row1 = (int) ((Math.random() * (UPPERBOUND - LOWERBOUND + 1 )) + LOWERBOUND);
            int row2 = (int) ((Math.random() * (UPPERBOUND - LOWERBOUND + 1 )) + LOWERBOUND);
            String valueHolder = userDeck[row1][1];
            userDeck[row1][1] = userDeck[row2][1];
            userDeck[row2][1] = valueHolder;
        }
        return userDeck;
    }

    /**
     * Flips the card that the user chooses
     * @param cardChoice
     * @param userDeck
     * @param deckBoolean
     */
    public static void flipChoice( int cardChoice , String[][] userDeck , Boolean[][] deckBoolean){
        //Flips card
        String[] flippedCard = userDeck[cardChoice];
        String cardNumber = flippedCard[0];
        String cardLetter = flippedCard[1];
        userDeck[cardChoice][0] = cardLetter;
        userDeck[cardChoice][1] = cardNumber;

        //Flips the 2D boolean array card
        Boolean[] flippedCardboolean = deckBoolean[cardChoice];
        Boolean cardFront = flippedCardboolean[0];
        Boolean cardBack = flippedCardboolean[1];
        deckBoolean[cardChoice][0] = cardBack;
        deckBoolean[cardChoice][1] = cardFront;
    }

    /**
     * Checks if the two cards that the user chose match
     * @param userDeck
     * @param cardChoice1
     * @param cardChoice2
     * @return true of cards match and false if the cards don't
     */
    public static boolean isMatch( String[][] userDeck , int cardChoice1, int cardChoice2 ){
        //Return true if cards match
        if ( userDeck[cardChoice1][0] == userDeck[cardChoice2][0] ){
            return true;
        }
        //Return false if cards don't match
        return false;
    }

    /**
     * Checks if the card is flipped or not
     * @param cardChoice
     * @param deckBoolean
     * @return true or false depending on state of deckBoolean
     */
    public static boolean checkFlipped( int cardChoice , Boolean[][] deckBoolean){
        if ( deckBoolean[cardChoice][0] == true ){
            return true;
        }
        return false;
    }

    /**
     * Displays the board with current states of cards
     * @param userDeck
     */
    public static void displayBoard( String[][] userDeck ){
        System.out.println("+----+  +----+  +----+  +----+");
        System.out.println("|    |  |    |  |    |  |    |");
        System.out.printf( "| %s |  | %s |  | %s |  | %s |", userDeck[0][0],userDeck[1][0],userDeck[2][0],userDeck[3][0]);
        System.out.println();
        System.out.println("|    |  |    |  |    |  |    |");
        System.out.println("+----+  +----+  +----+  +----+");
        System.out.println("+----+  +----+  +----+  +----+");
        System.out.println("|    |  |    |  |    |  |    |");
        System.out.printf( "| %s |  | %s |  | %s |  | %s |", userDeck[4][0],userDeck[5][0],userDeck[6][0],userDeck[7][0]);
        System.out.println();
        System.out.println("|    |  |    |  |    |  |    |");
        System.out.println("+----+  +----+  +----+  +----+");
        System.out.println("+----+  +----+  +----+  +----+");
        System.out.println("|    |  |    |  |    |  |    |");
        System.out.printf( "| %s |  | %s |  | %s |  | %s |", userDeck[8][0],userDeck[9][0],userDeck[10][0],userDeck[11][0]);
        System.out.println();
        System.out.println("|    |  |    |  |    |  |    |");
        System.out.println("+----+  +----+  +----+  +----+");
        System.out.println("+----+  +----+  +----+  +----+");
        System.out.println("|    |  |    |  |    |  |    |");
        System.out.printf( "| %s |  | %s |  | %s |  | %s |", userDeck[12][0],userDeck[13][0],userDeck[14][0],userDeck[15][0]);
        System.out.println();
        System.out.println("|    |  |    |  |    |  |    |");
        System.out.println("+----+  +----+  +----+  +----+");
    }

}
