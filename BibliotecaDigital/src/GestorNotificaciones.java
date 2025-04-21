import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GestorNotificaciones {
    private final List<ServicioNotificaciones> servicios;
    private final ExecutorService executor = Executors.newFixedThreadPool(2);

    public GestorNotificaciones(List<ServicioNotificaciones> servicios) {
        this.servicios = servicios;
    }

    public void enviar(String destino, String mensaje) {
        for (ServicioNotificaciones servicio : servicios) {
            executor.submit(() -> servicio.enviarNotificacion(destino, mensaje));
        }
    }

    public void cerrar() {
        executor.shutdown();
    }
}
