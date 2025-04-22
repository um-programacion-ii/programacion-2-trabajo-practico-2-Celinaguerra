import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class AlertaVencimiento {

    private GestorPrestamos gestorPrestamos;
    private Scanner scanner = new Scanner(System.in);

    public AlertaVencimiento(GestorPrestamos gestorPrestamos) {
        this.gestorPrestamos = gestorPrestamos;
    }

    public void verificarAlertas() {
        List<Prestamo> prestamos = gestorPrestamos.obtenerTodosLosPrestamos();
        LocalDate hoy = LocalDate.now();

        for (Prestamo prestamo : prestamos) {
            LocalDate fechaDevolucion = prestamo.getFechaDevolucion();

            if (fechaDevolucion.equals(hoy.plusDays(1))) {
                mostrarAlerta(prestamo, "Su préstamo vence mañana:");
                permitirRenovacion(prestamo);
            } else if (fechaDevolucion.equals(hoy)) {
                mostrarAlerta(prestamo, "Su préstamo vence hoy:");
                permitirRenovacion(prestamo);
            }
        }
    }

    private void mostrarAlerta(Prestamo prestamo, String mensaje) {
        System.out.println("\n============================");
        System.out.println(mensaje);
        System.out.println("Usuario: " + prestamo.getUsuario().getNombreCompleto());
        System.out.println("Recurso: " + prestamo.getRecurso().getTitulo());
        System.out.println("Fecha de devolución: " + prestamo.getFechaDevolucion());
        System.out.println("============================\n");
    }

    private void permitirRenovacion(Prestamo prestamo) {
        System.out.print("¿Desea renovar este préstamo? (s/n): ");
        String respuesta = scanner.nextLine().trim().toLowerCase();

        if (respuesta.equals("s")) {
            boolean exito = gestorPrestamos.renovarPrestamo(prestamo);
            if (exito) {
                System.out.println("Préstamo renovado.\n");
            } else {
                System.out.println("No fue posible renovar el préstamo.\n");
            }
        }
    }
}
