import java.util.*;

public class GestorPrestamos {
    private List<Prestamo> prestamosActivos = new ArrayList<>();
    private Map<RecursoDigital, Integer> contadorPrestamos = new HashMap<>();
    private Map<Usuario, Integer> contadorPrestamosPorUsuario = new HashMap<>();
    private Map<CategoriaRecurso, Integer> contadorPorCategoria = new HashMap<>();


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

            CategoriaRecurso categoria = recurso.getCategoria();
            contadorPorCategoria.put(categoria, contadorPorCategoria.getOrDefault(categoria, 0) + 1);

            //contadores para los reportes
            contadorPrestamos.merge(recurso, 1, Integer::sum);
            contadorPrestamosPorUsuario.merge(usuario, 1, Integer::sum);


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


    // REPORTES
    public synchronized void mostrarRecursosMasPrestados() {
        System.out.println("--- Recursos más prestados ---");
        contadorPrestamos.entrySet().stream()
                .filter(entry -> entry.getValue() > 0)
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .forEach(entry ->
                        System.out.println(entry.getKey().getTitulo() + " - " + entry.getValue() + " préstamos")
                );
    }

    public synchronized void mostrarUsuariosMasActivos() {
        System.out.println("--- Usuarios más activos ---");

        contadorPrestamosPorUsuario.entrySet().stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .forEach(entry ->
                        System.out.println(entry.getKey().getNombre() + " " + entry.getKey().getApellido() +
                                " - Préstamos: " + entry.getValue())
                );
    }

    public synchronized void mostrarEstadisticasPorCategoria() {
        System.out.println("--- Estadísticas de uso por categoría ---");
        contadorPorCategoria.entrySet().stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue() + " préstamos"));
    }


}
