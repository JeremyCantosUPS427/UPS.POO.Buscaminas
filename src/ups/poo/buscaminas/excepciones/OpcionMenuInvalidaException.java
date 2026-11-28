package ups.poo.buscaminas.excepciones;

/** SEÑALA QUE EL USUARIO ESCRIBIÓ UN COMANDO QUE NO PERTENECE AL MENÚ DE JUGADAS. */
public class OpcionMenuInvalidaException extends Exception {

    private static final long serialVersionUID = 1L;

    public OpcionMenuInvalidaException() {
        super("Opción de menú no reconocida. Usa A5 para abrir, F A5 para una bandera o 0 para salir.");
    }

    public OpcionMenuInvalidaException(String mensaje) {
        super(mensaje);
    }
}
