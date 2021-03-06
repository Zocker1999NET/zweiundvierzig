# Architekturplan Zweiundvierzig

**Letztes Update: 15.06.2016** (TT.MM.JJJJ / DD.MM.YYYY)

[Hier die offizielle Version vom Master-Branch sehen](https://github.com/HGE-IT-Course-2016/zweiundvierzig/blob/master/planung/architektur.md)

[Hier zur übersichtlichen Funktionsliste auf dem aktuellen Branch](funktionsliste.md)

Dieser Plan wird verfasst und regelmäßig gepflegt durch *Felix Stupp*. Das Alter der vorliegenden Version ist am Datum am Dateianfang zu erkennen.

## Erklärung zur Architektur

Hier werden alle Klassen mit deren öffentliche Methoden (**public** und **protected**) und den vorgesehenen Eigenschaften (nur **private**) festgehalten.

**Alle Fragen zur Architektur oder auch Änderungsvorschläge gerne an mich weitergeben.** Ich werde dies dann behandeln und gegebenfalls auch Änderungen an der Architektur durchführen.

### Wichtige Infos

- **Bitte kommentiert all eure Methoden nach dem Java-Standard, damit diese in der automatisch generierten Dokumentation auch mit einer kurzen Erklärung auftauchen.**
- **Die Provinz-ID und somit auch die Indexe der Arrays beginnen dafür erst bei 1!**

### Hinweise

- Die englischen Begriffe *World* und *Actor* stehen für die gegebenen Oberklassen von Greenfoot.
- Alle Methoden sind meist als **public** zu sehen und werden hauptsächlich von anderen Klassen aufgerufen.
- Die Kategorie **Privat Eigenschaften** wird nur aufgelistet, wenn die Klasse von mehreren Autoren geschrieben wird/wurde oder die Klasse noch geschrieben werden muss.

### Abkürzungen

- **GUI** ([Graphical User Interface](https://de.wikipedia.org/wiki/Grafische_Benutzeroberfl%C3%A4che)): Beschreibt die Möglichkeit, durch welche ein Benutzer gewöhnlicherweise mit Programmen interagieren kann.

### Tipps

- Falls euere Aufgabe die Umsetzung einer Methode ist, die hier bereits beschrieben wird, müsst ihr nicht diesselben Parameterbezeichner verwenden, wie sie hier verwendet wurden. Falls aus diesem Bezeichner jedoch nicht mehr die Bedeutung des Parameters ausgeht, muss dies in einem Java-Documentation Kommentar erklärt werden.
- Alle Klassen, die als Actor agieren und **nur** in der auf der *GeneralMap* beziehungsweise der Unterklassen dieser Klasse eingesetzt werden, müssen teilweise mit dieser Welt interagieren. Um die aktuelle Welt sofort im richtigen Typ zu bekommen, damit auf unsere Funktionen zugegriffen werden können, kann euch folgender Code Snippet helfen. Einfach in die Klassen einfügen einfügen und **getWorld** () wird euch besser helfen können.
``` java
@Override public GeneralMap getWorld() {
	return (GeneralMap) super.getWorld();
}
```
- Arbeitet bitte, wenn möglich, mit der *Utils*-Klasse, diese kann helfen den Code übersichtlicher und kürzer zu gestalten, da häufige und allgemein umsetzbare Aufgaben über diese einheitlich abgearbeitet werden sollen. Das Debuggen wird somit auch einfacher, sowohl für mich als auch für euch selbst.
- Ihr könnt auch ab und zu in die Dokumentationen der offiziellen Java-Bibliotheken schauen, falls ihr denkt, dass es bereits eine Methode geben sollte für den Vorgang, den ihr sonst selbst programmieren müsstet.

### Color-Zuweisung

An verschiedenen Stellen (wie beispielsweise beim Erstellen eines *Player*-Objektes) benötigt man eine Zahl als Farbwert, die einem Spieler eine Farbe zuweisen können. Auf folgende Zuordnung haben wir uns geeinigt:

0. schwarz
0. blau
0. grün
0. rot
0. gelb
0. lila

---

## Klassenverzeichnis

### Worlds

- World für den Spielstart
- *GeneralMap*
- Alle spezifischen Maps
	- *Map_World* (gesamte Weltkarte)

### Actors

- *Province*
- *Player*

- *GUI_Interface*
	- *Label*
	- *Button*
	- *Dice*

- *DicesList*
	- *OffenderDices*
	- *DefenderDices*

### Sonstige

- *Utils*

---

## Spielstart

Diese *World* hat die Aufgabe, zum einen den Titelbildschirm (mit einem schönen Hintergrundbild, welches später noch optional gezeichnet wird) des Spiels anzeigt, daraufhin ein Menü mit den Möglichkeiten, ein Spiel zu starten oder zu laden (der Button für das Laden eines Speicherstand's bleibt vorerst ohne Funktion, auch optional geplant). Beim Erstellen eines neuen Spiels soll man sowohl die Möglichkeit bekommen, die Eigenschaften der Spieler und die der Map auszuwählen.

Dies kann entweder in verschiedenen *World*'s gelöst werden, als auch in einer einzelnen. Dies bleibt euch zusammen mit dem Design diesem Menü überlassen.

Ich schlage euch vor, die bereits von mir erstellen Steuerelemente *Label* und *Button* zu verwenden. Diese sollten die Arbeit für euch wesentlich erleichtern, genauso wie das Schreiben von neuen Steuerelementen, falls ihr bemerken sollte, dass ihr diese öfters braucht als einmal. Diese sollten dann auch selbst von der Oberklasse *GUI_Interface* erben.

---

## GeneralMap

Alle spezifischen Maps erben von dieser Oberklasse.
Diese Klasse ist für Greenfoot die aktive *World* im laufenden Spiel und auch für die Anzeigen links/rechts/unten verantwortlich.
Die erbenden Unterklassen legen dann das Hintergrundbild, die Provinzen, und weitere spezifische Eigenschaften der Karten dar.
Diese Oberklasse kümmert sich dabei um die Anzeigen, die Spielmechanik und die Speicherung der Spieler und Provinzen.
Auch, wenn diese Klasse einen Konstruktor besitzt, ist dieser nur für die Unterklassen, also für die spezifischen Maps, als Vereinfachung gedacht.

### Konstruktorparameter

Als ersten Parameter wird die *GeneralMap* den Dateinamen des Hintergrundbilds der Map als *String* erwarten, welches automatisch von der jeweiligen Map-Klasse übergeben wird. Die folgenden Parameter werden von allen Map-Klassen, inklusive der *GeneralMap*, erwartet. Die Unterklassen sollen hierbei alles 1:1 weitergeben.

1. Spielerliste mit den Namen als *String[]*
2. Liste mit den jeweiligen "Farben" der Spieler als *int[]*

#### Hintergrundbild

Dies soll von den Konstruktoren der spezifischen Maps weitergegeben werden. Der Dateiname ist dort jeweils angepasst und direkt im Code als Konstante angegebenen. Diese Oberklasse übernimmt dann alle Aktionen, um es im Hintergrund der GUI Grafik anzuzeigen.

#### Spielerliste und "Farben"-Liste

Die beiden Arrays sollen Grundinformationen zu den Spielern weitergeben. Weitere Informationen zu den "Farben" sind bei der *Player*-Klasse zu finden.

### Private Eigenschaften

- Spielerliste (*Player[]*, der Index entspricht der Spieler ID, *anfangend bei 0*)
- Provinzliste (*Province[]*, als **protected** definiert, der Index entspricht der Provinz ID, *anfangend bei 1*)

#### Spielerliste

Diese Liste soll alle *Player*-Objekte für die Spieler enthalten, welche dann auch als Actor links von der Karte zu sehen sein sollten.

#### Provinzliste

Die Provinzliste enthält alle Provinzobjekte, die auf der Karte zu sehen sind. Diese wird erst vom Konstruktor der spezifischen Maps gefüllt.

### Protected Methoden

- *void* **initProvinces** ()

#### initProvinces()

Diese Methode soll das Hinzufügen der Provinzen an die richtige Position auf der *World* übernehmen (nicht ins Array). Diese Methode sucht sich alle Provinzen aus dem Array *Province[]* **provinces**.

### Public Methoden

- *int* **getPlayerCount** ()
- *String* **getPlayerName** ()
- *String* **getPlayerName** ( *int* playerID )
- *int* **getPlayerStars** ()

- *int* **getProvinceOwner** ( *int* provinceID )
- *int[]* **getProvinceOwners** ()
- *int* **getProvinceEntityCount** ( *int* playerID )

#### getPlayerCount()

Gibt die Anzahl der im Spiel vorhandenen Spieler aus.

#### getPlayerName()

Gibt den Namen des gegebenen Spielers aus. Muss Fehler durch falschen Indexen ausweichen. Falls kein Wert oder ein ungültiger übergeben wird, soll stattdessen der Name des aktuellen Spielers zurückgegeben werden.

#### getPlayerStars()

Diese Funktion soll die Anzahl der Sterne des aktuellen Spielers zurückgeben.

#### getProvinceOwner()

Gibt die Spieler ID von dem Spieler aus, dem die Provinz gehört. Bei falschen Indexen muss eine -1 (kein Spieler) zurückgegeben werden.

#### getProvinceOwners()

Gibt ein Array mit allen Provinzen (deren ID als Indexen) und den Spieler IDs als Wert aus.

#### getProvinceEntityCount()

Gibt die Anzahl der Einheiten von einer bestimmten Provinz zurück. Bei falschen Indexen muss eine 0 zurückgegeben werden.

---

## Spezifische Maps
*extends GeneralMap*

Diese Unterklassen enthalten Informationen wie das Hintergrundbild der jeweiligen Map als auch die Anordnung der Provinzen auf der Map.

### Konstruktorparameter

Diese Konstruktorparameter müssen nur zu dieser weitergeleitet werden. Für weitere Informationen bitte bei der *GeneralMap* nachschlagen.

---

## Province
*extends Actor*

Speichert Informationen zu den einzelnen Provinzen ab und stellt diese später auch als *Actor* dar.

### Konstruktorparameter

0. Provinznummer als *int*
0. Kontinentnummer als *int*
0. X-Position auf der Karte als *int*
0. Y-Position auf der Karte als *int*
0. Anzeigename als *String*
0. Sterne als *int*
0. Angrenzende Provinzen als *int[]* (als als *boolean[]* gespeichert)

#### Provinz-ID

Stellt die ID der Provinz dar.

#### Kontinent-ID

Stellt die ID des Kontinentes dar.

#### Position

Diese zwei Werte legen fest, wo die sichtbaren Eigenschaften der Provinz angezeigt werden sollen.
Sind nach dem Erstellen der Provinz nicht mehr abrufbar.

#### Anzeigename

Dies ist der Name, der auf der Karte und bei Events im Zusammenhang mit dieser Provinz angezeigt wird.

#### Sterne

Dieser Wert wird für die zufällige Verteilung von Einheiten benötigt. Er ist fest für eine Provinz festgeschrieben.

#### Angrenzende Provinzen

Dies ist ein Array von allen Provinzen, die es gibt (Provinznummer als Index), diese jeweils mit einem *boolean*-Wert, der festlegt, ob ein Kampf oder ein Weitergeben von Einheiten möglich ist.
```java
boolean[] nearProvinces;
```
Dem Konstruktor wird stattdessen ein *int[]* mit allen angrenzenden Provinzen als Werte übergeben werden, dieses wird dann automatisch konvertiert.

### Private Eigenschaften

- Provinznummer
- Kontinentnummer
- X/Y-Position auf der Karte
- Anzeigename
- Sterne
- Angrenzende Provinzen

- Besitzer
- Einheitenanzahl

#### Besitzer

Diese Variable speichert den aktuellen Besitzer.

Über die Methode **int getOwner()** bekommt ihr den aktuellen Besitzer zurück (-1 = keiner, 0 = Spieler 1, ...).

Die Methode **boolean setOwner(int)** speichert einen neuen Besitzer ab. Sie gibt den Erfolg des Setzens zurück, falls also die Zahl kleiner -1 oder größer gleich als die Spieleranzahl sein sollte, wird die Änderung nicht durchgeführt und es wird **false** zurückgegeben.

#### Einheitenanzahl

Diese Eigenschaft speichert, wie viele Einheiten auf diesem Feld stehen (natürlich welche, die dem Besitzer gehören).

### Public Methoden

- *int* **getXPos** ()
- *int* **getYPos** ()
- *int* **getID** ()
- *int* **getContinentID** ()
- *String* **getDisplayName** ()
- *int* **getStars** ()

- *boolean* **isProvinceNear** ( *int* provinceID )

- *int* **getOwner** ()
- *boolean* **setOwner** ( *int* playerID )

- *int* **getEntityCount** ()
- *int* **addToEntities** ( *int* entityCountToAdd )
- *int* **removeFromEntities** ( *int* entityCountToRemove )
- *int* **setEntityCount** ( *int* newEntityCount)

- *boolean* **hasClicked** ()

- *void* **redrawProvince** ()

#### getXPos()

Gibt die in der Provinz hinterlegte X-Position zurück.

#### getYPos()

Gibt die in der Provinz hinterlegte Y-Position zurück.

#### getID()

Gibt die Provinz-ID dieser Provinz zurück.

#### getContinentID()

Gibt die Kontinent-ID dieser Provinz zurück.

#### getDisplayName()

Gibt den Anzeigenamen dieser Provinz zurück.

#### getStars()

Gibt die hinterlegte Anzahl an Sternen dieser Provinz zurück.

#### isProvinceNear()

Gibt zurück, ob die angebenene Provinz mit dieser Provinz benachbart ist, umn beispielsweise Einheiten verschieben zu können.

#### getOwner()

Gibt die Spieler-ID des aktuellen Besitzer der Provinz als *int* zurück. **-1** bedeutet, dass diese Provinz aktuell keinen Besitzer hat.

#### setOwner()

Legt einen neuen Besitzer für diese Provinz fest. Bei Erfolg gibt die Methode **true** zurück. Sollte die ID für keinen Spieler stehen, wird die Methode **false** zurückgeben und nichts an dem gespeicherten Wert ändern.

#### getEntityCount()

Gibt die aktuelle Einheitenanzahl auf dieser Provinz zurück.

#### addToEntities()

Addiert die gegebene Anzahl der Einheiten auf die gespeicherte Anzahl drauf, speichert den neuen Wert ab und gibt diesen zurück.

#### removeFromEntities()

Subtrahiert die gegebene Anzahl der Einheiten von die gespeicherte Anzahl ab, speichert den neuen Wert ab und gibt diesen zurück. Bei einem neuen Wert von unter 0 wird dies nicht abspeichert, aber dennoch wird der noch aktuellen Wert zurückgeben.

#### setEntityCount()

Setzt einen neuen festen Wert für die Einheitenanzahl fest und gibt diesen wieder zurück. Bei gegebenen Werten unter 0 wird dies nicht abspeichert, aber dennoch wird der noch aktuellen Wert zurückgeben.

#### hasClicked()

Gibt zurück, ob seid dem letzten Aufruf dieser Methode diese Provinz von der Maus angeklickt wurde.

#### redrawProvince()

Wird von der Karte oder von internen Methoden aufgerurfen, um alle sichtbaren Eigenschaften erneut zu zeichnen.

---

## Player
*extends Actor*

Stellt die Spieler da, speichert Informationen zu diesen ab.

### Konstruktorparameter

0. Spieler-ID als *int*
0. Anzeigename als *String*
0. "Farbe" als *int*

#### Spielernummer

Wird bei der Welt intern benötigt und stellt die Position im Menü und in den Arrays dar.

#### Anzeigename

Ist der lesbare Name des Spielers, damit nicht nur "Spieler 1", ... usw. zu sehen ist.

#### "Farbe"

Dies soll die "Farbe" eines Spielers festlegen. *Falls euch ein besserer Name für diese Eigenschaft einfällt, bitte mir weitergeben.*

### Private Eigenschaften

- Spielernummer
- Anzeigename
- "Farbe"

- Sternanzahl
- Statistiken

#### Sternanzahl

Die Anzahl der Sterne, die ein Spieler besitzt und gegebenenfalls dann in Einheiten umtauschen kann.

#### Statistik

Die Statistik soll Events festhalten, um nach einem Spiel verschiedene Werte einsehen zu können. Diese werden von der Welt durch spezielle Funktionen erhöht. Die Reihenfolge, in der die Statistiken angegeben werden, ist wie folgt festgelegt:

1. Eroberte Provinzen
2. Verlorene Provinzen
3. Einflussmaximum (maximale Provinzenanzahl, nach einem Zug zu überprüfen)
4. Bekommene Einheiten
5. Verlorene Einheiten
6. Maximale Einheitenanzahl (nach einem Zug zu überprüfen)

### Public Methoden

- *int* **getID** ()
- *String* **getDisplayName** ()

- *int* **getStars** ()
- *int* **addToStars** ( *int* starsToAdd )
- *int* **removeFromStars** ( *int* starsToRemove )
- *int* **setStars** ( *int* newStarsCount )
- *boolean* **canStarsRemoved** ( *int* requiredStarsCount )

- *int[]* **getStatistics** ()
	- *void* **gotProvince** ()
	- *void* **lostProvince** ()
	- *void* **gotEntities** ( *int* addedEntities )
	- *void* **lostEntity** ()

- *boolean[]* **getMyProvinces** ()
- *int* **getProvinceCount** ()
- *void* **redrawPlayer** ()

#### getID()

Gibt die Spieler-ID dieses Spielers zurück.

#### getDisplayName()

Gibt den Anzeigenamen des Spielers zurück.

#### getStars()

Gibt die aktuelle Anzahl der Sterne, die der Spieler besitzt, zurück.

#### addToStars()

Addiert die gegebene Anzahl der Sterne auf die gespeicherte Anzahl drauf, speichert den neuen Wert ab und gibt diesen zurück.

#### removeFromStars()

Subtrahiert die gegebene Anzahl der Sterne von die gespeicherte Anzahl ab, speichert den neuen Wert ab und gibt diesen zurück. Bei einem neuen Wert von unter 0 wird dies nicht abspeichert, aber dennoch wird der noch aktuellen Wert zurückgeben.

#### setStars()

Setzt einen neuen festen Wert für die Sternenanzahl fest und gibt diesen wieder zurück. Bei gegebenen Werten unter 0 wird dies nicht abspeichert, aber dennoch wird der noch aktuellen Wert zurückgeben.

#### canStarsRemoved()

Prüft, ob die gegebenene Anzahl an Sternen von dem Spieler abgezogen werden könnte. Falls ja, wird **true** zurückgegebenen, sonst **false**

#### getStatistics()

Gibt in derselben Reihenfolge wie für die Statistik angegeben die aktuelllen Werte zurück als *int[]*-Array

#### gotProvince()

Wird von der Welt aufgerufen, sobald dieser Spieler eine Provinz erobert hat. Diese Methode erhöht dann den entsprechenden Wert in der Statistik um 1.

#### lostProvinze()

Wird von der Welt aufgerufen, sobald dieser Spieler eine Provinz verloren hat. Diese Methode erhöht dann den entsprechenden Wert in der Statistik um 1.

#### gotEntities()

Wird von der Welt aufgerufen, sobald dieser Spieler Einheiten dazubekommt. Diese Methode erhöht dann den entsprechenden Wert in der Statistik um die gegebenene Anzahl.

#### lostEntity()

Wird von der Welt aufgerufen, sobald dieser Spieler im Kampf eine Einheit verliert. Diese Methode erhöht dann den entsprechenden Wert in der Statistik um 1.

#### getMyProvinces()

Diese Methode hat die Aufgabe, eine Liste aller Provinzen zurückzugeben, wobei jede Provinz mit einem *boolean*-Wert gesagt bekommt, ob sie dem Spieler gehört oder nicht.

Diese Methode muss zwingend mit der Welt interagieren, um diese Informationen zu bekommen.

#### getProvinceCount()

Gibt die Anzahl der Provinzen, die der Spieler hat, zurück. Gut für die Statistik und die Anzeigen.

#### redrawPlayer()

Erzwingt das erneute Zeichnen des Player Objekts, um alle sichtbaren Eigenschaften erneut zu zeichnen.

---

## DicesList
*extends Actor*

Erstellt die gegebene Anzahl an Würfeln nebeneinander und bietet die gemeinsame Verwaltung dieser. Diese Klasse ist nur als *abstract* Oberklasse zu *OffenderDices* & *DefenderDices* gedacht und ist daher selbst nicht verwendbar, dennoch sich hier alle Methoden, die ihr braucht, definiert. Die Unterklassen legen nur die Farbe der Würfel und die maximale Anzahl fest.

### Verwendung

Nachdem ihr euch für eine Unterklasse entschieden habt, erstellt ihr eine neue Instanz von dieser und übergibt ihr die Anzahl der Würfel, die ihr darstellen wollt (diese Zahl kann größer als die erlaubte Anzahl an Würfel sein, dies wird **automatisch korrigiert**, beim Angreifer auf 3, beim Verteidiger auf 2). Bei zu kleinen Zahlen wird auf 1 korrigiert.
```java
DicesList offenders = new OffenderDices(5); // Wird korrigiert auf '3'
DicesList defenders = new DefenderDices(-3); // Wird korrigiert auf '1'
```
Die nun erstellte Instanz solltet ihr einer Welt hinzufügen, um die Würfel sehen zu können. Achtet dabei auf die Position, die ihr der Welt übergibt.
```java
addObject(offenders,200,800);
```
Nun könnt ihr mit den Methoden **getNumbers** und **roll** alle Würfel parallel steuern.
```java
int[] zahlenAng = offenders.roll();
int[] zahlenVer = defenders.getNumbers();
if(zahlenAng[1] > zahlenVer[1]) {
	// Angreifer gewinnt
} else {
	// Verteidiger gewinnt
}
```
Solltet ihr die Würfel nicht mehr brauchen und ihr möchtet sie entfernen, reicht ein simpler Aufruf von **removeAll**.
```java
offenders.removeAll();
offenders = null; // muss nicht sein, gehört aber zum sauberen Programmieren dazu
```

### Konstruktorparameter

1. Anzahl der Würfel als *int* (Weiterleitung des Konstruktors von den Unterklassen)
2. Maximale Anzahl als *int* (fester Wert der Unterklasse)
3. Hintergundfarbe als *java.awt.Color* (fester Wert der Unterklasse)
4. Vordergrundfarbe als *java.awt.Color* (fester Wert der Unterklasse)

### Protected Methoden

- *void* **addedToWorld** ( *World* world )

#### addedToWorld()

Diese Methode wird von Greenfoot selbst aufgerufen, sobald dieser Actor einer Welt hinzugefügt wurde und kümmert sich folgend darum, seine eigenen Würfel auch der Welt hinzuzufügen an derselben Position.

### Public Methoden

- *int[]* **getNumbers** ()
- *int[]* **roll** ()

- *void* **removeAll** ()

#### getNumbers()

Gibt die Augenzahlen aller Würfel in sortierter Reihenfolge (absteigend) aus.

#### roll()

Würfelt alle Würfel erneut und gibt die neuen Augenzahlen in sortierter Reihenfolge (absteigend) aus.

#### removeAll()

Entfernt alle Würfel aus ihrer Welt und löscht anschließend die Liste. Die Instanz ist danach nicht mehr zu verwenden.

## OffendersDices
*extends DicesList*

Erstellt eine Liste von Würfeln mit roter Hintergrundfarbe. Es sind maximal 3 Würfel erlaubt. Zur Verwendung, siehe *DicesList*.

### Konstruktorparamter

1. Anzahl der Würfel als *int* (wird korrigiert; siehe *DicesList*-Konstruktor)

## DefenderDices
*extends DicesList*

Erstellt eine Liste von Würfeln mit schwarzer Hintergrundfarbe. Es sind maximal 2 Würfel erlaubt. Zur Verwendung, siehe *DicesList*.

### Konstruktorparamter

1. Anzahl der Würfel als *int* (wird korrigiert; siehe *DicesList*-Konstruktor)

---

## GUI_Interface
*extends Actor*

Die Oberklasse für alle Interface-Objekte wie Buttons und Labels. Diese Klasse ist als *abstract* definiert. Diese Klasse besitzt als *abstract* definierte Methoden, welche zwingend von den erbendenen Klassen ersetzt werden muss.

### Protected Eigenschaften

- *int* **sx**
- *int* **sy**

#### sx

Gibt die Breite des Objektes an (Größe in X-Richtung, **S**ize**X**). Nur für Methoden der erbenden Objekte selbst gedacht.

#### sy

Gibt die Höhe des Objektes an (Größe in Y-Richtung, **S**ize**Y**). Nur für Methoden der  erbenden Objekte selbst gedacht.

### Public Methoden

- *int* **getWidth** ()
- *int* **getHeight** ()
- *void* **setSize** ( *int* width , *int* height )

- *java.awt.Color* **getBackColor** ()
- *boolean* **setBackColor** ( *java.awt.Color* newBackColor)
- *java.awt.Color* **getForeColor** ()
- *boolean* **setForeColor** ( *java.awt.Color* newForeColor)

#### getWidth()

Gibt die Breite des Objektes zurück.

#### getHeight()

Gibt die Höhe des Objektes zurück.

#### setSize()

Ändert die Größe des Objektes. Eventuell wird das Objekt diese Größenänderung nicht übernehmen wollen (siehe *autoSize* Eigenschaften bei diesen, falls vorhanden).

#### getBackColor()

Gibt die aktuelle Hintergrundfarbe des Objektes zurück.

#### setBackColor()

Legt eine neue Hintergrundfarbe für dieses Objekt fest.

#### getForeColor()

Gibt die aktuelle Vordergrundfarbe (meist Textfarbe) des Objektes zurück.

#### setForeColor()

Legt eine neue Vordergrundfarbe (meist Textfarbe) für dieses Objekt fest.

### Abstract Public Methoden

- *void* redraw()

#### redraw()

Durch diese Methode soll die erneute Zeichnung des Objektes verlangen können. Viele der **set**-Methoden rufen daher bei einer erfolgreichen Änderung diese Methode auf.

---

## Label
*extends GUI_Interface*

Zeigt einen Text auf dem Bildschirm an. Zuvor wurde dieses Objekt "Text" genannt, "Label" ist der fachlichere Ausdruck dafür.

### Konstruktorparameter

0. Anzeigetext als *String*
0. Textgröße als *int*

#### Anzeigetext

Gibt den Text an, den das Label darstellen soll.

#### Textgröße

Gibt die Textgröße an, in der das Label den Anzeigetext darstellen soll.

### Private Eigenschaften

- Anzeigetext
- Textgröße

- Automatische Größenanpassung

#### Automatische Größenanpassung

Diese Eigenschaft des Labels legt fest, ob es seine Größe dynamisch an den darzustellenden Text automatisch anpassen soll. Dies kann euch die Arbeit mit diesem erleichtern, kann aber auch zu Problemen bei der Darstellung mehrerer Objekte nebeneinander erschweren, da diese sich eventuell überlappen könnten.

### Public Methoden

- *boolean* **getAutoSize** ()
- *void* **setAutoSize** ( *boolean* newValue )
- *int* **getTextSize** ()
- *boolean* **setTextSize** ( *int* newSize )
- *String* **getText** ()
- *boolean* **setText** ( *String* newText )

- *void* **redraw** ()

#### getAutoSize()

Gibt an, ob das Label seine Größe automatisch an den Inhalt anpasst.

#### setAutoSize()

Legt fest, ob das Label seine Größe automatisch an den Inhalt anpassen soll. Falls die Eigenschaft damit aktiviert werden sollte, erfolgt automatisch ein Aufruf der **redraw**-Methode.

#### getTextSize()

Gibt die aktuelle Textgröße zurück.

#### setTextSize()

Legt eine neue Textgröße fest.

#### getText()

Gibt den aktuellen Anzeigetext zurück.

#### setText()

Legt einen neuen Anzeigetext zurück.

#### redraw()

Erneuert die Darstellung des Labels mit seinem Anzeigetext auf der Welt. Hiermit wird gegebenfalls auch die Größe des Labels automatisch angepasst.

---

## Button
*extends GUI_Interface*

Stellt einen Button mit einem Text dar. Dieses Objekt kann dazu ein Event auslösen, sobald darauf geklickt wurde.

### Konstruktorparameter

Hier gibt es mehrere Möglichkeiten, eine Instanz der Klasse *Button* zu erstellen:

Methode 1:
1. Anzeigetext als *String*
2. Textgröße als *int*

Methode 2:
1. EventHandler als *ButtonEvent*

Methode 3:
1. Anzeigetext als *String*
2. Textgröße als *int*
3. EventHandler als *ButtonEvent*

#### Anzeigetext

Gibt den Text an, den das Label darstellen soll.

#### Textgröße

Gibt die Textgröße an, in der das Label den Anzeigetext darstellen soll.

#### EventHandler

Hierfür muss die Instanz einer Klasse übergeben werden, welche das Interface *ButtonEvent* implementiert und damit eine Funktion namens *void* **buttonClicked** ( *Button* button ) besitzt. Folgend ein Beispiel für eine Klassendefinition, die eine Methode mit diesem Namen besitzt, die dann von dem Button aufgerufen wird, sobald dieser angeklickt wurde.

```java
public class TestClass implements ButtonEvent {
	Button button1 = new Button("Button 1",10,self);
	Button button1 = new Button("Button 2",10,self);
	public void buttonClicked(Button b) {
		// Hier steht nun, was passieren soll, wenn IRGENDEIN Button angeklickt wird, der eine Instanz von dieser Klasse als EventHandler zugewiesen bekommen hat.
		if(b == button1) {
			// mein erster Button
		} else if(b == button2) {
			// mein zweiter Button
		}
	}
}
```

### Private Eigenschaften

- Anzeigetext
- Textgröße
- EventHandler

- Automatische Größenanpassung

#### Automatische Größenanpassung

Diese Eigenschaft des Buttons legt fest, ob es seine Größe dynamisch an den darzustellenden Text automatisch anpassen soll. Dies kann euch die Arbeit mit diesem erleichtern, kann aber auch zu Problemen bei der Darstellung mehrerer Objekte nebeneinander erschweren, da diese sich eventuell überlappen könnten.

### Public Methoden

- *boolean* **getAutoSize** ()
- *void* **setAutoSize** ( *boolean* newValue )
- *int* **getTextSize** ()
- *boolean* **setTextSize** ( *int* newSize )
- *String* **getText** ()
- *boolean* **setText** ( *String* newText )

- *ButtonEvent* **getHandler** ()
- *void* **setHandler** ( *ButtonEvent* newEventHandler )
- *void* **removeHandler** ()

- *void* **redraw** ()

#### getAutoSize()

Gibt an, ob der Button seine Größe automatisch an den Inhalt anpasst.

#### setAutoSize()

Legt fest, ob der Button seine Größe automatisch an den Inhalt anpassen soll. Falls die Eigenschaft damit aktiviert werden sollte, erfolgt automatisch ein Aufruf der **redraw**-Methode.

#### getTextSize()

Gibt die aktuelle Textgröße zurück.

#### setTextSize()

Legt eine neue Textgröße fest.

#### getText()

Gibt den aktuellen Anzeigetext zurück.

#### setText()

Legt einen neuen Anzeigetext zurück.

#### getHandler()

Gibt den aktuellen EventHandler des Buttons zurück. Falls keiner vorhanden ist, wird **null** zurückgegeben.

#### setHandler()

Legt einen neuen EventHandler für den Button fest. Der alte EventHandler wird damit überschrieben.

#### removeHandler()

Deaktiviert den aktuellen EventHandler, damit keine Events mehr ausgelöst werden können.

#### redraw()

Erneuert die Darstellung des Buttons mit seinem Anzeigetext auf der Welt. Hiermit wird gegebenfalls auch die Größe des Buttons automatisch angepasst.

---

## Dice
*extends GUI_Interface*

### Konstruktorparameter

Methode 1: **keine Parameter**

Methode 2:
1. Startwert für die gespeicherte Augenzahl als *int*

#### Startwert

Dieser Wert wird im Voraus beim Dice hinterlegt, damit er diese direkt anzeigen kann. Wird dieser Wert nicht angegeben, wird als Augenzahl **0** hinterlegt, stehend für: noch nicht gewürfelt.

### Private Eigenschaften

- Augenzahl

#### Augenzahl

Der Wert, der beim letzten Würfeln gewürfelt wurde. Dieser Wert wird vom Würfel auch visuell dargestellt.

### Public Methoden

- *int* **getNumber** ()

- *void* **setSizeAsSquare** ( *int* length )

- *int* **roll** ()

- *void* **redraw** ()

#### getNumber()

Gibt die aktuell gespeicherte und somit auch visuell sichtbare Augenzahl zurück.

#### setSizeAsSquare()

Legt die Größe des Würfels als Quadraht fest. Nur beim Verhältnis 1:1 von Länge:Breite kann eine verzerrungsfreie Darstellung garantiert werden. Vergleichbar mit folgender Zeile:
```java
dice.setSize(length,length)
```

#### roll()

Würfelt den Würfel, speichert die neue Augenzahl ab und erneurt die visuelle Darstellung des Dice.

#### redraw()

Erneuert die visuelle Darstellung des Dice.

---

## Utils

Eine finale Klasse mit vielen kleinen Methoden, die den restlichen Code verkleinern und besser lesbar gestalten soll. Ergänzungen in Form von eigenen Funktionen dürfen **selbst** eingebracht werden. Alle Methoden dieser Klasse sollen *public* und *static* sein.

### Static Methoden

- *boolean[]* **copyArray** ( *boolean[]* array )
- *int[]* **copyArray** ( *int[]* array )
- *String[]* **copyArray** ( *String[]* array )

- *void* **drawInsideRectangle** ( *GreenfootImage* i, *Color* c, *int* b )

- *void* **sortDesc** ( **int[]** array )

#### copyArray()

Kopiert ein Array des Types *boolean*, *int* oder *String* mit identischer Größe.

#### drawInsideRectangle()

Zeichnet innerhalb eines *GreenfootImage* ein Rechteck gefüllt mit der angegebenen Farbe. Es besitzt zu allen Seiten den gegebenen Abstand zum Rand des Image.

#### sortDesc()

Sortiert ein *int[]*-Array absteigend.
