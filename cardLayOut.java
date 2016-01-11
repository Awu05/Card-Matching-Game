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


import java.awt.*;
import javax.swing.*;
import java.util.*;

public class cardLayOut extends JPanel {
    
    public void paintComponent (Graphics g ){
        super.paintComponent(g);
        
        int width = getWidth();
        int height = getHeight();
        
        //System.out.println("Width = " + width + " Height = " + height);
        for(int j = 1; j < 4; j++){
            for(int i = 1; i < 9; i++){
                g.setColor(new Color(0,0,255));
                g.fillRect((width/20)+(i*40),(height/7)+(j*60),35,50);
                
            }
            
            g.setColor(new Color(0,0,0));
            g.drawString(" " + j,(width/20)+(20),(height/7)+10+(j*70)); 
            
        }
        
        for(int k = 1; k < 9; k++){
            g.setColor(new Color(0,0,0));
            g.drawString(" " + k,(width/20)+10+(k*40),(height/7)+(50));
        }
        
        //Column Word
        g.drawString("Column",(width/15)+(150),(height/7)+(30));
        //Row Word
        g.drawString("R",(width/20),(height/7)+70+(70));
        g.drawString("o",(width/20),(height/7)+70+(80));
        g.drawString("w",(width/20),(height/7)+70+(90));
        
        //Word Meanings
        g.drawString("Rat means Shu",(width/20),(height/5)+150+(90));
        g.drawString("Ox means Niu",(width/20) + 110,(height/5)+150+(90));
        g.drawString("Tiger means Hu",(width/20) + 210,(height/5)+150+(90));
        g.drawString("Rabbit means Tu",(width/20) + 310,(height/5)+150+(90));
        
        g.drawString("Dragon means Long",(width/20),(height/5)+175+(90));
        g.drawString("Snake means She",(width/20) + 120,(height/5)+175+(90));
        g.drawString("Horse means Ma",(width/20) + 230,(height/5)+175+(90));
        g.drawString("Sheep means Yang",(width/20) + 330,(height/5)+175+(90));
        
        g.drawString("Monkey means Hou",(width/20),(height/5)+200+(90));
        g.drawString("Rooster means Ji",(width/20) + 120,(height/5)+200+(90));
        g.drawString("Dog means Gou",(width/20) + 230,(height/5)+200+(90));
        g.drawString("Pig means Zhu",(width/20) + 330,(height/5)+200+(90));
        
        //Title And Description
        g.drawString("Welcome to Andy's Card Matching Game!",(width/20),(height/70)+(10));
        g.drawString("Objective: ",(width/20),(height/70)+(25));
        g.drawString("1) You have to match all 12 chinese zodiac meanings to its english meaning.",(width/20),(height/70)+(40));
        g.drawString("2) Your score depends on how fast you can match each card.",(width/20),(height/70)+(55));
        g.drawString("3) Have Fun and lean a new Language!",(width/20),(height/70)+(70));
        
    }
    
    //Closes the JPanel
    public void pullThePlug() {
        setVisible(false);
        System.exit(0); 
    }
    
}