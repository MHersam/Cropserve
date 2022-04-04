package de.uni_stuttgart.informatik.sopra.sopraapp;

/**
 * Class that capsules attributes for an expert
 *
 * @author Patrick Koss
 * @author Sarah Gutierrez Munoz
 * @author Michael Hersam
 */
public class Gutachter extends Person {

    /**
     * constructor
     *
     * @param vorname reviewer's name
     * @param nachname reviewer's surname
     * @param adresse reviewer's address
     * @param id reviewer's specific ID
     */
    public Gutachter(String vorname, String nachname, String adresse, int id, String passwort) {
        super(vorname, nachname, adresse, id, passwort);
    }
}
