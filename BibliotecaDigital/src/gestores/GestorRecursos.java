package gestores;

import modelo.*;
import excepciones.*;
import alertas.*;
import consola.*;
import notificaciones.*;
import reportes.*;
import utils.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Comparator;

public class GestorRecursos {
    private List<RecursoDigital> recursos = new ArrayList<>();

    public static Comparator<RecursoDigital> POR_TITULO = Comparator.comparing(r -> r.getTitulo());
    public static Comparator<RecursoDigital> POR_ID = Comparator.comparingInt(RecursoDigital::getId);
    public static Comparator<RecursoDigital> POR_CATEGORIA = Comparator.comparing(r -> r.getCategoria());


    public void agregarRecurso(RecursoDigital recurso) {
        recursos.add(recurso);
    }

    public void listarRecursos() {
        for (RecursoDigital recurso : recursos) {
            recurso.mostrarInformacion();
        }
    }

    public RecursoDigital obtenerRecurso(int id) throws RecursoNoDisponibleException {
        for (RecursoDigital recurso : recursos) {
            if (recurso.getId() == id) {
                return recurso;
            }
        }
        throw new RecursoNoDisponibleException("Recurso con ID " + id + " no encontrado.");
    }


    //BUSQUEDA DE RECURSOS

    public RecursoDigital buscarRecursoPorId(int id) {
        for (RecursoDigital recurso : recursos) {
            if (recurso.getId() == id) {
                return recurso;
            }
        }
        return null;
    }

    public List<RecursoDigital> buscarPorTitulo(String titulo) {
        return recursos.stream()
                .filter(r -> r.getTitulo().equalsIgnoreCase(titulo))
                .collect(Collectors.toList());
    }

    public void mostrarRecursosDisponibles() {
        List<RecursoDigital> disponibles = recursos.stream()
                .filter(r -> r.getEstado() == EstadoRecurso.DISPONIBLE)
                .collect(Collectors.toList());

        if (disponibles.isEmpty()) {
            System.out.println("No hay recursos disponibles.");
        } else {
            System.out.println("--- Recursos disponibles ---");
            for (RecursoDigital recurso : disponibles) {
                recurso.mostrarInformacion();
            }
        }
    }


    //FILTRADO

    public List<RecursoDigital> filtrarPorCategoria(String categoria) {
        CategoriaRecurso categoriaEnum = CategoriaRecurso.valueOf(categoria.toUpperCase());
        return recursos.stream()
                .filter(r -> r.getCategoria() == categoriaEnum)
                .collect(Collectors.toList());
    }

    public List<RecursoDigital> obtenerOrdenados(Comparator<RecursoDigital> comparador) {
        return recursos.stream()
                .sorted(comparador)
                .collect(Collectors.toList());
    }

    public List<RecursoDigital> obtenerTodos() {
        return new ArrayList<>(recursos);
    }




}