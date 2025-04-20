import java.time.LocalDate;

public class Prestamo {
    private Usuario usuario;
    private RecursoDigital recurso;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;

    public Prestamo(Usuario usuario, RecursoDigital recurso) {
        this.usuario = usuario;
        this.recurso = recurso;
        this.fechaPrestamo = LocalDate.now();
        this.fechaDevolucion = fechaPrestamo.plusDays(14);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public RecursoDigital getRecurso() {
        return recurso;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    @Override
    public String toString() {
        return "Préstamo de " + recurso.getTitulo() + " a " + usuario.getNombre() + " (" + usuario.getEmail() + ")" +
                " - Fecha: " + fechaPrestamo + ", Devolución: " + fechaDevolucion;
    }
}