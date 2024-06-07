package it.unibs.fp.esame;

/**
 * La classe CartaMancato rappresenta una carta che permette al giocatore di annullare
 * un attacco di tipo "Bang!" subito durante il proprio turno.
 * Estende la classe astratta Carta.
 */
public class CartaMancato extends Carta {

    /** Messaggio di errore nel caso in cui la carta Mancato venga giocata senza un mittente del Bang associato. */
    public static final String ERR = "Errore: la carta Mancato è stata giocata ma non c'è un mittente del Bang associato.";

    /**
     * Costruttore della classe CartaMancato.
     * Crea una carta Mancato con nome "Mancato!" e tipo "Gioca e Scarta".
     */
    public CartaMancato() {
        super("Mancato!", "Gioca e Scarta");
    }

    /**
     * Applica l'effetto della carta Mancato.
     * Se il giocatore ha subito un attacco di tipo "Bang!" durante il turno corrente,
     * questa carta annulla l'attacco. Se la carta viene giocata senza un mittente del Bang associato,
     * viene stampato un messaggio di errore.
     *
     * @param giocatore Il giocatore che sta giocando la carta Mancato.
     * @param gioco     Il gioco in cui si sta giocando.
     */
    @Override
    public void applicaEffetto(Giocatore giocatore, Gioco gioco) {
        // Verifica se il giocatore ha subito un Bang durante il turno corrente
        if (!giocatore.isBangSubito()) {
            System.out.println(giocatore.getNome() + " non può giocare la carta Mancato perché non ha subito un Bang!");
            return;
        }

        // Applica l'effetto della carta Mancato solo se è stata giocata in risposta a un Bang
        Giocatore mittenteBang = giocatore.getMittenteBang();
        if (mittenteBang != null) {
            System.out.println(giocatore.getNome() + " annulla il Bang di " + mittenteBang.getNome() + " con la carta Mancato!");
            // Resetta il flag del Bang subito dal giocatore
            giocatore.setBangSubito(false);
            // Resetta il mittente del Bang per il giocatore
            giocatore.setMittenteBang(null);
        } else {
            System.out.println(ERR);
        }
    }

}
