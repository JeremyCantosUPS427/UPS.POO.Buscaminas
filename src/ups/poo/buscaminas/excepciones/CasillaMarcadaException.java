package ups.poo.buscaminas.excepciones;

/**
 * Excepción verificada del paquete {@code excepciones} para el juego Buscaminas.
 * Se lanza cuando el jugador intenta descubrir una casilla que previamente
 * marcó de forma preventiva para evitar activar una mina por accidente.
 *
 * @author Ariel Orrala
 * @project UPS.POO.Buscaminas
 */
public class CasillaMarcadaException extends Exception {

      // Identificador de versión para compatibilidad en procesos de serialización
    private static final long serialVersionUID = 1L;

      /**
     * Crea una nueva excepción con un mensaje predeterminado indicando que
     * la casilla está marcada y debe desmarcarse antes de revelarse.
     */
    public CasillaMarcadaException() {
        super("La casilla está marcada. Desmárcala antes de intentar descubrirla.");
    }

    public CasillaMarcadaException(String mensaje) {
        super(mensaje);
    }
}
