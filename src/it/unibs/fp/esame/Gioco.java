package it.unibs.fp.esame;

import java.util.ArrayList;
import java.util.Collections;


/**
 * La classe Gioco gestisce il flusso di gioco e le dinamiche di una partita.
 * È responsabile di avviare la partita, gestire i turni dei giocatori, determinare il vincitore e gestire la conclusione del gioco.
 */
public class Gioco {
    private ArrayList<Giocatore> giocatori;
    private Mazzo mazzo;
    private Gestore gestoreTurni;

    /**
     * Costruisce un nuovo oggetto Gioco con la lista dei giocatori e il mazzo specificati.
     * @param giocatori la lista dei giocatori partecipanti al gioco
     * @param mazzo il mazzo di carte utilizzato nel gioco
     */
    public Gioco(ArrayList<Giocatore> giocatori, Mazzo mazzo) {
        this.giocatori = giocatori;
        this.mazzo = mazzo;
        this.gestoreTurni = new Gestore(giocatori, Main.mazzo);

    }

    public ArrayList<Giocatore> getGiocatori() {
        return giocatori;
    }


    /**
     * Avvia una nuova partita, gestendo i turni dei giocatori fino alla conclusione del gioco.
     */
    public void avviaPartita() {
        boolean giocoFinito = false;

        // Inizializza i giocatori
        for (Giocatore g : giocatori) {
            if (g.getRuolo() == Ruolo.SCERIFFO) {
                g.setPuntiFerita(5);
            } else {
                g.setPuntiFerita(4);
            }
            for (int i = 0; i < g.getPuntiFerita(); i++) {
                g.pescaCarta(mazzo.pescaCarta());
            }
        }

        // Ciclo di gioco
        while (!giocoFinito) {
            for (int i = 0; i < giocatori.size(); i++) {
                gestoreTurni.gestisciTurno(giocatori.get(i));
                if (giocoTerminato()) {
                    giocoFinito = true;
                    break;
                }
            }
        }

        gestisciConclusioneGioco();
    }


    /**
     * Gestisce la conclusione del gioco, determinando il vincitore e assegnando i premi.
     */
    public void gestisciConclusioneGioco() {
        // Determina il vincitore della partita
        Giocatore vincitore = determinaVincitore();

        if (vincitore != null) {
            // Aggiorna la classifica del vincitore
            for (GiocatoreClassifica gc : Main.classifica) {
                if (gc.getNome().equals(vincitore.getNome())) {
                    gc.aggiungiSoldi(200); // Aggiungi 200 sbleuri al vincitore
                    break;
                }
            }
        } else {
            System.out.println("La partita è ancora in corso!"); // Non ci sono vincitori definiti
        }
    }



    /**
     * Verifica se il gioco è terminato, ossia se è stata soddisfatta una delle condizioni di fine partita.
     * @return true se il gioco è terminato, false altrimenti
     */
    private boolean giocoTerminato() {
        int sceriffoInGioco = 0;
        int fuorileggeInGioco = 0;
        int rinnegatoInGioco = 0;

        for (Giocatore g : giocatori) {
            if (g.getPuntiFerita() > 0) {
                if (g.getRuolo() == Ruolo.SCERIFFO) {
                    sceriffoInGioco++;
                } else if (g.getRuolo() == Ruolo.FUORILEGGE) {
                    fuorileggeInGioco++;
                } else if (g.getRuolo() == Ruolo.RINNEGATO) {
                    rinnegatoInGioco++;
                }
            }
        }

        return (sceriffoInGioco == 0) || (fuorileggeInGioco == 0 && rinnegatoInGioco == 0);
    }

    /**
     * Determina il vincitore della partita in base agli ultimi giocatori rimasti in gioco.
     * @return il giocatore vincitore della partita, null se non c'è un vincitore definito
     */
    public Giocatore determinaVincitore() {
        Giocatore vincitore = null;

        // Controlla se rimane solo un ruolo tra i giocatori
        int numSceriffi = 0;
        int numFuorilegge = 0;
        int numRinnegati = 0;

        for (Giocatore giocatore : giocatori) {
            switch (giocatore.getRuolo()) {
                case SCERIFFO:
                    numSceriffi++;
                    break;
                case FUORILEGGE:
                    numFuorilegge++;
                    break;
                case RINNEGATO:
                    numRinnegati++;
                    break;
            }
        }

        // Se rimane solo un ruolo, determina il vincitore in base a quello
        if (numSceriffi == 1 && numFuorilegge == 0 && numRinnegati == 0) {
            for (Giocatore giocatore : giocatori) {
                if (giocatore.getRuolo() == Ruolo.SCERIFFO) {
                    vincitore = giocatore;
                    break;
                }
            }
        } else if (numFuorilegge > 0 && numSceriffi == 0 && numRinnegati == 0) {
            // Se ci sono fuorilegge rimasti e nessun altro ruolo, i fuorilegge vincono
            for (Giocatore giocatore : giocatori) {
                if (giocatore.getRuolo() == Ruolo.FUORILEGGE) {
                    vincitore = giocatore;
                    break;
                }
            }
        } else if (numRinnegati > 0 && numSceriffi == 0 && numFuorilegge == 0) {
            // Se ci sono rinnegati rimasti e nessun altro ruolo, i rinnegati vincono
            for (Giocatore giocatore : giocatori) {
                if (giocatore.getRuolo() == Ruolo.RINNEGATO) {
                    vincitore = giocatore;
                    break;
                }
            }
        }

        return vincitore;
    }
}
