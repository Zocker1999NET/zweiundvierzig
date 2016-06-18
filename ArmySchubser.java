/*
    Hinweis zum Verbleib der Klasse:
    Diese Klasse wird nach ihrer Fertigstellung in die GeneralMap integriert.
    Dabei wird der Code der act()-Methode innerhalb einer If-Abfrage bei bestimmten States ausgeführt.
    Sonstige Methoden werden im Original belassen, sofern keine Überschneidungen bei den Bezeichnern vorhanden sind.
*/

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.MouseInfo.*;
import javax.swing.JOptionPane;

/**
 * Schubst Einheiten umher.
 * 
 * @author MaxiJohl, GruenerWal
 * @version 0.1.1
 */
public class ArmySchubser extends Map_World
{

    /**
     * Constructor for objects of class ArmySchubser.
     * 
     */
    public ArmySchubser(String[] playerList, int[] colorList)
    {
        super(playerList,colorList);
        // Hi.
    }

    public void act() 
    {
        Province firstProvince = null;
        Province secondProvince = null;

        for ( int i = 1; i <= provinceCount; i++)
        {
            if (provinces[i].hasClicked() == true)
            {
                provinces[i] = firstProvince;
                break;
            }
        }

        for ( int i = 1; i <= provinceCount; i++)
        {
            if (provinces[i].hasClicked() == true && provinces[i] != firstProvince)
            {
                provinces[i] = secondProvince;
                break;
            }
        }

        String toMove = JOptionPane.showInputDialog(null, "Wieviele Einheiten willst du verschieben?");
        Integer entitiesToMove = Integer.valueOf(toMove);

        if ( (firstProvince.getEntityCount() - entitiesToMove) > 0 && firstProvince.isProvinceNear(secondProvince.getID()) == true )
        {
            firstProvince.removeFromEntities(entitiesToMove);
            secondProvince.addToEntities(entitiesToMove);
        }

        else
        {
            System.out.println("Du hast nicht genügend Einheiten, um die gewünschte Anzahl von " + firstProvince.getDisplayName() + " nach " + secondProvince.getDisplayName() + " zu verschieben, Köhler.");
        }
    }
}