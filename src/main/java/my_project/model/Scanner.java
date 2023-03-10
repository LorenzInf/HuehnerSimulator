package my_project.model;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Scanner {
    /* ---------- Wörter: ----------

    /// Schlüsselwörter | Token name: S-WORT ///
    function
    part

    /// Befehle | Token name: BEFEHL ///
    erzeugeEssen
    erzeugeWand
    erzeugeHuhn
    geh
    drehLinks
    drehRechts
    friss

    /// Zahlen (für Koordinaten etc.) | Token name: ZAHL ///
    [0-9]* < 2,147,483,647

    /// Bezeichner | Token name: BEZEICHNER ///
    [a-zA-z][a-zA-Z0-9]*

    /// Punktuation | Token name: PUNKTUATION ///
    {
    }
    (
    )
    ;
    ,

     */
    private KAGO_framework.model.abitur.datenstrukturen.List<Token<String,String>> tokenList;

    public Scanner() {

    }

    public boolean scan(String input) {
        tokenList = new KAGO_framework.model.abitur.datenstrukturen.List<>();
        input = input.replaceAll("\t","");
        String[] inputLines = input.split("\\s");
        for(String line : inputLines) {
            for(int i = 0; i < line.length(); i++) {
                if(String.valueOf(line.charAt(i)).matches("[a-zA-z]")) {
                    StringBuilder currWord = new StringBuilder();
                    for(; i < line.length() && !String.valueOf(line.charAt(i)).matches("[(){},;\\s]"); i++) {
                        currWord.append(line.charAt(i));
                    }
                    i--;
                    if(currWord.toString().matches("function|part"))
                        tokenList.append(new Token<>("S-WORT",currWord.toString()));
                    else if(currWord.toString().matches("erzeugeEssen|erzeugeWand|erzeugeHuhn|geh|drehLinks|drehRechts|friss"))
                        tokenList.append(new Token<>("BEFEHL",currWord.toString()));
                    else
                        tokenList.append(new Token<>("BEZEICHNER",currWord.toString()));
                } else if(String.valueOf(line.charAt(i)).matches("[0-9]")) {
                    StringBuilder currWord = new StringBuilder();
                    for(; i < line.length() && String.valueOf(line.charAt(i)).matches("[0-9]"); i++) {
                        currWord.append(line.charAt(i));
                    }
                    i--;
                    tokenList.append(new Token<>("ZAHL",currWord.toString()));
                } else if(String.valueOf(line.charAt(i)).matches("[(){},;]")) {
                    tokenList.append(new Token<>("PUNKTUATION",Character.toString(line.charAt(i))));
                } else return false;
            }
        }
        //DEBUG//
        tokenList.toFirst();
        while(tokenList.hasAccess()) {
            System.out.print("[" + tokenList.getContent().getTokenType() + ", " + tokenList.getContent().getTokenName() + "], ");
            tokenList.next();
        }
        System.out.println();
        return true;
    }
}