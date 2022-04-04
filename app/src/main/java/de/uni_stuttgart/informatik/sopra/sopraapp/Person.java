package de.uni_stuttgart.informatik.sopra.sopraapp;

/**
 * Class that capsules attributes for a Person
 *
 * @author Patrick Koss
 * @author Sarah Gutierrez Muñoz
 * @author Michael Hersam
 */

public class Person {

    //attributes
    private String vorname;
    private String nachname;
    private String adresse;
    private int id;
    private String passwort;

    /**
     * constructor
     *
     * @param vorname
     * @param nachname
     * @param adresse
     * @param id
     */
    public Person(String vorname, String nachname, String adresse, int id, String passwort) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.adresse = adresse;
        this.id = id;
        this.passwort = passwort;
    }

    /**
     * getter passwort
     * @return
     */
    public String getPasswort() {
        return passwort;
    }

    /**
     * getter Vorname
     *
     * @return
     */
    public String getVorname() {
        return vorname;
    }

    /**
     * getter Nachname
     *
     * @return
     */
    public String getNachname() {
        return nachname;
    }

    public String getFullName() {
        return this.getVorname() + " " + this.getNachname();
    }

    /**
     * getter Adresse
     *
     * @return
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * getter ID
     *
     * @return
     */
    public int getId() {
        return id;
    }
}
