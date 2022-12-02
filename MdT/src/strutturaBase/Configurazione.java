package strutturaBase;

public class Configurazione {
    private Stato s;
    private Nastro n;
    private int posTestina;


    public Configurazione(Stato s, Nastro n, int posTestina) {
        this.s = s;
        this.n = new Nastro(n);
        this.posTestina = posTestina;
    }

    public Stato getStato() {
        return s;
    }
    public Nastro getNastro() {
        return n;
    }
    public int getPosTestina() {
        return posTestina;
    }

    //utili
    public String toString() {
        return s+" "+n+" "+posTestina;
    }



}
