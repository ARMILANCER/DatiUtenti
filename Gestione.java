package Preparazione;

public class Gestione {
    // table Mail position, contain the position of a record user
    Hashtable<String,Integer> tableMP;
    public Gestione(){

    }
    // get data (mail 'DIM_MAX 80',position 'Integer.Byte') of a file .dat, and save data in a Hashtable
    public void fillTableMP(){
        BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in));
        // memory the byte on the file
        byte[] data = new byte[80+Integer.BYTES];
        
        try(RandomAccessFile raf = new RandomAccessFile("MP.dat","rw")){
            // TODO: while cicle
            raf.read(data);
            // mostro il contenuto 
            System.out.println(new String(data, StandardCharsets.UTF_8));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
