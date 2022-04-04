package de.uni_stuttgart.informatik.sopra.sopraapp;

/**
 * Class that capsules attributes for a Region
 *
 * @author Patrick Koss
 * @author Sarah Gutierrez Munoz
 * @author Michael Hersam
 */

public class Region {

    //attributes
    private String landkreis;
    private String land;
    private String bundesland;
    private String stadt;
    private int id;

    /**
     * constructor
     *
     * @param landkreis
     * @param land
     * @param bundesland
     * @param stadt
     */
    public Region(String landkreis, String land, String bundesland, String stadt, int id) {
        this.landkreis = landkreis;
        this.land = land;
        this.bundesland = bundesland;
        this.stadt = stadt;
        this.id = id;
    }

    /**
     * getter ID
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * getter Landkreis
     *
     * @return
     */
    public String getLandkreis() {
        return landkreis;
    }

    /**
     * getter Land
     *
     * @return
     */
    public String getLand() {
        return land;
    }

    /**
     * getter Bundesland
     *
     * @return
     */
    public String getBundesland() {
        return bundesland;
    }

    /**
     * getter Stadt
     *
     * @return
     */
    public String getStadt() {
        return stadt;
    }
}
