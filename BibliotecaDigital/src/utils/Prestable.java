package utils;

import modelo.*;
import excepciones.*;
import alertas.*;
import consola.*;
import notificaciones.*;
import reportes.*;
import utils.*;

public interface Prestable {
    void prestar() throws RecursoNoDisponibleException;
    void devolver();
    boolean estaPrestado();
}
