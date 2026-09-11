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
                "4.Eliminar mascota\n" +
                "5.Actualizar mascota\n"+
                "0.Salir";

        do {
            option = pedirOpcion(mensaje);
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
                case 4:
                    eliminarMascota();
                    break;
                case 5:
                    actualizarMascota();
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

        if(buscarIndicePorId(id) != -1){
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

    //Ver informacion de una mascotas por id

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

    //Buscar mascota por Id

    public static void buscarMascotaId() {
        String id = pedirDatos("Ingrese el ID de la mascota a buscar");

        int indexMascota = buscarIndicePorId(id);

        if (indexMascota == -1) {
            mostrarMensaje("Mascota no encontrada");
            return;
        }

        Mascota mascota = listaMascotas[indexMascota];

        String mensaje = "Mascota: " + mascota.getNombre() + "\n" +
                "ID: " + mascota.getId() + "\n" +
                "Especie: " + mascota.getEspecie() + "\n" +
                "Raza: " + mascota.getRaza() + "\n" +
                "Edad: " + mascota.getEdad() + "\n" +
                "Propietario: " + mascota.getNombrePropietario() + "\n" +
                "Teléfono: " + mascota.getTelefonoPropietario();

        mostrarMensaje(mensaje);
    }


    //Eliminar mascota por su id

    public static void eliminarMascota(){
        String mensaje="";
        String idMascota = pedirDatos("Ingrese el Id de la mascota a eliminar");
        int indexMascota = buscarIndicePorId(idMascota);

        if (indexMascota != -1){
            listaMascotas[indexMascota] = null;
            mostrarMensaje("La mascota con el ID " + idMascota +
                    " fue eliminada correctamente.");
            return;
        }

        mensaje = "La mascota con el id: "+idMascota+" no existe";
        mostrarMensaje(mensaje);
    }

    //Actualizar los datos de una mascota dependiendo del dato que se desee actualizar

    public static void actualizarMascota() {
        String idMascota = pedirDatos("Ingrese el Id de la mascota a actualizar");
        int indexMascota = buscarIndicePorId(idMascota);

        if (indexMascota == -1) {
            mostrarMensaje("La mascota con el ID " + idMascota + " no existe");
            return;
        }

        Mascota mascota = listaMascotas[indexMascota];

        String menu = "¿Qué campo desea actualizar?\n" +
                "1. Nombre\n" +
                "2. Especie\n" +
                "3. Raza\n" +
                "4. Edad\n" +
                "5. Nombre propietario\n" +
                "6. Teléfono propietario\n" +
                "0. Cancelar";

        int opcion = pedirOpcion(menu);

        switch (opcion) {
            case 1:
                mascota.setNombre(pedirDatos("Nuevo nombre: "));
                break;

            case 2:
                mascota.setEspecie(pedirDatos("Nueva especie: "));
                break;

            case 3:
                mascota.setRaza(pedirDatos("Nueva raza: "));
                break;

            case 4:
                mascota.setEdad(pedirDatos("Nueva edad: "));
                break;

            case 5:
                mascota.setNombrePropietario(
                        pedirDatos("Nuevo nombre del propietario: ")
                );
                break;

            case 6:
                mascota.setTelefonoPropietario(
                        pedirDatos("Nuevo teléfono del propietario: ")
                );
                break;

            case 0:
                mostrarMensaje("Actualización cancelada.");
                return;

            default:
                mostrarMensaje("Opción no válida.");
                return;
        }

        mostrarMensaje("Mascota actualizada correctamente.");
    }


    // Buscar el indice de la mascota por id
    public static int  buscarIndicePorId(String id){
        for (int i = 0; i < listaMascotas.length; i++) {
            if(listaMascotas[i] != null && listaMascotas[i].getId().equals(id)){
                return i;
            }
        }
        return -1;
    }


    //---------------------------Pedir datos-----------------------------
    //Data
    private static String pedirDatos(String mensaje){
        return JOptionPane.showInputDialog(mensaje, null);
    }

    //mensajes
    private static void mostrarMensaje (String mensaje){
        JOptionPane.showMessageDialog(null,mensaje);
    }

    //Opcion de menu
    private static int pedirOpcion(String mensaje) {
        while (true) {
            try {
                return Integer.parseInt(pedirDatos(mensaje));
            } catch (NumberFormatException e) {
                mostrarMensaje("Debe ingresar un número válido.");
            }
        }
    }


}
