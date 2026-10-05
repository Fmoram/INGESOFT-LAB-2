public interface ProductoBancario {
    double calcularIntereses();
    void pagarCuota(double monto);
    String generarExtracto();
}

// se separan actividades que no se usan en todos los productos que implementan PRODUCTOBANCARIO

interface Depositar {
    void depositar(double monto);
}

interface Retirar {
    void retirar(double monto);
}
