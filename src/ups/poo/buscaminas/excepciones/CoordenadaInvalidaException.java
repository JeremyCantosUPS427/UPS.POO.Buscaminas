package ups.poo.buscaminas.excepciones;

/**
 * EXCEPCIÓN VERIFICADA PARA VALIDAR COORDENADAS EN EL TABLERO DE BUSCAMINAS.
 * SE LANZA CUANDO LA ENTRADA DEL USUARIO NO RESPETA EL FORMATO ESPERADO
 * (POR EJEMPLO, LETRA DE FILA Y COLUMNA COMO "A5") O QUEDA FUERA DEL RANGO VÁLIDO.
 */
public class CoordenadaInvalidaException extends Exception {

    private static final long serialVersionUID = 1L;

    public CoordenadaInvalidaException() {
        super("Coordenada inválida. Ingrese una fila y columna válidas (ejemplo: A5).");
    }

    public CoordenadaInvalidaException(String mensaje) {
        super(mensaje);
    }
}
