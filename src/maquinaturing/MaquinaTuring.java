/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maquinaturing;

import java.util.Scanner;

/**
 *
 * @author Ale
 */
public class MaquinaTuring {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LecturaTabla tbl = new LecturaTabla("src\\resources\\Ej1.txt");
        tbl.imprimirLenguaje();
        Scanner in = new Scanner(System.in);
        System.out.println("Ingrese la cadena a verificar: ");
        String cad = in.nextLine();
        Movimiento tra = new Movimiento(cad ,tbl);
    }
    
}
