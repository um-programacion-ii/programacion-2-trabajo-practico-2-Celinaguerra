package consola;

import alertas.*;
import excepciones.*;
import gestores.*;
import modelo.*;
import notificaciones.*;
import reportes.*;
import utils.*;

import java.util.Scanner;
import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutionException;


public class Consola {
    private Scanner scanner = new Scanner(System.in);
    private GestorUsuarios gestorUsuarios;
    private GestorRecursos gestorRecursos = new GestorRecursos();
    private GestorPrestamos gestorPrestamos = new GestorPrestamos();
    private GestorReservas gestorReservas = new GestorReservas();
    private AlertaVencimiento alertaVencimiento;
    private AlertaDisponibilidad alertaDisponibilidad;
    private ExecutorService executor = Executors.newCachedThreadPool();



    public Consola(ServicioNotificaciones notificador) { //
        this.gestorUsuarios = new GestorUsuarios(notificador);
        this.alertaVencimiento = new AlertaVencimiento(gestorPrestamos, scanner);
        this.alertaDisponibilidad = new AlertaDisponibilidad(gestorReservas, notificador);
    }

    // MENU
    public void iniciar() {
        int opcion = -1;

        while (opcion != 0) {
            System.out.println("\nMENÚ");
            System.out.println("1. Agregar usuario");
            System.out.println("2. Listar usuarios");
            System.out.println("3. Buscar usuario por email");
            System.out.println("4. Agregar recurso");
            System.out.println("5. Listar recursos");
            System.out.println("6. Buscar recurso por título");
            System.out.println("7. Buscar recurso por categoría");
            System.out.println("8. Prestar/Devolver/Renovar recurso");
            System.out.println("9. Gestionar Reservas");
            System.out.println("10. Generar reporte de préstamos");
            System.out.println("11. Reportes/Estadísticas");
            System.out.println("12. Ver historial de alertas");
            System.out.println("13. Configurar Notificaciones");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            alertaVencimiento.verificarAlertas();

            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1 -> agregarUsuario();
                case 2 -> listarUsuarios();
                case 3 -> buscarUsuarioPorEmail();
                case 4 -> agregarRecurso();
                case 5 -> listarRecursos();
                case 6 -> buscarRecursoPorTitulo();
                case 7 -> buscarPorCategoria();
                case 8 -> operarConRecurso();
                case 9 -> operarConReservas();
                case 10 -> {
                    System.out.println("[INFO] Generando reporte de préstamos en segundo plano...");
                    Future<?> futuro = executor.submit(new GeneradorReportes(gestorPrestamos));

                    try {
                        futuro.get(); // Espera a que termine la tarea antes de volver al menú
                    } catch (InterruptedException | ExecutionException e) {
                        System.out.println("[ERROR] No se pudo generar el reporte: " + e.getMessage());
                    }
                }

                case 11 -> mostrarEstadisticas();
                case 12 -> AlertaHistorial.mostrarHistorial();
                case 13 -> {
                    System.out.print("Ingrese el email del usuario para configurar las preferencias de notificación: ");
                    String emailUsuario = scanner.nextLine();

                    try {
                        Usuario usuario = gestorUsuarios.buscarUsuarioPorEmail(emailUsuario);
                        configurarPreferenciasNotificacion(usuario);
                    } catch (UsuarioNoEncontradoException e) {
                        System.out.println("Usuario no encontrado.");
                    }
                }


                case 0 -> {
                    System.out.println("Saliendo");
                    gestorUsuarios.apagarNotificaciones();
                }
                default -> System.out.println("Opción inválida.");
            }
        }
    }


    // PARA USUARIOS
    private void agregarUsuario() {
        System.out.print("ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        Usuario usuario = new Usuario(id, nombre, apellido, email);
        gestorUsuarios.agregarUsuario(usuario);
        System.out.println("Usuario agregado.");
    }

    private void listarUsuarios() {
        System.out.println("--- Lista de Usuarios ---");
        gestorUsuarios.listarUsuarios();
    }

    private void buscarUsuarioPorEmail() {
        System.out.print("Ingrese el email del usuario: ");
        String email = scanner.nextLine();

        try {
            Usuario usuario = gestorUsuarios.buscarUsuarioPorEmail(email);
            System.out.println("--- Usuario encontrado ---");
            System.out.println(usuario);
        } catch (UsuarioNoEncontradoException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    //PARA RECURSOS
    private void agregarRecurso() {
        System.out.println("Tipos disponibles: libro, revista, audiolibro");
        System.out.print("Tipo de recurso: ");
        String tipo = scanner.nextLine().toLowerCase();

        System.out.print("ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Título: ");
        String titulo = scanner.nextLine();

        RecursoDigital recurso = null;

        switch (tipo) {
            case "libro" -> {
                System.out.print("Autor: ");
                String autor = scanner.nextLine();
                recurso = new Libro(id, titulo, autor);
            }
            case "revista" -> {
                System.out.print("Número de edición: ");
                int edicion = Integer.parseInt(scanner.nextLine());
                recurso = new Revista(id, titulo, edicion);
            }
            case "audiolibro" -> {
                System.out.print("Narrador: ");
                String narrador = scanner.nextLine();
                System.out.print("Duración (minutos): ");
                int duracion = Integer.parseInt(scanner.nextLine());
                recurso = new Audiolibro(id, titulo, narrador, duracion);
            }
            default -> {
                System.out.println("Recurso inexistente.");
                return;
            }
        }

        gestorRecursos.agregarRecurso(recurso);
        System.out.println("Recurso agregado correctamente.");
    }

    private void listarRecursos() {
        System.out.println("--- Lista de Recursos ---");
        List<RecursoDigital> recursos = gestorRecursos.obtenerTodos();
        recursos = ordenarRecursos(recursos);
        recursos.forEach(RecursoDigital::mostrarInformacion);
    }


    private void buscarRecursoPorTitulo() {
        System.out.print("Ingrese el título del recurso: ");
        String titulo = scanner.nextLine();

        List<RecursoDigital> encontrados = gestorRecursos.buscarPorTitulo(titulo);

        if (encontrados.isEmpty()) {
            System.out.println("No se encontraron recursos con ese título.");
        } else {
            encontrados = ordenarRecursos(encontrados);
            System.out.println("--- Recursos encontrados ---");
            encontrados.forEach(RecursoDigital::mostrarInformacion);
        }
    }



    private void buscarPorCategoria() {
        System.out.print("Ingrese la categoría (Libro, Revista, Audiolibro): ");
        String categoria = scanner.nextLine();

        List<RecursoDigital> filtrados = gestorRecursos.filtrarPorCategoria(categoria);

        if (filtrados.isEmpty()) {
            System.out.println("No se encontraron recursos en esa categoría.");
        } else {
            filtrados = ordenarRecursos(filtrados);
            System.out.println("--- Recursos encontrados ---");
            filtrados.forEach(RecursoDigital::mostrarInformacion);
        }
    }


    private List<RecursoDigital> ordenarRecursos(List<RecursoDigital> recursos) {
        System.out.println("\n¿Desea ordenar los recursos?");
        System.out.println("1. Por título");
        System.out.println("2. Por ID");
        System.out.println("3. Por categoría");
        System.out.println("0. No ordenar");
        System.out.print("Seleccione una opción: ");
        int opcionOrden = Integer.parseInt(scanner.nextLine());

        return switch (opcionOrden) {
            case 1 -> recursos.stream()
                    .sorted(GestorRecursos.POR_TITULO)
                    .collect(Collectors.toList());
            case 2 -> recursos.stream()
                    .sorted(GestorRecursos.POR_ID)
                    .collect(Collectors.toList());
            case 3 -> recursos.stream()
                    .sorted(GestorRecursos.POR_CATEGORIA)
                    .collect(Collectors.toList());
            default -> recursos;
        };
    }

    //para prestamos
    private void ofrecerPrestamoInmediato(Usuario usuario, RecursoDigital recurso) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("¿Deseás realizar el préstamo de \"" + recurso.getTitulo() + "\" ahora? (si/no)");
        String respuesta = scanner.nextLine().trim().toLowerCase();

        if (respuesta.equals("si")) {
            gestorPrestamos.realizarPrestamo(usuario, recurso);
        } else {
            System.out.println("El préstamo no fue realizado.");
        }
    }

    //submenu configuracion
    private void configurarPreferenciasNotificacion(Usuario usuario) {
        System.out.println("--- Configurar Preferencias de Notificación ---");
        System.out.println("Seleccione el nivel mínimo de alertas que desea recibir:");
        System.out.println("1. INFO");
        System.out.println("2. WARNING");
        System.out.println("3. ERROR");
        System.out.print("Opción: ");

        int opcion = scanner.nextInt();
        scanner.nextLine();

        NivelAlerta nivel = switch (opcion) {
            case 1 -> NivelAlerta.INFO;
            case 2 -> NivelAlerta.WARNING;
            case 3 -> NivelAlerta.ERROR;
            default -> {
                System.out.println("Opción no válida. Se usará INFO por defecto.");
                yield NivelAlerta.INFO;
            }
        };

        PreferenciasNotificaciones.establecerPreferencia(usuario.getNombre(), nivel);
        System.out.println("Preferencia actualizada: " + nivel);
    }

    // Submenú de prestamos
    private void operarConRecurso() {
        gestorRecursos.mostrarRecursosDisponibles();

        System.out.print("Ingrese ID del usuario: ");
        int idUsuario = Integer.parseInt(scanner.nextLine());

        System.out.print("Ingrese ID del recurso: ");
        int idRecurso = Integer.parseInt(scanner.nextLine());

        try {
            Usuario usuario = gestorUsuarios.buscarUsuarioPorId(idUsuario);
            RecursoDigital recurso = gestorRecursos.obtenerRecurso(idRecurso);

            System.out.println("\nOperaciones disponibles:");
            System.out.println("1. Prestar recurso");
            System.out.println("2. Devolver recurso");
            System.out.println("3. Renovar recurso");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            int opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1 -> gestorPrestamos.realizarPrestamo(usuario, recurso);
                case 2 -> {
                    gestorPrestamos.devolverPrestamo(recurso);

                    alertaDisponibilidad.notificarSiHayReserva(recurso);

                    gestorReservas.mostrarReservasPendientes();

                    Reserva siguienteReserva = gestorReservas.obtenerSiguienteReserva(recurso);
                    if (siguienteReserva != null && siguienteReserva.getRecurso().equals(recurso)) {
                        Usuario usuarioReservante = siguienteReserva.getUsuario();
                        ofrecerPrestamoInmediato(usuarioReservante, recurso);
                    }
                }
                case 3 -> gestorPrestamos.renovarPrestamo(recurso);
                case 0 -> System.out.println("Volviendo al menú.");
                default -> System.out.println("Opción no válida.");
            }

        } catch (RecursoNoDisponibleException | UsuarioNoEncontradoException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }


    //submenu de reservas
    private void operarConReservas() {
        int opcion = -1;

        while (opcion != 0) {
            System.out.println("\n--- MENÚ DE RESERVAS ---");
            System.out.println("1. Agregar reserva");
            System.out.println("2. Listar reservas pendientes");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1 -> agregarReserva();
                case 2 -> gestorReservas.mostrarReservasPendientes();
                case 0 -> System.out.println("-----------------------");
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    private void agregarReserva() {
        try {
            System.out.print("Ingrese ID del usuario: ");
            int idUsuario = Integer.parseInt(scanner.nextLine());
            Usuario usuario = gestorUsuarios.buscarUsuarioPorId(idUsuario);

            System.out.print("Ingrese ID del recurso: ");
            int idRecurso = Integer.parseInt(scanner.nextLine());
            RecursoDigital recurso = gestorRecursos.obtenerRecurso(idRecurso);

            Reserva reserva = new Reserva(usuario, recurso, java.time.LocalDateTime.now());
            gestorReservas.agregarReserva(reserva);

        } catch (UsuarioNoEncontradoException e) {
            System.out.println("[ERROR] Usuario no encontrado.");
        } catch (Exception e) {
            System.out.println("[ERROR] No se pudo crear la reserva: " + e.getMessage());
        }
    }

    //submenú estadísticas y reportes
    private void mostrarEstadisticas() {
        int opcion = -1;

        while (opcion != 0) {
            System.out.println("\n--- MENÚ DE REPORTES / ESTADÍSTICAS ---");
            System.out.println("1. Mostrar recursos más prestados");
            System.out.println("2. Mostrar usuarios más activos");
            System.out.println("3. Mostrar estadísticas por categoría");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1 -> gestorPrestamos.mostrarRecursosMasPrestados();
                case 2 -> gestorPrestamos.mostrarUsuariosMasActivos();
                case 3 -> gestorPrestamos.mostrarEstadisticasPorCategoria();
                case 0 -> System.out.println("Volviendo al menú principal.");
                default -> System.out.println("Opción no válida.");
            }
        }
    }
}
