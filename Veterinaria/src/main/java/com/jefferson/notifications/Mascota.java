package com.jefferson.notifications;

public class Mascota {
    private String nombre;
    private String especie;
    private String raza;
    private  String edad;
    private  String id;
    private  String nombrePropietario;
    private String telefonoPropietario;

    public Mascota (String nombre, String especie, String raza, String edad, String id, String nombrePropietario, String telefonoPropietario){
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.edad = edad;
        this.id = id;
        this.nombrePropietario = nombrePropietario;
        this.telefonoPropietario = telefonoPropietario;
    }
    //get and set

    public String getEspecie() {
        return especie;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRaza() {
        return raza;
    }

    public String getEdad() {
        return edad;
    }

    public String getNombrePropietario() {
        return nombrePropietario;
    }

    public String getId() {
        return id;
    }

    public String getTelefonoPropietario() {
        return telefonoPropietario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombrePropietario(String nombrePropietario) {
        this.nombrePropietario = nombrePropietario;
    }

    public void setTelefonoPropietario(String telefonoPropietario) {
        this.telefonoPropietario = telefonoPropietario;
    }
}
