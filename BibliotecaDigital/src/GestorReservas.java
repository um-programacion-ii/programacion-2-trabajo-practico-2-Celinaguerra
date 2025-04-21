import java.util.concurrent.PriorityBlockingQueue;

public class GestorReservas {
    private PriorityBlockingQueue<Reserva> colaReservas = new PriorityBlockingQueue<>();

    public void agregarReserva(Reserva reserva) {
        colaReservas.offer(reserva);
        System.out.println("Reserva agregada: " + reserva);
    }

    public Reserva obtenerSiguienteReserva() {
        return colaReservas.poll();
    }

    public void mostrarReservasPendientes() {
        if (colaReservas.isEmpty()) {
            System.out.println("No hay reservas pendientes.");
        } else {
            System.out.println("--- Reservas pendientes ---");
            for (Reserva r : colaReservas) {
                System.out.println(r);
            }
        }
    }
}
