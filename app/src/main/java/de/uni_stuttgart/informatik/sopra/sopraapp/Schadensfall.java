package de.uni_stuttgart.informatik.sopra.sopraapp;

import com.google.android.gms.maps.model.LatLng;

/**
 * Class that capsules attributes for a damage case
 *
 * @author Patrick Koss
 * @author Sarah Gutierrez Munoz
 * @author Michael Hersam
 */

public class Schadensfall {

    //attributes
    private String schadensart;
    private Feld feld;
    private LatLng schadensposition[];
    private String status;
    private Region region;
    private String datum;
    private String bilder[];
    private int id;
    double flaeche;
    Gutachter gutachter;

    /**
     * constructor
     *
     * @param schadensart
     * @param feld
     * @param schadensposition
     * @param status
     * @param region
     * @param datum
     * @param bilder
     */
    public Schadensfall(String schadensart, Feld feld, LatLng[] schadensposition, String status, Region region, String datum, String[] bilder, int id, double flaeche, Gutachter gutachter) {
        this.schadensart = schadensart;
        this.feld = feld;
        this.schadensposition = schadensposition;
        this.status = status;
        this.region = region;
        this.datum = datum;
        this.bilder = bilder;
        this.id = id;
        this.flaeche=flaeche;
        this.gutachter=gutachter;
    }

    /**
     * getter id
     *
     * @return int ID
     */
    public int getId() {
        return id;
    }

    /**
     * getter schadensart
     *
     * @return String with the type of damage
     */
    public String getSchadensart() {
        return schadensart;
    }

    /**
     * getter feld
     *
     * @return the field associated with the case
     */
    public Feld getFeld() {
        return feld;
    }

    /**
     * getter Schadensposition
     *
     * @return the position of the dmg
     */
    public LatLng[] getSchadensposition() {
        return schadensposition;
    }

    /**
     * getter Status
     *
     * @return the current status of the case
     */
    public String getStatus() {
        return status;
    }

    /**
     * getter Region
     *
     * @return the region for the case
     */
    public Region getRegion() {
        return region;
    }

    /**
     * getter Datum
     *
     * @return the date the case was registered
     */
    public String getDatum() {
        return datum;
    }

    /**
     * getter Bilder
     *
     * @return the pictures taken of the dmg
     */
    public String[] getBilder() {
        return bilder;
    }

    /**
     * method to get flaeche
     * @return the area of the dmg
     */
    public double getFlaeche() {
        return flaeche;
    }

    /**
     * method to get gutachter
     * @return the Gutachter associated with the case
     */
    public Gutachter getGutachter() {
        return gutachter;
    }

}
