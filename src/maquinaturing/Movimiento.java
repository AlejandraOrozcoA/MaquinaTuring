/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maquinaturing;

import java.util.ArrayList;

/**
 *
 * @author Ale
 */
public class Movimiento {

    private ArrayList<String> edosUnidad = new ArrayList<String>();
    private ArrayList<String> edosFinales = new ArrayList<String>();
    private ArrayList<String> simbolosE = new ArrayList<String>();
    private ArrayList<String> simbolosC = new ArrayList<String>();
    private ArrayList<ArrayList> trans = new ArrayList<ArrayList>();
    private String edoI;
    private String b;

    private String cadena;
    private ArrayList cinta = new ArrayList();
    private String edoActual;

    public Movimiento(String cadena, LecturaTabla tb) {
        this.cadena = cadena;
        this.edosUnidad = tb.getEdosUnidad();
        this.edosFinales = tb.getEdosFinales();
        this.simbolosE = tb.getSimbolosE();
        this.simbolosC = tb.getSimbolosC();
        this.trans = tb.getTrans();
        this.edoI = tb.getEdoI();
        this.b = tb.getB();
        this.edoActual = this.edoI;
        llenarCinta();
        revisarCadena();
        mostrarMsj();
    }

    private void revisarCadena() {
        int indexCinta;
        int indexEdo;
        if (cinta.indexOf(edoActual) == cinta.size() - 1) {
            cinta.add(b);
            String aux = cinta.get(cinta.indexOf(edoActual) + 1).toString();
            indexCinta = simbolosC.indexOf(aux);
            indexEdo = edosUnidad.indexOf(edoActual);
        } else {
            String aux = cinta.get(cinta.indexOf(edoActual) + 1).toString();
            indexCinta = simbolosC.indexOf(aux);
            indexEdo = edosUnidad.indexOf(edoActual);
        }
        TransicionSalida ts = new TransicionSalida();
        ts = (TransicionSalida) trans.get(indexEdo).get(indexCinta);
        if (ts.getD().equals("R")) {
            moverDerecha(ts.getY(), ts.getEdoSig());
            revisarCadena();
        } else if (ts.getD().equals("L")) {
            moverIzquierda(ts.getY(), ts.getEdoSig());
            revisarCadena();
        }
    }

    private void llenarCinta() {
        cinta.clear();
        cinta.add(edoActual);
        for (int i = 0; i < cadena.length(); i++) {
            cinta.add(cadena.charAt(i));
        }
        for (int i = 0; i < cinta.size(); i++) {
            System.out.print(cinta.get(i) + " ");
        }
    }

    private void moverDerecha(String y, String sig) {
        if (cinta.indexOf(edoActual) == 0 && y.equals(b)) {
            int index = cinta.indexOf(edoActual);
            edoActual = sig;
            cinta.remove(index);
            cinta.remove(index);
            cinta.add(index, y);
            cinta.add(index + 1, edoActual);
            cinta.remove(0);
            System.out.println("");
            for (int i = 0; i < cinta.size(); i++) {
                System.out.print(cinta.get(i) + " ");
            }
        } else if (cinta.indexOf(edoActual) == cinta.size() - 1) {
            cinta.add(b);
            int index = cinta.indexOf(edoActual);
            edoActual = sig;
            cinta.remove(index);
            cinta.remove(index);
            cinta.add(index, y);
            cinta.add(index + 1, edoActual);
            System.out.println("");
            for (int i = 0; i < cinta.size(); i++) {
                System.out.print(cinta.get(i) + " ");
            }
        } else {
            int index = cinta.indexOf(edoActual);
            edoActual = sig;
            cinta.remove(index);
            cinta.remove(index);
            cinta.add(index, y);
            cinta.add(index + 1, edoActual);
            System.out.println("");
            for (int i = 0; i < cinta.size(); i++) {
                System.out.print(cinta.get(i) + " ");
            }
        }
    }

    private void moverIzquierda(String y, String sig) {
        if (cinta.indexOf(edoActual) == 0) {
            cinta.add(0, b);
            int index = cinta.indexOf(edoActual);
            edoActual = sig;
            cinta.remove(index);
            cinta.remove(index + 1);
            cinta.add(index, y);
            cinta.add(index - 1, edoActual);
            System.out.println("");
            for (int i = 0; i < cinta.size(); i++) {
                System.out.print(cinta.get(i) + " ");
            }
        } else if (cinta.indexOf(edoActual) == cinta.size() - 1 && y.equals(b)) {
            int index = cinta.indexOf(edoActual);
            edoActual = sig;
            cinta.remove(index);
            cinta.remove(index + 1);
            cinta.add(index, y);
            cinta.add(index - 1, edoActual);
            cinta.remove(cinta.size() - 1);
            System.out.println("");
            for (int i = 0; i < cinta.size(); i++) {
                System.out.print(cinta.get(i) + " ");
            }
        } else {
            int index = cinta.indexOf(edoActual);
            edoActual = sig;
            cinta.remove(index);
            cinta.remove(index);
            cinta.add(index, y);
            cinta.add(index - 1, edoActual);
            System.out.println("");
            for (int i = 0; i < cinta.size(); i++) {
                System.out.print(cinta.get(i) + " ");
            }
        }

    }

    private void mostrarMsj() {
        System.out.println("");
        if (edosFinales.contains(edoActual)) {
            System.out.println("La cadena es aceptada");
        } else {
            System.out.println("La cadena no es aceptada");
        }
    }
}
