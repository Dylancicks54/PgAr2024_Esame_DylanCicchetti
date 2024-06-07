package it.unibs.fp.esame;

public class RoseDoolan extends Personaggio{
    private static final int PUNTI_VITA = 4; // Punti vita di Rose Doolan
    private static final int DIMINUZIONE_DISTANZA_ABILITA = 1; // Diminuzione della distanza a cui vede gli altri grazie all'abilità
    private static final int DIMINUZIONE_DISTANZA_MIRINO = 1; // Diminuzione della distanza a cui vede gli altri se c'è un Mirino reale in gioco

    private int puntiVita;

    public RoseDoolan() {
        super("RoseDoolan", PUNTI_VITA);

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

    public int getDiminuzioneDistanza() {
        int diminuzioneDistanzaTotale = DIMINUZIONE_DISTANZA_ABILITA;
        // Se c'è un Mirino reale in gioco, aggiunge la diminuzione della distanza per il Mirino
        // alla diminuzione della distanza per l'abilità di Rose Doolan
        if (isMirinoInGioco()) {
            diminuzioneDistanzaTotale += DIMINUZIONE_DISTANZA_MIRINO;
        }
        return diminuzioneDistanzaTotale;
    }

    private boolean isMirinoInGioco() {
        // Implementazione del controllo se c'è un Mirino reale in gioco
        // (da completare in base alla logica del tuo gioco)
        return false; // Implementazione di esempio, da sostituire con la logica reale del gioco
    }

    @Override
    public void attivaAbilitaSpeciale(Giocatore giocatore, Mazzo mazzo) {
        System.out.println("Giocatore " + giocatore.getNome() + " attiva l'abilità speciale di Rose Doolan.");
        System.out.println("È visto a una distanza di -2.");
        // Implementa qui la logica specifica per Rose Doolan, se necessario
    }


}
