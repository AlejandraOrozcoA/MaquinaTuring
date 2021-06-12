/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maquinaturing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Ale
 */
public class LecturaTabla {

    private ArrayList<String> edosUnidad = new ArrayList<String>();
    private ArrayList<String> edosFinales = new ArrayList<String>();
    private ArrayList<String> simbolosE = new ArrayList<String>();
    private ArrayList<String> simbolosC = new ArrayList<String>();
    private ArrayList<ArrayList> trans = new ArrayList<ArrayList>();
    private String edoI;
    private String b;
    private FileReader fr;

    public LecturaTabla(String dir) {
        leerTabla(dir);
    }

    public ArrayList<String> getEdosUnidad() {
        return edosUnidad;
    }

    public ArrayList<String> getEdosFinales() {
        return edosFinales;
    }

    public ArrayList<String> getSimbolosE() {
        return simbolosE;
    }

    public ArrayList<String> getSimbolosC() {
        return simbolosC;
    }

    public ArrayList<ArrayList> getTrans() {
        return trans;
    }

    public String getEdoI() {
        return edoI;
    }

    public String getB() {
        return b;
    }
    
    //Obtiene toda la información de la tabla 
    private void leerTabla(String dir) {
        try {
            File archivo = new File(dir);
            fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);

            //Lee el archivo linea a linea 
            String linea;
            int aux = 0;
            while ((linea = br.readLine()) != null) {
                ArrayList<TransicionSalida> tSalida = new ArrayList<TransicionSalida>();
                if (aux == 0) {
                    b=linea;
                }else if (aux == 1) { //Lee el Alfabeto de entrada 
                    String ar[] = linea.split(",");
                    for (int i = 0; i < ar.length; i++) {
                        simbolosE.add(ar[i]);
                    }
                } else if (aux == 2) { //Lee el alfabeto de cinta 
                    String ar[] = linea.split(",");
                    for (int i = 2; i < ar.length; i++) {
                        simbolosC.add(ar[i]);
                    }
                } else {
                    String ar[] = linea.split(",");
                    //Guarda los Estados 
                    edosUnidad.add(ar[1]);

                    //Guarda el estado inicial
                    if (ar[0].equals("-") || ar[0].equals("*-")) {
                        edoI = ar[1];
                    }

                    //Guarda el estado de aceptacion
                    if (ar[0].equals("*") || ar[0].equals("*-")) {
                        edosFinales.add(ar[1]);
                    }

                    //Identifica las tansiciones de cada estado
                    for (int i = 2; i < ar.length ; i++) {
                        TransicionSalida ts;
                        if (!ar[i].equals("_")) {
                            String ar2[] = ar[i].split("\\|");
                            ts = new TransicionSalida(ar2[0], ar2[1], ar2[2]);
                            tSalida.add(ts);
                        } else {
                            ts = new TransicionSalida();
                            tSalida.add(ts);
                        }
                    }
                    trans.add(tSalida);
                }
                ++aux;
            }
        } catch (IOException e) {
            System.out.println("No se pudo leer el archivo");
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar el archivo");
            }
        }
    }
    
    //Imprime el Lenguaje del automata 
    public void imprimirLenguaje(){
        System.out.print("El Alfabeto de entrada es: ");
        for (int i = 0; i < simbolosE.size(); i++) {
            System.out.print(simbolosE.get(i)+ ",");
        }
        System.out.println(" ");
    }    
}
