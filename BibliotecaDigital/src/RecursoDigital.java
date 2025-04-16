public interface RecursoDigital {
    int getId();
    EstadoRecurso getEstado();
    void actualizarEstado(EstadoRecurso estado);
}