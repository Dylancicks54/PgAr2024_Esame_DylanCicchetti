package it.unibs.fp.esame;

import java.io.FileOutputStream;
import java.util.ArrayList;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

/**
 * La classe Writer fornisce metodi per scrivere dati in formato XML su file.
 */
public class Writer {

    /**
     * Scrive i dati della classifica dei giocatori in formato XML su un file specificato.
     * @param filepath il percorso del file XML da scrivere
     * @param classifica la lista dei giocatori da includere nella classifica XML
     */
    public static void scriviFileXML(String filepath, ArrayList<GiocatoreClassifica> classifica) {
        try {
            // Inizializzazione degli strumenti per la scrittura XML
            XMLOutputFactory xmlof = XMLOutputFactory.newInstance();
            FileOutputStream fname = new FileOutputStream(filepath);
            XMLStreamWriter xmlw = xmlof.createXMLStreamWriter(fname, "UTF-8");

            // Scrittura dell'intestazione del documento XML
            xmlw.writeStartDocument("UTF-8", "1.0");
            xmlw.writeCharacters("\n");
            xmlw.writeStartElement("Classifica"); // Inizio del tag radice

            // Scrittura dei dati dei giocatori
            for (GiocatoreClassifica giocatore : classifica) {
                xmlw.writeCharacters("\n\t"); // Indentazione
                xmlw.writeStartElement("Giocatore"); // Inizio del tag Giocatore

                // Scrittura del nome del giocatore
                xmlw.writeCharacters("\n\t\t"); // Indentazione
                xmlw.writeStartElement("Nome");
                xmlw.writeCharacters(giocatore.getNome());
                xmlw.writeEndElement();

                // Scrittura del totale sbleuri del giocatore
                xmlw.writeCharacters("\n\t\t"); // Indentazione
                xmlw.writeStartElement("TotSbleuri");
                xmlw.writeCharacters(String.valueOf(giocatore.getTotSbleuri()));
                xmlw.writeEndElement();

                // Scrittura del numero di partite giocate dal giocatore
                xmlw.writeCharacters("\n\t\t"); // Indentazione
                xmlw.writeStartElement("PartiteGiocate");
                xmlw.writeCharacters(String.valueOf(giocatore.getPartiteGiocate()));
                xmlw.writeEndElement();

                // Scrittura del punteggio medio del giocatore
                xmlw.writeCharacters("\n\t\t"); // Indentazione
                xmlw.writeStartElement("Punteggio");
                xmlw.writeCharacters(String.valueOf(giocatore.getPunteggio()));
                xmlw.writeEndElement();

                xmlw.writeCharacters("\n\t"); // Indentazione
                xmlw.writeEndElement(); // Fine del tag Giocatore
            }

            xmlw.writeCharacters("\n");
            xmlw.writeEndElement(); // Fine del tag Classifica
            xmlw.writeEndDocument(); // Fine del documento XML
            xmlw.flush();
            xmlw.close();
        } catch (Exception e) {
            System.out.println("Errore durante la scrittura del file XML: " + e.getMessage());
        }
    }
}
