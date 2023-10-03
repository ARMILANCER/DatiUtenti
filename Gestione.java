package Preparazione;
import java.io.*;
import java.util.Hashtable;

public class Gestione {
    // table Mail position, contain the position of a record user
    Hashtable<String,Integer> tableMP = new Hashtable<>();
    // bytes 0-80 -> <String>mail, 81-84 -> <int>position, 85 -> '\n'
    private final int MAX_LENGTH_RECORD = 85;
    public final int MAX_LENGTH_MAIL = 80;
    File mailPosition = new File("MP.dat");
    public Gestione(){
        // fill the Hashtable 'tableMP' at every startup TODO:uncomment this instruction
        //fillTMP();
    }
    // get data (mail 'DIM_MAX 80',position 'Integer.Byte') of a file .dat, and save data in a Hashtable
    public void addAccount(String mail) {
        RandomAccessFile userData;
        // memory the byte on the file (Mp mail<0-80>/position<80-84>)
        try (RandomAccessFile raf = new RandomAccessFile("MP.dat", "rw")) {
            // position is equals to the length of UserData, then open the file
            userData = new RandomAccessFile("UserData.dat", "r");
            // move index on the next line
            raf.seek(raf.length());
            // save a String
            raf.writeBytes(mail);
            //raf.writeChars(mail);
            // for (char nextChar : mail.toCharArray()) {
            //    raf.writeChar(nextChar);
            //}
            // mail.length()*2 why the for write a char character white a byte \0
            // char for char and fills to length 80
            for (int i = 0; i < (MAX_LENGTH_MAIL - mail.length()/*mail.length() * 2*/); i++) {
                raf.writeByte('\0');
            }
            ////System.out.println(raf.length());
            // add an new mail and the position
            tableMP.put(mail, (int) userData.length());
            // write the User data in the file MP
            raf.writeInt((int) userData.length());
            ////System.out.println(raf.length());
            raf.writeByte('\n');
            userData.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // fill the Hashtable 'tableMP'
    public void fillTMP(){
        // mail contain a String, and is use as key in the 'TableMP(mail,position)'
        StringBuilder mail = new StringBuilder();
        // contain the bytes of the file 'MP.dat'
        byte[] data = new byte[MAX_LENGTH_RECORD];
        // subByte -> contain a range of bytes read in the cycle while
        int subByte;
        try(RandomAccessFile raf = new RandomAccessFile(mailPosition,"r")){
            while((subByte = raf.read(data))!=-1){
                for(int i=0;i<subByte;i++){
                    if(data[i] != '\0'){
                        mail.append(new String(data,"UTF-8"));
                    }
                }
                System.out.println();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
