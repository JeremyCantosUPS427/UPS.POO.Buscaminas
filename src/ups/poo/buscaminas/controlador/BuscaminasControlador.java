package ups.poo.buscaminas.controlador;

import ups.poo.buscaminas.excepciones.CasillaMarcadaException;
import ups.poo.buscaminas.excepciones.CasillaYaDescubiertaException;
import ups.poo.buscaminas.excepciones.CoordenadaInvalidaException;
import ups.poo.buscaminas.excepciones.OpcionMenuInvalidaException;
import ups.poo.buscaminas.modelo.Coordenada;
import ups.poo.buscaminas.modelo.EstadoPartida;
import ups.poo.buscaminas.modelo.Jugador;
import ups.poo.buscaminas.modelo.Tablero;
import ups.poo.buscaminas.vista.EntradaConsola;
import ups.poo.buscaminas.vista.VistaConsola;

public final class BuscaminasControlador {
    private final Tablero tablero;
    private final Jugador jugador;
    private final VistaConsola vista;

    public BuscaminasControlador(Tablero tablero, Jugador jugador, VistaConsola vista) {
        this.tablero = tablero;
        this.jugador = jugador;
        this.vista = vista;
    }

    /** DEVUELVE FALSE AL SALIR CON 0 Y TRUE AL TERMINAR CON VICTORIA O DERROTA. */
    public boolean jugar(EntradaConsola entrada) {
        vista.mostrarInstrucciones();
        vista.mostrarPartida(tablero, jugador);
        while (tablero.getEstado() == EstadoPartida.EN_CURSO) {
            if (!procesarSiguienteJugada(entrada)) {
                return false;
            }
        }
        return true;
    }

    public void descubrirCasilla(int fila, int columna)
            throws CoordenadaInvalidaException, CasillaYaDescubiertaException, CasillaMarcadaException {
        tablero.descubrirCasilla(fila, columna);
        completarMovimientoValido();
    }

    public void alternarBandera(int fila, int columna)
            throws CoordenadaInvalidaException, CasillaYaDescubiertaException {
        boolean banderaColocada = tablero.alternarBandera(fila, columna);
        jugador.actualizarBanderas(banderaColocada);
        completarMovimientoValido();
    }

    private void completarMovimientoValido() {
        // SOLO SE LLAMA DESPUÉS DE QUE EL MODELO ACEPTE LA ACCIÓN.
        jugador.incrementarMovimientos();
        jugador.registrarResultado(tablero.getEstado());
        vista.mostrarPartida(tablero, jugador);
        vista.mostrarResultado(tablero.getEstado());
    }

    private boolean procesarSiguienteJugada(EntradaConsola entrada) {
        try {
            return ejecutarJugada(entrada.leerJugada());
        } catch (OpcionMenuInvalidaException | CoordenadaInvalidaException | CasillaYaDescubiertaException
                 | CasillaMarcadaException | IllegalStateException excepcion) {
            vista.mostrarMensaje(excepcion.getMessage());
            return true;
        }
    }

    private boolean ejecutarJugada(String jugada)
            throws CoordenadaInvalidaException, CasillaYaDescubiertaException, CasillaMarcadaException {
        if (jugada.equals("0")) {
            vista.mostrarMensaje("Partida abandonada.");
            return false;
        }
        // LA FILA F SE DISTINGUE DEL PREFIJO DE BANDERA: F7 ABRE; F F7 MARCA.
        boolean colocarBandera = jugada.matches("F +[A-Z].*");
        String textoCoordenada = colocarBandera ? jugada.substring(2).trim() : jugada;
        Coordenada coordenada = Coordenada.desdeTexto(textoCoordenada, tablero.getFilas(), tablero.getColumnas());
        if (colocarBandera) {
            alternarBandera(coordenada.getFila(), coordenada.getColumna());
        } else {
            descubrirCasilla(coordenada.getFila(), coordenada.getColumna());
        }
        return true;
    }
}
