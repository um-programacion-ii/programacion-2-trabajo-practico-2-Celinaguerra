import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Comparator;

public class GestorRecursos {
    private List<RecursoDigital> recursos = new ArrayList<>();

    public static Comparator<RecursoDigital> POR_TITULO = Comparator.comparing(r -> r.getTitulo());
    public static Comparator<RecursoDigital> POR_ID = Comparator.comparingInt(RecursoDigital::getId);
    public static Comparator<RecursoDigital> POR_CATEGORIA = Comparator.comparing(r -> r.getCategoria());


    public void agregarRecurso(RecursoDigital recurso) {
        recursos.add(recurso);
    }

    public void listarRecursos() {
        for (RecursoDigital recurso : recursos) {
            recurso.mostrarInformacion();
        }
    }

    public RecursoDigital obtenerRecurso(int id) {
        for (RecursoDigital recurso : recursos) {
            if (recurso.getId() == id) {
                return recurso;
            }
        }
        return null;
    }

    //BUSQUEDA DE RECURSOS

    public RecursoDigital buscarRecursoPorId(int id) {
        for (RecursoDigital recurso : recursos) {
            if (recurso.getId() == id) {
                return recurso;
            }
        }
        return null;
    }

    public List<RecursoDigital> buscarPorTitulo(String titulo) {
        return recursos.stream()
                .filter(r -> r.getTitulo().equalsIgnoreCase(titulo))
                .collect(Collectors.toList());
    }

    //FILTRADO

    public List<RecursoDigital> filtrarPorCategoria(String categoria) {
        return recursos.stream()
                .filter(r -> r.getCategoria().equalsIgnoreCase(categoria))
                .collect(Collectors.toList());
    }

    public List<RecursoDigital> obtenerOrdenados(Comparator<RecursoDigital> comparador) {
        return recursos.stream()
                .sorted(comparador)
                .collect(Collectors.toList());
    }

    public List<RecursoDigital> obtenerTodos() {
        return new ArrayList<>(recursos);
    }


}