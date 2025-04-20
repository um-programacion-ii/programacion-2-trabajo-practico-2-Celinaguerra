public class Revista implements RecursoDigital, Prestable {
    private int id;
    private String titulo;
    private int numeroEdicion;
    private EstadoRecurso estado;
    private CategoriaRecurso categoria;

    public Revista(int id, String titulo, int numeroEdicion) {
        this.id = id;
        this.titulo = titulo;
        this.numeroEdicion = numeroEdicion;
        this.estado = EstadoRecurso.DISPONIBLE;
        this.categoria = CategoriaRecurso.REVISTA;
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

    @Override
    public CategoriaRecurso getCategoria() {
        return categoria;
    }

    public void mostrarInformacion() {
        System.out.println("Revista:");
        System.out.println("ID: " + id);
        System.out.println("Título: " + titulo);
        System.out.println("Edición N°: " + numeroEdicion);
        System.out.println("Estado: " + estado);
    }

    //PRESTABLE
    @Override
    public void prestar() {
        if (estado == EstadoRecurso.DISPONIBLE) {
            estado = EstadoRecurso.PRESTADO;
            System.out.println("Revista prestada.");
        } else {
            System.out.println("La revista no está disponible.");
        }
    }

    @Override
    public boolean estaPrestado() {
        return estado == EstadoRecurso.PRESTADO;
    }

    @Override
    public void devolver() {
        if (estado == EstadoRecurso.PRESTADO) {
            estado = EstadoRecurso.DISPONIBLE;
            System.out.println("Revista devuelta.");
        } else {
            System.out.println("La revista no estaba prestada.");
        }
    }

}
