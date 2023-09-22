package examencito;
public class HashTable {
    Entry table = null;
    
    public void add(String name, long pos){
        if(table == null){
            table = new Entry(name, pos);
        }
        else{
            Entry siguiente = table.next;
            while(siguiente!=null){
                siguiente = siguiente.next;
            }
            siguiente.next = new Entry(name, pos);
        }
    }
    
    public void remove(String name){
        Entry item = table;
        boolean deleted = false;
        while(deleted == false){
            if(item == null)
                deleted = true;
            
            if(item.username.equals(name)){
                item.next = item;
                deleted = true;
            }
            else
                item = item.next;
        }
    }
    
    public long Search(String name){
        Entry item = table;
        long pos = -12345;
        while(pos==-12345){
            if(item == null){
                pos = -1;
            }
            else if(item.username.equals(name)){
                pos = item.posicion;
            }
            else
                item = item.next;
        }
        return pos;
    }
    
    public long getLenght(){
        Entry item = table;
        long pos = 0;
        long newPos = 0;
        while(pos == 0){
            if(item == null){
                pos = -1;
            }
            if(item.next==null){
                pos = newPos;
            }
            else{
                item = item.next;
                newPos++;
            }
        }
        return pos;
    }
}
