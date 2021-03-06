package zweiundvierzig;

 

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.lang.Math;
import java.util.Arrays;
import java.awt.Color;
import greenfoot.MouseInfo.*;
import javax.swing.JOptionPane;
import java.awt.Color;

/**
Oberklasse fuer verschiedene Maps;
neue Maps werden als Unterklasse dieser Klasse eingefuegt.

@author GruenerWal, MaxiJohl, Felix Stupp, Samuel
@version 0.3.0
 */
public abstract class GeneralMap extends World implements ButtonEvent
{
    /*
    Felder, im Moment nur Anzahl der Provinzen
    Spaeter evtl. weitere Werte wie Schwierigkeit denkbar
     */
    Button modus = new Button("Kampf\nbeginnen",25,this);

    private final int X_OFFSET = 200; // Verschiebt die Provinzen nach rechts
    private final int Y_OFFSET = 0; // Verschiebt die Provinzen nach unten

    /*
    Die einzelnen Positionen der Provinzen wird mit SCALE_VALUE/10000 multipliziert.
    Dies ist nuetzlich, wenn die Karte beispielsweise nur noch 80% der Originalgroeße bei ihrer Darstellung groß ist.
    Bei diesem Beispiel waere hier, neben dem Offset oben, der Wert 0.8 einzutragen.
     */
    private final double SCALE_VALUE = 1;

    protected enum GameStates {
        SETZEN,
        KAMPF,
        VERSCHIEBEN
    }

    protected Province[] provinces = new Province[] {null};
    protected int[] continentBoni;
    protected Player[] players;

    protected int currentPlayer = 0;
    private int statPlayer = -1;
    protected GameStates status = GameStates.SETZEN;

    protected int provinceCount;
    protected int armyMinimum;
    
    private Label statLabel1 = new Label("Lade Statistiken ...",20);
    private Label statLabel2 = new Label("Lade Statistiken ...",20);

    /**
    Erstellt eine GeneralMap mit allen Eigenschaften und initialisiert die Arrays fuer Provinzen und Spieler.
    @param backImage Das Hintergrundbild, welches von dieser Klasse geladen und dargestellt wird.
    @param playerList Die Liste mit den Namen der Spieler
    @param colorList Die Liste mit den Farben der Spieler
     */
    public GeneralMap(String[] playerList, int[] colorList)
    {    
        super(1600,900,1);
        players = new Player[playerList.length];
        modus.setSize(100,100);
        modus.setBackColor(Color.white);
        modus.setForeColor(Color.black);
        addObject(statLabel1, 270, 800);
        addObject(statLabel2, 500, 800);
        addObject( modus, 1500, 808);
        for (int i = 0; i < playerList.length; i++) {
            players[i] = new Player(i,playerList[i],colorList[i]);
            addObject(players[i],0,0);
        }

        createPlayerObjects(playerList.length);

        players[2].addToStars(1);

        if ( playerList.length > 3 )
        {
            players[3].addToStars(1);
        }
        if ( playerList.length > 4 )
        {
            players[4].addToStars(2);
        }
    }

    public int currentPlayer()
    {
        return currentPlayer;
    }

    public void redrawGameStates()
    {
        int textSize = 20;
        int X = 422;
        int Y = 677;

        if(status == GameStates.KAMPF)
        {
            GreenfootImage GameStates = new GreenfootImage("KAMPF!!! Waehle die Provinzen aus, die kaempfen sollen, " + getPlayerName() + ".",textSize,new Color(255,255,255),new Color(0,0,0));
            GreenfootImage GameStatesEmpty = new GreenfootImage(GameStates.getWidth(),textSize);
            GameStatesEmpty.drawImage(GameStates,0,0);
            GreenfootImage States = new GreenfootImage("MapWorldFight.png");
            States.drawImage(GameStatesEmpty,X,Y);
            setBackground(States);
        }
        if(status == GameStates.VERSCHIEBEN)
        {
            GreenfootImage GameStates = new GreenfootImage("VERSCHIEBEN! Waehle die Provinzen aus, bei denen du schubsen moechtest, " + getPlayerName() + ".",textSize,new Color(255,255,255),new Color(0,0,0));
            GreenfootImage GameStatesEmpty = new GreenfootImage(GameStates.getWidth(),textSize);

            GameStatesEmpty.drawImage(GameStates,0,0);
            GreenfootImage States = new GreenfootImage("MapWorldMove.png");
            States.drawImage(GameStatesEmpty,X,Y);
            setBackground(States);
        }
        if(status == GameStates.SETZEN)
        {
            GreenfootImage GameStates = new GreenfootImage("SETZEN! Waehle die Provinz aus, der du schenken moechtest, " + getPlayerName() + ".",textSize,new Color(255,255,255),new Color(0,0,0)); // "Setzten" Lern Deutsch, Samuel
            GreenfootImage GameStatesEmpty = new GreenfootImage(GameStates.getWidth(),textSize);
            GameStatesEmpty.drawImage(GameStates,0,0);
            GreenfootImage States = new GreenfootImage("MapWorld.png");
            States.drawImage(GameStatesEmpty,X,Y);
            setBackground(States);
        }        

    }

