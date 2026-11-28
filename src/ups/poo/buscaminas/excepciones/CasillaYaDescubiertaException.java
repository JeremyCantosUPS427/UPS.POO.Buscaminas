package ups.poo.buscaminas.excepciones;

/**
 * EXCEPCIÓN VERIFICADA DEL DOMINIO DEL JUEGO BUSCAMINAS.
 * SE LANZA CUANDO SE INTENTA DESCUBRIR UNA CASILLA DEL TABLERO QUE YA FUE
 * REVELADA ANTERIORMENTE, EVITANDO REPETIR JUGADAS INVÁLIDAS SOBRE CELDAS ABIERTAS.
 */
public class CasillaYaDescubiertaException extends Exception {

    private static final long serialVersionUID = 1L;

    public CasillaYaDescubiertaException() {
        super("La casilla seleccionada ya ha sido descubierta.");
    }

    public CasillaYaDescubiertaException(String mensaje) {
        super(mensaje);
    }
}
