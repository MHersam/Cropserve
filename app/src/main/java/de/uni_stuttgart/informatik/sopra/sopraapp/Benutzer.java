package de.uni_stuttgart.informatik.sopra.sopraapp;

/**
 * Class that capsules attributes for a User
 *
 * @author Patrick Koss
 * @author Sarah Gutierrez Muñoz
 * @author Michael Hersam
 */
public class Benutzer extends Person {

    /**
     * constructor
     *
     * @param vorname the user's name
     * @param nachname the user's surname
     * @param adresse the user's address
     * @param id user specific id
     */
    public Benutzer(String vorname, String nachname, String adresse, int id, String passwort) {
        super(vorname, nachname, adresse, id, passwort);
    }
}
