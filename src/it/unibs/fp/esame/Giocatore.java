package it.unibs.fp.esame;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * La classe Giocatore rappresenta un giocatore all'interno del gioco.
 * Ogni giocatore ha un nome, un ruolo, un certo numero di punti ferita,
 * una determinata gittata per le carte Bang!, e una serie di carte in mano.
 * Può anche equipaggiare delle armi e ricevere messaggi di provocazione.
 */
public class Giocatore {
    public static final String ERROR_EQUIP = "Hai già un'arma equipaggiata. La nuova arma non può essere equipaggiata.";
    public static final String CHOOSE_MANCATO = "Seleziona una carta Mancato! da giocare (indice):";
    public static final String ERR_INDEX = "Indice non valido.";
    public static final String NO_MANCATO_HAND = "Non hai carte Mancato! in mano!";
    public static final String SHOW_MANC_CARD = "Carte Mancato in mano:";
    public static final String SORRY = "Non puoi giocare la carta Bang contro questo giocatore.";

    private String nome;
    private Ruolo ruolo;
    private int puntiFerita;
    private int gittataBang;
    private boolean bangUsato;
    private boolean bangSubito;
    private boolean haGiocatoBang;
    private Mazzo mazzo;
    private Giocatore mittenteBang;
    private ArrayList<Carta> mano;
    private ArrayList<Carta> carteEquipaggiate;
    private ArrayList<CartaEquipaggiabile> carteArma;
    private ArrayList<Giocatore> giocatori;
    private String messaggioProvocazione;
    private int soldi;
    private Personaggio personaggio;
    private Giocatore giocatoreCheHaGiocatoUltimaCarta;
    public boolean haRicevutoColpo;
    private int distanzaVista;
    public static int puntiFeritaIniziali = 4;



    /**
     * Costruttore della classe Giocatore.
     * @param nome Il nome del giocatore.
     * @param ruolo Il ruolo del giocatore.
     * @param giocatori La lista dei giocatori.
     * @param mazzo Il mazzo del gioco.
     */
    public Giocatore(String nome, Ruolo ruolo, ArrayList<Giocatore> giocatori, Mazzo mazzo, Personaggio personaggio) {
        this.nome = nome;
        this.ruolo = ruolo;
        this.giocatori = giocatori;
        this.mano = new ArrayList<>();
        this.carteEquipaggiate = new ArrayList<>();
        this.bangUsato = false;
        this.bangSubito = false;
        this.mazzo = mazzo;
        this.gittataBang = 1;
        this.carteArma = new ArrayList<>();
        this.messaggioProvocazione = "";
        this.soldi = 500;
        this.personaggio = personaggio;
        giocatoreCheHaGiocatoUltimaCarta = this;


    }

    public void setRuolo(Ruolo ruolo) {
        this.ruolo = ruolo;
    }

    public Personaggio getPersonaggio() {
        return personaggio;
    }

    // Metodo per ottenere il giocatore che ha giocato l'ultima carta
    public Giocatore getGiocatoreCheHaGiocatoUltimaCarta() {
        return giocatoreCheHaGiocatoUltimaCarta;
    }
    public void setPersonaggio(Personaggio personaggio) {
        this.personaggio = personaggio;
    }

    public ArrayList<Carta> getMano() {
        return mano;
    }

    public String getNome() {
        return nome;
    }

    public Ruolo getRuolo() {
        return ruolo;
    }

    public int getDistanzaVista() {
        return distanzaVista;
    }

    public void setDistanzaVista(int distanzaVista) {
        this.distanzaVista = distanzaVista;
    }

    public boolean haRicevutoColpo() {
        return haRicevutoColpo;
    }

    public void setHaRicevutoColpo(boolean haRicevutoColpo) {
        this.haRicevutoColpo = haRicevutoColpo;
    }

    public int getPuntiFerita() {
        return puntiFerita;
    }

    public void setPuntiFerita(int puntiFerita) {
        this.puntiFerita = puntiFerita;
    }


    public boolean isEliminato() {
        return puntiFerita <= 0;
    }

    public Giocatore getMittenteBang() {
        return mittenteBang;
    }

    public void setMittenteBang(Giocatore mittenteBang) {
        this.mittenteBang = mittenteBang;
    }

    public boolean isBangSubito() {
        return bangSubito;
    }

    public boolean haGiocatoBang() {
        return haGiocatoBang;
    }

    public void setHaGiocatoBang(boolean haGiocatoBang) {
        this.haGiocatoBang = haGiocatoBang;
    }

    public void setBangSubito(boolean bangSubito) {
        this.bangSubito = bangSubito;
    }

    public int getGittataBang() {
        return gittataBang;
    }

    public void scartaCarta(Carta carta) {
        mano.remove(carta);
    }

    public void recuperaPuntiFerita() {
        if (puntiFerita < puntiFeritaIniziali) {
            puntiFerita++;
        }
    }
    private void eliminaGiocatore() {
        System.out.println(nome + " è stato eliminato!");
        mano.clear();
        carteEquipaggiate.clear();
    }

