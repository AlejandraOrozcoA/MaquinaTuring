// Instituto Politécnico Nacional - ESCOM 
// Alejandra Orozco Aguilar 
// Teoría Computacional  Grupo: 2CV12
// Prof. Luna Benoso Benjamin
// Ciclo escolar 21/2

package maquinaturing;

import java.util.Scanner;

/**
 *
 * @author Ale
 */
public class MaquinaTuring {
    public static void main(String[] args) {
        LecturaTabla tbl = new LecturaTabla("src\\resources\\Ej2.txt");
        tbl.imprimirLenguaje();
        Scanner in = new Scanner(System.in);
        System.out.println("Ingrese la cadena a verificar: ");
        String cad = in.nextLine();
        Movimiento tra = new Movimiento(cad ,tbl);
    }
    
}
