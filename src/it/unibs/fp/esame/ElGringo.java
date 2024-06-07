package it.unibs.fp.esame;

import java.util.ArrayList;

public class ElGringo extends Personaggio{
    private static final int PUNTI_VITA = 3; // Punti vita di El Gringo

    private int puntiVita;

    public ElGringo() {
        super("ElGringo", PUNTI_VITA);
    }

    public void perditaPuntoVita(Giocatore avversario, Mazzo mazzo) {
        puntiVita--;
        if (puntiVita < 0) {
            puntiVita = 0;
        }
        // Se l'avversario ha carte in mano, pesca una carta da quella mano per ogni punto vita perso
        for (int i = 0; i < puntiVita; i++) {
            if (!avversario.getMano().isEmpty()) {
                Carta cartaPescata = avversario.getMano().remove((int) (Math.random() * avversario.getMano().size()));
                mazzo.aggiungiCarta(cartaPescata);
                System.out.println("El Gringo pesca una carta dalla mano di " + avversario.getNome() + ": " + cartaPescata);
            } else {
                System.out.println(avversario.getNome() + " non ha carte in mano. El Gringo non pesca.");
            }
        }
    }

    public int getPuntiVita() {
        return puntiVita;
    }

    public void setPuntiVita(int puntiVita) {
        this.puntiVita = puntiVita;
    }


    @Override
    public void attivaAbilitaSpeciale(Giocatore giocatore, Mazzo mazzo) {
        // Controllo se il giocatore ha perso un punto ferita a causa di una carta giocata da un altro giocatore
        if (giocatore.getPuntiFerita() < this.getPuntiFerita()) {
            System.out.println(giocatore.getNome() + " attiva l'abilità speciale di El Gringo.");
            int puntiFeritaPersi = this.getPuntiFerita() - giocatore.getPuntiFeritaIniziali();
            Giocatore giocatoreCheHaGiocatoCarta = giocatore.getGiocatoreCheHaGiocatoUltimaCarta();
            if (giocatoreCheHaGiocatoCarta != null) {
                for (int i = 0; i < puntiFeritaPersi; i++) {
                    Carta cartaCasuale = giocatoreCheHaGiocatoCarta.getPescaCasualeCartaDallaMano();
                    if (cartaCasuale != null) {
                        giocatore.pescaCarta(cartaCasuale);
                        System.out.println(giocatore.getNome() + " pesca una carta da " + giocatoreCheHaGiocatoCarta.getNome() + ": " + cartaCasuale.getNome());
                    } else {
                        System.out.println(giocatoreCheHaGiocatoCarta.getNome() + " non ha carte in mano, El Gringo non pesca.");
                    }
                }
            }
        }
    }
}
