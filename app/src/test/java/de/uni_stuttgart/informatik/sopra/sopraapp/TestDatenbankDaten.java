package de.uni_stuttgart.informatik.sopra.sopraapp;

import android.support.v7.app.AppCompatActivity;

/**
 * Class that test the class DatenbankDaten
 *
 * @author Patrick Koss
 * @author Sarah Gutierrez Munoz
 * @author Michael Hersam
 */
public class TestDatenbankDaten extends AppCompatActivity {
   /* DatenbankDaten db = new DatenbankDaten(getApplicationContext());
    public void init(){
        db.open();
        db.data();
        db.close();
    }
    @Test
    public void testgetBenutzer(){
        init();
        db.open();
        assertEquals(db.getBenutzer()[0].getId(), 1);
        assertEquals(db.getBenutzer()[0].getAdresse(), "Stuttgart, Königsstraße 1");
        assertEquals(db.getBenutzer()[0].getNachname(), "Mustermann");
        assertEquals(db.getBenutzer()[0].getPasswort(), "1234");
        assertEquals(db.getBenutzer()[0].getVorname(), "Max");
        db.close();
    }

    @Test
    public void testgetGutachter(){
        init();
        db.open();
        assertEquals(db.getGutachter()[0].getId(), 1);
        assertEquals(db.getGutachter()[0].getAdresse(), "Stuttgart, Königsstraße 2");
        assertEquals(db.getGutachter()[0].getNachname(), "Gutachter");
        assertEquals(db.getGutachter()[0].getPasswort(), "1234");
        assertEquals(db.getGutachter()[0].getVorname(), "Gustav");
        db.close();
    }

    @Test
    public void testVertrag(){
        init();
        db.open();
        assertEquals(db.getVertrag()[0].getId(), 1);
        assertEquals(db.getVertrag()[0].getPerson().getVorname(), "Max");
        db.close();
    }

    @Test
    public void testFeld(){
        init();
        db.open();
        assertEquals(db.getFeld()[0].getId(), 1);
        assertEquals(db.getFeld()[0].getVertrag().getId(), 1);
        assertEquals(db.getFeld()[0].getArt(), "Maisfeld");
        assertEquals(db.getFeld()[0].getFlaeche(), 40.0, 0.1);
        assertEquals(db.getFeld()[0].getKoordinaten()[0].latitude, 48.7759465, 0.1);
        assertEquals(db.getFeld()[0].getKoordinaten()[0].longitude, 9.1821322, 0.1);
        assertEquals(db.getFeld()[0].getRegion().getStadt(), "Stuttgart");
        db.close();
    }

    @Test
    public void testSchadensfall(){
        init();
        db.open();
        assertEquals(db.getSchadensfall()[0].getId(), 1);
        assertEquals(db.getSchadensfall()[0].getStatus(), "gemeldet");
        assertEquals(db.getSchadensfall()[0].getFeld().getArt(), "Maisfeld");
        Date date = new GregorianCalendar(2017, 11, 8).getTime();
        assertEquals(db.getSchadensfall()[0].getDatum().toString(), date.toString());
        assertEquals(db.getSchadensfall()[0].getSchadensposition()[0].latitude, 48.7759465, 0.1);
        assertEquals(db.getSchadensfall()[0].getSchadensposition()[0].longitude, 9.1821322, 0.1);
        assertEquals(db.getSchadensfall()[0].getRegion().getStadt(), "Stuttgart");
        assertEquals(db.getSchadensfall()[0].getSchadensart(), "Erdbeben");
        assertEquals(db.getSchadensfall()[0].getBilder()[0], "bild.jpeg");
        db.close();
    }

    @Test
    public void testRegion(){
        init();
        db.open();
        assertEquals(db.getRegion()[0].getId(), 1);
        assertEquals(db.getRegion()[0].getStadt(), "Stuttgart");
        assertEquals(db.getRegion()[0].getBundesland(), "Baden-Württemberg");
        assertEquals(db.getRegion()[0].getLand(), "Deutschland");
        assertEquals(db.getRegion()[0].getLandkreis(), "Stuttgart");
        db.close();
    }*/
}
