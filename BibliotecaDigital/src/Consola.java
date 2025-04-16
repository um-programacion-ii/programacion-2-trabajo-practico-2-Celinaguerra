import java.util.Scanner;

public class Consola {
    private final Scanner scanner = new Scanner(System.in);
    private final GestorUsuarios gestorUsuarios = new GestorUsuarios();

    public void iniciar() {
        int opcion = -1;

        while (opcion != 0) {
            System.out.println("\nMENÚ");
            System.out.println("1. Agregar usuario");
            System.out.println("2. Listar usuarios");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1 -> agregarUsuario();
                case 2 -> listarUsuarios();
                case 0 -> System.out.println("Saliendo");
                default -> System.out.println("Opción inválida.");
            }
        }
    }

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
}
