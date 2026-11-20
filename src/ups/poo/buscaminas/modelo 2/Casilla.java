package ups.poo.buscaminas.modelo;

import java.io.Serializable;

public abstract class Casilla implements Serializable {
    private static final long serialVersionUID = 1L;

    private final int fila;
    private final int columna;
    private boolean descubierta;
    private boolean marcada;

    public Casilla(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.descubierta = false;
        this.marcada = false;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public boolean isDescubierta() {
        return descubierta;
    }

    public void setDescubierta(boolean descubierta) {
        this.descubierta = descubierta;
    }

    public boolean isMarcada() {
        return marcada;
    }

    public void setMarcada(boolean marcada) {
        this.marcada = marcada;
    }

    public void alternarMarca() {
        if (!descubierta) {
            this.marcada = !this.marcada;
        }
    }

    public abstract boolean tieneMina();

    public abstract char getSimboloDescubierto();

    public char getSimboloVisible(char simboloCubierta, char simboloMarcada) {
        if (!descubierta) {
            return marcada ? simboloMarcada : simboloCubierta;
        }
        return getSimboloDescubierto();
    }
}
