public enum NivelAlerta {
    INFO(1), WARNING(2), ERROR(3);

    private int prioridad;
    NivelAlerta(int prioridad) { this.prioridad = prioridad; }
    public int getPrioridad() { return prioridad; }
}
