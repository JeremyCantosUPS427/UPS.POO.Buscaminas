package ups.poo.buscaminas.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import ups.poo.buscaminas.excepciones.CasillaMarcadaException;
import ups.poo.buscaminas.excepciones.CasillaYaDescubiertaException;
import ups.poo.buscaminas.excepciones.CoordenadaInvalidaException;

/** APLICA LAS REGLAS DEL BUSCAMINAS SOBRE UNA MATRIZ FIJA DE 10 FILAS Y 10 COLUMNAS. */
public final class Tablero extends Matriz<Casilla> {
    // LAS DIMENSIONES NO CAMBIAN ENTRE PARTIDAS; LA APLICACIÓN NORMAL USA DIEZ MINAS.
    public static final int FILAS = 10;
    public static final int COLUMNAS = 10;
    public static final int TOTAL_MINAS = 10;
    private static final int TOTAL_CASILLAS = FILAS * COLUMNAS;

    private final int totalMinas;
    private int casillasSegurasDescubiertas;
    private EstadoPartida estado;

    /** CREA LA PARTIDA NORMAL DE 10X10 CON DIEZ MINAS. */
    public Tablero() {
        this(TOTAL_MINAS);
    }

    /** CONSERVA EL TAMAÑO 10X10 Y PERMITE VARIAR LAS MINAS PARA COMPROBAR CASOS DE PRUEBA. */
    public Tablero(int totalMinas) {
        super(crearMatriz(totalMinas));
        this.totalMinas = totalMinas;
        reiniciar();
    }

    /** VALIDA LA CANTIDAD DE MINAS ANTES DE RESERVAR LAS CASILLAS; EXIGE AL MENOS UNA SEGURA. */
    private static Casilla[][] crearMatriz(int totalMinas) {
        if (totalMinas < 0 || totalMinas >= TOTAL_CASILLAS) {
            throw new IllegalArgumentException(
                    "Las minas deben ser entre 0 y el total de casillas menos 1.");
        }
        return new Casilla[FILAS][COLUMNAS];
    }

    /** BORRA EL PROGRESO DE LA PARTIDA Y GENERA OTRA DISTRIBUCIÓN CON LA MISMA CANTIDAD DE MINAS. */
    public void reiniciar() {
        crearCasillasSeguras();
        casillasSegurasDescubiertas = 0;
        estado = EstadoPartida.EN_CURSO;
        colocarMinasAleatorias();
        calcularMinasAdyacentes();
    }

    /** REEMPLAZA LAS CASILLAS ANTERIORES POR CASILLAS OCULTAS SIN MINA NI BANDERA. */
    private void crearCasillasSeguras() {
        for (int fila = 0; fila < getFilas(); fila++) {
            for (int columna = 0; columna < getColumnas(); columna++) {
                setElemento(fila, columna, new CasillaSegura(fila, columna));
            }
        }
    }

    /** TOMA POSICIONES DIFERENTES DE LA LISTA BARAJADA PARA EVITAR MINAS REPETIDAS. */
    private void colocarMinasAleatorias() {
        List<Integer> posiciones = generarPosicionesAleatorias();
        for (int indice = 0; indice < totalMinas; indice++) {
            int posicion = posiciones.get(indice);
            int fila = posicion / getColumnas();
            int columna = posicion % getColumnas();
            setElemento(fila, columna, new CasillaMina(fila, columna));
        }
    }

    /** PREPARA LAS 100 POSICIONES EN ORDEN ALEATORIO SIN ELIMINAR NI DUPLICAR NINGUNA. */
    private List<Integer> generarPosicionesAleatorias() {
        List<Integer> posiciones = new ArrayList<>();
        for (int posicion = 0; posicion < TOTAL_CASILLAS; posicion++) {
            posiciones.add(posicion);
        }
        // BARAJAR POSICIONES ÚNICAS EVITA COLOCAR DOS MINAS EN LA MISMA CASILLA.
        Collections.shuffle(posiciones);
        return posiciones;
    }

    /** ASIGNA A CADA CASILLA SEGURA EL NÚMERO QUE VERÁ EL JUGADOR AL DESCUBRIRLA. */
    private void calcularMinasAdyacentes() {
        for (int fila = 0; fila < getFilas(); fila++) {
            for (int columna = 0; columna < getColumnas(); columna++) {
                if (getCasilla(fila, columna) instanceof CasillaSegura casillaSegura) {
                    casillaSegura.setMinasAdyacentes(contarMinasVecinas(fila, columna));
                }
            }
        }
    }

    /** CUENTA SOLO LAS MINAS DE LAS OCHO VECINAS, EXCLUYENDO LA CASILLA CENTRAL. */
    private int contarMinasVecinas(int fila, int columna) {
        int minasVecinas = 0;
        for (Casilla vecina : obtenerVecinas(fila, columna)) {
            if (vecina.tieneMina()) {
                minasVecinas++;
            }
        }
        return minasVecinas;
    }

    /** COMPARTE EL RECORRIDO DE VECINOS ENTRE EL CONTEO DE MINAS Y LA EXPANSIÓN RECURSIVA. */
    private List<Casilla> obtenerVecinas(int fila, int columna) {
        List<Casilla> vecinas = new ArrayList<>();
        for (int desplazamientoFila = -1; desplazamientoFila <= 1; desplazamientoFila++) {
            for (int desplazamientoColumna = -1; desplazamientoColumna <= 1; desplazamientoColumna++) {
                // EL DESPLAZAMIENTO CERO REPRESENTA LA CASILLA CENTRAL Y NO ES UNA VECINA.
                if (desplazamientoFila == 0 && desplazamientoColumna == 0) {
                    continue;
                }
                int filaVecina = fila + desplazamientoFila;
                int columnaVecina = columna + desplazamientoColumna;
                if (esCoordenadaValida(filaVecina, columnaVecina)) {
                    vecinas.add(getCasilla(filaVecina, columnaVecina));
                }
            }
        }
        return vecinas;
    }

