public class Main {
    public static void main(String[] args) {
        ServicioNotificaciones notificador = new ServicioNotificacionesEmail(); //
        Consola consola = new Consola(notificador);
        consola.iniciar();
    }
}