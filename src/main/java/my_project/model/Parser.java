package my_project.model;

public class Parser {
    /* ---------- Syntax: ----------

    /// Parts ///
    part Aufbau(x,y) {
        /// CODE
    }
    part Durchlauf {
        /// CODE
    }
    ---> Sowohl "Aufbau" und "Durchlauf" muss es *genau* ein mal in jedem Programm geben
    ---> erzeugeEssen/Wand/Feld/Huhn können nur in Aufbau aufgerufen werden, geh, drehLinks/Rechts, friss nur in Durchlauf
            der Code in Aufbau wird sofort ausgeführt, der in Durchlauf danach etwas mit je x Sekunden Pause nach jeder Aktion (würde 0.5 oder so vorschlagen)

    /// Befehle ///
    erzeugeEssen(ZAHL,ZAHL);
    erzeugeWand(ZAHL,ZAHL);
    erzeugeHuhn(ZAHL,ZAHL); (Darf nur ein mal aufgerufen werden)
    geh(ZAHL); ODER geh; (=geh(1);)
    drehLinks;
    drehRechts;

     */
}
