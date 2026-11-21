package ups.poo.buscaminas.modelo;

public class CasillaMina extends Casilla {
    private static final long serialVersionUID = 1L;

    public CasillaMina(int fila, int columna) {
        super(fila, columna);
    }

    @Override
    public boolean tieneMina() {
        return true;
    }

    @Override
    public char getSimboloDescubierto() {
        return '⬛';
    }
}
