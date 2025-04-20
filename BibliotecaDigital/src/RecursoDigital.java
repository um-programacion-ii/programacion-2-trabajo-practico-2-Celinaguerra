public interface RecursoDigital {
    int getId();
    String getTitulo();
    CategoriaRecurso getCategoria();
    EstadoRecurso getEstado();

    void actualizarEstado(EstadoRecurso estado);
    void mostrarInformacion();
}
