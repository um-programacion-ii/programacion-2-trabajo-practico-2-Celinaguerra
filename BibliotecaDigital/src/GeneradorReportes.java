import java.util.Scanner;

public class GeneradorReportes implements Runnable {
    private GestorPrestamos gestorPrestamos;

    public GeneradorReportes(GestorPrestamos gestorPrestamos) {
        this.gestorPrestamos = gestorPrestamos;
    }

    @Override
    public void run() {
        System.out.println("\n[INFO] Generando reporte de préstamos...");

        try {
            for (int i = 0; i <= 10; i++) {
                Thread.sleep(300);
                System.out.print("\rProgreso: " + (i * 10) + "%");
            }
            System.out.println("\n--- Reporte de Préstamos ---");

            gestorPrestamos.listarPrestamos();

        } catch (InterruptedException e) {
            System.out.println("\n[ERROR] Reporte interrumpido.");
            Thread.currentThread().interrupt();
        }
    }
}
