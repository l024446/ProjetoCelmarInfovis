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
package projetocelmarinfovis;

import conversaoMDS.TabelaContagemPalavras;
import java.io.File;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.JFileChooser;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author luis
 */
public class TelaPrincipal extends javax.swing.JFrame {
    private double[][] pontos;
    private HashMap <String, Integer> livros;
    private TabelaContagemPalavras contador;
    private double[][] dataset;
    private String[] listaLivros;
    XYSeriesCollection xyDataSet;

    /**
     * Creates new form TelaPrincipal
     */
    public TelaPrincipal() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        areaPlotagem = new javax.swing.JInternalFrame();
        selecionarPasta = new javax.swing.JButton();
        caminhoPasta = new javax.swing.JTextField();
        gerarGrafico = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        areaPlotagem.setVisible(true);

        javax.swing.GroupLayout areaPlotagemLayout = new javax.swing.GroupLayout(areaPlotagem.getContentPane());
        areaPlotagem.getContentPane().setLayout(areaPlotagemLayout);
        areaPlotagemLayout.setHorizontalGroup(
            areaPlotagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        areaPlotagemLayout.setVerticalGroup(
            areaPlotagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 347, Short.MAX_VALUE)
        );

        selecionarPasta.setText("Selecionar Pasta");
        selecionarPasta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selecionarPastaActionPerformed(evt);
            }
        });

        caminhoPasta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caminhoPastaActionPerformed(evt);
            }
        });
        caminhoPasta.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                caminhoPastaPropertyChange(evt);
            }
        });

        gerarGrafico.setText("Gerar Grafico MDS");
        gerarGrafico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gerarGraficoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(areaPlotagem)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(selecionarPasta)
                        .addGap(18, 18, 18)
                        .addComponent(caminhoPasta, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(gerarGrafico)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(areaPlotagem)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(selecionarPasta)
                            .addComponent(caminhoPasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(gerarGrafico)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void selecionarPastaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selecionarPastaActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        File f = chooser.getCurrentDirectory();
        String pasta = f.getAbsolutePath();
        caminhoPasta.setText(pasta);
    }//GEN-LAST:event_selecionarPastaActionPerformed

    private void gerarGraficoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gerarGraficoActionPerformed
        if(caminhoPasta.getText() == ""){
            erroCaminhoPasta();
        }else{
            
            lerArquivos(caminhoPasta.getText());
                
            criarDataset();
            
            plotarGrafico();
        }
        
    }//GEN-LAST:event_gerarGraficoActionPerformed

    private void caminhoPastaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caminhoPastaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_caminhoPastaActionPerformed

    private void caminhoPastaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_caminhoPastaPropertyChange

    }//GEN-LAST:event_caminhoPastaPropertyChange

    private void erroCaminhoPasta(){
        //TODO - implementar erro quando o caminho para a pasta estiver vazio
    }
    

    

    
    private void plotarGrafico(){
        JFreeChart chart = ChartFactory.createScatterPlot(
            "MDS", // chart title
            "", // x axis label
            "", // y axis label
            xyDataSet, // data  ***-----PROBLEM------***
            PlotOrientation.VERTICAL,
            true, // include legend
            true, // tooltips
            false // urls
            );

        // create and display a frame...
        ChartFrame frame = new ChartFrame("First", chart);
        frame.pack();
        frame.setVisible(true);
    }
    
    
        //funcoes auxiliares para controlar as informacoes
    private void inicializaListaLivros(){
        listaLivros = new String[contador.getTotalArquivos()];  
        livros = (HashMap<String, Integer>) contador.getListaArquivos();
        Iterator it = livros.entrySet().iterator();
        int i = 0;
        for (HashMap.Entry<String, Integer> pair : livros.entrySet()) {
            listaLivros[i] = pair.getKey();
            i++;
        }
    }
    
    private void atualizarDataset(){
        
    }
    
    //funcoes para receber dados    
    private void lerArquivos(String pasta)
    {
        File file = new File(pasta);
        contador = new TabelaContagemPalavras();
        contador.lerArquivos(file);
    }
    
    private void criarDataset()
    {
        dataset = new double[contador.getTotalArquivos()][2];
        inicializaListaLivros();
        dataset = contador.getPontosMDS();
        livros = (HashMap<String, Integer>) contador.getListaArquivos();

        xyDataSet = new XYSeriesCollection();
        XYSeries series = new XYSeries("inicial");
        for (int i = 0; i < contador.getTotalArquivos(); i++) {
            series.add(dataset[i][0], dataset[i][1]);
        }
        xyDataSet.addSeries(series);
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JInternalFrame areaPlotagem;
    private javax.swing.JTextField caminhoPasta;
    private javax.swing.JButton gerarGrafico;
    private javax.swing.JButton selecionarPasta;
    // End of variables declaration//GEN-END:variables

      
    
}

