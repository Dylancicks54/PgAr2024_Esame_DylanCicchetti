package it.unibs.fp.esame;

import it.kibo.fp.lib.AnsiColors;
import it.kibo.fp.lib.InputData;

import java.util.ArrayList;

public class Main {
    public static final String NUM_PLAYER = "Inserisci il numero di giocatori (min: 4, max:7) : ";
    public static final String BENVENUTO = AnsiColors.RED +"██████  ███████ ███    ██ ██    ██ ███████ ███    ██ ██    ██ ████████  ██████      ██ ███    ██ \n" +
            "██   ██ ██      ████   ██ ██    ██ ██      ████   ██ ██    ██    ██    ██    ██     ██ ████   ██ \n" +
            "██████  █████   ██ ██  ██ ██    ██ █████   ██ ██  ██ ██    ██    ██    ██    ██     ██ ██ ██  ██ \n" +
            "██   ██ ██      ██  ██ ██  ██  ██  ██      ██  ██ ██ ██    ██    ██    ██    ██     ██ ██  ██ ██ \n" +
            "██████  ███████ ██   ████   ████   ███████ ██   ████  ██████     ██     ██████      ██ ██   ████ ";
    public static final String TITOLO = " █████  ██████  ███    ██  █████  ██      ██████   ██████      ██     ██ ███████ ███████ ████████ \n" +
            "██   ██ ██   ██ ████   ██ ██   ██ ██      ██   ██ ██    ██     ██     ██ ██      ██         ██    \n" +
            "███████ ██████  ██ ██  ██ ███████ ██      ██   ██ ██    ██     ██  █  ██ █████   ███████    ██    \n" +
            "██   ██ ██   ██ ██  ██ ██ ██   ██ ██      ██   ██ ██    ██     ██ ███ ██ ██           ██    ██    \n" +
            "██   ██ ██   ██ ██   ████ ██   ██ ███████ ██████   ██████       ███ ███  ███████ ███████    ██ " + AnsiColors.RESET;
    public static final String SCHOFIELD = "Schofield";
    public static final String REMINGTON = "Remington";
    public static final String REV_CARABINE = "Rev. Carabine";
    public static final String WINCHESTER = "Winchester";
    public static final String SCERIFFO = "Sceriffo";
    public static final String GIOCATORE_1 = "Giocatore1";
    public static final String GIOCATORE_2 = "Giocatore2";
    public static final String GIOCATORE_3 = "Giocatore3";
    public static final String GIOCATORE_4 = "Giocatore4";
    public static final String GIOCATORE_5 = "Giocatore5";
    public static final String GIOCATORE_6 = "Giocatore6";
    public static final String REMINDER = "REMINDER: LA MODALITA' TORNEO PERMETTE ANCHE DI SALVARE LE PARTITE IN UN XML";
    public static final String BILANCI = "Bilanci effettivi dei giocatori:";
    public static final String RESTART = "Ricominciare la partita? ";
    public static Gioco partita;
    public static Mazzo mazzo;
    public static ArrayList<GiocatoreClassifica> classifica = new ArrayList<>();
    public static ArrayList<Personaggio> personaggiDisp = new ArrayList<>();



