import java.util.Map;
import java.util.HashMap;

public class PreferenciasNotificaciones {
    private static Map<String, NivelAlerta> preferencias = new HashMap<>();

    public static void establecerPreferencia(String nombreUsuario, NivelAlerta nivel) {
        preferencias.put(nombreUsuario, nivel);
    }

    public static boolean debeNotificar(String nombreUsuario, NivelAlerta nivelActual) {
        NivelAlerta preferencia = preferencias.getOrDefault(nombreUsuario, NivelAlerta.INFO);
        return nivelActual.getPrioridad() >= preferencia.getPrioridad();
    }
}
