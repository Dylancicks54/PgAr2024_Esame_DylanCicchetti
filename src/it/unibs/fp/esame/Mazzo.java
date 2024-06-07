package it.unibs.fp.esame;

import java.util.Collections;
import java.util.ArrayList;

/**
 * La classe Mazzo rappresenta un mazzo di carte.
 * È costituita da una lista di carte e una lista di carte scartate.
 */
public class Mazzo {
    private ArrayList<Carta> carte; // La lista delle carte nel mazzo
    private ArrayList<Carta> scarti; // La lista delle carte scartate

    /**
     * Costruisce un nuovo oggetto Mazzo con le carte specificate.
     * Il mazzo viene mescolato automaticamente alla creazione.
     * @param carte la lista delle carte da inserire nel mazzo
     */
    public Mazzo(ArrayList<Carta> carte) {
        this.carte = new ArrayList<>(carte);
        this.scarti = new ArrayList<>();
        mescola();
    }

    /**
     * Mescola le carte nel mazzo.
     */
    public void mescola() {
        Collections.shuffle(carte);
    }

    /**
     * Pesca una carta dal mazzo.
     * Se il mazzo è vuoto, le carte scartate vengono mescolate e reinserite nel mazzo.
     * @return la carta pescata, null se il mazzo è vuoto
     */
    public Carta pescaCarta() {
        if (carte.isEmpty()) {
            rimescolaScarti();
        }
        return carte.isEmpty() ? null : carte.remove(0);
    }

    /**
     * Scarta una carta, aggiungendola alla lista delle carte scartate.
     * @param c la carta da scartare
     */
    public void scartaCarta(Carta c) {
        scarti.add(c);
    }

    /**
     * Rimescola le carte scartate e le reinserisce nel mazzo.
     */
    private void rimescolaScarti() {
        carte.addAll(scarti);
        scarti.clear();
        mescola();
    }

    public void aggiungiCarta(Carta carta) {
        carte.add(carta);
    }
}
