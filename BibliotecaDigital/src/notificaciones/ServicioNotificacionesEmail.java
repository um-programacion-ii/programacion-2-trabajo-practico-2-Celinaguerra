package notificaciones;

public class ServicioNotificacionesEmail implements ServicioNotificaciones {
    @Override
    public void enviarNotificacion(String destino, String mensaje) {
        System.out.println("[EMAIL a " + destino + "] " + mensaje);
    }
}
