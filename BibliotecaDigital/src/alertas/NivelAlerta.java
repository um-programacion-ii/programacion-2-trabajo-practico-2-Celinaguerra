package alertas;

import modelo.*;
import excepciones.*;
import gestores.*;
import consola.*;
import notificaciones.*;
import reportes.*;
import utils.*;

public enum NivelAlerta {
    INFO(1), WARNING(2), ERROR(3);

    private int prioridad;
    NivelAlerta(int prioridad) { this.prioridad = prioridad; }
    public int getPrioridad() { return prioridad; }
}
