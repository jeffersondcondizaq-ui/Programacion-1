package com.jefferson.notifications;

public class Veterinaria {

    private String nit;
    private String name;

    private Pet[] listaMascotas;
    private Person[] listaPersonas;

    //constructor
    public Veterinaria(String nit, String name) {
        this.nit = nit;
        this.name = name;
        this.listaMascotas = new Pet[10];
        this.listaPersonas = new Person[10];
    }

    // Getters y setters

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Pet[] getListaMascotas() {
        return listaMascotas;
    }

    public Person[] getListaPersonas() {
        return listaPersonas;
    }


    //Registar mascota

    public Pet registrarMascota(){
        String id = Main.pedirDatos("ID mascota: ");
        int indiceMascota = buscarIndiceMascotaPorId(id);

        if(indiceMascota != -1){
            Main.mostrarMensaje("El ID de la mascota ya esta registardo");
            return listaMascotas[indiceMascota];
        }
        for (int i = 0; i < listaMascotas.length ; i++) {
            if(listaMascotas[i] == null){

                Person propietario = registrarPropietario();

                if (propietario == null) {
                    Main.mostrarMensaje("No se pudo registrar el propietario. Registro cancelado.");
                    return null;
                }
                Pet mascota = new Pet(
                        Main.pedirDatos("Nombre mascota: "),
                        Main.pedirDatos("Especie mascota: "),
                        Main.pedirDatos("Raza mascota: "),
                        Main.pedirDatos("Edad mascota: "),
                        id,
                        propietario
                );
                listaMascotas[i] = mascota;
                agregarMascotaAPersona(propietario, mascota);

                Main.mostrarMensaje("Mascota registrada correctamente");
                return mascota;
            }
        }
        Main.mostrarMensaje("No hay espacio para registrar más mascotas");
        return null;
    }

    // Busca una persona existente por ID, o crea una nueva si no existe

    public Person registrarPropietario() {
        String idPersona = Main.pedirDatos("ID del propietario: ");
        int indexPersona = buscarIndicePersonaPorId(idPersona);

        if (indexPersona != -1) {
            Main.mostrarMensaje("Propietario encontrado: " + listaPersonas[indexPersona].getName());
            return listaPersonas[indexPersona];
        }
        // No existe, se crea una nueva persona
        for (int i = 0; i < listaPersonas.length; i++) {
            if (listaPersonas[i] == null) {
                Person nuevaPersona = new Person(
                        idPersona,
                        Main.pedirDatos("Nombre propietario: "),
                        Main.pedirDatos("Dirección propietario: "),
                        Main.pedirDatos("Teléfono propietario: ")
                );
                listaPersonas[i] = nuevaPersona;
                return nuevaPersona;
            }
        }

        Main.mostrarMensaje("No hay espacio para registrar más propietarios");
        return null;
    }

    // Agrega una mascota al arreglo interno de mascotas de una persona,
    // buscando el primer espacio libre

    private boolean agregarMascotaAPersona(Person persona, Pet mascota) {
        Pet[] mascotasPersona = persona.getListPets();

        for (int i = 0; i < mascotasPersona.length; i++) {
            if (mascotasPersona[i] == null) {
                mascotasPersona[i] = mascota;
                return true;
            }
        }

        Main.mostrarMensaje("El propietario ya tiene el máximo de mascotas registradas");
        return false;
    }

    //Ver informacion de una mascotas por id

    public void visualizarMascotas (){
        boolean hayMascotas = false;

        for (int i = 0; i < listaMascotas.length; i++) {
            if (listaMascotas[i] != null ){
                Pet mascota = listaMascotas[i];
                Person propietario = mascota.getPerson();

                String mensaje = "Mascota #" + (i + 1) + "\n" +
                        "ID: " + mascota.getId() + "\n" +
                        "Nombre: " + mascota.getName() + "\n" +
                        "Especie: " + mascota.getSpecies() + "\n" +
                        "Raza: " + mascota.getRace() + "\n" +
                        "Edad: " + mascota.getAgeace() + "\n" +
                        "Propietario: " + propietario.getName() + "\n" +
                        "Teléfono: " + propietario.getPhoneNumber();

                Main.mostrarMensaje(mensaje);

                hayMascotas = true;
            }
        }
        if (!hayMascotas) {
            Main.mostrarMensaje("No hay mascotas registradas.");
        }
    }

