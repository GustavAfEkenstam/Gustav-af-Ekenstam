# En introduktion till ritmotorn version 2 som kan användas i lab4
Ritmotorn (DrawingUtil) syftar till att rita ut figurer på en skärmyta. För att kunna rita ut en figur anropas någon av metoderna
```
#!java

 drawCircle(figur,Graphics-objekt);  
 drawRect(figur, Graphics-objekt); 
 drawLine(figur, Graphics-objekt);
```

Dessa anrop medför att den figur som skickas in som inparameter ritas upp på skärmytan som Graphics-objektet håller. 
Graphics-objektet måste komma från en instans av klassen DrawingPanel som i sin tur måste ingå i ett existerande Swing-gränssnitt.

De figurer som kan ritas ut måste komma ur en subklass till `DrawingShape`. För varje figur att ritas behövs en serie data att hantera:

* Typ av figur
* Övre vänstra hörn (x, y koordinater koordinat 1,1 är övre vänstra hörnet av komponenten vars skärmyta vi ritar på)
* Höjd (antal pixlar) 
* Bredd (antal pixlar)
* Om vi ritar en linje anges linjens nedre högra hörn (x,y). Vi använder attributen width, height för detta då vi ritar linjer.
* Linjetjocklek (i pixlar)
* Linjefärg (Använder ett Color objekt från paketet java.awt. Det finns färdiga konstanter för färger där ex. "Color.GREEN" 
* Fyllfärg (för rektanglar och cirklar) Använder Color objekt som linjefärg ovan. Sätts till "null" för transparent figur.


# Ett komplett exempel
För att tilldela ritmotorn en skärmyta måste vi bygga ett Swing-gränssitt med en instans av DrawingPanel i sig. Därefter använder vi DrawingUtil för att "rita på" DrawingPanel instansen. Observera att detta lilla exempel inte tar någon hänsyn till MVC utan bara är till för att illustrera hur ritmotorn arbetar!

```
#!java

 01. import java.awt.Color;
 02. import javax.swing.JFrame;
 03. import se.kau.isgc08.lab4.model.*;
 04. import se.kau.isgc08.lab4.view.*;
 05.
 06. public class MinimalDraw {
 07.     public static void main(String[] args) {
 08.         JFrame j=new JFrame();
 09.         j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
 10.         DrawingContainer dc=new DrawingContainer(); 
 11.         DrawingUtil da=new DrawingUtil(); 
 12.         Line l=new Line(da,3,3,50,50,1,Color.RED);  											
 13.         Circle c=new Circle(da,20,20,80,80,1,Color.BLUE,null);
 14.         Circle c2=new Circle(da,150,150,50,250,20,Color.BLUE, Color.CYAN); 
 15.         Rect r=new Rect(da,320,200,80,80,5,Color.GREEN,Color.PINK); 
 16.         dc.add(l);   
 17.         dc.add(c);
 18.         dc.add(c2);
 19.         dc.add(r);
 20.         DrawingPanel dp = new DrawingPanel(dc); 
 21.         dp.setBackground(Color.WHITE); 
 22.         j.add(dp);
 23.         j.setSize(500, 500); 
 24.         j.setVisible(true);
 25.         Rect r2=new Rect(da,320,200,80,80,5,Color.BLACK,null); 
 26.         dc.add(r2);
 27.         dc.remove(r);
 28.         j.repaint();
 29.     }
 30.}

```
## Kommentarer till källkoden ovan
* Rad 08. Tillverkas det fönster som ger oss en skrämyta att rita på. Utan detta ingen skärmyta.
* Rad 09. Möjliggör programavslut genom att "klicka på krysset" i fönstret. Går lika bra att implementera en WindowListener för detta
* Rad 10. Skapar ritningens huvudcontainer, alla figurer kommer att placeras i denna container.
* Rad 11. Skapar ritmotorn 
* Rad 12. Skapar en linje och skickar med en referens till ritmotorn (för att linjen själv ska kunna anropa "sin" ritmetod i ritmotorn då compositen ritas ut. Därefter följer över vänstra hörn, nedre högra hörn, linjetjocklek samt linjefärg.
* Rad 13. Skapar en cirkel men nu är andra paret parametrar bredd, höjd, samt sista parametern är fyllfärg, i detta exempel null alltså transparent.
* Rad 14. Skapar en cirkel till med andra färger samt fylld med Cyan (läcker:-)
* Rad 15.skapar en rosa rektangel med grön linje)
* Rad 16-19. Lägger till figurerna vi gjort i containern som i sin tur finns tilgänglig i ritmotorn...utan detta steg kommer inget ritas ut. 
* Rad 20. Instansierar en "Ritpanel" för att ha en skärmyta att rita på. 
* Rad 21. Sätter Ritpanelens bakgrundsfärg till vit.
* Rad 22. Lägger till ritpanelen i fönstret (JFramen) utan denna aktivitet har ritpanelen ingen skärmyta att rita på.
* Rad 23. Sätter fönsterstorlek, ger indirekt storleken på vår skärmyta att rita på (genom val av Layout).
* Rad 24. Tänder/ritar upp skärmen.
* Rad 25. Skapar en svart transparent rektangel.
* Rad 26. Lägger till rektangeln.
* Rad 27. Tar bort den rosa rektangeln...Vad händer om ni kommentarmärker denna rad?
* Rad 27. överflödigt anrop men med för er referens, Kan anropas närsom för att tvinga ritmotorn att rita om ritningen.				  

# Checka ut ritmotorn
Om ni inte använt en tidigare version av ritmotorn klonar ni bara ut detta git-repo.

Därefter använder ni ritmotorn enligt instruktioner ovan samt den javadoc dokumentation som distribueras tillsammans med källkodsträdet i detta repo.


# Rapportera och följa buggar
Om ni mot förmodan skulle stöta på en bugg i ritmotorn rapporterar ni in den via Issues taben ovan. Ni startar ett nytt "issue" och beskriver buggen så tydligt som möjligt.  Tack/benc



# Klassdiagram![KlassdiagramRitAPI2.png](https://github.com/karlstad-business-school/ISGC08-drawingapi/blob/master/KlassdiagramRitAPI2.png)

# Revisionskommentarer
