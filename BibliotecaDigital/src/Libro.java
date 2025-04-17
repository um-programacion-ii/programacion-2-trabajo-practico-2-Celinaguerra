public class Libro implements RecursoDigital {
    private int id;
    private String titulo;
    private String autor;
    private EstadoRecurso estado; //para que funcione con RecursoDigital

    public Libro(int id, String titulo, String autor) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
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

    public String getAutor() {
        return autor;
    }

    public void mostrarInformacion() {
        System.out.println("Libro:");
        System.out.println("ID: " + id);
        System.out.println("Título: " + titulo);
        System.out.println("Autor: " + autor);
        System.out.println("Estado: " + estado);
    }
}