    private void createPlayerObjects(int playerCount)
    {
        if(playerCount > 6) {
            playerCount = 6; // Um denselben Effekt wie beim Code zuvor zu erzeugen
        }
        switch (playerCount) {
            case 6:
            players[5].setLocation(1512,450);
            case 5:
            players[4].setLocation(1512,280);
            case 4:
            players[3].setLocation(1512,110);
            case 3:
            players[2].setLocation(82,450);
            case 2:
            players[1].setLocation(82,280);
        }
        players[0].setLocation(82,110);
    }

    /**
    Fuegt alle Provinzen aus dem Array der Welt an der entsprechden Stelle zu.
     */
    protected void initProvinces() {
        for(int i = 1; i < provinces.length; i++) {
            addObject(provinces[i],((int) Math.floor(provinces[i].getXPos() * SCALE_VALUE)) + X_OFFSET,((int) Math.floor(provinces[i].getYPos() * SCALE_VALUE)) + Y_OFFSET);
        }
    }

    private void redrawProvinces() {
        for(int i = 1; i < provinces.length; i++) {
            provinces[i].redrawProvince();
        }
    }

    protected void redrawPlayers() {
        for(int i = 0; i < players.length; i++) {
            players[i].reloadMaxInfluence();
            players[i].reloadMaxEntities();
            players[i].redrawPlayer();
        }
    }

    /**
    Zeigt die angegebene Nachricht in einem JOptionPane Dialogfeld an.
    @param msg Die anzuzeigend  e Nachricht
     */
    private void showDialog(String msg) {
        JOptionPane.showMessageDialog(null,msg);
    }

    public void act() {
        if (status == GameStates.SETZEN) {
            actPlace();
        } else if(status == GameStates.KAMPF) {
            actFight();
        } else if (status == GameStates.VERSCHIEBEN) {
            actMove();
        }
        redrawGameStates();
    }

    /**
    Gibt die Anzahl der vorhandenen Spieler aus.
     */
    public int getPlayerCount()
    {
        return players.length;
    }

    /**
     * Gibt die Farbe des angefragten Spielers heraus.
     * @param int pID -> Farbe des Spielers
     */
    public int getPlayerColor(int pID)
    {
        return players[pID].getColor();
    }

    /**
    Gibt die PlayerID des aktuellen Spielers an.
    @return PlayerID des aktuellen Spielers
     */
    public int getCurrentPlayerID() {
        return currentPlayer;
    }

    /**
    Gibt den Namen des aktuellen Spielers aus.
    @return Der Name des aktuellen Spielers
     */
    public String getPlayerName()
    {
        return players[currentPlayer].getDisplayName();
    }

    /**
    Gibt den Namen des Spielers aus, dem dessen ID gehoert.
    @param plID Die ID des zu findenden Spielers
    @return Der Name des Spielers
     */
    public String getPlayerName(int plID)
    {
        return players[plID].getDisplayName();
    }

    /**
    Gibt die Anzahl der Sterne des aktuellen Spielers zurueck.
    @return Die Anzahl der Sterne des aktuellen Spielers
     */
    public int getPlayerStars()
    {
        return players[currentPlayer].getStars();
    }

    /**
    Gibt die ID des Spielers zurueck, dem die gefragte Provinz gehoert.
    @param prID Die gefragte Provinz
     */
    public int getProvinceOwner(int prID)
    {
        if(prID < 1 || prID > provinces.length) {
            return -1;
        }
        return provinces[prID].getOwner();
    }

