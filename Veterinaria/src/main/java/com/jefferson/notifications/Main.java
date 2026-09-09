package com.jefferson.notifications;

import javax.swing.*;

public class Main {
     static Mascota[] listaMascotas = new Mascota[10];
    static void main() {
        int option = 0;
        String mensaje = "Bienbenido a la veterinaria: \n" +
                "Selecciones una de las operaciones \n" +
                "1.Registar mascota\n" +
                "2.Visualizar mascotar\n" +
                "3.Buacar mascota por id\n" +
                "0.Salir";

        do {
            option = Integer.parseInt(JOptionPane.showInputDialog(mensaje,null));
            switch (option){
                case 1:
                    registarMascota();
                    break;
                case 2:
                    visualizarMascotas();
                    break;
                case 3:
                    buscarMascotaId();
                    break;
                case 0:
                    break;
                default:
                    mostrarMensaje("Opcion no valida");
            }
        }while (option != 0);

    }

    //Registar mascota

    public static boolean registarMascota(){
        String id = pedirDatos("ID: ");

        if(validateId(id)){
            mostrarMensaje("El ID ya esta registardo");
            return false;
        }
        for (int i = 0; i < listaMascotas.length ; i++) {
            if(listaMascotas[i] == null){
                Mascota mascota = new Mascota(
                        pedirDatos("Nombre: "),
                        pedirDatos("Especie: "),
                        pedirDatos("Raza: "),
                        pedirDatos("Edad: "),
                        id,
                        pedirDatos("Nombre propietario: "),
                        pedirDatos("Telefono propietario: ")
                );
                listaMascotas[i] = mascota;
                mostrarMensaje("Mascota registrada correctamente");
                return true;
            }
        }
        mostrarMensaje("No hay espacio para registrar más mascotas");
        return false;
    }

    //Ver mascotas

    public static void visualizarMascotas (){
        boolean hayMascotas = false;

        for (int i = 0; i < listaMascotas.length; i++) {
            if (listaMascotas[i] != null ){
                Mascota mascota = listaMascotas[i];
                String mensaje = "Mascota #" + (i + 1) + "\n" +
                        "ID: " + mascota.getId() + "\n" +
                        "Nombre: " + mascota.getNombre() + "\n" +
                        "Especie: " + mascota.getEspecie() + "\n" +
                        "Raza: " + mascota.getRaza() + "\n" +
                        "Edad: " + mascota.getEdad() + "\n" +
                        "Propietario: " + mascota.getNombrePropietario() + "\n" +
                        "Teléfono: " + mascota.getTelefonoPropietario();

                mostrarMensaje(mensaje);

                hayMascotas = true;
            }
        }
        if (!hayMascotas) {
            mostrarMensaje("No hay mascotas registradas.");
        }
    }

    //Buscar por Id

    public static void buscarMascotaId(){
        String mensaje = "";
        String id = pedirDatos("Ingrese el Id de la mascota a buscar");

        for (int i = 0; i < listaMascotas.length ; i++) {
            if (validateId(id)){
                Mascota mascota = listaMascotas[i];
                 mensaje = "Mascota:" + mascota.getNombre() + "\n" +
                        "ID: " + mascota.getId() + "\n" +
                        "Especie: " + mascota.getEspecie() + "\n" +
                        "Raza: " + mascota.getRaza() + "\n" +
                        "Edad: " + mascota.getEdad() + "\n" +
                        "Propietario: " + mascota.getNombrePropietario() + "\n" +
                        "Teléfono: " + mascota.getTelefonoPropietario();
                 mostrarMensaje(mensaje);
                return;
            }
        }
        mostrarMensaje("Mascota no encontrada");
    }

    // Validar Id ingresado
    public static boolean validateId(String id){
        boolean existence = false;
        for (int i = 0; i < listaMascotas.length; i++) {
            if(listaMascotas[i] != null && listaMascotas[i].getId().equals(id)){
                existence = true;
            }
        }
        return existence;
    }


    //Data
    private static String pedirDatos(String mensaje){
        return JOptionPane.showInputDialog(mensaje, null);
    }

    //mensajes
    private static void mostrarMensaje (String mensaje){
        JOptionPane.showMessageDialog(null,mensaje);
    }

}
