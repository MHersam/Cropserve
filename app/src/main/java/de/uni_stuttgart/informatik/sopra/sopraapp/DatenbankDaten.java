package de.uni_stuttgart.informatik.sopra.sopraapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;
import java.util.Date;

/**
 * Class that helps to write and get Data from the database
 *
 * @author Patrick Koss
 * @author Sarah Gutierrez Munoz
 * @author Michael Hersam
 */
public class DatenbankDaten {

    private SQLiteDatabase database;
    private Datenbank datenbank;
    private static final String LOG_TAG = Datenbank.class.getSimpleName();

    /**
     * constructor
     *
     * @param context
     */
    public DatenbankDaten(Context context) {
        datenbank = new Datenbank(context);
    }

    /**
     * method open the database, so you can read/ write new data out/in
     */
    public void open() {
        database = datenbank.getWritableDatabase();
    }

    /**
     * method close the database after reading or writing
     */
    public void close() {
        datenbank.close();
    }

    /**
     * method add a Region to table region of the database
     *
     * @param region
     */
    public void addRegion(Region region) {
        ContentValues values = new ContentValues();
        values.put(Datenbank.REGION_COLUMN_STADT, region.getStadt());
        values.put(Datenbank.REGION_COLUMN_BUNDESLAND, region.getBundesland());
        values.put(Datenbank.REGION_COLUMN_LAND, region.getLand());
        values.put(Datenbank.REGION_COLUMN_LANDKREIS, region.getLandkreis());
        long insertId = database.insert(Datenbank.TABLE_REGION, null, values);
    }

    /**
     * method add a Feld to table feld of the database
     *
     * @param feld
     */
    public void addFeld(Feld feld) {
        ContentValues values = new ContentValues();
        values.put(Datenbank.FELD_COLUMN_ART, feld.getArt());
        values.put(Datenbank.FELD_COLUMN_FLAECHE, String.valueOf(feld.getFlaeche()));
        values.put(Datenbank.FELD_COLUMN_REGION, feld.getRegion().getId());
        String koordinaten = "";
        for (int i = 0; i < feld.getKoordinaten().length; i++) {
            koordinaten += String.valueOf(feld.getKoordinaten()[i].latitude) + "," + String.valueOf(feld.getKoordinaten()[i].longitude) + ";";
        }
        values.put(Datenbank.FELD_COLUMN_KOORDINATEN, koordinaten);
        values.put(Datenbank.FELD_COLUMN_VERTRAG, feld.getVertrag().getId());
        long insertId = database.insert(Datenbank.TABLE_FELD, null, values);
    }

    /**
     * method adds a Schadensfall to the table schadensfall of the database
     *
     * @param schadensfall
     */
    public void addSchadensfall(Schadensfall schadensfall) {
        ContentValues values = new ContentValues();
        String bilder = "";
        String koordinaten = "";
        String date = "";
        //add each pic to a string separated by ;
        bilder = schadensfall.getBilder()[0];

        //add x-coordinate,y-coordinate and separate each coordinate with ;
        for (int i = 0; i < schadensfall.getSchadensposition().length; i++) {
            koordinaten += String.valueOf(schadensfall.getSchadensposition()[i].latitude) + "," + String.valueOf(schadensfall.getSchadensposition()[i].longitude) + ";";
        }

        //add day;month;year to a string
        String datumString = schadensfall.getDatum();
        String sub = datumString.substring(0, 10);
        String sub2 = datumString.substring(datumString.length()-4, datumString.length());
        String datumRe = sub + " - " + sub2;

        //Calendar cal = Calendar.getInstance();
        //date += cal.get(Calendar.DAY_OF_MONTH) + ";";
        //date += cal.get(Calendar.MONTH) + ";";
        //date += cal.get(Calendar.YEAR) + ";";

        values.put(Datenbank.SCHADENSFALL_COLUMN_BILDER, bilder);
        values.put(Datenbank.SCHADENSFALL_COLUMN_SCHADENSPOSITION, koordinaten);
        values.put(Datenbank.SCHADENSFALL_COLUMN_DATUM, datumRe);
        values.put(Datenbank.SCHADENSFALL_COLUMN_FELD, schadensfall.getFeld().getId());
        values.put(Datenbank.SCHADENSFALL_COLUMN_REGION, schadensfall.getRegion().getId());
        values.put(Datenbank.SCHADENSFALL_COLUMN_SCHADENSART, schadensfall.getSchadensart());
        values.put(Datenbank.SCHADENSFALL_COLUMN_STATUS, schadensfall.getStatus());
        values.put(Datenbank.SCHADENSFALL_COLUMN_FLAECHE, schadensfall.getFlaeche());
        values.put(Datenbank.SCHADENSFALL_COLUMN_GUTACHTER, schadensfall.getGutachter().getId());
        long insertId = database.insert(Datenbank.TABLE_SCHADENSFALL, null, values);
    }

    /**
     * method add a Benutzer to table benutzer of the database
     *
     * @param benutzer
     */
    public void addBenutzer(Benutzer benutzer) {
        ContentValues values = new ContentValues();
        values.put(Datenbank.BENUTZER_COLUMN_ADRESSE, benutzer.getAdresse());
        values.put(Datenbank.BENUTZER_COLUMN_NACHNAME, benutzer.getNachname());
        values.put(Datenbank.BENUTZER_COLUMN_VORNAME, benutzer.getVorname());
        values.put(Datenbank.BENUTZER_COLUMN_PASSWORT, benutzer.getPasswort());
        long insertId = database.insert(Datenbank.TABLE_BENUTZER, null, values);
    }

