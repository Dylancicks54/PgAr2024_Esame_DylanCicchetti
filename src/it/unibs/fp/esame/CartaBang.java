package it.unibs.fp.esame;

import java.util.ArrayList;
import java.util.Scanner;
import it.kibo.fp.lib.InputData;

/**
 * La classe CartaBang rappresenta una carta del gioco Bang! che permette
 * al giocatore di attaccare un altro giocatore entro una determinata gittata.
 * Estende la classe astratta Carta.
 */
public class CartaBang extends Carta {
    public static final String NO_PLAYER = "Non ci sono giocatori nella gittata della tua carta Bang.";
    public static final String PLAYER_AVAL = "Giocatori disponibili nella gittata della tua carta Bang:";
    public static final String TARGET_SELE = "Seleziona il giocatore da colpire: ";
    public static final String ERR_FAR_DIST = "Bersaglio troppo lontano!";
    private int gittata;

    /**
     * Costruttore della classe CartaBang.
     *
     * @param gittata La gittata della carta Bang.
     */
    public CartaBang(int gittata) {
        super("Bang!", "Gioca e Scarta");
        this.gittata = gittata;
    }

    /**
     * Applica l'effetto della carta Bang a un giocatore nel gioco.
     *
     * @param giocatore Il giocatore che sta utilizzando la carta Bang.
     * @param gioco     Il gioco in cui si sta giocando.
     */
    @Override
    public void applicaEffetto(Giocatore giocatore, Gioco gioco) {
        Scanner scanner = new Scanner(System.in);

        // Chiedi al giocatore bersaglio
        Giocatore bersaglio = scegliBersaglio(giocatore, gioco);

        // Controllo sulla distanza e l'arma
        int distanza = giocatore.calcolaDistanza(bersaglio);
        if (distanza <= getGittata()) {
            // Chiedi al giocatore bersaglio se vuole giocare una carta Mancato!
            System.out.println(bersaglio.getNome() + ", vuoi giocare una carta Mancato? (s/n)");
            String risposta = scanner.nextLine();
            if (risposta.equalsIgnoreCase("s")) {
                // Il flag bangSubito viene impostato automaticamente quando il giocatore gioca una carta Mancato
                bersaglio.giocaCartaMancato(Main.mazzo);
                return; // Esci dal metodo senza applicare l'effetto del Bang!
            }

            // Applica l'effetto del Bang! solo se il giocatore bersaglio non ha giocato una carta Mancato!
            if (!bersaglio.isBangSubito()) {
                bersaglio.perdiPuntoFerita();
                System.out.println(bersaglio.getNome() + " perde un punto ferita!");
                System.out.println("Ti rimangono " + bersaglio.getPuntiFerita() + " punti ferita");
            } else {
                System.out.println(bersaglio.getNome() + " ha usato una carta Mancato.");
            }
        } else {
            System.out.println(ERR_FAR_DIST);
        }
    }

    /**
     * Restituisce la gittata della carta Bang.
     *
     * @return La gittata della carta Bang.
     */
    public int getGittata() {
        return gittata;
    }

    /**
     * Metodo privato per scegliere un bersaglio per la carta Bang.
     *
     * @param giocatore Il giocatore che sta utilizzando la carta Bang.
     * @param gioco     Il gioco in cui si sta giocando.
     * @return Il giocatore bersaglio scelto.
     */
    private Giocatore scegliBersaglio(Giocatore giocatore, Gioco gioco) {
        ArrayList<Giocatore> giocatoriNellaGittata = new ArrayList<>();

        // Aggiungi tutti i giocatori che sono entro la gittata disponibile della carta Bang giocata
        for (Giocatore g : gioco.getGiocatori()) {
            if (!g.equals(giocatore) && !g.isEliminato() && giocatore.calcolaDistanza(g) <= giocatore.getGittataBang()) {
                giocatoriNellaGittata.add(g);
            }
        }

        // Se non ci sono giocatori nella gittata, restituisci null
        if (giocatoriNellaGittata.isEmpty()) {
            System.out.println(NO_PLAYER);
            return null;
        }

        // Mostra i giocatori disponibili e chiedi di selezionare un bersaglio
        System.out.println(PLAYER_AVAL);
        for (int i = 0; i < giocatoriNellaGittata.size(); i++) {
            System.out.println((i + 1) + ": " + giocatoriNellaGittata.get(i).getNome());
        }

        Scanner scanner = new Scanner(System.in);
        int indiceBersaglio = InputData.readInteger(TARGET_SELE) -1;
        return giocatoriNellaGittata.get(indiceBersaglio);
    }
}