    /**
    Gibt eine Liste mit allen Provinzen und deren Besitzern zurueck.
    @return Array mit der Provinz-ID als Index und dem Besitzer als Wert
     */
    public int[] getProvinceOwners()
    {
        int[] prOwners = new int[provinces.length];
        for (int i = 1; i < provinces.length; i++) {
            prOwners[i] = provinces[i].getOwner();
        }
        return prOwners;
    }

    /**
    Zaehlt die Anzahl der Einheiten von allen Provinzen zusammen, die einem bestimmten Spieler gehoert.
    @param playerID Die ID des Spielers, fuer den die Einheiten gezaehlt werden sollen.
    @return Die Anzahl der Einheiten, die dem Spieler gehoeren.
     */
    public int getPlayerEntityCount(int playerID)
    {
        int c = 0;
        for (int i = 1; i < provinces.length; i++) {
            if(provinces[i].getOwner() == playerID) {
                c = c + provinces[i].getEntityCount();
            }
        }
        return c;
    }

    private void resetClicked() {
        for(int i = 1; i <= (provinces.length - 1); i++) {
            provinces[i].hasClicked();
        }
    }

    public void reloadPlayerStat() {
        Player cP = players[(statPlayer == -1) ? currentPlayer : statPlayer];
        cP.reloadMaxInfluence();
        cP.reloadMaxEntities();
        int[] stats = cP.getStatistics();
        statLabel1.setText(
            "Provinz-Statistik" +
            "\nAktuell: " + cP.getProvinceCount() +
            "\nErobert: " + stats[0] +
            "\nVerloren: " + stats[1] +
            "\nMaximum: " + stats[2]
        );
        statLabel2.setLocation(statLabel1.getX() + statLabel1.getWidth() + 20, statLabel2.getY());
        statLabel2.setText(
            "Einheiten-Statistik" +
            "\nAktuell: " + cP.getEntitiesCount() +
            "\nAusgebildet: " + stats[3] +
            "\nGefallen: " + stats[4] +
            "\nMaximum: " + stats[5]
        );
    }
    
    public void buttonClicked(Bildbutton b) {
        if ( modus == b ) {
            if(status==GameStates.SETZEN && freeArmies == 0 ) {
                status=GameStates.KAMPF;
                offenderProvince = null;
                defenderProvince = null;
                maxDiceOffender = "";
                maxDiceDefender = "";
                modus.setBackColor(Color.white);
                modus.setForeColor(Color.black);
                modus.setText("Kampf\nbeenden");
            } else if (status==GameStates.KAMPF) {
                giveRandomStars(players[currentPlayer]);
                status=GameStates.VERSCHIEBEN;
                savedProvince = null;
                modus.setText("Naechster\nSpieler");
            } else if (status==GameStates.VERSCHIEBEN) {
                freeArmies = -1;
                if(currentPlayer >= players.length-1)
                {
                    currentPlayer=0;
                }
                else
                {
                    currentPlayer+=1;   
                }
                status=GameStates.SETZEN;
                modus.setText("Kampf\nbeginnen");
            }
            redrawProvinces();
            redrawPlayers();
            reloadPlayerStat();
        }
    }

    // Kampfsystem

    Province offenderProvince;
    Province defenderProvince;
    String maxDiceOffender = "";
    String maxDiceDefender = "";

    private void actFight() {
        for(int i = 1; i <= (provinces.length - 1); i++) {
            if (provinces[i].hasClicked() == true) {
                if(provinces[i].getOwner() == currentPlayer) {
                    OffenderProvince(provinces[i]);
                } else {
                    DefenderProvince(provinces[i]);
                }
            }
        }
    }

    private void OffenderProvince(Province p)
    {
        if(p.getEntityCount() > 1) {
            if(offenderProvince != null) {
                offenderProvince.redrawProvince();
            }
            offenderProvince = p;
            p.redrawProvince(2);
            // System.out.println("Die Provinz " + provinces[i].getDisplayName() + " wurde als angreifende Provinz ausgewaehlt! Sie gehoert Spieler" + provinces[i].getOwner());
            chooser();
        }
    }