    //Buscar mascota por Id

    public void buscarMascotaId() {
        String id = Main.pedirDatos("Ingrese el ID de la mascota a buscar");

        int indexMascota = buscarIndiceMascotaPorId(id);

        if (indexMascota == -1) {
            Main.mostrarMensaje("Mascota no encontrada");
            return;
        }

        Pet mascota = listaMascotas[indexMascota];
        Person propietario = mascota.getPerson();

        String mensaje = "Mascota: " + mascota.getName() + "\n" +
                "ID: " + mascota.getId() + "\n" +
                "Especie: " + mascota.getSpecies() + "\n" +
                "Raza: " + mascota.getRace() + "\n" +
                "Edad: " + mascota.getAgeace() + "\n" +
                "Propietario: " + propietario.getName() + "\n" +
                "Teléfono: " + propietario.getPhoneNumber();

        Main.mostrarMensaje(mensaje);
    }

    //Buscar duenio por id
    public void buscarDuenioId(){
        String idDuenio = Main.pedirDatos("Ingrese el ID del duenio a buscar");
        int indexDuenio = buscarIndicePersonaPorId(idDuenio);

        if (indexDuenio == -1){
            Main.mostrarMensaje("Duenio no encontrado");
            return;
        }
        Person person = listaPersonas[indexDuenio];
        String nombresMascotas = "";
        Pet[] mascotas = person.getListPets();

        for (int i = 0; i < mascotas.length; i++) {
            Pet mascota = mascotas[i];
            if (mascota != null) {
                if (nombresMascotas.length() > 0) {
                    nombresMascotas = nombresMascotas + ", ";
                }
                nombresMascotas += mascota.getName();
            }
        }
        if (nombresMascotas.length() == 0) {
            nombresMascotas = "Sin mascotas registradas";
        }
        String mensaje = "Id: "+ person.getId()+"\n" +
                "Nombre: "+person.getName()+"\n" +
                "Direccion: "+person.getAddress()+"\n" +
                "Telefono: "+ person.getPhoneNumber()+"\n" +
                "Mascota(s): "+ nombresMascotas;
        Main.mostrarMensaje(mensaje);
    }

    //Eliminar mascota por su id

    public void eliminarMascota(){
        String mensaje="";
        String idMascota = Main.pedirDatos("Ingrese el Id de la mascota a eliminar");
        int indexMascota = buscarIndiceMascotaPorId(idMascota);

        if (indexMascota != -1){
            listaMascotas[indexMascota] = null;
            Main.mostrarMensaje("La mascota con el ID " + idMascota +
                    " fue eliminada correctamente.");
            return;
        }

        mensaje = "La mascota con el id: "+idMascota+" no existe";
        Main.mostrarMensaje(mensaje);
    }

    //Actualizar los datos de una mascota dependiendo del dato que se desee actualizar

    public void actualizarMascota() {
        String idMascota = Main.pedirDatos("Ingrese el Id de la mascota a actualizar");
        int indexMascota = buscarIndiceMascotaPorId(idMascota);

        if (indexMascota == -1) {
            Main.mostrarMensaje("La mascota con el ID " + idMascota + " no existe");
            return;
        }

        Pet mascota = listaMascotas[indexMascota];

        String menu = "¿Qué campo desea actualizar?\n" +
                "1. Nombre\n" +
                "2. Especie\n" +
                "3. Raza\n" +
                "4. Edad\n" +
                "5. Nombre propietario\n" +
                "6. Teléfono propietario\n" +
                "0. Cancelar";

        int opcion = Main.pedirOpcion(menu);

        switch (opcion) {
            case 1:
                mascota.setName(Main.pedirDatos("Nuevo nombre: "));
                break;

            case 2:
                mascota.setSpecies(Main.pedirDatos("Nueva especie: "));
                break;

            case 3:
                mascota.setRace(Main.pedirDatos("Nueva raza: "));
                break;

            case 4:
                mascota.setAgeace(Main.pedirDatos("Nueva edad: "));
                break;

            case 5:
                mascota.getPerson().setName(
                        Main.pedirDatos("Nuevo nombre del propietario: ")
                );
                break;

            case 6:
                mascota.getPerson().setPhoneNumber(
                        Main.pedirDatos("Nuevo teléfono del propietario: ")
                );
                break;

            case 0:
                Main.mostrarMensaje("Actualización cancelada.");
                return;

            default:
                Main.mostrarMensaje("Opción no válida.");
                return;
        }

        Main.mostrarMensaje("Mascota actualizada correctamente.");
    }

