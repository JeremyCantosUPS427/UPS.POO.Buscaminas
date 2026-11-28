package ups.poo.buscaminas.modelo;

import java.io.Serializable;

/** DEFINE LA POSICIÓN Y EL ESTADO COMÚN DE LAS CASILLAS; LAS SUBCLASES DECIDEN SI CONTIENEN UNA MINA. */
public abstract class Casilla implements Serializable {

    private static final long serialVersionUID = 1L;

    // LA POSICIÓN NO CAMBIA; LOS ESTADOS SOLO SE MODIFICAN DESDE EL PAQUETE DEL MODELO.
    private final int fila;
    private final int columna;
    private boolean descubierta;
    private boolean marcada;

    public Casilla(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
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

    /** ABRE LA CASILLA; TABLERO CONTROLA LAS VALIDACIONES Y EL CONTADOR ANTES DE LLAMARLO. */
    void descubrir() {
        this.descubierta = true;
    }

    public boolean isMarcada() {
        return marcada;
    }

    void alternarBandera() {
        if (!descubierta) {
            this.marcada = !this.marcada;
        }
    }

    public abstract boolean tieneMina();

    public abstract char getSimboloDescubierto();

    public char getSimboloVisible(char simboloOculta, char simboloBandera) {
        if (!descubierta) {
            return marcada ? simboloBandera : simboloOculta;
        }
        return getSimboloDescubierto();
    }
}
