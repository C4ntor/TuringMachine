package strutturaBase;

import java.util.HashMap;

public class Nastro {


    private HashMap<Integer, Character> hMnastro = new HashMap<>();
    private char beta;
    public int minPos;
    public int maxPos;



    public Nastro(String s, char beta) {
        this.beta=beta;
        minPos = 0;
        if (s.length()==0) {maxPos=0;}
        else {maxPos = s.length()-1;
            for(int i=minPos;i<=maxPos;i++) {
                hMnastro.put(i,s.charAt(i));
            }
        }

    }



    public String toString() {
        String ris="|";
        for(int i=minPos;i<=maxPos;i++) {
            if(i!=maxPos) ris+=get(i)+" "; else ris+=get(i)+"|";
        }
        return ris;


    }

    public Nastro(Nastro n) {
        Nastro nuovo = new Nastro(n.creaStringaNastro(),n.beta,n.minPos,n.maxPos);
        this.hMnastro=nuovo.hMnastro;
        minPos = nuovo.minPos;
        maxPos = nuovo.maxPos;
    }

    public char get(int i) {
        if (hMnastro.get(i)!=null) return hMnastro.get(i);
        return beta;
    }

    public void set (int i, char c) {
        if (i< minPos) {
            minPos=i;
        } else if (i>maxPos) {
            maxPos=i;
        }
        hMnastro.put(i, c);
    }

    //utili
    private String creaStringaNastro() {
        //restituisce una stringa la cui prima posizione è 0 ed è lunga (maxPos+(-1*minPos)+1)
        String ris=get(minPos)+"";
        for (int i=minPos+1;i<=maxPos;i++) {
            ris+=get(i);
        }
        return ris;
    }

    private Nastro(String s, char beta, int minPos, int maxPos) {
        this.beta=beta;
        this.minPos = minPos;
        this.maxPos = maxPos;
        int j=0;
        for(int i=minPos;i<=maxPos;i++) {
            //essendo che la stringa restituita parte da 0 e va fino a maxPos+(-1*minPos)
            hMnastro.put(i,s.charAt(j));
            j++;

        }

    }




}
