package it.unibs.fp.esame;

import java.util.ArrayList;
import java.util.Collections;

public abstract class Personaggio {
    private String nome;
    private int puntiFerita;

    public Personaggio(String nome, int puntiFerita) {
        this.nome = nome;
        this.puntiFerita = puntiFerita;
    }

    public String getNome() {
        return nome;
    }

    public int getPuntiFerita() {
        return puntiFerita;
    }


    public abstract void attivaAbilitaSpeciale(Giocatore giocatore, Mazzo mazzo);

    /**
     * Restituisce un personaggio casuale dalla lista dei personaggi disponibili, evitando doppioni.
     * @param personaggiDisponibili Lista dei personaggi tra cui scegliere.
     * @return Il personaggio casuale.
     */
    public static Personaggio getPersonaggioCasuale(ArrayList<Personaggio> personaggiDisponibili) {
        Collections.shuffle(personaggiDisponibili);
        return personaggiDisponibili.remove(0);
    }
}
