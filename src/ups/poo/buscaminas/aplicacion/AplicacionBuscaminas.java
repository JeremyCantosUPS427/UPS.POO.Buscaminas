package ups.poo.buscaminas.aplicacion;

import java.util.NoSuchElementException;
import ups.poo.buscaminas.controlador.BuscaminasControlador;
import ups.poo.buscaminas.modelo.Jugador;
import ups.poo.buscaminas.modelo.Tablero;
import ups.poo.buscaminas.vista.EntradaConsola;
import ups.poo.buscaminas.vista.VistaConsola;

public final class AplicacionBuscaminas {
    public static void main(String[] args) {
        VistaConsola vista = new VistaConsola();
        Jugador jugador = new Jugador("Jugador");
        vista.mostrarMensaje("BUSCAMINAS 10 x 10");

        try (EntradaConsola entrada = new EntradaConsola()) {
            ejecutarPartidas(entrada, vista, jugador);
        } catch (NoSuchElementException excepcion) {
            // CERRAR LA ENTRADA NO CUENTA COMO UNA DERROTA.
            vista.mostrarMensaje("\nEntrada finalizada.");
        }

        vista.mostrarEstadisticas(jugador);
        vista.mostrarMensaje("Gracias por jugar.");
    }

    private static void ejecutarPartidas(EntradaConsola entrada, VistaConsola vista, Jugador jugador) {
        do {
            jugador.reiniciarPartida();
            BuscaminasControlador controlador = new BuscaminasControlador(new Tablero(), jugador, vista);
            if (!controlador.jugar(entrada)) {
                return;
            }
        } while (entrada.deseaJugarOtraPartida());
    }
}