    //Actualizar duenio

    public void actualizarDuenio(){
        String idDuenio = Main.pedirDatos("Ingrese el id del duenio a actualizar");
        int indexDuenio = buscarIndicePersonaPorId(idDuenio);

        if (indexDuenio == -1) {
            Main.mostrarMensaje("El duenio con el ID " + idDuenio + " no existe");
            return;
        }

        Person duenio = listaPersonas[indexDuenio];

        String menu = "¿Qué campo desea actualizar?\n" +
                "1. Nombre\n" +
                "2. Dirección\n" +
                "3. Teléfono\n" +
                "0. Cancelar";

        int opcion = Main.pedirOpcion(menu);

        switch (opcion) {
            case 1:
                duenio.setName(Main.pedirDatos("Nuevo nombre: "));
                break;

            case 2:
                duenio.setAddress(Main.pedirDatos("Nueva dirección: "));
                break;

            case 3:
                duenio.setPhoneNumber(Main.pedirDatos("Nuevo teléfono: "));
                break;

            case 0:
                Main.mostrarMensaje("Actualización cancelada.");
                return;

            default:
                Main.mostrarMensaje("Opción no válida.");
                return;
        }

        Main.mostrarMensaje("Duenio actualizado correctamente.");
    }

    //Eliminar duenio por su id

    public void eliminarDuenio(){
        String idDuenio = Main.pedirDatos("Ingrese el Id del duenio a eliminar");
        int indexDuenio = buscarIndicePersonaPorId(idDuenio);

        if (indexDuenio == -1){
            Main.mostrarMensaje("El duenio con el id: "+idDuenio+" no existe");
            return;
        }

        Person duenio = listaPersonas[indexDuenio];

        boolean tieneMascotas = false;
        for (Pet mascota : duenio.getListPets()) {
            if (mascota != null) {
                tieneMascotas = true;
                break;
            }
        }

        if (tieneMascotas) {
            Main.mostrarMensaje("No se puede eliminar: el duenio con el ID " + idDuenio +
                    " todavía tiene mascotas registradas. Elimine primero sus mascotas.");
            return;
        }

        listaPersonas[indexDuenio] = null;
        Main.mostrarMensaje("El duenio con el ID " + idDuenio + " fue eliminado correctamente.");
    }

    //Visualizar todos los duenios registrados

    public void visualizarDuenios(){
        boolean hayDuenios = false;

        for (int i = 0; i < listaPersonas.length; i++) {
            if (listaPersonas[i] != null){
                Person duenio = listaPersonas[i];
                String nombresMascotas = "";
                Pet[] mascotas = duenio.getListPets();

                for (int j = 0; j < mascotas.length; j++) {
                    Pet mascota = mascotas[j];
                    if (mascota != null) {
                        if (nombresMascotas.length() > 0) {
                            nombresMascotas = nombresMascotas + ", ";
                        }
                        nombresMascotas = nombresMascotas + mascota.getName();
                    }
                }
                if (nombresMascotas.length() == 0) {
                    nombresMascotas = "Sin mascotas registradas";
                }
                String mensaje = "Duenio #" + (i + 1) + "\n" +
                        "ID: " + duenio.getId() + "\n" +
                        "Nombre: " + duenio.getName() + "\n" +
                        "Direccion: " + duenio.getAddress() + "\n" +
                        "Telefono: " + duenio.getPhoneNumber() + "\n" +
                        "Mascota(s): " + nombresMascotas;
                Main.mostrarMensaje(mensaje);
                hayDuenios = true;
            }
        }
        if (!hayDuenios) {
            Main.mostrarMensaje("No hay duenios registrados.");
        }
    }
    // Buscar el indice de la mascota por id
    public int buscarIndiceMascotaPorId(String id){
        for (int i = 0; i < listaMascotas.length; i++) {
            if(listaMascotas[i] != null && listaMascotas[i].getId().equals(id)){
                return i;
            }
        }
        return -1;
    }
    // Buscar el indice de una persona por id
    public int buscarIndicePersonaPorId(String id){
        for (int i = 0; i < listaPersonas.length; i++) {
            if(listaPersonas[i] != null && listaPersonas[i].getId().equals(id)){
                return i;
            }
        }
        return -1;
    }
}