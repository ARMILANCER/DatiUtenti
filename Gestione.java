package Preparazione;
import java.io.*;
import java.util.Hashtable;

public class Gestione {
    // table Mail position, contain the position of a record user
    Hashtable<String,Integer> tableMP = new Hashtable<>();
    public Gestione(){
        // fill the Hashtable 'tableMP' at every startup
        fillMail();
    }
    // get data (mail 'DIM_MAX 80',position 'Integer.Byte') of a file .dat, and save data in a Hashtable
    public void addAccount(String mail){
        // memory the byte on the file (Mp mail<0-80>/position<80-84>)
        try(RandomAccessFile raf = new RandomAccessFile("MP.dat","rw")){
            // position is equals to the length of UserData, then open the file
            RandomAccessFile userData = new RandomAccessFile("UserData.dat","r");
            // move index on the next line
            raf.seek(raf.length());
            // save a String
            for(char nextChar : mail.toCharArray()){
                raf.writeChar(nextChar);
            }
            // mail.length()*2 why the for write a char character white a byte \0
            // char for char and fills to length 80
            for(int i=0;i<(80-mail.length()*2);i++){
                raf.writeByte('\0');
                raf.readChar();
            }
            ////System.out.println(raf.length());
            // add an new mail and the position
            tableMP.put(mail,(int) userData.length());
            // write the User data in the file MP
            raf.writeInt((int) userData.length());
            ////System.out.println(raf.length());
            raf.writeByte('\n');
            userData.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    // fill the Hashtable 'tableMP'
    public void fillMail(){
        StringBuilder stringa = new StringBuilder();
        char character;
        int start=0;
        int end = 81;
        try(RandomAccessFile raf = new RandomAccessFile("MP.dat","r")){
            while(raf.getFilePointer() < raf.length()){
                raf.seek(start);
                character = raf.readChar();
                if(raf.getFilePointer() <end||character != '\0'){
                    stringa.append(character);
                }else if(raf.getFilePointer()==end){
                    tableMP.put(stringa.toString(),raf.readInt());
                };
                start += 81;
                end += 81;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
