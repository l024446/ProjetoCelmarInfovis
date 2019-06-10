/* 
 * Trabalho 2 Visualizacao de informacao
 * Luis Miguel Barletta Filho RA 024446 
 * Felipe Tosta Dos Santos RA 171225
 * Jonatas Estevão de Oliveira Bueno RA 159776
 */
package conversaoMDS;

import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author luis
 */
public class TabelaPontosPlotados extends TabelaBase{
    Random rand;
    int totalVersoesTabela;
    {
        super.MAXLINHAS = 100;
        super.MAXCOLUNAS = 2;
        super.colunas = new HashMap <String, Integer>();
        colunas.put("x", 0);
        colunas.put("y", 1);
        super.linhas = new HashMap<String, Integer>();
        super.contLinhas = 0;
        super.contColunas = 0;
        super.dados = new double[MAXLINHAS][MAXCOLUNAS];
        rand = new Random();
        totalVersoesTabela = 0;
    }
    
    public void randomPosicoes(){
            for(int i=0; i<contLinhas; i++){
                dados[i][0] = rand.nextDouble();
                dados[i][1] = rand.nextDouble();
            }
    }
    
}
