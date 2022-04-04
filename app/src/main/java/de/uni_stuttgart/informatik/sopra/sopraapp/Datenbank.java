package de.uni_stuttgart.informatik.sopra.sopraapp;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Class that creates a database
 *
 * @author Patrick Koss
 * @author Sarah Gutierrez Munoz
 * @author Michael Hersam
 */

public class Datenbank extends SQLiteOpenHelper {

    public static final String LOG_TAG = Datenbank.class.getSimpleName();

    public static final String DB_NAME = "Datenbank.db";
    private static final int DB_VERSON = 1;

    public static final String TABLE_REGION = "region";
    public static final String REGION_COLUMN_STADT = "stadt";
    public static final String REGION_COLUMN_BUNDESLAND = "bundesland";
    public static final String REGION_COLUMN_LAND = "land";
    public static final String REGION_COLUMN_LANDKREIS = "landkreis";
    public static final String REGION_COLUMN_ID = "id";

    public static final String TABLE_GUTACHTER = "gutachter";
    public static final String GUTACHTER_COLUMN_VORNAME = "vorname";
    public static final String GUTACHTER_COLUMN_NACHNAME = "nachname";
    public static final String GUTACHTER_COLUMN_ADRESSE = "adresse";
    public static final String GUTACHTER_COLUMN_ID = "id";
    public static final String GUTACHTER_COLUMN_PASSWORT = "passwort";

    public static final String TABLE_BENUTZER = "benutzer";
    public static final String BENUTZER_COLUMN_VORNAME = "vorname";
    public static final String BENUTZER_COLUMN_NACHNAME = "nachname";
    public static final String BENUTZER_COLUMN_ADRESSE = "adresse";
    public static final String BENUTZER_COLUMN_ID = "id";
    public static final String BENUTZER_COLUMN_PASSWORT = "passwort";

    public static final String TABLE_VERTRAG = "vertrag";
    public static final String VERTRAG_COLUMN_ID = "id";
    public static final String VERTRAG_COLUMN_BENUTZER = "benutzer";
    public static final String VERTRAG_COLUMN_ART = "art";

    public static final String TABLE_FELD = "feld";
    public static final String FELD_COLUMN_ID = "id";
    public static final String FELD_COLUMN_ART = "art";
    public static final String FELD_COLUMN_FLAECHE = "flaeche";
    public static final String FELD_COLUMN_KOORDINATEN = "koordinaten";
    public static final String FELD_COLUMN_REGION = "region";
    public static final String FELD_COLUMN_VERTRAG = "vertrag";

    public static final String TABLE_FELDOHNEVERTRAG = "feldohnevertrag";
    public static final String FELDOHNEVERTRAG_COLUMN_ID = "id";
    public static final String FELDOHNEVERTRAG_COLUMN_ART = "art";
    public static final String FELDOHNEVERTRAG_COLUMN_FLAECHE = "flaeche";
    public static final String FELDOHNEVERTRAG_COLUMN_KOORDINATEN = "koordinaten";
    public static final String FELDOHNEVERTRAG_COLUMN_REGION = "region";
    public static final String FELDOHNEVERTRAG_COLUMN_BENUTZER = "benutzer";

    public static final String TABLE_SCHADENSFALL = "schadensfall";
    public static final String SCHADENSFALL_COLUMN_ID = "id";
    public static final String SCHADENSFALL_COLUMN_SCHADENSART = "schadensart";
    public static final String SCHADENSFALL_COLUMN_FELD = "feld";
    public static final String SCHADENSFALL_COLUMN_SCHADENSPOSITION = "schadensposition";
    public static final String SCHADENSFALL_COLUMN_STATUS = "status";
    public static final String SCHADENSFALL_COLUMN_REGION = "region";
    public static final String SCHADENSFALL_COLUMN_DATUM = "datum";
    public static final String SCHADENSFALL_COLUMN_BILDER = "bilder";
    public static final String SCHADENSFALL_COLUMN_FLAECHE = "flaeche";
    public static final String SCHADENSFALL_COLUMN_GUTACHTER = "gutachter";


    public static final String SQL_CREATE_REGION =
            "CREATE TABLE " + TABLE_REGION + "(" + REGION_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    REGION_COLUMN_STADT + " TEXT NOT NULL, " +
                    REGION_COLUMN_LANDKREIS + " TEXT NOT NULL, " +
                    REGION_COLUMN_BUNDESLAND + " TEXT NOT NULL, " +
                    REGION_COLUMN_LAND + " TEXT NOT NULL);";

    public static final String SQL_CREATE_GUTACHTER =
            "CREATE TABLE " + TABLE_GUTACHTER + "(" + GUTACHTER_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    GUTACHTER_COLUMN_VORNAME + " TEXT NOT NULL, " +
                    GUTACHTER_COLUMN_NACHNAME + " TEXT NOT NULL, " +
                    GUTACHTER_COLUMN_PASSWORT + " TEXT NOT NULL, " +
                    GUTACHTER_COLUMN_ADRESSE + " TEXT NOT NULL);";

