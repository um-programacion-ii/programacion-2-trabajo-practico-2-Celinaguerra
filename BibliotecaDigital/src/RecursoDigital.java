public abstract class RecursoDigital {
    private int id;
    private String titulo;

    public RecursoDigital(String id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }
}
