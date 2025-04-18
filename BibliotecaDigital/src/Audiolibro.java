public class Audiolibro implements RecursoDigital {
    private int id;
    private String titulo;
    private String narrador;
    private int duracionEnMinutos;
    private EstadoRecurso estado;

    public Audiolibro(int id, String titulo, String narrador, int duracionEnMinutos) {
        this.id = id;
        this.titulo = titulo;
        this.narrador = narrador;
        this.duracionEnMinutos = duracionEnMinutos;
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

    public String getNarrador() {
        return narrador;
    }

    public int getDuracionEnMinutos() {
        return duracionEnMinutos;
    }

    public void mostrarInformacion() {
        System.out.println("Audiolibro:");
        System.out.println("ID: " + id);
        System.out.println("Título: " + titulo);
        System.out.println("Narrador: " + narrador);
        System.out.println("Duración: " + duracionEnMinutos + " minutos");
        System.out.println("Estado: " + estado);
    }

    //No lo considero prestable ni renovable
}
