package zweiundvierzig;

 

import greenfoot.*;
import java.awt.Color;

/**
 * Write a description of class Player here.
 * 
 * @author (Genosse Betakevin und Genosse Julien und Genosse GruenerWal) 
 * @version 6.6.6-build2
 */
public class Player extends Actor
{
    @Override public GeneralMap getWorld(){
        return (GeneralMap) super.getWorld();
    }
    int stars=0;
    int add=0;
    int id=0;
    int provZahl=0;
    int provVgl=0;
    int[] stats = new int [6];
    String n;
    int color;
    int textsize;
    int c;
    public int active;
    boolean starsNeeded = false;

    //definiert die ID und Namens Variable
    public Player(int identity,String name, int c)
    {
        n = name;
        id = identity;
        color = c;
        // redrawPlayer();
    }
    //gibt die Spieler ID zurueck
    public int getID()
    {
        return id;
    }
    // gibt den Spielernamen zurueck
    public String getDisplayName ()
    {
        return n;
    }
    //gibt die Sternenanzahl zurueck
    public int getStars()
    {
        return stars;
    }

    /**
     * Gibt die Farbe des Spielers als int-Wert heraus.
     * Wahnsinn, dass du dir den Scheiß hier grad durchliest.
     * ~GruenerWal
     */
    public int getColor()
    {
        return color;
    }

    // Von Felix: Methode nicht architektur-konform

    /* // erhoeht die Sternenzahl um eine random ausgewaehlte Anzahl von 1-3
    public void addToStars()
    {
    int rand;
    int pre;
    rand = (int)(1+6*Math.random());
    if (rand == 1 || rand == 2|| rand == 3)
    {
    add = 1;
    redrawPlayer();
    }
    if (rand == 4|| rand == 5)
    {
    add = 2;
    redrawPlayer();
    }
    if (rand == 6)
    {
    add = 3;
    redrawPlayer();
    }
    if (gotProv== true)
    {
    pre = stars;
    stars+=add;
    System.out.println("Deine vorherige Sternenanzahl betrug " + pre + ".");
    System.out.println("Du hast " + add + " Sterne erhalten.");
    System.out.println("Deine aktuelle Sternenanzahl betraegt " + stars + ".");
    redrawPlayer();
    }
    } */

    private void checkStars() {
        if(stars < 0) {
            stars = 0;
        }
    }

    // Von Felix: Architektur-konforme Funktion
    public int addToStars(int s) {
        stars += s;
        checkStars();
        redrawPlayer();
        return stars;
    }
    //eine fuer das Testen gedachte Methode, die die Anzahl der Sterne auf ein gewuenschtes Maß setzt
    public int setStars (int set)
    {
        stars = set;
        checkStars();
        redrawPlayer();
        return stars;
    }
    //eine Methode, die das Abziehen von Sternen testet und, wenn das Ergebnis >= 0 ist, die Sternenanzahl um eine gewaehlte Anzahl verringert
    public int removeFromStars(int sub)
    {
        stars -= sub;
        checkStars();
        redrawPlayer();
        return stars;
    }

    public boolean canStarsRemoved(int s) {
        return (stars - s) >= 0;
    }

    public int getProvinceCount()
    { 
        int p = 0;
        int[] provinces = getWorld().getProvinceOwners();
        for (int x=1; x < provinces.length; x++)
        {
            if (provinces[x] == id)
            {
                p++;
            }
        }
        return p;
    }
    
    public int getEntitiesCount() {
        return getWorld().getPlayerEntityCount(id);
    }

    public void gotEntities(int gotEnt)
    {
        stats[3]+= gotEnt;
        reloadMaxEntities();
        redrawPlayer();
    }

    public void lostEntity()
    {
        stats[4]+=1;
        redrawPlayer();
    }

    public void gotProvince() {
        stats[0]++;
        reloadMaxInfluence();
        redrawPlayer();
        starsNeeded = true;
    }

    public void lostProvince() {
        stats[1]++;
        redrawPlayer();
    }

    public void reloadMaxInfluence() 
    {
        int c = getProvinceCount();
        if(stats[2]< c)
        {
            stats[2]=c;
            redrawPlayer();
        }
    }

    public void reloadMaxEntities()
    {
        int c = getEntitiesCount();
        if (stats[5]< c)
        {
            stats[5]=c;
            redrawPlayer();
        }
    }

    public boolean[] getMyProvinces()
    {
        int[] provinces = getWorld().getProvinceOwners();
        boolean[] myProvinces = new boolean[provinces.length];
        for (int x=0; x < provinces.length; x++)
        {
            if (provinces[x]== id)
            {
                myProvinces[x]=true;
            }
            else 
            {
                myProvinces[x]=false;
            }

        }
        redrawPlayer();
        return myProvinces;
    }

    public int[] getStatistics()
    {
        return stats;
    }

    public int setColor(int c)
    {
        color = c;
        redrawPlayer();
        return color;

    }

    private Color getTextCol() {
        return (getWorld().getCurrentPlayerID() == id) ? new Color(0,0,0) : new Color(255,255,255);
    }

    private Color getTransBackCol() {
        return (getWorld().getCurrentPlayerID() == id) ? new Color(255,255,255) : new Color(0,0,0);
    }

    public void redrawPlayer()
    {
        getWorld().reloadPlayerStat();
        int textSize = 20;
        if(n == null)
        {
            n = "leererSpieler";
        }
        GreenfootImage statistics = new GreenfootImage(137,120);
        if(getWorld().getCurrentPlayerID() == id) {
            statistics.setColor(new Color(255,255,255));
            statistics.fill();
        }
        GreenfootImage name = new GreenfootImage(n,textSize,getTextCol(),getTransBackCol());        
        statistics.drawImage(name,0,0);

        setImage(statistics);
        oDecide(statistics,textSize);        
    }

    private void oDecide(GreenfootImage statistics,int textSize)
    {
        GreenfootImage flag = new GreenfootImage("images\\BlaueArmee.jpg");
        switch(color)
        {
            case 2:
            flag = new GreenfootImage("images\\BlaueArmee.jpg");
            break;
            case 5:
            flag = new GreenfootImage("images\\GelbeArmee.jpg");
            break;
            case 6:
            flag = new GreenfootImage("images\\LilaArmee.jpg");
            break;
            case 4:
            flag = new GreenfootImage("images\\RoteArmee.jpg");
            break;
            case 1:
            flag = new GreenfootImage("images\\SchwarzeArmee.jpg");
            break;
            case 3:
            flag = new GreenfootImage("images\\GrueneArmee.jpg");
            break;
        }
        redraw(statistics,flag,textSize);
    }

    private void redraw(GreenfootImage statistics,GreenfootImage flag, int textSize)
    {
        flag.scale(137,83);
        statistics.drawImage(flag,0,textSize);
        String statTxt = (getWorld().getCurrentPlayerID() == id) ? "Du bist dran!" : stats[0] + " | " + stats[1] + " | " + stats[2] + " | " +stats[3] + " | " + stats[4] + " | " + stats[5];
        GreenfootImage playerStatistics = new GreenfootImage(statTxt,textSize,getTextCol(),getTransBackCol());
        statistics.drawImage(playerStatistics, 0, 103);

        setImage(statistics);
    }
}