import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.awt.Graphics2D;

/**
	Diese Klasse stellt einen Button dar.
	Als funktionelles Vorbild dafuer haelt der Button aus dem .NET-Framework fuer UWP-Apps dar.

	@author Felix Stupp
	@version 20.05.2016
*/
public class Button extends Bildbutton {
	
	private int textSize = 32; // as default text size
	private String text = "";
	
	/**
		Erstellt einen Button mit dem gegebenen Text und der zugehoerigen Textgroeße.
		@param txt Der Text, den der Button darstellen soll.
		@param size Die Textgroeße, in der der Button den Text darstellen soll.
	*/
	public Button(String txt, int size) {
	    super(new GreenfootImage(txt,size,Color.WHITE,Color.BLACK));
		text = txt;
		textSize = size;
		redraw();
	}
	/**
		Erstellt einen Button mit dem gegebenen Objekt als Event-Handler.
		@param h Der Handler mit dem Interface ButtonEvent implementiert.
	*/
	public Button(ButtonEvent h) {
	    super(h);
		redraw();
	}
	/**
		Erstellt einen Button mit dem gegebenen Text, der zugehoerigen Textgroeße und dem gegebenen Objekt als Event-Handler.
		@param txt Der Text, den der Button darstellen soll.
		@param size Die Textgroeße, in der der Button den Text darstellen soll.
		@param h Der Handler mit dem Interface ButtonEvent implementiert.
	*/
	public Button(String txt, int size, ButtonEvent h) {
	    super(new GreenfootImage(txt,size,Color.WHITE,Color.BLACK),h);
		text = txt;
		textSize = size;
		redraw();
	}
	
	/**
		Gibt die aktelle Textgroeße zurueck.
		@return Aktuelle Textgroeße
	*/
	public int getTextSize() {
		return textSize;
	}
	/**
		Legt eine neue Textgroeße fest.
		@param s Neue Textgroeße
		@return Gibt an, ob sich die Textgroeße geaendert hat. Bei TRUE erfolgte bereits ein Redraw.
	*/
	public boolean setTextSize(int s) {
		if(textSize != s && s > 0) {
			textSize = s;
			redraw();
			return true;
		}
		return false;
	}
	
	/**
		Gibt den aktuellen Text zurueck.
		@return Der aktuelle Text
	*/
	public String getText() {
		return text;
	}
	/**
		Setzt den darzustellenden Text auf den neuen Wert.
		@param s Der neue Text
		@return Gibt an, ob sich der Text geaendert hat. Bei TRUE erfolgte bereits ein Redraw.
	*/
	public boolean setText(String s) {
		if(text != s) {
			text = s;
			redraw();
			return true;
		}
		return false;
	}
	
	public GreenfootImage getPicture() {
	    return null;
	}
	
	public boolean setPicture(GreenfootImage i) {
	    return false;
	}
	
	/**
		Zeichnet das GreenfootImage des Buttons erneut und zeigt es an.
	*/
	public void redraw() {
	    //picture = new GreenfootImage(text,textSize,Color.BLACK,Color.WHITE);
		picture = new GreenfootImage(text,textSize,foreC,backC);
		super.redraw();
	}
}
