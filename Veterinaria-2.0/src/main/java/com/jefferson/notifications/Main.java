package com.jefferson.notifications;

import javax.swing.*;

public class Main {

    static Veterinaria veterinaria;

    static void main() {
        String nit = pedirDatos("NIT de la veterinaria: ");
        String nombreVeterinaria = pedirDatos("Nombre de la veterinaria: ");
        veterinaria = new Veterinaria(nit, nombreVeterinaria);

        int option = 0;
        String mensaje = "Bienvenido a " + veterinaria.getName() + ": \n" +
                "Selecciones una de las operaciones \n" +
                "1.Registar mascota\n" +
                "2.Visualizar mascotar\n" +
                "3.Buacar mascota por id\n" +
                "4.Eliminar mascota\n" +
                "5.Actualizar mascota\n" +
                "6.Buscar duenio por id\n" +
                "7. Atualizar duenio\n"+
                "8. Eliminar duenio\n"+
                "9. Visualizar duenios\n"+
                "0.Salir";

        do {
            option = pedirOpcion(mensaje);
            switch (option){
                case 1:
                    veterinaria.registrarMascota();
                    break;
                case 2:
                    veterinaria.visualizarMascotas();
                    break;
                case 3:
                    veterinaria.buscarMascotaId();
                    break;
                case 4:
                    veterinaria.eliminarMascota();
                    break;
                case 5:
                    veterinaria.actualizarMascota();
                    break;
                case 6:
                    veterinaria.buscarDuenioId();
                    break;
                case 7:
                    veterinaria.actualizarDuenio();
                    break;
                case 8:
                    veterinaria.eliminarDuenio();
                    break;
                case 9:
                    veterinaria.visualizarDuenios();
                    break;
                case 0:
                    break;
                default:
                    mostrarMensaje("Opcion no valida");
            }
        }while (option != 0);

    }

    //---------------------------Pedir datos-----------------------------
    //Data
    static String pedirDatos(String mensaje){
        return JOptionPane.showInputDialog(mensaje, null);
    }

    //mensajes
    static void mostrarMensaje (String mensaje){
        JOptionPane.showMessageDialog(null,mensaje);
    }

    //Opcion de menu
    static int pedirOpcion(String mensaje) {
        while (true) {
            try {
                return Integer.parseInt(pedirDatos(mensaje));
            } catch (NumberFormatException e) {
                mostrarMensaje("Debe ingresar un número válido.");
            }
        }
    }

}