package examencito;
public class Entry {
    String username;
    long posicion;
    Entry next;
    
    Entry(String name, long pos){
        username = name;
        posicion = pos;
        next = null;
    }
}
