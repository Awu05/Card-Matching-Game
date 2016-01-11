/******************************************
  *                                         
  * Name: Andy Wu                          
  *                                         
  * E-mail: awu03@syr.edu               
  *                                         
  * Final Project: Card Matching Game               
  *                                         
  * Compiler: drJava on a pc              
  *                                         
  * Date: November 28, 2012              
  *                                         
  *******************************************/

// DeckOfCards class represents a deck of playing cards.

import java.util.Random;

// (CLS)
public class DeckOfCards
{
    private int currentCard; // index of next Card to be dealt (0-51)
    private static final int NUMBER_OF_CARDS = 12; // constant # of Cards
    // random number generator
    private static final Random randomNumbers = new Random();
    static String x = "null";
    
    static String[] combine = {"Rat", "Ox", "Tiger", "Rabbit", "Dragon", "Snake", "Horse", "Sheep", "Monkey", "Rooster", "Dog", "Pig", 
        "Shu", "Niu", "Hu", "Tu", "Long", "She", "Ma", "Yang", "Hou", "Ji", "Gou", "Zhu"};
    
    static String[] original = {"Rat", "Ox", "Tiger", "Rabbit", "Dragon", "Snake", "Horse", "Sheep", "Monkey", "Rooster", "Dog", "Pig", 
        "Shu", "Niu", "Hu", "Tu", "Long", "She", "Ma", "Yang", "Hou", "Ji", "Gou", "Zhu"};
    
    // constructor fills deck of Cards
    public DeckOfCards()
    {
        String[] animals = { "Rat", "Ox", "Tiger", "Rabbit", "Dragon", "Snake", "Horse", "Sheep", "Monkey", "Rooster", "Dog", "Pig"};
        String[] chinese = { "Shu", "Niu", "Hu", "Tu", "Long", "She", "Ma", "Yang", "Hou", "Ji", "Gou", "Zhu"};
        
        currentCard = 0; // set currentCard so first Card dealt is deck[ 0 ]
    }
    
// (RAN)
    // shuffle deck of Cards with one-pass algorithm
    public void shuffle()
    {
        currentCard = 0;
        
        for ( int first = 0; first < combine.length; first++ ) 
        {
            int second =  randomNumbers.nextInt( NUMBER_OF_CARDS );
            
            // swap current Card with randomly selected Card
            String temp = combine[ first ];        
            combine[ first ] = combine[ second ];   
            combine[ second ] = temp;            
        }
    }
    
    // deal one Card
    public String dealCard()
    {
        // determine whether Cards remain to be dealt
        if ( currentCard < combine.length )
            return combine[ currentCard++ ]; // return current Card in array
        else        
            return null; // return null to indicate that all Cards were dealt
    }
    
    public int checkCard1(String[] a,String[] a1, String b){
        
        for(int i = 0; i < a.length; i++){
            if(b.equals(a[i]))
                return i;
            if(b.equals(a1[i]))
                return i;
        }
        return 0;  
    }
    
    //Finds the card in the array (column,row)
    public String choosenCard(int a, int b){
        
        if(b == 1) {
            
            return combine[a - 1];
        }
        else if(b == 2) {
            
            return combine[a + 7];
        }
        else if(b == 3) {
            return combine[a + 15];
        }
        
        else
            return " ";
        
    }
    
    // Checks to see if both cards match (card1, card2)
    public boolean compareCards(int a, int b){
        
        if(a == b) {
            return true;
        }
        else
            return false;
        
    }
    
    //Take out cards that are already matched up (column,row)
    public void cardsTaken(int a,int b){
        if(b == 1) {
            combine[a - 1] = x;
        }
        
        else if(b == 2) {
            combine[a + 7] = x;
        }
        
        else if(b == 3) {
            combine[a + 15] = x;
        }
        
        else 
            combine[a] = x;
        
//    printArray();
    }
    
    //Prints the array (Only used to check if the cards matched are taken out)
    public void printArray(){
        System.out.println();
        for(int i = 0; i < 24; i++)
            System.out.println(combine[i]);
        System.out.println();
    }
    
    public void reStart() {
        
        combine = original;
        
    }
} // end class DeckOfCards
