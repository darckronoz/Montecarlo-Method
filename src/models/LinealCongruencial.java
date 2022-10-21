package models;


import java.util.ArrayList;

public class LinealCongruencial {

    private int seed;
    private int a;
    private int m;
    private int c;
    private int numerosAGenerar;
    private ArrayList<Integer> xi;
    private  ArrayList <Double> ri;
    private ArrayList<Double> uniformNi;
    
    /**
     * 
     * @param seed Valor de la semilla inicial
     * @param k Valor de K
     * @param c Valor de C
     * @param g Valor G
     * @param numerosAGenerar Cantidad de Numeros a Generar
     */

    public LinealCongruencial(int seed, int k, int g, int c, int numerosAGenerar){
        this.a = 2*k + 1;
        this.m = (int) Math.pow(2, g);
        this.c = c;
        this.numerosAGenerar = numerosAGenerar;
        this.xi = new ArrayList<>();
        this.ri = new ArrayList<>();
        this.uniformNi = new ArrayList<>();
        this.seed = seed;
    }

    /**
     * 
     * @param x Semilla para la generacion de nuevas semillas
     * @return nueva semilla
     */
    public int calculateXi(int x){
        return (a*x + c) % m;
    }

    /**
     * 
     * @param x numero para calcular el numero aleatorio 
     * @return nuevo numero aleatorio entre 0 y 1
     */
    public double calculateRi(int x){
        return (double) x/(m-1);
    }
    
    /**
     * 
     * @param a numero menor del intervalo
     * @param b numero maximo del intervalo
     * @param xi numero que representa una semilla
     * @return nuevo numero aleatorio entre 0 y 1
     */

    public double generateUniformNi(int a, int b, int xi){
        return a + ( b- a) * (calculateRi((xi)));
    }

    /**
     * metoddo que calcula x cantidad numeros Xi y los guarda en una lista Xi
     */
    public void fillXi(){
        xi.add(calculateXi(seed));
        for (int i = 0; i <numerosAGenerar; i++){
            xi.add(calculateXi(xi.get(i)));
        }
    }

    /**
     * metoddo que  calcula x cantidad numeros Ri y los guarda en una lista Ri
     */
    public void fillRi(){
        for (int i = 0; i <numerosAGenerar; i++){
            ri.add(calculateRi(xi.get(i)));
        }
    }

    /**
     * metoddo que calcula x cantidad numeros Ni uniformes y los guarda en una lista Ni uniformes
     */
    public void fillUniformNi(int a, int b){
        for (int i = 0; i < numerosAGenerar; i++){
            uniformNi.add(generateUniformNi(a,b,xi.get(i)));
        }
    }

    /**
     * @return metoddo que retorna una lista de Xi
     */
    public ArrayList<String> getXi(){
        ArrayList <String> aux = new ArrayList<>();
        for (Integer i: xi) {
            aux.add(String.valueOf(i));
        }
        return aux;
    }

    /**
     * @return metoddo que retorna una lista de Ri
     */
    public ArrayList<Double> getRi(){
        return ri;
    }

    /**
     * @return metoddo que retorna una lista de Ni uniformes
     */
    public ArrayList<Double> getNiUniforme() {
        return uniformNi;
    }
}
