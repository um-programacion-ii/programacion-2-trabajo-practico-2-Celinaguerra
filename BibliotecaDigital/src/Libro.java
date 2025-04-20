public class Libro implements RecursoDigital, Prestable, Renovable {
    private int id;
    private String titulo;
    private String autor;
    private EstadoRecurso estado; //para que funcione con RecursoDigital
    private CategoriaRecurso categoria;

    public Libro(int id, String titulo, String autor) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.estado = EstadoRecurso.DISPONIBLE;
        this.categoria = CategoriaRecurso.LIBRO;
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

    @Override
    public CategoriaRecurso getCategoria() {
        return categoria;
    }

    public void mostrarInformacion() {
        System.out.println("Libro:");
        System.out.println("ID: " + id);
        System.out.println("Título: " + titulo);
        System.out.println("Autor: " + autor);
        System.out.println("Estado: " + estado);
    }

    //Prestable
    @Override
    public void prestar() {
        if (estado == EstadoRecurso.DISPONIBLE) {
            estado = EstadoRecurso.PRESTADO;
            System.out.println("Libro prestado.");
        } else {
            System.out.println("El libro no está disponible.");
        }
    }

    @Override
    public void devolver() {
        if (estado == EstadoRecurso.PRESTADO) {
            estado = EstadoRecurso.DISPONIBLE;
            System.out.println("Libro devuelto.");
        } else {
            System.out.println("No tiene libro para devolver.");
        }
    }

    @Override
    public boolean estaPrestado() {
        return estado == EstadoRecurso.PRESTADO;
    }

    //Renovable
    @Override
    public void renovar() {
        if (estado == EstadoRecurso.PRESTADO) {
            System.out.println("Libro renovado.");
        } else {
            System.out.println("No se puede renovar un libro que no está prestado.");
        }
    }
}
