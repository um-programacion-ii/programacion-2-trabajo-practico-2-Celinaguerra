package modelo;

import alertas.*;
import excepciones.*;
import gestores.*;
import consola.*;
import notificaciones.*;
import reportes.*;
import utils.*;

public interface RecursoDigital {
    int getId();
    String getTitulo();
    CategoriaRecurso getCategoria();
    EstadoRecurso getEstado();

    void actualizarEstado(EstadoRecurso estado);
    void mostrarInformacion();
}
