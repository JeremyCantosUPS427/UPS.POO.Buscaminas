package ups.poo.buscaminas.modelo;

public class CasillaVacia extends Casilla {
    private static final long serialVersionUID = 1L;

    private int minasAdyacentes;

    public CasillaVacia(int fila, int columna) {
        super(fila, columna);
        this.minasAdyacentes = 0;
    }

    public int getMinasAdyacentes() {
        return minasAdyacentes;
    }

    public void setMinasAdyacentes(int minasAdyacentes) {
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
            return 'V';
        }
        return Character.forDigit(minasAdyacentes, 10);
    }
}
