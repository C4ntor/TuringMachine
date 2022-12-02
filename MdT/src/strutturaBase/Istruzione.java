package strutturaBase;

public class Istruzione {

    private final Stato s;
    private final char a;
    private final Stato t;
    private final char b;
    private final byte m;

    public Istruzione(Stato s, char a, Stato t, char b, byte m) {
        this.s=s;
        this.a=a;
        this.t=t;
        this.b=b;
        this.m=m;
    }


    public Stato getS() {
        return s;
    }
    public char getA() {
        return a;
    }
    public Stato getT() {
        return t;
    }
    public char getB() {
        return b;
    }
    public byte getM() {
        return m;
    }

    //utili
    public String toString() {
        return "<"+s.getNome()+","+a+","+t.getNome()+","+b+","+m+">";
    }

    //presa un istruzione ne restituisce la coppia (s,a) simboleggiante la chiave di una hashMap per inserirla nella macchina di turing
    public ChiaveIstruzione makeKey() {
        return new ChiaveIstruzione(this.s,this.a);
    }

    //presa un istruzione ne restituisce la terna (t,b,m) simboleggiante il valore di una hashMap per inserirla nella macchina di turing
    public ValoreIstruzione makeValue() {
        return new ValoreIstruzione(this.t,this.b,this.m);
    }


    public boolean verifyM() {
        if(m==-1 || m==0 || m==1) return true; else return false;
    }


}


//classi ausiliarie
class ChiaveIstruzione{
    private final Stato s;
    private final char a;

    ChiaveIstruzione(Stato s, char a) {
        this.s=s;
        this.a=a;
    }

    public String toString() {
        return "<"+s.getNome()+","+a+">";
    }


    public boolean equals(Object o) {
        if (!(o instanceof ChiaveIstruzione)) return false;
        ChiaveIstruzione p = (ChiaveIstruzione) o;
        return p.s.equals(this.s ) && p.a==this.a;
    }

    public int hashCode() {
        return (s+""+a).hashCode();
        //return 1;
    }


}

class ValoreIstruzione {
    private final Stato t;
    private final char b;
    private final byte m;

    public Stato getStatoT(){
        return t;
    }
    public char getCharB(){
        return b;
    }
    public byte getByteM(){
        return m;
    }

    ValoreIstruzione(Stato t, char b, byte m){
        this.t=t;
        this.b=b;
        this.m=m;
    }

    public String toString() {
        return "<"+t.getNome()+","+b+","+m+">";
    }

    public boolean equals(Object o) {
        if (!(o instanceof ValoreIstruzione)) return false;
        ValoreIstruzione p = (ValoreIstruzione) o;
        return p.t.equals(this.t) && p.b==this.b && p.m==this.m;
    }

    public int hashCode() {
        return (t.getNome()+b+m).hashCode();
    }




}
