public class ServicioNotificacionesEmail implements ServicioNotificaciones {
    @Override
    public void notificar(String mensaje) {
        System.out.println("[EMAIL] " + mensaje);
    }
}
