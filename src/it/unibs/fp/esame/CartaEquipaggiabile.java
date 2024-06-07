package it.unibs.fp.esame;

/**
 * La classe CartaEquipaggiabile rappresenta una carta equipaggiabile del gioco.
 * Estende la classe astratta Carta.
 */
public class CartaEquipaggiabile extends Carta {
    private String nomeArma;
    private int gittataArma;

    /**
     * Costruttore della classe CartaEquipaggiabile.
     *
     * @param nomeArma   Il nome dell'arma equipaggiabile.
     * @param gittataArma La gittata dell'arma equipaggiabile.
     */
    public CartaEquipaggiabile(String nomeArma, int gittataArma) {
        super("Equipaggiabile", "Blu"); // Tipo e colore della carta
        this.nomeArma = nomeArma;
        this.gittataArma = gittataArma;
    }

    /**
     * Applica l'effetto della carta equipaggiabile.
     * Poiché la carta equipaggiabile non ha un effetto diretto,
     * questo metodo è vuoto e non fa nulla.
     *
     * @param giocatore Il giocatore a cui applicare l'effetto.
     * @param gioco     Il gioco in cui si sta giocando.
     */
    @Override
    public void applicaEffetto(Giocatore giocatore, Gioco gioco) {
        // Metodo vuoto poiché la carta equipaggiabile non ha effetti diretti.
    }
}
