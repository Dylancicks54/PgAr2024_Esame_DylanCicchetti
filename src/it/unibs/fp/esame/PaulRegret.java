package it.unibs.fp.esame;

public class PaulRegret extends Personaggio{
    private static final int PUNTI_VITA = 3; // Punti vita di Paul Regret
    private static final int AUMENTO_DISTANZA_ABILITA = 1; // Aumento della distanza a cui è visto dagli altri grazie all'abilità
    private static final int AUMENTO_DISTANZA_MUSTANG = 1; // Aumento della distanza a cui è visto dagli altri se c'è un Mustang reale in gioco

    private int puntiVita;

    public PaulRegret() {
        super("PaulRegret", PUNTI_VITA);
    }

    public int getPuntiVita() {
        return puntiVita;
    }

    public void perditaPuntoVita() {
        puntiVita--;
        if (puntiVita < 0) {
            puntiVita = 0;
        }
    }

    public boolean tentativoAnnullaColpo() {
        // Simulazione del tentativo di annullare il colpo (Barile)
        return Math.random() < 0.5; // 50% di probabilità di riuscita
    }

    public int getAumentoDistanza() {
        int aumentoDistanzaTotale = AUMENTO_DISTANZA_ABILITA;
        // Se c'è un Mustang reale in gioco, aggiunge l'aumento della distanza per il Mustang
        // all'aumento della distanza per l'abilità di Paul Regret
        if (isMustangInGioco()) {
            aumentoDistanzaTotale += AUMENTO_DISTANZA_MUSTANG;
        }
        return aumentoDistanzaTotale;
    }

    private boolean isMustangInGioco() {
        // Implementazione del controllo se c'è un Mustang reale in gioco
        // (da completare in base alla logica del tuo gioco)
        return false; // Implementazione di esempio, da sostituire con la logica reale del gioco
    }

    @Override
    public void attivaAbilitaSpeciale(Giocatore giocatore, Mazzo mazzo) {
        // Aumenta la distanza a cui è visto di 1
        giocatore.setDistanzaVista(giocatore.getDistanzaVista() + 1);
        System.out.println(giocatore.getNome() + " attiva l'abilità speciale di Paul Regret. È visto a una distanza di " + (giocatore.getDistanzaVista() + 1));
    }

}
