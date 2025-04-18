import java.util.Scanner;

public class Consola {
    private Scanner scanner = new Scanner(System.in);
    private GestorUsuarios gestorUsuarios = new GestorUsuarios();
    private GestorRecursos gestorRecursos = new GestorRecursos();

    // MENU
    public void iniciar() {
        int opcion = -1;

        while (opcion != 0) {
            System.out.println("\nMENÚ");
            System.out.println("1. Agregar usuario");
            System.out.println("2. Listar usuarios");
            System.out.println("3. Agregar recurso");
            System.out.println("4. Listar recursos");
            System.out.println("5. Prestar/Devolver/Renovar recurso");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1 -> agregarUsuario();
                case 2 -> listarUsuarios();
                case 3 -> agregarRecurso();
                case 4 -> listarRecursos();
                case 5 -> operarConRecurso();

                case 0 -> System.out.println("Saliendo");
                default -> System.out.println("Opción inválida.");
            }
        }
    }


    // PARA USUARIOS
    private void agregarUsuario() {
        System.out.print("ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        Usuario usuario = new Usuario(id, nombre, apellido, email);
        gestorUsuarios.agregarUsuario(usuario);
        System.out.println("Usuario agregado.");
    }

    private void listarUsuarios() {
        System.out.println("--- Lista de Usuarios ---");
        gestorUsuarios.listarUsuarios();
    }

    //PARA RECURSOS
    private void agregarRecurso() {
        System.out.println("Tipos disponibles: libro, revista, audiolibro");
        System.out.print("Tipo de recurso: ");
        String tipo = scanner.nextLine().toLowerCase();

        System.out.print("ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Título: ");
        String titulo = scanner.nextLine();

        RecursoDigital recurso = null;

        switch (tipo) {
            case "libro" -> {
                System.out.print("Autor: ");
                String autor = scanner.nextLine();
                recurso = new Libro(id, titulo, autor);
            }
            case "revista" -> {
                System.out.print("Número de edición: ");
                int edicion = Integer.parseInt(scanner.nextLine());
                recurso = new Revista(id, titulo, edicion);
            }
            case "audiolibro" -> {
                System.out.print("Narrador: ");
                String narrador = scanner.nextLine();
                System.out.print("Duración (minutos): ");
                int duracion = Integer.parseInt(scanner.nextLine());
                recurso = new Audiolibro(id, titulo, narrador, duracion);
            }
            default -> {
                System.out.println("Recurso inexistente.");
                return;
            }
        }

        gestorRecursos.agregarRecurso(recurso);
        System.out.println("Recurso agregado correctamente.");
    }

    private void listarRecursos() {
        System.out.println("--- Lista de Recursos ---");
        gestorRecursos.listarRecursos();
    }

    // Submenú de recursos
    private void operarConRecurso() {
        System.out.print("Ingrese ID del recurso: ");
        int id = Integer.parseInt(scanner.nextLine());

        RecursoDigital recurso = gestorRecursos.obtenerRecurso(id);

        if (recurso == null) {
            System.out.println("No se encontró el recurso.");
            return;
        }

        recurso.mostrarInformacion();
        System.out.println("\nOperaciones disponibles:");

        if (recurso instanceof Prestable) {
            System.out.println("1. Prestar");
            System.out.println("2. Devolver");
        }

        if (recurso instanceof Renovable) {
            System.out.println("3. Renovar");
        }

        System.out.println("0. Volver");
        System.out.print("Seleccione una opción: ");
        int opcion = Integer.parseInt(scanner.nextLine());

        if (recurso instanceof Prestable prestable) {
            switch (opcion) {
                case 1 -> prestable.prestar();
                case 2 -> prestable.devolver();
            }
        }

        if (recurso instanceof Renovable renovable) {
            if (opcion == 3) {
                renovable.renovar();
            }
        }

        System.out.println("Operación completada.");
    }

}
