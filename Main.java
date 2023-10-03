package Preparazione;

import java.io.*;

public class Main {
    private static BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
    private static Gestione gestione = new Gestione();
    private static String mail = "arcotangente.1.17@";
    public static void main(String [] args){
        if(mail.length()<gestione.MAX_LENGTH_MAIL) {
            gestione.addAccount("arcotangente.1.17@");
        }
        // TODO: remove this instruction
        gestione.fillTMP();
    }
}