    /**
     * method adds a Gutachter to table gutachter of the database
     *
     * @param gutachter
     */
    public void addGutachter(Gutachter gutachter) {
        ContentValues values = new ContentValues();
        values.put(Datenbank.GUTACHTER_COLUMN_ADRESSE, gutachter.getAdresse());
        values.put(Datenbank.GUTACHTER_COLUMN_NACHNAME, gutachter.getNachname());
        values.put(Datenbank.GUTACHTER_COLUMN_VORNAME, gutachter.getVorname());
        values.put(Datenbank.GUTACHTER_COLUMN_PASSWORT, gutachter.getPasswort());
        long insertId = database.insert(Datenbank.TABLE_GUTACHTER, null, values);
    }

    /**
     * method adds a Vertrag to table vertrag of the database
     *
     * @param vertrag
     */
    public void addVertrag(Vertrag vertrag) {
        ContentValues values = new ContentValues();
        values.put(Datenbank.VERTRAG_COLUMN_BENUTZER, vertrag.getPerson().getId());
        values.put(Datenbank.VERTRAG_COLUMN_ART, vertrag.getArt());
        long insertId = database.insert(Datenbank.TABLE_VERTRAG, null, values);
    }

    /**
     * method returns all data as a field of Gutachter from table gutachter
     *
     * @return
     */
    public Gutachter[] getGutachter() {
        ArrayList<Gutachter> gutachter = new ArrayList<Gutachter>();
        String[] columns = {Datenbank.GUTACHTER_COLUMN_ID, Datenbank.GUTACHTER_COLUMN_VORNAME, Datenbank.GUTACHTER_COLUMN_NACHNAME, Datenbank.GUTACHTER_COLUMN_ADRESSE, Datenbank.GUTACHTER_COLUMN_PASSWORT};
        Cursor cursor = database.query(Datenbank.TABLE_GUTACHTER, columns, null, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(cursor.getColumnIndex(Datenbank.GUTACHTER_COLUMN_ID));
            String vorname = cursor.getString(cursor.getColumnIndex(Datenbank.GUTACHTER_COLUMN_VORNAME));
            String nachname = cursor.getString(cursor.getColumnIndex(Datenbank.GUTACHTER_COLUMN_NACHNAME));
            String adresse = cursor.getString(cursor.getColumnIndex(Datenbank.GUTACHTER_COLUMN_ADRESSE));
            String passwort = cursor.getString(cursor.getColumnIndex(Datenbank.GUTACHTER_COLUMN_PASSWORT));
            gutachter.add(new Gutachter(vorname, nachname, adresse, id, passwort));
            cursor.moveToNext();
        }
        Gutachter result[] = new Gutachter[gutachter.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = gutachter.get(i);
        }
        cursor.close();
        return result;
    }

    /**
     * method returns all data as a field Benutzer from table benutzer
     *
     * @return
     */
    public Benutzer[] getBenutzer() {
        ArrayList<Benutzer> benutzer = new ArrayList<Benutzer>();
        String[] columns = {Datenbank.BENUTZER_COLUMN_ID, Datenbank.BENUTZER_COLUMN_VORNAME, Datenbank.BENUTZER_COLUMN_NACHNAME, Datenbank.BENUTZER_COLUMN_ADRESSE, Datenbank.BENUTZER_COLUMN_PASSWORT};
        Cursor cursor = database.query(Datenbank.TABLE_BENUTZER, columns, null, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(cursor.getColumnIndex(Datenbank.BENUTZER_COLUMN_ID));
            String vorname = cursor.getString(cursor.getColumnIndex(Datenbank.BENUTZER_COLUMN_VORNAME));
            String nachname = cursor.getString(cursor.getColumnIndex(Datenbank.BENUTZER_COLUMN_NACHNAME));
            String adresse = cursor.getString(cursor.getColumnIndex(Datenbank.BENUTZER_COLUMN_ADRESSE));
            String passwort = cursor.getString(cursor.getColumnIndex(Datenbank.BENUTZER_COLUMN_PASSWORT));
            benutzer.add(new Benutzer(vorname, nachname, adresse, id, passwort));
            cursor.moveToNext();
        }
        Benutzer result[] = new Benutzer[benutzer.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = benutzer.get(i);
        }
        cursor.close();
        return result;
    }

