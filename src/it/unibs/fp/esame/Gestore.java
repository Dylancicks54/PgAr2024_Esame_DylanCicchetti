package it.unibs.fp.esame;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * La classe Gestore gestisce il turno di gioco per i giocatori, permettendo loro di pescare carte, giocare carte,
 * scartare carte in eccesso e inviare messaggi di provocazione cifrati durante il gioco.
 */
public class Gestore {
    /**
     * Testo per la selezione dell'avversario a cui inviare il messaggio di provocazione.
     */
    public static final String SELECT_AVV = "Seleziona un avversario a cui inviare il messaggio di provocazione:";

    /**
     * Messaggio di errore per indice non valido.
     */
    public static final String INVALID_INDX = "Indice non valido.";

    private Giocatore giocatoreCorrente;
    private ArrayList<Giocatore> giocatori;
    private int indiceCorrente;
    private Mazzo mazzo;
    private Scanner scanner;
    Cifrario cf = new Cifrario();

    /**
     * Costruisce un nuovo oggetto Gestore con la lista dei giocatori e il mazzo specificati.
     *
     * @param giocatori la lista dei giocatori partecipanti al gioco
     * @param mazzo     il mazzo di carte utilizzato nel gioco
     */
    public Gestore(ArrayList<Giocatore> giocatori, Mazzo mazzo) {
        this.giocatori = giocatori;
        this.mazzo = mazzo;
        this.indiceCorrente = 0;
        this.giocatoreCorrente = giocatori.get(indiceCorrente);
        this.scanner = new Scanner(System.in);
    }

    /**
     * Passa al prossimo turno nel gioco.
     */
    public void prossimoTurno() {
        indiceCorrente = (indiceCorrente + 1) % giocatori.size();
        giocatoreCorrente = giocatori.get(indiceCorrente);
    }

    /**
     * Gestisce il turno di gioco per il giocatore specificato.
     *
     * @param g Il giocatore di cui gestire il turno.
     */
    public void gestisciTurno(Giocatore g) {
        // Fase 1: Pescare due carte
        System.out.println(g.getNome() + " pesca due carte.");
        g.pescaCarta(mazzo.pescaCarta());
        g.pescaCarta(mazzo.pescaCarta());

        // Fase 2: Giocare carte
        boolean finitoDiGiocare = false;
        while (!finitoDiGiocare && !g.getMano().isEmpty()) {
            System.out.println(g.getNome() + ", vuoi giocare una carta? (s/n)");
            String risposta = scanner.nextLine();
            if (risposta.equalsIgnoreCase("s")) {
                mostraMano(g);
                System.out.println("Seleziona una carta da giocare (indice):");
                int indiceCarta = scanner.nextInt();
                scanner.nextLine(); // Consuma il newline

                if (indiceCarta >= 0 && indiceCarta < g.getMano().size()) {
                    Carta cartaDaGiocare = g.getMano().get(indiceCarta);
                    if (cartaDaGiocare instanceof CartaBang && g.haGiocatoBang()) {
                        System.out.println("Hai già giocato una carta Bang! questo turno.");
                    } else {
                        g.giocaCarta(cartaDaGiocare);
                        mazzo.scartaCarta(cartaDaGiocare);
                        if (cartaDaGiocare instanceof CartaBang) {
                            g.setHaGiocatoBang(true);
                        }
                    }
                } else {
                    System.out.println("Indice non valido.");
                }
            } else {
                finitoDiGiocare = true;
            }
        }

        // Fase 3: Scartare le carte in eccesso
        System.out.println(g.getNome() + " scarta le carte in eccesso.");
        g.scartaCarteInEccesso();

        System.out.println("Abilità speciali dei personaggi:");
        for (Giocatore giocatore : giocatori) {
            Personaggio personaggio = giocatore.getPersonaggio();
            if (personaggio instanceof BartCassidy) {
                ((BartCassidy) personaggio).attivaAbilitaSpeciale(giocatore, mazzo);
            } else if (personaggio instanceof ElGringo) {
                ((ElGringo) personaggio).attivaAbilitaSpeciale(giocatore, mazzo);
            } else if (personaggio instanceof Jourdonnais) {
                ((Jourdonnais) personaggio).attivaAbilitaSpeciale(giocatore, mazzo);
            } else if (personaggio instanceof PaulRegret) {
                ((PaulRegret) personaggio).attivaAbilitaSpeciale(giocatore, mazzo);
            } else if (personaggio instanceof RoseDoolan) {
                ((RoseDoolan) personaggio).attivaAbilitaSpeciale(giocatore, mazzo);
            } else if (personaggio instanceof SidKetchum) {
                ((SidKetchum) personaggio).attivaAbilitaSpeciale(giocatore, mazzo);
            }
        }
            // Offerta per provocazione
            System.out.println(g.getNome() + ", vuoi inserire un messaggio di provocazione? (s/n)");
            String rispostaProvocazione = scanner.nextLine();
            if (rispostaProvocazione.equalsIgnoreCase("s")) {
                System.out.println("Scegli il giocatore da provocare");
                for (int i = 0; i < giocatori.size(); i++) {
                    if (giocatori.get(i) != g) {
                        System.out.println(i + ": " + giocatori.get(i).getNome());
                    }
                }
                int indice = scanner.nextInt();
                scanner.nextLine();
                Giocatore flammato = null;
                if (indice >= 0 && indice < giocatori.size() && giocatori.get(indice) != g) {
                    flammato = giocatori.get(indice);
                }
                System.out.println("Inserisci il messaggio da cifrare e mandare a " + flammato.getNome() + ": ");
                cf.cifra();
            }

            // Passare al prossimo turno
            prossimoTurno();

    }

        /**
         * Mostra le carte nella mano del giocatore specificato.
         * @param g Il giocatore di cui mostrare la mano.
         */
        private void mostraMano (Giocatore g){
            System.out.println("Carte in mano di " + g.getNome() + ":");
            ArrayList<Carta> mano = g.getMano();
            for (int i = 0; i < mano.size(); i++) {
                Carta carta = mano.get(i);
                System.out.println(i + ": " + carta.getNome() + " (" + carta.getTipo() + ")");
            }
        }
    }

