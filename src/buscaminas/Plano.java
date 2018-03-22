/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;

/**
 *
 * @author House
 */
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
public class Plano extends JPanel{
    private Casilla[][] matrix;
    private Integer fil;
    private Integer col;
    private Integer numeroDeMinas = 0;
    public Plano(){
        
    }
    public void setPlano(Integer filas, Integer columnas){
        this.fil = filas+2;
        this.col = columnas+2;
        matrix = new Casilla[fil][col];
        
        for(int i=0; i<fil; i++){
            matrix[i][0] = new Casilla();
            matrix[i][0].setAbierto(true);
            matrix[i][0].setMina(false);
            matrix[i][col-1] = new Casilla();
            matrix[i][col-1].setAbierto(true);
            matrix[i][col-1].setMina(false);

        }
        for(int i=0; i<col; i++){
            matrix[0][i] = new Casilla();
            matrix[0][i].setAbierto(true);
            matrix[0][i].setMina(false);
            matrix[fil-1][i] = new Casilla();
            matrix[fil-1][i].setAbierto(true);
            matrix[fil-1][i].setMina(false);

        }
      
    }
     public void colorearDemasMinas(){
         ImageIcon saveImage = new ImageIcon("C:\\Users\\House\\Documents\\UNIVERSIDAD\\JavaPrograms\\Buscaminas\\resources\\minemine.png");
         Icon icono;
        for(int i=0; i<this.fil-2; i++){
            for(int j=0; j<this.col-2; j++){
                if(matrix[i+1][j+1].tieneMina()){
                    icono = new ImageIcon(saveImage.getImage().getScaledInstance(matrix[i+1][j+1].getWidth()/2, 
                            matrix[i+1][j+1].getHeight()/2, Image.SCALE_DEFAULT));
                    matrix[i+1][j+1].setIcon(icono);
                    matrix[i+1][j+1].setBackground(Color.BLACK); 
                    
                }
            }
        }
    }
    public void colorearDemasMinasWIN(){
         ImageIcon saveImage = new ImageIcon("C:\\Users\\House\\Documents\\UNIVERSIDAD\\JavaPrograms\\Buscaminas\\resources\\minemine.png");
         Icon icono;
        for(int i=0; i<this.fil-2; i++){
            for(int j=0; j<this.col-2; j++){
                if(matrix[i+1][j+1].tieneMina()){
                    icono = new ImageIcon(saveImage.getImage().getScaledInstance(matrix[i+1][j+1].getWidth()/2, 
                    matrix[i+1][j+1].getHeight()/2, Image.SCALE_DEFAULT));
                    matrix[i+1][j+1].setIcon(icono);
                    matrix[i+1][j+1].setBackground(Color.BLUE); 
                }
            }
        }
    }
    public void obtenerMinasAlrededor(){
        Integer[] F ={-1, -1, 0, 1, 1, 1, 0, -1};
        Integer[] C ={0, 1, 1, 1, 0, -1, -1, -1};
        for(int i=0; i<this.fil-2; i++){
           for(int j=0; j<this.col-2; j++){
               for(int m=0;m<8;m++){
                   if(matrix[i+1+F[m]][j+1+C[m]].tieneMina() == true){
                       matrix[i+1][j+1].addMinasAlrededor();
                   }
               }
           }
       }
    }
    /*
    Con este método coloco a todos los botones sus coordenadas
    */
    public void setXY(){
       for(int i=0; i<fil; i++){
           for(int j=0; j<col; j++){
               matrix[i][j].setI(i);
               matrix[i][j].setJ(j);
           }
       }
    }
    
    /*
        Abre las minas vacias alrededor de la mina enviada (no tiene que ser vacia pero de eso se encarga 
        alguien mas
        */
    public void descubrirMinasVacias(Casilla actual){
        Integer i = actual.getI();
        Integer j = actual.getJ();
        Integer[] F ={-1, -1, 0, 1, 1, 1, 0, -1};
        Integer[] C ={0, 1, 1, 1, 0, -1, -1, -1};
               for(int m=0;m<8;m++){
                   if(!matrix[i+F[m]][j+C[m]].getAbierto()){
                        if(matrix[i+F[m]][j+C[m]].getMinasAlrededor() != 0){
                            matrix[actual.getI()+F[m]][actual.getJ()+C[m]].setAbierto(true);
                            matrix[actual.getI()+F[m]][actual.getJ()+C[m]].setBackground(Color.PINK);
                            matrix[actual.getI()+F[m]][actual.getJ()+C[m]].setText(
                                    String.format("%d", matrix[actual.getI()+F[m]][actual.getJ()+C[m]].getMinasAlrededor()));
                            matrix[actual.getI()+F[m]][actual.getJ()+C[m]].setFont(new Font("Comic Sans MS", Font.PLAIN, 8));
                        }
                        if(matrix[i+F[m]][j+C[m]].getMinasAlrededor() == 0){
                            matrix[actual.getI()+F[m]][actual.getJ()+C[m]].setAbierto(true);
                            matrix[actual.getI()+F[m]][actual.getJ()+C[m]].setBackground(Color.PINK);
                            descubrirMinasVacias(matrix[actual.getI()+F[m]][actual.getJ()+C[m]]);

                        }
                        
                   }
               }
    }
    
    
    public Integer getCasillasAbiertas(){
        Integer cont=0;
        for(int i=0; i<fil-2; i++){
            for(int j=0; j<col-2; j++){
                if(matrix[i+1][j+1].getAbierto()){
                    cont++;
                }
            }
        }
        return cont;
    }
    

    /**
     * @return the matrix
     */
    public Casilla[][] getMatrix() {
        return matrix;
    }

    /**
     * @param matrix the matrix to set
     */
    public void setMatrix(Casilla[][] matrix) {
        this.matrix = matrix;
    }

    /**
     * @return the numeroDeMinas
     */
    public Integer getNumeroDeMinas() {
        return numeroDeMinas;
    }

    /**
     * @param numeroDeMinas the numeroDeMinas to set
     */
    public void setNumeroDeMinas(Integer numeroDeMinas) {
        this.numeroDeMinas = numeroDeMinas;
    }
    
}
