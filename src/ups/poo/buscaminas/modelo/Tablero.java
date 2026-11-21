package ups.poo.buscaminas.modelo;

import java.util.Random;

public class Tablero extends Matriz {

    //ATRIBUTOS
    private static final long serialVersionUID = 1L;

    public static final int FILAS = 10;
    public static final int COLUMNAS = 10;
    public static final int TOTAL_MINAS = 10;

    private final Casilla[][] casillas;
    private final int totalMinas;
    private int casillasSegurasDescubiertas;

    //CONSTRUCTOR PARA MATRIZ
    public Tablero() {
        this(FILAS, COLUMNAS, TOTAL_MINAS);
    }

    //CONSTRUCTOR PARA TABLERO
    public Tablero(int filas, int columnas, int totalMinas) {
        super(new int[filas][columnas]);
        this.casillas = new Casilla[filas][columnas];
        this.totalMinas = totalMinas;
        inicializarTablero();
    }

    //METODO PARA INCIAR CREAR VALORES EN TABLERO
    private void inicializarTablero() {
        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas[i].length; j++) {
                casillas[i][j] = new CasillaVacia(i, j);
            }
        }
        this.casillasSegurasDescubiertas = 0;
        colocarMinasAleatorias();
        calcularMinasAdyacentes();
        sincronizarMatriz();
    }

    public void reiniciar() {
        inicializarTablero();
    }

    //METODO PARA GENERAR MINAS ALEATORIAS
    private void colocarMinasAleatorias() {
        Random random = new Random();
        int minasColocadas = 0;
        int filas = casillas.length;
        int columnas = casillas[0].length;

        while (minasColocadas < totalMinas) {
            int f = random.nextInt(filas);
            int c = random.nextInt(columnas);
            if (!casillas[f][c].tieneMina()) {
                casillas[f][c] = new CasillaMina(f, c);
                minasColocadas++;
            }
        }
    }

    //METODO CALCULAR MINAS CERCANAS
    private void calcularMinasAdyacentes() {
        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas[i].length; j++) {
                if (casillas[i][j] instanceof CasillaVacia) {
                    CasillaVacia vacia = (CasillaVacia) casillas[i][j];
                    vacia.setMinasAdyacentes(contarMinasAlrededor(i, j));
                }
            }
        }
    }

    //CONTADOR DE MINAS ALREDEDOR
    private int contarMinasAlrededor(int fila, int columna) {
        int contador = 0;
        for (int df = -1; df <= 1; df++) {
            for (int dc = -1; dc <= 1; dc++) {
                if (df == 0 && dc == 0) {
                    continue;
                }
                int nf = fila + df;
                int nc = columna + dc;
                if (esCoordenadaValida(nf, nc) && casillas[nf][nc].tieneMina()) {
                    contador++;
                }
            }
        }
        return contador;
    }

    //VALIDAR COORDENADA
    public boolean esCoordenadaValida(int fila, int columna) {
        return fila >= 0 && fila < casillas.length
                && columna >= 0 && columna < casillas[0].length;
    }

    //GETTERS AND SETTERS
    public Casilla getCasilla(int fila, int columna) {
        return casillas[fila][columna];
    }

    public boolean descubrirCasilla(int fila, int columna) {
        Casilla actual = casillas[fila][columna];
        if (actual.isDescubierta() || actual.isMarcada()) {
            return false;
        }

        actual.setDescubierta(true);

        if (actual.tieneMina()) {
            return true;
        }

        casillasSegurasDescubiertas++;

        if (actual instanceof CasillaVacia && ((CasillaVacia) actual).sinMinasAlrededor()) {
            expandirVacias(fila, columna);
        }

        return false;
    }

    //METODO EXPANDIR CASILLAS VACIAS DEL TABLERO
    private void expandirVacias(int fila, int columna) {
        for (int df = -1; df <= 1; df++) {
            for (int dc = -1; dc <= 1; dc++) {
                if (df == 0 && dc == 0) {
                    continue;
                }
                int nf = fila + df;
                int nc = columna + dc;
                if (esCoordenadaValida(nf, nc)) {
                    Casilla vecina = casillas[nf][nc];
                    if (!vecina.isDescubierta() && !vecina.isMarcada() && !vecina.tieneMina()) {
                        vecina.setDescubierta(true);
                        casillasSegurasDescubiertas++;
                        if (vecina instanceof CasillaVacia && ((CasillaVacia) vecina).sinMinasAlrededor()) {
                            expandirVacias(nf, nc);
                        }
                    }
                }
            }
        }
    }

    public boolean todasLasCasillasSegurasDescubiertas() {
        int totalCasillas = casillas.length * casillas[0].length;
        return casillasSegurasDescubiertas == (totalCasillas - totalMinas);
    }

    public int getFilas() {
        return casillas.length;
    }

    public int getColumnas() {
        return casillas[0].length;
    }

    public int getTotalMinas() {
        return totalMinas;
    }

    //ENVIAR DATOS A MATRIZ
    private void sincronizarMatriz() {
        int[][] datos = getMatriz();
        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas[i].length; j++) {
                if (casillas[i][j].tieneMina()) {
                    datos[i][j] = -1;
                } else {
                    datos[i][j] = ((CasillaVacia) casillas[i][j]).getMinasAdyacentes();
                }
            }
        }
        setMatriz(datos);
    }

}
