package zweiundvierzig;

 

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Beginnt neues Spiel/ laedt altes Spiel.
 * 
 * @author 4ngelica 
 * @version 04.07.16
 */
public class Start_Load extends World implements ButtonEvent 
{
    static String backgroundImage = "backgroundLight.png"; // Gibt an, welches Bild in allen Menues als Hintergrund gewaehlt werden soll.
    // Light Theme: "backgroundLight.png"
    // Dark Theme: "backgroundDark.png"
    
    Button chulien = new Button("Neues Spiel", 16 , this);
    Button spielanleitung = new Button("Spielanleitung", 16, this);
    Button zurueck = new Button("zurueck", 16, this);
    /**
     * Constructor for objects of class Start_Load.
     * 
     */
    public Start_Load()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1600, 900, 1); 
        setBackground(backgroundImage);
        chulien.setSize(100,50);
        spielanleitung.setSize(100,50);
        zurueck.setSize(100,50);
        addObject (chulien, (1600-chulien.getWidth())/2+100, (900-chulien.getHeight())/2);
        addObject (spielanleitung, (1600-spielanleitung.getWidth())/2+100, (900-spielanleitung.getHeight())/2 + 80);
        
        Greenfoot.setSpeed(100);
        Greenfoot.start();
    }
    public void buttonClicked ( Bildbutton b)
    {
       if ( b  == chulien)
       {
           World chean = new Colours (1600, 900, 1);
           Greenfoot.setWorld(chean);
           
       }
       
       if (b == spielanleitung)
       {
          addObject (new Spielanleitung(), 800, 410);
          addObject (zurueck, 800 , 870);
        }
        
       if (b == zurueck)
       {
           removeObjects(getObjects(Spielanleitung.class));
           removeObject(zurueck);
        }
    }
    
    
    public void Load()
    {
        
    }
}
