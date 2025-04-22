import java.util.*;

public class GestorPrestamos {
    private List<Prestamo> prestamosActivos = new ArrayList<>();

    public void realizarPrestamo(Usuario usuario, RecursoDigital recurso) {
        synchronized (this) {
            if (!(recurso instanceof Prestable)) {
                System.out.println("Este recurso no puede ser prestado.");
                return;
            }

            if (recurso.getEstado() != EstadoRecurso.DISPONIBLE) {
                System.out.println("El recurso no está disponible.");
                return;
            }

            Prestamo prestamo = new Prestamo(usuario, recurso);
            prestamosActivos.add(prestamo);
            recurso.actualizarEstado(EstadoRecurso.PRESTADO);

            System.out.println(Thread.currentThread().getName() + " realizó préstamo:");
            System.out.println(prestamo);
        }
    }

    public void devolverPrestamo(RecursoDigital recurso) {
        synchronized (this) {
            Optional<Prestamo> prestamo = prestamosActivos.stream()
                    .filter(p -> p.getRecurso().equals(recurso))
                    .findFirst();

            if (prestamo.isPresent()) {
                prestamosActivos.remove(prestamo.get());
                recurso.actualizarEstado(EstadoRecurso.DISPONIBLE);
                System.out.println(Thread.currentThread().getName() + " devolvió el recurso con éxito.");
            } else {
                System.out.println("No se encontró un préstamo activo para este recurso.");
            }
        }
    }

    public void renovarPrestamo(RecursoDigital recurso) {
        synchronized (this) {
            if (!(recurso instanceof Renovable renovable)) {
                System.out.println("Este recurso no permite renovación.");
                return;
            }

            Optional<Prestamo> prestamo = prestamosActivos.stream()
                    .filter(p -> p.getRecurso().equals(recurso))
                    .findFirst();

            if (prestamo.isPresent()) {
                renovable.renovar();
                System.out.println(Thread.currentThread().getName() + " renovó el recurso.");
            } else {
                System.out.println("No se encontró un préstamo activo para este recurso.");
            }
        }
    }

    public void listarPrestamos() {
        synchronized (this) {
            if (prestamosActivos.isEmpty()) {
                System.out.println("No hay préstamos activos.");
            } else {
                System.out.println("--- Préstamos activos ---");
                prestamosActivos.forEach(System.out::println);
            }
        }
    }
}
