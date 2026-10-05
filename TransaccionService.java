import java.time.LocalDateTime;

public class TransaccionService {
    private final OracleRepositorio repositorio = new OracleRepositorio();
    private final SmsGateway sms = new SmsGateway();

    public void transferir(Cuenta origen, Cuenta destino, double monto, String tipo) {
        // 1. Validación
        if (monto <= 0) throw new IllegalArgumentException("Monto inválido");
        if (monto > 5_000_000) throw new IllegalArgumentException("Supera el tope diario");

        // 2. Cálculo de la comisión principio SOLID O
        COMISION com;
        switch (tipo) {
            case "MISMO_BANCO" -> com = new MISMO_BANCO();
            case "OTRO_BANCO" -> com = new OTRO_BANCO();
            case "INTERNACIONAL" -> com  = new INTERNACIONAL();
            default -> throw new IllegalArgumentException("Tipo de transferencia desconocido");
        }
        double comision = com.comision(monto);

        // 3. Movimiento del dinero
        origen.retirar(monto + comision);
        destino.depositar(monto);

        // 4. Persistencia
        repositorio.guardarTransaccion(origen.getNumero(), destino.getNumero(), monto, comision);

        // 5. Comprobante
        COMPROBANTE comprobante = new COMPROBANTE();
        comprobante.comprobante(origen, destino, monto, tipo, comision);

        // 6. Notificación
        sms.enviar(origen.getTitular(), "Transferiste $" + monto + " a la cuenta " +
            destino.getNumero());

        // 7. Auditoría
        comprobante.auditoria(origen, destino, monto, tipo);
    }
}

class COMPROBANTE {
    public void comprobante(Cuenta origen, Cuenta destino, double monto, String tipo, double comision) {
        System.out.println("===== BANCO ANDINO - COMPROBANTE =====");
        System.out.println("Origen: " + origen.getNumero());
        System.out.println("Destino: " + destino.getNumero());
        System.out.println("Monto: $" + monto);
        System.out.println("Comisión: $" + comision);
        System.out.println("======================================");
    }
    public void auditoria(Cuenta origen, Cuenta destino, double monto, String tipo){
        System.out.println("[AUDITORIA] " + LocalDateTime.now() + " " + tipo
            + " " + origen.getNumero() + " -> " + destino.getNumero() + " $" + monto);
    }
}

interface COMISION {
    double comision(double monto);
}

class MISMO_BANCO implements COMISION{
    public double comision(double monto){
        return 0;
    }
}
class OTRO_BANCO implements COMISION{
    public double comision(double monto){
        return 7_500;
    }
}
class INTERNACIONAL implements  COMISION{
    public double comision(double monto){
        return (monto * 0.03 + 25_000);
}
}
