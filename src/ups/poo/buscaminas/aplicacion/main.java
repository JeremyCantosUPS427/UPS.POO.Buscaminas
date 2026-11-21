package ups.poo.buscaminas.aplicacion;

import ups.poo.buscaminas.controlador.BuscaminaControlador;
import ups.poo.buscaminas.modelo.Coordenada;
import ups.poo.buscaminas.modelo.Jugador;
import ups.poo.buscaminas.modelo.Tablero;
import ups.poo.buscaminas.vista.IngresarCoordenadas;
import ups.poo.buscaminas.vista.MostrarTablero;

public class main {
    public static void main(String[] args) {

        //CREAR JUGADOR
        Jugador jugador = new Jugador("Administrador");

        //CREAR TABLERO
        Tablero tablero = new Tablero();

        //CREAR VISTA
        MostrarTablero vista = new MostrarTablero();

        //CREAR COORDENADAS
        IngresarCoordenadas IngresarCoordenadas = new IngresarCoordenadas();

        //CREAR CONTROLADOR
        BuscaminaControlador BC = new BuscaminaControlador(tablero, jugador, vista);

        //MOSTRAR TABLERO INICIAL
        BC.actualizarTablero();

        //INGRESAR COORDENADAS
        BC.ingresarMovimiento(IngresarCoordenadas);

//        //DISCUBRIR UNA CASILLA
//        tablero.descubrirCasilla(0,0);
//        System.out.println("\n TABLERO ACTUALIZADO:" );
//
    }
}
