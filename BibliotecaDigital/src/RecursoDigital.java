public interface RecursoDigital {
    int getId();
    String getTitulo();
    EstadoRecurso getEstado();
    void actualizarEstado(EstadoRecurso estado);
    void mostrarInformacion();
}
