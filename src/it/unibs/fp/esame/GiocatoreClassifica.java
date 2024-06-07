package it.unibs.fp.esame;

/**
 * La classe GiocatoreClassifica rappresenta un giocatore all'interno di una classifica.
 * Ogni giocatore ha un nome, un totale di sbleuri accumulati, il numero di partite giocate e un punteggio calcolato
 * in base al rapporto tra il totale di sbleuri e il numero di partite giocate.
 */
public class GiocatoreClassifica {
    private String nome; // Il nome del giocatore
    private int totSbleuri; // Il totale di sbleuri accumulati dal giocatore
    private int partiteGiocate; // Il numero di partite giocate dal giocatore
    private double punteggio; // Il punteggio del giocatore, calcolato come rapporto tra totSbleuri e partiteGiocate

    /**
     * Costruisce un nuovo oggetto GiocatoreClassifica con i parametri specificati.
     * @param nome il nome del giocatore
     * @param totSbleuri il totale di sbleuri accumulati dal giocatore
     * @param partiteGiocate il numero di partite giocate dal giocatore
     */
    public GiocatoreClassifica(String nome, int totSbleuri, int partiteGiocate) {
        this.nome = nome;
        this.totSbleuri = totSbleuri;
        this.partiteGiocate = partiteGiocate;
        this.punteggio = (partiteGiocate > 0) ? (double) totSbleuri / partiteGiocate : 0;
    }

    /**
     * Restituisce il nome del giocatore.
     * @return il nome del giocatore
     */
    public String getNome() {
        return nome;
    }
    public void aggiungiSoldi(int sbleuriagg) {
        totSbleuri += sbleuriagg;
    }
    /**
     * Restituisce il totale di sbleuri accumulati dal giocatore.
     * @return il totale di sbleuri accumulati dal giocatore
     */
    public int getTotSbleuri() {
        return totSbleuri;
    }

    /**
     * Restituisce il numero di partite giocate dal giocatore.
     * @return il numero di partite giocate dal giocatore
     */
    public int getPartiteGiocate() {
        return partiteGiocate;
    }

    /**
     * Restituisce il punteggio del giocatore, calcolato come rapporto tra totSbleuri e partiteGiocate.
     * @return il punteggio del giocatore
     */
    public double getPunteggio() {
        return punteggio;
    }
}