    public static void main(String[] args) {
        personaggiDisp.add(new BartCassidy());
        personaggiDisp.add(new ElGringo());
        personaggiDisp.add(new Jourdonnais());
        personaggiDisp.add(new PaulRegret());
        personaggiDisp.add(new RoseDoolan());
        personaggiDisp.add(new SidKetchum());

        int numPartite = 0;
        int sbleuri = 500;
        System.out.println(BENVENUTO);
        System.out.println("\n\n\n");
        System.out.println(TITOLO);
        while (true) {
            int numGiocatori = InputData.readIntegerBetween(NUM_PLAYER, 4, 7);
            System.out.println(REMINDER);
            boolean modalita = InputData.readYesOrNo("vuoi giocare la modalità torneo?");

            // Creazione del mazzo
            ArrayList<Carta> carte = new ArrayList<>();
            for (int i = 0; i < 50; i++) {
                carte.add(new CartaBang(1)); // Imposta la gittata di default a 1 per tutte le carte Bang
            }
            for (int i = 0; i < 24; i++) {
                carte.add(new CartaMancato());
            }
            for (int i = 0; i < 3; i++) {
                carte.add(new CartaEquipaggiabile(SCHOFIELD, 2));
            }
            carte.add(new CartaEquipaggiabile(REMINGTON, 3));
            carte.add(new CartaEquipaggiabile(REV_CARABINE, 4));
            carte.add(new CartaEquipaggiabile(WINCHESTER, 5));

            mazzo = new Mazzo(carte);

            // Creazione dei giocatori
            ArrayList<Giocatore> giocatori = new ArrayList<>();
            giocatori.add(new Giocatore(SCERIFFO, Ruolo.SCERIFFO, giocatori, mazzo, Personaggio.getPersonaggioCasuale(personaggiDisp)));
            classifica.add(new GiocatoreClassifica("Sceriffo", sbleuri, numPartite));
            giocatori.add(new Giocatore(GIOCATORE_1, Ruolo.FUORILEGGE, giocatori, mazzo,Personaggio.getPersonaggioCasuale(personaggiDisp)));
            classifica.add(new GiocatoreClassifica("Fuorilegge1", sbleuri, numPartite));
            giocatori.add(new Giocatore(GIOCATORE_2, Ruolo.FUORILEGGE, giocatori, mazzo,Personaggio.getPersonaggioCasuale(personaggiDisp)));
            classifica.add(new GiocatoreClassifica("Fuorilegge2", sbleuri, numPartite));
            giocatori.add(new Giocatore(GIOCATORE_3, Ruolo.RINNEGATO, giocatori, mazzo,Personaggio.getPersonaggioCasuale(personaggiDisp)));
            classifica.add(new GiocatoreClassifica("Rinnegato", sbleuri, numPartite));

            if (numGiocatori == 5) {
                giocatori.add(new Giocatore(GIOCATORE_4, Ruolo.VICE, giocatori, mazzo,Personaggio.getPersonaggioCasuale(personaggiDisp)));
                classifica.add(new GiocatoreClassifica("Vice", sbleuri, numPartite));
            }
            if (numGiocatori == 6) {
                giocatori.add(new Giocatore(GIOCATORE_4, Ruolo.VICE, giocatori, mazzo,Personaggio.getPersonaggioCasuale(personaggiDisp)));
                classifica.add(new GiocatoreClassifica("Vice", sbleuri, numPartite));
                giocatori.add(new Giocatore(GIOCATORE_5, Ruolo.FUORILEGGE, giocatori, mazzo,Personaggio.getPersonaggioCasuale(personaggiDisp)));
                classifica.add(new GiocatoreClassifica("Fuorilegge3", sbleuri, numPartite));
            }
            if (numGiocatori == 7) {
                giocatori.add(new Giocatore(GIOCATORE_4, Ruolo.VICE, giocatori, mazzo,Personaggio.getPersonaggioCasuale(personaggiDisp)));
                classifica.add(new GiocatoreClassifica("Vice", sbleuri, numPartite));
                giocatori.add(new Giocatore(GIOCATORE_5, Ruolo.FUORILEGGE, giocatori, mazzo,Personaggio.getPersonaggioCasuale(personaggiDisp)));
                classifica.add(new GiocatoreClassifica("Fuorilegge3", sbleuri, numPartite));
                giocatori.add(new Giocatore(GIOCATORE_6, Ruolo.VICE, giocatori, mazzo,Personaggio.getPersonaggioCasuale(personaggiDisp)));
                classifica.add(new GiocatoreClassifica("Vice2", sbleuri, numPartite));
            }
            // Creazione del gioco
            partita = new Gioco(giocatori, mazzo);

            // Avvio della partita
            partita.avviaPartita();

            boolean nuovaPartita = InputData.readYesOrNo(RESTART);
            if (!nuovaPartita) {
                if(modalita){
                    Writer.scriviFileXML("src\\Assets\\output.xml", classifica);
                }
                // Stampa i bilanci effettivi dei giocatori
                System.out.println(BILANCI);
                for (GiocatoreClassifica gc : classifica) {
                    System.out.println(gc.getNome() + ": " + gc.getTotSbleuri() + " sbleuri");
                }
                break;
            }
            else{
                numPartite++;
            }

        }
    }

}