    /**
     * method returns all data as a field Vertrag from table vertrag
     *
     * @return
     */
    public Vertrag[] getVertrag() {
        ArrayList<Vertrag> vertrag = new ArrayList<Vertrag>();
        String[] columns = {Datenbank.VERTRAG_COLUMN_ID, Datenbank.VERTRAG_COLUMN_BENUTZER, Datenbank.VERTRAG_COLUMN_ART};
        Cursor cursor = database.query(Datenbank.TABLE_VERTRAG, columns, null, null, null, null, null, null);
        cursor.moveToFirst();
        Benutzer[] benutzer = getBenutzer();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(cursor.getColumnIndex(Datenbank.VERTRAG_COLUMN_ID));
            int benutzerId = cursor.getInt(cursor.getColumnIndex(Datenbank.VERTRAG_COLUMN_BENUTZER));
            String art = cursor.getString(cursor.getColumnIndex(Datenbank.VERTRAG_COLUMN_ART));
            Benutzer benutzer1 = null;
            for (int i = 0; i < benutzer.length; i++)
                if (benutzer[i].getId() == benutzerId) benutzer1 = benutzer[i];
            vertrag.add(new Vertrag(id, benutzer1, art));
            cursor.moveToNext();
        }
        Vertrag result[] = new Vertrag[vertrag.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = vertrag.get(i);
        }
        cursor.close();
        return result;
    }

    /**
     * method returns all data as a field Feld from table feld
     *
     * @return
     */
    public Feld[] getFeld() {
        ArrayList<Feld> feld = new ArrayList<Feld>();
        String[] columns = {Datenbank.FELD_COLUMN_ID, Datenbank.FELD_COLUMN_ART, Datenbank.FELD_COLUMN_FLAECHE, Datenbank.FELD_COLUMN_KOORDINATEN, Datenbank.FELD_COLUMN_REGION, Datenbank.FELD_COLUMN_VERTRAG};
        Cursor cursor = database.query(Datenbank.TABLE_FELD, columns, null, null, null, null, null, null);
        cursor.moveToFirst();
        Region region[] = getRegion();
        Vertrag vertrag[] = getVertrag();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(cursor.getColumnIndex(Datenbank.FELD_COLUMN_ID));
            int regionId = cursor.getInt(cursor.getColumnIndex(Datenbank.FELD_COLUMN_REGION));
            int vertragId = cursor.getInt(cursor.getColumnIndex(Datenbank.FELD_COLUMN_VERTRAG));
            String art = cursor.getString(cursor.getColumnIndex(Datenbank.FELD_COLUMN_ART));
            Double flaeche = Double.parseDouble(cursor.getString(cursor.getColumnIndex(Datenbank.FELD_COLUMN_FLAECHE)));
            String koordinaten = cursor.getString(cursor.getColumnIndex(Datenbank.FELD_COLUMN_KOORDINATEN));
            String[] latLng = koordinaten.split(";");
            ArrayList<LatLng> pointsNew = new ArrayList<LatLng>();
            for (int i = 0; i < latLng.length; i++) {
                String[] coordinate = latLng[i].split(",");
                pointsNew.add(new LatLng(Double.parseDouble(coordinate[0]), Double.parseDouble(coordinate[1])));
            }
            LatLng[] pointsResult = new LatLng[pointsNew.size()];
            for (int i = 0; i < pointsNew.size(); i++) {
                pointsResult[i] = pointsNew.get(i);
            }
            Region r = null;
            for (int i = 0; i < region.length; i++)
                if (region[i].getId() == regionId) r = region[i];
            Vertrag v = null;
            for (int i = 0; i < vertrag.length; i++)
                if (vertrag[i].getId() == vertragId) v = vertrag[i];
            feld.add(new Feld(id, art, flaeche, pointsResult, r, v));
            cursor.moveToNext();
        }
        Feld result[] = new Feld[feld.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = feld.get(i);
        }
        cursor.close();
        return result;

    }

    /**
     * method returns all data as a field Region from table region
     *
     * @return
     */
    public Region[] getRegion() {
        ArrayList<Region> region = new ArrayList<Region>();
        String[] columns = {Datenbank.REGION_COLUMN_ID, Datenbank.REGION_COLUMN_BUNDESLAND, Datenbank.REGION_COLUMN_LAND, Datenbank.REGION_COLUMN_LANDKREIS, Datenbank.REGION_COLUMN_STADT};
        Cursor cursor = database.query(Datenbank.TABLE_REGION, columns, null, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(cursor.getColumnIndex(Datenbank.REGION_COLUMN_ID));
            String bundesland = cursor.getString(cursor.getColumnIndex(Datenbank.REGION_COLUMN_BUNDESLAND));
            String land = cursor.getString(cursor.getColumnIndex(Datenbank.REGION_COLUMN_LAND));
            String landkreis = cursor.getString(cursor.getColumnIndex(Datenbank.REGION_COLUMN_LANDKREIS));
            String stadt = cursor.getString(cursor.getColumnIndex(Datenbank.REGION_COLUMN_STADT));
            region.add(new Region(landkreis, land, bundesland, stadt, id));
            cursor.moveToNext();
        }
        Region result[] = new Region[region.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = region.get(i);
        }
        cursor.close();
        return result;
    }

    /**
     * method returns all data as a field Schadensfall from table schadensfall
     *
     * @return
     */
    public Schadensfall[] getSchadensfall() {
        ArrayList<Schadensfall> schadensfall = new ArrayList<Schadensfall>();
        String[] columns = {Datenbank.SCHADENSFALL_COLUMN_BILDER, Datenbank.SCHADENSFALL_COLUMN_REGION, Datenbank.SCHADENSFALL_COLUMN_SCHADENSPOSITION, Datenbank.SCHADENSFALL_COLUMN_FELD, Datenbank.SCHADENSFALL_COLUMN_DATUM, Datenbank.SCHADENSFALL_COLUMN_ID, Datenbank.SCHADENSFALL_COLUMN_SCHADENSART, Datenbank.SCHADENSFALL_COLUMN_STATUS, Datenbank.SCHADENSFALL_COLUMN_FLAECHE, Datenbank.SCHADENSFALL_COLUMN_GUTACHTER};
        Cursor cursor = database.query(Datenbank.TABLE_SCHADENSFALL, columns, null, null, null, null, null, null);
        cursor.moveToFirst();
        Region[] regions = getRegion();
        Feld[] felder = getFeld();
        Gutachter[] gutachter = getGutachter();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(cursor.getColumnIndex(Datenbank.SCHADENSFALL_COLUMN_ID));
            int regionId = cursor.getInt(cursor.getColumnIndex(Datenbank.SCHADENSFALL_COLUMN_REGION));
            int feldId = cursor.getInt(cursor.getColumnIndex(Datenbank.SCHADENSFALL_COLUMN_FELD));
            int gutachterId = cursor.getInt(cursor.getColumnIndex(Datenbank.SCHADENSFALL_COLUMN_GUTACHTER));
            String art = cursor.getString(cursor.getColumnIndex(Datenbank.SCHADENSFALL_COLUMN_SCHADENSART));
            String bilder = cursor.getString(cursor.getColumnIndex(Datenbank.SCHADENSFALL_COLUMN_BILDER));
            String koordinaten = cursor.getString(cursor.getColumnIndex(Datenbank.SCHADENSFALL_COLUMN_SCHADENSPOSITION));
            String date = cursor.getString(cursor.getColumnIndex(Datenbank.SCHADENSFALL_COLUMN_DATUM));
            String status = cursor.getString(cursor.getColumnIndex(Datenbank.SCHADENSFALL_COLUMN_STATUS));
            Double flaeche = Double.parseDouble(cursor.getString(cursor.getColumnIndex(Datenbank.SCHADENSFALL_COLUMN_FLAECHE)));
            String[] latLng = koordinaten.split(";");
            String[] pic = new String[1];
            pic[0] = bilder;


            ArrayList<LatLng> pointsNew = new ArrayList<LatLng>();
            //Date datumResult = new GregorianCalendar(Integer.parseInt(datum[2]), Integer.parseInt(datum[1]), Integer.parseInt(datum[0])).getTime();
            for (int i = 0; i < latLng.length; i++) {
                String[] coordinate = latLng[i].split(",");
                pointsNew.add(new LatLng(Double.parseDouble(coordinate[0]), Double.parseDouble(coordinate[1])));
            }
            LatLng[] pointsResult = new LatLng[pointsNew.size()];
            for (int i = 0; i < pointsNew.size(); i++) {
                pointsResult[i] = pointsNew.get(i);
            }
            Region r = null;
            for (int i = 0; i < regions.length; i++)
                if (regions[i].getId() == regionId) r = regions[i];
            Feld f = null;
            for (int i = 0; i < felder.length; i++)
                if (felder[i].getId() == feldId) f = felder[i];
            Gutachter g = null;
            for (int i = 0; i < gutachter.length; i++)
                if (gutachter[i].getId() == gutachterId) g = gutachter[i];
            schadensfall.add(new Schadensfall(art, f, pointsResult, status, r, date, pic, id, flaeche, g));
            cursor.moveToNext();
        }
        Schadensfall result[] = new Schadensfall[schadensfall.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = schadensfall.get(i);
        }
        cursor.close();
        return result;
    }

    /**
     * initialize the database with some data
     */
    public void data() {
        Benutzer benutzer = new Benutzer("Max", "Mustermann", "Stuttgart, Königsstraße 1", 1, "1234");
        Benutzer benutzer2 = new Benutzer("Maxine", "Secoundo", "Stuttgart, Königsstraße 2", 2, "1234");
        Gutachter gutachter = new Gutachter("Gustav", "Gutachter", "Stuttgart, Königsstraße 2", 1, "1234");
        Vertrag vertrag = new Vertrag(1, benutzer, "Haftpflichtversicherung");
        Vertrag vertrag2 = new Vertrag(2, benutzer2, "Frost-Schutz");
        LatLng p1 = new LatLng(48.7759465, 9.1821322);
        LatLng p2 =  new LatLng(48.7769465, 9.1821322);
        LatLng p3 = new LatLng(48.7759465, 9.1831322);
        LatLng p4 = new LatLng(48.7769465, 9.1831322);
        LatLng[] koordinaten = {p1,p2, p4,p3, p1};
        LatLng[] koordinaten3 = {new LatLng(48.7779465, 9.1821322),new LatLng(48.7769465, 9.1821322), new LatLng(48.7769465, 9.1831322),new LatLng(48.7779465, 9.1831322), new LatLng(48.7779465, 9.1821322)};
        //add defined string datum
        Date date = new Date(1514119500105L);
        String datumString = date.toString();
        String sub = datumString.substring(0, 10);
        String sub2 = datumString.substring(datumString.length()-4, datumString.length());
        String dateRe = sub + " - " + sub2;
        Region region = new Region("Stuttgart", "Deutschland", "Baden-Württemberg", "Stuttgart", 1);
        Feld feld = new Feld(1, "Mais", 40.0, koordinaten, region, vertrag);
        Feld feld2 = new Feld(2, "Weizen", 32.0, koordinaten3, region, vertrag2);
        String[] photo = {"bilder.jpeg"};
        Schadensfall schadensfall = new Schadensfall("Erdbeben", feld, koordinaten, "gemeldet", region, dateRe, photo, 1, 40.0, gutachter);
        Schadensfall schadensfall2 = new Schadensfall("Wildschaden", feld2, koordinaten3, "gemeldet", region, dateRe, photo, 2, 32.0, gutachter);
        addBenutzer(benutzer);
        addBenutzer(benutzer2);
        addFeld(feld);
        addFeld(feld2);
        addGutachter(gutachter);
        addRegion(region);
        addSchadensfall(schadensfall);
        addSchadensfall(schadensfall2);
        addVertrag(vertrag);
        addVertrag(vertrag2);
    }

    /**
     * update table benutzer with new Benutzer data
     *
     * @param benutzer
     */
    public void updateBenutzer(Benutzer benutzer) {
        ContentValues values = new ContentValues();
        values.put(Datenbank.BENUTZER_COLUMN_ADRESSE, benutzer.getAdresse());
        values.put(Datenbank.BENUTZER_COLUMN_NACHNAME, benutzer.getNachname());
        values.put(Datenbank.BENUTZER_COLUMN_VORNAME, benutzer.getVorname());
        values.put(Datenbank.BENUTZER_COLUMN_PASSWORT, benutzer.getPasswort());
        database.update(Datenbank.TABLE_BENUTZER, values, Datenbank.BENUTZER_COLUMN_ID + "=" + benutzer.getId(), null);
    }

    /**
     * update table gutachter with new Gutachter data
     *
     * @param gutachter
     */
    public void updateGutachter(Gutachter gutachter) {
        ContentValues values = new ContentValues();
        values.put(Datenbank.GUTACHTER_COLUMN_ADRESSE, gutachter.getAdresse());
        values.put(Datenbank.GUTACHTER_COLUMN_NACHNAME, gutachter.getNachname());
        values.put(Datenbank.GUTACHTER_COLUMN_VORNAME, gutachter.getVorname());
        values.put(Datenbank.GUTACHTER_COLUMN_PASSWORT, gutachter.getPasswort());
        database.update(Datenbank.TABLE_GUTACHTER, values, Datenbank.GUTACHTER_COLUMN_ID + "=" + gutachter.getId(), null);
    }

    /**
     * update table vertrag with new Vertrag data
     *
     * @param vertrag
     */
    public void updateVertrag(Vertrag vertrag) {
        ContentValues values = new ContentValues();
        values.put(Datenbank.VERTRAG_COLUMN_BENUTZER, vertrag.getPerson().getId());
        values.put(Datenbank.VERTRAG_COLUMN_ART, vertrag.getArt());
        database.update(Datenbank.TABLE_VERTRAG, values, Datenbank.VERTRAG_COLUMN_ID + "=" + vertrag.getId(), null);
    }

    /**
     * update table feld with new Feld data
     *
     * @param feld
     */
    public void updateFeld(Feld feld) {
        ContentValues values = new ContentValues();
        values.put(Datenbank.FELD_COLUMN_ART, feld.getArt());
        values.put(Datenbank.FELD_COLUMN_FLAECHE, String.valueOf(feld.getFlaeche()));
        values.put(Datenbank.FELD_COLUMN_REGION, feld.getRegion().getId());
        String koordinaten = "";
        for (int i = 0; i < feld.getKoordinaten().length; i++) {
            koordinaten += String.valueOf(feld.getKoordinaten()[i].latitude) + "," + String.valueOf(feld.getKoordinaten()[i].longitude) + ";";
        }
        values.put(Datenbank.FELD_COLUMN_KOORDINATEN, koordinaten);
        values.put(Datenbank.FELD_COLUMN_VERTRAG, feld.getVertrag().getId());
        database.update(Datenbank.TABLE_FELD, values, Datenbank.FELD_COLUMN_ID + "=" + feld.getId(), null);
    }

    /**
     * update table region with new Region data
     *
     * @param region
     */
    public void updateRegion(Region region) {
        ContentValues values = new ContentValues();
        values.put(Datenbank.REGION_COLUMN_STADT, region.getStadt());
        values.put(Datenbank.REGION_COLUMN_BUNDESLAND, region.getBundesland());
        values.put(Datenbank.REGION_COLUMN_LAND, region.getLand());
        values.put(Datenbank.REGION_COLUMN_LANDKREIS, region.getLandkreis());
        database.update(Datenbank.TABLE_REGION, values, Datenbank.REGION_COLUMN_ID + "=" + region.getId(), null);
    }

    /**
     * update table schadensfall with new Schadensfall data
     *
     * @param schadensfall
     */
    public void updateSchadensfall(Schadensfall schadensfall) {
        ContentValues values = new ContentValues();
        String bilder = "";
        String koordinaten = "";

        //add each pic to a string separated by ;
        bilder = schadensfall.getBilder()[0];

        //add x-coordinate,y-coordinate and separate each coordinate with ;
        for (int i = 0; i < schadensfall.getSchadensposition().length; i++) {
            koordinaten += String.valueOf(schadensfall.getSchadensposition()[i].latitude) + "," + String.valueOf(schadensfall.getSchadensposition()[i].longitude) + ";";
        }
        //add day;month;year to a string
        String datumString = schadensfall.getDatum();
        String sub = datumString.substring(0, 10);
        String sub2 = datumString.substring(datumString.length()-4, datumString.length());
        String datumRe = sub + " - " + sub2;

        //date = schadensfall.getDatum().toString();
        values.put(Datenbank.SCHADENSFALL_COLUMN_BILDER, bilder);
        values.put(Datenbank.SCHADENSFALL_COLUMN_SCHADENSPOSITION, koordinaten);
        values.put(Datenbank.SCHADENSFALL_COLUMN_DATUM, datumRe);
        values.put(Datenbank.SCHADENSFALL_COLUMN_FELD, schadensfall.getFeld().getId());
        values.put(Datenbank.SCHADENSFALL_COLUMN_REGION, schadensfall.getRegion().getId());
        values.put(Datenbank.SCHADENSFALL_COLUMN_SCHADENSART, schadensfall.getSchadensart());
        values.put(Datenbank.SCHADENSFALL_COLUMN_STATUS, schadensfall.getStatus());
        values.put(Datenbank.SCHADENSFALL_COLUMN_GUTACHTER, schadensfall.getGutachter().getId());
        values.put(Datenbank.SCHADENSFALL_COLUMN_FLAECHE, schadensfall.getFlaeche());
        database.update(Datenbank.TABLE_SCHADENSFALL, values, Datenbank.SCHADENSFALL_COLUMN_ID + "=" + schadensfall.getId(), null);
    }

    /**
     * getter for a contract by id
     *
     * @param identity
     * @return
     */
    public Vertrag getVertragById(int identity) {
        String[] columns = {Datenbank.VERTRAG_COLUMN_ID, Datenbank.VERTRAG_COLUMN_BENUTZER, Datenbank.VERTRAG_COLUMN_ART};
        Cursor cursor = database.query(Datenbank.TABLE_VERTRAG, columns, identity + "=" + Datenbank.VERTRAG_COLUMN_ID, null, null, null, null, null);
        cursor.moveToFirst();
        int id = cursor.getInt(cursor.getColumnIndex(Datenbank.VERTRAG_COLUMN_ID));
        int benutzerId = cursor.getInt(cursor.getColumnIndex(Datenbank.VERTRAG_COLUMN_BENUTZER));
        String art = cursor.getString(cursor.getColumnIndex(Datenbank.VERTRAG_COLUMN_ART));
        Benutzer benutzer1 = getBenutzerById(benutzerId);
        cursor.close();
        return new Vertrag(id, benutzer1, art);
    }

    /**
     * getter for a user by id
     *
     * @param identity
     * @return
     */
    public Benutzer getBenutzerById(int identity) {
        String[] columns = {Datenbank.BENUTZER_COLUMN_ID, Datenbank.BENUTZER_COLUMN_VORNAME, Datenbank.BENUTZER_COLUMN_NACHNAME, Datenbank.BENUTZER_COLUMN_ADRESSE, Datenbank.BENUTZER_COLUMN_PASSWORT};
        Cursor cursor = database.query(Datenbank.TABLE_BENUTZER, columns, identity + "=" + Datenbank.BENUTZER_COLUMN_ID, null, null, null, null, null);
        cursor.moveToFirst();
        int id = cursor.getInt(cursor.getColumnIndex(Datenbank.BENUTZER_COLUMN_ID));
        String vorname = cursor.getString(cursor.getColumnIndex(Datenbank.BENUTZER_COLUMN_VORNAME));
        String nachname = cursor.getString(cursor.getColumnIndex(Datenbank.BENUTZER_COLUMN_NACHNAME));
        String adresse = cursor.getString(cursor.getColumnIndex(Datenbank.BENUTZER_COLUMN_ADRESSE));
        String passwort = cursor.getString(cursor.getColumnIndex(Datenbank.BENUTZER_COLUMN_PASSWORT));
        cursor.close();
        return new Benutzer(vorname, nachname, adresse, id, passwort);
    }

    /**
     * getter for an expert by id
     *
     * @param identity
     * @return
     */
    public Gutachter getGutachterById(int identity) {
        String[] columns = {Datenbank.GUTACHTER_COLUMN_ID, Datenbank.GUTACHTER_COLUMN_VORNAME, Datenbank.GUTACHTER_COLUMN_NACHNAME, Datenbank.GUTACHTER_COLUMN_ADRESSE, Datenbank.GUTACHTER_COLUMN_PASSWORT};
        Cursor cursor = database.query(Datenbank.TABLE_GUTACHTER, columns, identity + "=" + Datenbank.GUTACHTER_COLUMN_ID, null, null, null, null, null);
        cursor.moveToFirst();
        int id = cursor.getInt(cursor.getColumnIndex(Datenbank.GUTACHTER_COLUMN_ID));
        String vorname = cursor.getString(cursor.getColumnIndex(Datenbank.GUTACHTER_COLUMN_VORNAME));
        String nachname = cursor.getString(cursor.getColumnIndex(Datenbank.GUTACHTER_COLUMN_NACHNAME));
        String adresse = cursor.getString(cursor.getColumnIndex(Datenbank.GUTACHTER_COLUMN_ADRESSE));
        String passwort = cursor.getString(cursor.getColumnIndex(Datenbank.GUTACHTER_COLUMN_PASSWORT));
        cursor.close();
        return new Gutachter(vorname, nachname, adresse, id, passwort);
    }

    /**
     * getter for a damage case by id
     *
     * @param identity
     * @return
     */
    public Schadensfall getSchadensfallById(int identity) {
        String[] columns = {Datenbank.SCHADENSFALL_COLUMN_BILDER, Datenbank.SCHADENSFALL_COLUMN_REGION, Datenbank.SCHADENSFALL_COLUMN_SCHADENSPOSITION, Datenbank.SCHADENSFALL_COLUMN_FELD, Datenbank.SCHADENSFALL_COLUMN_DATUM, Datenbank.SCHADENSFALL_COLUMN_ID, Datenbank.SCHADENSFALL_COLUMN_SCHADENSART, Datenbank.SCHADENSFALL_COLUMN_STATUS, Datenbank.SCHADENSFALL_COLUMN_FLAECHE, Datenbank.SCHADENSFALL_COLUMN_GUTACHTER};
        Cursor cursor = database.query(Datenbank.TABLE_SCHADENSFALL, columns, identity + "=" + Datenbank.SCHADENSFALL_COLUMN_ID, null, null, null, null, null);
        cursor.moveToFirst();
        int id = cursor.getInt(cursor.getColumnIndex(Datenbank.SCHADENSFALL_COLUMN_ID));
        int regionId = cursor.getInt(cursor.getColumnIndex(Datenbank.SCHADENSFALL_COLUMN_REGION));
        int feldId = cursor.getInt(cursor.getColumnIndex(Datenbank.SCHADENSFALL_COLUMN_FELD));
        String art = cursor.getString(cursor.getColumnIndex(Datenbank.SCHADENSFALL_COLUMN_SCHADENSART));
        String bilder = cursor.getString(cursor.getColumnIndex(Datenbank.SCHADENSFALL_COLUMN_BILDER));
        String koordinaten = cursor.getString(cursor.getColumnIndex(Datenbank.SCHADENSFALL_COLUMN_SCHADENSPOSITION));
        String date = cursor.getString(cursor.getColumnIndex(Datenbank.SCHADENSFALL_COLUMN_DATUM));
        String status = cursor.getString(cursor.getColumnIndex(Datenbank.SCHADENSFALL_COLUMN_STATUS));
        int gutachterId = cursor.getInt(cursor.getColumnIndex(Datenbank.SCHADENSFALL_COLUMN_GUTACHTER));
        Double flaeche = Double.valueOf(cursor.getString(cursor.getColumnIndex(Datenbank.SCHADENSFALL_COLUMN_FLAECHE)));
        String[] latLng = koordinaten.split(";");
        String[] photos = new String[1];
        photos[0] = bilder;


        ArrayList<LatLng> pointsNew = new ArrayList<LatLng>();
        //Date datumResult = new GregorianCalendar(Integer.parseInt(datum[2]), Integer.parseInt(datum[1]), Integer.parseInt(datum[0])).getTime();
        for (int i = 0; i < latLng.length; i++) {
            String[] coordinate = latLng[i].split(",");
            pointsNew.add(new LatLng(Double.parseDouble(coordinate[0]), Double.parseDouble(coordinate[1])));
        }
        LatLng[] pointsResult = new LatLng[pointsNew.size()];
        for (int i = 0; i < pointsNew.size(); i++) {
            pointsResult[i] = pointsNew.get(i);
        }
        Region r = getRegionById(regionId);
        Feld f = getFeldById(feldId);
        Gutachter g = getGutachterById(gutachterId);
        cursor.close();
        return new Schadensfall(art, f, pointsResult, status, r, date, photos, id, flaeche, g);
    }

    /**
     * getter for a field by id
     *
     * @param identity
     * @return
     */
    public Feld getFeldById(int identity) {
        String[] columns = {Datenbank.FELD_COLUMN_ID, Datenbank.FELD_COLUMN_ART, Datenbank.FELD_COLUMN_FLAECHE, Datenbank.FELD_COLUMN_KOORDINATEN, Datenbank.FELD_COLUMN_REGION, Datenbank.FELD_COLUMN_VERTRAG};
        Cursor cursor = database.query(Datenbank.TABLE_FELD, columns, identity + "=" + Datenbank.FELD_COLUMN_ID, null, null, null, null, null);
        cursor.moveToFirst();
        int id = cursor.getInt(cursor.getColumnIndex(Datenbank.FELD_COLUMN_ID));
        int regionId = cursor.getInt(cursor.getColumnIndex(Datenbank.FELD_COLUMN_REGION));
        int vertragId = cursor.getInt(cursor.getColumnIndex(Datenbank.FELD_COLUMN_VERTRAG));
        String art = cursor.getString(cursor.getColumnIndex(Datenbank.FELD_COLUMN_ART));
        Double flaeche = Double.parseDouble(cursor.getString(cursor.getColumnIndex(Datenbank.FELD_COLUMN_FLAECHE)));
        String koordinaten = cursor.getString(cursor.getColumnIndex(Datenbank.FELD_COLUMN_KOORDINATEN));
        String[] latLng = koordinaten.split(";");
        ArrayList<LatLng> pointsNew = new ArrayList<LatLng>();
        for (int i = 0; i < latLng.length; i++) {
            String[] coordinate = latLng[i].split(",");
            pointsNew.add(new LatLng(Double.parseDouble(coordinate[0]), Double.parseDouble(coordinate[1])));
        }
        LatLng[] pointsResult = new LatLng[pointsNew.size()];
        for (int i = 0; i < pointsNew.size(); i++) {
            pointsResult[i] = pointsNew.get(i);
        }
        Region r = getRegionById(regionId);
        Vertrag v = getVertragById(vertragId);
        cursor.close();
        return new Feld(id, art, flaeche, pointsResult, r, v);
    }

    /**
     * getter for a region by id
     *
     * @param identity
     * @return
     */
    public Region getRegionById(int identity) {
        String[] columns = {Datenbank.REGION_COLUMN_ID, Datenbank.REGION_COLUMN_BUNDESLAND, Datenbank.REGION_COLUMN_LAND, Datenbank.REGION_COLUMN_LANDKREIS, Datenbank.REGION_COLUMN_STADT};
        Cursor cursor = database.query(Datenbank.TABLE_REGION, columns, identity + "=" + Datenbank.REGION_COLUMN_ID, null, null, null, null, null);
        cursor.moveToFirst();
        int id = cursor.getInt(cursor.getColumnIndex(Datenbank.REGION_COLUMN_ID));
        String bundesland = cursor.getString(cursor.getColumnIndex(Datenbank.REGION_COLUMN_BUNDESLAND));
        String land = cursor.getString(cursor.getColumnIndex(Datenbank.REGION_COLUMN_LAND));
        String landkreis = cursor.getString(cursor.getColumnIndex(Datenbank.REGION_COLUMN_LANDKREIS));
        String stadt = cursor.getString(cursor.getColumnIndex(Datenbank.REGION_COLUMN_STADT));
        cursor.close();
        return new Region(landkreis, land, bundesland, stadt, id);
    }

    /**
     * delete a contract out of the database and also delete a damage case and a field where the contract is involved
     *
     * @param vertrag
     */
    public void removeVertrag(Vertrag vertrag) {
        database.execSQL("DELETE FROM " + Datenbank.TABLE_FELD + " WHERE " + Datenbank.FELD_COLUMN_VERTRAG + "=" + vertrag.getId());
        database.execSQL("DELETE FROM " + Datenbank.TABLE_VERTRAG + " WHERE " + Datenbank.VERTRAG_COLUMN_ID + "=" + vertrag.getId());
    }

    /**
     * Method removes a field from a contract and adds the field to field without a contract
     * @param feld
     */
    public void removeFeldFromVertrag(Feld feld){
        database.execSQL("DELETE FROM " + Datenbank.TABLE_FELD + " WHERE " + Datenbank.FELD_COLUMN_ID + "=" + feld.getId());
    }

    /**
     * delete a user out of the database and also delete all contracts where the user is involved
     *
     * @param benutzer
     */
    public void removeBenutzer(Benutzer benutzer) {
        int userId = benutzer.getId();
        for(Schadensfall sf : getSchadensfall()) {
            if(sf.getFeld().getVertrag().getPerson().getId() == userId) {
                //delete schadensfall first, because we need the field
                database.execSQL("DELETE FROM " + Datenbank.TABLE_SCHADENSFALL + " WHERE " + Datenbank.SCHADENSFALL_COLUMN_ID + "=" + sf.getId());
            }
        }

        for(Feld f : getFeld()) {
            if(f.getVertrag().getPerson().getId() == userId) {
                //then delete field because we still need the contract
                database.execSQL("DELETE FROM " + Datenbank.TABLE_FELD + " WHERE " + Datenbank.FELD_COLUMN_ID + "=" + f.getId());
            }
        }

        /*if(Datenbank.VERTRAG_COLUMN_BENUTZER!=null) {
            //delete contract that was referenced by this user
            database.execSQL("DELETE FROM " + Datenbank.TABLE_VERTRAG + " WHERE " + Datenbank.VERTRAG_COLUMN_BENUTZER + "=" + benutzer.getId());
        }*/
        //delete user data for this user ID
        database.execSQL("DELETE FROM " + Datenbank.TABLE_BENUTZER + " WHERE " + Datenbank.BENUTZER_COLUMN_ID + "=" + benutzer.getId());

    }

    /**
     * delete a expert out of the database
     *
     * @param gutachter
     */
    public void removeGutachter(Gutachter gutachter) {
        database.execSQL("DELETE FROM " + Datenbank.TABLE_GUTACHTER + " WHERE " + Datenbank.GUTACHTER_COLUMN_ID + "=" + gutachter.getId());
    }

    /**
     * delete a field from the database and also delete a damage case where the field is involved
     *
     * @param feld
     */
    public void removeFeld(Feld feld) {
        database.execSQL("DELETE FROM " + Datenbank.TABLE_FELD + " WHERE " + Datenbank.FELD_COLUMN_ID + "=" + feld.getId());
        database.execSQL("DELETE FROM " + Datenbank.TABLE_SCHADENSFALL + " WHERE " + Datenbank.SCHADENSFALL_COLUMN_FELD + "=" + feld.getId());
    }

    /**
     * delete a damage case
     *
     * @param schadensfall
     */
    public void removeSchadensfall(Schadensfall schadensfall) {
        database.execSQL("DELETE FROM " + Datenbank.TABLE_SCHADENSFALL + " WHERE " + Datenbank.SCHADENSFALL_COLUMN_ID + "=" + schadensfall.getId());
    }

    /**
     * delete a region out of the database and also delete a damage case and field where the region is involved
     *
     * @param region
     */
    public void removeRegion(Region region) {
        //database.execSQL("DELETE FROM " + Datenbank.TABLE_SCHADENSFALL + " WHERE " + Datenbank.SCHADENSFALL_COLUMN_REGION + "=" + region.getId());
        //database.execSQL("DELETE FROM " + Datenbank.TABLE_FELD + " WHERE " + Datenbank.FELD_COLUMN_REGION + "=" + region.getId());
        database.execSQL("DELETE FROM " + Datenbank.TABLE_REGION + " WHERE " + Datenbank.REGION_COLUMN_ID + "=" + region.getId());
    }
}
