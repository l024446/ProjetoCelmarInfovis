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
public class TabelaContagemPalavras extends TabelaBase{
    
    TabelaContagemPalavras(){
        super.MAXLINHAS = 100;
        super.MAXCOLUNAS = 50000;
        super.colunas = new HashMap <String, Integer>();
        super.linhas = new HashMap<String, Integer>();
        super.contLinhas = 0;
        super.contColunas = 0;
        super.dados = new double[MAXLINHAS][MAXCOLUNAS];
    }
    
}
