package zweiundvierzig;

 

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import javax.swing.JOptionPane;

/**
 * Write a description of class Colors here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Colours extends World implements ButtonEvent
{
    Bildbutton schwarz = new Bildbutton (new GreenfootImage("SchwarzeArmeeSkal.png"), this);
    Bildbutton blau = new Bildbutton (new GreenfootImage("BlaueArmeeSkal.png"), this);
    Bildbutton rot = new Bildbutton (new GreenfootImage("RoteArmeeSkal.png"), this);
    Bildbutton gelb = new Bildbutton (new GreenfootImage("GelbeArmeeSkal.png"), this);
    Bildbutton lila = new Bildbutton (new GreenfootImage("LilaArmeeSkal.png"), this);
    Bildbutton gruen = new Bildbutton (new GreenfootImage("GrueneArmeeSkal.png"), this);
    Button weiter = new Button ("Weiter", 16, this);
    int[] color = new int [5];
    String[] pn = new String [5];
    int x = 0;
    boolean possw = true; // schwarz
    boolean posbl = true; // blau
    boolean posgr = true; // gruen
    boolean posrt = true; // rot
    boolean posgb = true; // gelb
    boolean posli = true; // lila
    int sw = 1;
    int bl = 2;
    int gr = 3;
    int rt = 4;
    int gb = 5;
    int li = 6;
    
    Label header = new Label("Klicke auf eine Farbe, um einen Spieler hinzuzufuegen:",16);
    Button remove = new Button("Rueckgaengig",16,this);
    Label pl1 = new Label("",22);
    Label pl2 = new Label("",22);
    Label pl3 = new Label("",22);
    Label pl4 = new Label("",22);
    Label pl5 = new Label("",22);

    /**
     * Constructor for objects of class Colors.
     * 
     */
    public Colours(int x, int y, int z)
    {
        super(x, y, z);
        setBackground(Start_Load.backgroundImage);
        
        Bildbutton[] bList = new Bildbutton[] {schwarz,gelb,blau,gruen,rot,lila,weiter,remove};
        Label[] lList = new Label[] {header,pl1,pl2,pl3,pl4,pl5};
        
        addObject(header,200,15);
        
        addObject(schwarz, 80, 50);
        addObject ( blau, 240, 50);
        addObject ( gruen, 400, 50);
        addObject ( rot, 560, 50);
        addObject(gelb, 720, 50);
        addObject(lila, 880, 50);
        addObject(remove,300,160);
        addObject (weiter, 510,160);
        
        addObject(pl1,355,360);
        addObject(pl2,355,380);
        addObject(pl3,355,400);
        addObject(pl4,355,420);
        addObject(pl5,355,440);
        
        //schwarz.setForeColor(Color.black);
        //gelb.setForeColor(Color.yellow);
        //blau.setForeColor(Color.blue);
        //gruen.setForeColor(Color.green);
        //rot.setForeColor(Color.red);
        //lila.setForeColor(new Color(161,70,255));
        
        pl1.setForeColor(Color.black);
        pl2.setForeColor(Color.black);
        pl3.setForeColor(Color.black);
        pl4.setForeColor(Color.black);
        pl5.setForeColor(Color.black);
        
        for(int i = 0; i < bList.length; i++) {
            bList[i].setSize(164,100);
        }
        for(int i = 0; i < lList.length; i++) {
            lList[i].setBackColor(new Color(0,0,0,0));
        }
        
        redraw();
    }
    // ueberprueft, ob ein Farbbutton geklickt wurde
    //ueberprueft, ob die Farbe noch nicht ausgewaehlt wurde
    // Wenn alle Bedingungen erfuellt wurden:
    // Setzt die aktuelle Stelle des Farbarrays gleich der Zahl der aktuellen Farbe
    // erhoeht die Variable zum Durchzaehlen um eins
    // verhindert durch falschsetzten einer Variable die Wiederauswahl einer Farbe
    // wenn Weiter geklickt wurde und x groeßer 1 ist wird eine neue Welt Map erzeugt, 
    //der per Konstruktor die Daten der Colorklasse uebertragen werden, dannach wird die Map die aktive Welt
    public void buttonClicked (Bildbutton b)
    {
        if (x < 5) {
            if (b == schwarz && possw == true) {
                color[x] = sw;
                pn[x]=getName(x,"Schwarz");
                if(pn[x].length() > 0) {
                    x+=1;
                    possw = false;
                }
            } else if (b == blau && posbl == true) {
                color[x] = bl;
                pn[x]=getName(x,"Blau");
                if(pn[x].length() > 0) {
                    x+=1;
                    posbl = false;
                }
            } else if (b == gruen && posgr == true )  {
                color[x] = gr;
                pn[x]=getName(x,"Gruen");
                if(pn[x].length() > 0) {
                    x+=1;
                    posgr = false;
                }
            } else if (b == rot && posrt == true) {
                color[x] = rt;
                pn[x]=getName(x,"Rot");
                if(pn[x].length() > 0) {
                    x+=1;
                    posrt = false;
                }
            } else if (b == gelb && posgb == true) {
                color [x] = gb;
                pn[x]=getName(x,"Gelb");
                if(pn[x].length() > 0) {
                    x+=1;
                    posgb = false;
                }
            } else if (b == lila && posli == true) {
                color [x] = li;
                pn[x]=getName(x,"Lila");
                if(pn[x].length() > 0) {
                    x+=1;
                    posli = false;
                }
            }
        }
        if(b == remove && x > 0) {
            x -= 1;
            switch(color[x]) {
                case 1: possw = true; break;
                case 2: posbl = true; break;
                case 3: posgr = true; break;
                case 4: posrt = true; break;
                case 5: posgb = true; break;
                case 6: posli = true; break;
            }
        }
        if ( b == weiter && x > 2 )
        {
            String[] newpn = new String[x];
            int[] newcolor = new int[x];
            for (int i = 0; i< x; i++)
            {
                newpn[i] = pn[i];
                newcolor[i] = color[i];
            }
            World m = new Map(newcolor,x,newpn);
            Greenfoot.setWorld(m);
        }
        redraw();
    }
    
    private Color getC(boolean pos) {
        if(x >= 5) {
            return Color.gray;
        }
        return (pos) ? Color.lightGray : Color.gray;
    }
    
    /**
        Passt alle adaptiven Parameter automatisch an.
    */
    private void redraw() {
        schwarz.setBackColor(getC(possw));
        blau.setBackColor(getC(posbl));
        gruen.setBackColor(getC(posgr));
        rot.setBackColor(getC(posrt));
        lila.setBackColor(getC(posli));
        gelb.setBackColor(getC(posgb));
        remove.setBackColor((x > 0) ? Color.black : Color.gray);
        weiter.setBackColor((x > 2) ? Color.black : Color.gray);
        pl1.setText("");
        pl2.setText("");
        pl3.setText("");
        pl4.setText("");
        pl5.setText("");
        switch(x) {
            case 5:
                pl5.setText(pn[4]);
            case 4:
                pl4.setText(pn[3]);
            case 3:
                pl3.setText(pn[2]);
            case 2:
                pl2.setText(pn[1]);
            case 1:
                pl1.setText(pn[0]);
        }
    }
    
    private String getName(int id, String col) {
        String name = JOptionPane.showInputDialog(null, "Wie soll Spieler Nr. "+(id+1)+" mit der Farbe "+col+" heißen?");
        if(name == null) {
            name = "";
        }
        return name;
    }
}


