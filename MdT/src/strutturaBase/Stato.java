package strutturaBase;

public class Stato {
    private final String nome;

    public Stato(String nome) {
        //se il nome è null o vuoto? sollevo eccezione?
        this.nome=nome;
    }

    public String getNome() {
        return nome;
    }


    //utili
    public String toString() {
        return nome;
    }

    public boolean equals(Object o) {
        if (!(o instanceof Stato)) return false;
        Stato s = (Stato) o;
        return s.nome==this.nome;
        //anche per gli stati finali confronto solo il nome?

    }


}

class StatoFinale extends Stato{

    public StatoFinale(String nome) {
        super(nome);
    }

}
