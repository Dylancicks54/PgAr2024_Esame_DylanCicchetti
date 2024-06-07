package it.unibs.fp.esame;

/**
 * Classe astratta che rappresenta una carta del gioco.
 * Ogni carta ha un nome e un tipo, e può applicare un effetto
 * a un giocatore all'interno di un gioco.
 */
public abstract class Carta {
    private String nome;
    private String tipo;

    /**
     * Costruttore della classe Carta.
     *
     * @param nome Il nome della carta.
     * @param tipo Il tipo della carta.
     */
    public Carta(String nome, String tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }

    /**
     * Restituisce il nome della carta.
     *
     * @return Il nome della carta.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Restituisce il tipo della carta.
     *
     * @return Il tipo della carta.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Metodo astratto che applica l'effetto della carta a un giocatore
     * all'interno di un gioco.
     *
     * @param giocatore Il giocatore a cui applicare l'effetto della carta.
     * @param gioco     Il gioco in cui si sta giocando.
     */
    public abstract void applicaEffetto(Giocatore giocatore, Gioco gioco);
}
