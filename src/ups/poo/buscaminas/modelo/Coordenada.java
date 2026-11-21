package ups.poo.buscaminas.modelo;

public class Coordenada {

    //ATRIBUTOS
    private int fila;
    private int columna;

    //CONSTRUCTOR
    public Coordenada(int fila, int columna){
        this.columna = columna;
        this.fila = fila;
    }

    //GETTERS AND SETTERS


    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }
}
