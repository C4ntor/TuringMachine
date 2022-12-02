package strutturaBase;
import java.util.ArrayList;
import java.util.HashMap;

public class MacchinaDiTuring {
    private Nastro nastro;
    private ArrayList<Stato> stati;
    private int posInizialeTestina;
    private int maxLunghezzaEvoluzione;
    //due variabili di appoggio molto utili
    private int posCorrenteTestina;
    private Stato statoCorrente;
    private int numeroDiPassiEffettuati;
    private ArrayList<Configurazione> configurazioni;

    private HashMap<ChiaveIstruzione, ValoreIstruzione> hmIstruzioni = new HashMap<>();

    //dovrei avere il campo statoIniziale? ma comunque verrà contenuto anche nell'array list degli stati
    private Stato statoIniziale;


    //campo che indica se la macchina ha già eseguito il programma o meno
    private boolean eseguito=false;

    //una hashMap per mantenere tutte le istruzioni possibili che il programma deve eseguire


    public MacchinaDiTuring(String input, char beta, ArrayList<Stato> stati, Stato statoIniziale, int maxLunghezzaEvoluzione, int posInizialeTestina) {
        this.nastro = new Nastro(input,beta);
        this.stati = stati;
        this.posInizialeTestina = posInizialeTestina;
        this.maxLunghezzaEvoluzione = maxLunghezzaEvoluzione;
        this.statoIniziale = statoIniziale;
        if (!(isInStati(statoIniziale))) stati.add(statoIniziale);
        this.posCorrenteTestina = posInizialeTestina;
        this.statoCorrente = statoIniziale;
        this.numeroDiPassiEffettuati = 0;
        this.configurazioni = new ArrayList<>();
    }

    public MacchinaDiTuring(String input, char beta, ArrayList<Stato> stati, Stato statoIniziale) {
        this(input,beta,stati,statoIniziale,5000,0);
    }

    private boolean verificaIstruzioniEseguibili() {
        //controlla in base allo stato attuale della mdt e al carattere corrente sulla testina, se esiste una istruzione corrispondente nella hasmMap istruzioni
        ChiaveIstruzione iCorrente = new ChiaveIstruzione(statoCorrente, nastro.get(posCorrenteTestina));
        if(hmIstruzioni.get(iCorrente)==null) return false;
        return true;
    }

    public void esegui() {
        if (eseguito) return;
        boolean ciSonoIstruzioniEseguibili = verificaIstruzioniEseguibili();
        configurazioni.add(new Configurazione(statoCorrente, nastro, posCorrenteTestina));
        while(numeroDiPassiEffettuati<=maxLunghezzaEvoluzione  && ciSonoIstruzioniEseguibili) {
            //ottiene le istruzioni associate allo stato e carattere corrente sul nastro
            ValoreIstruzione daEseguire = hmIstruzioni.get(new ChiaveIstruzione(statoCorrente, nastro.get(posCorrenteTestina)));
            //bisogna aggiornare lo stato corrente e i valori sul nastro e la posizione della testina
            statoCorrente=daEseguire.getStatoT();
            nastro.set(posCorrenteTestina, daEseguire.getCharB());
            posCorrenteTestina = posCorrenteTestina + daEseguire.getByteM();
            numeroDiPassiEffettuati++;
            ciSonoIstruzioniEseguibili = verificaIstruzioniEseguibili();
            configurazioni.add(new Configurazione(statoCorrente, nastro, posCorrenteTestina));
        }
        eseguito=true;
    }


    public int getCondizione() {
        if(!eseguito) return 0;
        if (isInStatiFinali(statoCorrente))return 1;
        if (numeroDiPassiEffettuati < maxLunghezzaEvoluzione) return 2;
        return 3;
    }

    public ArrayList<Configurazione> getEvoluzione(){
        if (!eseguito) return null;
        return configurazioni;
    }


    public boolean aggiungiIstruzione(Istruzione i) {
        if (eseguito) return false;
        if (!(isInStatiFinali(i.getS())) && isInStati(i.getS()) && isInStati(i.getT()) && i.verifyM() && (hmIstruzioni.get(i.makeKey())==null) ) {
            hmIstruzioni.put(i.makeKey(), i.makeValue());
            return true;
        }
        return false;
    }


    //utili
    private boolean isInStati(Stato p) {
        //verifica se lo stato p è presente nella lista degli stati fornita alla mDT
        boolean trovato= false;
        int i=0;
        while(!trovato && i<=stati.size()-1) {
            if( (stati.get(i)).equals(p)) trovato=true;
            i++;
        }
        return trovato;
    }

    //superflua ma rende il codice più pulito
    private boolean isInStatiFinali(Stato f) {
        //verifica se lo stato f (che può essere un qualsiasi stato passato al metodo) è uno degli stati finali (presenti nella lista degli stati) forniti alla mDT
        if (!(f instanceof StatoFinale)) return false;
        boolean trovato= false;
        int i=0;
        while(!trovato && i<=stati.size()-1) {
            if( (stati.get(i)).equals(f)) trovato=true;
            i++;
        }
        return trovato;
    }

    public Nastro getNastro() {
        return nastro;
    }

    public String toString() {
        String ris="";
        ris+="Il nastro: "+nastro + "\n" + "Gli Stati inseriti: "+stati+"\n"+"Lo stato iniziale: "+statoIniziale+"\n"+"La posizione Iniziale della Testina: "+posInizialeTestina+"\n"+"Lunghezza massima del programma durante l'Evoluzione: "+maxLunghezzaEvoluzione+"\n"+"Posizione Corrente della Testina: "+posCorrenteTestina+"\n"+"Stato Corrente: "+statoCorrente+"\n";
        ris+= "Il numero di passi effettuati: "+numeroDiPassiEffettuati+"\n" +"Le istruzioni inserite: "+hmIstruzioni+"\n"+"Il programma è stato eseguito? : "+eseguito+"\n"+"La condizione della MdT è: "+getCondizione()+"\n";
        if (getCondizione()>0) {
            ris+="Le configurazioni ottenute sono le seguenti: "+configurazioni;
        }
        return ris;
    }


}
