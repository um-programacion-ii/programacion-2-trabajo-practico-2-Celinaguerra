import java.util.*;

public class GestorPrestamos {
    private List<Prestamo> prestamosActivos = new ArrayList<>();

    public void realizarPrestamo(Usuario usuario, RecursoDigital recurso) {
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

        System.out.println("Préstamo realizado:");
        System.out.println(prestamo);
    }

    public void devolverPrestamo(RecursoDigital recurso) {
        Optional<Prestamo> prestamo = prestamosActivos.stream()
                .filter(p -> p.getRecurso().equals(recurso))
                .findFirst();

        if (prestamo.isPresent()) {
            prestamosActivos.remove(prestamo.get());
            recurso.actualizarEstado(EstadoRecurso.DISPONIBLE);
            System.out.println("Recurso devuelto con éxito.");
        } else {
            System.out.println("No se encontró un préstamo activo para este recurso.");
        }
    }

    public void renovarPrestamo(RecursoDigital recurso) {
        if (!(recurso instanceof Renovable renovable)) {
            System.out.println("Este recurso no permite renovación.");
            return;
        }

        Optional<Prestamo> prestamo = prestamosActivos.stream()
                .filter(p -> p.getRecurso().equals(recurso))
                .findFirst();

        if (prestamo.isPresent()) {
            renovable.renovar(); // Lógica de renovación propia del recurso
            System.out.println("Recurso renovado.");
        } else {
            System.out.println("No se encontró un préstamo activo para este recurso.");
        }
    }

    public void listarPrestamos() {
        if (prestamosActivos.isEmpty()) {
            System.out.println("No hay préstamos activos.");
        } else {
            System.out.println("--- Préstamos activos ---");
            prestamosActivos.forEach(System.out::println);
        }
    }
}
