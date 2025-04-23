package alertas;

import modelo.*;
import excepciones.*;
import gestores.*;
import consola.*;
import notificaciones.*;
import reportes.*;
import utils.*;
import java.util.List;
import java.util.ArrayList;

public class AlertaHistorial {
    private static List<String> historial = new ArrayList<>();

    public static void registrar(String nivel, String mensaje) {
        String alerta = "[" + nivel + "] " + mensaje;
        historial.add(alerta);
        System.out.println(alerta);
    }

    public static void mostrarHistorial() {
        System.out.println("--- Historial de Alertas ---");
        historial.forEach(System.out::println);
    }
}
