package ups.poo.buscaminas.modelo;

import java.util.Random;

public class Matriz {

    //ATRIBUTOS

    int [][] matriz;
    public Matriz(int [][] matriz) {
        this.matriz = matriz;
    }

    //CONSTRUCTOR
    public Matriz() {
        this.matriz = new int[10][10];
    }

    //GETTERS
    public int[][] getMatriz() {
        return matriz;
    }

    //METODO
    public void setMatriz(int[][] matriz) {
        this.matriz = matriz;
    }

    //METODO RELLENAR MATRIZ
    public void LlenarMatriz(){
//        Random random = new Random();
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                matriz[i][j] = Integer.parseInt("0");
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }
}