    private void DefenderProvince(Province p)
    {
        if(defenderProvince != null) {
            defenderProvince.redrawProvince();
        }
        defenderProvince = p;
        p.redrawProvince(3);
        // System.out.println("Die Provinz " + provinces[i].getDisplayName() + " wurde als verteidigende Provinz ausgewaehlt! Sie gehoert Spieler" + provinces[i].getOwner());
        chooser();
    }

    private void chooser()
    {
        if(offenderProvince == null || defenderProvince == null) {
            return;
        }
        if(!offenderProvince.isProvinceNear(defenderProvince.getID())) {
            JOptionPane.showMessageDialog(null,"Die Provinzen liegen nicht mal ansatzweise nebeneinander!");
            offenderProvince.redrawProvince();
            defenderProvince.redrawProvince();
            offenderProvince = null;
            defenderProvince = null;
            return;
        }
        //System.out.println("Es wird gewuerfelt!");        
        Dice_Offender diceOffender = new Dice_Offender();
        // System.out.println("Der Angreifer ereichte folgende Wuerfelzahlen:"); 
        int[] maxDiceOffenderArray = diceOffender.dice_offender(offenderProvince.getEntityCount());
        Dice_Defender diceDefender = new Dice_Defender();
        // System.out.println("Der Verteidiger ereichte folgende Wuerfelzahlen:");
        int[] maxDiceDefenderArray = diceDefender.dice_defender(defenderProvince.getEntityCount());
        Arrays.sort(maxDiceOffenderArray);
        Arrays.sort(maxDiceDefenderArray);
        maxDiceOffender = "";
        for(int i = 0;i<3;i++)
        {
            if(maxDiceOffenderArray[i] != 0) {
                if(i == 0)
                {
                    maxDiceOffender = "" + maxDiceOffenderArray[i];
                }
                else
                {
                    maxDiceOffender = maxDiceOffender + ", " + maxDiceOffenderArray[i];
                }
            }
        }
        maxDiceDefender = "";
        for(int i = 0;i<2;i++)
        {
            if(maxDiceDefenderArray[i] != 0) {
                if(i == 0)
                {
                    maxDiceDefender = "" + maxDiceDefenderArray[i];
                }
                else
                {
                    maxDiceDefender = maxDiceDefender + ", " + maxDiceDefenderArray[i];
                }
            }
        }
        JOptionPane.showMessageDialog(null,"Es wurde gewuerfelt. Der Angreifer erreichte folgende Wuerfelzahlen: " + maxDiceOffender + "\nDer Verteidiger erreichte diese Wuerfelzahlen: " + maxDiceDefender);
        diceOffender = null;
        diceDefender = null;
        decider(maxDiceOffenderArray, maxDiceDefenderArray);
    }

    // berechnet Zahlen und findet Gewinner; fuehrt Konsequenz aus
    private void decider(int[] maxDiceOffender, int [] maxDiceDefender)
    {
        Player offPl = players[offenderProvince.getOwner()];
        Player defPl = players[defenderProvince.getOwner()];

        int maxDefender = maxDiceDefender[1];
        int maxOffender = maxDiceOffender[2];
        if (maxOffender > maxDefender)
        {
            defenderProvince.removeFromEntities(1);
            defPl.lostEntity();
            if (defenderProvince.getEntityCount() <= 0) {
                defenderProvince.setOwner(offenderProvince.getOwner());
                offenderProvince.removeFromEntities(1);
                defenderProvince.setEntityCount(1);
                offPl.gotProvince();
                defPl.lostProvince();
                JOptionPane.showMessageDialog(null,"Somit gewinnt der Angreifer (" + getPlayerName(offenderProvince.getOwner()) + "). Die Provinz gehoert fortan dem Angreifer (" + getPlayerName(offenderProvince.getOwner()) + ").");
            } else {
                JOptionPane.showMessageDialog(null,"Somit gewinnt der Angreifer (" + getPlayerName(offenderProvince.getOwner()) + "). Dem Verteidiger (" + getPlayerName(defenderProvince.getOwner()) + ") wird eine Einheit abgezogen. Er hat nun noch " + defenderProvince.getEntityCount() + " Einheiten.");
            }
        }

        if (maxOffender < maxDefender && offenderProvince.getEntityCount()>1)
        {
            offenderProvince.removeFromEntities(1);
            offPl.lostEntity();
            JOptionPane.showMessageDialog(null,"Somit gewinnt der Verteidiger (" + getPlayerName(defenderProvince.getOwner()) + "). Dem Angreifer (" + getPlayerName(offenderProvince.getOwner()) + ") wird eine Einheit abgezogen. Er hat nun noch " + offenderProvince.getEntityCount() + " Einheiten.");            
        }

        if (maxOffender == maxDefender && offenderProvince.getEntityCount()>1)
        {
            offenderProvince.removeFromEntities(1);
            offPl.lostEntity();
            JOptionPane.showMessageDialog(null,"Da es unentschieden ist, gewinnt der Verteidiger (" + getPlayerName(defenderProvince.getOwner()) + "). Dem Angreifer (" + getPlayerName(offenderProvince.getOwner()) + ") wird eine Einheit abgezogen. Er hat nun noch " + offenderProvince.getEntityCount() + " Einheiten.");
        }

        offenderProvince.redrawProvince();
        defenderProvince.redrawProvince();
        offenderProvince = null;
        defenderProvince = null;
    }

