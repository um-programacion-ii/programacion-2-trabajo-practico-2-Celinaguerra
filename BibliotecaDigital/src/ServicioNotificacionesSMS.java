public class ServicioNotificacionesSMS implements ServicioNotificaciones {
    @Override
    public void notificar(String mensaje) {
        System.out.println("[SMS] " + mensaje);
    }
}
