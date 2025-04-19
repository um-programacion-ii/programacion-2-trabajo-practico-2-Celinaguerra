import java.util.Map;
import java.util.HashMap;

public class GestorUsuarios {
    private Map<String, Usuario> usuarios = new HashMap<>(); //ahora se busca por email, ya que id es int
    private ServicioNotificaciones notificador;

    public GestorUsuarios(ServicioNotificaciones notificador) {
        this.notificador = notificador;
    } //

    public void agregarUsuario(Usuario usuario) {
        usuarios.put(usuario.getEmail(), usuario);
        notificador.enviarNotificacion("Bienvenido, " + usuario.getNombre() + "!", usuario.getEmail());
    }

    public void listarUsuarios() {
        usuarios.values().forEach(System.out::println);
    }

    //BUSQUEDA USUARIOS
    public Usuario buscarUsuarioPorEmail(String email) {
        return usuarios.get(email);
    }

}
