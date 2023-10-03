package Preparazione;

import java.io.*;

public class Main {
    private static BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
    private static Gestione gestione = new Gestione();
    private static String mail;
    public static void main(String [] args){
        try {
            gestione.addAccount("arcotangente.1.17@");
            gestione.fillMail();
            mail = buff.readLine();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