    public void perdiPuntoFerita() {
        puntiFerita--;
        if (puntiFerita <= 0) {
            eliminaGiocatore();
        }
    }
    // Metodo per ottenere una carta casuale dalla mano del giocatore
    public Carta getPescaCasualeCartaDallaMano() {
        if (!mano.isEmpty()) {
            Random random = new Random();
            int indiceCasuale = random.nextInt(mano.size());
            return mano.get(indiceCasuale);
        } else {
            return null;
        }
    }
    /**
     * Restituisce il numero di punti ferita iniziali predefinito per tutti i personaggi.
     * @return Il numero di punti ferita iniziali predefinito.
     */
    public int getPuntiFeritaIniziali() {
        return 4; // Modifica con il valore appropriato dei punti ferita iniziali
    }
    /**
     * Calcola la distanza in termini di giocatori tra il giocatore corrente e un altro giocatore specificato.
     * @param g Il giocatore di cui calcolare la distanza.
     * @return La distanza in termini di giocatori tra il giocatore corrente e il giocatore specificato.
     *         Se uno dei giocatori non è presente nella lista dei giocatori o se entrambi sono eliminati, restituisce -1.
     */
    public int calcolaDistanza(Giocatore g) {
        int index1 = giocatori.indexOf(this);
        int index2 = giocatori.indexOf(g);

        if (index1 == -1 || index2 == -1) {
            return -1; // Giocatore non trovato
        }

        int distanzaOraria = 0;
        int i = index1;
        while (i != index2) {
            i = (i + 1) % giocatori.size();
            if (!giocatori.get(i).isEliminato()) {
                distanzaOraria++;
            }
        }

        int distanzaAntioraria = 0;
        i = index1;
        while (i != index2) {
            i = (i - 1 + giocatori.size()) % giocatori.size();
            if (!giocatori.get(i).isEliminato()) {
                distanzaAntioraria++;
            }
        }

        return Math.min(distanzaOraria, distanzaAntioraria);
    }


    /**
     * Verifica se il giocatore possiede almeno una carta Mancato! nella sua mano.
     * @return true se il giocatore ha almeno una carta Mancato! nella mano, false altrimenti.
     */
    public boolean haCartaMancato() {
        for (Carta carta : mano) {
            if (carta instanceof CartaMancato) {
                return true;
            }
        }
        return false;
    }

    /**
     * Consente al giocatore di giocare una carta Mancato! dalla sua mano.
     * Se il giocatore non ha una carta Mancato! nella mano o se viene selezionata una carta non valida, viene visualizzato un messaggio di errore appropriato.
     * @param mazzo Il mazzo di carte del gioco.
     */
    public void giocaCartaMancato(Mazzo mazzo) {
        if (haCartaMancato()) {
            mostraCarteMancato();
            Scanner scanner = new Scanner(System.in);
            System.out.println(CHOOSE_MANCATO);
            int indiceCarta = scanner.nextInt();
            scanner.nextLine(); // Consuma il newline

            if (indiceCarta >= 0 && indiceCarta < mano.size()) {
                Carta cartaDaGiocare = mano.get(indiceCarta);
                if (cartaDaGiocare instanceof CartaMancato) {
                    giocaCarta(cartaDaGiocare);
                    mazzo.scartaCarta(cartaDaGiocare);
                    setBangSubito(true);
                } else {
                    System.out.println("Devi selezionare una carta Mancato!.");
                }
            } else {
                System.out.println(ERR_INDEX);
            }
        } else {
            System.out.println(NO_MANCATO_HAND);
        }
    }


    /**
     * Visualizza le carte Mancato! attualmente presenti nella mano del giocatore.
     */
    private void mostraCarteMancato() {
        System.out.println(SHOW_MANC_CARD);
        for (int i = 0; i < mano.size(); i++) {
            Carta carta = mano.get(i);
            if (carta instanceof CartaMancato) {
                System.out.println(i + ": " + carta.getNome() + " (" + carta.getTipo() + ")");
            }
        }
    }

    public void pescaCarta(Carta c) {
        mano.add(c);
    }

    /**
     * Gioca una carta dalla mano del giocatore.
     * Se la carta è di tipo {@link CartaBang}, viene verificata la distanza dal mittente del Bang e, se entro gittata,
     * viene applicato l'effetto della carta. Se la carta è stata giocata con successo, viene rimossa dalla mano del giocatore.
     * @param c La carta da giocare.
     */
    public void giocaCarta(Carta c) {
        if (c instanceof CartaBang) {
            CartaBang cartaBang = (CartaBang) c;
            int distanza = calcolaDistanza(mittenteBang);
            if (distanza <= cartaBang.getGittata()) {
                mano.remove(c);
                c.applicaEffetto(this, Main.partita);
                this.haGiocatoBang = true;
            } else {
                System.out.println(SORRY);
            }
        } else {
            mano.remove(c);
            c.applicaEffetto(this, Main.partita);
        }
    }

    /**
     * Scarta le carte in eccesso dalla mano del giocatore, fino a ridurre il numero di carte alla quantità corrispondente ai suoi punti ferita.
     */
    public void scartaCarteInEccesso() {
        while (mano.size() > puntiFerita) {
            mano.remove(mano.size() - 1);
        }
    }
}
