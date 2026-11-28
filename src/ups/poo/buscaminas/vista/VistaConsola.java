package ups.poo.buscaminas.vista;

import ups.poo.buscaminas.modelo.Casilla;
import ups.poo.buscaminas.modelo.EstadoPartida;
import ups.poo.buscaminas.modelo.Jugador;
import ups.poo.buscaminas.modelo.Tablero;

/** PRESENTA EL JUEGO EN CONSOLA; CONSULTA EL MODELO SIN CAMBIAR SUS CASILLAS NI CONTADORES. */
public final class VistaConsola {
    /** EXPLICA LOS COMANDOS Y LA LEYENDA AL COMENZAR CADA PARTIDA. */
    public void mostrarInstrucciones() {
        mostrarMensaje("\nA5: abrir | F A5: bandera | 0: salir");
        mostrarMensaje("# oculta   . vacía   F bandera   * mina");
    }

    /** ACTUALIZA JUNTOS EL TABLERO Y LOS CONTADORES DESPUÉS DE UNA ACCIÓN VÁLIDA. */
    public void mostrarPartida(Tablero tablero, Jugador jugador) {
        mostrarTablero(tablero);
        System.out.printf("Minas: %d | Jugadas: %d | Derrotas: %d%n",
                tablero.getTotalMinas(), jugador.getMovimientos(), jugador.getDerrotas());
    }

    /** ORGANIZA EL DIBUJO DE ENCABEZADO, FILAS Y BORDES CON EL MISMO ANCHO DE COLUMNA. */
    public void mostrarTablero(Tablero tablero) {
        imprimirEncabezado(tablero.getColumnas());
        imprimirBorde(tablero.getColumnas());
        for (int fila = 0; fila < tablero.getFilas(); fila++) {
            imprimirFila(tablero, fila);
        }
        imprimirBorde(tablero.getColumnas());
    }

    /** ALINEA LOS NÚMEROS DE COLUMNA CON LAS CASILLAS, RESERVANDO ESPACIO PARA LAS LETRAS DE FILA. */
    private void imprimirEncabezado(int columnas) {
        System.out.print("\n    ");
        for (int columna = 1; columna <= columnas; columna++) {
            System.out.printf("%2d ", columna);
        }
        System.out.println();
    }

    /** REUTILIZA EL MISMO BORDE PARA LA PARTE SUPERIOR E INFERIOR DEL TABLERO. */
    private void imprimirBorde(int columnas) {
        System.out.println("   +" + "---".repeat(columnas) + "+");
    }

    /** CONVIERTE EL ÍNDICE DE FILA EN UNA LETRA Y DIBUJA SUS CASILLAS CON ANCHO FIJO. */
    private void imprimirFila(Tablero tablero, int fila) {
        System.out.printf(" %c |", (char) ('A' + fila));
        for (int columna = 0; columna < tablero.getColumnas(); columna++) {
            char simbolo = obtenerSimbolo(tablero.getCasilla(fila, columna), tablero.getEstado());
            System.out.printf("%2c ", simbolo);
        }
        System.out.println("|");
    }

    /** MANTIENE LAS MINAS OCULTAS DURANTE EL JUEGO Y LAS REVELA AL GANAR O PERDER. */
    private char obtenerSimbolo(Casilla casilla, EstadoPartida estado) {
        // REVELAR UNA MINA EN PANTALLA NO DEBE MODIFICAR EL TABLERO.
        if (estado != EstadoPartida.EN_CURSO && casilla.tieneMina()) {
            return casilla.getSimboloDescubierto();
        }
        return casilla.getSimboloVisible('#', 'F');
    }

    /** SOLO PRESENTA UN RESULTADO SI LA PARTIDA ESTÁ GANADA O PERDIDA. */
    public void mostrarResultado(EstadoPartida estado) {
        if (estado == EstadoPartida.PERDIDA) {
            mostrarMensaje("¡Perdiste! Encontraste una mina.");
        } else if (estado == EstadoPartida.GANADA) {
            mostrarMensaje("¡Ganaste!");
        }
    }

    /** PRESENTA LOS RESULTADOS ACUMULADOS DE TODAS LAS PARTIDAS DE LA EJECUCIÓN. */
    public void mostrarEstadisticas(Jugador jugador) {
        System.out.printf("%nVictorias: %d | Derrotas: %d%n",
                jugador.getVictorias(), jugador.getDerrotas());
    }

    /** CENTRALIZA LOS MENSAJES DE TEXTO QUE ENVÍAN LA APLICACIÓN Y EL CONTROLADOR. */
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}