    public static final String SQL_CREATE_BENUTZER =
            "CREATE TABLE " + TABLE_BENUTZER + "(" + BENUTZER_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    BENUTZER_COLUMN_VORNAME + " TEXT NOT NULL, " +
                    BENUTZER_COLUMN_NACHNAME + " TEXT NOT NULL, " +
                    BENUTZER_COLUMN_PASSWORT + " TEXT NOT NULL, " +
                    BENUTZER_COLUMN_ADRESSE + " TEXT NOT NULL);";

    public static final String SQL_CREATE_VERTRAG =
            "CREATE TABLE " + TABLE_VERTRAG + "(" + VERTRAG_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    VERTRAG_COLUMN_BENUTZER + " INTEGER NOT NULL, " +
                    VERTRAG_COLUMN_ART + " TEXT NOT NULL, " +
                    "FOREIGN KEY (benutzer) REFERENCES benutzer(id) ON DELETE CASCADE);";

    public static final String SQL_CREATE_FELD =
            "CREATE TABLE " + TABLE_FELD + "(" + FELD_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    FELD_COLUMN_ART + " TEXT NOT NULL, " +
                    FELD_COLUMN_FLAECHE + " TEXT NOT NULL, " +
                    FELD_COLUMN_KOORDINATEN + " TEXT NOT NULL, " +
                    FELD_COLUMN_REGION + " INTEGER NOT NULL, " +
                    FELD_COLUMN_VERTRAG + " INTEGER NOT NULL, " +
                    "FOREIGN KEY (region) REFERENCES region(id) ON DELETE CASCADE, " +
                    "FOREIGN KEY (vertrag) REFERENCES vertrag(id) ON DELETE CASCADE);";

    public static final String SQL_CREATE_FELDOHNEVERTRAG =
            "CREATE TABLE " + TABLE_FELDOHNEVERTRAG + "(" + FELDOHNEVERTRAG_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    FELDOHNEVERTRAG_COLUMN_ART + " TEXT NOT NULL, " +
                    FELDOHNEVERTRAG_COLUMN_FLAECHE + " TEXT NOT NULL, " +
                    FELDOHNEVERTRAG_COLUMN_KOORDINATEN + " TEXT NOT NULL, " +
                    FELDOHNEVERTRAG_COLUMN_REGION + " INTEGER NOT NULL, " +
                    FELDOHNEVERTRAG_COLUMN_BENUTZER + " INTEGER NOT NULL, " +
                    "FOREIGN KEY (benutzer) REFERENCES benutzer(id) ON DELETE CASCADE, " +
                    "FOREIGN KEY (region) REFERENCES region(id) ON DELETE CASCADE);";

    public static final String SQL_CREATE_SCHADENSFALL =
            "CREATE TABLE " + TABLE_SCHADENSFALL + "(" + SCHADENSFALL_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    SCHADENSFALL_COLUMN_SCHADENSART + " TEXT NOT NULL, " +
                    SCHADENSFALL_COLUMN_SCHADENSPOSITION + " TEXT NOT NULL, " +
                    SCHADENSFALL_COLUMN_STATUS + " TEXT NOT NULL, " +
                    SCHADENSFALL_COLUMN_DATUM + " TEXT NOT NULL, " +
                    SCHADENSFALL_COLUMN_FELD + " INTEGER NOT NULL, " +
                    SCHADENSFALL_COLUMN_REGION + " INTEGER NOT NULL, " +
                    SCHADENSFALL_COLUMN_BILDER + " TEXT, " +
                    SCHADENSFALL_COLUMN_FLAECHE + " TEXT NOT NULL, " +
                    SCHADENSFALL_COLUMN_GUTACHTER + " TEXT NOT NULL, " +
                    "FOREIGN KEY (region) REFERENCES region(id) ON DELETE CASCADE, " +
                    "FOREIGN KEY (gutachter) REFERENCES gutachter(id) ON DELETE CASCADE, " +
                    "FOREIGN KEY (feld) REFERENCES feld(id) ON DELETE CASCADE);";


    public Datenbank(Context context) {
        super(context, DB_NAME, null, DB_VERSON);
        Log.d(LOG_TAG, "Datenbank: " + getDatabaseName() + " wurde erzeugt.");
    }

    public Datenbank(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(SQL_CREATE_BENUTZER);
            Log.e(LOG_TAG, TABLE_BENUTZER + " wurde erzeugt");
            db.execSQL(SQL_CREATE_VERTRAG);
            Log.e(LOG_TAG, TABLE_VERTRAG + " wurde erzeugt");
            db.execSQL(SQL_CREATE_GUTACHTER);
            Log.e(LOG_TAG, TABLE_GUTACHTER + " wurde erzeugt");
            db.execSQL(SQL_CREATE_REGION);
            Log.e(LOG_TAG, TABLE_REGION + " wurde erzeugt");
            db.execSQL(SQL_CREATE_SCHADENSFALL);
            Log.e(LOG_TAG, TABLE_SCHADENSFALL + " wurde erzeugt");
            db.execSQL(SQL_CREATE_FELD);
            Log.e(LOG_TAG, TABLE_FELD + " wurde erzeugt");
            db.execSQL(SQL_CREATE_FELDOHNEVERTRAG);
            Log.e(LOG_TAG, TABLE_FELDOHNEVERTRAG + " wurde erzeugt");
        } catch (Exception e) {
            Log.e(LOG_TAG, R.string.error_1 + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
