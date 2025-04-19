public interface RecursoDigital {
    int getId();
    String getTitulo();
    String getCategoria();
    EstadoRecurso getEstado();
    void actualizarEstado(EstadoRecurso estado);
    void mostrarInformacion();
}
