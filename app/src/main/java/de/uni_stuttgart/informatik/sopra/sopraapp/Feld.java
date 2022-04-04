package de.uni_stuttgart.informatik.sopra.sopraapp;

import com.google.android.gms.maps.model.LatLng;

/**
 * Class that capsules attributes for a field
 *
 * @author Patrick Koss
 * @author Sarah Gutierrez Muñoz
 * @author Michael Hersam
 */

public class Feld {
    private int id;
    private String art;
    private double flaeche;
    private LatLng koordinaten[];
    private Region region;
    private Vertrag vertrag;

    /**
     * constructor
     *
     * @param id field specific id
     * @param art type of field
     * @param flaeche are of field
     * @param koordinaten coordinates of field
     * @param region location of field
     */
    public Feld(int id, String art, double flaeche, LatLng[] koordinaten, Region region, Vertrag vertrag) {
        this.id = id;
        this.art = art;
        this.flaeche = flaeche;
        this.koordinaten = koordinaten;
        this.region = region;
        this.vertrag = vertrag;
    }

    /**
     * getter Vertrag
     *
     * @return contract(s) of field
     */
    public Vertrag getVertrag() {
        return vertrag;
    }

    /**
     * getter ID
     *
     * @return field ID
     */
    public int getId() {
        return id;
    }

    /**
     * getter Art
     *
     * @return field type
     */
    public String getArt() {
        return art;
    }

    /**
     * getter Flaeche
     *
     * @return field area
     */
    public double getFlaeche() {
        return flaeche;
    }

    /**
     * getter Koordinaten
     *
     * @return field coordinates
     */
    public LatLng[] getKoordinaten() {
        return koordinaten;
    }

    /**
     * getter Region
     *
     * @return region
     */
    public Region getRegion() {
        return region;
    }
}
