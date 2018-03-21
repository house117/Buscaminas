/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;
import javax.swing.JButton;
/**
 *
 * @author House
 */
public class Casilla extends JButton{
    private Boolean mina;
    private Boolean flageada; //Para verificar si esta marcada como mina o no.
    private Boolean abierta = false; //Para verificar si esta abierto 
                             //o cerrado, true = abierto, false = cerrado
    private Integer minasAlrededor = 0; //contendra el numero de minas que tiene alrededor
    private Integer i;
    private Integer j;

            
    public Casilla(){
        super();
        Double random = Math.random();
        if(random > 0.9) //probabilidad de que tenga mina
            mina = true;
        else
            mina = false;
    }
    public Boolean tieneMina(){
        return getMina();
    }

    /**
     * @return the estado
     */
    public Boolean getAbierto() {
        return abierta;
    }

    /**
     * @param estado the estado to set
     */
    public void setAbierto(Boolean estado) {
        this.abierta = estado;
    }

    /**
     * @return the minasAlrededor
     */
    public Integer getMinasAlrededor() {
        return minasAlrededor;
    }

    /**
     * suma una minaalrededor
     */
    public void addMinasAlrededor() {
        this.minasAlrededor++;
    }

    /**
     * @return the mina
     */
    public Boolean getMina() {
        return mina;
    }

    /**
     * @param mina the mina to set
     */
    public void setMina(Boolean mina) {
        this.mina = mina;
    }

    /**
     * @return the i
     */
    public Integer getI() {
        return i;
    }

    /**
     * @param i the i to set
     */
    public void setI(Integer i) {
        this.i = i;
    }

    /**
     * @return the j
     */
    public Integer getJ() {
        return j;
    }

    /**
     * @param j the j to set
     */
    public void setJ(Integer j) {
        this.j = j;
    }
}
