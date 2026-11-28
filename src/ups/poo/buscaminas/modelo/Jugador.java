package ups.poo.buscaminas.modelo;

import java.io.Serializable;

public final class Jugador implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nombre;
    private int movimientos;
    private int banderasColocadas;
    private EstadoPartida resultadoPartida;
    private int victorias;
    private int derrotas;

    public Jugador(String nombre) {
        setNombre(nombre);
        reiniciarPartida();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El jugador debe tener un nombre.");
        }
        this.nombre = nombre.trim();
    }

    public int getMovimientos() {
        return movimientos;
    }

    public void incrementarMovimientos() {
        movimientos++;
    }

    public int getBanderasColocadas() {
        return banderasColocadas;
    }

    public void actualizarBanderas(boolean banderaColocada) {
        if (banderaColocada) {
            banderasColocadas++;
        } else if (banderasColocadas > 0) {
            banderasColocadas--;
        }
    }

    public boolean isGanador() {
        return resultadoPartida == EstadoPartida.GANADA;
    }

    public boolean isDerrotado() {
        return resultadoPartida == EstadoPartida.PERDIDA;
    }

    public int getVictorias() {
        return victorias;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public void registrarResultado(EstadoPartida resultado) {
        // UNA PARTIDA FINALIZADA SOLO PUEDE SUMAR UN RESULTADO.
        if (resultadoPartida != EstadoPartida.EN_CURSO || resultado == EstadoPartida.EN_CURSO) {
            return;
        }
        resultadoPartida = resultado;
        if (resultado == EstadoPartida.GANADA) {
            victorias++;
        } else {
            derrotas++;
        }
    }

    public void reiniciarPartida() {
        movimientos = 0;
        banderasColocadas = 0;
        // LAS VICTORIAS Y DERROTAS DE LA SESIÓN SE CONSERVAN.
        resultadoPartida = EstadoPartida.EN_CURSO;
    }
}
