package it.unibs.fp.esame;
import java.util.*;

/**
 * L'enumerazione Ruolo rappresenta i diversi ruoli dei giocatori in una partita di Bang!.
 * Ogni ruolo ha un obiettivo specifico nel gioco.
 */
public enum Ruolo {
    SCERIFFO("Eliminare tutti i Fuorilegge e il Rinnegato"),
    FUORILEGGE("Eliminare lo Sceriffo"),
    VICE("Proteggere lo Sceriffo e perseguire i suoi stessi obiettivi"),
    RINNEGATO("Rimanere l'ultimo personaggio in gioco e diventare il nuovo Sceriffo");

    private final String obiettivo; // L'obiettivo associato al ruolo

    /**
     * Costruisce un nuovo ruolo con l'obiettivo specificato.
     * @param obiettivo l'obiettivo associato al ruolo
     */
    Ruolo(String obiettivo) {
        this.obiettivo = obiettivo;
    }

    /**
     * Restituisce l'obiettivo associato al ruolo.
     * @return l'obiettivo associato al ruolo
     */
    public String getObiettivo() {
        return obiettivo;
    }

    /**
     * Restituisce la lista dei ruoli ancora disponibili.
     * @return la lista dei ruoli ancora disponibili
     */
    public static ArrayList<Ruolo> getRuoliDisponibili() {
        return new ArrayList<>(Arrays.asList(Ruolo.values()));
    }
}
