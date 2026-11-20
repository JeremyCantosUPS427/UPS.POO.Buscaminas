package ups.poo.buscaminas.modelo;

import java.io.Serializable;

public class Jugador implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nombre;
    private int movimientos;
    private int casillasMarcadas;
    private boolean ganador;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.movimientos = 0;
        this.casillasMarcadas = 0;
        this.ganador = false;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getMovimientos() {
        return movimientos;
    }

    public void incrementarMovimientos() {
        this.movimientos++;
    }

    public int getCasillasMarcadas() {
        return casillasMarcadas;
    }

    public void registrarMarca(boolean marcada) {
        if (marcada) {
            this.casillasMarcadas++;
        } else if (this.casillasMarcadas > 0) {
            this.casillasMarcadas--;
        }
    }

    public boolean isGanador() {
        return ganador;
    }

    public void setGanador(boolean ganador) {
        this.ganador = ganador;
    }

    public void reiniciarEstadisticas() {
        this.movimientos = 0;
        this.casillasMarcadas = 0;
        this.ganador = false;
    }
}
