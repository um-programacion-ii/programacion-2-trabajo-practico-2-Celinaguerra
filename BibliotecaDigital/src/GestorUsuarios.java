import java.util.Map;
import java.util.HashMap;

public class GestorUsuarios {
    private Map<Integer, Usuario> usuarios = new HashMap<>();
    private ServicioNotificaciones notificador; //

    public GestorUsuarios(ServicioNotificaciones notificador) {
        this.notificador = notificador;
    } //

    public void agregarUsuario(Usuario usuario) {
        usuarios.put(usuario.getId(), usuario);

        String mensaje = "Bienvenido/a " + usuario.getNombre() + " " + usuario.getApellido();//
        notificador.enviarNotificacion(usuario.getEmail(), mensaje); //
    }

    public void listarUsuarios() {
        usuarios.values().forEach(System.out::println);
    }

}
