package it.unibs.fp.esame;

import it.kibo.fp.lib.InputData;

import java.util.ArrayList;

public class SidKetchum extends Personaggio {
    private static final int PUNTI_VITA = 4; // Punti vita di Sid Ketchum

    private int puntiVita;
    private ArrayList<Carta> mano;

    public SidKetchum() {
        super("SidKetchum", PUNTI_VITA);
        this.mano = new ArrayList<>();
    }

    public int getPuntiVita() {
        return puntiVita;
    }

    public void perditaPuntoVita() {
        puntiVita--;
        if (puntiVita < 0) {
            puntiVita = 0;
        }
    }

    public void recuperoPuntoVita() {
        // Sid Ketchum può recuperare un punto vita scartando due carte dalla mano
        if (puntiVita < PUNTI_VITA) {
            // Controlla se ci sono abbastanza carte in mano per il recupero
            if (mano.size() >= 2) {
                // Effettua il recupero
                puntiVita++;
                // Rimuovi due carte dalla mano
                mano.remove(0);
                mano.remove(0);
                System.out.println("Sid Ketchum recupera un punto vita.");
            } else {
                System.out.println("Non hai abbastanza carte in mano per recuperare un punto vita.");
            }
        } else {
            System.out.println("Sid Ketchum ha già raggiunto il massimo dei punti vita.");
        }
    }

    // Metodo per aggiungere una carta alla mano di Sid Ketchum
    public void aggiungiCarta(Carta carta) {
        mano.add(carta);
    }

    @Override
    public void attivaAbilitaSpeciale(Giocatore giocatore, Mazzo mazzo) {
        // Controlla se il giocatore può recuperare un punto ferita
        if (giocatore.getPuntiFerita() < giocatore.getPuntiFeritaIniziali()) {
            // Controlla se il giocatore ha almeno 2 carte nella mano
            if (giocatore.getMano().size() >= 2) {
                System.out.println(giocatore.getNome() + " attiva l'abilità speciale di Sid Ketchum.");
                System.out.println("Scarta 2 carte dalla tua mano per recuperare 1 punto ferita.");

                // Scarta due carte dalla mano
                for (int i = 0; i < 2; i++) {
                    int indiceCarta = InputData.readInteger("Seleziona una carta da scartare (indice):");

                    if (indiceCarta >= 0 && indiceCarta < giocatore.getMano().size()) {
                        Carta cartaDaScartare = giocatore.getMano().get(indiceCarta);
                        giocatore.scartaCarta(cartaDaScartare);
                    } else {
                        System.out.println("Indice non valido. La carta non è stata scartata.");
                    }
                }

                // Recupera un punto ferita
                giocatore.recuperaPuntiFerita();
            } else {
                System.out.println("Non hai abbastanza carte in mano per attivare l'abilità speciale di Sid Ketchum.");
            }
        } else {
            System.out.println("Hai già raggiunto il massimo dei punti ferita.");
        }
    }

}
