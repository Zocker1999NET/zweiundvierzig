package zweiundvierzig;

 

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
	Oberklasse fuer saemtliche GUI Objekte, wie Labels und Buttons
	
	@author Felix Stupp 
	@version 04.06.2016
*/
public abstract class GUI_Interface extends Actor
{
	/**
		Die Breite des Objektes
	*/
	protected int sx = 20;
	/**
		Die Hoehe des Objektes
	*/
	protected int sy = 20;
	/**
		Die Hintergrundfarbe des Objektes
	*/
	protected Color backC = Color.BLACK;
	/**
		Die Vordergrundfarbe (meist die Textfarbe) des Objektes
	*/
	protected Color foreC = Color.WHITE;
	
	/**
		Gibt die Breite des Objektes zurueck.
		@return Die aktuelle Breite
	*/
	public int getWidth() {
		return sx;
	}
	/**
		Gibt die Hoehe des Objektes zurueck.
		@return Die aktuelle Hoehe
	*/
	public int getHeight() {
		return sy;
	}
	/**
		Legt die Groeße des Objektes neu fest und zeichnet es danach damit neu.
		Je nach Objekttyp kann diese Groeße wieder vom Objekt selbst angepasst werden, falls noetig.
		@param w Die neue Breite
		@param h Die neue Hoehe
	*/
	public void setSize(int w, int h) {
		if(w < 1 || h < 1) {
			return;
		}
		sx = w;
		sy = h;
		redraw();
	}
	
	/**
		Gibt die aktuelle Hintergrundfarbe des Objektes zurueck.
		@return Die aktuelle Hintergrundfarbe als System.awt.Color
	*/
	public Color getBackColor() {
		return backC;
	}
	/**
		Legt die Hintergrundfarbe des Objektes fest.
		@param c Die neue Hintergrundfarbe als System.awt.Color
		@return Gibt an, ob diese Farbe unterschiedlich zur bisherig genutzten Farbe wahr. Bei TRUE erfolgte bereits ein Redraw.
	*/
	public boolean setBackColor(Color c) {
		if(!c.equals(backC)) {
			backC = c;
			redraw();
			return true;
		}
		return false;
	}
	
	/**
		Gibt die aktuelle Vordergrundfarbe (meist die Textfarbe) des Objektes zurueck.
		@return Die aktuelle Vordergrundfarbe als System.awt.Color
	*/
	public Color getForeColor() {
		return foreC;
	}
	/**
		Legt die Vordergrundfarbe (meist die Textfarbe) des Objektes fest.
		@param c Die neue Vordergrundfarbe als System.awt.Color
		@return Gibt an, ob diese Farbe unterschiedlich zur bisherig genutzten Farbe wahr. Bei TRUE erfolgte bereits ein Redraw.
	*/
	public boolean setForeColor(Color c) {
		if(!c.equals(foreC)) {
			foreC = c;
			redraw();
			return true;
		}
		return false;
	}
	
	public void act() {}
	
	/**
		Diese Funktion soll die erneute Zeichnung des Objekts erzwingen und wird daher auch von (fast) allen set-Methoden aufgerufen, sollte der neue Wert sich von dem alten unterscheiden.
	*/
	public abstract void redraw();
}
