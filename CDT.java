import java.time.LocalDate;

public class CDT{
    private final LocalDate vencimiento;
    protected final String numero;
    protected final String titular;
    protected double saldo;

    public CDT(String numero, String titular, double monto, LocalDate vencimiento) {
        this.numero = numero;
        this.titular = titular;
        this.saldo = monto;
        this.vencimiento = vencimiento;
    }

    public void retirar(double monto) {
        if (LocalDate.now().isBefore(vencimiento)) {
            throw new UnsupportedOperationException(
                "Un CDT no permite retiros antes del vencimiento");
        }

        if (monto > saldo) throw new IllegalStateException("Saldo insuficiente");
        saldo -= monto;
    }
}
