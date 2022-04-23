/* 
 * Copyright (c) 2010-2012 Thiago T. Sá
 * 
 * This file is part of CloudReports.
 *
 * CloudReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * CloudReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * For more information about your rights as a user of CloudReports,
 * refer to the LICENSE file or see <http://www.gnu.org/licenses/>.
 */

package cloudreports.gui;

import cloudreports.simulation.Simulation;
import cloudreports.utils.ElapsedTime;
import cloudreports.utils.FileIO;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.cloudbus.cloudsim.core.CloudSim;

/**
 * The SimulationView form.
 * Most of its code is generated automatically by the NetBeans IDE.
 * 
 * @author      Thiago T. Sá
 * @since       1.0
 */
public class SimulationView extends javax.swing.JDialog {

    /** Creates a new SimulationView form. */
    public SimulationView() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        closeButton = new javax.swing.JButton();
        openFolderButton = new javax.swing.JButton();
        progressBarLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Simulation");
        setLocationByPlatform(true);
        setModal(true);
        setResizable(false);

        closeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cloudreports/gui/resources/close.png"))); // NOI18N
        closeButton.setText("Close");
        closeButton.setMaximumSize(new java.awt.Dimension(109, 36));
        closeButton.setMinimumSize(new java.awt.Dimension(109, 36));
        closeButton.setPreferredSize(new java.awt.Dimension(109, 36));
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        openFolderButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cloudreports/gui/resources/folder.png"))); // NOI18N
        openFolderButton.setText("Open folder");
        openFolderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openFolderButtonActionPerformed(evt);
            }
        });

        progressBarLabel.setText("Simulation is in progress...");

        progressBar.setForeground(new java.awt.Color(44, 132, 184));
        progressBar.setIndeterminate(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(progressBar, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(openFolderButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(progressBarLabel))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {closeButton, openFolderButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(progressBarLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(openFolderButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /** 
     * Stops the simulations and closes the form when the Close button is 
     * clicked.
     *
     * @param   evt     an action event.
     * @since           1.0
     */     
    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
            Simulation.stopSimulation();
            CloudSim.abruptallyTerminate();
        
        dispose();
    }//GEN-LAST:event_closeButtonActionPerformed

    /** 
     * Opens the folder that contains the generated reports when the Open Folder
     * button is clicked.
     *
     * @param   evt     an action event.
     * @since           1.0
     */      
    private void openFolderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openFolderButtonActionPerformed
        String path = FileIO.getPathOfExecutable() + "reports";
        File reportsDirectory = new File(path);
        if (!reportsDirectory.exists() || !reportsDirectory.isDirectory()) return;

        try {
            java.awt.Desktop.getDesktop().open(reportsDirectory);
        } catch (IOException ex) {
            Logger.getLogger(SimulationView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_openFolderButtonActionPerformed

    /** 
     * Sets the state of the progress bar to complete.
     *
     * @param   elapsedTime     the value of elapsed time to be shown on the
     *                          progress bar label.
     * @since                   1.0
     */       
    public void setStateToComplete(ElapsedTime elapsedTime) {
        progressBar.setIndeterminate(false);
        progressBar.setValue(progressBar.getMaximum());
        progressBarLabel.setText("Simulations have completed in " + elapsedTime.toString() + ".");
        openFolderButton.setVisible(true);
    }
    
    /** 
     * Sets the state of the progress bar to in progress.
     *
     * @since   1.0
     */      
    public void setStateToInProgress() {
        progressBar.setIndeterminate(true);
        progressBarLabel.setText("Simulation is in progress...");
        openFolderButton.setVisible(false);
    }
    
    /** 
     * Sets the text of the progress bar label.
     *
     * @since   1.0
     */
    public void setBarLabel(String message) {
        progressBarLabel.setText(message);
    }    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    private javax.swing.JButton openFolderButton;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JLabel progressBarLabel;
    // End of variables declaration//GEN-END:variables
}
