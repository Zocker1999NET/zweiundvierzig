import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Oberklasse für verschiedene Maps;
 * neue Maps werden als Unterklasse dieser Klasse eingefügt.
 * 
 * @author GruenerWal
 * @version 0.0.1
 */
public class GeneralMap extends World
{
    /**
     *  Felder, im Moment nur Anzahl der Provinzen
     *  Später evtl. weitere Werte wie Schwierigkeit denkbar
     */
    
    protected int provinzen;    

    /** Konstruktor für nicht weiter definierte Map, sollte im Moment nicht benutzt werden.
     * Später als Konstruktor für Default-Map denkbar.
     */
    public GeneralMap(int x, int y, int p)
    {    
        /**
         * Erstellt eine leere Karte mit den übergebenen Eigenschaften
         * @param x X-Ausdehnung der Welt
         * @param y Y-Ausdehnung
         * @param p Kantenlänge der Felder in Pixeln
         */
        super(1600, 900, 1);
        addObject(new Menue_Button(),100,38);
        addObject(new Roll_Button(),84,835);
        addObject(new Roll_Button(),1513,835);
    }
}