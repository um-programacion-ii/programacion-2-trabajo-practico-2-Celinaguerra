package gestores;

import modelo.*;
import excepciones.*;
import alertas.*;
import consola.*;
import notificaciones.*;
import reportes.*;
import utils.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GestorNotificaciones {
    private ServicioNotificaciones servicio;
    private ExecutorService executor;

    public GestorNotificaciones(ServicioNotificaciones servicio) {
        this.servicio = servicio;
        this.executor = Executors.newFixedThreadPool(2);
    }

    public void enviarNotificacion(String destino, String mensaje) {
        executor.submit(() -> servicio.enviarNotificacion(destino, mensaje));
    }

    public void apagar() {
        executor.shutdown();
    }
}