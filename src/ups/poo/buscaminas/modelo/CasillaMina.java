package ups.poo.buscaminas.modelo;

/** REPRESENTA UNA MINA: DESCUBRIRLA HACE PERDER LA PARTIDA. */
public final class CasillaMina extends Casilla {

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
        return '*';
    }
}
