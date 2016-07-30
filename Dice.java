import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
	Stellt einen Wuerfel (von 1-6) dar, welcher in der Welt sichtbar dargestellt werden soll.

	@author Felix Stupp
	@version 13.06.2016
 */
public class Dice extends GUI_Interface {

	private int number = 0;

	/**
		Erstellt einen Wuerfel mit dem Startwert 0 (fuer noch nicht verwendet).
	*/
	public Dice() {
		redraw();
	}

	/**
		Erstellt einen Wuerfel mit einem bestimmten Startwert, welcher sofort sichtbar ist.
	*/
	public Dice(int startNumber) {
		number = startNumber;
		redraw();
	}

	/**
		Gibt die aktuelle Zahl, die der Wuerfel anzeigt, aus. Gibt die Methode 0 zurueck, heißt dies, dass der Wuerfel noch nicht gewuerfelt wurde.
		@return Die aktuelle Augenzahl oder 0
	*/
	public int getNumber() {
		return number;
	}

	/**
		Legt die Groeße des Wuerfels als Quadrat fest.
	*/
	public void setSizeAsSquare(int l) {
		setSize(l,l);
	}

	/**
		Wuerfelt den Wuerfel, erneuert die Darstellung und gibt die neue Augenzahl zurueck.
	*/
	public int roll() {
		number = (int) (Math.floor(Math.random()*6)+1);
		redraw();
		return number;
	}

	/**
		Zeichnet auf dem gegebenen GreenfootImage ein Oval an der gegebenen Stelle multipliziert mit der einheitlichen Groeße.
	*/
	private void fO(GreenfootImage i, int eX, int eY, int x, int y) {
		i.fillOval(eX*x,eY*y,eX*2,eY*2);
	}

	/**
		Erneuert die Darstellung des Wuerfels.
	*/
	public void redraw() {
		// einheitliche Groeße X und Y (ein Vierzehntel der gesamten Breite und Hoehe)
		int eX = (int) Math.round(sx/8);
		int eY = (int) Math.round(sy/8);
		// Vereinfachung fuer die Augenzahl
		int a = number;
		GreenfootImage i = new GreenfootImage(sx,sy);
		i.setColor(new java.awt.Color(0,0,0,0));
		i.clear();
		i.setColor(backC);
		i.fillShape(new java.awt.geom.RoundRectangle2D.Double(0,0,sx,sy,eX,eY));
		i.setColor(foreC);
		// das "else" fehlt absichtlich
		if(a==1||a==3||a==5) { //Mittlerer Punkt
			fO(i,eX,eY,3,3);
		} if(a>1) { //Punkte links-oben und rechts-unten
			fO(i,eX,eY,1,1);
			fO(i,eX,eY,5,5);
		} if(a>3) { //Punkte links-unten und rechts-oben
			fO(i,eX,eY,1,5);
			fO(i,eX,eY,5,1);
		} if(a>5) { //Punkte links-mitte und rechts-mitte
			fO(i,eX,eY,1,3);
			fO(i,eX,eY,5,3);
		}
		setImage(i);
	}
}