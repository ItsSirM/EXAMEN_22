package examencito;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Calendar;
public class PSNUsers {
    RandomAccessFile RAF;
    RandomAccessFile PSN;
    HashTable users;
    
    PSNUsers() throws IOException{
        try {
            RAF = new RandomAccessFile("userStuff/user.raf", "rw");
            PSN = new RandomAccessFile("userStuff/psn.raf", "rw");
            users = new HashTable();
            reloadHashTable();
        } catch (IOException e) {
            System.out.println("ERROR");
        }
    }
    
    private void reloadHashTable(){
        try {
            RAF.seek(0);
            while (RAF.getFilePointer() < RAF.length()) {
                long pos = RAF.getFilePointer();
                String username = RAF.readUTF();
                RAF.readInt();
                RAF.readInt();
                if (RAF.readBoolean() == true) {
                    users.add(username, pos);
                } else {
                    
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void addUser(String name) throws IOException{
        RAF.seek(RAF.length());
        if (users.Search(name)==-1){
            RAF.writeUTF(name);
            RAF.writeInt(0);
            RAF.writeInt(0);
            RAF.writeBoolean(true);
            if(users.getLenght()==-1){
                users.add(name, 1);
            }
            else
                users.add(name, users.getLenght()+1);
        }
    }
    
    public void deactivateUser(String username) throws IOException{
        RAF.seek(0);
        boolean foundOr = false;
        while(foundOr==false){
            if(RAF.readUTF().equals(username)){
                RAF.readInt();
                RAF.readInt();
                RAF.writeBoolean(true);
                users.remove(username);
                foundOr = true;
            }
            else{
                RAF.readInt();
                RAF.readInt();
                RAF.readBoolean();
            }
        }
    }
    
    public void addTrophieTo(String username, String trophyGame, String trophyName, trophy type) throws IOException{
        RAF.seek(0);
        boolean foundOr = false;
        while(foundOr==false){
            if(RAF.readUTF().equals(username)){
                PSN.seek(PSN.length());
                PSN.writeUTF(username);
                PSN.writeUTF(type.name());
                PSN.writeUTF(trophyGame);
                PSN.writeUTF(trophyName);
                PSN.writeUTF(Calendar.getInstance().toString());
                foundOr = true;
            }
            else{
                RAF.readInt();
                RAF.readInt();
                RAF.readBoolean();
            }
        }
    }
    
    public String playerInfo(String username) throws IOException{
        String text = "";
        RAF.seek(0);
        boolean foundOr = false;
        while(foundOr==false){
            if(RAF.readUTF().equals(username)){
                text = "Nombre: "+username+
                        "\nPuntos: "+RAF.readInt()+
                        "\nCantidad de Trofeos: "+RAF.readInt()+
                        "\n---TROFEOS---";
                PSN.seek(0);
                boolean foundOr2 = false;
                while(foundOr2==false){
                    if(PSN.readUTF().equals(username)){
                        String type = PSN.readUTF();
                        String game = PSN.readUTF();
                        String name = PSN.readUTF();
                        String date = PSN.readUTF();
                        text += "\nFecha: "+date+
                                " - Tipo: "+type+
                                " - Juego:"+game+
                                " - Descripcion: "+name;
                    }
                    else{
                        PSN.readUTF();
                        PSN.readUTF();
                        PSN.readUTF();
                    }
                }
            }
            else{
                RAF.readInt();
                RAF.readInt();
                RAF.readBoolean();
            }
        }
        return text;
    }
}
