import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransaccionServiceTest {

    @Test
    void transferenciaMismoBanco() {

        // ARRANGE: preparar
        CuentaAhorros origen =
                new CuentaAhorros("001", "Ana", 1_000_000);

        CuentaAhorros destino =
                new CuentaAhorros("002", "Luis", 500_000);

        REPOSITORIO repo = new repofalso();
        NOTIFICATION noti = new smsfalso();

        TransaccionService service =
                new TransaccionService(repo, noti);

        // ACT: ejecutar
        service.transferir(
                origen,
                destino,
                100_000,
                "MISMO_BANCO"
        );

        // ASSERT: comprobar
        assertEquals(900_000, origen.getSaldo());
        assertEquals(600_000, destino.getSaldo());
    }
	@Test
void transferenciaOtroBanco() {

    // ARRANGE
    CuentaAhorros origen =
            new CuentaAhorros("001", "Ana", 1_000_000);

    CuentaAhorros destino =
            new CuentaAhorros("002", "Luis", 500_000);

    REPOSITORIO repo = new repofalso();
    NOTIFICATION noti = new smsfalso();

    TransaccionService service =
            new TransaccionService(repo, noti);

    // ACT
    service.transferir(
            origen,
            destino,
            100_000,
            "OTRO_BANCO"
    );

    // ASSERT
    assertEquals(892_500, origen.getSaldo());
    assertEquals(600_000, destino.getSaldo());
}
@Test
void transferenciaSaldoInsuficiente() {

    // ARRANGE
    CuentaAhorros origen =
            new CuentaAhorros("001", "Ana", 50_000);

    CuentaAhorros destino =
            new CuentaAhorros("002", "Luis", 500_000);

    REPOSITORIO repo = new repofalso();
    NOTIFICATION noti = new smsfalso();

    TransaccionService service =
            new TransaccionService(repo, noti);

    // ACT + ASSERT
   assertThrows(
        IllegalStateException.class,
        () -> service.transferir(
                origen,
                destino,
                100_000,
                "MISMO_BANCO"
        )
);
    // El saldo debe permanecer igual
    assertEquals(50_000, origen.getSaldo());
}

@Test
void transferenciaGuardaYNotificaUnaVez() {

    // ARRANGE
    CuentaAhorros origen =
            new CuentaAhorros("001", "Ana", 1_000_000);

    CuentaAhorros destino =
            new CuentaAhorros("002", "Luis", 500_000);

    class RepositorioPrueba implements REPOSITORIO {

        int vecesGuardado = 0;

        @Override
        public void guardarTransaccion(
                String origen,
                String destino,
                double monto,
                double comision) {

            vecesGuardado++;
        }
    }

    class NotificacionPrueba implements NOTIFICATION {

        int vecesEnviado = 0;

        @Override
        public void enviar(String titular, String mensaje) {

            vecesEnviado++;
        }
    }

    RepositorioPrueba repo = new RepositorioPrueba();
    NotificacionPrueba noti = new NotificacionPrueba();

    TransaccionService service =
            new TransaccionService(repo, noti);

    // ACT
    service.transferir(
            origen,
            destino,
            100_000,
            "MISMO_BANCO"
    );

    // ASSERT
    assertEquals(1, repo.vecesGuardado);
    assertEquals(1, noti.vecesEnviado);
}
@Test
void transferenciaTipoDesconocido() {

    // ARRANGE
    CuentaAhorros origen =
            new CuentaAhorros("001", "Ana", 1_000_000);

    CuentaAhorros destino =
            new CuentaAhorros("002", "Luis", 500_000);

    REPOSITORIO repo = new repofalso();
    NOTIFICATION noti = new smsfalso();

    TransaccionService service =
            new TransaccionService(repo, noti);

    // ACT + ASSERT
    assertThrows(
            IllegalArgumentException.class,
            () -> service.transferir(
                    origen,
                    destino,
                    100_000,
                    "TIPO_INEXISTENTE"
            )
    );

    // El saldo de origen no debe cambiar
    assertEquals(1_000_000, origen.getSaldo());
}

}
