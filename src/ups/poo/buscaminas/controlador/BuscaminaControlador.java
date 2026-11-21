package ups.poo.buscaminas.controlador;

import ups.poo.buscaminas.modelo.Tablero;
import ups.poo.buscaminas.modelo.Jugador;
import ups.poo.buscaminas.vista.MostrarTablero;
import ups.poo.buscaminas.modelo.Coordenada;
import ups.poo.buscaminas.vista.IngresarCoordenadas;

public class BuscaminaControlador {

    //ATRIBUTOS
    private Tablero tablero;
    private Jugador jugador;
    private MostrarTablero vista;

    //CONSTRUCTOR
    public BuscaminaControlador(Tablero tablero, Jugador jugador, MostrarTablero vista){
        this.jugador = jugador;
        this.tablero = tablero;
        this.vista = vista;
    }

    // ACTUALIZAR TABLERO
    public void actualizarTablero() {
        vista.MostrarTablero(tablero);
    }

    // METODO VALIDAR CASILLA
    public void SeleccionarCasilla(int fila, int columna) {

        if (!tablero.esCoordenadaValida(fila, columna)) {
            return;
        }

        if (tablero.getCasilla(fila, columna).isDescubierta()
                || tablero.getCasilla(fila, columna).isMarcada()) {
            return;
        }

        boolean mina = tablero.descubrirCasilla(fila, columna);

        jugador.incrementarMovimientos();

        if (!mina && tablero.todasLasCasillasSegurasDescubiertas()) {
            jugador.setGanador(true);
        }

        actualizarTablero();
    }

    //METODO RECIBIR COORDENADAS
    public void ingresarMovimiento(IngresarCoordenadas entrada) {

        // OBTENER DATOS DE LA VISTA
        int fila = entrada.ingresarFila();
        int columna = entrada.ingresarColumna();

        // ALMACENAR DATOS EN EL MODELO
        Coordenada coordenada = new Coordenada(fila, columna);

        // SELECCIONAR CASILLA
        SeleccionarCasilla(
                coordenada.getFila(),
                coordenada.getColumna()
        );
    }

}