    // Einheiten verschieben

    Province savedProvince = null;

    private void actMove() {
        for ( int i = 1; i <= (provinces.length - 1); i++) {
            if (provinces[i].hasClicked() == true) {
                useProvincesToMove(provinces[i]);
                break;
            }
        }
    }

    /**
    Nimmt zwei Provinzen entgegen, und fragt, wieviele Einheiten vom ersten zum zweiten Eintrag verschoben werden sollen.
    ueberprueft, ob eine Verschiebung moeglich ist und fuehrt sie bei Erfolg aus.
     */
    private void moveEntities(Province sourceProvince, Province destinationProvince)
    {
        String toMoveString = JOptionPane.showInputDialog(null, "Wieviele Einheiten willst du verschieben?");
        int entitiesToMove = Utils.StringToInt(toMoveString);

        if (entitiesToMove == 0) {
            JOptionPane.showMessageDialog(null,"Bitte eine Zahl eingeben, Kommandant " + getPlayerName() + ".");
            return;
        }

        if ( (sourceProvince.getEntityCount() - entitiesToMove) > 0)
        {
            sourceProvince.removeFromEntities(entitiesToMove);
            destinationProvince.addToEntities(entitiesToMove);
            JOptionPane.showMessageDialog(null,"Einheiten erfolgreich verschoben, Kommandant " + getPlayerName() + ".");
        }

        else if ( (sourceProvince.getEntityCount() - entitiesToMove) <= 0)
        {
            JOptionPane.showMessageDialog(null,"Du hast nicht genuegend Einheiten, um die gewuenschte Anzahl von " + sourceProvince.getDisplayName() + " nach " + destinationProvince.getDisplayName() + " zu verschieben, Koehler.");
        }
    }

    /**
    Speichert ein gegebene Provinz als savedProvince ein, insofern dieser Platz nicht bereits belegt ist.
    Ist er das, so wird ueberprueft, ob eine neue, an savedProvince angrenzende Provinz angeklickt wurde.
    Ist dies der Fall, werden beide Provinzen an moveEntities uebergeben.
     */
    private void useProvincesToMove(Province givenProvince)
    {
        if(givenProvince.getOwner() != currentPlayer) {
            return;
        }
        if (savedProvince == null)
        {
            savedProvince = givenProvince;
            givenProvince.redrawProvince(2);
        }
        else if ((savedProvince != null) && (givenProvince != savedProvince))
        {
            if (givenProvince.isProvinceNear(savedProvince.getID()) == true)
            {
                moveEntities(savedProvince,givenProvince);
            }
            savedProvince.redrawProvince();
            savedProvince = null;
        } 
    }

    // Einheiten setzen

    int freeArmies = -1;

