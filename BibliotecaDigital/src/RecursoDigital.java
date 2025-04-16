public abstract class RecursoDigital {
    private int id;
    private String titulo;

    public RecursoDigital(int id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }
}
