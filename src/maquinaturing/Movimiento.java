// Instituto Politécnico Nacional - ESCOM 
// Alejandra Orozco Aguilar 
// Teoría Computacional  Grupo: 2CV12
// Prof. Luna Benoso Benjamin
// Ciclo escolar 21/2

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
        revisarCinta();
        mostrarMsj();
    }

    private void revisarCinta() {
        int indexCinta;
        int indexEdo;
        //Obtiene la ubicacion del cabezal 
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
        //Identifica la transicion siguiente 
        TransicionSalida ts = new TransicionSalida();
        ts = (TransicionSalida) trans.get(indexEdo).get(indexCinta);
        if (ts.getD().equals("R")) { 
            moverDerecha(ts.getY(), ts.getEdoSig());
            revisarCinta();
        } else if (ts.getD().equals("L")) {
            moverIzquierda(ts.getY(), ts.getEdoSig());
            revisarCinta();
        }
    }
    
    //Inicializa la cinta con el estado inicial y la cadena 
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
    //Se encarga de mover el cabezal a la derecha 
    private void moverDerecha(String y, String sig) {
        if (cinta.indexOf(edoActual) == 0 && y.equals(b)) { // El cabezal apunta a X1 & Y=B
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
        } else if (cinta.indexOf(edoActual) == cinta.size() - 1) { // El cabezal apunta a Xn
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
        } else { //El cabezal apunta a Xi
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
    //Se encarga de mover el cabezal a la izquierda 
    private void moverIzquierda(String y, String sig) {
        if (cinta.indexOf(edoActual) == 0) { //El cabezal apunta a X1
            cinta.add(0, b);
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
        } else if (cinta.indexOf(edoActual) == cinta.size() - 1 && y.equals(b)) { //El cabezal abunta a Xn & Y=B
            int index = cinta.indexOf(edoActual);
            edoActual = sig;
            cinta.remove(index);
            cinta.remove(index);
            cinta.add(index, y);
            cinta.add(index - 1, edoActual);
            cinta.remove(cinta.size() - 1);
            System.out.println("");
            for (int i = 0; i < cinta.size(); i++) {
                System.out.print(cinta.get(i) + " ");
            }
        } else { //El cabezal apunta a Xi
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
    
    //Muestra el mensaje que indica si la cadena es aceptada o no 
    private void mostrarMsj() {
        System.out.println("");
        if (edosFinales.contains(edoActual)) {
            System.out.println("La cadena es aceptada");
        } else {
            System.out.println("La cadena no es aceptada");
        }
    }
}
