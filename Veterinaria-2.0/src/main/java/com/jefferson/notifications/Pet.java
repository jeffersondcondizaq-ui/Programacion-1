package com.jefferson.notifications;

public class Pet {

    private String name;
    private String species;
    private String race;
    private String ageace;
    private String id;

    // Dueño
    private Person person;

    public Pet(String name, String species, String race,
                   String ageace, String id, Person person) {

        this.name = name;
        this.species = species;
        this.race = race;
        this.ageace = ageace;
        this.id = id;
        this.person = person;
    }

    // Getters y setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getAgeace() {
        return ageace;
    }

    public void setAgeace(String ageace) {
        this.ageace = ageace;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}