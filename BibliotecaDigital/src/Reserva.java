import java.time.LocalDateTime;

public class Reserva implements Comparable<Reserva> {
    private Usuario usuario;
    private RecursoDigital recurso;
    private LocalDateTime fechaReserva;

    public Reserva(Usuario usuario, RecursoDigital recurso, LocalDateTime fechaReserva) {
        this.usuario = usuario;
        this.recurso = recurso;
        this.fechaReserva = fechaReserva;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public RecursoDigital getRecurso() {
        return recurso;
    }

    public LocalDateTime getFechaReserva() {
        return fechaReserva;
    }

    @Override
    public int compareTo(Reserva otra) {
        // Las reservas más antiguas tienen mayor prioridad
        return this.fechaReserva.compareTo(otra.fechaReserva);
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "Usuario = " + usuario.getNombre() + " " + usuario.getApellido() +
                ", Recurso = " + recurso.getTitulo() +
                ", Hora = " + fechaReserva +
                '}';

    }
}