    /** RECHAZA ÍNDICES FUERA DE LA MATRIZ ANTES DE CONSULTAR UNA CASILLA. */
    public Casilla getCasilla(int fila, int columna) {
        if (!esCoordenadaValida(fila, columna)) {
            throw new IndexOutOfBoundsException("La casilla está fuera del tablero.");
        }
        return getElemento(fila, columna);
    }

    /** DEVUELVE TRUE AL DESCUBRIR UNA MINA Y FALSE AL DESCUBRIR UNA CASILLA SEGURA. */
    public boolean descubrirCasilla(int fila, int columna)
            throws CoordenadaInvalidaException, CasillaYaDescubiertaException, CasillaMarcadaException {
        Casilla casilla = obtenerCasillaOculta(fila, columna);
        if (casilla.isMarcada()) {
            throw new CasillaMarcadaException();
        }
        if (casilla.tieneMina()) {
            descubrir(casilla);
            estado = EstadoPartida.PERDIDA;
            return true;
        }
        descubrirRegionRecursivamente(casilla);
        if (todasLasCasillasSegurasDescubiertas()) {
            estado = EstadoPartida.GANADA;
        }
        return false;
    }

    /** COLOCA O QUITA UNA BANDERA EN UNA CASILLA OCULTA Y DEVUELVE SI QUEDÓ MARCADA. */
    public boolean alternarBandera(int fila, int columna)
            throws CoordenadaInvalidaException, CasillaYaDescubiertaException {
        Casilla casilla = obtenerCasillaOculta(fila, columna);
        casilla.alternarBandera();
        return casilla.isMarcada();
    }

    /** CENTRALIZA LAS VALIDACIONES DE PARTIDA ACTIVA, COORDENADA VÁLIDA Y CASILLA SIN DESCUBRIR. */
    private Casilla obtenerCasillaOculta(int fila, int columna)
            throws CoordenadaInvalidaException, CasillaYaDescubiertaException {
        comprobarPartidaEnCurso();
        validarCoordenada(fila, columna);
        Casilla casilla = getCasilla(fila, columna);
        if (casilla.isDescubierta()) {
            throw new CasillaYaDescubiertaException();
        }
        return casilla;
    }

    /** ACTUALIZA EL CONTADOR SOLO PARA CASILLAS SEGURAS; EL LLAMADOR EVITA ABRIRLAS DOS VECES. */
    private void descubrir(Casilla casilla) {
        casilla.descubrir();
        if (!casilla.tieneMina()) {
            casillasSegurasDescubiertas++;
        }
    }

    /** ABRE LAS CASILLAS VACÍAS CONECTADAS Y SUS NÚMEROS DE BORDE SIN DESCUBRIR MINAS NI BANDERAS. */
    private void descubrirRegionRecursivamente(Casilla casilla) {
        // CASO BASE: DETENER LA LLAMADA SI LA CASILLA YA FUE VISITADA O NO DEBE ABRIRSE.
        if (casilla.isDescubierta() || casilla.isMarcada() || casilla.tieneMina()) {
            return;
        }
        // ABRIR ANTES DE RECURRIR EVITA CICLOS Y CONTAR LA MISMA CASILLA DOS VECES.
        descubrir(casilla);
        // PASO RECURSIVO: LOS CEROS EXPANDEN LA REGIÓN; LOS NÚMEROS SOLO SE DESCUBREN.
        if (casilla instanceof CasillaSegura casillaSegura && casillaSegura.sinMinasAlrededor()) {
            for (Casilla vecina : obtenerVecinas(casilla.getFila(), casilla.getColumna())) {
                descubrirRegionRecursivamente(vecina);
            }
        }
    }

    /** INFORMA LOS LÍMITES A-J Y 1-10 CUANDO UNA JUGADA QUEDA FUERA DEL TABLERO. */
    private void validarCoordenada(int fila, int columna) throws CoordenadaInvalidaException {
        if (!esCoordenadaValida(fila, columna)) {
            throw new CoordenadaInvalidaException("Use una fila entre A y "
                    + (char) ('A' + getFilas() - 1) + " y una columna entre 1 y " + getColumnas() + ".");
        }
    }

    /** BLOQUEA NUEVAS ACCIONES CUANDO LA PARTIDA YA TERMINÓ CON VICTORIA O DERROTA. */
    private void comprobarPartidaEnCurso() {
        if (estado != EstadoPartida.EN_CURSO) {
            throw new IllegalStateException("La partida terminó. Inicia otra para seguir jugando.");
        }
    }

    /** LA VICTORIA DEPENDE DE ABRIR TODAS LAS CASILLAS SEGURAS, NO DE LA CANTIDAD DE BANDERAS. */
    public boolean todasLasCasillasSegurasDescubiertas() {
        return casillasSegurasDescubiertas == TOTAL_CASILLAS - totalMinas;
    }

    /** EXPONE LA CANTIDAD DE MINAS FIJADA AL CONSTRUIR LA PARTIDA. */
    public int getTotalMinas() {
        return totalMinas;
    }

    /** INCLUYE LAS CASILLAS ABIERTAS AUTOMÁTICAMENTE POR LA RECURSIVIDAD, PERO NUNCA LAS MINAS. */
    public int getCasillasSegurasDescubiertas() {
        return casillasSegurasDescubiertas;
    }

    /** PERMITE AL CONTROLADOR DETENER LOS TURNOS Y A LA VISTA DECIDIR CUÁNDO REVELAR LAS MINAS. */
    public EstadoPartida getEstado() {
        return estado;
    }
}
