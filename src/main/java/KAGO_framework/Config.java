package KAGO_framework;

/**
 * In dieser Klasse werden globale, statische Einstellungen verwaltet.
 * Diese beziehen sich nur auf die Funktionsweise des Frameworks.
 * Für individuelle Einstellungen am eigenen Projekt sollte die Config-Datei im Paket "my_project"
 * verwendet werden.
 */
public class Config {

    // Frameworkversion
    public final static String VERSION = "KNB-AOS-GraphicalObject-Java-Framework - 4.5b - 14.01.2022";
    public final static String JAVA_SUPPORTED = "Java 11 bis Java 15";

    // Schaltet die Infomeldungen des Frameworks an oder aus
    public final static boolean INFO_MESSAGES = true;
    public final static boolean DEBUG = false;


    /*
1:

part Aufbau(7,7) {
  erzeugeHuhn(3,4);
  erzeugeEssen(3,2);
  erzeugeZaun(3,3);
}

part Durchlauf {
  geh(1);
  drehLinks();
  geh(2);
  drehLinks();
  geh(1);
}

    2:
part Aufbau(2,2) {
  erzeugeHuhn(0,0);
  erzeugeEssen(1,1);
}

part Durchlauf {
  geh(2);
  drehRechts();
  geh(2);
  drehRechts();
  geh(2);
  drehRechts();
  geh(2);
}

3:
part Aufbau(7,7) {
  erzeugeHuhn(1,4);
  erzeugeZaun(3,2);
  erzeugeZaun(3,3);
  erzeugeZaun(3,4);
  erzeugeZaun(3,5);
  erzeugeZaun(3,6);
}

part Durchlauf {
  geh(5);
}

4:
part Aufbau(7,7) {
  erzeugeHuhn(1,4);
  erzeugeZaun(1,4);
  erzeugeEssen(1,4);
  erzeugeZaun(2,4);
  erzeugeEssen(3,3);
  erzeugeZaun(3,6);
}

part Durchlauf {

}

     */
}