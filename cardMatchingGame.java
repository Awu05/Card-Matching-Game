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


import java.util.*;
import javax.swing.*;
import java.io.*;
import java.awt.*;

public class cardMatchingGame {
    //Static components
    static Scanner scan = new Scanner(System.in);
    static DeckOfCards myDeckOfCards = new DeckOfCards();
    static long startTime=0,endTime=0;  //for timing
    static String[] text = new String[6]; //String to show high score
    static long[] scores = new long[6];
    static String name = " ";
    static cardLayOut panel = new cardLayOut();
    
    public static void main(String[] args) throws IOException {
        //User decides 
        printMenu();
        int choice = scan.nextInt();
        
        while (choice != 0)
        {
            processMenu(choice);  //process the user's choice
            
            printMenu();       //obtain next choice from user
            
            choice = scan.nextInt();
        }
        
        if(choice == 0 ) {
            String message = String.format("Bye! Please come play again.");
            JOptionPane.showMessageDialog(null, message);
        }
        
    }
    
    // Starts the game
    public static void startGame() throws IOException {
        String[] animals2 = { "Rat", "Ox", "Tiger", "Rabbit", "Dragon", "Snake", "Horse", "Sheep", "Monkey", "Rooster", "Dog", "Pig"};
        String[] chinese2 = { "Shu", "Niu", "Hu", "Tu", "Long", "She", "Ma", "Yang", "Hou", "Ji", "Gou", "Zhu"};
        boolean win = false; 
        int counter = 0; //To see if they matched all 12 cards
        nameInput(); 
        int c;
        int r;
        
        int a;
        int b;
        
        int e;
        int f;
        
        String g = " ";
        String j = " ";
        
        boolean x;
        startTime=System.nanoTime();
        while(!win){
            cardLayOut2();
            dealCard();
            while(!win){
                
                // Card 1 Choices:
                //Column Choice
                c = enterColumn();
                if(c == 12) {
                    win = true;
                    break;
                }
                while(c > 8){
                    String message = String.format("You cannot choose from a column greater than 8. Please choose again.");
                    JOptionPane.showMessageDialog(null, message);
                    c = enterColumn();
                    
                }
                while(c < 1){
                    String message = String.format("You cannot choose from a column less than 1. Please choose again.");
                    JOptionPane.showMessageDialog(null, message);
                    c = enterColumn();
                }
                
                //Row Choice
                r = enterRow();
                while(r > 3){
                    String message = String.format("You cannot choose from a row greater than 3. Please choose again.");
                    JOptionPane.showMessageDialog(null, message);
                    r = enterRow();
                }
                while(r < 1){
                    String message = String.format("You cannot choose from a row less than 1. Please choose again.");
                    JOptionPane.showMessageDialog(null, message);
                    r = enterRow();
                }
                
                g = myDeckOfCards.choosenCard(c,r);
                //Check if cards were matched already
                while(g == "null"){
                    String message = String.format("You have already matched this card. Please pick another card.");
                    JOptionPane.showMessageDialog(null, message);
                    c = enterColumn();
                    r = enterRow();
                    g = myDeckOfCards.choosenCard(c,r);
                }
                
                //*********************** Card 2 Choices: *******************************************
                //Column Choice
                a = enterColumn2();
                while(a > 8){
                    String message = String.format("You cannot choose from a column greater than 8. Please choose again.");
                    JOptionPane.showMessageDialog(null, message);
                    a = enterColumn2();
                }
                while(a < 1){
                    String message = String.format("You cannot choose from a column less than 1. Please choose again.");
                    JOptionPane.showMessageDialog(null, message);
                    a = enterColumn2();
                }
                
                //Row Choice
                b = enterRow2();
                while(b > 3){
                    String message = String.format("You cannot choose from a row greater than 3. Please choose again.");
                    JOptionPane.showMessageDialog(null, message);
                    b = enterRow2();
                }
                while(b < 1){
                    String message = String.format("You cannot choose from a row less than 1. Please choose again.");
                    JOptionPane.showMessageDialog(null, message);
                    b = enterRow2();
                }
                
                j = myDeckOfCards.choosenCard(a,b);
                //Check if cards were matched already
                while(j == "null"){
                    String message = String.format("You have already matched this card. Please pick another card.");
                    JOptionPane.showMessageDialog(null, message);
                    a = enterColumn2();
                    b = enterRow2();
                    j = myDeckOfCards.choosenCard(a,b);
                }
                
                // If card 1 and card 2 are equal
                while(g == j){
                    String message = String.format("You cannnot pick the same card. Please pick again.");
                    JOptionPane.showMessageDialog(null, message);
                    a = enterColumn();
                    b = enterRow();
                    j = myDeckOfCards.choosenCard(a,b);
                }
                //Finds the index of the cards
                e = myDeckOfCards.checkCard1(animals2,chinese2,g);
                f = myDeckOfCards.checkCard1(animals2,chinese2,j);
                //Compares both cards
                x = myDeckOfCards.compareCards(e,f);
                //If the cards match
                if(x == true){
                    correct(g , c, r, j, a, b);
                    counter++;
                    myDeckOfCards.cardsTaken(c,r);
                    myDeckOfCards.cardsTaken(a,b);
                }
                else {
                    incorrect(g , c, r, j, a, b);
                }
                //If all cards are matched 
                if(counter >= 12)
                    win = true;
            }
            
        }
        endTime=System.nanoTime();
        rewriteScore(name,((endTime - startTime)/(1000000000)));
        winningBanner();
        panel.pullThePlug();
    }
    
