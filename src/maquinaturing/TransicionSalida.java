// Instituto Politécnico Nacional - ESCOM 
// Alejandra Orozco Aguilar 
// Teoría Computacional  Grupo: 2CV12
// Prof. Luna Benoso Benjamin
// Ciclo escolar 21/2

package maquinaturing;

/**
 *
 * @author Ale
 */
public class TransicionSalida {
    private String edoSig;
    private String y;
    private String D;

    public TransicionSalida(String edoSig, String y, String D) {
        this.edoSig = edoSig;
        this.y = y;
        this.D = D;
    }

    public TransicionSalida() {
        this.edoSig = " ";
        this.y = " ";
        this.D = " ";
    }

    public String getEdoSig() {
        return edoSig;
    }

    public void setEdoSig(String edoSig) {
        this.edoSig = edoSig;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getD() {
        return D;
    }

    public void setD(String D) {
        this.D = D;
    }
}
