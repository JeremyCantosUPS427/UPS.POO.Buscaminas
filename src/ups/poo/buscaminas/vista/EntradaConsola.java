package ups.poo.buscaminas.vista;

import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Pattern;
import ups.poo.buscaminas.excepciones.OpcionMenuInvalidaException;

/** GESTIONA LA ENTRADA DE TECLADO Y VALIDA LOS COMANDOS ANTES DE ENVIARLOS AL CONTROLADOR. */
public final class EntradaConsola implements AutoCloseable {
    // EL PATRÓN SE COMPILA UNA SOLA VEZ Y SE REUTILIZA EN CADA TURNO.
    private static final Pattern FORMATO_JUGADA = Pattern.compile("0|(?:F +)?[A-Z]\\s*[0-9]{1,2}");
    private final Scanner scanner;

    /** CONECTA EL LECTOR AL TECLADO; SE CIERRA AL FINALIZAR LA APLICACIÓN. */
    public EntradaConsola() {
        this(new Scanner(System.in));
    }

    /** PERMITE USAR UN LECTOR SIMULADO EN LAS PRUEBAS SIN DEPENDER DEL TECLADO. */
    public EntradaConsola(Scanner scanner) {
        this.scanner = scanner;
    }

    /** ACEPTA 0, A5 O F A5; RECHAZA COMANDOS DESCONOCIDOS MEDIANTE LA EXCEPCIÓN DE MENÚ. */
    public String leerJugada() throws OpcionMenuInvalidaException {
        String jugada = leerLinea("Jugada: ").toUpperCase(Locale.ROOT);
        // EL FORMATO SE VALIDA AQUÍ; LOS LÍMITES DEL TABLERO SE VALIDAN EN COORDENADA.
        if (!FORMATO_JUGADA.matcher(jugada).matches()) {
            throw new OpcionMenuInvalidaException();
        }
        return jugada;
    }

    /** REPITE LA PREGUNTA HASTA RECIBIR S O N; ENTER TAMBIÉN TERMINA LA SESIÓN. */
    public boolean deseaJugarOtraPartida() {
        while (true) {
            String respuesta = leerLinea("¿Otra partida? (s/n): ");
            if (respuesta.isEmpty() || respuesta.equalsIgnoreCase("n")) {
                return false;
            }
            if (respuesta.equalsIgnoreCase("s")) {
                return true;
            }
            System.out.println("Escribe s o n.");
        }
    }

    /** ELIMINA ESPACIOS EXTERIORES Y SEÑALA EL FIN DE ENTRADA PARA QUE LA APLICACIÓN CIERRE ORDENADAMENTE. */
    private String leerLinea(String mensaje) {
        System.out.print(mensaje);
        if (!scanner.hasNextLine()) {
            throw new NoSuchElementException("Entrada cerrada.");
        }
        return scanner.nextLine().trim();
    }

    /** LIBERA EL SCANNER CUANDO TERMINA EL BLOQUE TRY-WITH-RESOURCES. */
    @Override
    public void close() {
        scanner.close();
    }
}
