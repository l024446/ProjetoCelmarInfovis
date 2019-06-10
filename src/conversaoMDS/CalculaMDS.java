/* 
 * Trabalho 2 Visualizacao de informacao
 * Luis Miguel Barletta Filho RA 024446 
 * Felipe Tosta Dos Santos RA 171225
 * Jonatas Estevão de Oliveira Bueno RA 159776
 */
package conversaoMDS;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

/**
 *
 * @author luis
 */
public class CalculaMDS {
    private TabelaDistancias distanciaInicial;
    private TabelaDistancias distanciaPlotagem;
    private double[][] stress;
    private TabelaPontosPlotados posicaoPlotagem;
    private int totalPosicoesGeradas;
    Random gerador;
    
    CalculaMDS(TabelaDistancias distancia, TabelaPontosPlotados posicao){
        this.gerador = new Random();
        distanciaInicial = distancia;
        posicaoPlotagem = posicao;
        totalPosicoesGeradas = 0;
        distanciaPlotagem = new TabelaDistancias(posicaoPlotagem, (HashMap<String, Integer>) posicaoPlotagem.getListaArquivos(), posicaoPlotagem.getTotalLinhas());
        this.stress = new double[distanciaPlotagem.getTamanho()][distanciaPlotagem.getTamanho()];   
    }
    
    
    private void comparaMatrizes(){
        int tamanho = distanciaPlotagem.getTamanho();
        double dIni = 0;
        double dEst = 0;
        for (int i=0; i<tamanho; i++){
            for (int j=0; j<tamanho; j++){
                dIni = distanciaInicial.getDistancia(i,j);
                dEst = distanciaPlotagem.getDistancia(i, j);
                stress[i][j] = Math.pow(((dIni*dEst)/(dIni*dIni)),1/2);
            }
        }         
    }
    
    private double medeStress(){
        int tamanho = distanciaPlotagem.getTamanho();
        double retorno = 0;
        for (int i=0; i<tamanho; i++){
            for (int j=0; j<tamanho; j++){
                if(stress[i][j] > retorno){
                    retorno = stress[i][j];
                }
            }
        }
        return retorno;
    }
    
    
    private void calculaPosicao(){
        if(totalPosicoesGeradas == 0){
            posicaoPlotagem.randomPosicoes();
            totalPosicoesGeradas++;
        }else{
            calculaValoresPontos();
        }
        
    }
    
    public TabelaPontosPlotados retornaMatrizPlotagem(){
     return posicaoPlotagem;
    }

    /**
    *TODO - implementar calculo de valores de pontos para o grafico, utilizando o
    * gradient descent (unico algoritimo que encontrei na net)
    */
    private void calculaValoresPontos(){
        
    }
    
}
