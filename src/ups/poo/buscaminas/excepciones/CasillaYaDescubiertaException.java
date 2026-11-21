package ups.poo.buscaminas.excepciones;

/**
 * Excepción verificada del dominio del juego Buscaminas.
 * Se lanza cuando se intenta descubrir una casilla del tablero que ya fue
 * revelada anteriormente, evitando repetir jugadas inválidas sobre celdas abiertas.
 *
 * @author Ariel Orrala
 * @project UPS.POO.Buscaminas
 */
public class CasillaYaDescubiertaException extends Exception {
    
    // Identificador de versión para mantener coherencia en la serialización
    private static final long serialVersionUID = 1L;

    /**
     * Construye la excepción con el mensaje por defecto de casilla ya revelada.
     */
    public CasillaYaDescubiertaException() {
        super("La casilla seleccionada ya ha sido descubierta.");
    }

    public CasillaYaDescubiertaException(String mensaje) {
        super(mensaje);
    }
}
