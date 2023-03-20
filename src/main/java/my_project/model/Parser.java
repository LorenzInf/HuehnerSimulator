package my_project.model;

import KAGO_framework.model.abitur.datenstrukturen.Queue;

public class Parser implements ParserInterface {

    private final Scanner scanner;
    private Queue<int[]> queue;
    private int[] array;
    private boolean huhnErzeugt;
    private int chickenX, chickenY,fenceX,fenceY,foodX,foodY;

    // int[0] == erzeugeFeld
    // int[1] == erzeugeHuhn
    // int[2] == erzeugeEssen
    // int[3] == erzeugeZaun
    // int[4] == geh
    // int[5] == drehRechts
    // int[6] == drehLinks

    public Parser() {
        scanner = new Scanner();
        queue = new Queue();
        huhnErzeugt = false;

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
    ---> erzeugeEssen/Zaun/Feld/Huhn können nur in Aufbau aufgerufen werden, geh, drehLinks/Rechts, friss nur in Durchlauf
            der Code in Aufbau wird sofort ausgeführt, der in Durchlauf danach etwas mit je x Sekunden Pause nach jeder Aktion (würde 0.5 oder so vorschlagen)

    /// Befehle ///
    erzeugeEssen(ZAHL,ZAHL);
    erzeugeZaun(ZAHL,ZAHL);
    erzeugeHuhn(ZAHL,ZAHL); (Darf nur ein mal aufgerufen werden)
    geh(ZAHL); ODER geh; (=geh(1);)
    drehLinks;
    drehRechts;

     */

    //Richtige Parse Methode, die Rückgabe fehlt, weil das bei dem Part nach Durchlauf kommt
    public String parse(String input) {
        huhnErzeugt = false;
        queue = new Queue<>();
        foodX = foodY = fenceY = fenceX = 0;
        if (!input.equals("") && scanner.scan(input)) {
            if (scanner.hasAccess() && scanner.getType().equals("S-WORT") && scanner.getValue().equals("part")) {
                //partAufbau
                scanner.nextToken();
                if (scanner.hasAccess() && scanner.getType().equals("BEZEICHNER") && scanner.getValue().equals("Aufbau")) {
                    scanner.nextToken();
                    //partAufbau(
                    if (scanner.hasAccess() && scanner.getType().equals("PUNKTUATION") && scanner.getValue().equals("(")) {
                        scanner.nextToken();
                        //partAufbau(ZAHL
                        if (scanner.hasAccess() && scanner.getType().equals("ZAHL") && Integer.parseInt(scanner.getValue()) >= 0 && Integer.parseInt(scanner.getValue()) < 16){
                            int zahlOne = Integer.parseInt(scanner.getValue());
                            scanner.nextToken();
                            //partAufbau(ZAHL,
                            if (scanner.hasAccess() && scanner.getType().equals("PUNKTUATION") && scanner.getValue().equals(",")) {
                                scanner.nextToken();
                                //partAufbau(ZAHL,ZAHL
                                if (scanner.hasAccess() && scanner.getType().equals("ZAHL") && Integer.parseInt(scanner.getValue()) >= 0 && Integer.parseInt(scanner.getValue()) < 16){
                                    int zahlTwo = Integer.parseInt(scanner.getValue());
                                    queue.enqueue(array = new int[]{0, zahlOne, zahlTwo});
                                    scanner.nextToken();
                                    //partAufbau(ZAHL,ZAHL)
                                    if (scanner.hasAccess() && scanner.getType().equals("PUNKTUATION") && scanner.getValue().equals(")")) {
                                        scanner.nextToken();
                                        //partAufbau(ZAHL,ZAHL){
                                        if (scanner.hasAccess() && scanner.getType().equals("PUNKTUATION") && scanner.getValue().equals("{")) {
                                            scanner.nextToken();
                                            //partAufbau(ZAHL,ZAHL){erzeugeHuhn;
                                            if (scanner.hasAccess() && checkBefehl("erzeugeHuhn")) {
                                                scanner.nextToken();
                                                //partAufbau(ZAHL,ZAHL){erzeugeHuhn(ZAHL,ZAHL);(erzeugeEssen(ZAHL,ZAHL);)*(erzeugeZaun(ZAHL,ZAHL);)*
                                                while (scanner.hasAccess() && (checkBefehl("erzeugeEssen") || checkBefehl("erzeugeZaun"))) {
                                                    if((foodX == chickenX && foodY == chickenY) || (fenceX == chickenX && fenceY == chickenY)){
                                                        return "Keine Objekte auf dem Huhzn erzeugen!";
                                                    }
                                                    scanner.nextToken();
                                                }
                                                //Abfangen, wenn zu viel geschrieben wird
                                                if (scanner.hasAccess() && scanner.getType().equals("BEZEICHNER")) {
                                                    return "Unbekannter Befehl";
                                                }
                                                //partAufbau(ZAHL,ZAHL){erzeugeHuhn(ZAHL,ZAHL);(erzeugeEssen(ZAHL,ZAHL);)*(erzeugeZaun(ZAHL,ZAHL);)}
                                                if (scanner.hasAccess() && scanner.getType().equals("PUNKTUATION") && scanner.getValue().equals("}")) {
                                                    scanner.nextToken();
                                                    if (scanner.hasAccess() && scanner.getType().equals("S-WORT") && scanner.getValue().equals("part")) {
                                                        scanner.nextToken();
                                                        if (scanner.hasAccess() && scanner.getType().equals("BEZEICHNER") && scanner.getValue().equals("Durchlauf")) {
                                                            scanner.nextToken();
                                                            if (scanner.hasAccess() && scanner.getType().equals("PUNKTUATION") && scanner.getValue().equals("{")) {
                                                                scanner.nextToken();
                                                                while (scanner.hasAccess() && !scanner.getValue().equals("}")) {
                                                                    if (scanner.getType().equals("BEFEHL")) {
                                                                        checkBefehl(scanner.getValue());
                                                                        scanner.nextToken();
                                                                    } else {
                                                                        return "Unbekannter Befehl";
                                                                    }
                                                                }
                                                                if (scanner.hasAccess() && scanner.getType().equals("PUNKTUATION") && scanner.getValue().equals("}")) {
                                                                    return "Keine Fehler";
                                                                } else {
                                                                    return "Es fehlt eine Punktuation('}')";
                                                                }
                                                            } else {
                                                                return "Es fehlt eine Punktuation ('{')";
                                                            }
                                                        } else {
                                                            return "Es fehlt der Bezeichner";
                                                        }
                                                    } else {
                                                        return "Es fehlt ein Schlüsselwort";
                                                    }
                                                } else {
                                                    return "Es fehlt eine Punktuation('}')";
                                                }
                                            } else {
                                                return "Du musst ein Huhn erzeugen";
                                            }
                                        } else {
                                            return "Es fehlt eine Punktuation('{')";
                                        }
                                    } else {
                                        return "Es fehlt eine Punktuation(')')";
                                    }
                                } else {
                                    return "Es fehlt eine Zahl";
                                }
                            } else {
                                return "Es fehlt eine Punktuation(',')";
                            }
                        } else {
                            return "Es fehlt eine Zahl";
                        }
                    } else {
                        return "Es fehlt eine Punktuation('(')";
                    }
                /*} else if (scanner.hasAccess() && scanner.getType().equals("BEZEICHNER") && scanner.getValue().equals("Durchlauf")) {
                    scanner.nextToken();
                    if (scanner.hasAccess() && scanner.getType().equals("PUNKTUATION") && scanner.getValue().equals("{")) {
                        scanner.nextToken();
                        while (scanner.hasAccess() && !scanner.getValue().equals("}")) {
                            if (scanner.getType().equals("BEFEHL")) {
                                checkBefehl(scanner.getValue());
                                scanner.nextToken();
                            } else {
                                return "Unbekannter Befehl";
                            }
                        }
                        if (scanner.hasAccess() && scanner.getType().equals("PUNKTUATION") && scanner.getValue().equals("}")) {
                            return "Keine Fehler";
                        }
                    }
                    */
                } else {
                    return "Es fehlt ein Bezeichner";
                }
            } else {
                return "Es fehlt das Schlüsselwort";
            }
        }
        return "Die Syntax ist falsch!";
    }

    //Checkt ob der Befehl gültig ist: z.B. erzeugeHuhn
    public boolean checkBefehl(String befehl) {
        //BEFEHL
        if (scanner.getType().equals("BEFEHL") && scanner.getValue().equals(befehl)) {
            scanner.nextToken();
            }
            //BEFEHL(
            if (scanner.hasAccess() && scanner.getType().equals("PUNKTUATION") && scanner.getValue().equals("(")) {
                scanner.nextToken();
                //BEFEHL(ZAHL
                if (scanner.hasAccess() && scanner.getType().equals("ZAHL") && Integer.parseInt(scanner.getValue()) < 16 && Integer.parseInt(scanner.getValue()) >= 0){
                    int zahlOne = Integer.parseInt(scanner.getValue());
                    scanner.nextToken();
                    //BEFEHL(ZAHL,
                    if (scanner.hasAccess() && scanner.getType().equals("PUNKTUATION") && scanner.getValue().equals(",")) {
                        scanner.nextToken();
                        //BEFEHL(ZAHL,ZAHL
                        if (scanner.hasAccess() && scanner.getType().equals("ZAHL") && Integer.parseInt(scanner.getValue()) < 16 && Integer.parseInt(scanner.getValue()) >= 0){
                            int zahlTwo = Integer.parseInt(scanner.getValue());
                            scanner.nextToken();
                            //BEFEHL(ZAHL,ZAHL)
                            if (scanner.hasAccess() && scanner.getType().equals("PUNKTUATION") && scanner.getValue().equals(")")) {
                                scanner.nextToken();
                                //BEFEHL(ZAHL,ZAHL);
                                if(scanner.hasAccess() && scanner.getType().equals("PUNKTUATION") && scanner.getValue().equals(";")){
                                    switch (befehl) {
                                        case "erzeugeEssen" -> {
                                            queue.enqueue(array = new int[]{2, zahlOne, zahlTwo});
                                            foodX = zahlOne;
                                            foodY = zahlTwo;
                                        }
                                        case "erzeugeZaun" -> {
                                            queue.enqueue(array = new int[]{3, zahlOne, zahlTwo});
                                            fenceX = zahlOne;
                                            fenceY = zahlTwo;
                                        }
                                        case "erzeugeHuhn" -> {
                                            if(!huhnErzeugt){
                                                queue.enqueue(array = new int[]{1, zahlOne, zahlTwo});
                                                huhnErzeugt = true;
                                                chickenX = zahlOne;
                                                chickenY = zahlTwo;
                                            }
                                        }
                                    }
                                    return true;
                                }
                            }
                        }
                    }else if(scanner.hasAccess() && scanner.getType().equals("PUNKTUATION") && scanner.getValue().equals(")")){
                        scanner.nextToken();
                        if(scanner.hasAccess() && scanner.getType().equals("PUNKTUATION") && scanner.getValue().equals(";")){
                            for(int i = 0; i < zahlOne; i++){
                                queue.enqueue(array = new int[]{4});
                            }
                            return true;
                        }
                    }
                }else if(scanner.hasAccess() && scanner.hasAccess() && scanner.getType().equals("PUNKTUATION") && scanner.getValue().equals(")")){
                    scanner.nextToken();
                    if(scanner.hasAccess() && scanner.getType().equals("PUNKTUATION") && scanner.getValue().equals(";")){
                        switch(befehl){
                            case "geh" -> queue.enqueue(array = new int[]{4});
                            case "drehLinks" -> queue.enqueue(array = new int[]{6});
                            case "drehRechts" -> queue.enqueue(array = new int[]{5});
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

    public Queue<int[]> getQueue() {
        return queue;
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
