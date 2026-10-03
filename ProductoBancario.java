public interface ProductoBancario {
    double calcularIntereses();
    void pagarCuota(double monto);
    String generarExtracto();
}

interface Depositar {
    void depositar(double monto);
}

interface Retirar {
    void retirar(double monto);
}