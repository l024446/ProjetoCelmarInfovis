/* 
 * Trabalho 2 Visualizacao de informacao
 * Luis Miguel Barletta Filho RA 024446 
 * Felipe Tosta Dos Santos RA 171225
 * Jonatas Estevão de Oliveira Bueno RA 159776
 */
package conversaoMDS;

import java.util.HashMap;

/**
 *
 * @author luis
 */
public class TabelaDistancias {
    private TabelaBase entrada;
    private HashMap<String, Integer> listaArquivos;
    private double[][] distancias;
    
    TabelaDistancias(TabelaBase entrada, HashMap<String, Integer> listaArquivos, int totalArquivos){
        this.entrada = entrada;
        this.listaArquivos = listaArquivos;
        distancias = new double[totalArquivos][totalArquivos];
        calculaDistancias();
    }
    
    
    
    public void calculaDistancias(){
        listaArquivos.forEach((k,v)->{
            listaArquivos.forEach((j,w)->{
               distancias[v][w] = distanciaEuclidiana(k,j);
                
            });
        });
    }
    
    private double distanciaEuclidiana(String k, String j){
        return entrada.getDistancia(k, j);
    }
    
    public double getDistancia(int linha, int coluna){
        return distancias[linha][coluna];
    }
    
    public void setDistancia(int linha, int coluna, double valor){
        distancias[linha][coluna] = valor;
    }
    

    public int getTamanho(){
        int tamanho = listaArquivos.size();
        return tamanho;
    }

    
}
