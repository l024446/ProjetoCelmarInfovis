/* 
 * Trabalho 2 Visualizacao de informacao
 * Luis Miguel Barletta Filho RA 024446 
 * Felipe Tosta Dos Santos RA 171225
 * Jonatas Estevão de Oliveira Bueno RA 159776
 */
package conversaoMDS;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis
 */
public class LerArquivosDaPasta{

    public void lerArquivos(final File folder, TabelaContagemPalavras contagemPalavras) {
        String nomeArquivo;
        for (final File fileEntry : folder.listFiles()) {
            if (!fileEntry.isDirectory()) {
                nomeArquivo = fileEntry.getName();
                contagemPalavras.incluiArquivoParaContagem(nomeArquivo);
                lerLinhasdoArquivo (nomeArquivo, fileEntry, contagemPalavras);
                }
            }
        }
    
    private void lerLinhasdoArquivo(String nomeArquivo, final File arquivo, TabelaContagemPalavras contagemPalavras){
        try {
            Scanner scanner = new Scanner(arquivo);
            while (scanner.hasNextLine()){
                lerPalavrasDaLinha(nomeArquivo, scanner.nextLine(), contagemPalavras);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LerArquivosDaPasta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void lerPalavrasDaLinha(String nomeArquivo, String linha, TabelaContagemPalavras contagemPalavras){
        String [] palavras;
        palavras = linha.split(" ");
        contarPalavras(nomeArquivo, palavras, contagemPalavras);
    }
    
    private void contarPalavras(String nomeArquivo, String [] palavras, TabelaContagemPalavras contagemPalavras){
        for (String palavra : palavras) {
            contagemPalavras.incluiPalavra(nomeArquivo, palavra);
        }
    }
}
