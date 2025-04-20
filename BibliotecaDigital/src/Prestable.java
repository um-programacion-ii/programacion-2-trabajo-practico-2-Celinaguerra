public interface Prestable {
    void prestar() throws RecursoNoDisponibleException;
    void devolver();
    boolean estaPrestado();
}