    public static void dealCard(){
        
        myDeckOfCards.shuffle(); // place Cards in random order
        
        // print all 24 Cards in the order in which they are dealt
        for ( int i = 1; i <= 24; i++ )
        {
            // deal and display a Card
            System.out.printf( "%-19s", myDeckOfCards.dealCard() );
            
            
            if ( i % 4 == 0 ) // output a newline after every fourth card
                System.out.println();
        } // end for
        
    }
    
// (JP)
    // Calls the JPanel
    public static void cardLayOut2() {
        
        
        
        JFrame application = new JFrame();
        application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
        
        application.add(panel);  //add panel to the frame
        
        application.setSize(500, 500); //set the size
        application.setVisible( true);  // show the frame
        
    }
    
    //Prints menu
    public static void processMenu(int choice) throws IOException {
        
        switch(choice) {
            case 1:  //starts the game
                startGame();
                break;
            case 2:  //print the high scores
                viewScore(scores);
                break;
                
            default:
                System.out.println("Sorry, invalid choice");
        }
        
    }
    
// (II/O)
    public static void nameInput(){
        
        String name2 = JOptionPane.showInputDialog("What is your name?");
        name = name2;
        String message = String.format("Welcome %s! Please Enjoy the game.", name2);
        
        //display a message in a dialogue box
        JOptionPane.showMessageDialog(null, message);
        
    }
    //Winning Banner
    public static void winningBanner(){
        String message = String.format("Congratulations! You have matched all the cards in " + ((endTime - startTime)/(1000000000)) + " seconds.");
        
        //display a message in a dialogue box
        JOptionPane.showMessageDialog(null, message);
        
        reSet();
    }
    
