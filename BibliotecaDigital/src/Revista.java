public class Revista implements RecursoDigital {
    private int id;
    private String titulo;
    private int numeroEdicion;
    private EstadoRecurso estado;

    public Revista(int id, String titulo, int numeroEdicion) {
        this.id = id;
        this.titulo = titulo;
        this.numeroEdicion = numeroEdicion;
        this.estado = EstadoRecurso.DISPONIBLE;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public EstadoRecurso getEstado() {
        return estado;
    }

    @Override
    public void actualizarEstado(EstadoRecurso estado) {
        this.estado = estado;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getNumeroEdicion() {
        return numeroEdicion;
    }

    public void mostrarInformacion() {
        System.out.println("Revista:");
        System.out.println("ID: " + id);
        System.out.println("Título: " + titulo);
        System.out.println("Edición N°: " + numeroEdicion);
        System.out.println("Estado: " + estado);
    }
}
