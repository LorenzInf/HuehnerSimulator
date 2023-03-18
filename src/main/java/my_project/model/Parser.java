package my_project.model;

import KAGO_framework.model.abitur.datenstrukturen.Queue;

public class Parser implements ParserInterface {

    private final Scanner scanner;
    private Queue<int[][][]> queue;
    private int[][][] array;

    // int[0] == erzeugeEssen
    // int[1] == erzeugeWand
    // int[2] == erzeugeHuhn
    // int[3] == geh
    // int[4] == drehLinks
    // int[5] == drehRechts

    public Parser() {
        scanner = new Scanner();
        queue = new Queue();

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
    public String parse(String input) {
        if (scanner.scan(input)) {
            if (scanner.getType().equals("S-WORT")  && scanner.getValue().equals("part")) {
                //partAufbau
                scanner.nextToken();
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
                                                //partAufbau(ZAHL,ZAHL){erzeugeHuhn(ZAHL,ZAHL);(erzeugeEssen(ZAHL,ZAHL);)*(erzeugeWand(ZAHL,ZAHL);)}
                                                if (scanner.getType().equals("PUNKTUATION") && scanner.getValue().equals("}")) {
                                                    scanner.nextToken();
                                                    return "Keine Fehler";
                                                }else{
                                                   return "Es fehlt eine Punktuation('}')";
                                                }
                                            }else{
                                                return "Du musst ein Huhn erzeugen";
                                            }
                                        }else{
                                            return "Es fehlt eine Punktuation('{')";
                                        }
                                    }else{
                                        return "Es fehlt eine Punktuation(')')";
                                    }
                                }else{
                                    return "Es fehlt eine Zahl";
                                }
                            }else{
                                return "Es fehlt eine Punktuation(',')";
                            }
                        }else {
                            return "Es fehlt eine Zahl";
                        }
                    }else{
                        return "Es fehlt eine Punktuation('(')";
                    }
                }else{
                    return "Es fehlt der Bezeichner 'Aufbau'";
                }
            }else{
                if(scanner.getType().equals("BEFEHL")){
                    return "Keine Fehler";
                }
            }
        }
        return "Es fehlt das Schlüsselwort ´part´";
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
                    int zahlOne = Integer.parseInt(scanner.getValue());
                    scanner.nextToken();
                    //BEFEHL(ZAHL,
                    if (scanner.getType().equals("PUNKTUATION") && scanner.getValue().equals(",")) {
                        scanner.nextToken();
                        //BEFEHL(ZAHL,ZAHL
                        if (scanner.getType().equals("ZAHL") /*&& Integer.parseInt(scanner.getValue()) < 2 && Integer.parseInt(scanner.getValue()) >= 0*/) {
                            int zahlTwo = Integer.parseInt(scanner.getValue());
                            scanner.nextToken();
                            //BEFEHL(ZAHL,ZAHL)
                            if (scanner.getType().equals("PUNKTUATION") && scanner.getValue().equals(")")) {
                                scanner.nextToken();
                                //BEFEHL(ZAHL,ZAHL);
                                if(scanner.getType().equals("PUNKTUATION") && scanner.getValue().equals(";")){
                                    switch (befehl) {
                                        case "erzeugeEssen" -> queue.enqueue(array = new int[0][zahlOne][zahlTwo]);
                                        case "erzeugeWand" -> queue.enqueue(array = new int[1][zahlOne][zahlTwo]);
                                        case "erzeugeHuhn" -> queue.enqueue(array = new int[2][zahlOne][zahlTwo]);
                                    }
                                    return true;
                                }
                            }
                        }
                    }else if(scanner.getType().equals("PUNKTUATION") && scanner.getValue().equals(")")){
                        scanner.nextToken();
                        if(scanner.getType().equals("PUNKTUATION") && scanner.getValue().equals(";")){
                            queue.enqueue(array = new int[3][zahlOne][0]);
                            return true;
                        }
                    }else if(scanner.getType().equals("PUNKTUATION") && scanner.getValue().equals(";")){
                        if(befehl.equals("drehLinks")){
                            queue.enqueue(array = new int[4][0][0]);
                        }
                        if(befehl.equals("drehRechts")){
                            queue.enqueue(array = new int[5][0][0]);
                        }
                        return true;
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