    private void actPlace()
    {
        if ( freeArmies == -1 ) {
            freeArmies = calculateArmies();
            players[currentPlayer].gotEntities(freeArmies);
        } else if ( freeArmies == 0 ) {
            modus.setBackColor(Color.white);
            modus.setForeColor(Color.black);
        } else {
            modus.setBackColor(Color.black);
            modus.setForeColor(Color.black);
        }
        for ( int i = 1; i <= (provinces.length - 1); i++) {
            if (provinces[i].hasClicked() == true && provinces[i].getOwner() == currentPlayer)
            {
                String toUseString = JOptionPane.showInputDialog(null, "Wieviele Einheiten willst du setzen? Du hast noch " + freeArmies + " freie Einheiten.");
                int armiesToUse = Utils.StringToInt(toUseString);
                if ( armiesToUse <= freeArmies )
                {
                    if ( armiesToUse > 0 )
                    {
                        provinces[i].addToEntities(armiesToUse);
                        freeArmies = freeArmies- armiesToUse;
                        JOptionPane.showMessageDialog(null,"Einheiten erfolgreich gesetzt, Kommandant " + getPlayerName() + ".");
                    }
                    if ( armiesToUse < 0 )
                    {
                        JOptionPane.showMessageDialog(null,"Willst du mich verarschen?");
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Nicht genuegend freie Einheiten.");
                }
            }
        }
    }

    private int calculateArmies()
    {
        int armiesToPlace;
        Province[] continentArray;
        boolean continentChecked = false;

        // 1. ArmyMinimum einbeziehen
        armiesToPlace = armyMinimum;

        // 2. Einheiten durch Provinzen einbeziehen
        armiesToPlace = armiesToPlace + Math.round(players[currentPlayer].getProvinceCount() / 3);

        // 3. Einheiten durch Kontinente

        // Kontinente durchgehen
        for ( int i = 1; i < continentBoni.length; i++ )
        {
            continentArray = giveContinentArray(i);
            // Provinzen des aktuellen Kontinents durchgehen
            for ( int p = 1; p >= continentArray.length; p++ )
            {
                // Pruefen, ob eine Provinz NICHT dem aktuellen Spieler gehoert
                if ( continentArray[p].getOwner() != currentPlayer )
                {
                    break;
                }
                // Wenn nicht, wird der Kontinent als gecheckt markiert
                else
                {
                    continentChecked = true;
                }
            }
            if ( continentChecked == true )
            {
                armiesToPlace = armiesToPlace + continentBoni[i];
                continentChecked = false;
            }
        }

        // 4. Einheiten durch Sterne
        if ( players[currentPlayer].getStars() > 0)
        {
            String toUseString;

            if ( players[currentPlayer].getStars() == 1 )
            {
                toUseString = JOptionPane.showInputDialog(null, "Wieviele Sterne willst du verwenden? \n Du besitzt noch 1 Stern.");
            }
            else
            {
                toUseString = JOptionPane.showInputDialog(null, "Wieviele Sterne willst du verwenden? \n Du besitzt noch " + players[currentPlayer].getStars() + " Sterne.");
            }

            int starsToUse = Utils.StringToInt(toUseString);

            if ( starsToUse <= players[currentPlayer].getStars() )
            {
                int[] starBoni = new int [] {1,2,4,7,10,13,17,21,25,30};
                if ( starsToUse > 0 && starsToUse < 11 )
                {
                    armiesToPlace = armiesToPlace + starBoni[starsToUse -1];
                }
                if ( starsToUse < 0 && starsToUse > 10 )
                {
                    JOptionPane.showMessageDialog(null,"Ungueltige Zahl. Bitte eine Zahl zwischen 0 und 10 eingeben");
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Du besitzt nicht die erforderliche Anzahl an Sternen! \n Verarschen kannst du jemand anderen.");
            }
        }
        return armiesToPlace;
    }

    private Province[] giveContinentArray(int cID)
    {
        Province[] savedProvinces = new Province[provinceCount +1];
        Province[] continentProvinces;
        int c = 0;
        for (int i = 1; i <= provinceCount; i++)
        {
            if (provinces[i].getContinentID() == cID)
            {
                savedProvinces[i] = provinces[i];
                c++;
            }
        }
        if ( c < 1 )
        {
            return null;
        }
        else
        {
            return Utils.cutArray(savedProvinces);
        }
    }

    /**
     * Gibt einem Spieler eine zufaellige Anzahl an Sternen zwischen 1 und 3.
     * 
     * @param player Der Spieler, dem die Sterne gegeben werden
     */

    protected void giveRandomStars (Player player)
    {
        double zufallszahl = Math.random();

        if ( zufallszahl <= 0.6 )
        {
            players[currentPlayer].addToStars(1);
        }
        else if ( zufallszahl <= 0.9 )
        {
            players[currentPlayer].addToStars(2);
        }
        else
        {
            players[currentPlayer].addToStars(3);
        }
    }
}