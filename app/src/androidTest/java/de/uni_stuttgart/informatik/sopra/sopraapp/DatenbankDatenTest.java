package de.uni_stuttgart.informatik.sopra.sopraapp;
/*
 * class to test the database interface and interfaces of data classes.
 *
 * @author Patrick Koss
 * @author Sarah Gutierrez Munoz
 * @author Michael Hersam
 */

import android.support.test.InstrumentationRegistry;

import com.google.android.gms.maps.model.LatLng;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DatenbankDatenTest {

    private DatenbankDaten db;
    private Benutzer benutzerAdded;
    private Gutachter gutachterAdded;
    private Region regionAdded;
    private Vertrag vertragAdded;
    private Feld feldAdded;
    private Schadensfall schadensfallAdded;

    @Before
    public void before() {
        db = new DatenbankDaten(InstrumentationRegistry.getTargetContext());
        db.open();
        addBenutzerTest();
        addGutachterTest();
        addRegionTest();
        addVertragTest();
        addFeldTest();
        addSchadensfall();
}
    @After
    public void after(){
        db.close();
        InstrumentationRegistry.getTargetContext().deleteDatabase("Datenbank.db");
    }

    @Test
    public void removeAdded(){
        db.updateGutachter(gutachterAdded);
        db.updateBenutzer(benutzerAdded);
        db.updateSchadensfall(schadensfallAdded);
        db.updateRegion(regionAdded);
        db.updateFeld(feldAdded);
        db.removeFeldFromVertrag(feldAdded);
        db.removeSchadensfall(schadensfallAdded);
        db.removeVertrag(vertragAdded);
        db.removeGutachter(gutachterAdded);
        db.removeBenutzer(benutzerAdded);
        db.removeRegion(regionAdded);
    }

    public void addBenutzerTest(){
        benutzerAdded = new Benutzer("Karl", "Heintz", "Pfarrstr. 17", db.getBenutzer().length + 1, "123456");
        db.addBenutzer(benutzerAdded);
    }

    public void addGutachterTest(){
        gutachterAdded = new Gutachter("Jochen", "Braun", "Panoramastr. 34", db.getGutachter().length + 1, "654321");
        db.addGutachter(gutachterAdded);
    }

    public void addRegionTest(){
        regionAdded = new Region("Stuttgart", "Deutschland", "Baden-Württemberg", "Vaihingen", db.getRegion().length + 1);
        db.addRegion(regionAdded);
    }

    public void addVertragTest(){
        vertragAdded = new Vertrag(benutzerAdded.getId(), benutzerAdded, "Haftpflicht");
        db.addVertrag(vertragAdded);
    }

    public void addSchadensfall(){
        LatLng[] k = new LatLng[3];
        k[0] = new LatLng(48.77374886599271,9.180734145920724);
        k[1] = new LatLng(48.77383696833366,9.182856077095494);
        k[2] = new LatLng(48.773417889704824,9.182618853355962);
        String[] bilder = new String[1];
        bilder[0]= "bilder.jpeg";
        schadensfallAdded = new Schadensfall("Erdbeben", db.getFeldById(1), k, "gemeldet", db.getRegionById(1), "Sun 01 Jan - 2018", bilder, 1, 40.0, db.getGutachterById(1));
        db.addSchadensfall(schadensfallAdded);
    }

    public void addFeldTest(){
        LatLng[] k = new LatLng[3];
        k[0] = new LatLng(48.77374886599271,9.180734145920724);
        k[1] = new LatLng(48.77383696833366,9.182856077095494);
        k[2] = new LatLng(48.773417889704824,9.182618853355962);

        feldAdded = new Feld(db.getFeld().length + 1, "Gerste", 50.0, k, regionAdded, vertragAdded);
        db.addFeld(feldAdded);
    }

    @Test
    public void getBenutzerTest1(){
        assertTrue(db.getBenutzer().length >= 1);
    }

    @Test
    public void getBenutzerTestAttributesNotEmpty(){
        for (Benutzer b: db.getBenutzer()) {
            assertTrue(b.getVorname().length() >= 2);
            assertTrue(b.getNachname().length() >= 2);
            assertTrue(b.getFullName().length() >= 5);
            assertTrue(b.getPasswort().length() >= 4);
            assertTrue(b.getAdresse().length() >= 6);
            assertTrue(b.getId() >= 1);
        }
    }

    @Test
    public void getBenutzerByIDTest(){
        Benutzer benutzerGot = db.getBenutzerById(benutzerAdded.getId());

        assertEquals(benutzerAdded.getId(), benutzerGot.getId());
        assertEquals(benutzerAdded.getNachname(), benutzerGot.getNachname());
        assertEquals(benutzerAdded.getPasswort(), benutzerGot.getPasswort());
        assertEquals(benutzerAdded.getFullName(), benutzerGot.getFullName());
        assertEquals(benutzerAdded.getAdresse(), benutzerGot.getAdresse());
        assertEquals(benutzerAdded.getVorname(), benutzerGot.getVorname());
    }

    @Test
    public void getBenutzerTest(){
        Benutzer[] ba = db.getBenutzer();
        Benutzer benutzerGot = ba[ba.length-1];

        assertEquals(benutzerAdded.getId(), benutzerGot.getId());
        assertEquals(benutzerAdded.getNachname(), benutzerGot.getNachname());
        assertEquals(benutzerAdded.getPasswort(), benutzerGot.getPasswort());
        assertEquals(benutzerAdded.getFullName(), benutzerGot.getFullName());
        assertEquals(benutzerAdded.getAdresse(), benutzerGot.getAdresse());
        assertEquals(benutzerAdded.getVorname(), benutzerGot.getVorname());
    }

    @Test
    public void getGutachterTest1(){
        assertTrue(db.getGutachter().length >= 1);
    }

    @Test
    public void getGutachterTestAttributesNotEmpty(){
        for (Gutachter g: db.getGutachter()) {
            assertTrue(g.getVorname().length() >= 2);
            assertTrue(g.getNachname().length() >= 2);
            assertTrue(g.getFullName().length() >= 5);
            assertTrue(g.getPasswort().length() >= 4);
            assertTrue(g.getAdresse().length() >= 6);
            assertTrue(g.getId() >= 1);
        }
    }

    @Test
    public void getGutachterByIDTest(){
        Gutachter gutachterGot = db.getGutachterById(gutachterAdded.getId());

        assertEquals(gutachterAdded.getId(), gutachterGot.getId());
        assertEquals(gutachterAdded.getNachname(), gutachterGot.getNachname());
        assertEquals(gutachterAdded.getPasswort(), gutachterGot.getPasswort());
        assertEquals(gutachterAdded.getFullName(), gutachterGot.getFullName());
        assertEquals(gutachterAdded.getAdresse(), gutachterGot.getAdresse());
        assertEquals(gutachterAdded.getVorname(), gutachterGot.getVorname());
    }

    @Test
    public void getGutachterTest(){
        Gutachter[] ga = db.getGutachter();
        Gutachter gutachterGot = ga[ga.length-1];

        assertEquals(gutachterAdded.getId(), gutachterGot.getId());
        assertEquals(gutachterAdded.getNachname(), gutachterGot.getNachname());
        assertEquals(gutachterAdded.getPasswort(), gutachterGot.getPasswort());
        assertEquals(gutachterAdded.getFullName(), gutachterGot.getFullName());
        assertEquals(gutachterAdded.getAdresse(), gutachterGot.getAdresse());
        assertEquals(gutachterAdded.getVorname(), gutachterGot.getVorname());
    }


    @Test
    public void getRegionTest(){
        Region[] ra = db.getRegion();
        Region regionGot = ra[ra.length-1];

        assertEquals(regionAdded.getId(), regionGot.getId());
        assertEquals(regionAdded.getStadt(), regionGot.getStadt());
        assertEquals(regionAdded.getLandkreis(), regionGot.getLandkreis());
        assertEquals(regionAdded.getLand(), regionGot.getLand());
        assertEquals(regionAdded.getBundesland(), regionGot.getBundesland());
    }

    @Test
    public void getRegionByIdTest(){
        Region regionGot = db.getRegionById(regionAdded.getId());

        assertEquals(regionAdded.getId(), regionGot.getId());
        assertEquals(regionAdded.getStadt(), regionGot.getStadt());
        assertEquals(regionAdded.getLandkreis(), regionGot.getLandkreis());
        assertEquals(regionAdded.getLand(), regionGot.getLand());
        assertEquals(regionAdded.getBundesland(), regionGot.getBundesland());
    }


    @Test
    public void getVertragTest(){
        Vertrag[] va = db.getVertrag();
        Vertrag vertragGot = va[va.length-1];

        assertEquals(vertragAdded.getId(), vertragGot.getId());

    }

    @Test
    public void getVertragByIdTest(){
        Vertrag vertragGot = db.getVertragById(vertragAdded.getId());

        assertEquals(vertragAdded.getId(), vertragGot.getId());
    }

    @Test
    public void getFeldTest(){
        Feld[] fa = db.getFeld();
        Feld feldGot = fa[fa.length - 1];

        assertEquals(feldAdded.getArt(), feldGot.getArt());
        assertEquals(feldAdded.getFlaeche(), feldGot.getFlaeche(), 0.0000000001);
        assertEquals(feldAdded.getKoordinaten()[0], feldGot.getKoordinaten()[0]);
        assertEquals(feldAdded.getKoordinaten()[1], feldGot.getKoordinaten()[1]);
        assertEquals(feldAdded.getKoordinaten()[2], feldGot.getKoordinaten()[2]);
        assertEquals(feldAdded.getRegion().getId(), feldGot.getRegion().getId());
        assertEquals(feldAdded.getRegion().getBundesland(), feldGot.getRegion().getBundesland());
        assertEquals(feldAdded.getRegion().getLand(),feldGot.getRegion().getLand());
        assertEquals(feldAdded.getRegion().getLandkreis(),feldGot.getRegion().getLandkreis());
        assertEquals(feldAdded.getRegion().getStadt(),feldGot.getRegion().getStadt());
        assertEquals(feldAdded.getVertrag().getId(), feldGot.getVertrag().getId());
        assertEquals(feldAdded.getVertrag().getPerson().getId(), feldGot.getVertrag().getPerson().getId());
        assertEquals(feldAdded.getVertrag().getPerson().getAdresse(), feldGot.getVertrag().getPerson().getAdresse());
        assertEquals(feldAdded.getVertrag().getPerson().getFullName(), feldGot.getVertrag().getPerson().getFullName());
        assertEquals(feldAdded.getVertrag().getPerson().getPasswort(), feldGot.getVertrag().getPerson().getPasswort());
        assertEquals(feldAdded.getVertrag().getPerson().getNachname(), feldGot.getVertrag().getPerson().getNachname());
    }

    @Test
    public void getFeldByIdTest(){
        Feld feldGot = db.getFeldById(feldAdded.getId());

        assertEquals(feldAdded.getArt(), feldGot.getArt());
        assertEquals(feldAdded.getFlaeche(), feldGot.getFlaeche(), 0.0000000001);
        assertEquals(feldAdded.getKoordinaten()[0], feldGot.getKoordinaten()[0]);
        assertEquals(feldAdded.getKoordinaten()[1], feldGot.getKoordinaten()[1]);
        assertEquals(feldAdded.getKoordinaten()[2], feldGot.getKoordinaten()[2]);
        assertEquals(feldAdded.getRegion().getId(), feldGot.getRegion().getId());
        assertEquals(feldAdded.getRegion().getBundesland(), feldGot.getRegion().getBundesland());
        assertEquals(feldAdded.getRegion().getLand(),feldGot.getRegion().getLand());
        assertEquals(feldAdded.getRegion().getLandkreis(),feldGot.getRegion().getLandkreis());
        assertEquals(feldAdded.getRegion().getStadt(),feldGot.getRegion().getStadt());
        assertEquals(feldAdded.getVertrag().getId(), feldGot.getVertrag().getId());
        assertEquals(feldAdded.getVertrag().getPerson().getId(), feldGot.getVertrag().getPerson().getId());
        assertEquals(feldAdded.getVertrag().getPerson().getAdresse(), feldGot.getVertrag().getPerson().getAdresse());
        assertEquals(feldAdded.getVertrag().getPerson().getFullName(), feldGot.getVertrag().getPerson().getFullName());
        assertEquals(feldAdded.getVertrag().getPerson().getPasswort(), feldGot.getVertrag().getPerson().getPasswort());
        assertEquals(feldAdded.getVertrag().getPerson().getNachname(), feldGot.getVertrag().getPerson().getNachname());
    }

    @Test
    public void schadensfallTest(){
        assertEquals(schadensfallAdded.getBilder()[0], "bilder.jpeg");
        assertEquals(schadensfallAdded.getRegion().getId(), db.getRegionById(1).getId());
        assertEquals(schadensfallAdded.getId(), 1);
        assertEquals(schadensfallAdded.getFlaeche(), 40.0, 0.1);
        assertEquals(schadensfallAdded.getGutachter().getId(), db.getGutachterById(1).getId());
        assertEquals(schadensfallAdded.getSchadensart(), "Erdbeben");
        assertEquals(schadensfallAdded.getStatus(), "gemeldet");
    }

}
