package my_project.model;

public class Parser implements ParserInterface {

    private final Scanner scanner;

    public Parser() {
        scanner = new Scanner();
    }
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

    //Richtige Parse Methode, die Rückgabe fehlt, weil das bei dem Part nach Durchlauf kommt
    public boolean parse(String input) {
        if (scanner.scan(input)) {
            if (scanner.getType().equals("S-WORT")) {
                //part
                if (scanner.getValue().equals("part")) {
                    scanner.nextToken();
                    //partAufbau
                    if (scanner.getType().equals("BEZEICHNER") && scanner.getValue().equals("Aufbau")) {
                        scanner.nextToken();
                        //partAufbau(
                        if (scanner.getType().equals("PUNKTUATION") && scanner.getValue().equals("(")) {
                            scanner.nextToken();
                            //partAufbau(ZAHL
                            if (scanner.getType().equals("ZAHL")/* && Integer.parseInt(scanner.getValue()) < 0 && Integer.parseInt(scanner.getValue()) >= 20*/) {
                                scanner.nextToken();
                                //partAufbau(ZAHL,
                                if (scanner.getType().equals("PUNKTUATION") && scanner.getValue().equals(",")) {
                                    scanner.nextToken();
                                    //partAufbau(ZAHL,ZAHL
                                    if (scanner.getType().equals("ZAHL") /*&& Integer.parseInt(scanner.getValue()) < 0 && Integer.parseInt(scanner.getValue()) >= 20*/) {
                                        scanner.nextToken();
                                        //partAufbau(ZAHL,ZAHL)
                                        if (scanner.getType().equals("PUNKTUATION") && scanner.getValue().equals(")")) {
                                            scanner.nextToken();
                                            //partAufbau(ZAHL,ZAHL){
                                            if (scanner.getType().equals("PUNKTUATION") && scanner.getValue().equals("{")) {
                                                scanner.nextToken();
                                                //partAufbau(ZAHL,ZAHL){erzeugeHuhn;
                                                if (checkBefehl("erzeugeHuhn")) {
                                                    scanner.nextToken();
                                                    //partAufbau(ZAHL,ZAHL){erzeugeHuhn(ZAHL,ZAHL);(erzeugeEssen(ZAHL,ZAHL);)*
                                                    while (checkBefehl("erzeugeEssen")) {
                                                        scanner.nextToken();
                                                    }
                                                    //partAufbau(ZAHL,ZAHL){erzeugeHuhn(ZAHL,ZAHL);(erzeugeEssen(ZAHL,ZAHL);)*(erzeugeWand(ZAHL,ZAHL);)*
                                                    while (checkBefehl("erzeugeWand")) {
                                                        scanner.nextToken();
                                                    }
                                                    //partAufbau(ZAHL,ZAHL){erzeugeHuhn(ZAHL,ZAHL);(erzeugeEssen(ZAHL,ZAHL);)*(erzeugeWand(ZAHL,ZAHL);)*}
                                                    if (scanner.getType().equals("PUNKTUATION") && scanner.getValue().equals("}")) {
                                                        scanner.nextToken();
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    //Checkt ob der Befehl gültig ist: z.B. erzeugeHuhn
    public boolean checkBefehl(String befehl) {
        //BEFEHL
        if (scanner.getType().equals("BEFEHL") && scanner.getValue().equals(befehl)) {
            scanner.nextToken();
            }
            //BEFEHL(
            if (scanner.getType().equals("PUNKTUATION") && scanner.getValue().equals("(")) {
                scanner.nextToken();
                //BEFEHL(ZAHL
                if (scanner.getType().equals("ZAHL") /*&& Integer.parseInt(scanner.getValue()) < 2 && Integer.parseInt(scanner.getValue()) >= 0*/) {
                    scanner.nextToken();
                    //BEFEHL(ZAHL,
                    if (scanner.getType().equals("PUNKTUATION") && scanner.getValue().equals(",")) {
                        scanner.nextToken();
                        //BEFEHL(ZAHL,ZAHL
                        if (scanner.getType().equals("ZAHL") /*&& Integer.parseInt(scanner.getValue()) < 2 && Integer.parseInt(scanner.getValue()) >= 0*/) {
                            scanner.nextToken();
                            //BEFEHL(ZAHL,ZAHL)
                            if (scanner.getType().equals("PUNKTUATION") && scanner.getValue().equals(")")) {
                                scanner.nextToken();
                                //BEFEHL(ZAHL,ZAHL);
                                return scanner.getType().equals("PUNKTUATION") && scanner.getValue().equals(";");
                            }
                        }
                    }
                }
            }
        return false;
    }

    public boolean getScannerResult(String input) {
        return scanner.scan(input);
    }

    //Erste Parse Methode, die normal war, aber man kann immer Sachen copy pasten --> Später löschen
    /*
    public boolean parse(String input){
        if(scanner.scan(input)){
            //BEFEHL
            if(scanner.getType().equals("BEFEHL")){
                scanner.nextToken();
                //BEFEHL(
                if(scanner.getType().equals("PUNKTUATION") && scanner.getValue().equals("(")){
                    scanner.nextToken();
                    //BEFEHL(ZAHL
                    if(scanner.getType().equals("ZAHL")){
                        scanner.nextToken();
                        //Mehr als eine Ziffer
                        while(scanner.getType().equals("ZAHL")){
                            scanner.nextToken();
                        }
                        //BEFEHL(ZAHL,
                        if(scanner.getType().equals("PUNKTUATION") && scanner.getValue().equals(",")){
                            scanner.nextToken();
                            //BEFEHL(ZAHL,ZAHL
                            if(scanner.getType().equals("ZAHL")){
                                scanner.nextToken();
                                //Mehr als eine Ziffer
                                while(scanner.getType().equals("ZAHL")){
                                    scanner.nextToken();
                                }
                                //BEFEHL(ZAHL,ZAHL)
                                if(scanner.getType().equals("PUNKTUATION") && scanner.getValue().equals(")")){
                                    scanner.nextToken();
                                    //BEFEHL(ZAHL,ZAHL);
                                    if(scanner.getType().equals("PUNKTUATION") && scanner.getValue().equals(";")){
                                        scanner.nextToken();
                                        return scanner.getType().equals("NODATA");
                                    }
                                }
                            }
                        }else{
                            //BEFEHL(ZAHL)
                            if(scanner.getType().equals("PUNKTUATION") && scanner.getValue().equals(")")){
                                scanner.nextToken();
                                //BEFEHL(ZAHL);
                                if(scanner.getType().equals("PUNKTUATION") && scanner.getValue().equals(";")){
                                    scanner.nextToken();
                                    return scanner.getType().equals("NODATA");
                                }
                            }
                        }
                    }
                }
            }else{
             //BEFEHL;
                if(scanner.getType().equals("PUNKTUATION") && scanner.getValue().equals(";")){
                    scanner.nextToken();
                    return scanner.getType().equals("NODATA");
                }
            }
        }
        return false;
    }
     */
}
