import java.util.Map;
import java.util.HashMap;

public class GestorUsuarios {
    private Map<Integer, Usuario> usuarios = new HashMap<>();

    public void agregarUsuario(Usuario usuario) {
        usuarios.put(usuario.getId(), usuario);
    }

    public void listarUsuarios() {
        usuarios.values().forEach(System.out::println);
    }
}
