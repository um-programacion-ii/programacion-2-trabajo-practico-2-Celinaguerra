public class AlertaHistorial {
    private static final List<String> historial = new ArrayList<>();

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
