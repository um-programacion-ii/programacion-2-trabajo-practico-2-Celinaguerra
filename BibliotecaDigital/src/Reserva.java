public class Reserva {
    private Usuario usuario;
    private RecursoDigital recurso;
    private long timestamp;

    public Reserva(Usuario usuario, RecursoDigital recurso) {
        this.usuario = usuario;
        this.recurso = recurso;
        this.timestamp = System.currentTimeMillis();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public RecursoDigital getRecurso() {
        return recurso;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "usuario=" + usuario.getNombre() + " " + usuario.getApellido() +
                ", recurso=" + recurso.getTitulo() +
                ", timestamp=" + timestamp +
                '}';
    }
}
