package alertas;

import modelo.*;
import excepciones.*;
import gestores.*;
import consola.*;
import notificaciones.*;
import reportes.*;
import utils.*;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Recordatorio implements Runnable {
    private final GestorPrestamos gestorPrestamos;

    public Recordatorio(GestorPrestamos gestorPrestamos) {
        this.gestorPrestamos = gestorPrestamos;
    }

    @Override
    public void run() {
        List<Prestamo> prestamos = gestorPrestamos.obtenerTodosLosPrestamos();
        LocalDate hoy = LocalDate.now();

        for (Prestamo prestamo : prestamos) {
            long diasRestantes = ChronoUnit.DAYS.between(hoy, prestamo.getFechaDevolucion());

            if (diasRestantes <= 0) {
                AlertaHistorial.registrar("ERROR", "¡Préstamo vencido para: " + prestamo.getRecurso().getTitulo() + "!");
            } else if (diasRestantes <= 2) {
                AlertaHistorial.registrar("WARNING", "El recurso \"" + prestamo.getRecurso().getTitulo() + "\" vence pronto.");
            } else if (diasRestantes <= 5) {
                AlertaHistorial.registrar("INFO", "Recordatorio: el recurso \"" + prestamo.getRecurso().getTitulo() + "\" vence en " + diasRestantes + " días.");
            }
        }
    }
}
