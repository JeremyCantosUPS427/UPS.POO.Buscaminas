package ups.poo.buscaminas.modelo;

/** MATRIZ QUE PROTEGE SUS ELEMENTOS Y PERMITE REUTILIZAR LAS DIMENSIONES. */
public abstract class Matriz<T> {

    private final T[][] elementos;

    protected Matriz(T[][] elementos) {
        if (elementos == null || elementos.length == 0
                || elementos[0] == null || elementos[0].length == 0) {
            throw new IllegalArgumentException("La matriz debe tener filas y columnas.");
        }
        // COPIAR EL ARREGLO EXTERIOR Y CADA FILA; LOS OBJETOS GUARDADOS CONSERVAN SUS REFERENCIAS.
        int columnas = elementos[0].length;
        this.elementos = elementos.clone();
        for (int fila = 0; fila < elementos.length; fila++) {
            if (elementos[fila] == null || elementos[fila].length != columnas) {
                throw new IllegalArgumentException("La matriz debe ser rectangular.");
            }
            this.elementos[fila] = elementos[fila].clone();
        }
    }

    public final int getFilas() {
        return elementos.length;
    }

    public final int getColumnas() {
        return elementos[0].length;
    }

    public final boolean esCoordenadaValida(int fila, int columna) {
        return fila >= 0 && fila < getFilas()
                && columna >= 0 && columna < getColumnas();
    }

    protected final T getElemento(int fila, int columna) {
        return elementos[fila][columna];
    }

    protected final void setElemento(int fila, int columna, T elemento) {
        elementos[fila][columna] = elemento;
    }
}
