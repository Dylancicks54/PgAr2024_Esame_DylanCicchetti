package it.unibs.fp.esame;

public class Jourdonnais extends Personaggio{
    private static final int PUNTI_VITA = 4; // Punti vita di Jourdonnais

    private int puntiVita;

    public Jourdonnais() {
        super("Jourdonnais", PUNTI_VITA);
    }

    public boolean tentativoMancato() {
        // Simulazione del tentativo di annullare il colpo (Barile)
        return Math.random() < 0.5; // 50% di probabilità di riuscita
    }

    public boolean tentativoMancatoDoppio() {
        // Simulazione del secondo tentativo di annullare il colpo (secondo Barile)
        return Math.random() < 0.5; // 50% di probabilità di riuscita
    }

    @Override
    public void attivaAbilitaSpeciale(Giocatore giocatore, Mazzo mazzo) {
        // Controllo se il giocatore ha ricevuto un colpo da una carta Bang
        if (giocatore.haRicevutoColpo()) {
            System.out.println(giocatore.getNome() + " attiva l'abilità speciale di Jourdonnais.");
            // Tentativo di annullare il colpo (Barile)
            boolean primoTentativo = tentativoMancato();
            if (primoTentativo) {
                System.out.println("Il colpo è stato annullato da Jourdonnais.");
            } else {
                // Secondo tentativo di annullare il colpo (secondo Barile) se Jourdonnais ha ancora punti ferita disponibili
                if (getPuntiFerita() > 1) {
                    boolean secondoTentativo = tentativoMancatoDoppio();
                    if (secondoTentativo) {
                        System.out.println("Il colpo è stato annullato da Jourdonnais con il secondo Barile.");
                    } else {
                        System.out.println("Jourdonnais non è riuscito ad annullare il colpo.");
                    }
                } else {
                    System.out.println("Jourdonnais non ha abbastanza punti ferita per il secondo tentativo di annullare il colpo.");
                }
            }
        }
    }

}
