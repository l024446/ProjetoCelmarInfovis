/* 
 * Trabalho 2 Visualizacao de informacao
 * Luis Miguel Barletta Filho RA 024446 
 * Felipe Tosta Dos Santos RA 171225
 * Jonatas Estevão de Oliveira Bueno RA 159776
 */
package conversaoMDS;

/*
 * Copyright (C) 2019 luis
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.BiConsumer;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author luis
 */
public class TabelaBase {
    Map colunas;
    Map linhas;
    int contLinhas;
    int contColunas;
    double[][] dados;
    int MAXCOLUNAS;
    int MAXLINHAS;

    public TabelaBase() {
        MAXLINHAS = 100;
        MAXCOLUNAS = 50000;
        this.colunas = new HashMap <String, Integer>();
        this.linhas = new HashMap<String, Integer>();
        this.contLinhas = 0;
        this.contColunas = 0;
        dados = new double[MAXLINHAS][MAXCOLUNAS];
      }


  
    public void incluiArquivoParaContagem(String nome){
        if (!linhas.containsKey(nome)){
            addLinha(nome);
        }
    }

    public void incluiPalavra (String arquivo, String palavra){
        if (!colunas.containsKey(palavra)){
            inicializaColuna(palavra);
        }
        Integer lin = (Integer) linhas.get(arquivo);
        Integer col = (Integer) colunas.get(palavra);
        
        dados[col][lin]++;
      
    }
  
    private void addLinha(String nomeLinha){
        linhas.put(nomeLinha, contLinhas);
        contLinhas++;
    }
  
    private void addColuna(String nomeColuna){
        colunas.put(nomeColuna, contColunas);
        contColunas++;
    }

    private void inicializaColuna(String palavra){
        addColuna(palavra);
        Integer col = (Integer) colunas.get(palavra);
        for(int i=0; i<MAXLINHAS; i++){
            dados[i][col.intValue()] = 0;
        }
    }
  
    public Map getListaArquivos(){
        return this.linhas;
    }
    
    public double getDistancia(String linha1, String linha2){
        double distancia = 0;
        int raiz = 0;
        Iterator it;
        it = colunas.entrySet().iterator();
        
        while(it.hasNext()){
            Map.Entry coluna = (Map.Entry)it.next();
            distancia = distancia + dados[Integer.valueOf(linhas.get(linha1).toString())][Integer.valueOf(coluna.getValue().toString())]*dados[Integer.valueOf(linhas.get(linha1).toString())][Integer.valueOf(coluna.getValue().toString())];
            raiz++;
        }
        
        distancia = Math.pow (distancia, 1.0 / raiz);
        
        return distancia;
    }
    
    public int getTotalLinhas(){
        return this.contLinhas;
    }
}
