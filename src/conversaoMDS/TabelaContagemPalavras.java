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
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import mdsj.MDSJ;

/**
 *
 * @author luis
 */
public class TabelaContagemPalavras {
    HashMap<String, Integer> colunas;
    HashMap<String, Integer> linhas;
    int totalColunas;
    int totalLinhas;
    double[][] dados;
    int MAXCOLUNAS;
    int MAXLINHAS;
    double[][] pontosIniciais;
    double[][] distancias;

    //construtor
    public TabelaContagemPalavras() 
    {
        MAXLINHAS = 60;
        MAXCOLUNAS = 50000;
        this.colunas = new HashMap <>();
        this.linhas = new HashMap<>();
        this.totalColunas = 0;
        this.totalLinhas = 0;
        
        this.distancias = new double[MAXLINHAS][MAXLINHAS];
        for(int i =0; i<MAXLINHAS; i++){
            for(int j =0; j<MAXLINHAS; j++){
                distancias[i][j] = 0;
            } 
        }
        
        this.dados = new double[MAXLINHAS][MAXCOLUNAS];
        for(int i =0; i<MAXLINHAS; i++){
            for(int j =0; j<MAXCOLUNAS; j++){
                dados[i][j] = 0;
            } 
        }
      }

    //funções de acesso aos pontos para plotagem
    public double[][] getPontosIniciais()
    {
        calculaPontosIniciais();
        return pontosIniciais;
    }
    
    public double[][] getPontosMDS()
    {
        calculaDistancias();
        double[][] pontosMDS = MDSJ.classicalScaling(dados, 2);
        
        System.out.println("testando distancias depois do getPontosMDS:");
        for(int w = 0; w < distancias[0].length; w++){
            for(int k = 0; k < distancias[0].length; k++){
            System.out.print(distancias[w][k]+"  ");
                
            }
            System.out.println();
        }
               
            
            
        for(int i = 0; i< linhas.size(); i++)
        {
            System.out.println("ponto: ("+pontosMDS[0][i]+ " , "+pontosMDS[1][i]+")"); 
        }
        
        return pontosMDS;
    }

    public int getTotalArquivos()
    {
        return linhas.size();
    }
    
    public int getTotalPalavras()
    {
        return this.totalLinhas;
    }
    
    public Map getListaArquivos()
    {
        return this.linhas;
    }
    
    //funções auxiliares para o calculo dos pontos:
    private void calculaPontosIniciais()
    {
        pontosIniciais = new double[linhas.size()][2];
        for(int i = 0; i<linhas.size(); i++){
            pontosIniciais[i][0] = Math.random();
            pontosIniciais[i][1] = Math.random();
        }
    }

    private void calculaDistancias()
    {      
        for (int i = 0; i< linhas.size(); i++) {
            for (int j = 0; j< linhas.size(); j++) {

                distancias[i][j] = getDistancia(i, j);
                
            }
        }
        
    }
    
    private double getDistancia(Integer linha1, Integer linha2)
    {
        double distancia = 0;
        int raiz = 0;
        while (raiz < colunas.size()) {
            distancia = distancia + Math.pow(dados[linha1][raiz]- dados[linha2][raiz], 2);
            raiz++;
        }
        
        
        distancia = Math.pow (distancia, 1.0 / raiz);
        
        return distancia;
    }
    
    
    public void lerArquivos(final File folder) 
    {
        String nomeArquivo;
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isFile() && fileEntry.getName().endsWith(".txt")) {
                nomeArquivo = fileEntry.getName();
                incluiArquivo(nomeArquivo);
                lerLinhasdoArquivo (nomeArquivo, fileEntry);
                }
            }
    }
    
    private void lerLinhasdoArquivo(String nomeArquivo, final File arquivo)
    {
        String palavras;
        try {
            Scanner scanner = new Scanner(arquivo);
            while (scanner.hasNextLine()){
                palavras = scanner.nextLine().replaceAll("[^a-zA-Z ]", "").toLowerCase();
                lerPalavrasDaLinha(nomeArquivo, palavras);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LerArquivosDaPasta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void lerPalavrasDaLinha(String nomeArquivo, String linha)
    {
        String[] palavras = linha.split(" ");
        contarPalavras(nomeArquivo, palavras);
    }
    
    private void contarPalavras(String nomeArquivo, String [] palavras)
    {
        for (String palavra : palavras) {
            incluiPalavra(nomeArquivo, palavra);
        }
    }
    
    public void incluiPalavra (String arquivo, String palavra)
    {
        
        if (!colunas.containsKey(palavra)){
            addColuna(palavra);
        }
        Integer lin = linhas.get(arquivo);
        Integer col = colunas.get(palavra);
        
        dados[lin][col]++;
    }

    
    //funções para incluir informações no mapa de arquivos ou palavras
    public void incluiArquivo(String nome)
    {
        if (!linhas.containsKey(nome)){
            addLinha(nome);
        }
    }

    private void addLinha(String nomeLinha)
    {
        linhas.put(nomeLinha, totalColunas);
        totalColunas++;
    }
  
    private void addColuna(String nomeColuna)
    {
        colunas.put(nomeColuna, totalLinhas);
        totalLinhas++;
    }
}
