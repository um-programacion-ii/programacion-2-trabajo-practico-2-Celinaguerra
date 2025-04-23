package gestores;

import modelo.*;
import excepciones.*;
import alertas.*;
import consola.*;
import notificaciones.*;
import reportes.*;
import utils.*;

import java.util.Map;
import java.util.HashMap;

public class GestorUsuarios {
    private Map<String, Usuario> usuariosPorEmail = new HashMap<>(); //ahora se busca por email, ya que id es int
    private Map<Integer, Usuario> usuariosPorId = new HashMap<>();
    private GestorNotificaciones gestorNotificaciones;

    public GestorUsuarios(ServicioNotificaciones servicio) {
        this.gestorNotificaciones = new GestorNotificaciones(servicio);
    }

    public Usuario buscarUsuarioPorId(int id) throws UsuarioNoEncontradoException {
        Usuario usuario = usuariosPorId.get(id);
        if (usuario == null) {
            throw new UsuarioNoEncontradoException("No se encontró un usuario con ID: " + id);
        }
        return usuario;
    }


    public void agregarUsuario(Usuario usuario) {
        usuariosPorEmail.put(usuario.getEmail(), usuario);
        usuariosPorId.put(usuario.getId(), usuario);
        gestorNotificaciones.enviarNotificacion(usuario.getEmail(), "Bienvenido, " + usuario.getNombre() + "!");
    }

    public void listarUsuarios() {
        usuariosPorId.values().forEach(System.out::println);
    }

    //BUSQUEDA USUARIOS
    public Usuario buscarUsuarioPorEmail(String email) throws UsuarioNoEncontradoException {
        Usuario usuario = usuariosPorEmail.get(email);
        if (usuario == null) {
            throw new UsuarioNoEncontradoException("No se encontró un usuario con el email: " + email);
        }
        return usuario;
    }

    public void apagarNotificaciones() {
        gestorNotificaciones.apagar();
    }


}
