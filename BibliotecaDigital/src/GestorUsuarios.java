import java.util.HashMap;
import java.util.Map;

public class GestorUsuarios {
    private Map<Integer, Usuario> usuarios = new HashMap<>();

    public void agregarUsuario(Usuario usuario) {
        usuarios.put(usuario.getId(), usuario);
    }

    public Usuario buscarUsuarioPorId(int id) {
        return usuarios.get(id);
    }

    public void eliminarUsuario(int id) {
        usuarios.remove(id);
    }

    public void listarUsuarios() {
        usuarios.values().forEach(System.out::println);
    }
}
