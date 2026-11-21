package ups.poo.buscaminas.excepciones;

/**
 * Excepción verificada para validar coordenadas en el tablero de Buscaminas.
 * Se lanza cuando la entrada del usuario no respeta el formato esperado
 * (por ejemplo, letra de fila y columna como "A5") o queda fuera del rango válido.
 *
 * @author Ariel Orrala
 * @project UPS.POO.Buscaminas
 */
public class CoordenadaInvalidaException extends Exception {

    // Identificador de versión para control de serialización
    private static final long serialVersionUID = 1L;

    /**
     * Inicializa la excepción con un mensaje estándar de formato o rango inválido.
     */
    public CoordenadaInvalidaException() {
        super("Coordenada inválida. Ingrese una fila y columna válidas (ejemplo: A5).");
    }

    public CoordenadaInvalidaException(String mensaje) {
        super(mensaje);
    }
}