    //Resets the game
    public static void reSet(){
        
        myDeckOfCards.reStart();
        long startTime = 0, endTime = 0;
        
    }
    //If the cards match prints where the cards are located (card1,column,row,card2,column,row)
    public static void correct(String g, int c, int r, String j, int a, int b){
        String message = String.format("Correct. \n%s in column %d row %d means %s in column %d row %d",g , c, r, j, a, b );
        
        //display a message in a dialogue box
        JOptionPane.showMessageDialog(null, message);
        
    }
    //If the cards do not match prints where the cards are located (card1,column,row,card2,column,row)
    public static void incorrect(String g, int c, int r, String j, int a, int b){
        String message = String.format("Incorrect. \n%s in column %d row %d does not mean %s in column %d row %d",g , c, r, j, a, b );
        
        //display a message in a dialogue box
        JOptionPane.showMessageDialog(null, message);
        
    }
    
// (DB)
    //Enter the column for card 1
    public static int enterColumn() throws IOException{
        String column = JOptionPane.showInputDialog("Enter Your Column for card 1");
        int f;
        try {
            
            int x = Integer.parseInt( column );
            //If it is not in the bounds
            if(x == 12) {
                //win = true;
                return 12;
            }
            if(x > 9 || x < 0){
                String message = String.format("Please enter a number from 1 to 8.");
                JOptionPane.showMessageDialog(null, message);
                f = enterColumn();
            }
            else 
                return x;
            return f;
        }
        catch (NumberFormatException e) {
            String message = String.format("Please enter a number from 1 to 8.");
            JOptionPane.showMessageDialog(null, message);
            f = enterColumn();
        }
        
        return f;
    }
    //Enter the row for card 1
    public static int enterRow(){
        String row = JOptionPane.showInputDialog("Enter Your Row for card 1");
        int f;
        try {
            
            int y = Integer.parseInt( row );
            //If it is not in the bounds
            if(y > 4 || y < 0) {
                String message = String.format("Please enter a number from 1 to 3.");
                JOptionPane.showMessageDialog(null, message);
                f = enterRow();
            }
            else 
                return y;
            return f;
        }
        catch (NumberFormatException e) {
            String message = String.format("Please enter a number from 1 to 3.");
            JOptionPane.showMessageDialog(null, message);
            f = enterRow();
        }
        
        return f;
    }
    //Enter the column for card 2
    public static int enterColumn2(){
        String column = JOptionPane.showInputDialog("Enter Your Column for card 2");
        int f;
        try {
            
            int x = Integer.parseInt( column );
            //If it is not in the bounds
            if(x > 9 || x < 0) {
                String message = String.format("Please enter a number from 1 to 8.");
                JOptionPane.showMessageDialog(null, message);
                f = enterColumn2();
            }
            else 
                return x;
            return f;
        }
        catch (NumberFormatException e) {
            String message = String.format("Please enter a number from 1 to 8.");
            JOptionPane.showMessageDialog(null, message);
            f = enterColumn2();
        }
        
        return f;
    }
    //Enter the row for card 2
    public static int enterRow2(){
        String row = JOptionPane.showInputDialog("Enter Your Row for card 1");
        int f;
        try {
            
            int y = Integer.parseInt( row );
            //If it is not in the bounds
            if(y > 4 || y < 0) {
                String message = String.format("Please enter a number from 1 to 3.");
                JOptionPane.showMessageDialog(null, message);
                f = enterRow2();
            }
            else 
                return y;
            return f;
        }
        catch (NumberFormatException e) {
            String message = String.format("Please enter a number from 1 to 3.");
            JOptionPane.showMessageDialog(null, message);
            f = enterRow2();
        }
        
        return f;
    }
    //Reads the high score (Score)
    public static void viewHighScore() throws IOException{
        Scanner highScore = new Scanner(new File("score.txt"));
        //Reads the time from score.txt
        try{
            for(int j = 0; j < 6; j++){
                if(highScore.hasNextLine()){
                    highScore.nextLine();
                    scores[j] = highScore.nextInt();
                    highScore.nextLine();
                    
                }
            }
        }
        catch(Exception e){
            
        }
        
    }
    //Reads the high score (Names)
    public static void viewScore(long[] scores) throws IOException {
        Scanner highScore = new Scanner(new File("score.txt"));
        //Read the names from score.txt into an array
        int i = 0;
        while (highScore.hasNext()) {
            text[i] = highScore.nextLine();
            highScore.nextLine();
            i++;
        }
        
        viewHighScore();
        
        sort(scores,text);
        
        // Prints the high scores
        
        String message = String.format("          High Scores\nName     Time (In Seconds) \n%-10s%15d\n%-10s%15d\n%-10s%15d\n%-10s%15d\n%-10s%15d\n%-10s%12d", 
                                       text[0], scores[0], text[1], scores[1], text[2], scores[2], text[3], scores[3], text[4], scores[4], text[5], scores[5]);
        
        //display a message in a dialogue box
        JOptionPane.showMessageDialog(null, message);
        
    }
    //Prints the menu
    public static void printMenu() {
        System.out.println("\n Menu ");
        System.out.println(" ====");
        System.out.println("0: Quit");
        System.out.println("1: Start Game");
        System.out.println("2: View High Scores");
        
        System.out.println("Enter your choice: ");
    }
    
// (FI/O)
    // Outputs the Score to score.txt file
    public static void rewriteScore(String name, long score) throws IOException {
        
        text[5] = name;
        scores[5] = score;
        
        sort(scores,text);
        writeToAFile();
    }
    
// (FI/O)
    //Writes to score.txt
    public static void writeToAFile() throws IOException 
    {
        FileWriter fw = new FileWriter ("score.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter outFile = new PrintWriter (bw);
        //use outFile like you would use System.out.
        //for print and println
        
        for (int r = 0; r < 6; r++) {
            outFile.println(text[r]);
            outFile.println(scores[r]);
        }
        
        outFile.close();  //close the file
        System.out.println("High Scores Saved.");
    }
    
//(ORD)
    // Sorts the score from lowest to highest from score.txt
    public static void sort(long[] list, String[] list2)
    {
        //*********< bubbleSort >**************
        int last; // index of last element of UNSORTED list
        long temp;  //temporary storage for swapping
        String temp2;
        
        for(last = list.length; last >= 1; last--)
        {
            for (int i = 0; i < last-1; i++)
            {
                if (list[i] > list[i+1])
                {
                    //swap
                    temp = list[i];
                    list[i] = list[i+1];
                    list[i+1] = temp;
                    
                    temp2 = list2[i];
                    list2[i] = list2[i+1];
                    list2[i+1] = temp2;
                } //end of if
            } //end for i
        } // end for last
    } //end bubbleSort
    
}

