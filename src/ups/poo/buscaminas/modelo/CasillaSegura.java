package ups.poo.buscaminas.modelo;

/** REPRESENTA UNA CASILLA SEGURA Y ALMACENA CUÁNTAS MINAS TIENE EN SUS OCHO VECINAS. */
public final class CasillaSegura extends Casilla {

    private static final long serialVersionUID = 1L;

    // UN CERO PERMITE CONTINUAR LA EXPANSIÓN RECURSIVA; DE 1 A 8 INDICA EL BORDE DE LA REGIÓN.
    private int minasAdyacentes;

    public CasillaSegura(int fila, int columna) {
        super(fila, columna);
    }

    public int getMinasAdyacentes() {
        return minasAdyacentes;
    }

    void setMinasAdyacentes(int minasAdyacentes) {
        if (minasAdyacentes < 0 || minasAdyacentes > 8) {
            throw new IllegalArgumentException("Una casilla puede tener entre 0 y 8 minas adyacentes.");
        }
        this.minasAdyacentes = minasAdyacentes;
    }

    public boolean sinMinasAlrededor() {
        return minasAdyacentes == 0;
    }

    @Override
    public boolean tieneMina() {
        return false;
    }

    @Override
    public char getSimboloDescubierto() {
        if (minasAdyacentes == 0) {
            return '.';
        }
        return Character.forDigit(minasAdyacentes, 10);
    }
}
