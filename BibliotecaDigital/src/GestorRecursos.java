import java.util.HashMap;
import java.util.Map;

public class GestorRecursos {
    private Map<Integer, RecursoDigital> recursos = new HashMap<>();

    public void agregarRecurso(RecursoDigital recurso) {
        recursos.put(recurso.getId(), recurso);
    }

    public void listarRecursos() {
        for (RecursoDigital recurso : recursos.values()) {
            recurso.mostrarInformacion();
        }
    }

    public RecursoDigital obtenerRecurso(int id) {
        return recursos.get(id);
    }

}
