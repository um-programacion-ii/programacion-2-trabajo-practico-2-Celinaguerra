import java.util.*;

public class GestorPrestamos {
    private List<Prestamo> prestamosActivos = new ArrayList<>();

    public void realizarPrestamo(Usuario usuario, RecursoDigital recurso) {
        if (recurso.getEstado() != EstadoRecurso.DISPONIBLE) {
            System.out.println("El recurso no está disponible.");
            return;
        }

        Prestamo prestamo = new Prestamo(usuario, recurso);
        prestamosActivos.add(prestamo);
        recurso.setEstado(EstadoRecurso.PRESTADO);

        System.out.println("Préstamo realizado con éxito:");
        System.out.println(prestamo);
    }

    public void devolverPrestamo(RecursoDigital recurso) {
        Optional<Prestamo> prestamo = prestamosActivos.stream()
                .filter(p -> p.getRecurso().equals(recurso))
                .findFirst();

        if (prestamo.isPresent()) {
            prestamosActivos.remove(prestamo.get());
            recurso.setEstado(EstadoRecurso.DISPONIBLE);
            System.out.println("Recurso devuelto con éxito.");
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
