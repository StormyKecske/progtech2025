
# Amőba NxM-es parancssoros játék implementáció

* A félév során a hallgatóknak önállóan kell lefejleszteni egy Java parancssoros Amőba játékot
* Ennek leírása itt olvasható magyarul és angolul:
    https://hu.wikipedia.org/wiki/Gomoku https://en.wikipedia.org/wiki/Gomoku
    * Az amőba kétszemélyes stratégiai táblajáték, mely egy db NxM-es (N és M pozitiv egész szám, 5 <=M <= N <=25), tipikusan 10x10-as táblán játszható. N -- sorok, M -- oszlopok száma.
    * Az oszlopok számozása tipikusan a,b,c, ... betűkkel történik, a soroké 1,2,3,..,N sorszámokkal -- de ettől nem függ a játékprogram, más megnevezés is használható.
    * Induláskor a tábla üres. 
    * A két játékos közül az egyik az "x", a másik a "o" jelet vezeti. Az "x" lesz a humán játékosé, a "o" a gépi játékosé. Az "x" játékos, a humán kezd.
    * A játékosok minden körben egy-egy, még nem elfoglalt helyre tehetik a jelüket, de úgy, hogy a lerakott jel legalább diagonálisan érintkezzen a már fennlévőkkel. A kezdő jel a tábla egyik középső mezőjére menjen. 
    * A gépi ellenfél ebben a félévben még rém egyszerű, csak random generál egy lehetséges lépést, mindegyiket egyenlő valószínűséggel.
    *  Az a játékos nyer, amelyik függőlegesen, vízszintesen, vagy átlósan kirakott négyet a jeléből.
* A beadandó feladatot két ütemben kell majd elkészíteni és megvédeni
* A védések az óra időpontjában fognak történni (7. és 13. héten)
* **Elvárások az első (7. heti) védésre:**
    * Egy publikus GitHub repository létrehozása
    * A létrehozott Git repository tartalmazza a beadandó forráskódját
    * A repository tartalmaz egy megfelelő .gitignore fájlt annak érdekébe, hogy IDE vagy Maven specifikus ideiglenes fájlok ne kerüljenek fel a repository-ba
    * Egy Java 21-es Maven projekt létrehozása (pom.xml és Maven folder struktúra)
    * A Maven projekt az alábbi konfigurációkat tartalmazza:
        * Plugin-ek:
            * org.apache.maven.plugins.maven-jar-plugin - annak érdekében, hogy felkonfiguráljuk az alkalmazásunk belépési pontját (Main Class)
            * org.apache.maven.plugins.maven-assembly-plugin - annak érdekében, hogy egy függőségeket tartalmazó, futtatható JAR fájl jöjjön létre az alkalmazás build-elése eredményeként
            * org.jacoco.jacoco-maven-plugin - annak érdekében, hogy a megírt Egység tesztek kód lefedettségét tudjuk mérni
            * org.apache.maven.plugins.maven-checkstyle-plugin - annak érdekében, hogy a projekten elkövetett kód formázási hibákat és egyéb rossz praktikák automatikus detektáljunk
        * Függőségek:
            * JUnit5
            * Mockito
            * Logback
    * Az alkalmazás objektum-orientált modellezésének megkezdése
        * Az alkalmazásunkhoz szükséges VO (Value Object) osztályok létrehozása (ügyelve és figyelembe véve a "best practice"-eket: Object methods overriding, Immutability, stb)
    * Az induláskor egy szövegfájlból beolvas egy  játékállást, ha nincs meg az input fájl, akkor üres pályáról indulunk
	* Az alkalmazás képes kezdetleges felhasználói interakciókat fogadni
		* egy szövegfájlból betölteni egy pályát
		* egy szövegfájlba kiÍrni egy pályát
        * Például: Játékos nevének bekérése, Játék elindítása, a játéktér kiiratása, Egy lépés fogadása a parancssoron, a lépés vizsgálata abból a szempontból, hogy alkalmazható-e; a lépés alkalmazása és az eredmény kiírása, stb
        * Itt nem határozunk meg kötelező elvárásokat, tetszőleges kezdetleges interakciók elegendőek
    * Egység tesztek 80% lefedettséget biztosítanak üzleti logikát tartalmazó osztályokra (tehát például VO osztályokra nem szükséges egységteszteket írni)
* **Elvárások a második (13. heti) védésre:**
    * A teljes játék funkcionalitás lefejlesztésre került (lehetséges egy játékot végig játszani elejétől a végéig)
    * A projekt a `mvn clean install` parancs futtatására hiba nélkül fordul
	* Az alkalmazás egy adatbázisba lementi a játékosok nevét és azt, hogy hányszor nyertek
        * Az alkalmazás képes megjeleníteni parancssorban egy high score táblázatot (melyik játékos hány meccset nyert)
    * Opcionális (plusz pontért): egy aktuálisan folyamatban lévő játék állást az alkalmazás képes egy XML fájlba kimenteni és később visszatölteni (tehát a játékos onnan folytathatja a játékot, ahol korábban abba hagyta)
    * Egység tesztek továbbra is 80% lefedettséget biztosítanak üzleti logikát tartalmazó osztályokra
