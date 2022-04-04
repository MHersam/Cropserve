package de.uni_stuttgart.informatik.sopra.sopraapp;

/**
 * Class that capsules attributes for a contract
 *
 * @author Patrick Koss
 * @author Sarah Gutierrez Munoz
 * @author Michael Hersam
 */

public class Vertrag {
    private int id;
    private Benutzer person;
    private String art;

    /**
     * constructor
     * @param id user ID
     * @param person the user
     * @param art type of contract
     */
    public Vertrag(int id, Benutzer person, String art) {
        this.id = id;
        this.person = person;
        this.art = art;
    }

    /**
     * getter art
     * @return Vertragsart
     */
    public String getArt() {
        return art;
    }

    /**
     * getter ID
     * @return contract ID
     */
    public int getId() {
        return id;
    }

    /**
     * getter Person
     * @return person associated w/ contract
     */
    public Benutzer getPerson() {
        return person;
    }

    /**
     * method delete data from memory/database
     */
    public void loeschen(){
        //delete data from database
    }

}
