import java.time.LocalDate;
import java.util.List;

public class AlertaVencimiento {

    private GestorPrestamos gestorPrestamos;

    public AlertaVencimiento(GestorPrestamos gestorPrestamos) {
        this.gestorPrestamos = gestorPrestamos;
    }

    public void verificarAlertas() {
        List<Prestamo> prestamos = gestorPrestamos.obtenerTodosLosPrestamos();

        for (Prestamo prestamo : prestamos) {
            LocalDate fechaDevolucion = prestamo.getFechaDevolucion();
            LocalDate hoy = LocalDate.now();

            if (fechaDevolucion.equals(hoy.plusDays(1))) {
                mostrarAlerta(prestamo, "Su préstamo vence mañana:");
            } else if (fechaDevolucion.equals(hoy)) {
                mostrarAlerta(prestamo, "Su préstamo vence hoy:");
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
}
