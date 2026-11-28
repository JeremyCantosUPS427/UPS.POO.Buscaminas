package ups.poo.buscaminas.excepciones;

/**
 * EXCEPCIÓN VERIFICADA DEL PAQUETE EXCEPCIONES PARA EL JUEGO BUSCAMINAS.
 * SE LANZA CUANDO EL JUGADOR INTENTA DESCUBRIR UNA CASILLA QUE PREVIAMENTE
 * MARCÓ DE FORMA PREVENTIVA PARA EVITAR ACTIVAR UNA MINA POR ACCIDENTE.
 */
public class CasillaMarcadaException extends Exception {

    private static final long serialVersionUID = 1L;

    public CasillaMarcadaException() {
        super("La casilla está marcada. Desmárcala antes de intentar descubrirla.");
    }

    public CasillaMarcadaException(String mensaje) {
        super(mensaje);
    }
}
