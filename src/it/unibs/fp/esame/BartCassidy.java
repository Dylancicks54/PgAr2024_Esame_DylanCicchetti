package it.unibs.fp.esame;

import java.util.ArrayList;

public class BartCassidy extends Personaggio{
    private static final int PUNTI_VITA = 4; // Punti vita di Bart Cassidy
    private static final int NUM_CARTE_PESCA = 1; // Numero di carte da pescare quando perde un punto vita

    private int puntiVita;
    private ArrayList<Carta> mano;

    public BartCassidy() {
        super("BartCassidy", PUNTI_VITA);
        this.mano = new ArrayList<>();
    }

    public void perditaPuntoVita(Mazzo mazzo) {
        puntiVita--;
        if (puntiVita < 0) {
            puntiVita = 0;
        }
        // Pesca una carta dal mazzo per ogni punto vita perso
        for (int i = 0; i < NUM_CARTE_PESCA; i++) {
            Carta cartaPescata = mazzo.pescaCarta();
            mano.add(cartaPescata);
            System.out.println("Bart Cassidy pesca una carta: " + cartaPescata);
        }
    }

    public int getPuntiVita() {
        return puntiVita;
    }

    public void setPuntiVita(int puntiVita) {
        this.puntiVita = puntiVita;
    }

    public ArrayList<Carta> getMano() {
        return mano;
    }

    public void aggiungiCartaInMano(Carta carta) {
        mano.add(carta);
    }

    public void rimuoviCartaDallaMano(Carta carta) {
        mano.remove(carta);
    }

    @Override
    public void attivaAbilitaSpeciale(Giocatore giocatore, Mazzo mazzo) {
        if (giocatore.getPuntiFerita() < giocatore.getPuntiFeritaIniziali()) {
            Carta cartaPescata = mazzo.pescaCarta();
            System.out.println(giocatore.getNome() + " attiva l'abilità speciale di Bart Cassidy: pesca una carta.");
            giocatore.pescaCarta(cartaPescata);
        }
    }
}
