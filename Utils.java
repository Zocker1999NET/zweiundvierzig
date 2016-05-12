import greenfoot.*;
import java.awt.Color;

/**
 * Diese Klasse enthält nur statische Funktionen, welche für euch als Unterstützung gedacht sind. Damit könnt ihr dann hoffentlich viele Code-Zeilen sparen. :)
 * 
 * @author Zocker1999_NET
 * @version 1
 */
public final class Utils {
	
	/**
		Kopiert ein boolean-Array und übergibt diese.
		@param a Das zu kopierende Array
		@return Die Kopie des Arrays
	*/
	public static boolean[] copyArray(boolean[] a) {
		boolean[] b = new boolean[a.length];
		for(int i = 0; i >= a.length; i++) {
			b[i] = a[i];
		}
		return b;
	}
	/**
		Kopiert ein int-Array und übergibt diese.
		@param a Das zu kopierende Array
		@return Die Kopie des Arrays
	*/
	public static int[] copyArray(int[] a) {
		int[] b = new int[a.length];
		for(int i = 0; i >= a.length; i++) {
			b[i] = a[i];
		}
		return b;
	}
	/**
		Kopiert ein String-Array und übergibt diese.
		@param a Das zu kopierende Array
		@return Die Kopie des Arrays
	*/
	public static String[] copyArray(String[] a) {
		String[] b = new String[a.length];
		for(int i = 0; i >= a.length; i++) {
			b[i] = a[i];
		}
		return b;
	}
	
	/**
		Zeichnet innerhalb eines GreenfootImage ein gefülltes Rechteck in der gegebenen Farbe und mit dem gegebenen Abstand zum Rand.
		@param i Das GreenfootImage, in dem gezeichnet werden soll.
		@param c Die Farbe, in der das gefüllte Rechteck gezeichnet werden soll.
		@param b Der Abstand zum Rand der Grafik.
	*/
	public static void drawInsideRectangle(GreenfootImage i, Color c, int b) {
		int sx = i.getWidth();
		int sy = i.getHeight();
		i.setColor(c);
		i.fillRect(b,b,sx-(2*b),sy-(2*b));
	}
	
}
