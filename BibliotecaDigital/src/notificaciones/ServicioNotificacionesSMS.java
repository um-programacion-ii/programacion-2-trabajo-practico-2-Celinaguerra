package notificaciones;

public class ServicioNotificacionesSMS implements ServicioNotificaciones {
    @Override
    public void enviarNotificacion(String destino, String mensaje) {
        System.out.println("[SMS a " + destino + "] " + mensaje);
    }
}
