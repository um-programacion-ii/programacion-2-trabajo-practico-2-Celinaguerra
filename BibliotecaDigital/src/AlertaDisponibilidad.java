public class AlertaDisponibilidad {
    private final GestorReservas gestorReservas;
    private final ServicioNotificaciones servicioNotificaciones;

    public AlertaDisponibilidad(GestorReservas gestorReservas, ServicioNotificaciones servicioNotificaciones) {
        this.gestorReservas = gestorReservas;
        this.servicioNotificaciones = servicioNotificaciones;
    }

    public void notificarSiHayReserva(RecursoDigital recurso) {
        Reserva siguiente = gestorReservas.obtenerSiguienteReserva(recurso);
        if (siguiente != null && siguiente.getRecurso().equals(recurso)) {
            Usuario usuario = siguiente.getUsuario();
            String mensaje = "El recurso \"" + recurso.getTitulo() + "\" que reservaste está disponible.";
            String usuarionom = usuario.getNombre();

            servicioNotificaciones.enviarNotificacion(usuarionom, mensaje);
        }
    }
}
