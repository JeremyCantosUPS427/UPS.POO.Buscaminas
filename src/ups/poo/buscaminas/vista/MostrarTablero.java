package ups.poo.buscaminas.vista;
import ups.poo.buscaminas.modelo.*;

public class MostrarTablero {

    //METODO MOSTRAR TABLERO
    public void MostrarTablero(Tablero tablero) {
        for (int i = 0; i < tablero.getFilas(); i++) {
            for (int j = 0; j < tablero.getColumnas(); j++) {
                System.out.print(tablero.getCasilla(i, j).getSimboloVisible('■', '⚑') + " " );
            }
            System.out.println();
        }
    }

}
