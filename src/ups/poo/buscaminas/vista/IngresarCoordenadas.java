package ups.poo.buscaminas.vista;

import java.util.Scanner;
import ups.poo.buscaminas.modelo.Tablero;
import ups.poo.buscaminas.vista.MostrarTablero;

public class IngresarCoordenadas {

    //LLAMAR SCANER
    Scanner sc = new Scanner(System.in);

    //METODO INGRESAR FILA
    public int ingresarFila() {
        System.out.print("Ingrese fila:");
        return sc.nextInt();
    }

    //METODO INGRESAR FILA
    public int ingresarColumna() {
        System.out.print("Ingrese Columna:");
        return sc.nextInt();
    }

    //CERRAR SCANNER
    public void cerraScanner(){
        sc.close();
    }

}
