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
        //revisarCadena(this.cadena);
    }

    private void revisarCadena(String cadena) {

        for (int i = 0; i < cadena.length(); i++) {
            for (int j = 0; j < trans.get(edosUnidad.indexOf(edoActual)).size(); j++) {
                if (String.valueOf(cadena.charAt(i)).equals(simbolosE.get(j))) {
                    /*    TransicionSalida ts = new TransicionSalida();
                    ts = (TransicionSalida) trans.get(edosUnidad.indexOf(edoActual)).get(simbolosE.indexOf(simbolosE.get(j)));
                    edoActual = ts.getEdoSig();
                    if (ts.getD().equals("D")) {
                        moverDerecha(ts.getY());
                    } else {
                        moverIzquierda(ts.getY());
                    }*/
                }
            }
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

    private void moverDerecha(String y) {
        if (cinta.indexOf(edoActual) == 0 && y.equals(b)) {
            String temporal = cinta.get(cinta.indexOf(edoActual)).toString();
            int index = cinta.indexOf(edoActual);
            cinta.remove(index);
            cinta.remove(index);
            cinta.add(index, y);
            cinta.add(index + 1, temporal);
            cinta.remove(0);
        } else if (cinta.indexOf(edoActual) == cinta.size() - 1) {
            cinta.add(b);
            String temporal = cinta.get(cinta.indexOf(edoActual)).toString();
            int index = cinta.indexOf(edoActual);
            cinta.remove(index);
            cinta.remove(index);
            cinta.add(index, y);
            cinta.add(index + 1, temporal);
        } else {
            String temporal = cinta.get(cinta.indexOf(edoActual)).toString();
            int index = cinta.indexOf(edoActual);
            cinta.remove(index);
            cinta.remove(index);
            cinta.add(index, y);
            cinta.add(index + 1, temporal);
        }
    }

    private void moverIzquierda(String y) {
        if (cinta.indexOf(edoActual) == 0) {
            cinta.add(0, b);
            String temporal = cinta.get(cinta.indexOf(edoActual)).toString();
            int index = cinta.indexOf(edoActual);
            cinta.remove(index);
            cinta.remove(index - 1);
            cinta.add(index - 1, y);
            cinta.add(index - 1, temporal);
        } else if (cinta.indexOf(edoActual) == cinta.size() - 1 && y.equals(b)) {
            String temporal = cinta.get(cinta.indexOf(edoActual)).toString();
            int index = cinta.indexOf(edoActual);
            cinta.remove(index);
            cinta.remove(index - 1);
            cinta.add(index - 1, y);
            cinta.add(index - 1, temporal);
            cinta.remove(cinta.size() - 1);
        } else {
            String temporal = cinta.get(cinta.indexOf(edoActual)).toString();
            int index = cinta.indexOf(edoActual);
            cinta.remove(index);
            cinta.remove(index - 1);
            cinta.add(index - 1, y);
            cinta.add(index - 1, temporal);
        }

    }
}
