package examencito
        ;
public enum trophy {
    PLATINO (5),
    ORO (3),
    PLATA (2),
    BRONCE (1);
    
    int points;
    trophy(int points){
        this.points = points;
    }
}
