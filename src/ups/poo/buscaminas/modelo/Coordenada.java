package ups.poo.buscaminas.modelo;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ups.poo.buscaminas.excepciones.CoordenadaInvalidaException;

public final class Coordenada {
    // ACEPTA UNA LETRA, ESPACIOS OPCIONALES Y UNO O DOS DÍGITOS, POR EJEMPLO A5 O A 5.
    private static final Pattern FORMATO_COORDENADA = Pattern.compile("([A-Z])\\s*([0-9]{1,2})");

    private final int fila;
    private final int columna;

    public Coordenada(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    /**
     * VALIDA EL FORMATO Y LOS LÍMITES DE UNA COORDENADA ESCRITA POR EL USUARIO.
     * POR EJEMPLO, B5 SE CONVIERTE EN FILA 1 Y COLUMNA 4.
     */
    public static Coordenada desdeTexto(String texto, int filas, int columnas)
            throws CoordenadaInvalidaException {
        Matcher coincidencia = FORMATO_COORDENADA.matcher(texto == null ? "" : texto.trim().toUpperCase(Locale.ROOT));
        if (!coincidencia.matches()) {
            throw new CoordenadaInvalidaException("Escribe una letra de fila y una columna, por ejemplo A5.");
        }

        int fila = coincidencia.group(1).charAt(0) - 'A';
        int columna = Integer.parseInt(coincidencia.group(2)) - 1;

        if (fila < 0 || fila >= filas || columna < 0 || columna >= columnas) {
            throw new CoordenadaInvalidaException("Coordenada fuera del tablero. Filas: A-"
                    + (char) ('A' + filas - 1) + "; columnas: 1-" + columnas + ".");
        }
        return new Coordenada(fila, columna);
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }
}
