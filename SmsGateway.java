public class SmsGateway implements NOTIFICATION{
    public void enviar(String destinatario, String mensaje) {
        System.out.println("[SMS] Conectando al proveedor de mensajería...");
        System.out.println("[SMS] Para " + destinatario + ": " + mensaje);
    }
}

interface NOTIFICATION{
    public void enviar(String destinatario, String mensaje);
}
